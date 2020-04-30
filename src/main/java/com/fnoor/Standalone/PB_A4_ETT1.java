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

public class PB_A4_ETT1 {

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

    @Parameters({"customTarget"})
    @Test(groups = { "standalone" })
    public static void customTarget(String testId) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/11068/action/1?mode=DEMO");

        fields.selectTitle("Miss");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAppealCode("testAppealCode");

        fields.submit();

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl().equals("https://politicalnetworks.com/page/11068/action/2"));
        fields.validateETTContactDetailsTitle("Junior");
        fields.validateETTContactDetailsFirstName("A5");
        fields.validateETTContactDetailsLastName("customtarget1");
        fields.validateETTContactDetailsOrganization("Amazing Stories");
        fields.validateETTTargetMessage("Mr");
        fields.validateETTTargetMessage("My message to Marta A5 customtarget1");
        fields.validateETTTargetMessage("Test message");
        fields.validateETTTargetMessage("Kind regards,");
        fields.validateETTTargetMessage("Unit Tester");
        fields.submit();

        String myurlfinalUrl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinalUrl.equals("https://politicalnetworks.com/page/11068/action/3"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));
    }
}
