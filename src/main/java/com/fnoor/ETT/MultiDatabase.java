package com.fnoor.ETT;

import com.fnoor.FundraisingPageHelper;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class MultiDatabase {


    static FundraisingPageHelper helper = new FundraisingPageHelper();
    private static String FUNDRAISING_TEST;

    public static void multiDatabase13(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12901/action/1?mode=DEMO");

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

        fields.validateTargetTitle("Subject (default): ETT_13 Multi Database (single page) - plain text");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12901/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabase13", fields);
    }


    public static void multiDatabase14(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12902/action/1?mode=DEMO");

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

        fields.validateETTMessageEditable("Message (default - uneditable): ETT_14 Multi Database (single page)");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12902/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabase14", fields);
    }

    public static void multiDatabase15(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12903/action/1?mode=DEMO");

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

        fields.validateTargetTitle("Subject (default): ETT_15 Multi Database (single page) - HTML");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12903/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabase15", fields);

    }

    public static void multiDatabase16(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12904/action/1?mode=DEMO");

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
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12904/action/2"));

        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Marta");
        fields.validateETTContactDetailsLastName("Fornal de Seixas");
        fields.validateETTTargetMessage("Dear Ms Fornal de Seixas,");
        fields.validateETTTargetMessage("My message to Marta Fornal de Seixas");
        fields.validateETTTargetMessage("Message (default): ETT_16 Multi Database (two pages) - plain text");
        fields.validateETTTargetMessage("Kind regards,");
        fields.validateETTTargetMessage("Evy Tester");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12904/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabase16", fields);
    }

    public static void multiDatabase17(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12905/action/1?mode=DEMO");

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

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12905/action/2"));

        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Anna");
        fields.validateETTContactDetailsFirstName("Priyanka");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsLastName("Chua");
        fields.validateETTContactDetailsLastName("Shinkar");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("Engaging Networks");

        fields.setETTToggle();
        fields.openETTToggle();
        fields.validateETTMessageEditable("Message (default - uneditable): ETT_17 Multi Database (two pages)");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12905/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("GB"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabase17", fields);
    }

    public static void multiDatabase18(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12906/action/1?mode=DEMO");

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
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12906/action/2"));

        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Anna");
        fields.validateETTContactDetailsFirstName("Priyanka");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsLastName("Chua");
        fields.validateETTContactDetailsLastName("Shinkar");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("Engaging Networks");

        fields.validateETTTargetHTMLSingleMessage("Dear Anna");
        fields.validateETTTargetHTMLSingleMessage("My message to Anna Chua");
        fields.validateETTTargetHTMLSingleMessage("Message (default):  ETT_18 Multiple Database (two pages) - HTML");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Ms Shinkar");
        fields.validateETTTargetHTMLDoubleMessage("My message to Priyanka Shinkar");
        fields.openETTToggle();
        fields.validateETTTargetHTMLTripleMessage("Dear Mr Szymczak");
        fields.validateETTTargetHTMLTripleMessage("My message to Dan Szymczak");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12906/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabase18", fields);
    }

    public static void multiDatabaseCommittees(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12951/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("2620 Jefferson Davis Hwy,");
        fields.setCity("Arlington");
        fields.selectRegion("VA");
        fields.setPostCode("22202");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12951/action/2"));

        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Mark");
        fields.validateETTContactDetailsFirstName("Tim");
        fields.validateETTContactDetailsFirstName("Don");
        fields.validateETTContactDetailsLastName("Warner");
        fields.validateETTContactDetailsLastName("Kaine");
        fields.validateETTContactDetailsLastName("Beyer");
        fields.validateETTContactDetailsOrganization("Virginia");
        fields.validateETTContactDetailsOrganization("Virginia");
        fields.validateETTContactDetailsOrganization("Virginia District 08");

        fields.validateETTTargetHTMLSingleMessage("Dear Senator Warner,");
        fields.validateETTTargetHTMLSingleMessage("My message to Mark R. Warner");
        fields.validateETTTargetHTMLSingleMessage("Message (default): ETT_36 Committees Multi Database All Contacts(two pages) - HTML");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Senator Kaine,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Tim Kaine");
        fields.openETTToggle();
        fields.validateETTTargetHTMLTripleMessage("Dear Representative Beyer,");
        fields.validateETTTargetHTMLTripleMessage("My message to Don Beyer");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12951/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabaseCommittees", fields);
    }

    public static void multiDatabaseCommitteesEdit(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12952/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("3545 Palmer Hwy");
        fields.setCity("Texas City");
        fields.selectRegion("TX");
        fields.setPostCode("77590");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12952/action/2"));

        // Validate custom message if Organization=Texas
        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsFirstName("John");
        fields.validateETTContactDetailsFirstName("Ted");
        fields.validateETTContactDetailsLastName("Cornyn");
        fields.validateETTContactDetailsLastName("Cruz");
        fields.validateETTContactDetailsOrganization("Texas");
        fields.setETTToggle();
        fields.validateETTMessageEditable
                ("Message (default - uneditable): ETT_37 Committees Multi Database Custom Target (two pages). If Organization is equal to Texas, senator should receive this message.");

        //Set new address to validate Vermont custom message
        driver.navigate().back();
        fields.clearAddress1();
        fields.setAddress1("1 Chester Rd");
        fields.clearCity();
        fields.setCity("Springfield");
        fields.selectRegion("VT");
        fields.clearPostCode();
        fields.setPostCode("05156");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12952/action/2"));

        // Validate custom message if Organization=Vermont
        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Peter");
        fields.validateETTContactDetailsLastName("Welch");
        fields.validateETTContactDetailsOrganization("Vermont District 01");
        fields.validateETTMessageEditable
                ("Message (default - uneditable): ETT_37 Committees Multi Database Custom Target (two pages). If Organization is equal to Vermont, representative should recive this message.");

        //Set new address to validate default message
        driver.navigate().back();
        fields.clearAddress1();
        fields.setAddress1("1598 Hawthorne Ave NE");
        fields.clearCity();
        fields.setCity("Salem");
        fields.selectRegion("OR");
        fields.clearPostCode();
        fields.setPostCode("97302");

        fields.submit();
        // Validate default message
        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsFirstName("Jeff");
        fields.validateETTContactDetailsFirstName("Ron");
        fields.validateETTContactDetailsLastName("Merkley");
        fields.validateETTContactDetailsLastName("Wyden");
        fields.validateETTContactDetailsOrganization("Oregon");

        fields.validateETTTargetHTMLSingleMessage("Dear Senator Merkley,");
        fields.validateETTTargetHTMLSingleMessage("My message to Jeff Merkley");
        fields.validateETTTargetHTMLSingleMessage("Message (default): ETT_37 Committees Multi Database Custom Target (two pages) - editable area");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Senator Wyden,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Ron Wyden");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12952/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "multiDatabaseCommitteesEdit", fields);
    }
}
