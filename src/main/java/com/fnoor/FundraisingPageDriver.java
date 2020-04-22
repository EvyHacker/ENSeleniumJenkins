package com.fnoor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnoor.ETT.*;
import com.fnoor.FundraisingTest.*;
import com.fnoor.Redirects.*;
import com.fnoor.Standalone.*;
import com.fnoor.ValidationTests.MonerisVal;
import com.fnoor.ValidationTests.PaySafeVal;
import com.fnoor.ValidationTests.StripeVal;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static com.fnoor.PageFields.*;

public class FundraisingPageDriver {

    private static String FUNDRAISING_TEST;
    public static WebDriver driver;
    //PageFields fields = PageFactory.initElements(driver, PageFields.class);

    @BeforeClass(alwaysRun = true)
    public static void ensAuthTest() {
        HttpPost post = new HttpPost(SERVICE_URL + "/authenticate");
        post.setHeader("Content-Type", "application/json");
        try {

            // Test Account 09
            String body = "dae1e490-ddce-4be9-9225-8910b090d674";

            InputStream is = new ByteArrayInputStream(body.getBytes());
            InputStreamEntity inputStreamEntity;
            inputStreamEntity = new InputStreamEntity(is, body.getBytes().length);
            post.setEntity(inputStreamEntity);

            HttpResponse response = HttpClientBuilder.create().build().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status != HTTP_STATUS_OK) {
                throw new IOException("Unable to authenticate. Received invalid http status=" + status);
            }

            String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            System.out.println("RESPONSE as String: " + jsonResponse);

            // use jackson library to pull the string into json objects
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonResponse);
            ens_auth_token = node.get("ens-auth-token").asText();
            System.out.println("ens-auth-token: " + ens_auth_token);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true)
    public static void ensAuthTestVal() {
        HttpPost post = new HttpPost(SERVICE_URL + "/authenticate");
        post.setHeader("Content-Type", "application/json");
        try {

            String body = "0490bc76-8649-4494-babf-e3f285880a7e";
            InputStream is = new ByteArrayInputStream(body.getBytes());
            InputStreamEntity inputStreamEntity;
            inputStreamEntity = new InputStreamEntity(is, body.getBytes().length);
            post.setEntity(inputStreamEntity);

            HttpResponse response = HttpClientBuilder.create().build().execute(post);
            int status = response.getStatusLine().getStatusCode();
            if (status != HTTP_STATUS_OK) {
                throw new IOException("Unable to authenticate. Received invalid http status=" + status);
            }

            String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            System.out.println("RESPONSE as String: " + jsonResponse);

            // use jackson library to pull the string into json objects
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonResponse);
            ens_auth_token = node.get("ens-auth-token").asText();
            System.out.println("ens-auth-token: " + ens_auth_token);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmail(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterByEmail:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmail(testId);

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter?email=" + supporterEmail);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmail): " + jsonResponse);

        // use jackson library to pull the string into json objects
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonResponse);
        supporterId = node.get("supporterId").asText();
        supporterEmail = node.get("Email Address").asText();
        System.out.println("supporterId: " + supporterId);

        System.out.println("Status getSupporterByEmail: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);
    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterById(String testId, PageFields fields) throws IOException, InterruptedException {
        System.out.println("In after class getSupporterById:");
        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createEmail(testId);
        supporterTaxId = fields.getSupporterTaxID();

        HttpGet get = new HttpGet(SERVICE_URL + "/supporter/" + supporterId + "/transactions/" + supporterTaxId);

        System.out.println("url: " + get);
        get.setHeader("Content-Type", "application/json");
        get.setHeader("ens-auth-token", ens_auth_token);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterById): " + jsonResponse);

        System.out.println("Status getSupporterById: " + status);

    }

    @Parameters({"browser"})
    @BeforeTest
    public static WebDriver createInstance(String browser) {
        WebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "webdrivers/linux/chromedriver");
            driver = new ChromeDriver();
            return driver;
        }
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "webdrivers/linux/geckodriver");
            driver = new FirefoxDriver();
            return driver;
        }
        if (browser.equalsIgnoreCase("internet")) {
            driver = new InternetExplorerDriver();
            return driver;
        }

        return driver;
    }


    public static WebDriver getBrowser(String browser) {
        WebDriver driver;
        switch (browser) {
            case "firefox":
                return driver = new FirefoxDriver();
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "webdrivers/linux/chromedriver");
                return driver = new ChromeDriver();
            case "IE":
                System.setProperty("webdriver.gecko.driver", "webdrivers/linux/geckodriver");
                return driver = new FirefoxDriver();
            default:
                System.out.println("browser : " + browser + " is invalid, Launching Firefox as browser of choice..");
                return driver = new InternetExplorerDriver();
        }
    }
    public static WebDriver driverSettings() {

        System.setProperty("webdriver.chrome.driver", "webdrivers/linux/chromedriver");
        DesiredCapabilities capabilitiesChrome = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("--always-authorize-plugins");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--load-extension=\"+s+\"/stopper");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("pageLoadStrategy=normal");
        options.addArguments("--headless");
        capabilitiesChrome.setCapability(ChromeOptions.CAPABILITY, options);
        System.out.println("Im here");
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        return driver;
    }

}
