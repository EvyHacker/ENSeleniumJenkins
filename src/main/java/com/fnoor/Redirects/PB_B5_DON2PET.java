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

public class PB_B5_DON2PET {


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

    @Parameters({"donationToPetition"})
    @Test(groups = { "redirect" })
    public static void donationToPetition(String testId) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/12582/donate/1?mode=DEMO");

        fields.selectTitle("Miss");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");
        fields.submit();

        //Validate redirect to donation page
        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/12582/donate/2"));

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //Validate redirect to petition page and submit
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't redirect to petition page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/10617/petition/1?chain"));
        Assert.assertTrue("Email address is missing or incorrect", fields.getSupEmail().equals(new_email));
        Assert.assertTrue("First Name is missing or incorrect", fields.getSupFirstName().equals("Unit"));
        Assert.assertTrue("Last Name  is missing or incorrect", fields.getSupLastName().equals("Tester"));
        Assert.assertTrue("Address is missing or incorrect", fields.getSupAddress1().equals("1 Hilltop"));
        Assert.assertTrue("City is missing or incorrect", fields.getSupCity().equals("Baltimore"));
        Assert.assertTrue("Last Name address is missing or incorrect", fields.getSupPostCode().equals("20001"));
        fields.setAppealCode("testAppealCode");
        fields.submit();

        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/10617/petition/2"));

        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address missing from last page", bodytext.contains("1 Hilltop"));
        Assert.assertTrue("City missing from last page", bodytext.contains("Baltimore"));
        Assert.assertTrue("PostCode missing from last page", bodytext.contains("20001"));
        Assert.assertTrue("Region missing from last page", bodytext.contains("MD"));
        Assert.assertTrue("Country missing from last page", bodytext.contains("US"));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));

    }
}
