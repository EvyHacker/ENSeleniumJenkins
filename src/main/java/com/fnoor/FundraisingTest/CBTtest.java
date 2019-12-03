package com.fnoor.FundraisingTest;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CBTtest {

    private RemoteWebDriver driver;
    private CBTAPI api;
    private String score;

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
}
