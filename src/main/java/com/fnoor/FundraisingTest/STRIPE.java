package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.fnoor.PageFields.ENLOGIN;

public class STRIPE {

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
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"stripeSingle"})
    @Test(groups = { "stripe" })
    public static void stripeSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/11502/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
        fields.setLastname("Single");
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
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        Thread.sleep(600);
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/11502/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("7059"));
        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeSingle", fields);
    }

    @Parameters({"stripeRecurring"})
    @Test(groups = { "stripe" })
    public static void stripeRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/11503/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
        fields.setLastname("Recurring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");
        fields.submit();

        fields.waitForPageLoad();
        Thread.sleep(600);
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/11503/donate/3"));
        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("7060"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeRecurring", fields);
    }

    @Parameters({"stripeBancontactSingle"})
    @Test(groups = { "stripe" })
    public static void stripeBancontactSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/11504/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
        fields.setLastname("BancontactSingle");
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

        fields.selectPaymentType("bancontact");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

//        Authorize the payment
        Thread.sleep(4000);
        Assert.assertTrue("Didn't redirect to Stripe payment page",
                driver.getCurrentUrl().contains("bancontact&usage=single_use"));
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".common-Button--default")));
        myDynamicElement.click();
        fields.waitForPageLoad();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/11504/donate/3"));
        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("7061"));
        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("€15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("EUR"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: bancontact"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeBancontactSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeBancontactSingle", fields);
    }

    @Parameters({"stripeSingle3D"})
    @Test(groups = { "stripe" })
    public static void stripeSingle3D(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12663/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
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

        fields.setOtherAmt1("other amount test 1");
        fields.setOtherAmt2("other amount test 2");
        fields.setOtherAmt3("other amount test 3");
        fields.setOtherAmt4("other amount test 4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000002500003155");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("111");

        fields.submit();

        // Verify failed transaction
        fields.waitForPageLoad();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
                        (By.id("challengeFrame")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
                (By.name("acsFrame")));

        WebElement myFailTransaction = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-fail-3ds")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", myFailTransaction);
        fields.waitForPageLoad();

        driver.switchTo().defaultContent();
        fields.waitForURLToChange("https://politicalnetworks.com/page/12663/donate/2");
        String alert = driver.findElement(By.xpath("//li[@class='en__error en__error__gateway']")).getText();
        Assert.assertTrue("There is no alerts on the page",
                alert.contains("We are unable to authenticate your payment method. Please choose a different payment method and try again."));
        fields.submit();

        // Verify success transaction
        fields.waitForPageLoad();
        wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
                (By.id("challengeFrame")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
                (By.name("acsFrame")));

        WebElement myCompleteTransaction = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-authorize-3ds")));
        executor.executeScript("arguments[0].click();", myCompleteTransaction);
        fields.waitForPageLoad();

        Thread.sleep(4000);
        driver.switchTo().defaultContent();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12663/donate/3"));
        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8360"));
        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeSingle3D", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeSingle3D", fields);

    }

    @Parameters({"stripeRecurring3D"})
    @Test(groups = { "stripe" })
    public static void stripeRecurring3D(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12777/donate/1?ea.tracking.id=Google&mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
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

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000002500003155");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        // Verify success transaction
        fields.waitForPageLoad();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
                (By.id("challengeFrame")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
                (By.name("acsFrame")));

        WebElement myCompleteDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-authorize-3ds")));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click();", myCompleteDynamicElement);
        fields.waitForPageLoad();

        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12777/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8479"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeRecurring3D", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeRecurring3D", fields);
    }

    @Parameters({"stripeSEPAsingle"})
    @Test(groups = { "stripe" })
    public static void stripeSEPAsingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/13322/donate/1?ea.tracking.id=Google&mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
        fields.setLastname("SEPASingle");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("sepa_debit");
        driver.switchTo().frame(0);
        WebElement sepaIBAN = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector(".Input--empty")));
        sepaIBAN.sendKeys("GB82WEST12345698765432");
        driver.switchTo().defaultContent();

        fields.submit();
        fields.waitForPageLoad();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13322/donate/3"));
        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("9162"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("€15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("EUR"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: sepa_debit"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeSEPAsingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeSEPAsingle", fields);

    }

    @Parameters({"stripeSEPArecurring"})
    @Test(groups = { "stripe" })
    public static void stripeSEPArecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/13328/donate/1?ea.tracking.id=Facebook");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
        fields.setLastname("SEPAReccuring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("sepa_debit");
        driver.switchTo().frame(0);
        WebElement sepaIBAN = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector(".Input--empty")));
        sepaIBAN.sendKeys("IT40S0542811101000000123456");
        driver.switchTo().defaultContent();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.submit();
        fields.waitForPageLoad();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13328/donate/3"));
        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("9168"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("€15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("EUR"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: sepa_debit"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeSEPArecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeSEPArecurring", fields);

    }

    @Parameters({"stripeIDEALsingleABN"})
    @Test(groups = { "stripe" })
    public static void stripeIDEALsingleABN(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/13323/donate/1");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
        fields.setLastname("iDEALABN");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("iDEAL");
        driver.switchTo().frame(0);
        WebElement idealSelect = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector(".SelectField-control")));
        Actions actions = new Actions(driver);
        actions.click(idealSelect).perform();
        WebElement ABN = driver.findElement(By.id("bank-list-item-0"));
        actions.moveToElement(idealSelect).moveToElement(ABN).sendKeys(Keys.ENTER).perform();

        driver.switchTo().defaultContent();
        fields.submit();
        fields.waitForPageLoad();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        fields.waitForURLToChange("https://stripe.com/sources/test_source?amount=1500&currency=eur");

        // Validate fail test payment
        Assert.assertTrue("Urls are not the same, payment didn't go through",
                driver.getCurrentUrl().contains("https://stripe.com/sources/test_source?amount=1500&currency=eur"));
        WebElement fail = driver.findElement(By.xpath("//*[contains(text(), 'Fail Test Payment')]"));
        fail.click();
        fields.waitForURLToChange("https://politicalnetworks.com/page/13323/donate/2?val" );
        String error = driver.findElement(By.className("en__error")).getText();
        Assert.assertTrue("Urls are not the same",
                error.equals("This transaction has failed as there has been an error in processing your payment."));
        fields.selectPaymentType("iDEAL");
        driver.switchTo().frame(0);
        try{
        WebElement idealSelect1 = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector(".SelectField-control")));
            actions.moveToElement(idealSelect1).click().perform();
            actions.sendKeys(Keys.ENTER).perform();
         } catch (StaleElementReferenceException e) {
        System.err.println(e.getMessage());
    }

        driver.switchTo().defaultContent();
        fields.submit();
        fields.waitForPageLoad();

        // Validate bank payment
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl().contains("https://stripe.com/sources/test_source?amount=1500&currency=eur"));
        WebElement authorize = driver.findElement(By.xpath("//*[contains(text(), 'Authorize Test Payment')]"));
        authorize.click();
        fields.waitForPageLoad();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13323/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("9163"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("€15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("EUR"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: ideal"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeIDEALsingleABN", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeIDEALsingleABN", fields);
    }

    @Parameters({"stripeIDEALsingleASN"})
    @Test(groups = { "stripe" })
    public static void stripeIDEALsingleASN(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/13323/donate/1");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Stripe");
        fields.setLastname("iDEALASN");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("iDEAL");
        driver.switchTo().frame(0);
        WebElement idealSelect = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector(".SelectField-control")));
        Actions actions = new Actions(driver);
        actions.click(idealSelect).perform();
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        driver.switchTo().defaultContent();
        fields.submit();
        fields.waitForPageLoad();
        fields.waitForURLToChange("https://stripe.com/sources/test_source?amount=1500&currency=eur");

        // Validate fail test payment
        Assert.assertTrue("Urls are not the same, payment didn't go through",
                driver.getCurrentUrl().contains("https://stripe.com/sources/test_source?amount=1500&currency=eur"));
        WebElement fail = driver.findElement(By.xpath("//*[contains(text(), 'Fail Test Payment')]"));
        fail.click();
        fields.waitForURLToChange("https://politicalnetworks.com/page/13323/donate/2?val" );
        String error = driver.findElement(By.className("en__error")).getText();
        Assert.assertTrue("Urls are not the same",
                error.equals("This transaction has failed as there has been an error in processing your payment."));
        fields.selectPaymentType("iDEAL");
        driver.switchTo().frame(0);
        try{
            WebElement idealSelect1 = (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.presenceOfElementLocated
                            (By.cssSelector(".SelectField-control")));
            actions.moveToElement(idealSelect1).click().perform();
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        } catch (StaleElementReferenceException e) {
            System.err.println(e.getMessage());
        }

        driver.switchTo().defaultContent();
        fields.submit();
        fields.waitForPageLoad();

        // Validate bank payment
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl().contains("https://stripe.com/sources/test_source?amount=1500&currency=eur"));
        WebElement authorize = driver.findElement(By.xpath("//*[contains(text(), 'Authorize Test Payment')]"));
        authorize.click();
        fields.waitForPageLoad();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13323/donate/3"));

        fields.getSupporterTaxID();

        System.out.println(Currency.getInstance("EUR").getSymbol(Locale.FRANCE));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("9163"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("€15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("EUR"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: ideal"));

        page.getSupporterByEmail(FUNDRAISING_TEST="stripeIDEALsingleASN", fields);
        page.getSupporterById(FUNDRAISING_TEST="stripeIDEALsingleASN", fields);
    }

    @Parameters({"stripeIDEALsingleASNRefund"})
    @Test(groups = { "stripe" })
    public static void stripeIDEALsingleASNRefund() throws InterruptedException {

        driver.navigate().to(ENLOGIN);
        fields.enLogin();
        fields.waitForPageLoad();
        Thread.sleep(2000);
        LocalDate date = LocalDate.now();
        fields.searchSupporter("pb_stripeidealsingleasn_" + date.toString() + "@tellamazingstories.com");
        Thread.sleep(2000);

        // Validate supporter Details
        fields.selectSupporter();
        Thread.sleep(2000);
        Assert.assertTrue("First name missing from supporter details", fields.getSupporterDetails().contains("Stripe"));
        Assert.assertTrue("Last name missing from supporter details", fields.getSupporterDetails().contains("iDEALASN"));
        Assert.assertTrue("Address1 missing from supporter details", fields.getSupporterDetails().contains("1 Hilltop"));
        Assert.assertTrue("City missing from supporter details", fields.getSupporterDetails().contains("Baltimore"));
        Assert.assertTrue("Region name missing from supporter details", fields.getSupporterDetails().contains("MD"));
        Assert.assertTrue("Country name missing from supporter details", fields.getSupporterDetails().contains("US"));
        Assert.assertTrue("Postcode missing from supporter details", fields.getSupporterDetails().contains("20001"));

        // Submit a refund
        fields.expendSingleTransaction("FBS");
        fields.waitForPageLoad();
        fields.validateOriginalReceipt("Original receipt");
        fields.validateReplacementReceipt("Replacement receipt");
        fields.validateChangeTaxStatus("Change tax status");
        fields.refundTransaction("Refund donation");
        fields.refundTransactionAmount("10.00");
        fields.setRefundReceipt("Refund receipt");
        fields.setRefundTemplate("Default for Donation Refund (single and recurring)");
        fields.submitRefund();
        fields.confirmRefund("Are you sure you wish to refund this donation?");

        fields.waitForPageLoad();
        Thread.sleep(6000);
        fields.validateRefund("RFD");

        // Validate refund transaction
        fields.waitForPageLoad();
        Thread.sleep(4000);
        Assert.assertTrue("Transaction error, amount is incorrect or missing ",
                fields.getSingleTransactionDetails().contains("Amount -10 EUR"));
        Assert.assertTrue("Transaction error, status is incorrect or not present",
                fields.getSingleTransactionDetails().contains("success"));
        Assert.assertTrue("Transaction error, gateway is incorrect or not present",
                fields.getSingleTransactionDetails().contains("Stripe Gateway"));
        Assert.assertTrue("Transaction error, payment type is incorrect or not present",
                fields.getSingleTransactionDetails().contains("TEST: ideal"));

    }

}
