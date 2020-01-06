package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ACI {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void aciSingleVisa(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Visa#4120300909000003 test number
        driver.get("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        System.out.println("Visa1: transaction complete");

        //Testing Visa#4120300909000011 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: Visa"));

        System.out.println("Visa2: transaction complete");

        //Testing Visa#4120300909000029 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: Visa"));

        System.out.println("Visa3: transaction complete");

        //Testing Visa#4120300909000037 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: Visa"));

        System.out.println("Visa4: transaction complete");

        //Testing Visa#4120300909000045 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: Visa"));

        System.out.println("Visa5: transaction complete");

        //Testing Visa#4120300909000052 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: Visa"));

        System.out.println("Visa6: transaction complete");

        //Testing Visa#4120300909000060 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext6 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext6.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext6.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext6.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext6.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext6.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext6.contains("TEST: Visa"));

        System.out.println("Visa7: transaction complete");
        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleVisa", fields);
    }

    public static void aciSingleMasterCard(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing MD#5102590909090900 test number
        driver.get("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MasterCard"));

        System.out.println("MD1: transaction complete");

        //Testing MD#5102590909090918 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: MasterCard"));

        System.out.println("MD2: transaction complete");

        //Testing MD#5102590909090926 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: MasterCard"));

        System.out.println("MD3: transaction complete");

        //Testing MD#5102590909090934 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: MasterCard"));

        System.out.println("MD4: transaction complete");

        //Testing MD#5102590909090942 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: MasterCard"));

        System.out.println("MD5: transaction complete");

        //Testing MD#5102590909090959 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: MasterCard"));

        System.out.println("MD6: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleMasterCard", fields);
    }

    public static void aciSingleDiscover(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing D#5102590909090900 test number
        driver.get("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011595932208781");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Discover"));

        System.out.println("D1: transaction complete");

        //Testing D#6011555547691185 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011555547691185");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: Discover"));

        System.out.println("D2: transaction complete");

        //Testing D#6011801722677189 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011801722677189");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: Discover"));

        System.out.println("D3: transaction complete");

        //Testing D#6011360911773382 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011360911773382");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: Discover"));

        System.out.println("D4: transaction complete");

        //Testing D#6011797356770904 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011797356770904");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: Discover"));

        System.out.println("D5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleDiscover", fields);
    }

    public static void aciSingleAmex(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Amex#371256509288675 test number
        driver.get("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("371256509288675");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: AMEX"));

        System.out.println("Amex1: transaction complete");

        //Testing AMEX#376340447073095 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();
        fields.waitForPageLoad();
        fields.waitForPageLoad();
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("376340447073095");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: AMEX"));

        System.out.println("Amex2: transaction complete");

        //Testing Amex#345116909694489 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("345116909694489");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: AMEX"));

        System.out.println("Amex3: transaction complete");

        //Testing D#345477538563440 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("345477538563440");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: AMEX"));

        System.out.println("Amex4: transaction complete");

        //Testing Amex#341517424696707 test number
        driver.navigate().to("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("341517424696707");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: AMEX"));

        System.out.println("Amex5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleAmex", fields);
    }

    public static void aciSingleErrors(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        //Testing D#5102590909090900 test number
        driver.get("https://politicalnetworks.com/page/12988/donate/1?mode=DEMO");

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
        fields.setCCExpiry(new CharSequence[] {"10", "2019"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();
        WebElement errorMessage = driver.findElement(By.xpath("//li[@class='en__error']"));
        Assert.assertTrue(errorMessage.getText().contains("Invalid credit card expiry:"));
        fields.clearCCV();
        fields.setCCExpiry(new CharSequence[] {"12", "2021"});
        fields.clearCCNumber();
        fields.setCCNUmber("412030090900000");
        fields.submit();
        fields.waitForPageLoad();
        WebElement errorMessageCC = driver.findElement(By.xpath("//li[@class='en__error']"));
        Assert.assertTrue(errorMessageCC.getText().equals("This transaction has failed as there has been an error in processing your payment."));

        fields.clearCCNumber();
        fields.setCCNUmber("4120300909000060");
        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        System.out.println("Error: transaction complete");
    }

    public static void aciRecurringVisa(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Visa#4120300909000003 test number
        driver.get("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Visa"));

        System.out.println("Visa1: transaction complete");

        //Testing Visa#4120300909000011 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: Visa"));

        System.out.println("Visa2: transaction complete");

        //Testing Visa#4120300909000029 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: Visa"));

        System.out.println("Visa3: transaction complete");

        //Testing Visa#4120300909000037 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: Visa"));

        System.out.println("Visa4: transaction complete");

        //Testing Visa#4120300909000045 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: Visa"));

        System.out.println("Visa5: transaction complete");

        //Testing Visa#4120300909000052 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: Visa"));

        System.out.println("Visa6: transaction complete");

        //Testing Visa#4120300909000060 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext6 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext6.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext6.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext6.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext6.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext6.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext6.contains("TEST: Visa"));

        System.out.println("Visa7: transaction complete");
        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringVisa", fields);
    }

    public static void aciRecurringMasterCard(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing MD#5102590909090900 test number
        driver.get("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MasterCard"));

        System.out.println("MD1: transaction complete");

        //Testing MD#5102590909090918 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: MasterCard"));

        System.out.println("MD2: transaction complete");

        //Testing MD#5102590909090926 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: MasterCard"));

        System.out.println("MD3: transaction complete");

        //Testing MD#5102590909090934 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: MasterCard"));

        System.out.println("MD4: transaction complete");

        //Testing MD#5102590909090942 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: MasterCard"));

        System.out.println("MD5: transaction complete");

        //Testing MD#5102590909090959 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: MasterCard"));

        System.out.println("MD6: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringMasterCard", fields);
    }

    public static void aciRecurringDiscover(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing D#5102590909090900 test number
        driver.get("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011595932208781");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Discover"));

        System.out.println("D1: transaction complete");

        //Testing D#6011555547691185 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        fields.setRecurPeriod("2");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011555547691185");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: Discover"));

        System.out.println("D2: transaction complete");

        //Testing D#6011801722677189 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        fields.setRecurPeriod("2");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011801722677189");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: Discover"));

        System.out.println("D3: transaction complete");

        //Testing D#6011360911773382 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011360911773382");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: Discover"));

        System.out.println("D4: transaction complete");

        //Testing D#6011797356770904 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("6011797356770904");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: Discover"));

        System.out.println("D5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringDiscover", fields);
    }

    public static void aciRecurringAmex(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Amex#371256509288675 test number
        driver.get("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("371256509288675");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: AMEX"));

        System.out.println("Amex1: transaction complete");

        //Testing AMEX#376340447073095 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        fields.setRecurPeriod("2");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("376340447073095");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: AMEX"));

        System.out.println("Amex2: transaction complete");

        //Testing Amex#345116909694489 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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
        fields.setRecurPeriod("2");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("345116909694489");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: AMEX"));

        System.out.println("Amex3: transaction complete");

        //Testing D#345477538563440 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("345477538563440");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: AMEX"));

        System.out.println("Amex4: transaction complete");

        //Testing Amex#341517424696707 test number
        driver.navigate().to("https://politicalnetworks.com/page/12989/donate/1?mode=DEMO");

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

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("341517424696707");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("1234");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://politicalnetworks.com/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: AMEX"));

        System.out.println("Amex5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringAmex", fields);
    }

    public static void aciRecurringACH(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();

        driver.get("https://politicalnetworks.com/page/12991/donate/1?mode=DEMO");

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

        fields.selectPaymentType("ACH");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.selectBankAccType("PERSONAL CHECKING");
        fields.setBankAccNumber("031000011");
        fields.setBankRoutingNumber("222371863");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("MONTHLY");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12991/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8761"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: ACH"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "aciRecurringACH", fields);
    }

    public static void aciSingleACH(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        page.ensAuthTest();

        driver.get("https://politicalnetworks.com/page/12993/donate/1?mode=DEMO");

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

        fields.selectPaymentType("ACH");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.selectBankAccType("PERSONAL SAVINGS");
        fields.setBankAccNumber("031000011");
        fields.setBankRoutingNumber("222371863");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12993/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8763"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: ACH"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "aciSingleACH", fields);
    }
}
