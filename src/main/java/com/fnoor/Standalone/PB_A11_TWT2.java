package com.fnoor.Standalone;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class PB_A11_TWT2 {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void singleDB(String testId, PageFields fields, WebDriver driver) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/11071/tweet/1?mode=DEMO");

        fields.selectTitle("Miss");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("2001 S Street NW");
        fields.setCity("Washington DC");
        fields.selectRegion("DC");
        fields.setPostCode("WC2N 5DU");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");

        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://politicalnetworks.com/page/11071/tweet/2"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address 1 is incorrect/ not present ", bodytext.contains("2001 S Street NW"));
        Assert.assertTrue("City is incorrect/ not present", bodytext.contains("Washington DC"));
        Assert.assertTrue("Postcode is incorrect/ not present", bodytext.contains("WC2N 5DU"));
        Assert.assertTrue("Region is incorrect/ not present", bodytext.contains("DC"));
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