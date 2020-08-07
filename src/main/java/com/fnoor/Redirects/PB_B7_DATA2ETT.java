package com.fnoor.Redirects;

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

public class PB_B7_DATA2ETT {

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

    @Parameters({"dataToETT"})
    @Test(groups = { "redirect" })
    public static void dataToETT(String testId) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/12599/data/1?mode=DEMO");

        fields.setSupFirstName("Data");
        fields.setSupLastName("ToETT");
        String new_email = fields.createEmail(testId);
        fields.setSupEmailAddress(new_email);
        fields.setAppealCode("testAppealCode");
        fields.submit();

        //Validate redirect to donation page
        Assert.assertTrue("Didn't redirect to data capture page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/12599/data/2"));

        fields.setSupAddress("1 Hilltop");
        fields.setSupCity("Baltimore");
        fields.selectRegion("MD");
        fields.setSupPostcode("20001");
        fields.selectSupCountry("US");
        fields.submit();

        //Validate redirect to data page
        Assert.assertTrue("Didn't redirect to data capture page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/11068/action/1?chain"));

        Assert.assertTrue("First Name is missing or incorrect", fields.getSupFirstName().equals("Data"));
        Assert.assertTrue("Last Name  is missing or incorrect", fields.getSupLastName().equals("ToETT"));
        Assert.assertTrue("Email address is missing or incorrect", fields.getSupEmail().equals(new_email));
        fields.setAppealCode("testAppealCode");

        fields.submit();

        Assert.assertTrue("Didn't redirect to data capture 2nd page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/11068/action/2"));

        fields.validateETTContactDetailsTitle("Junior");
        fields.validateETTContactDetailsFirstName("A5");
        fields.validateETTContactDetailsLastName("customtarget1");
        fields.validateETTContactDetailsOrganization("Amazing Stories");
        fields.validateETTTargetMessage("Mr");
        fields.validateETTTargetMessage("My message to Marta A5 customtarget1");
        fields.validateETTTargetMessage("Test message");
        fields.validateETTTargetMessage("Kind regards,");
        fields.validateETTTargetMessage("Data ToETT");
        fields.submit();

        fields.waitForPageLoad();

        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/11068/action/3"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Data"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("ToETT"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address 1 is incorrect/ not present ", bodytext.contains("1 Hilltop"));
        Assert.assertTrue("City is incorrect/ not present", bodytext.contains("Baltimore"));
        Assert.assertTrue("Postcode is incorrect/ not present", bodytext.contains("20001"));
        Assert.assertTrue("Region is incorrect/ not present", bodytext.contains("MD"));
        Assert.assertTrue("Country is incorrect/ not present", bodytext.contains("US"));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));
    }
}
