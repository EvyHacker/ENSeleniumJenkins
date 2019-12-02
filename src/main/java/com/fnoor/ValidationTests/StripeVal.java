package com.fnoor.ValidationTests;

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
import java.util.List;

import static com.fnoor.PageFields.STRIPEDASHBOARD;

public class StripeVal {

    static FundraisingPageHelper helper = new FundraisingPageHelper();
    private static String FUNDRAISING_TEST;

    public static void stripeSingleVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTestVal();
        driver.navigate().to("https://politicalnetworks.com/page/12843/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.submit();

        fields.clickNoReccuringPaymentcheckbox();
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12843/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8577"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        driver.navigate().to(STRIPEDASHBOARD);
        fields.waitForPageLoad();
        fields.stripeLogin();
        fields.waitForPageLoad();
        WebElement myPaymentsElement = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[contains(text(),'Payments')]")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", myPaymentsElement);
        fields.waitForPageLoad();
        String myStripePaymentsURL = driver.getCurrentUrl();
        Assert.assertTrue("You are not on payments page",
                myStripePaymentsURL.equals("https://dashboard.stripe.com/test/payments"));
        fields.waitForPageLoad();
        fields.searchStripeOrder(newTxnId);
        fields.waitForPageLoad();

        List<WebElement> valTransaction = driver.findElements(By.className(".Text-display--inline"));
        for (WebElement orderId : valTransaction) {
            Assert.assertTrue("Donation amount is incorrect or not present", orderId.getText().equals("$15.00"));
            Assert.assertTrue("First and Last name is incorrect or not present", orderId.getText().equals("Unit Tester"));
            Assert.assertTrue("Email address is incorrect or not present", orderId.getText().equals(new_email));
            Assert.assertTrue("Address is incorrect or not present",
                    orderId.getText().equals("1 Hilltop Baltimore, 20001, US"));
        }

        helper.getSupporterByEmail(FUNDRAISING_TEST = "stripeSingleVal", fields);
    }

    public static void stripeRecurringVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTestVal();
        driver.navigate().to("https://politicalnetworks.com/page/12843/donate/1?mode=DEMO");

        fields.selectDonationAmt("1");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.submit();

        fields.clickRecurringPaymentchkbox();
        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12843/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("8577"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$1.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        driver.navigate().to(STRIPEDASHBOARD);
        fields.waitForPageLoad();
        fields.stripeLogin();
        fields.waitForPageLoad();
        WebElement myPaymentsElement = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[contains(text(),'Payments')]")));
        JavascriptExecutor executorPayment = (JavascriptExecutor) driver;
        executorPayment.executeScript("arguments[0].click();", myPaymentsElement);
        fields.waitForPageLoad();
        String myStripePaymentsURL = driver.getCurrentUrl();
        Assert.assertTrue("You are not on payments page",
                myStripePaymentsURL.equals("https://dashboard.stripe.com/test/payments"));
        fields.waitForPageLoad();
        fields.searchStripeOrder(newTxnId);
        fields.waitForPageLoad();

        List<WebElement> valTransaction = driver.findElements(By.className(".Text-display--inline"));
        for (WebElement orderId : valTransaction) {
            Assert.assertTrue("Donation amount is incorrect or not present", orderId.getText().equals("$1.00"));
            Assert.assertTrue("First and Last name is incorrect or not present", orderId.getText().equals("Unit Tester"));
            Assert.assertTrue("Email address is incorrect or not present", orderId.getText().equals(new_email));
            Assert.assertTrue("Address is incorrect or not present",
                    orderId.getText().equals("1 Hilltop Baltimore, 20001, US"));
        }

        helper.getSupporterByEmail(FUNDRAISING_TEST = "stripeRecurringVal", fields);
    }

    public static void stripeSingle3DVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTestVal();
        driver.navigate().to("https://politicalnetworks.com/page/12841/donate/1?mode=DEMO");

        fields.selectDonationAmt("20");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.submit();

        fields.clickNoReccuringPaymentcheckbox();
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000002500003155");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //3D Secure validation
        fields.waitForPageLoad();
        driver.switchTo().frame("__privateStripeFrame4");
        driver.switchTo().frame("challengeFrame");
        fields.waitForPageLoad();

        //Fail transaction and assert page URL
        WebElement myFailDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-fail-3ds")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", myFailDynamicElement);

        String myurliframe = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Submit payment page",
                myurliframe.equals("https://politicalnetworks.com/page/12841/donate/2"));
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
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", myCompleteDynamicElement);

        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12841/donate/3"));

//      Get the details from the third page and Verify the fields
        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields

        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8575"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$20.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        driver.navigate().to(STRIPEDASHBOARD);
        fields.waitForPageLoad();
        fields.stripeLogin();
        fields.waitForPageLoad();
        WebElement myPaymentsElement = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[contains(text(),'Payments')]")));
        JavascriptExecutor executorPayment = (JavascriptExecutor) driver;
        executorPayment.executeScript("arguments[0].click();", myPaymentsElement);
        fields.waitForPageLoad();
        String myStripePaymentsURL = driver.getCurrentUrl();
        Assert.assertTrue("You are not on payments page",
                myStripePaymentsURL.equals("https://dashboard.stripe.com/test/payments"));
        fields.waitForPageLoad();
        fields.searchStripeOrder(newTxnId);
        fields.waitForPageLoad();

        List<WebElement> valTransaction = driver.findElements(By.className(".Text-display--inline"));
        for (WebElement orderId : valTransaction) {
            Assert.assertTrue("Donation amount is incorrect or not present", orderId.getText().equals("$20.00"));
            Assert.assertTrue("First and Last name is incorrect or not present", orderId.getText().equals("Unit Tester"));
            Assert.assertTrue("Email address is incorrect or not present", orderId.getText().equals(new_email));
            Assert.assertTrue("Address is incorrect or not present",
                    orderId.getText().equals("1 Hilltop Baltimore, 20001, US"));
        }

        helper.getSupporterByEmail(FUNDRAISING_TEST = "stripeSingle3DVal", fields);
    }

    public static void stripeRecurring3DVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        helper.ensAuthTestVal();
        driver.navigate().to("https://politicalnetworks.com/page/12841/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.submit();

        fields.clickRecurringPaymentchkbox();
        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000002500003155");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //3D Secure validation
        fields.waitForPageLoad();
        driver.switchTo().frame("__privateStripeFrame4");
        driver.switchTo().frame("challengeFrame");
        fields.waitForPageLoad();

        //Fail transaction and assert page URL
        WebElement myFailDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("test-source-fail-3ds")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", myFailDynamicElement);

        String myurliframe = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Submit payment page",
                myurliframe.equals("https://politicalnetworks.com/page/12841/donate/2"));
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
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", myCompleteDynamicElement);

        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12841/donate/3"));

//      Get the details from the third page and Verify the fields
        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields

        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8575"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Stripe Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        driver.navigate().to(STRIPEDASHBOARD);
        fields.waitForPageLoad();
        fields.stripeLogin();
        fields.waitForPageLoad();
        WebElement myPaymentsElement = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[contains(text(),'Payments')]")));
        JavascriptExecutor executorPayment = (JavascriptExecutor) driver;
        executorPayment.executeScript("arguments[0].click();", myPaymentsElement);
        fields.waitForPageLoad();
        String myStripePaymentsURL = driver.getCurrentUrl();
        Assert.assertTrue("You are not on payments page",
                myStripePaymentsURL.equals("https://dashboard.stripe.com/test/payments"));
        fields.waitForPageLoad();
        fields.searchStripeOrder(newTxnId);
        fields.waitForPageLoad();

        List<WebElement> valTransaction = driver.findElements(By.className(".Text-display--inline"));
        for (WebElement orderId : valTransaction) {
            Assert.assertTrue("Donation amount is incorrect or not present", orderId.getText().equals("$15.00"));
            Assert.assertTrue("First and Last name is incorrect or not present", orderId.getText().equals("Unit Tester"));
            Assert.assertTrue("Email address is incorrect or not present", orderId.getText().equals(new_email));
            Assert.assertTrue("Address is incorrect or not present",
                    orderId.getText().equals("1 Hilltop Baltimore, 20001, US"));
        }

        helper.getSupporterByEmail(FUNDRAISING_TEST = "stripeRecurring3DVal", fields);
    }
}
