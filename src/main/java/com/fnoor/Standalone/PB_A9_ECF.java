package com.fnoor.Standalone;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PB_A9_ECF {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void ecardValidation(String testId, PageFields fields, WebDriver driver) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/11074/action/1");

        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        fields.setAddress1("2001 S Street NW");
        fields.setCity("Washington DC");
        fields.selectRegion("DC");
        fields.setPostCode("20009");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");

        fields.selectEcardDesign();
        fields.addEcardMessage("Hi");
        fields.addEcardrecipient("Evy");
        fields.addEcardRecipientEmail("testid_ecard@tellamazingstories.com");
        fields.addEcardRecipienttoList();
        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://politicalnetworks.com/page/11074/action/2"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address 1 is incorrect/ not present ", bodytext.contains("2001 S Street NW"));
        Assert.assertTrue("City is incorrect/ not present", bodytext.contains("Washington DC"));
        Assert.assertTrue("Postcode is incorrect/ not present", bodytext.contains("20009"));
        Assert.assertTrue("Region is incorrect/ not present", bodytext.contains("DC"));
        Assert.assertTrue("Country is incorrect/ not present", bodytext.contains("US"));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));

    }
}
