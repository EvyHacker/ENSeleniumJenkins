package com.fnoor.ETT;

import com.fnoor.FundraisingPageHelper;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class SenateCommittees {

    static FundraisingPageHelper helper = new FundraisingPageHelper();
    private static String FUNDRAISING_TEST;

    public static void senateCommmittees25(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12929/action/1");
    }

    // Validate AK state (Sen. Lisa Murkowski & Sen. Dan Sullivan)

    public static void senateCommmitteesAK(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12930/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("2285 Trout St");
        fields.setCity("Juneau");
        fields.selectRegion("AK");
        fields.setPostCode("99801");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12930/action/2"));

        fields.validateETTTargetHTMLSingleMessage("Dear Senator Murkowski,");
        fields.validateETTTargetHTMLSingleMessage("My message to Lisa Murkowski");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Senator Sullivan,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Dan Sullivan");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12930/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "senateCommmitteesAK", fields);
    }

    // Validate MD state (Sen. Chris Van Hollen & Sen. Benjamin L. Cardin)
    public static void senateCommmitteesMD(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12930/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("15 W North Ave");
        fields.setCity("Baltimore");
        fields.selectRegion("FL");
        fields.setPostCode("00000");
        fields.selectCountry("US");

        fields.submit();
//      Error message validation
        fields.waitForPageLoad();
        WebElement errorNote = driver.findElement(By.className("en__error"));
        Assert.assertTrue("There is no errors on the page", errorNote.getText().equals("Please enter a valid address."));

        fields.selectRegion("MD");
        fields.setPostCode("21201");
        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12930/action/2"));

        fields.validateETTTargetHTMLSingleMessage("Dear Senator Van Hollen,");
        fields.validateETTTargetHTMLSingleMessage("My message to Chris Van Hollen");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Senator Cardin,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Benjamin L. Cardin");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12930/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "senateCommmitteesMD", fields);
    }

    //Banking, Housing, and Urban Affairs
    public static void senateCommmitteesBanking(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12940/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("417 N Broadway");
        fields.setCity("Pennsville");
        fields.selectRegion("NJ");
        fields.setPostCode("08070");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12940/action/2"));

        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsFirstName("Robert");
        fields.validateETTContactDetailsLastName("Menendez");
        fields.validateETTContactDetailsOrganization("New Jersey");
        fields.validateETTTargetHTMLSingleMessage("Dear Senator Menendez,");
        fields.validateETTTargetHTMLSingleMessage("My message to Robert Menendez");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12940/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "senateCommmitteesBanking", fields);
    }

    //Validate Florida senator Marco Rubio
    public static void senateCommmitteesFL(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12941/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("6875 Sand Lake Rd");
        fields.setCity("Orlando");
        fields.selectRegion("FL");
        fields.setPostCode("32819");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12941/action/2"));

        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsFirstName("Marco");
        fields.validateETTContactDetailsLastName("Rubio");
        fields.validateETTContactDetailsOrganization("Florida");
        fields.validateETTTargetHTMLSingleMessage("Dear Senator Rubio,");
        fields.validateETTTargetHTMLSingleMessage("My message to Marco Rubio");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12941/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "senateCommmitteesFL", fields);
    }

    //Validate Custom messages based on rules
    public static void senateCommmitteesCustTarget(String testId, PageFields fields, WebDriver driver) throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12947/action/1");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1695 Great Basin Blvd");
        fields.setCity("Ely");
        fields.selectRegion("NV");
        fields.setPostCode("89301");
        fields.selectCountry("US");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12947/action/2"));

        // Validate custom message if Organization=Nevada
        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsFirstName("Jacky");
        fields.validateETTContactDetailsFirstName("Catherine");
        fields.validateETTContactDetailsLastName("Rosen");
        fields.validateETTContactDetailsLastName("Cortez Masto");
        fields.validateETTContactDetailsOrganization("Nevada");

        fields.validateETTTargetHTMLSingleMessage("Dear Senator Rosen,");
        fields.validateETTTargetHTMLSingleMessage("If organization is equal to Nevada, this message should be sent.");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Senator Cortez Masto,");
        fields.validateETTTargetHTMLDoubleMessage("If organization is equal to Nevada, this message should be sent.");

        //Set new address to validate PA custom message
        driver.navigate().back();
        fields.clearAddress1();
        fields.setAddress1("142 S George St");
        fields.clearCity();
        fields.setCity("York");
        fields.selectRegion("PA");
        fields.clearPostCode();
        fields.setPostCode("17401");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12947/action/2"));

        // Validate custom message if Organization=Pennsylvania
        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsFirstName("Patrick");
        fields.validateETTContactDetailsFirstName("Robert");
        fields.validateETTContactDetailsLastName("J.Toomey");
        fields.validateETTContactDetailsLastName("P.Casey");
        fields.validateETTContactDetailsOrganization("Pennsylvania");

        fields.validateETTTargetHTMLSingleMessage("Dear Senator Toomey,");
        fields.validateETTTargetHTMLSingleMessage("If organization is equal to Pennsylvania, this message should be sent.");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Senator Casey,");
        fields.validateETTTargetHTMLDoubleMessage("If organization is equal to Pennsylvania, this message should be sent.");

        //Set new address to validate AZ default message
        driver.navigate().back();
        fields.clearAddress1();
        fields.setAddress1("252 W Crawford St");
        fields.clearCity();
        fields.setCity("Nogales");
        fields.selectRegion("AZ");
        fields.clearPostCode();
        fields.setPostCode("85621");

        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://politicalnetworks.com/page/12947/action/2"));

        // Validate cdefault message if Organization=Arizona
        fields.validateETTContactDetailsTitle("Sen.");
        fields.validateETTContactDetailsFirstName("Krysten");
        fields.validateETTContactDetailsFirstName("Martha");
        fields.validateETTContactDetailsLastName("Sinema");
        fields.validateETTContactDetailsLastName("McSally");
        fields.validateETTContactDetailsOrganization("Arizona");

        fields.validateETTTargetHTMLSingleMessage("Dear Senator Sinema,");
        fields.validateETTTargetHTMLSingleMessage("My message to Kyrsten Sinema");
        fields.validateETTTargetHTMLSingleMessage("Message (default): ETT_30 Senate Committees Database  Custom target (two pages) - HTML");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Senator McSally,");
        fields.validateETTTargetHTMLDoubleMessage("My message to Martha McSally");
        fields.validateETTTargetHTMLDoubleMessage("Message (default): ETT_30 Senate Committees Database  Custom target (two pages) - HTML");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12947/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        helper.getSupporterByEmailETT(FUNDRAISING_TEST = "senateCommmitteesCustTarget", fields);
    }
}


