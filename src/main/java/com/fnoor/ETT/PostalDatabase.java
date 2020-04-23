package com.fnoor.ETT;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.fnoor.PageFields.SERVICE_BASEURL;

public class
PostalDatabase {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static String FUNDRAISING_TEST;

    public static void postalDatabase7(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12889/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("D123AA");
        fields.selectCountry("GB");

        fields.validateTargetTitle("Subject (default): ETT_7 Postal Database (single page) - plain text");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12889/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase7", fields);
    }

    public static void postalDatabase8(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12890/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("D123AA");
        fields.selectCountry("GB");

        fields.validateETTTripleRotationMessage();

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12890/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase8", fields);
    }

    public static void postalDatabase9(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12891/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("D123AA");
        fields.selectCountry("GB");

        fields.validateTargetTitle("Subject (default): ETT_9 Postal Database (single page) - HTML");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12891/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase9", fields);
    }

    public static void postalDatabase10(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12892/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("E123AA");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12892/action/2"));

        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsFirstName("Corin");
        fields.validateETTContactDetailsLastName("Hartley-Pearce");
        fields.validateETTContactDetailsOrganization("Engaging Networks");
        fields.validateETTTargetMessage("Dear Mr Hartley-Pearce,");
        fields.validateETTTargetMessage("My message to Corin Hartley-Pearce");
        fields.validateETTTargetMessage("Message (default): ETT_10 Postal Database (two pages) - plain text");
        fields.validateETTTargetMessage("Kind regards,");
        fields.validateETTTargetMessage("Evy Tester");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12892/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase10", fields);
    }

    public static void postalDatabase11(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12893/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("A123AA");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12893/action/2"));

        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Marta");
        fields.validateETTContactDetailsLastName("Fornal de Seixas");
        fields.validateETTContactDetailsOrganization("Engaging Networks");
        fields.validateETTMessageEditable("Message (default - uneditable): ETT_11 Postal Database (two pages)");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12893/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase11", fields);
    }

    public static void postalDatabase12(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12900/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("D123AA");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12900/action/2"));

        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsFirstName("Priyanka");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsLastName("Shinkar");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("Engaging Networks");

        // Validate message if country=USA
        fields.validateETTTargetHTMLSingleMessage("Dear Ms Shinkar,");
        fields.validateETTTargetHTMLSingleMessage("My message to Priyanka Shinkar");
        fields.validateETTTargetHTMLSingleMessage("Your country is equals to US, so we use this message.");

        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Mr Szymczak,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Dan Szymczak");

        driver.navigate().back();
        fields.selectCountry("GB");
        fields.submit();
        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12900/action/2"));

        // Validate message if country=UK
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsFirstName("Priyanka");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsLastName("Shinkar");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("Engaging Networks");
        fields.validateETTTargetHTMLSingleMessage("Dear Ms Shinkar,");
        fields.validateETTTargetHTMLSingleMessage("My message to Priyanka Shinkar");
        fields.validateETTTargetHTMLSingleMessage("Note: ETT_12 Postal Database (two pages) - HTML (default mesage)");

        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Mr Szymczak,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Dan Szymczak");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12900/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase12", fields);
        }

    public static void postalDatabase19(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12907/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("D123AA");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12907/action/2"));

        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsFirstName("Priyanka");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsLastName("Shinkar");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("Engaging Networks");
        fields.validateETTTargetHTMLSingleMessage("Dear Ms Shinkar,");
        fields.validateETTTargetHTMLSingleMessage("My message to Priyanka Shinkar");
        fields.validateETTTargetHTMLSingleMessage("Note: ETT_19 Postal Database with CC of another Postal Database");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Mr Szymczak,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Dan Szymczak");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12907/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase19", fields);
         }

    public static void postalDatabase20(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12908/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("350 W Brazos Ave");
        fields.setCity("Brazoria");
        fields.selectRegion("TX");
        fields.setPostCode("77422");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals(SERVICE_BASEURL + "/page/12908/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("John");
        fields.validateETTContactDetailsLastName("Culberson");
        fields.validateETTContactDetailsOrganization("Texas District 07");
        fields.validateTargetMultiMessage("Dear Rep. Culberson");
        fields.validateTargetMultiMessage("My message to John Culberson");
        fields.validateTargetMultiMessage("Kind regards,");
        fields.validateTargetMultiMessage("Evy Tester");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12908/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase20", fields);
    }

    public static void postalDatabase21(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12921/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("350 W Brazos Ave");
        fields.setCity("Brazoria");
        fields.selectRegion("TX");
        fields.setPostCode("77422");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals(SERVICE_BASEURL + "/page/12921/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("John");
        fields.validateETTContactDetailsLastName("Culberson");
        fields.validateETTContactDetailsOrganization("Texas District 07");
        fields.validateTargetMultiMessage("Dear Rep. Culberson");
        fields.validateTargetMultiMessage("My message to John Culberson");
        fields.validateTargetMultiMessage("Kind regards,");
        fields.validateTargetMultiMessage("Evy Tester");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12921/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase21", fields);
    }

    public static void postalDatabase38(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/13048/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("W71NG");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/13048/action/2"));

        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsFirstName("Steve");
        fields.validateETTContactDetailsLastName("Pound");
        fields.validateETTContactDetailsOrganization("Ealing North");
        fields.validateETTMessageEditable("Message (default - uneditable): ETT_38 Postal Database (two pages)");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/13048/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "postalDatabase38", fields);
    }
}
