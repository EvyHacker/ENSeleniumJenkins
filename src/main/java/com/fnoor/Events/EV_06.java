package com.fnoor.Events;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class EV_06 {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static String FUNDRAISING_TEST;
    public static WebDriver driver;
    static PageFields fields;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun=true)
    public void setUp(String browser) throws MalformedURLException {
        driver = page.createInstance(browser);
        fields = PageFactory.initElements(driver, PageFields.class);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"multiFreeTicketsIATS"})
    @Test(groups = { "events" })
    public static void multiFreeTicketsIATS(String testId) throws InterruptedException, IOException {

        page.ensAuthTestEvent();
        driver.get("https://politicalnetworks.com/page/12626/event/1");

        fields.selectTitle("Ms.");
        fields.setFirstname("Event");
        fields.setLastname("Free IATS");
        //		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.addMultipleTickets();
        fields.eventCheckout();

        fields.waitForURLToChange("https://politicalnetworks.com/page/12626/event/2");
        fields.verifyEventSummary("0.00 USD");
        fields.addAttendee1FN("Attendee 1 FN");
        fields.addAttendee1LN("Attendee 1 LN");
        String attendee_email = fields.createAttendeeEmail(testId);
        fields.addAttendee1Email(attendee_email);
        fields.addAttendee2FN("Attendee 2 FN");
        fields.addAttendee2LN("Attendee 2 LN");
        fields.addAttendee2Email(attendee_email);
        fields.submit();

//		Assert that the payment was successful and the third page was reached
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12626/event/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8323"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Tickets Amount is incorrect/not present", bodytext.contains("$0.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CASH"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: cash"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "multiFreeTicketsIATS", fields);
        page.getSupporterById(FUNDRAISING_TEST = "multiFreeTicketsIATS", fields);

    }
}
