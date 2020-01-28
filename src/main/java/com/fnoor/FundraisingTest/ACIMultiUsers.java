package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ACIMultiUsers {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void aciSingleVisaMultiUsers(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Visa#4120300909000003 test number
        driver.get("https://politicalnetworks.com/page/13031/donate/1");

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
        fields.setCCNUmber("4120300909000003");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        System.out.println("Visa1: transaction complete");

        //Testing Visa#4120300909000011 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber("4120300909000011");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: Visa"));

        System.out.println("Visa2: transaction complete");

        //Testing Visa#4120300909000029 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber("4120300909000029");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: Visa"));

        System.out.println("Visa3: transaction complete");

        //Testing Visa#4120300909000037 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.waitForPageLoad();
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000037");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: Visa"));

        System.out.println("Visa4: transaction complete");

        //Testing Visa#4120300909000045 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber("4120300909000045");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: Visa"));

        System.out.println("Visa5: transaction complete");

        //Testing Visa#4120300909000052 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber("4120300909000052");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: Visa"));

        System.out.println("Visa6: transaction complete");

        //Testing Visa#4120300909000060 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber("4120300909000060");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext6 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext6.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext6.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext6.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext6.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext6.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext6.contains("TEST: Visa"));

        System.out.println("Visa7: transaction complete");
        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleVisaMultiUsers", fields);
    }

    public static void aciSingleMasterCardMultiUsers(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing MD#5102590909090900 test number
        driver.get("https://politicalnetworks.com/page/13031/donate/1");

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

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090900");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MasterCard"));

        System.out.println("MD1: transaction complete");

        //Testing MD#5102590909090918 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090918");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: MasterCard"));

        System.out.println("MD2: transaction complete");

        //Testing MD#5102590909090926 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090926");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: MasterCard"));

        System.out.println("MD3: transaction complete");

        //Testing MD#5102590909090934 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090934");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: MasterCard"));

        System.out.println("MD4: transaction complete");

        //Testing MD#5102590909090942 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090942");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: MasterCard"));

        System.out.println("MD5: transaction complete");

        //Testing MD#5102590909090959 test number
        driver.navigate().to("https://politicalnetworks.com/page/13031/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("EUR");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090959");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13031/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8812"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: MasterCard"));

        System.out.println("MD6: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleMasterCardMultiUsers", fields);
    }

    public static void aciRecurringVisaMultiUsers(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Visa#4120300909000003 test number
        driver.get("https://politicalnetworks.com/page/13032/donate/1");

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
        fields.setRecurFreq("DAILY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000003");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        System.out.println("Visa1: transaction complete");

        //Testing Visa#4120300909000011 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000011");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: Visa"));

        System.out.println("Visa2: transaction complete");

        //Testing Visa#4120300909000029 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000029");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: Visa"));

        System.out.println("Visa3: transaction complete");

        //Testing Visa#4120300909000037 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000037");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: Visa"));

        System.out.println("Visa4: transaction complete");

        //Testing Visa#4120300909000045 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000045");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: Visa"));

        System.out.println("Visa5: transaction complete");

        //Testing Visa#4120300909000052 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000052");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: Visa"));

        System.out.println("Visa6: transaction complete");

        //Testing Visa#4120300909000060 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4120300909000060");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext6 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext6.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext6.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext6.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext6.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext6.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext6.contains("TEST: Visa"));

        System.out.println("Visa7: transaction complete");
        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringVisaMultiUsers", fields);
    }

    public static void aciRecurringMasterCardMultiUsers(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing MD#5102590909090900 test number
        driver.get("https://politicalnetworks.com/page/13032/donate/1");

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
        fields.setRecurFreq("DAILY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090900");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MasterCard"));

        System.out.println("MD1: transaction complete");

        //Testing MD#5102590909090918 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.waitForPageLoad();
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090918");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: MasterCard"));

        System.out.println("MD2: transaction complete");

        //Testing MD#5102590909090926 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090926");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: MasterCard"));

        System.out.println("MD3: transaction complete");

        //Testing MD#5102590909090934 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090934");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: MasterCard"));

        System.out.println("MD4: transaction complete");

        //Testing MD#5102590909090942 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090942");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: MasterCard"));

        System.out.println("MD5: transaction complete");

        //Testing MD#5102590909090959 test number
        driver.navigate().to("https://politicalnetworks.com/page/13032/donate/1");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("EUR");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("5102590909090959");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/13032/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8813"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: MasterCard"));

        System.out.println("MD6: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringMasterCardMultiUsers", fields);
    }

}
