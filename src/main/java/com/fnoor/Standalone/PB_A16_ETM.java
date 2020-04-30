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

public class PB_A16_ETM {

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

    @Parameters({"ecommerceSymbolicGift"})
    @Test(groups = { "standalone" })
    public static void ecommerceSymbolicGift(String testId) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/12116/shop/1");

        fields.buyFeaturedItem();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("URL's are not the same", myurl.contains("Herd+of+Goats"));
        Assert.assertTrue(fields.verifyProductListShows());
        fields.addProdEcomm();
        fields.clickCartEcomm();
        fields.waitForPageLoad();

        //		Assert that the payment was successful and the third page was reached
        Assert.assertTrue(driver.getCurrentUrl().contains("https://politicalnetworks.com/page/12116/shop/4"));
        Assert.assertFalse(fields.verifyEmptyCartMsg().contains("Your cart is empty"));

        fields.ecommCheckout();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        Assert.assertTrue(driver.getCurrentUrl().contains("https://politicalnetworks.com/page/12116/shop/5"));

        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        //		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setPhoneNum("202-234-2345");
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.submit();

        fields.waitForPageLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://politicalnetworks.com/page/12116/shop/6"));
        fields.setEcommDeliveryMethod("EMAIL");
        fields.senPesonalMessage("Enjoy your gift!");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://politicalnetworks.com/page/12116/shop/7"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Campaign ID not present", bodytext.contains("7754"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$50.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("MC"));

    }
}