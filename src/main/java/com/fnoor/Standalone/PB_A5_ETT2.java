package com.fnoor.Standalone;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class PB_A5_ETT2 {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void singleDBETT(String testId, PageFields fields, WebDriver driver) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/11069/action/1?mode=DEMO");

        fields.selectTitle("Miss");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("2001 S Street NW");
        fields.setCity("Washington DC");
        fields.selectRegion("DC");
        fields.setPostCode("W71NG");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");

        fields.submit();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://politicalnetworks.com/page/11069/action/2"));
        Assert.assertTrue("Target block not present", fields.verifyTargetblockIsPresent());

        String customMessage = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Contact info not found ", customMessage.contains("Mr Steve Pound"));
        Assert.assertTrue("Supporter details not found in Target message", customMessage.contains("Unit Tester"));

        fields.submit();

        String myurlfinalUrl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinalUrl.equals("https://politicalnetworks.com/page/11069/action/3"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address 1 is incorrect/ not present ", bodytext.contains("2001 S Street NW"));
        Assert.assertTrue("City is incorrect/ not present", bodytext.contains("Washington DC"));
        Assert.assertTrue("Postcode is incorrect/ not present", bodytext.contains("W71NG"));
        Assert.assertTrue("Region is incorrect/ not present", bodytext.contains("DC"));
        Assert.assertTrue("Country is incorrect/ not present", bodytext.contains("US"));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));

    }
}
