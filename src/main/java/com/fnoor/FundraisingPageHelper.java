package com.fnoor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnoor.FundraisingTest.*;
import com.fnoor.ValidationTests.MonerisVal;
import com.fnoor.ValidationTests.PaySafeVal;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.fnoor.PageFields.*;

public class FundraisingPageHelper extends FundraisingPageDriver {


    private static  String FUNDRAISING_TEST;

    @BeforeClass(alwaysRun = true)
    public static void ensAuthTest() {
        HttpPost post = new HttpPost(SERVICE_URL + "/authenticate");
        post.setHeader("Content-Type", "application/json");
        try {

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
        System.out.println("In after class");
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

        System.out.println("status: " + status);
        System.out.println("SupporterEmail: " + supporterEmail);

    }

    @AfterTest(alwaysRun = true)
    public static void getSupporterByEmailETT(String testId, PageFields fields) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        supporterEmail = fields.createETTEmail(testId);
        HttpGet get = new HttpGet(SERVICE_URL_ETT + supporterEmail);

        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        if (status != HTTP_STATUS_OK) {
            throw new IOException("Unable to authenticate. Received invalid http status=" + status);
        }
        String jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        System.out.println("RESPONSE as String(getSupporterByEmailETT): " + jsonResponse);

//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode node = mapper.readTree(jsonResponse);
//        subjectETT = node.get("subject").asText();
//        System.out.println("subjectETT: " + subjectETT);

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        if(args.length != 3) {
            System.out.println("ERROR: <environment> <webdriver> <TEST_CASE>");
            System.exit(0);
        }

        String env = args[0];
        String webdrive = args[1];
        String testCase = args[2];
        String testHelper = args[3];
        String webDriverProperty = String.format("webdrivers/%s/%s", env, webdrive);
        if (env.equals("win")) {
            webDriverProperty = webDriverProperty + ".exe";
        }

        System.setProperty("webdriver.gecko.driver", webDriverProperty);
        WebDriver driver = new FirefoxDriver();
        PageFields fields = PageFactory.initElements(driver, PageFields.class);

        switch (testCase) {
//            case "F100": {//PB_F1
//                IATS.iatsSingle(FUNDRAISING_TEST="iatsSingle", fields, driver);
//            }
            case "F101":{//PB_F2
                IATS.IATSRecurring(FUNDRAISING_TEST="IATSRecurring", fields, driver);
            }
            case "F102":{//PB_F29
                IATS.IATSACHRecurring(FUNDRAISING_TEST="IATSACHRecurring", fields, driver);
            }
            case "F103":{//PB_F30
                IATS.IATSACHRecurPaymenttypelogic(FUNDRAISING_TEST="IATSACHRecurPaymenttypelogic", fields, driver);
                break;
            }
            case "F200":{//PB_F3
                WORLDPAY.worldpayCCSingle(FUNDRAISING_TEST="WorldpayCCSingle", fields, driver);
            }
            case "F201":{//PB_F4
                WORLDPAY.worldpayCCRecurring(FUNDRAISING_TEST="WorldpayCCRecurring", fields, driver);
            }
            case "F202":{//PB_F5
                WORLDPAY.worldpay3DSecureTest(FUNDRAISING_TEST="worldpay3DSecureTest", fields, driver);
            }
            case "F203":{//PB_F31
                WORLDPAY.worldpay3DRecurring(FUNDRAISING_TEST="worldpay3DRecurring", fields, driver);
                break;
            }
            case "WORLDPAY":{
                WORLDPAY.worldpay3DSecureTest(FUNDRAISING_TEST="worldpay3DSecureTest", fields, driver);
                WORLDPAY.worldpay3DRecurring(FUNDRAISING_TEST="worldpay3DRecurring", fields, driver);
                break;
            }
            case "F300":{//PB_F6
                PAYPAL.paypalPaymentsProSingle(FUNDRAISING_TEST="paypalPaymentsProSingle", fields, driver);
            }
            case "F301":{//PB_F7
                PAYPAL.payPalPaymentsProRecurring(FUNDRAISING_TEST="payPalPaymentsProRecurring", fields, driver);
            }
            case "F302":{//PB_F8
                PAYPAL.payViaPayPalSingle(FUNDRAISING_TEST="payViaPayPalSingle", fields, driver);
            }
            case "F303":{//PB_F9
                PAYPAL.payViaPayPalRecurring(FUNDRAISING_TEST="payViaPayPalRecurring", fields, driver);
            }
            case "F304":{//PB_F43
                PAYPAL.paypalCardinalComSingle3D(FUNDRAISING_TEST="paypalCardinalComSingle3D", fields, driver);
            }
            case "F305":{//PB_F43
                PAYPAL.paypalViaPayPalCardinalComSingle3D(FUNDRAISING_TEST="paypalViaPayPalCardinalComSingle3D", fields, driver);
            }
            case "F306":{//PB_F43_01
                PAYPAL.paypalCardinalComRecurring3D(FUNDRAISING_TEST="paypalCardinalComRecurring3D", fields, driver);
            }
            case "F307":{//PB_F43_01
                PAYPAL.stripeViaPaypalSingle(FUNDRAISING_TEST="stripeViaPaypalSingle", fields, driver);
                break;
            }
            case "F400":{//PB_F10
                MONERIS.moneriseSelectSingle(FUNDRAISING_TEST="moneriseSelectSingle", fields, driver);
            }
//            case "F401":{//PB_F11
//                MONERIS.moneriseSelectRecurring(FUNDRAISING_TEST="moneriseSelectRecurring", fields, driver);
//                break;
//            }
            case "F402":{//PB_F13
                MONERIS.monerisVaultRecurring(FUNDRAISING_TEST="monerisVaultRecurring", fields, driver);
            }
            case "F403":{//PB_F36
                MONERIS.monerisSingleNoCvv(FUNDRAISING_TEST="monerisSingleNoCvv", fields, driver);
            }
            case "F404":{//PB_F37
                MONERIS.monerisRecurringNoCvv(FUNDRAISING_TEST="monerisRecurringNoCvv", fields, driver);
            }
            case "F405":{//PB_F48
                MONERIS.monerisVault3DSingle(FUNDRAISING_TEST="monerisVault3DSingle", fields, driver);
            }
            case "F406":{//PB_F49
                MONERIS.monerisVault3DRecurring(FUNDRAISING_TEST="monerisVault3DRecurring", fields, driver);
            }
            case "V407":{
                MonerisVal.moneriseSelectSingleVal(FUNDRAISING_TEST="moneriseSelectSingleVal", fields, driver);
            }
            case "V408":{
                MonerisVal.moneriseSelectRecurringVal(FUNDRAISING_TEST="moneriseSelectRecurringVal", fields, driver);
            }
            case "V409":{
                MonerisVal.moneriseSelectSingleVal3D(FUNDRAISING_TEST="moneriseSelectSingleVal3D", fields, driver);
            }
            case "V410":{
                MonerisVal.moneriseSelectRecurring3DVal(FUNDRAISING_TEST="moneriseSelectRecurring3DVal", fields, driver);
                break;
            }
            case "F500":{//PB_F14
                RSM.rsmSingle(FUNDRAISING_TEST="rsmSingle", fields, driver);
            }
            case "F501":{//PB_F15
                RSM.rsmRecurring(FUNDRAISING_TEST="rsmRecurring", fields, driver);
            }
            // Transaction has failed; email address throws an error
            case "F502":{//PB_F16
                RSM.rsmDirectDebit(FUNDRAISING_TEST="rsmDirectDebit", fields, driver);
                break;
            }
            case "F503":{//PB_F50
                RSM.rsm3DSingle(FUNDRAISING_TEST="rsm3DSingle", fields, driver);
                break;
            }
            // Transaction has failed; email address throws an error
            case "F504":{//PB_F51
                RSM.rsm3DRecurring(FUNDRAISING_TEST="rsm3DRecurring", fields, driver);
                break;
            }
            case "F600":{//PB_F17
                VANTIV.vantivSingle(FUNDRAISING_TEST="vantivSingle", fields, driver);
            }
            case "F601":{//PB_F18
                VANTIV.vantivRecurring(FUNDRAISING_TEST="vantivRecurring", fields, driver);
            }
            case "F602":{//PB_F19
                VANTIV.vantiveCheck(FUNDRAISING_TEST="vantiveCheck", fields, driver);
            }
            case "F603":{//PB_F45
                VANTIV.vantivSingleAcheft(FUNDRAISING_TEST="vantivSingleAcheft", fields, driver);
            }
            case "F604":{//PB_F45
                VANTIV.vantivRecurringAcheft(FUNDRAISING_TEST="vantivRecurringAcheft", fields, driver);
                break;
            }
            case "F700":{//PB_F20
                PAYSAFE.paysafeSingle(FUNDRAISING_TEST="paysafeSingle", fields, driver);
            }
            case "F701":{//PB_F21
                PAYSAFE.paysafeRecurring(FUNDRAISING_TEST="paysafeRecurring", fields, driver);
            }
            case "F702":{//PB_F52
                PAYSAFE.paysafe3DSingle(FUNDRAISING_TEST="paysafe3DSingle", fields, driver);
            }
            case "F703":{//PB_F53
                PAYSAFE.paysafe3DRecurring(FUNDRAISING_TEST="paysafe3DRecurring", fields, driver);
            }
            case "V704":{
                PaySafeVal.paySafeSelectSingleVal(FUNDRAISING_TEST="paySafeSelectSingleVal", fields, driver);
            }
            case "V705":{
                PaySafeVal.paySafeSelectRecurringVal(FUNDRAISING_TEST="paySafeSelectRecurringVal", fields, driver);
            }
            case "V706":{
                PaySafeVal.paySafeSelectSingle3DVal(FUNDRAISING_TEST="paySafeSelectSingle3DVal", fields, driver);
            }
            case "V707":{
                PaySafeVal.paySafeSelectRecurring3DVal(FUNDRAISING_TEST="paySafeSelectRecurring3DVal", fields, driver);
                break;
            }
            default: {
                throw new IllegalArgumentException("No test case found with id=" + testCase);
            }
        }

        driver.quit();
    }
}