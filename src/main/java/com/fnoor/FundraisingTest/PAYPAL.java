package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class PAYPAL {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static String FUNDRAISING_TEST;
    public static WebDriver driver;
    static PageFields fields;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun=true)
    public void setUp(String browser) throws MalformedURLException {
        driver = page.createInstance(browser);
        fields = PageFactory.initElements(driver, PageFields.class);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"paypalPaymentsProSingle"})
    @Test(groups = { "paypal" })
    public static void paypalPaymentsProSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        driver.get("https://politicalnetworks.com/page/843/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("ProSingle");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.submit();

        fields.selectPaymentType("Visa");
        // fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4032034810451941");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();


        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/843/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3515"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paypalPaymentsProSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="paypalPaymentsProSingle", fields);
    }

    @Parameters({"payPalPaymentsProRecurring"})
    @Test(groups = { "paypal" })
    public static void payPalPaymentsProRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        driver.get("https://politicalnetworks.com/page/865/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("ProRecurring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.submit();

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4032034810451941");
        fields.setCCExpiry(new CharSequence[] {"12", "2021"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/865/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3516"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="payPalPaymentsProRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="payPalPaymentsProRecurring", fields);
    }

    @Parameters({"payViaPayPalSingle"})
    @Test(groups = { "paypal" })
    public  static void payViaPayPalSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/844/donate/1?mode=DEMO");


        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("PaypalSingle");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.selectPaymentType("Paypal");
        fields.selectPayCurrency("USD");
        fields.setCCV("123");

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
        Thread.sleep(4000);

        WebElement paypalContinue = (new WebDriverWait(driver, 400))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("payment-submit-btn")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", paypalContinue);

        fields.waitForPageLoad();
        Thread.sleep(8000);
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/844/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3517"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Paypal"));

        page.getSupporterByEmail(FUNDRAISING_TEST="payViaPayPalSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="payViaPayPalSingle", fields);
    }

    @Parameters({"payViaPayPalRecurring"})
    @Test(groups = { "paypal" })
    public static void payViaPayPalRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/866/donate/1?mode=DEMO");


        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("PaypalRecurring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.setRecurDay("23");
        fields.selectPaymentType("Paypal");
        fields.selectPayCurrency("USD");

        fields.submit();
        fields.waitForPageLoadPayPal();
//        fields.noLogPaypal();
//        fields.waitForPageLoad();
        fields.setPaypalEmail();
        fields.nextPayapl();
        fields.waitForPageLoad();
        fields.setPaypalPassword();
        fields.submitPaypal();
        fields.waitForPageLoadPayPal();
        Thread.sleep(4000);
            try {
                WebElement paypalContinue = (new WebDriverWait(driver, 60))
                        .until(ExpectedConditions.visibilityOfElementLocated
                                (By.xpath("//*[contains(text(), 'Continue')]")));
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", paypalContinue);

            } catch (StaleElementReferenceException e) {
                System.err.println(e.getMessage());
            }

            fields.waitForPageLoadPayPal();
            WebElement paypalAgree = (new WebDriverWait(driver, 30))
                    .until(ExpectedConditions.presenceOfElementLocated
                            (By.xpath("//*[contains(text(), 'Agree & Continue')]")));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", paypalAgree);
            Thread.sleep(2000);
            fields.waitForPageLoadPayPal();

            //		Assert that the payment was successful and the third page was reached

            Assert.assertTrue("Urls are not the same",
                    driver.getCurrentUrl().equals("https://politicalnetworks.com/page/866/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue("Campaign ID not present", bodytext.contains("3518"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Paypal"));

            page.getSupporterByEmail(FUNDRAISING_TEST = "payViaPayPalRecurring", fields);
            page.getSupporterById(FUNDRAISING_TEST = "payViaPayPalRecurring", fields);
        }

    // Can not validate via email for the transaction when sending API call
    @Parameters({"paypalCardinalComSingle3D"})
    @Test(groups = { "paypal" })
    public static void paypalCardinalComSingle3D(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/13376/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("Cardinal3DSingle");
//		Call the createEmail function
        String new_email = fields.createRSMemail(testId);
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
        fields.setCCNUmber("4000000000000002");
        fields.setCCExpiry(new CharSequence[]{"12", "2021"});
        fields.setCCV("123");
        fields.clickRecurringSinglePaymentchkbox();

        fields.submit();

        //Assert donation amount and submit payment
        fields.waitForPageLoad();
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        String labeltext = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/b/font"))
                .getText();
        Assert.assertTrue("Donation Amount displayed is incorrect", labeltext.contains("$15.00"));
        WebElement myPasswordDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("password")));
        myPasswordDynamicElement.sendKeys("1234");
        WebElement mySubmitDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("UsernamePasswordEntry")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", mySubmitDynamicElement);
        Thread.sleep(2000);
        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13376/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("9227"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmailRSM(FUNDRAISING_TEST = "paypalCardinalComSingle3D", fields);
        page.getSupporterById(FUNDRAISING_TEST="paypalCardinalComSingle3D", fields);
    }

    @Parameters({"paypalViaPayPalCardinalComSingle3D"})
    @Test(groups = { "paypal" })
    public static void paypalViaPayPalCardinalComSingle3D(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.navigate().to("https://politicalnetworks.com/page/13376/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("PaypalCardinal3D");
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

        fields.selectPaymentType("Paypal");
        fields.clickRecurringSinglePaymentchkbox();

        fields.submit();

        fields.waitForPageLoadPayPal();
//        fields.logPaypal();
//        fields.waitForPageLoad();
        fields.setPaypalEmail();
        fields.nextPayapl();
        fields.waitForPageLoad();
        fields.setPaypalPassword();
        fields.submitPaypal();
        fields.waitForPageLoadPayPal();

        Thread.sleep(6000);
        WebElement paypalAmount = (new WebDriverWait(driver, 400))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("Cart_cartAmount_4dnoL")));
        Assert.assertTrue("Donation amount displayed is incorrect", paypalAmount.getText().contains("$15"));
        WebElement paypalContinue = (new WebDriverWait(driver, 600))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("payment-submit-btn")));
        try{
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", paypalContinue);
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }

        fields.waitForPageLoadPayPal();

        //		Assert that the payment was successful and the third page was reached
        Thread.sleep(8000);
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl().equals("https://politicalnetworks.com/page/13376/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("9227"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Paypal"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paypalViaPayPalCardinalComSingle3D", fields);
        page.getSupporterById(FUNDRAISING_TEST="paypalViaPayPalCardinalComSingle3D", fields);
    }

    @Parameters({"paypalCardinalComRecurring3D"})
    @Test(groups = { "paypal" })
    public static void paypalCardinalComRecurring3D(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/13376/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("CardinalRecurring3D");
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
        fields.setCCNUmber("4000000000000002");
        fields.setCCExpiry(new CharSequence[]{"12", "2021"});
        fields.setCCV("123");

        fields.setRecurFreq("MONTHLY");

        fields.submit();

        //Assert donation amount and submit payment
        fields.waitForPageLoad();
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        String labeltext = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/b/font"))
                .getText();
        Assert.assertTrue("Donation Amount displayed is incorrect", labeltext.contains("$15.00"));
        WebElement myPasswordDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("password")));
        myPasswordDynamicElement.sendKeys("1234");
        WebElement mySubmitDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("UsernamePasswordEntry")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", mySubmitDynamicElement);
        Thread.sleep(2000);
        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13376/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("9227"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paypalCardinalComRecurring3D", fields);
        page.getSupporterById(FUNDRAISING_TEST="paypalCardinalComRecurring3D", fields);
    }

    @Parameters({"stripeViaPaypalSingle"})
    @Test(groups = { "paypal" })
    public static void stripeViaPaypalSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12511/donate/1?mode=DEMO");


        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Paypal");
        fields.setLastname("StripeViaPaypalSingle");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");
        fields.selectPaymentType("Paypal");
        fields.selectPayCurrency("USD");
        //fields.setCCNUmber("4242424242424242");
        fields.submit();

        fields.waitForPageLoad();
//        fields.logPaypal();
//        fields.waitForPageLoad();
        fields.setPaypalEmail();
        fields.nextPayapl();
        fields.waitForPageLoad();
        fields.setPaypalPassword();
        fields.submitPaypal();
        fields.waitForPageLoadPayPal();
        Thread.sleep(4000);

        WebElement paypalContinue = (new WebDriverWait(driver, 200))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("payment-submit-btn")));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", paypalContinue);
        fields.waitForPageLoad();
        Thread.sleep(6000);



        //		Assert that the payment was successful and the third page was reached
        Assert.assertTrue("Urls are not the same",  driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12511/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8204"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("PayPal Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Paypal"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeViaPaypalSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeViaPaypalSingle", fields);
    }
}
