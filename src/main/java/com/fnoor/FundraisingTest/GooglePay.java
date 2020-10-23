package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.fnoor.PageFields.ENLOGIN;
import static com.fnoor.PageFields.GOOGLE;

public class GooglePay {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static String FUNDRAISING_TEST;

    static PageFields fields;

//    @Parameters({"browser"})
//    @BeforeClass(alwaysRun=true)
//    public void setUp(String browser) throws MalformedURLException {
//        driver = page.createInstance(browser);
//        fields = PageFactory.initElements(driver, PageFields.class);
//        //driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().pageLoadTimeout(800, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(800, TimeUnit.SECONDS);
//    }

//    @AfterClass(alwaysRun = true)
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @Parameters({"googlePay"})
    @Test(groups = { "google" })
    public static void googlePay(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        System.setProperty("webdriver.chrome.driver", "webdrivers/linux/chromedriver");
        //System.setProperty("webdriver.chrome.logfile", "/Users/user1/Temp/chromedriver_TC.log");
        //def chromeBinaryPath = '/Applications/Google Chrome.app/Contents/MacOSocal/Google/Chrome/Google Chrome'
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        Map<String, Boolean> auxProfile = new HashMap<String, Boolean>();
        Map < String, Object > profile = new HashMap < String, Object > ();
        Map<String, Object> contentSettings = new HashMap<String, Object>();

        // SET CHROME OPTIONS
        // 0 - Default, 1 - Allow, 2 - Block
        contentSettings.put("notifications", 1);
        profile.put("profile.default_content_setting_values.notifications", contentSettings);
//        auxProfile.put("auxiliary_profiles_enabled", true); // this causes a faliur when used with chromedriver
//        auxProfile.put("enabled", true); // this works okay with chromedriver
      //  prefs.put("autofill", auxProfile);
        prefs.put("profile", profile);

        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("enable-autofill-credit-card-upload", true);
//        options.setExperimentalOption("enable-sync", true);
//        options.setExperimentalOption("credentials_enable_service", true);
//        options.setExperimentalOption("profile.password_manager_enabled", true);
//        options.setExperimentalOption("enable-offer-store-unmasked-wallet-cards", true);
//        options.setExperimentalOption("ignore-autocomplete-off-autofill", true);
//        options.setExperimentalOption("disable-web-security", true);
//        options.setExperimentalOption("allow-running-insecure-content", true);
//        options.setExperimentalOption("enable-automatic-password-saving", true);
//        options.setExperimentalOption("allow-autofill-sync-credential", true);
//        options.setExperimentalOption("enable-offer-upload-credit-cards", true);
        options.setExperimentalOption("prefs", prefs);


        options.addArguments("--disable-infobars");
       // final ChromeOptions options = new ChromeOptions();


        options.addArguments("--enable-offer-store-unmasked-wallet-cards");
        options.addArguments("--reduce-security-for-testing");

//        options.addArguments("--enable-sandbox-logging");
//        options.addArguments("--show-autofill-signatures");
//        options.addArguments("--ignore-autocomplete-off-autofill");
    //    options.addArguments("--disable-web-security");
//        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--enable-sync");
        options.addArguments("--allow-running-insecure-content");
//        options.addArguments("--enable-credit-card-scan");
//        options.addArguments("--enable-autofill-keyboard-accessory-view[8]");
       // options.addArguments("--enable-automatic-password-saving");
        options.addArguments("--allow-autofill-sync-credential");
//        options.addArguments("--enable-wifi-credential-sync");
        options.addArguments("--enable-offer-upload-credit-cards");
        options.addArguments("--user-data-dir=/Users/ievgeniiagaidarenko/Library/Application Support/Cypress/cy/production/browsers/chrome-stable/interactive/Default");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        fields = PageFactory.initElements(driver, PageFields.class);

        driver.navigate().to(GOOGLE);
        fields.googleLogin();

        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));


       // driver.navigate().to("chrome://settings/payments");
//        WebElement w = driver.findElement(By.xpath("//iframe[@name='settings/payments']"));
       // WebDriverWait wait = new WebDriverWait(driver, 20);
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
//                (By.xpath("//iframe[@name='settings/payments']")));
//        //driver = driver.switchTo().frame(w);
//        WebElement myToggle = (new WebDriverWait(driver, 20))
//                .until(ExpectedConditions.presenceOfElementLocated
//                        (By.xpath("//*[@id=\"labelWrapper\"]/div[1]")));
//
//        myToggle.click();

//        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
//        driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);
        driver.get("https://politicalnetworks.com/page/13573/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Payflow");
        fields.setLastname("ProSingle");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
//
//        fields.setOtherAmt1("other amount test 1");
//        fields.setOtherAmt2("other amount test 2");
//        fields.setOtherAmt3("other amount test 3");
//        fields.setOtherAmt4("other amount test 4");
//        fields.setAppealCode("appeal code test");
//        fields.setDirectMyGift("direct gift test");
//        fields.setAdditionalComments("additional comments");
//        fields.setTaxDeductible("Y");
//
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000002500003155");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");
//
//        fields.submit();
//
//        String myurl = driver.getCurrentUrl();
//        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10879/donate/3"));
//
//        fields.getSupporterTaxID();
//
////		Get the details from the third page and Verify the fields
//        String bodytext = driver.findElement(By.tagName("body")).getText();
//
//        Assert.assertTrue("Campaign ID not present", bodytext.contains("6308"));
//        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
//        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Payflow Gateway"));
//        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
//        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
//        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
//        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));
//
//        page.getSupporterByEmail(FUNDRAISING_TEST="payflowProSingle", fields);
//        page.getSupporterById(FUNDRAISING_TEST="payflowProSingle", fields);
    }
}
