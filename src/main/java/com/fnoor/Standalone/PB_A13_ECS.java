package com.fnoor.Standalone;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PB_A13_ECS {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void event(String testId, PageFields fields, WebDriver driver) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/11073/event/1");

        fields.selectTitle("Miss");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");

        fields.verifyEventblockPresent();
        // make additional donation
        fields.addEventTkt();
        String newQuantity = fields.getTktQuantity();
        Assert.assertTrue("Second ticket hasn't been added to your cart, please try again "
                , newQuantity.equals("2"));
        fields.removeEventTkt();
        String removeEventTicket = fields.getTktQuantity();
        Assert.assertTrue("Second ticket hasn't been removed from your cart, please try again "
                , removeEventTicket.equals("1"));
        fields.additionalAmtEvent("50");

        fields.eventCheckout();
        fields.waitForPageLoad();

        Assert.assertTrue(driver.getCurrentUrl().contains("https://politicalnetworks.com/page/11073/event/2"));
        fields.attendeeTicketFN("Atendee 1");
        fields.attendeeTicketLN("Atendee 2");
        fields.attendeeTicketEmail("testid_atendee1@tellamazingstories.com");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://politicalnetworks.com/page/11073/event/3"));
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
    }
}