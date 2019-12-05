package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PAYSAFE {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void paysafeSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/873/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4530910000012345");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/873/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        System.out.println("paysafe1 " + bodytext);
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3529"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paysafeSingle", fields);
    }

    public static void paysafeRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/874/donate/1?mode=DEMO");

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

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4530910000012345");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/874/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        System.out.println("paysafe2 " + bodytext);
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3530"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paysafeRecurring", fields);
    }

    public static void paysafe3DSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12868/donate/1?mode=DEMO");

        fields.selectDonationAmt("1");
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

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000000000001091");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //      Validate 3D authentication
        fields.waitForPageLoad();
        //driver.switchTo().frame("Cardinal-collector");
       // driver.switchTo().frame(0);
//        WebElement otp1 = driver.findElement(By.name("challengeDataEntry"));
//        otp1.sendKeys("1234");
        Thread.sleep(5000);
        List<WebElement> iframes = driver.findElements(By.id("Cardinal-"));
        for (WebElement iframe : iframes)
        {
            System.out.println("Frame " + iframe);
            System.out.println("Frame1 " + iframe.getAttribute("id"));
            System.out.println("Frame2 " + iframe.getAttribute("outerHTML"));
        }
//        actions = ActionChains(self.browser)
//        actions.move_to_element(open_login_modal_btn).click().perform()
//        try{
//            driver.switchTo().frame("Cardinal-CCA-IFrame");
//        } catch (NoSuchFrameException e) {
//        }

        driver.switchTo().frame( driver.findElement( By.id("Cardinal-collector") ) );
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,1000)");
//        WebElement cancelTransaction = (new WebDriverWait(driver, 20))
//                .until(ExpectedConditions.presenceOfElementLocated
//                        (By.className("button")));
        //cancelTransaction.click();
        WebElement cancelTransaction = driver.findElement(By.name("challengeCancel"));
       // if(cancelTransaction.getAttribute("value").equals("CANCEL")){
        cancelTransaction.submit();
        //executor.executeScript("arguments[0].submit();", cancelTransaction);
        fields.waitForPageLoad();
        WebElement alertNote = driver.findElement(By.xpath("//li[@class='en__error']"));
        Assert.assertTrue("Your transaction didn't go through" ,
                alertNote.getText().contains("This transaction has failed as there has been an error in processing your payment."));
        Assert.assertTrue("Donation amount is incorrect or not present" ,
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12868/donate/2?val"));
        fields.submit();
        fields.waitForPageLoad();
        driver.switchTo().frame(1);
        WebElement resendCode = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("resendChallengeData")));
       // executor.executeScript("arguments[0].click();", resendCode);
        resendCode.submit();
        WebElement alertMessage = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert")));
        Assert.assertTrue("The code hasn't been resent" ,
                alertMessage.getText().contains("Your code has been resent."));
        fields.waitForPageLoad();
        WebElement donationAmount = driver.findElement(By.cssSelector(".challengeinfotext"));
        Assert.assertTrue("Donation amount is incorrect or not present" ,
                donationAmount.getText().contains("$1.00"));
        WebElement otp = driver.findElement(By.name("challengeDataEntry"));
        otp.sendKeys("1234");
        WebElement submit = driver.findElement(By.cssSelector(".button.primary"));
        executor.executeScript("arguments[0].click();", submit);
        fields.waitForPageLoad();


        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12868/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8611"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$1.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paysafe3DSingle", fields);
    }

    public static void paysafe3DRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12869/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
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

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000000000001091");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //      Validate 3D authentication
        fields.waitForPageLoad();
        driver.switchTo().frame("Cardinal-CCA-IFrame");
        WebElement resendCode = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("resendChallengeData")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", resendCode);
        WebElement alertMessage = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert")));
        Assert.assertTrue("The code hasn't been resent" ,
                alertMessage.getText().contains("Your code has been resent."));
        fields.waitForPageLoad();
        WebElement donationAmount = driver.findElement(By.cssSelector(".challengeinfotext"));
        Assert.assertTrue("Donation amount is incorrect or not present" ,
                donationAmount.getText().contains("$10.00"));
        WebElement otp = driver.findElement(By.name("challengeDataEntry"));
        otp.sendKeys("1234");
        WebElement submit = driver.findElement(By.cssSelector(".button.primary"));
        executor.executeScript("arguments[0].click();", submit);
        fields.waitForPageLoad();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12869/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8612"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        page.getSupporterByEmail(FUNDRAISING_TEST="paysafe3DRecurring", fields);
    }
}
