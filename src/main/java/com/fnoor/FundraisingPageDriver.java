package com.fnoor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnoor.ETT.*;
import com.fnoor.FundraisingTest.*;
import com.fnoor.Redirects.PB_B1_PETDON_P1;
import com.fnoor.Redirects.PB_B2_PETDON_P2;
import com.fnoor.Redirects.PB_B3_INMEMDON;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.fnoor.PageFields.*;

public class FundraisingPageDriver {

    private static  String FUNDRAISING_TEST;
    static public String txID;
    protected IATS iats;
    static WebDriver driver;

@BeforeClass(alwaysRun = true)
    public static void ensAuthTest() {
        HttpPost post = new HttpPost(SERVICE_URL + "/authenticate");
        post.setHeader("Content-Type", "application/json");
        try {

            // Test Account 09
            String body = "dae1e490-ddce-4be9-9225-8910b090d674";
            // Evy's Test Account

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
            System.setProperty("webdriver.gecko.driver", webDriverProperty);
            //System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            driver = new FirefoxDriver();
        }else{
        System.setProperty("webdriver.chrome.driver", webDriverProperty);
            driver = new ChromeDriver();}

        PageFields fields = PageFactory.initElements(driver, PageFields.class);
        FundraisingPageHelper helper = new FundraisingPageHelper();

        switch (testCase) {

            case "IATS": {
                IATS.iatsSingle(FUNDRAISING_TEST="iatsSingle", fields, driver);
                IATS.IATSRecurring(FUNDRAISING_TEST="IATSRecurring", fields, driver);
                IATS.IATSACHRecurring(FUNDRAISING_TEST="IATSACHRecurring", fields, driver);
                IATS.IATSACHRecurPaymenttypelogic(FUNDRAISING_TEST="IATSACHRecurPaymenttypelogic", fields, driver);
                break;
            }
            case "F100": {//PB_F1
                IATS.iatsSingle(FUNDRAISING_TEST="iatsSingle", fields, driver);
            }
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
            case "WORLDPAY":{
                WORLDPAY.worldpayCCSingle(FUNDRAISING_TEST="WorldpayCCSingle", fields, driver);
                WORLDPAY.worldpayCCRecurring(FUNDRAISING_TEST="WorldpayCCRecurring", fields, driver);
                WORLDPAY.worldpay3DSecureTest(FUNDRAISING_TEST="worldpay3DSecureTest", fields, driver);
                WORLDPAY.worldpay3DRecurring(FUNDRAISING_TEST="worldpay3DRecurring", fields, driver);
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
            case "PAYPAL":{
                PAYPAL.paypalPaymentsProSingle(FUNDRAISING_TEST="paypalPaymentsProSingle", fields, driver);
                PAYPAL.payPalPaymentsProRecurring(FUNDRAISING_TEST="payPalPaymentsProRecurring", fields, driver);
                PAYPAL.payViaPayPalSingle(FUNDRAISING_TEST="payViaPayPalSingle", fields, driver);
                PAYPAL.payViaPayPalRecurring(FUNDRAISING_TEST="payViaPayPalRecurring", fields, driver);
                PAYPAL.paypalCardinalComSingle3D(FUNDRAISING_TEST="paypalCardinalComSingle3D", fields, driver);
                PAYPAL.paypalViaPayPalCardinalComSingle3D(FUNDRAISING_TEST="paypalViaPayPalCardinalComSingle3D", fields, driver);
                PAYPAL.paypalCardinalComRecurring3D(FUNDRAISING_TEST="paypalCardinalComRecurring3D", fields, driver);
                PAYPAL.stripeViaPaypalSingle(FUNDRAISING_TEST="stripeViaPaypalSingle", fields, driver);
                break;
            }
            case "F300":{//PB_F6
                PAYPAL.paypalPaymentsProSingle(FUNDRAISING_TEST="paypalPaymentsProSingle", fields, driver);
            }
            case "F301":{//PB_F7
                PAYPAL.payPalPaymentsProRecurring(FUNDRAISING_TEST="payPalPaymentsProRecurring", fields, driver);
                break;
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
            case "F307":{//PB_F44
                PAYPAL.stripeViaPaypalSingle(FUNDRAISING_TEST="stripeViaPaypalSingle", fields, driver);
                break;
            }
            case "MONERIS": {
                MONERIS.moneriseSelectSingle(FUNDRAISING_TEST = "moneriseSelectSingle", fields, driver);
                //MONERIS.moneriseSelectRecurring(FUNDRAISING_TEST="moneriseSelectRecurring", fields, driver);
                MONERIS.monerisVaultRecurring(FUNDRAISING_TEST = "monerisVaultRecurring", fields, driver);
                MONERIS.monerisSingleNoCvv(FUNDRAISING_TEST = "monerisSingleNoCvv", fields, driver);
                MONERIS.monerisRecurringNoCvv(FUNDRAISING_TEST = "monerisRecurringNoCvv", fields, driver);
                MONERIS.monerisVault3DSingle(FUNDRAISING_TEST = "monerisVault3DSingle", fields, driver);
                MONERIS.monerisVault3DRecurring(FUNDRAISING_TEST = "monerisVault3DRecurring", fields, driver);
                MONERIS.moneriseSelectSingleUP(FUNDRAISING_TEST="moneriseSelectSingleUP", fields, driver);
                break;
            }
            case "MonerisVal": {
                MonerisVal.moneriseSelectSingleVal(FUNDRAISING_TEST="moneriseSelectSingleVal", fields, driver);
                MonerisVal.moneriseSelectRecurringVal(FUNDRAISING_TEST="moneriseSelectRecurringVal", fields, driver);
                MonerisVal.moneriseSelectSingleVal3D(FUNDRAISING_TEST="moneriseSelectSingleVal3D", fields, driver);
                MonerisVal.moneriseSelectRecurring3DVal(FUNDRAISING_TEST="moneriseSelectRecurring3DVal", fields, driver);
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
                break;
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
            case "F407":{//PB_F60
                MONERIS.moneriseSelectSingleUP(FUNDRAISING_TEST="moneriseSelectSingleUP", fields, driver);
                break;
            }
            case "V400":{
                MonerisVal.moneriseSelectSingleVal(FUNDRAISING_TEST="moneriseSelectSingleVal", fields, driver);
            }
            case "V401":{
                MonerisVal.moneriseSelectRecurringVal(FUNDRAISING_TEST="moneriseSelectRecurringVal", fields, driver);
            }
            case "V402":{
                MonerisVal.moneriseSelectSingleVal3D(FUNDRAISING_TEST="moneriseSelectSingleVal3D", fields, driver);
            }
            case "V403":{
                MonerisVal.moneriseSelectRecurring3DVal(FUNDRAISING_TEST="moneriseSelectRecurring3DVal", fields, driver);
                break;
            }
            case "RSM":{
                RSM.rsmSingle(FUNDRAISING_TEST="rsmSingle", fields, driver);
                RSM.rsmRecurring(FUNDRAISING_TEST="rsmRecurring", fields, driver);
                RSM.rsmDirectDebit(FUNDRAISING_TEST="rsmDirectDebit", fields, driver);
                RSM.rsm3DSingle(FUNDRAISING_TEST="rsm3DSingle", fields, driver);
                RSM.rsm3DRecurring(FUNDRAISING_TEST="rsm3DRecurring", fields, driver);
                break;
            }
            case "F500":{//PB_F14
                RSM.rsmSingle(FUNDRAISING_TEST="rsmSingle", fields, driver);
            }
            case "F501":{//PB_F15
                RSM.rsmRecurring(FUNDRAISING_TEST="rsmRecurring", fields, driver);
            }
            case "F502":{//PB_F16
                RSM.rsmDirectDebit(FUNDRAISING_TEST="rsmDirectDebit", fields, driver);
            }
            case "F503":{//PB_F50
                RSM.rsm3DSingle(FUNDRAISING_TEST="rsm3DSingle", fields, driver);
            }
            case "F504":{//PB_F51
                RSM.rsm3DRecurring(FUNDRAISING_TEST="rsm3DRecurring", fields, driver);
                break;
            }
            case "VANTIV":{
                VANTIV.vantivSingle(FUNDRAISING_TEST="vantivSingle", fields, driver);
                VANTIV.vantivRecurring(FUNDRAISING_TEST="vantivRecurring", fields, driver);
                VANTIV.vantiveCheck(FUNDRAISING_TEST="vantiveCheck", fields, driver);
                VANTIV.vantivSingleAcheft(FUNDRAISING_TEST="vantivSingleAcheft", fields, driver);
                VANTIV.vantivRecurringAcheft(FUNDRAISING_TEST="vantivRecurringAcheft", fields, driver);
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
            case "PAYSAFE":{
                PAYSAFE.paysafeSingle(FUNDRAISING_TEST="paysafeSingle", fields, driver);
                PAYSAFE.paysafeRecurring(FUNDRAISING_TEST="paysafeRecurring", fields, driver);
                PAYSAFE.paysafe3DSingle(FUNDRAISING_TEST="paysafe3DSingle", fields, driver);
                PAYSAFE.paysafe3DRecurring(FUNDRAISING_TEST="paysafe3DRecurring", fields, driver);
                PaySafeVal.paySafeSelectSingleVal(FUNDRAISING_TEST="paySafeSelectSingleVal", fields, driver);
                PaySafeVal.paySafeSelectRecurringVal(FUNDRAISING_TEST="paySafeSelectRecurringVal", fields, driver);
                PaySafeVal.paySafeSelectSingle3DVal(FUNDRAISING_TEST="paySafeSelectSingle3DVal", fields, driver);
                PaySafeVal.paySafeSelectRecurring3DVal(FUNDRAISING_TEST="paySafeSelectRecurring3DVal", fields, driver);
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
                break;
            }
            case "V700":{
                PaySafeVal.paySafeSelectSingleVal(FUNDRAISING_TEST="paySafeSelectSingleVal", fields, driver);
            }
            case "V701":{
                PaySafeVal.paySafeSelectRecurringVal(FUNDRAISING_TEST="paySafeSelectRecurringVal", fields, driver);
            }
            case "V702":{
                PaySafeVal.paySafeSelectSingle3DVal(FUNDRAISING_TEST="paySafeSelectSingle3DVal", fields, driver);
            }
            case "V703":{
                PaySafeVal.paySafeSelectRecurring3DVal(FUNDRAISING_TEST="paySafeSelectRecurring3DVal", fields, driver);
                break;
            }
            case "PAYFLOW":{
                PAYFLOW.payflowProSingle(FUNDRAISING_TEST="payflowProSingle", fields, driver);
                PAYFLOW.payflowProRecurring(FUNDRAISING_TEST="payflowProRecurring", fields, driver);
                PAYFLOW.payflowProPayViaPaypal(FUNDRAISING_TEST="payflowProPayViaPaypal", fields, driver);
                break;
            }
            case "F800":{//PB_F32
                PAYFLOW.payflowProSingle(FUNDRAISING_TEST="payflowProSingle", fields, driver);
            }
            case "F801":{//PB_F33
                PAYFLOW.payflowProRecurring(FUNDRAISING_TEST="payflowProRecurring", fields, driver);
            }
            case "F802":{//PB_F34
                PAYFLOW.payflowProPayViaPaypal(FUNDRAISING_TEST="payflowProPayViaPaypal", fields, driver);
                break;
            }
            case "STRIPE": {
                STRIPE.stripeSingle(FUNDRAISING_TEST = "stripeSingle", fields, driver);
                STRIPE.stripeRecurring(FUNDRAISING_TEST = "stripeRecurring", fields, driver);
                STRIPE.stripeBancontactSingle(FUNDRAISING_TEST = "stripeBancontactSingle", fields, driver);
                STRIPE.stripeSingle3D(FUNDRAISING_TEST = "stripeSingle3D", fields, driver);
                STRIPE.stripeRecurring3D(FUNDRAISING_TEST = "stripeRecurring3D", fields, driver);
                StripeVal.stripeSingleVal(FUNDRAISING_TEST = "stripeSingleVal", fields, driver);
                StripeVal.stripeRecurringVal(FUNDRAISING_TEST = "stripeRecurringVal", fields, driver);
                StripeVal.stripeSingle3DVal(FUNDRAISING_TEST = "stripeSingle3DVal", fields, driver);
                StripeVal.stripeRecurring3DVal(FUNDRAISING_TEST = "stripeRecurring3DVal", fields, driver);
                break;
            }
            case "F900": {//PB_F40
                STRIPE.stripeSingle(FUNDRAISING_TEST = "stripeSingle", fields, driver);
            }
            case "F901": {//PB_F41
                STRIPE.stripeRecurring(FUNDRAISING_TEST = "stripeRecurring", fields, driver);
            }
            case "F902": {//PB_F42
                STRIPE.stripeBancontactSingle(FUNDRAISING_TEST = "stripeBancontactSingle", fields, driver);
            }
            case "F903": {//PB_F46
                STRIPE.stripeSingle3D(FUNDRAISING_TEST = "stripeSingle3D", fields, driver);
            }
            case "F904": {//PB_F47
                STRIPE.stripeRecurring3D(FUNDRAISING_TEST = "stripeRecurring3D", fields, driver);
                break;
            }
            case "V900": {
                StripeVal.stripeSingleVal(FUNDRAISING_TEST = "stripeSingleVal", fields, driver);
                break;
            }
            case "V901": {
                StripeVal.stripeRecurringVal(FUNDRAISING_TEST = "stripeRecurringVal", fields, driver);
                break;
            }
            case "V902": {
                StripeVal.stripeSingle3DVal(FUNDRAISING_TEST = "stripeSingle3DVal", fields, driver);
            }
            case "V903": {
                StripeVal.stripeRecurring3DVal(FUNDRAISING_TEST = "stripeRecurring3DVal", fields, driver);
                break;
            }
            case "ACIGateway":{
                ACI.aciSingleVisa(FUNDRAISING_TEST = "aciSingleVisa", fields, driver);
                ACI.aciSingleMasterCard(FUNDRAISING_TEST = "aciSingleMasterCard", fields, driver);
                ACI.aciSingleDiscover(FUNDRAISING_TEST = "aciSingleDiscover", fields, driver);
                ACI.aciSingleAmex(FUNDRAISING_TEST = "aciSingleAmex", fields, driver);
                ACI.aciSingleErrors(FUNDRAISING_TEST = "aciSingleErrors", fields, driver);
                ACI.aciRecurringVisa(FUNDRAISING_TEST = "aciRecurringVisa", fields, driver);
                ACI.aciRecurringMasterCard(FUNDRAISING_TEST = "aciRecurringMasterCard", fields, driver);
                ACI.aciRecurringDiscover(FUNDRAISING_TEST = "aciRecurringDiscover", fields, driver);
                ACI.aciRecurringAmex(FUNDRAISING_TEST = "aciRecurringAmex", fields, driver);
                ACI.aciRecurringACH(FUNDRAISING_TEST = "aciRecurringACH", fields, driver);
                ACI.aciSingleACH(FUNDRAISING_TEST = "aciSingleACH", fields, driver);
                break;
            }
            case "F1000": {//PB_F54
                ACI.aciSingleVisa(FUNDRAISING_TEST = "aciSingleVisa", fields, driver);
                break;
            }
            case "F1001": {//PB_F54
                ACI.aciSingleMasterCard(FUNDRAISING_TEST = "aciSingleMasterCard", fields, driver);
            }
            case "F1002": {//PB_F54
                ACI.aciSingleDiscover(FUNDRAISING_TEST = "aciSingleDiscover", fields, driver);
            }
            case "F1003": {//PB_F54
                ACI.aciSingleAmex(FUNDRAISING_TEST = "aciSingleAmex", fields, driver);
            }
            case "F1004": {//PB_F54
                ACI.aciSingleErrors(FUNDRAISING_TEST = "aciSingleErrors", fields, driver);
                break;
            }
            case "F1005": {//PB_F55
                ACI.aciRecurringVisa(FUNDRAISING_TEST = "aciRecurringVisa", fields, driver);
                break;
            }
            case "F1006": {//PB_F55
                ACI.aciRecurringMasterCard(FUNDRAISING_TEST = "aciRecurringMasterCard", fields, driver);
                break;
            }
            case "F1007": {//PB_F55
                ACI.aciRecurringDiscover(FUNDRAISING_TEST = "aciRecurringDiscover", fields, driver);
                break;
            }
            case "F1008": {//PB_F55
                ACI.aciRecurringAmex(FUNDRAISING_TEST = "aciRecurringAmex", fields, driver);
                break;
            }
            case "F1009": {//PB_F56
                ACI.aciRecurringACH(FUNDRAISING_TEST = "aciRecurringACH", fields, driver);
            }
            case "F1010": {//PB_F57
                ACI.aciSingleACH(FUNDRAISING_TEST = "aciSingleACH", fields, driver);
                break;
            }
            case "ACIGatewayMultiUsers":{
                ACIMultiUsers.aciSingleVisaMultiUsers(FUNDRAISING_TEST = "aciSingleVisaMultiUsers", fields, driver);
                ACIMultiUsers.aciSingleMasterCardMultiUsers(FUNDRAISING_TEST = "aciSingleMasterCardMultiUsers", fields, driver);
                ACIMultiUsers.aciRecurringVisaMultiUsers(FUNDRAISING_TEST = "aciRecurringVisaMultiUsers", fields, driver);
                ACIMultiUsers.aciRecurringMasterCardMultiUsers(FUNDRAISING_TEST = "aciRecurringMasterCardMultiUsers", fields, driver);
                break;
            }
            case "F1011": {
                ACIMultiUsers.aciSingleVisaMultiUsers(FUNDRAISING_TEST = "aciSingleVisaMultiUsers", fields, driver);
                break;
            }
            case "F1012": {
                ACIMultiUsers.aciSingleMasterCardMultiUsers(FUNDRAISING_TEST = "aciSingleMasterCardMultiUsers", fields, driver);
                break;
            }
            case "F1013": {
                ACIMultiUsers.aciRecurringVisaMultiUsers(FUNDRAISING_TEST = "aciRecurringVisaMultiUsers", fields, driver);
                break;
            }
            case "F1014": {
                ACIMultiUsers.aciRecurringMasterCardMultiUsers(FUNDRAISING_TEST = "aciRecurringMasterCardMultiUsers", fields, driver);
                break;
            }
            case "ETTCustom" :{
                CustomTarget.customTarget1(FUNDRAISING_TEST = "customTarget1", fields, driver);
                CustomTarget.customTarget2(FUNDRAISING_TEST = "customTarget2", fields, driver);
                CustomTarget.customTarget3(FUNDRAISING_TEST = "customTarget3", fields, driver);
                CustomTarget.customTarget4(FUNDRAISING_TEST = "customTarget4", fields, driver);
                CustomTarget.customTarget5(FUNDRAISING_TEST = "customTarget5", fields, driver);
                CustomTarget.customTarget6(FUNDRAISING_TEST = "customTarget6", fields, driver);
                break;
            }
            case "E1": {
                CustomTarget.customTarget1(FUNDRAISING_TEST = "customTarget1", fields, driver);
            }
            case "E2": {
                CustomTarget.customTarget2(FUNDRAISING_TEST = "customTarget2", fields, driver);
                break;
            }
            case "E3": {
                CustomTarget.customTarget3(FUNDRAISING_TEST = "customTarget3", fields, driver);
            }
            case "E4": {
                CustomTarget.customTarget4(FUNDRAISING_TEST = "customTarget4", fields, driver);
            }
            case "E5": {
                CustomTarget.customTarget5(FUNDRAISING_TEST = "customTarget5", fields, driver);
            }
            case "E6": {
                CustomTarget.customTarget6(FUNDRAISING_TEST = "customTarget6", fields, driver);
                break;
            }
            case "ETTPostal": {
                PostalDatabase.postalDatabase7(FUNDRAISING_TEST = "postalDatabase7", fields, driver);
                PostalDatabase.postalDatabase8(FUNDRAISING_TEST = "postalDatabase8", fields, driver);
                PostalDatabase.postalDatabase9(FUNDRAISING_TEST = "postalDatabase9", fields, driver);
                PostalDatabase.postalDatabase10(FUNDRAISING_TEST = "postalDatabase10", fields, driver);
                PostalDatabase.postalDatabase11(FUNDRAISING_TEST = "postalDatabase11", fields, driver);
                PostalDatabase.postalDatabase12(FUNDRAISING_TEST = "postalDatabase12", fields, driver);
                PostalDatabase.postalDatabase19(FUNDRAISING_TEST = "postalDatabase19", fields, driver);
                PostalDatabase.postalDatabase20(FUNDRAISING_TEST = "postalDatabase20", fields, driver);
                PostalDatabase.postalDatabase21(FUNDRAISING_TEST = "postalDatabase21", fields, driver);
                PostalDatabase.postalDatabase38(FUNDRAISING_TEST = "postalDatabase38", fields, driver);
                break;
            }
            case "E7": {
                PostalDatabase.postalDatabase7(FUNDRAISING_TEST = "postalDatabase7", fields, driver);
            }
            case "E8": {
                PostalDatabase.postalDatabase8(FUNDRAISING_TEST = "postalDatabase8", fields, driver);
            }
            case "E9": {
                PostalDatabase.postalDatabase9(FUNDRAISING_TEST = "postalDatabase9", fields, driver);
            }
            case "E10": {
                PostalDatabase.postalDatabase10(FUNDRAISING_TEST = "postalDatabase10", fields, driver);
            }
            case "E11": {
                PostalDatabase.postalDatabase11(FUNDRAISING_TEST = "postalDatabase11", fields, driver);
            }
            case "E12": {
                PostalDatabase.postalDatabase12(FUNDRAISING_TEST = "postalDatabase12", fields, driver);
            }
            case "E19": {
                PostalDatabase.postalDatabase19(FUNDRAISING_TEST = "postalDatabase19", fields, driver);
            }
            case "E20": {
                PostalDatabase.postalDatabase20(FUNDRAISING_TEST = "postalDatabase20", fields, driver);
            }
            case "E21": {
                PostalDatabase.postalDatabase21(FUNDRAISING_TEST = "postalDatabase21", fields, driver);
            }
            case "E38": {
                PostalDatabase.postalDatabase38(FUNDRAISING_TEST = "postalDatabase38", fields, driver);
                break;
            }
            case "ETTMulti": {
                MultiDatabase.multiDatabase13(FUNDRAISING_TEST = "multiDatabase13", fields, driver);
                MultiDatabase.multiDatabase14(FUNDRAISING_TEST = "multiDatabase14", fields, driver);
                MultiDatabase.multiDatabase15(FUNDRAISING_TEST = "multiDatabase15", fields, driver);
                MultiDatabase.multiDatabase16(FUNDRAISING_TEST = "multiDatabase16", fields, driver);
                MultiDatabase.multiDatabase16_2(FUNDRAISING_TEST = "multiDatabase16_2", fields, driver);
                MultiDatabase.multiDatabase17(FUNDRAISING_TEST = "multiDatabase17", fields, driver);
                MultiDatabase.multiDatabase18(FUNDRAISING_TEST = "multiDatabase18", fields, driver);
                MultiDatabase.multiDatabaseCommittees(FUNDRAISING_TEST = "multiDatabaseCommittees", fields, driver);
                MultiDatabase.multiDatabaseCommitteesEdit(FUNDRAISING_TEST = "multiDatabaseCommitteesEdit", fields, driver);
                MultiDatabase.multiDatabase39(FUNDRAISING_TEST = "multiDatabase39", fields, driver);
                MultiDatabase.multiDatabase39_2(FUNDRAISING_TEST = "multiDatabase39_2", fields, driver);
                break;
            }
            case "E13": {
                MultiDatabase.multiDatabase13(FUNDRAISING_TEST = "multiDatabase13", fields, driver);
            }
            case "E14": {
                MultiDatabase.multiDatabase14(FUNDRAISING_TEST = "multiDatabase14", fields, driver);
            }
            case "E15": {
                MultiDatabase.multiDatabase15(FUNDRAISING_TEST = "multiDatabase15", fields, driver);
            }
            case "E16": {
                MultiDatabase.multiDatabase16(FUNDRAISING_TEST = "multiDatabase16", fields, driver);
            }
            case "E16_2": {
                MultiDatabase.multiDatabase16_2(FUNDRAISING_TEST = "multiDatabase16_2", fields, driver);
            }
            case "E17": {
                MultiDatabase.multiDatabase17(FUNDRAISING_TEST = "multiDatabase17", fields, driver);
            }
            case "E18": {
                MultiDatabase.multiDatabase18(FUNDRAISING_TEST = "multiDatabase18", fields, driver);
            }
            case "E36": {
                MultiDatabase.multiDatabaseCommittees(FUNDRAISING_TEST = "multiDatabaseCommittees", fields, driver);
            }
            case "E37": {
                MultiDatabase.multiDatabaseCommitteesEdit(FUNDRAISING_TEST = "multiDatabaseCommitteesEdit", fields, driver);
            }
            case "E39": {
                MultiDatabase.multiDatabase39(FUNDRAISING_TEST = "multiDatabase39", fields, driver);
            }
            case "E39_2": {
                MultiDatabase.multiDatabase39_2(FUNDRAISING_TEST = "multiDatabase39_2", fields, driver);
                break;
            }
            case "ETTHouse": {
                HouseCommittees.houseCommmitteesHI(FUNDRAISING_TEST = "houseCommmitteesHI", fields, driver);
                HouseCommittees.houseCommmitteesOH(FUNDRAISING_TEST = "houseCommmitteesOH", fields, driver);
                HouseCommittees.houseCommmitteesCA(FUNDRAISING_TEST = "houseCommmitteesCA", fields, driver);
                HouseCommittees.houseCommmitteesDefense(FUNDRAISING_TEST = "houseCommmitteesDefense", fields, driver);
                HouseCommittees.houseCommmitteesCustTarget(FUNDRAISING_TEST = "houseCommmitteesCustTarget", fields, driver);
                break;
            }
            case "E31": {
                HouseCommittees.houseCommmitteesHI(FUNDRAISING_TEST = "houseCommmitteesHI", fields, driver);
            }
            case "E32": {
                HouseCommittees.houseCommmitteesOH(FUNDRAISING_TEST = "houseCommmitteesOH", fields, driver);
            }
            case "E33": {
                HouseCommittees.houseCommmitteesCA(FUNDRAISING_TEST = "houseCommmitteesCA", fields, driver);
            }
            case "E34": {
                HouseCommittees.houseCommmitteesDefense(FUNDRAISING_TEST = "houseCommmitteesDefense", fields, driver);
            }
            case "E35": {
                HouseCommittees.houseCommmitteesCustTarget(FUNDRAISING_TEST = "houseCommmitteesCustTarget", fields, driver);
                break;
            }
            case "ETTSenate":{
                SenateCommittees.senateCommmitteesAK(FUNDRAISING_TEST = "senateCommmitteesAK", fields, driver);
                SenateCommittees.senateCommmitteesMD(FUNDRAISING_TEST = "senateCommmitteesMD", fields, driver);
                SenateCommittees.senateCommmitteesBanking(FUNDRAISING_TEST = "senateCommmitteesBanking", fields, driver);
                SenateCommittees.senateCommmitteesFL(FUNDRAISING_TEST = "senateCommmitteesFL", fields, driver);
                SenateCommittees.senateCommmitteesCustTarget(FUNDRAISING_TEST = "senateCommmitteesCustTarget", fields, driver);
                break;
            }
            case "E26": {
                SenateCommittees.senateCommmitteesAK(FUNDRAISING_TEST = "senateCommmitteesAK", fields, driver);
            }
            case "E27": {
                SenateCommittees.senateCommmitteesMD(FUNDRAISING_TEST = "senateCommmitteesMD", fields, driver);
            }
            case "E28": {
                SenateCommittees.senateCommmitteesBanking(FUNDRAISING_TEST = "senateCommmitteesBanking", fields, driver);
            }
            case "E29": {
                SenateCommittees.senateCommmitteesFL(FUNDRAISING_TEST = "senateCommmitteesFL", fields, driver);
            }
            case "E30": {
                SenateCommittees.senateCommmitteesCustTarget(FUNDRAISING_TEST = "senateCommmitteesCustTarget", fields, driver);
            }
            case "Standalone": {
                PB_A1_PET.petition(FUNDRAISING_TEST = "petition", fields, driver);
                PB_A2_DCF.dataCapture(FUNDRAISING_TEST = "dataCapture", fields, driver);
                PB_A3_DCF2.dataCapture2(FUNDRAISING_TEST = "dataCapture2", fields, driver);
                PB_A4_ETT1.customTarget(FUNDRAISING_TEST = "customTarget", fields, driver);
                PB_A5_ETT2.singleDBETT(FUNDRAISING_TEST = "singleDBETT", fields, driver);
                PB_A8_EMS.signUp(FUNDRAISING_TEST = "signUp", fields, driver);
                PB_A9_ECF.ecardValidation(FUNDRAISING_TEST = "ecardValidation", fields, driver);
                PB_A10_TWT1.tweetCustomTarget(FUNDRAISING_TEST = "tweetCustomTarget", fields, driver);
                PB_A11_TWT2.singleDB(FUNDRAISING_TEST = "sungleDB", fields, driver);
                PB_A12_TWT3.multiDB(FUNDRAISING_TEST = "multiDB", fields, driver);
                PB_A13_ECS.event(FUNDRAISING_TEST= "event", fields, driver);
                PB_A15_PTM.premiumDonationSingle(FUNDRAISING_TEST = "premiumDonationSingle", fields, driver);
                PB_A16_ETM.ecommerceSymbolicGift(FUNDRAISING_TEST = "ecommerceSymbolicGift", fields, driver);
                break;
            }
            case "A1": {
                PB_A1_PET.petition(FUNDRAISING_TEST = "petition", fields, driver);
            }
            case "A2": {
                PB_A2_DCF.dataCapture(FUNDRAISING_TEST = "dataCapture", fields, driver);
            }
            case "A3": {
                PB_A3_DCF2.dataCapture2(FUNDRAISING_TEST = "dataCapture2", fields, driver);
            }
            case "A4": {
                PB_A4_ETT1.customTarget(FUNDRAISING_TEST = "customTarget", fields, driver);
            }
            case "A5": {
                PB_A5_ETT2.singleDBETT(FUNDRAISING_TEST = "singleDBETT", fields, driver);
            }
            case "A8": {
                PB_A8_EMS.signUp(FUNDRAISING_TEST = "signUp", fields, driver);
            }
            case "A9": {
                PB_A9_ECF.ecardValidation(FUNDRAISING_TEST = "ecardValidation", fields, driver);
            }
            case "A10": {
                PB_A10_TWT1.tweetCustomTarget(FUNDRAISING_TEST = "tweetCustomTarget", fields, driver);
            }
            case "A11": {
                PB_A11_TWT2.singleDB(FUNDRAISING_TEST = "singleDB", fields, driver);
                break;
            }
            case "A12": {
                PB_A12_TWT3.multiDB(FUNDRAISING_TEST = "multiDB", fields, driver);
            }
            case "A13": {
                PB_A13_ECS.event(FUNDRAISING_TEST = "event", fields, driver);
            }
            case "A15": {
                PB_A15_PTM.premiumDonationSingle(FUNDRAISING_TEST = "premiumDonationSingle", fields, driver);
            }
            case "A16": {
                PB_A16_ETM.ecommerceSymbolicGift(FUNDRAISING_TEST = "ecommerceSymbolicGift", fields, driver);
                break;
            }
            case "Redirects": {
                PB_B1_PETDON_P1.petition2DonationP1(FUNDRAISING_TEST = "petition2DonationP1", fields, driver);
                PB_B2_PETDON_P2.petition2DonationP2(FUNDRAISING_TEST = "petition2DonationP2", fields, driver);
                PB_B3_INMEMDON.inMemoriamDonation(FUNDRAISING_TEST = "inMemoriamDonation", fields, driver);
                break;
            }
            case "B1": {
                PB_B1_PETDON_P1.petition2DonationP1(FUNDRAISING_TEST = "petition2DonationP1", fields, driver);
            }
            case "B2": {
                PB_B2_PETDON_P2.petition2DonationP2(FUNDRAISING_TEST = "petition2DonationP2", fields, driver);
            }
            case "B3": {
                PB_B3_INMEMDON.inMemoriamDonation(FUNDRAISING_TEST = "inMemoriamDonation", fields, driver);
                break;
            }
            default: {
                throw new IllegalArgumentException("No test case found with id=" + testCase);
            }
        }

        driver.quit();
    }
}
