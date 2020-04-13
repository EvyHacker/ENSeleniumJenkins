package com.fnoor.Redirects;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class PB_B6_DON2TWEET {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void donationToTweet(String testId, PageFields fields, WebDriver driver) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/12596/donate/1?mode=DEMO");

        fields.selectTitle("Miss");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");
        fields.submit();

        //Validate redirect to donation page
        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/12596/donate/2"));

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //Validate redirect to tweet page
        Assert.assertTrue("Didn't redirect to tweet page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/11070/tweet/1?chain"));

        Assert.assertTrue("First Name is missing or incorrect", fields.getSupFirstName().equals("Unit"));
        Assert.assertTrue("Last Name  is missing or incorrect", fields.getSupLastName().equals("Tester"));
        Assert.assertTrue("Email address is missing or incorrect", fields.getSupEmail().equals(new_email));
        fields.setAppealCode("testAppealCode");

        fields.submit();

        fields.waitForPageLoad();

        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/11070/tweet/2"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address 1 is incorrect/ not present ", bodytext.contains("1 Hilltop"));
        Assert.assertTrue("City is incorrect/ not present", bodytext.contains("Baltimore"));
        Assert.assertTrue("Postcode is incorrect/ not present", bodytext.contains("20001"));
        Assert.assertTrue("Region is incorrect/ not present", bodytext.contains("MD"));
        Assert.assertTrue("Country is incorrect/ not present", bodytext.contains("US"));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));

        fields.clickTweetButton();

        //Assert that the transaction went through
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles.size());
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                fields.waitForURLToChange("twitter");
////		         <!--Perform your operation here for new window-->
                String twitterURL = driver.getCurrentUrl();
                Assert.assertTrue("Urls are not the same", twitterURL.contains("https://twitter.com"));
                driver.close(); //closing child window
                driver.switchTo().window(parentWindow); //cntrl to parent window
            }
        }

    }
}
