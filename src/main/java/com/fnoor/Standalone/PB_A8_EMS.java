package com.fnoor.Standalone;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class PB_A8_EMS {

    static FundraisingPageDriver page = new FundraisingPageDriver();
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

    @Parameters({"signUp"})
    @Test(groups = { "standalone" })
    public static void signUp(String testId) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/11072/subscribe/1");

        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        fields.setAddress1("2001 S Street NW");
        fields.setCity("Washington DC");
        fields.selectRegion("DC");
        fields.setPostCode("20009");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");
        fields.setquestion1("test");
        fields.question_radio_cat_dog(0);
        fields.selectdiet("No pref");
        fields.question_radio_optin(0);
        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://politicalnetworks.com/page/11072/subscribe/2"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address 1 is incorrect/ not present ", bodytext.contains("2001 S Street NW"));
        Assert.assertTrue("City is incorrect/ not present", bodytext.contains("Washington DC"));
        Assert.assertTrue("Postcode is incorrect/ not present", bodytext.contains("20009"));
        Assert.assertTrue("Region is incorrect/ not present", bodytext.contains("DC"));
        Assert.assertTrue("Country is incorrect/ not present", bodytext.contains("US"));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));

    }
}
