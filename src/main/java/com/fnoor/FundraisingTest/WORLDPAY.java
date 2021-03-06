package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class WORLDPAY {

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
        driver.manage().timeouts().pageLoadTimeout(600, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"worldpayCCSingle"})
    @Test(groups = { "worldpay" })
    public static void worldpayCCSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/842/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Worldpay");
        fields.setLastname("Single");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4444333322221111");
        fields.setCCExpiry(new CharSequence[] {"12", "2024"});
        fields.setCCV("123");

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.setRegion("MD");
        fields.setPostCode("20001");
        fields.setCountry("US");

        fields.submit();


        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/842/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3511"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RBS Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA-SSL"));

        page.getSupporterByEmail(FUNDRAISING_TEST="WorldpayCCSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="WorldpayCCSingle", fields);
    }

    @Parameters({"worldpayCCRecurring"})
    @Test(groups = { "worldpay" })
    public static void worldpayCCRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/862/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Worldpay");
        fields.setLastname("Recurring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("5");
        fields.setRecurPeriod("6");

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4444333322221111");
        fields.setCCExpiry(new CharSequence[] {"12", "2022"});
        fields.setCCV("123");

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/862/donate/2"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3512"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RBS Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA-SSL"));

        page.getSupporterByEmail(FUNDRAISING_TEST="WorldpayCCRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="WorldpayCCRecurring", fields);
    }

    @Parameters({"worldpay3DSecureTest"})
    @Test(groups = { "worldpay" })
    public static void worldpay3DSecureTest(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        driver.get("https://politicalnetworks.com/page/863/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Worldpay");
        fields.setLastname("Single3D");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("3D");
        fields.setCCNUmber("5454545454545454");
        fields.setCCExpiry(new CharSequence[] {"12", "2021"});
        fields.setCCV("123");

        fields.submit();
        //Validate 3D secure page
        fields.waitForPageLoad();
        Assert.assertTrue(driver.getCurrentUrl()
                .contains("https://secure-test.worldpay.com/jsp/test/shopper/ThreeDResponseSimulator.jsp?orderCode="));
        String securetext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("3d Amount is incorrect/not present", securetext.contains("USD 15.00"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@class='lefty']"));
        submitButton.click();
        Thread.sleep(800);
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/863/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3513"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RBS Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA-SSL"));

        page.getSupporterByEmail(FUNDRAISING_TEST="worldpay3DSecureTest", fields);
        page.getSupporterById(FUNDRAISING_TEST="worldpay3DSecureTest", fields);
    }

    @Parameters({"worldpay3DRecurring"})
    @Test(groups = { "worldpay" })
    public static void worldpay3DRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        driver.get("https://politicalnetworks.com/page/10877/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Worldpay");
        fields.setLastname("Recurring3D");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurFreq("MONTHLY");
        fields.setRecurDay("23");

        fields.setCCName("3D");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[] {"12", "2021"});
        fields.setCCV("123");

        fields.submit();
        //Validate 3D secure page
        fields.waitForPageLoad();
        Assert.assertTrue(driver.getCurrentUrl()
                .contains("https://secure-test.worldpay.com/jsp/test/shopper/ThreeDResponseSimulator.jsp?orderCode="));
        String securetext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("3d Amount is incorrect/not present", securetext.contains("USD 15.00"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@class='lefty']"));
        submitButton.click();
        Thread.sleep(1000);
        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10877/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("6306"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RBS Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA-SSL"));

        page.getSupporterByEmail(FUNDRAISING_TEST="worldpay3DRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="worldpay3DRecurring", fields);
    }

    @Parameters({"worldpayPaypalCCSingle"})
    @Test(groups = { "worldpay" })
    public static void worldpayPaypalCCSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10893/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Worldpay");
        fields.setLastname("Single");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4444333322221111");
        fields.setCCExpiry(new CharSequence[] {"12", "2024"});
        fields.setCCV("123");

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.setRegion("MD");
        fields.setPostCode("20001");
        fields.setCountry("US");

        fields.submit();


        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals
                ("https://politicalnetworks.com/page/10893/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("6323"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RBS Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA-SSL"));

        page.getSupporterByEmail(FUNDRAISING_TEST="worldpayPaypalCCSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="worldpayPaypalCCSingle", fields);

        //Worldpay pay via PayPal
        driver.get("https://politicalnetworks.com/page/10893/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Worldpay");
        fields.setLastname("SinglePaypal");
//		Call the createEmail function
        String new_email_paypal = fields.createEmail(testId);
        fields.setEmailAddress(new_email_paypal);

        fields.submit();

        fields.selectPaymentType("Paypal");

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.setRegion("MD");
        fields.setPostCode("20001");
        fields.setCountry("US");

        fields.submit();

        fields.waitForPageLoadPayPal();
        fields.waitForURLToChange("https://www.sandbox.paypal.com/");
        //		Assert that the payment is redirected to Paypal page

        Assert.assertTrue("Didn't redirect to Paypal", driver.getCurrentUrl().contains
                ("https://www.sandbox.paypal.com/"));
//        fields.waitForPageLoad();
//        fields.logPaypal();
        fields.waitForPageLoad();
        fields.setPaypalEmail();
        fields.nextPayapl();
        fields.waitForPageLoad();
        fields.setPaypalPassword();
        fields.submitPaypal();
        fields.waitForPageLoadPayPal();
        Thread.sleep(4000);

        WebElement paypalContinue = (new WebDriverWait(driver, 400))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("payment-submit-btn")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", paypalContinue);

        fields.waitForPageLoad();
        Thread.sleep(8000);


        //		Assert that the payment was successful and the third page was reached
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl().equals
                ("https://politicalnetworks.com/page/10893/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytextPaypal = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytextPaypal.contains("6323"));
        Assert.assertTrue("Gateway details are incorrect/not present",
                bodytextPaypal.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytextPaypal.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytextPaypal.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytextPaypal.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytextPaypal.contains("TEST: Paypal"));

    }
}
