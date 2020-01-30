package com.fnoor.Redirects;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PB_B3_INMEMDON {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static  String FUNDRAISING_TEST;

    public static void inMemoriamDonation(String testId, PageFields fields, WebDriver driver) throws InterruptedException {
        driver.get("https://politicalnetworks.com/page/12135/petition/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Miss");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.submit();

        fields.setAddress1("2001 S Street NW");
        fields.setCity("Washington DC");
        fields.selectRegion("DC");
        fields.setPostCode("20009");
        fields.selectCountry("US");

        fields.setInMemoriam("Y");
        fields.setHonoreeName("Evy");
        fields.setInformName("John");
        fields.setInformEmail("testid_memoriam@tellamazingstories.com");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4222222222222220");
        fields.setCCExpiry(new CharSequence[] {"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();
        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://politicalnetworks.com/page/12136/action/1?chain"));
        Assert.assertTrue("First name is incorrect/ not present",fields.getFirstName().
                equals("Unit"));
        Assert.assertTrue("Last name is incorrect/ not present",fields.getLastName().
                equals("Tester"));
        Assert.assertTrue("Email address is incorrect/ not present",fields.getEmail().
                equals(new_email));

        fields.previewEcard();
        driver.switchTo().frame(0);
        fields.waitForPageLoad();
        String ecardText = driver.findElement(By.id("emailContainer")).getText();
        driver.switchTo().parentFrame();

        Assert.assertTrue("Inform name missing from last page ", ecardText.contains("John"));
        Assert.assertTrue("Donation amount missing from last page", ecardText.contains("$5.00"));
        Assert.assertTrue("Honoree address missing from last page ", ecardText.contains("Evy"));

        fields.closeEcardPreview();
        fields.addEcardMessage("Hi Friend!");
        fields.addEcardrecipient("John");
        fields.addEcardRecipientEmail("testid_inmemorium@tellamazingstories.com");
        fields.addEcardRecipienttoList();
        fields.submit();

        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        org.junit.Assert.assertTrue("Urls are not the same", myurl.equals("https://politicalnetworks.com/page/12136/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Unit"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Tester"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
    }
}
