package com.fnoor.Events;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class EV_08 {

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

    @Parameters({"groupTicketsPayPal"})
    @Test(groups = { "events" })
    public static void groupTicketsPayPal(String testId) throws InterruptedException, IOException {

        page.ensAuthTestEvent();
        driver.get("https://politicalnetworks.com/page/12628/event/1");

        fields.selectTitle("Ms.");
        fields.setFirstname("Event");
        fields.setLastname("Group PayPal");
        //		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.addMultipleTickets();
        fields.addAdditionalDonation("101.99");
        fields.eventCheckout();

        fields.waitForURLToChange("https://politicalnetworks.com/page/12628/event/2");
        fields.verifyEventSummary("100.00 USD");
        fields.verifyEventSummary("101.99 USD");
        fields.selectPaymentType("Paypal");
        fields.submit();

        fields.waitForPageLoadPayPal();
        fields.waitForURLToChange("https://www.sandbox.paypal.com/");
        //		Assert that the payment is redirected to Paypal page
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Paypal", myurl.contains("https://www.sandbox.paypal.com/"));
//        fields.waitForPageLoad();
//        fields.logPaypal();
        fields.waitForPageLoad();
        fields.setPaypalEmail();
        fields.nextPayapl();
        fields.waitForPageLoad();
        fields.setPaypalPassword();
        fields.submitPaypal();
        fields.waitForPageLoadPayPal();
        Thread.sleep(6000);

        try {
            Assert.assertTrue("Didn't redirect to submit payment page, Paypal", driver.getCurrentUrl()
                    .contains("https://www.sandbox.paypal.com/"));
            WebElement paypalContinue = (new WebDriverWait(driver, 100))
                    .until(ExpectedConditions.visibilityOfElementLocated
                            (By.xpath("//button[contains(text(), 'Continue')]")));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", paypalContinue);

        } catch (StaleElementReferenceException e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(8000);
        fields.waitForPageLoad();

//		Assert that the payment was successful and the third page was reached
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12628/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8325"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Tickets Amount is incorrect/not present", bodytext.contains("$201.99"));
        Assert.assertTrue("Additional Donation Amount is incorrect/not present", bodytext.contains("101.99"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Paypal"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "groupTicketsPayPal", fields);
        page.getSupporterById(FUNDRAISING_TEST = "groupTicketsPayPal", fields);

    }
}
