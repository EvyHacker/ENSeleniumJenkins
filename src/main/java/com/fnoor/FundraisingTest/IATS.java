package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.fnoor.PageFields.ENLOGIN;

@Test(groups = {"donations"})
public class IATS {


    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static String FUNDRAISING_TEST;


    public static void iatsSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/841/donate/1?mode=DEMO");

        fields.waitForPageLoad();
        Thread.sleep(2000);
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

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

//		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/841/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3509"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("VISA"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "iatsSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST = "iatsSingle", fields);
    }


    public static void IATSRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/861/donate/1?mode=DEMO");

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
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/861/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3510"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("VISA"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "IATSRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST = "IATSRecurring", fields);
    }

    public static void IATSACHRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        //  page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/5724/donate/1?mode=DEMO");

        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.setRegion("MD");
        fields.setPostCode("20001");
        fields.setCountry("US");

        fields.submit();

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.selectDonationAmt("15");
        fields.selectPaymentType("ACHEFT");
        fields.selectPayCurrency("GBP");
        fields.setCCName("Unit Tester");
        fields.selectBankAccType("Checking");
        fields.setBankAccNumber("1234567");
        fields.setBankRoutingNumber("000000000");

        fields.submit();


        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/5724/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("4542"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("ACHEFT"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "IATSACHRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST = "IATSACHRecurring", fields);
    }

    public static void IATSACHRecurPaymenttypelogic(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        //  page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/5725/donate/1?mode=DEMO");

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
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        // verify that transaction fails

        Assert.assertTrue(driver.getCurrentUrl().equals("https://politicalnetworks.com/page/5725/donate/2?val"));

        fields.selectPaymentType("ACHEFT");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.selectBankAccType("Checking");
        fields.setBankAccNumber("1234567");
        fields.setBankRoutingNumber("000000000");


        // fields.clickRecurringSinglePaymentchkbox();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/5725/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("4543"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("ACHEFT"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "IATSACHRecurPaymenttypelogic", fields);
        page.getSupporterById(FUNDRAISING_TEST = "IATSACHRecurPaymenttypelogic", fields);
    }

    public static void IATSvalidateTransaction(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.navigate().to(ENLOGIN);
        fields.enLogin();
        fields.waitForPageLoad();
        Thread.sleep(2000);
        LocalDate date = LocalDate.now();
        //String strDate = format(date);

        //Validate IATSsingle transaction
        fields.searchSupporter("pb_iatssingle_" + date.toString() + "@tellamazingstories.com");
        Thread.sleep(2000);

        // Validate supporter Details
        fields.selectSupporter();
        Thread.sleep(2000);
        Assert.assertTrue("First name missing from supporter details", fields.getSupporterDetails().contains("Unit"));
        Assert.assertTrue("Last name missing from supporter details", fields.getSupporterDetails().contains("Tester"));
        Assert.assertTrue("Address1 missing from supporter details", fields.getSupporterDetails().contains("1 Hilltop"));
        Assert.assertTrue("City missing from supporter details", fields.getSupporterDetails().contains("Baltimore"));
        Assert.assertTrue("Region name missing from supporter details", fields.getSupporterDetails().contains("MD"));
        Assert.assertTrue("Country name missing from supporter details", fields.getSupporterDetails().contains("US"));
        Assert.assertTrue("Postcode missing from supporter details", fields.getSupporterDetails().contains("20001"));

        //Validate transaction details
       fields.expendTransaction();
       Thread.sleep(2000);
        Assert.assertTrue("Transaction error, amount is incorrect or missing ",
                fields.getTransactionDetails().contains("Amount 15USD"));
        Assert.assertTrue("Transaction error, status is incorrect or not present",
                fields.getTransactionDetails().contains("success"));
        Assert.assertTrue("Transaction error, gateway is incorrect or not present",
                fields.getTransactionDetails().contains("IATS North America"));
        Assert.assertTrue("Transaction error, payment type is incorrect or not present",
                fields.getTransactionDetails().contains("VISA"));
        Assert.assertTrue("Transaction error, transaction type is incorrect or not present",
                fields.getTransactionDetails().contains("Recurring? N"));
        Assert.assertTrue("Transaction error, campaign ID is missing or incorrect",
                fields.getTransactionDetails().contains("3509"));

        //Validate IATS NA Recurring transaction
        fields.nextSupporter("pb_iatsrecurring_" + date.toString() + "@tellamazingstories.com");
        Thread.sleep(2000);

        // Validate supporter Details
        fields.selectSupporter();
        Thread.sleep(2000);
        Assert.assertTrue("First name missing from supporter details", fields.getSupporterDetails().contains("Unit"));
        Assert.assertTrue("Last name missing from supporter details", fields.getSupporterDetails().contains("Tester"));
        Assert.assertTrue("Address1 missing from supporter details", fields.getSupporterDetails().contains("1 Hilltop"));
        Assert.assertTrue("City missing from supporter details", fields.getSupporterDetails().contains("Baltimore"));
        Assert.assertTrue("Region name missing from supporter details", fields.getSupporterDetails().contains("MD"));
        Assert.assertTrue("Country name missing from supporter details", fields.getSupporterDetails().contains("US"));
        Assert.assertTrue("Postcode missing from supporter details", fields.getSupporterDetails().contains("20001"));

        //Validate transaction details
        fields.expendTransaction();
        Thread.sleep(2000);
        Assert.assertTrue("Transaction error, amount is incorrect or missing ",
                fields.getTransactionDetails().contains("Amount 15USD"));
        Assert.assertTrue("Transaction error, status is incorrect or not present",
                fields.getTransactionDetails().contains("success"));
        Assert.assertTrue("Transaction error, gateway is incorrect or not present",
                fields.getTransactionDetails().contains("IATS North America"));
        Assert.assertTrue("Transaction error, payment type is incorrect or not present",
                fields.getTransactionDetails().contains("VISA"));
        Assert.assertTrue("Transaction error, transaction type is incorrect or not present",
                fields.getTransactionDetails().contains("Recurring? Y"));
        Assert.assertTrue("Transaction error, campaign ID is missing or incorrect",
                fields.getTransactionDetails().contains("3510"));


    }
}
