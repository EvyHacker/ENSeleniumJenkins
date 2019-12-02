package com.fnoor.ValidationTests;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.fnoor.PageFields.MONERISDASHBOARD;

public class MonerisVal {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void moneriseSelectSingleVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.get("https://politicalnetworks.com/page/12863/donate/1?mode=DEMO");

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

        fields.clickNoReccuringPaymentcheckbox();
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12863/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        System.out.println("monval1 " + bodytext);
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8606"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$1.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        driver.navigate().to(MONERISDASHBOARD);
        fields.waitForPageLoad();
        fields.monerisLogin();
        fields.waitForPageLoad();
        WebElement reportsOver = driver.findElement(By.id("mrc_reports"));
        Actions action = new Actions(driver);
        action.moveToElement(reportsOver).build().perform();
        WebElement transaction = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[2]//ul[1]//li[1]//a[1]")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", transaction);
        fields.waitForPageLoad();

        WebElement orderIdSearch = driver.findElement(By.name("other_orderno"));
        //JavascriptExecutor executor0 = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", orderIdSearch);
        WebElement submitSearch = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("do_query")));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", submitSearch);

        fields.waitForPageLoad();
        Assert.assertTrue("Didn't redirect to transactions page", driver.getCurrentUrl().
                equals("https://esqa.moneris.com/mpg/reports/transaction/index.php"));

        fields.searchMonerisOrder(newTxnId);
        Assert.assertTrue("Didn't redirect to transactions page3", driver.getCurrentUrl().
                contains("order_history/index.php?order_no="));
        fields.waitForPageLoad();
        String tableID = driver.findElement(By.xpath("//body//table[2]")).getText();
        Assert.assertTrue("Card Type is incorrect or not present", tableID.contains("Visa"));
        Assert.assertTrue("Donation Amount is incorrect or not present", tableID.contains("$1.00"));

        String billingTable = driver.findElement(By.xpath("//body//table[9]")).getText();
        Assert.assertTrue("First Name is incorrect or not present", billingTable.contains("Unit"));
        Assert.assertTrue("Last Name is incorrect or not present", billingTable.contains("Tester"));
        Assert.assertTrue("Address is incorrect or not present", billingTable.contains("1 Hilltop"));
        Assert.assertTrue("City is incorrect or not present", billingTable.contains("Baltimore"));
        Assert.assertTrue("Postal Code is incorrect or not present", billingTable.contains("20001"));
        Assert.assertTrue("Country is incorrect or not present", billingTable.contains("US"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectSingleVal", fields);
    }

    public static void moneriseSelectRecurringVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.navigate().to("https://politicalnetworks.com/page/12863/donate/1?mode=DEMO");

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
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12863/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        System.out.println("monval2 " + bodytext);
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8606"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        driver.navigate().to(MONERISDASHBOARD);
        fields.waitForPageLoad();
        fields.monerisLogin();
        fields.waitForPageLoad();
        WebElement reportsOver = driver.findElement(By.id("mrc_reports"));
        Actions action = new Actions(driver);
        action.moveToElement(reportsOver).build().perform();
        WebElement transaction = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[2]//ul[1]//li[1]//a[1]")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", transaction);
        fields.waitForPageLoad();

        WebElement orderIdSearch = driver.findElement(By.name("other_orderno"));
        orderIdSearch.click();
        WebElement submitSearch = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("do_query")));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", submitSearch);

        fields.waitForPageLoad();
        Assert.assertTrue("Didn't redirect to transactions page", driver.getCurrentUrl().
                equals("https://esqa.moneris.com/mpg/reports/transaction/index.php"));

        fields.searchMonerisOrder(newTxnId);
        Assert.assertTrue("Didn't redirect to transactions page3", driver.getCurrentUrl().
                contains("order_history/index.php?order_no="));
        fields.waitForPageLoad();
        String tableID = driver.findElement(By.xpath("//body//table[2]")).getText();
        Assert.assertTrue("Card Type is incorrect or not present", tableID.contains("Visa"));
        Assert.assertTrue("Donation Amount is incorrect or not present", tableID.contains("$15.00"));

        String billingTable = driver.findElement(By.xpath("//body//table[9]")).getText();
        Assert.assertTrue("First Name is incorrect or not present", billingTable.contains("Unit"));
        Assert.assertTrue("Last Name is incorrect or not present", billingTable.contains("Tester"));
        Assert.assertTrue("Address is incorrect or not present", billingTable.contains("1 Hilltop"));
        Assert.assertTrue("City is incorrect or not present", billingTable.contains("Baltimore"));
        Assert.assertTrue("Postal Code is incorrect or not present", billingTable.contains("20001"));
        Assert.assertTrue("Country is incorrect or not present", billingTable.contains("US"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectRecurringVal", fields);
    }

    public static void moneriseSelectSingleVal3D(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.navigate().to("https://politicalnetworks.com/page/12864/donate/1?mode=DEMO");

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
        fields.setCCNUmber("4012001037141112");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();
        fields.waitForURLToChange("https://pit.3dsecure.net/");

        //Assert user got redirected to payment page
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Submit payment page", myurl.contains("https://pit.3dsecure.net/"));
        fields.waitForPageLoad();

        WebElement myCompleteDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input[1]")));
        myCompleteDynamicElement.click();
        fields.waitForPageLoad();

        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12864/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        System.out.println("monval3 " + bodytext);
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8607"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$20.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        driver.navigate().to(MONERISDASHBOARD);
        fields.waitForPageLoad();
        fields.monerisLogin();
        fields.waitForPageLoad();
        WebElement reportsOver = driver.findElement(By.id("mrc_reports"));
        Actions action = new Actions(driver);
        action.moveToElement(reportsOver).build().perform();
        WebElement transaction = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[2]//ul[1]//li[1]//a[1]")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", transaction);
        fields.waitForPageLoad();

        WebElement orderIdSearch = driver.findElement(By.name("other_orderno"));
        orderIdSearch.click();
        WebElement submitSearch = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("do_query")));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", submitSearch);

        fields.waitForPageLoad();
        Assert.assertTrue("Didn't redirect to transactions page", driver.getCurrentUrl().
                equals("https://esqa.moneris.com/mpg/reports/transaction/index.php"));

        fields.searchMonerisOrder(newTxnId);
        Assert.assertTrue("Didn't redirect to transactions page3", driver.getCurrentUrl().
                contains("order_history/index.php?order_no="));
        fields.waitForPageLoad();
        String tableID = driver.findElement(By.xpath("//body//table[2]")).getText();
        Assert.assertTrue("Card Type is incorrect or not present", tableID.contains("Visa"));
        Assert.assertTrue("Donation Amount is incorrect or not present", tableID.contains("$20.00"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectSingleVal3D", fields);
    }

    public static void moneriseSelectRecurring3DVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.navigate().to("https://politicalnetworks.com/page/12863/donate/1?mode=DEMO");

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

        fields.clickRecurringPaymentchkbox();
        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4012001037141112");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();
        fields.waitForURLToChange("https://pit.3dsecure.net/");

        //Assert user got redirected to payment page
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Didn't redirect to Submit payment page", myurl.contains("https://pit.3dsecure.net/"));
        fields.waitForPageLoad();

        WebElement myCompleteDynamicElement = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("/html/body/form/table/tbody/tr/td/table/tbody/tr[4]/td/input[1]")));
        myCompleteDynamicElement.click();
        fields.waitForPageLoad();

        //		Assert that the payment was successful and the third page was reached
        String myurlFinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlFinal.equals("https://politicalnetworks.com/page/12863/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        System.out.println("monval4 " + bodytext);
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8606"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$20.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        driver.navigate().to(MONERISDASHBOARD);
        fields.waitForPageLoad();
        fields.monerisLogin();
        fields.waitForPageLoad();
        WebElement reportsOver = driver.findElement(By.id("mrc_reports"));
        Actions action = new Actions(driver);
        action.moveToElement(reportsOver).build().perform();
        WebElement transaction = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[2]//ul[1]//li[1]//a[1]")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", transaction);
        fields.waitForPageLoad();

        WebElement orderIdSearch = driver.findElement(By.name("other_orderno"));
        orderIdSearch.click();
        WebElement submitSearch = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("do_query")));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor1.executeScript("arguments[0].click();", submitSearch);

        fields.waitForPageLoad();
        Assert.assertTrue("Didn't redirect to transactions page", driver.getCurrentUrl().
                equals("https://esqa.moneris.com/mpg/reports/transaction/index.php"));

        fields.searchMonerisOrder(newTxnId);
        Assert.assertTrue("Didn't redirect to transactions page3", driver.getCurrentUrl().
                contains("order_history/index.php?order_no="));
        fields.waitForPageLoad();
        String tableID = driver.findElement(By.xpath("//body//table[2]")).getText();
        Assert.assertTrue("Card Type is incorrect or not present", tableID.contains("Visa"));
        Assert.assertTrue("Donation Amount is incorrect or not present", tableID.contains("$20.00"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectRecurring3DVal", fields);
    }
}
