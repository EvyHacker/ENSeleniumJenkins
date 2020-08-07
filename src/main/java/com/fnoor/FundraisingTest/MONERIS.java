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

public class MONERIS {

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

    @Parameters({"moneriseSelectSingle"})
    @Test(groups = { "moneris" })
    public static void moneriseSelectSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/845/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Moneris");
        fields.setLastname("SelectSingle");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        System.out.println("Email:" + new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/845/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3519"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="moneriseSelectSingle", fields);
    }

    @Parameters({"moneriseSelectRecurring"})
    @Test(groups = { "moneris" })
    public static void moneriseSelectRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/867/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Moneris");
        fields.setLastname("SelectRecurring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("month");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/867/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3520"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("RECUR_UNMANAGED"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="moneriseSelectRecurring", fields);
    }

    @Parameters({"monerisVaultRecurring"})
    @Test(groups = { "moneris" })
    public static void monerisVaultRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/869/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Moneris");
        fields.setLastname("VaultRecurring€");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 H₣illtop");
        fields.setCity("ÀàÂâÆæÇçÈèÉèÉéÊêËëÎîÏïÔôŒœÙùÛûÜü « »  €₣");
        //fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("5");
        fields.setRecurPeriod("6");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/869/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3522"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisVaultRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="monerisVaultRecurring", fields);
    }

    @Parameters({"monerisSingleNoCvv"})
    @Test(groups = { "moneris" })
    public static void monerisSingleNoCvv(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10938/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Moneris");
        fields.setLastname("SingleNoCVV");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10938/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("6378"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisSingleNoCvv", fields);
        page.getSupporterById(FUNDRAISING_TEST="monerisSingleNoCvv", fields);
    }

    @Parameters({"monerisRecurringNoCvv"})
    @Test(groups = { "moneris" })
    public static void monerisRecurringNoCvv(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10939/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Moneris");
        fields.setLastname("RecurringNoCVV");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});

        fields.setRecurDay("23");
        fields.setRecurFreq("month");
        fields.setRecurCount("12");
        fields.setRecurPeriod("12");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10939/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("6379"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("RECUR_UNMANAGED"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisRecurringNoCvv", fields);
        page.getSupporterById(FUNDRAISING_TEST="monerisRecurringNoCvv", fields);
    }

    @Parameters({"monerisVault3DSingle"})
    @Test(groups = { "moneris" })
    public static void monerisVault3DSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12779/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Monerisât");
        fields.setLastname("Vault3DSingleÀàÂâÆæÇçÈèÉèÉéÊêËëÎîÏïÔôŒœÙùÛûÜü « »  €₣");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 ÎHilltop«");
        fields.setCity("Baltimoreë");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setCCName("Unit Testerô");
        fields.setCCNUmber("4012001037141112");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

//        fields.waitForPageLoad();
//        fields.waitForURLToChange("https://pit.3dsecure.net/");
//
//        //Assert user got redirected to payment page
//        String myurl = driver.getCurrentUrl();
//        Assert.assertTrue("Didn't redirect to Submit payment page", myurl.contains("https://pit.3dsecure.net/"));
//
//        WebElement myCompleteDynamicElement = (new WebDriverWait(driver, 20))
//                .until(ExpectedConditions.presenceOfElementLocated
//                        (By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input[1]")));
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("arguments[0].click();", myCompleteDynamicElement);
//        fields.waitForPageLoad();

        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12779/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8481"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisVault3DSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="monerisVault3DSingle", fields);
    }

    @Parameters({"monerisVault3DRecurring"})
    @Test(groups = { "moneris" })
    public static void monerisVault3DRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12783/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Moneris»");
        fields.setLastname("Vault3DRecurringî");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();
        fields.waitForPageLoad();

        fields.setAddress1("ÀàÂâÆæÇçÈèÉèÉéÊêËëÎîÏïÔôŒœÙùÛûÜü « »  €₣");
        //fields.setCity("Baltimore");
        fields.setCity("Montralœt");
        fields.selectRegion("MD");
        fields.setPostCode("20001");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4012001037141112");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.setRecurDay("23");
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("12");
        fields.setRecurPeriod("12");

        fields.submit();

//        fields.waitForPageLoad();
//        fields.waitForURLToChange("https://pit.3dsecure.net/");
//
//        //Assert user got redirected to payment page
//        String myurl = driver.getCurrentUrl();
//        Assert.assertTrue("Didn't redirect to Submit payment page", myurl.contains("https://pit.3dsecure.net/"));
//
//        WebElement myCompleteDynamicElement = (new WebDriverWait(driver, 20))
//                .until(ExpectedConditions.presenceOfElementLocated
//                        (By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input[1]")));
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("arguments[0].click();", myCompleteDynamicElement);

        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12783/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8489"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisVault3DRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="monerisVault3DRecurring", fields);
    }

    @Parameters({"moneriseSelectSingleUP"})
    @Test(groups = { "moneris" })
    public static void moneriseSelectSingleUP(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/13068/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Moneris");
        fields.setLastname("SelectSingleUp");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6250944000000771");
        fields.setCCExpiry(new CharSequence[] {"12", "2049"});
        fields.setCCV("371");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13068/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8855"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: UP"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectSingleUP", fields);
        page.getSupporterById(FUNDRAISING_TEST="moneriseSelectSingleUP", fields);
    }
}
