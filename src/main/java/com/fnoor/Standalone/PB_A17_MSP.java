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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class PB_A17_MSP {

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

    @Parameters({"membershipDogsJoin"})
    @Test(groups = {"standalone"})
    public static void membershipDogsJoin(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12120/membership/1?ea.tracking.id=mkx35hfo&utm_content=A17%20-%20Standalone%20Membership&utm_campaign=utm_member&utm_medium=email&utm_source=engagingnetworks\n");

        fields.field_MSP_Join.click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12120/membership/2?membershipTypeId=71"));

        fields.validatememberoption("12 months / $12.00");
        fields.validatememberoption("24 months / $24.00");
        fields.validatememberoption("36 months / $36.00");
        fields.field_MemberAddAmoun.click();
        fields.setFirstname("Membership");
        fields.setLastname("Join");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setPhoneNum("202-234-4353");
        fields.setAddress1("1 Hilltop");
        fields.setAddress2("Apt 2");
        fields.setPostCode("20001");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.selectCountry("US");
        fields.submit();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12120/membership/3"));
        fields.validateMembershipTotal("$17.00");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");
        fields.setMemberFN("Member#1 FN");
        fields.setMemberLN("Member#1 LN");
        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12120/membership/4"));
        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("7758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$17.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("MC"));

        page.getSupporterByEmail(FUNDRAISING_TEST="membershipDogsJoin", fields);
        page.getSupporterById(FUNDRAISING_TEST="membershipDogsJoin", fields);
    }
}
