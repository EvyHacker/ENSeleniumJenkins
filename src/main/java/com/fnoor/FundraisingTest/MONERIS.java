package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MONERIS {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void moneriseSelectSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/845/donate/1?mode=DEMO");

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

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/845/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3519"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectSingle", fields);
    }

    public static void moneriseSelectRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/867/donate/1?mode=DEMO");

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

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("month");
        fields.setRecurCount("6");
        fields.setRecurPeriod("2");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/867/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3520"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="moneriseSelectRecurring", fields);
    }

    public static void monerisVaultRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/869/donate/1?mode=DEMO");

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
        fields.setRecurCount("5");
        fields.setRecurPeriod("6");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/869/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3522"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisVaultRecurring", fields);
    }

    public static void monerisSingleNoCvv(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10938/donate/1?mode=DEMO");

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

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10938/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("6378"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisSingleNoCvv", fields);
    }

    public static void monerisRecurringNoCvv(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/10939/donate/1?mode=DEMO");

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

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4242424242424242");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});

        fields.setRecurDay("23");
        fields.setRecurFreq("month");
        fields.setRecurCount("12");
        fields.setRecurPeriod("12");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/10939/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("6379"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("RECUR_UNMANAGED"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisRecurringNoCvv", fields);
    }

    public static void monerisVault3DSingle(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12779/donate/1?mode=DEMO");

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

        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12779/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("8481"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisVault3DSingle", fields);
    }

    public static void monerisVault3DRecurring(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://politicalnetworks.com/page/12783/donate/1?mode=DEMO");

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

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4012001037141112");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.setRecurDay("23");
        fields.setRecurFreq("MONTHLY");
        fields.setRecurCount("12");
        fields.setRecurPeriod("12");

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

        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://politicalnetworks.com/page/12783/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();

        Assert.assertTrue("Campaign ID not present", bodytext.contains("8489"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Moneris eSelect Vault Canada"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: V"));

        page.getSupporterByEmail(FUNDRAISING_TEST="monerisVault3DRecurring", fields);
    }
}
