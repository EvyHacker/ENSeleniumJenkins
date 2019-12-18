package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.FundraisingPageHelper;
import com.fnoor.PageFields;
import com.sun.xml.internal.ws.api.server.WSWebServiceContext;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

public class Testing {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static FundraisingPageHelper helper = new FundraisingPageHelper();
    private static String FUNDRAISING_TEST;
    public static WebDriver driver;
    PageFields fields;
    String testId;


//    public TestWatcher watcher = new TestWatcher() {     @Override
//    protected void failed(Throwable e, Description description) {
//        status= "falha";
//    }
//        @Override
//        protected void skipped(AssumptionViolatedException e, Description description) {
//            status= "skiped";
//        }
//        @Override
//        protected void succeeded(Description description) {
//            status= "sucesso";
//        }
//        @Override
//        protected void finished(Description d) {
//            try {
//                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("target/teststream.txt", true)));
//                out.println("{\"metodo\":\""+actualTestMethod+"\", \"status\":\""+status+"\", "
//                        + "\"classe\":\""+actualTestClass+"\", \"descricao\":\""+actualTestMethod+"\"}");
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    };

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/ievgeniiagaidarenko/EngagingNetworks/Automation/ENSeleniumJenkins/webdrivers/linux/chromedriver");
        driver = new ChromeDriver();
        fields = PageFactory.initElements(driver, PageFields.class);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
//    @AfterMethod
//    public static void takeSnapShot(ITestResult result) throws Exception{
//
//        if(ITestResult.FAILURE==result.getStatus()){
//
//            //Convert web driver object to TakeScreenshot
//            TakesScreenshot scrShot =((TakesScreenshot)driver);
//
//            //Call getScreenshotAs method to create image file
//            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//
//            //Move image file to new destination
//            File DestFile=new File("./ScreenShots/"+result.getName()+".png");
//            //Copy file at destination
//            FileUtils.copyFile(SrcFile, DestFile);
//            System.out.println("Screenshot taken");
//        }
//
//
//    }

    @Test

    public void paysafe3DSingle() throws InterruptedException, IOException {
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
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //      Validate 3D authentication
        fields.waitForPageLoad();
//        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
//        for (WebElement iframeT : iframes) {
//            System.out.println("Frame " + iframeT);
//            System.out.println("Frame1 " + iframeT.getAttribute("id"));
//            System.out.println("Frame2 " + iframeT.getAttribute("outerHTML"));
           // driver.switchTo().frame("Cardinal-collector");
        //String iframe = driver.findElement(By.id("Cardinal-CCA-IFrame");
           // driver.switchTo().defaultContent();
//        WebElement zaebal = driver.findElement(By.tagName("iframe"));
//        System.out.println("Frame is here " + zaebal);
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        System.out.println("Frame is here " + elements);
        for(WebElement element:elements) {
            // driver.switchTo().defaultContent();
            System.out.println("Frame " + element);
            driver.switchTo().frame(element);
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver).executeScript("alert('hello world');");
            }
            System.out.println("Iframe " + element.getAttribute("id"));
        }
            driver.switchTo().frame("Cardinal-CCA-IFrame");
            System.out.println("Frame I am here");
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

            //driver.switchTo().frame(0);
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
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", submit);
            fields.waitForPageLoad();


            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12868/donate/3"));

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();
            System.out.println("Final page " + bodytext);
            Assert.assertTrue("Campaign ID not present", bodytext.contains("8611"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$1.00"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

            page.getSupporterByEmail(FUNDRAISING_TEST = "paysafe3DSingle", fields);
        }

}
