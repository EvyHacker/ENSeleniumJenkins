package com.fnoor.FundraisingTest;

import java.io.IOException;
import java.net.URL;

import com.fnoor.FundraisingPageHelper2;
import com.fnoor.PageFields;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;


public class FundraisingPageDriver {

    private RemoteWebDriver driver;
    private CBTAPI api;
    private String score;
    private static  String FUNDRAISING_TEST;

    @Before
    public void setUp() throws Exception {
        String username = System.getenv("CBTUSRNAME").replaceAll("@", "%40");
        String authkey = System.getenv("CBTAUTH");
        System.out.println(username);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("name", "CBT Java");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("platform", "Macc OSX 14");
        caps.setCapability("screenResolution", "1366x768");
        caps.setCapability("record_video", "true");
        // caps.setCapability("version", "75"); // If this cap isn't specified, it will
        // just get the latest one

        // caps.setCapability("build", "1.0"); //Set a build number.
        caps.setCapability("record_network", "false");

        api = new CBTAPI(username, authkey);

        String hubAddress = String.format("http://%s:%s@hub.crossbrowsertesting.com:80/wd/hub", username, authkey);
        URL url = new URL(hubAddress);
        driver = new RemoteWebDriver(url, caps);
        // record a video using the API instead of the capabilities above.
        api.record_video(driver.getSessionId().toString());
    }

    @Test
    public void testToDo() {
        // test 1: Get title.
        driver.get("https://www.whatsmybrowser.org");
        // test 2:check what title equals.
        Assert.assertEquals("What browser am I using?", driver.getTitle());
        System.out.println(driver.getTitle());
        score = "Pass";
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            // Set the score depending on the tests.
            api.setScore(score, driver.getSessionId().toString());
            driver.quit();
            System.out.println(score);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        if(args.length != 3) {
            System.out.println("ERROR: <environment> <webdriver> <TEST_CASE>");
            System.exit(0);
        }

        String env = args[0];
        String webdrive = args[1];
        String testCase = args[2];
        String webDriverProperty = String.format("webdrivers/%s/%s", env, webdrive);
        if (env.equals("win")) {
            webDriverProperty = webDriverProperty + ".exe";
        }

        System.setProperty("webdriver.chrome.driver", webDriverProperty);
        WebDriver driver = new ChromeDriver();
        PageFields fields = PageFactory.initElements(driver, PageFields.class);
        FundraisingPageHelper2 helper = new FundraisingPageHelper2();

        switch (testCase) {
            case "RSM":{
                RSM.rsmSingle(FUNDRAISING_TEST="rsmSingle", fields, driver);
                RSM.rsmRecurring(FUNDRAISING_TEST="rsmRecurring", fields, driver);
                // RSM.rsmDirectDebit(FUNDRAISING_TEST="rsmDirectDebit", fields, driver);
                RSM.rsm3DSingle(FUNDRAISING_TEST="rsm3DSingle", fields, driver);
                break;
            }
            default: {
                throw new IllegalArgumentException("No test case found with id=" + testCase);
            }
        }

        driver.quit();
        }
}
