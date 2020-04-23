package com.fnoor.Standalone;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PB_A15_PTM {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void premiumDonationSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/12115/shop/1");

        fields.selectDonationAmt("5");
        String premGift = fields.getPremGiftName();
        Assert.assertEquals(premGift, "Pen - Premium");

        fields.selectDonationAmt("1");
        premGift = fields.getPremGiftName();
        Assert.assertEquals(premGift, null);

        fields.selectDonationAmt("15");
        premGift = fields.getPremGiftName();
        Assert.assertEquals(premGift, "Coffee mug - Premium");

        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.submit();

        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        Assert.assertTrue(driver.getCurrentUrl().contains("https://politicalnetworks.com/page/12115/shop/2"));

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://politicalnetworks.com/page/12115/shop/3"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Campaign ID not present", bodytext.contains("7752"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("VISA"));

    }
}
