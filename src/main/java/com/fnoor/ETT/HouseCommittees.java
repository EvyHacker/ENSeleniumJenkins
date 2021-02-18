package com.fnoor.ETT;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class HouseCommittees {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static String FUNDRAISING_TEST;
    public static WebDriver driver;
    static PageFields fields;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun=true)
    public void setUp(String browser) throws MalformedURLException {
        driver = page.createInstance(browser);
        fields = PageFactory.initElements(driver, PageFields.class);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(800, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(800, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"houseCommmitteesOH"})
    @Test(groups = { "houseCommittees" })
    public static void houseCommmitteesOH(String testId) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12942/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1261 Ohio Pike");
        fields.setCity("Amelia");
        fields.selectRegion("OH");
        fields.setPostCode("45102");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12942/action/2"));

        fields.validateETTTargetHTMLSingleMessage("Dear Representative Wenstrup,");
        fields.validateETTTargetHTMLSingleMessage("My message to Brad Wenstrup");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12942/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "houseCommmitteesOH", fields);
    }

    // Validate HI state (Rep. Ed Case)
    @Parameters({"houseCommmitteesHI"})
    @Test(groups = { "houseCommittees" })
    public static void houseCommmitteesHI(String testId) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12942/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("377 Keahole St");
        fields.setCity("Honolulu");
        fields.selectRegion("HI");
        fields.setPostCode("96825");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12942/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Ed");
        fields.validateETTContactDetailsLastName("Case");
        fields.validateETTContactDetailsOrganization("Hawaii District 01");
        fields.validateETTTargetHTMLSingleMessage("Dear Representative Case,");
        fields.validateETTTargetHTMLSingleMessage("My message to Ed Case");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12942/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "houseCommmitteesHI", fields);
    }

    // Validate Defense Subcommittee (Rep. Cheri Bustos)
    @Parameters({"houseCommmitteesDefense"})
    @Test(groups = { "houseCommittees" })
    public static void houseCommmitteesDefense(String testId) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12944/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1601 16th Ave");
        fields.setCity("Fulton");
        fields.selectRegion("IL");
        fields.setPostCode("61252");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12944/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Cheri");
        fields.validateETTContactDetailsLastName("Bustos");
        fields.validateETTContactDetailsOrganization("Illinois District 17");
        fields.validateETTTargetHTMLSingleMessage("Dear Representative Bustos,");
        fields.validateETTTargetHTMLSingleMessage("My message to Cheri Bustos");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12944/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "houseCommmitteesDefense", fields);
    }

    // Validate CA state  (Rep. Juan Vargas)
    @Parameters({"houseCommmitteesCA"})
    @Test(groups = { "houseCommittees" })
    public static void houseCommmitteesCA(String testId) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12945/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("619 Broadway");
        fields.setCity("Chula Vista");
        fields.selectRegion("CA");
        fields.setPostCode("91910");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12945/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Juan");
        fields.validateETTContactDetailsLastName("Vargas");
        fields.validateETTContactDetailsOrganization("California District 51");
        fields.validateETTTargetHTMLSingleMessage("Dear Representative Vargas,");
        fields.validateETTTargetHTMLSingleMessage("My message to Juan Vargas");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12945/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "houseCommmitteesCA", fields);
    }

    @Parameters({"houseCommmitteesCustTarget"})
    @Test(groups = { "houseCommittees" })
    public static void houseCommmitteesCustTarget(String testId) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12950/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1275 Parkway Dr");
        fields.setCity("Blackfoot");
        fields.selectRegion("ID");
        fields.setPostCode("83221");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12950/action/2"));

        // Validate custom message if Organization=Idaho District 02
        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Mick");
        fields.validateETTContactDetailsLastName("Simpson");
        fields.validateETTContactDetailsOrganization("Idaho District 02");

        fields.validateETTTargetHTMLSingleMessage("Dear Representative Simpson,");
        fields.validateETTTargetHTMLSingleMessage("If organization is equal to Idaho, this message should be sent.");

        // Validate custom message if Organization=Idaho District 01
        driver.navigate().back();
        fields.clearAddress1();
        fields.setAddress1("205 Bonner Mall Way");
        fields.clearCity();
        fields.setCity("Ponderay");
        fields.clearPostCode();
        fields.setPostCode("83852");
        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12950/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Russ");
        fields.validateETTContactDetailsLastName("Fulcher");
        fields.validateETTContactDetailsOrganization("Idaho District 01");

        fields.validateETTTargetHTMLSingleMessage("Dear Representative Fulcher,");
        fields.validateETTTargetHTMLSingleMessage("If organization is equal to Idaho, this message should be sent.");

        // Validate custom message if Organization=Maine
        driver.navigate().back();
        fields.clearAddress1();
        fields.setAddress1("1502 Maine St");
        fields.clearCity();
        fields.setCity("Poland");
        fields.setRegion("ME");
        fields.clearPostCode();
        fields.setPostCode("04274");
        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12950/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Jared");
        fields.validateETTContactDetailsLastName("Golden");
        fields.validateETTContactDetailsOrganization("Idaho District 01");

        fields.validateETTTargetHTMLSingleMessage("Dear Representative Golden,");
        fields.validateETTTargetHTMLSingleMessage("If organization is equal to Maine, this message should be sent.");

        // Validate custom message if Organization=Rhode Island (default message)
        driver.navigate().back();
        fields.clearAddress1();
        fields.setAddress1("876 Bald Hill Rd");
        fields.clearCity();
        fields.setCity("Warwick");
        fields.setRegion("RI");
        fields.clearPostCode();
        fields.setPostCode("02886");
        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12950/action/2"));

        fields.validateETTContactDetailsTitle("Rep.");
        fields.validateETTContactDetailsFirstName("Jim");
        fields.validateETTContactDetailsLastName("Langevin");
        fields.validateETTContactDetailsOrganization("Idaho District 01");

        fields.validateETTTargetHTMLSingleMessage("Dear Representative Langevin,");
        fields.validateETTTargetHTMLSingleMessage("My message to Jim Langevin");
        fields.validateETTTargetHTMLSingleMessage("Message (default): ETT_35 House Committees Database Custom Target (two pages) - HTML");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12950/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "houseCommmitteesCustTarget", fields);
    }
}
