package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageHelper;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class STRIPE {

    static FundraisingPageHelper helper = new FundraisingPageHelper();
    private static  String FUNDRAISING_TEST;

    public static void stripeSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/11502/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
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

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/11502/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("7059"));
        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        helper.getSupporterByEmail(FUNDRAISING_TEST="stripeSingle", fields);
    }

    public static void stripeRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/11503/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
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

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/11503/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("7060"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        helper.getSupporterByEmail(FUNDRAISING_TEST="stripeRecurring", fields);
    }

    public static void stripeBancontactSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/11504/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
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
        String redirecturl = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Stripe payment page",
                redirecturl.contains("bancontact&usage=single_use"));
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".common-Button--default")));
        myDynamicElement.click();
        Thread.sleep(1000);


        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/11504/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("7061"));
        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("€15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("EUR"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: bancontact"));

        helper.getSupporterByEmail(FUNDRAISING_TEST="stripeBancontactSingle", fields);
    }

    public static void stripeSingle3D(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12663/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
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
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();
        driver.switchTo().frame("__privateStripeFrame4");
        driver.switchTo().frame("challengeFrame");
        fields.waitForPageLoad();

        //Fail transaction and assert page URL
        WebElement myFailDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-fail-3ds")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", myFailDynamicElement);

        String myurliframe = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurliframe.equals("https://politicalnetworks.com/page/12663/donate/2"));
        driver.switchTo().defaultContent();
        fields.waitForPageLoad();
        fields.submit();

        //Complete transaction
        fields.waitForPageLoad();
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[1]/iframe")));
        driver.switchTo().frame("challengeFrame");
        fields.waitForPageLoad();
        WebElement myCompleteDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-authorize-3ds")));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click();", myCompleteDynamicElement);

        fields.waitForPageLoad();
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12663/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8360"));
        Assert.assertTrue("Tax deductible", bodytext.contains("true"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        helper.getSupporterByEmail(FUNDRAISING_TEST="stripeSingle3D", fields);
    }

    public static void stripeRecurring3D(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12777/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
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

        fields.waitForPageLoad();
        driver.switchTo().frame("__privateStripeFrame4");
        driver.switchTo().frame("challengeFrame");
        fields.waitForPageLoad();

        //Fail transaction and assert page URL
        WebElement myFailDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-fail-3ds")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", myFailDynamicElement);

        String myurliframe = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Submit payment page", myurliframe.equals("https://politicalnetworks.com/page/12777/donate/2"));
        driver.switchTo().defaultContent();
        fields.waitForPageLoad();
        fields.submit();

        //Complete transaction
        fields.waitForPageLoad();
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[1]/iframe")));
        driver.switchTo().frame("challengeFrame");
        fields.waitForPageLoad();
        WebElement myCompleteDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-authorize-3ds")));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click();", myCompleteDynamicElement);

        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12777/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("8479"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        helper.getSupporterByEmail(FUNDRAISING_TEST="stripeRecurring3D", fields);
    }
}
