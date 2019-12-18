package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.FundraisingPageHelper;
import com.fnoor.PageFields;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Iframe {
    static FundraisingPageDriver page = new FundraisingPageDriver();
    static FundraisingPageHelper helper = new FundraisingPageHelper();
    private static String FUNDRAISING_TEST;
    public static WebDriver driver;
    PageFields fields;
    String testId;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/ievgeniiagaidarenko/EngagingNetworks/Automation/ENSeleniumJenkins/webdrivers/linux/chromedriver");
        driver = new ChromeDriver();
        fields = PageFactory.initElements(driver, PageFields.class);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void piframeTest() throws InterruptedException, IOException {

        driver.get("https://politicalnetworks.com/page/12979/action/1?mode=DEMO");

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframeT : iframes) {
            System.out.println("Frame " + iframeT);
            System.out.println("Frame1 " + iframeT.getAttribute("id"));
            System.out.println("Frame2 " + iframeT.getAttribute("outerHTML"));}
        driver.switchTo().frame(driver.findElement(By.id("Cardinal-collector")));
        System.out.println("Inside the frame");
        driver.switchTo().frame(driver.findElement(By.id("Cardinal-CCA-IFrame")));
        System.out.println("Inside the 2nd frame");
        WebElement zoomInMap = driver.findElement(By.className("leaflet-control-zoom-in"));
        zoomInMap.click();
        fields.waitForPageLoad();
        zoomInMap.click();
        fields.waitForPageLoad();
        Thread.sleep(5000);
        WebElement zoomOutMap = driver.findElement(By.className("leaflet-control-zoom-out"));
        zoomOutMap.click();
        fields.waitForPageLoad();
        System.out.println("I zommed out");
    }
}
