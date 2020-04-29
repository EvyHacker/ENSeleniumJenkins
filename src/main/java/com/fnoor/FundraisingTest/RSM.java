package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class RSM {

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

    @Parameters({"rsmSingle"})
    @Test(groups = { "rsm" })
    public static void rsmSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/846/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("49 Featherstone Street");
        fields.setCity("LONDON");
        fields.setRegion("AYLESBURY");
        fields.setPostCode("EC1Y 8SY");
        fields.isCountryFieldEmpty("GBR");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("GBP");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5404000000000001");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/846/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3523"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RSM Credit Car"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("£15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("GBP"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA"));

        page.getSupporterByEmail(FUNDRAISING_TEST="rsmSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="rsmSingle", fields);
    }

    @Parameters({"rsmRecurring"})
    @Test(groups = { "rsm" })
    public static void rsmRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/870/donate/1?mode=DEMO");

        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("49 Featherstone Street");
        fields.setCity("LONDON");
        fields.setRegion("AYLESBURY");
        fields.setPostCode("EC1Y 8SY");
        fields.isCountryFieldEmpty("GBR");

        fields.submit();

        fields.selectDonationAmt("15");
        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPayCurrency("GBP");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5404000000000001");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/870/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3524"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RSM Credit Car"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("£15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("GBP"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA"));

        page.getSupporterByEmail(FUNDRAISING_TEST="rsmRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="rsmRecurring", fields);
    }

    @Parameters({"rsmDirectDebit"})
    @Test(groups = { "rsm" })
    public static void rsmDirectDebit(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/847/donate/1?mode=DEMO");

        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createRSMemail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("49 Featherstone Street");
        fields.setCity("LONDON");
        fields.setRegion("AYLESBURY");
        fields.setPostCode("EC1Y 8SY");
        fields.isCountryFieldEmpty("GBR");

        fields.submit();

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(14);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("12");
        fields.setRecurCount("5");
        fields.setRecurPeriod("6");

        fields.selectDonationAmt("15");
        fields.selectPaymentType("Bank");
        fields.setCCName("Unit Tester");
        fields.setBankAccNumber("12345112");
        fields.setBankRoutingNumber("074456");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/847/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3525"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RSM PDD"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("£15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("GBP"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("RECUR_UNMANAGED"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST:Direct Debit"));

        page.getSupporterByEmailRSM(FUNDRAISING_TEST="rsmDirectDebit", fields);
        page.getSupporterById(FUNDRAISING_TEST="rsmDirectDebit", fields);
    }

    @Parameters({"rsm3DSingle"})
    @Test(groups = { "rsm" })
    public static void rsm3DSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12784/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("49 Featherstone Street");
        fields.setCity("LONDON");
        fields.setRegion("AYLESBURY");
        fields.setPostCode("EC1Y 8SY");
        fields.isCountryFieldEmpty("GBR");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("GBP");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4462000000000003");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        fields.waitForURLToChange("https://test.sagepay.com/");
        String redirecturl = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Submit payment page", redirecturl.contains("https://test.sagepay.com/"));

        String rsmAmount = driver.findElement(By.id("field_amount")).getAttribute("value");
        Assert.assertTrue("Donation amount displayed is incorrect", rsmAmount.contentEquals("15.0 GBP"));
        WebElement myCompleteDynamicElement = driver.findElement(By.id("field_password"));
        myCompleteDynamicElement.sendKeys("password");
        WebElement submitButton = driver.findElement(By.id("submit-button"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", submitButton);
        fields.waitForPageLoad();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12784/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8490"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RSM Credit Car"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("£15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("GBP"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "rsm3DSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="rsm3DSingle", fields);
    }

    @Parameters({"rsm3DRecurring"})
    @Test(groups = { "rsm" })
    public static void rsm3DRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12785/donate/1?mode=DEMO");

        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createRSMemail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("49 Featherstone Street");
        fields.setCity("LONDON");
        fields.setRegion("AYLESBURY");
        fields.setPostCode("EC1Y 8SY");
        fields.isCountryFieldEmpty("GBR");

        fields.submit();

        fields.selectDonationAmt("15");
        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(14);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPayCurrency("GBP");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4462000000000003");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();
        fields.waitForURLToChange("https://test.sagepay.com/");
        String redirecturl = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Submit payment page", redirecturl.contains("https://test.sagepay.com/"));

        String rsmAmount = driver.findElement(By.id("field_amount")).getAttribute("value");
        Assert.assertTrue("Donation amount displayed is incorrect", rsmAmount.contentEquals("15.0 GBP"));
        WebElement myCompleteDynamicElement = driver.findElement(By.id("field_password"));
        myCompleteDynamicElement.sendKeys("password");
        WebElement submitButton = driver.findElement(By.id("submit-button"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", submitButton);
        fields.waitForPageLoad();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12785/donate/3"));

        fields.getSupporterTaxID();

        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8491"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("RSM Credit Car"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("£15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("GBP"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VISA"));

        page.getSupporterByEmailRSM(FUNDRAISING_TEST = "rsm3DRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="rsm3DRecurring", fields);
    }
}
