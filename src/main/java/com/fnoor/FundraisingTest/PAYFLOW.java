package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class PAYFLOW {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void payflowProSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10879/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setOtherAmt1("other amount test 1");
        fields.setOtherAmt2("other amount test 2");
        fields.setOtherAmt3("other amount test 3");
        fields.setOtherAmt4("other amount test 4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10879/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("6308"));
        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Payflow Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="payflowProSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="payflowProSingle", fields);
    }

    public static void payflowProRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10880/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        //fields.clickRecurringPaymentchkbox();
        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10880/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("6309"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Payflow Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="payflowProRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="payflowProRecurring", fields);
    }

    public static void payflowProPayViaPaypal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10887/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.clickRecurringSinglePaymentchkbox();

        fields.selectPaymentType("Paypal");
        fields.selectPayCurrency("USD");

        fields.submit();

        fields.waitForPageLoad();
//        String myurl = driver.getCurrentUrl();
//        if (myurl.contains("#/checkout")) {
//            fields.waitForPageLoad();
//        } else {
            //Submit Paypal payment
            fields.setPaypalEmail();
            fields.nextPayapl();
            fields.waitForPageLoad();
            fields.setPaypalPassword();
            fields.submitPaypal();
            //fields.waitForPageLoad();

        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

        Assert.assertTrue("You didnt submit the payment",
                driver.getCurrentUrl().contains("https://www.sandbox.paypal.com/webapps/hermes?flow=1-P&ulReturn=true&token="));

            WebElement paypalContinue = (new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.presenceOfElementLocated
                            (By.id("payment-submit-btn")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", paypalContinue);
//        paypalContinue.click();
//        paypalContinue.submit();
            fields.waitForPageLoad();

            //		Assert that the payment was successful and the third page was reached

            Assert.assertTrue("Urls are not the same",
                    driver.getCurrentUrl().equals("https://politicalnetworks.com/page/10887/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue("Campaign ID not present", bodytext.contains("6317"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Payflow Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Paypal"));

            page.getSupporterByEmail(FUNDRAISING_TEST = "payflowProPayViaPaypal", fields);
            page.getSupporterById(FUNDRAISING_TEST = "payflowProPayViaPaypal", fields);
        }
}
