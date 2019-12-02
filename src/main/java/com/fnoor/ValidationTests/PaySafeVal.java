package com.fnoor.ValidationTests;

import com.fnoor.FundraisingPageDriver;
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

import static com.fnoor.PageFields.PAYSAFEDASHBOARD;

public class PaySafeVal {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static String FUNDRAISING_TEST;

    public static void paySafeSelectSingleVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.get("https://politicalnetworks.com/page/12866/donate/1?mode=DEMO");

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
        fields.setCCNUmber("4000000000001000");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12866/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8609"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$1.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        driver.navigate().to(PAYSAFEDASHBOARD);
        fields.waitForPageLoad();
        fields.paySafeLogin();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't login to PaySafe", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/stmt/showStatement.htm?csrfToken"));
        fields.reportSearchPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/reports/showActivity.htm?csrfToken"));
        fields.generateReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to generate reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/search.htm"));
        fields.authReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to authorization reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getSummaryByAcct.htm"));
        fields.enReportSafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnList.htm"));
        fields.searchPaySafeOrder(newTxnId);
        fields.waitForPageLoad();

        Assert.assertTrue("Didn't navigate to purchase details page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnDetail.htm"));
        Assert.assertTrue("Name is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[1]/div[2]"))
                .getText().equals("UNIT TESTER"));
        System.out.println("Email" + new_email.toUpperCase());
        Assert.assertTrue("Email is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[2]/div[2]"))
                .getText().equals(new_email.toUpperCase()));
        Assert.assertTrue("Address is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[1]/div[2]"))
                .getText().equals("1 Hilltop"));
        Assert.assertTrue("City is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[3]/div[2]"))
                .getText().equals("Baltimore"));
        Assert.assertTrue("State is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[4]/div[2]"))
                .getText().equals("MD"));
        Assert.assertTrue("Country is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[5]/div[2]"))
                .getText().equals("US"));
        Assert.assertTrue("Zip code is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[6]/div[2]"))
                .getText().equals("20001"));
        Assert.assertTrue("Donation amount is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]"))
                .getText().equals("1.00 USD"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "paySafeSelectSingleVal", fields);
    }

    public static void paySafeSelectRecurringVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.get("https://politicalnetworks.com/page/12866/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
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
        fields.setCCNUmber("4000000000001000");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12866/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8609"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        driver.navigate().to(PAYSAFEDASHBOARD);
        fields.waitForPageLoad();
        fields.paySafeLogin();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't login to PaySafe", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/stmt/showStatement.htm?csrfToken"));
        fields.reportSearchPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/reports/showActivity.htm?csrfToken"));
        fields.generateReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to generate reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/search.htm"));
        fields.authReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to authorization reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getSummaryByAcct.htm"));
        fields.enReportSafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnList.htm"));
        fields.searchPaySafeOrder(newTxnId);
        fields.waitForPageLoad();

        Assert.assertTrue("Didn't navigate to purchase details page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnDetail.htm"));
        Assert.assertTrue("Name is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[1]/div[2]"))
                .getText().equals("UNIT TESTER"));
        Assert.assertTrue("Email is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[2]/div[2]"))
                .getText().equals(new_email.toUpperCase()));
        Assert.assertTrue("Address is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[1]/div[2]"))
                .getText().equals("1 Hilltop"));
        Assert.assertTrue("City is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[3]/div[2]"))
                .getText().equals("Baltimore"));
        Assert.assertTrue("State is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[4]/div[2]"))
                .getText().equals("MD"));
        Assert.assertTrue("Country is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[5]/div[2]"))
                .getText().equals("US"));
        Assert.assertTrue("Zip code is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[6]/div[2]"))
                .getText().equals("20001"));
        Assert.assertTrue("Donation amount is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]"))
                .getText().equals("10.00 USD"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "paySafeSelectRecurringVal", fields);
    }

    public static void paySafeSelectSingle3DVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.get("https://politicalnetworks.com/page/12871/donate/1?mode=DEMO");

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
        fields.setCCNUmber("4000000000001091");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //      Validate 3D authentication
        fields.waitForPageLoad();
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        WebElement cancelTransaction = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("challengeCancel")));
        cancelTransaction.submit();
        fields.waitForPageLoad();
        Assert.assertTrue("Your transaction didn't go through" ,
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12871/donate/2?val"));
        fields.submit();
        fields.waitForPageLoad();
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        WebElement resendCode = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("resendChallengeData")));
        resendCode.click();
        WebElement alertMessage = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert")));
        Assert.assertTrue("Donation amount is incorrect or not present" ,
                alertMessage.getText().contains("Your code has been resent."));
        fields.waitForPageLoad();
        WebElement donationAmount = driver.findElement(By.cssSelector(".challengeinfotext"));
        Assert.assertTrue("Donation amount is incorrect or not present" ,
                donationAmount.getText().contains("$20.00"));
        WebElement otp = driver.findElement(By.name("challengeDataEntry"));
        otp.sendKeys("1234");
        WebElement submit = driver.findElement(By.cssSelector(".button.primary"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", submit);
        fields.waitForPageLoad();


        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12871/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8615"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$20.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        //      Validate PaySafe Dashboard for succsessfull transaction

        driver.navigate().to(PAYSAFEDASHBOARD);
        fields.waitForPageLoad();
        fields.paySafeLogin();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't login to PaySafe", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/stmt/showStatement.htm?csrfToken"));
        fields.reportSearchPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/reports/showActivity.htm?csrfToken"));
        fields.generateReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to generate reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/search.htm"));
        fields.authReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to authorization reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getSummaryByAcct.htm"));
        fields.enReportSafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnList.htm"));
        fields.searchPaySafeOrder(newTxnId);
        fields.waitForPageLoad();

        Assert.assertTrue("Didn't navigate to purchase details page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnDetail.htm"));
        Assert.assertTrue("Name is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[1]/div[2]"))
                .getText().equals("UNIT TESTER"));
        Assert.assertTrue("Email is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[2]/div[2]"))
                .getText().equals(new_email.toUpperCase()));
        Assert.assertTrue("Address is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[1]/div[2]"))
                .getText().equals("1 Hilltop"));
        Assert.assertTrue("City is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[3]/div[2]"))
                .getText().equals("Baltimore"));
        Assert.assertTrue("State is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[4]/div[2]"))
                .getText().equals("MD"));
        Assert.assertTrue("Country is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[5]/div[2]"))
                .getText().equals("US"));
        Assert.assertTrue("Zip code is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[6]/div[2]"))
                .getText().equals("20001"));
        Assert.assertTrue("Donation amount is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]"))
                .getText().equals("20.00 USD"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paySafeSelectSingle3DVal", fields);
    }

    public static void paySafeSelectRecurring3DVal(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTestVal();
        driver.get("https://politicalnetworks.com/page/12871/donate/1?mode=DEMO");

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
        fields.setCCNUmber("4000000000001091");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //      Validate 3D authentication
        fields.waitForPageLoad();
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        WebElement cancelTransaction = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.name("challengeCancel")));
        cancelTransaction.submit();
        fields.waitForPageLoad();
        Assert.assertTrue("Your transaction didn't go through" ,
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12871/donate/2?val"));
        fields.submit();
        fields.waitForPageLoad();
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        WebElement resendCode = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("resendChallengeData")));
        resendCode.click();
        WebElement alertMessage = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert")));
        Assert.assertTrue("Donation amount is incorrect or not present" ,
                alertMessage.getText().contains("Your code has been resent."));
        fields.waitForPageLoad();
        WebElement donationAmount = driver.findElement(By.cssSelector(".challengeinfotext"));
        Assert.assertTrue("Donation amount is incorrect or not present" ,
                donationAmount.getText().contains("$15.00"));
        WebElement otp = driver.findElement(By.name("challengeDataEntry"));
        otp.sendKeys("1234");
        WebElement submit = driver.findElement(By.cssSelector(".button.primary"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", submit);
        fields.waitForPageLoad();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12871/donate/3"));

        String txnId = driver.findElement(By.cssSelector(".txnID")).getText();
        String newTxnId = txnId.replaceAll("TXN ID:-\\s", " ");
        System.out.println("ID: " + newTxnId);

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8615"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        driver.navigate().to(PAYSAFEDASHBOARD);
        fields.waitForPageLoad();
        fields.paySafeLogin();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't login to PaySafe", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/stmt/showStatement.htm?csrfToken"));
        fields.reportSearchPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                contains("https://login.test.netbanx.com/office/reports/showActivity.htm?csrfToken"));
        fields.generateReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to generate reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/search.htm"));
        fields.authReportPaySafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to authorization reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getSummaryByAcct.htm"));
        fields.enReportSafe();
        fields.waitForPageLoad();
        Assert.assertTrue("Didn't navigate to reports page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnList.htm"));
        fields.searchPaySafeOrder(newTxnId);
        fields.waitForPageLoad();

        Assert.assertTrue("Didn't navigate to purchase details page", driver.getCurrentUrl().
                equals("https://login.test.netbanx.com/office/reports/getTxnDetail.htm"));
        Assert.assertTrue("Name is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[1]/div[2]"))
                .getText().equals("UNIT TESTER"));
        Assert.assertTrue("Email is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[1]/div[2]/div[2]"))
                .getText().equals(new_email.toUpperCase()));
        Assert.assertTrue("Address is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[1]/div[2]"))
                .getText().equals("1 Hilltop"));
        Assert.assertTrue("City is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[3]/div[2]"))
                .getText().equals("Baltimore"));
        Assert.assertTrue("State is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[4]/div[2]"))
                .getText().equals("MD"));
        Assert.assertTrue("Country is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[5]/div[2]"))
                .getText().equals("US"));
        Assert.assertTrue("Zip code is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[1]/table[2]/tbody[1]/tr[1]/td[2]/div[6]/div[2]"))
                .getText().equals("20001"));
        Assert.assertTrue("Donation amount is incorrect or not present", driver.findElement(
                By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[2]"))
                .getText().equals("15.00 USD"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "paySafeSelectRecurring3DVal", fields);
    }
}
