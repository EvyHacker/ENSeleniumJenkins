package com.fnoor;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageFields {

    WebDriver driver;

    public PageFields(WebDriver driver) {
        this.driver = driver;
    }

    //  API Test Details  //

    public static final String SERVICE_URL = "http://politicalnetworks.com/ens/service";
    public static final String SERVICE_BASEURL = "https://politicalnetworks.com";
    public static final String SERVICE_URL_ETT = "https://restmail.net/mail/";
    public static final Integer HTTP_STATUS_OK = 200;
    public static String ens_auth_token;
    public static String supporterEmail;
    public static String supporterId;
    public static String subjectETT;

    public static final String ENHome = "https://politicalnetworks.com/ea-account/index.jsp";
    public static final String ENLOGIN = "https://politicalnetworks.com/index.html#login";
    public static final String ENDASHBOARD = "https://politicalnetworks.com/index.html#dashboard";
    public static final String STRIPEDASHBOARD = "https://dashboard.stripe.com/login";
    public static final String MONERISDASHBOARD = "https://esqa.moneris.com/mpg/index.php";
    public static final String MONERISTRANSACTIONS = "https://esqa.moneris.com/mpg/reports/transaction/index.php";
    public static final String PAYSAFEDASHBOARD = "https://login.test.netbanx.com/office/public/preLogin.htm";

    public static final String USERNAME = "test_account_09@engagingnetworks.net";
    public static final String PASSWORD = "test_account_09";

    public static final String USERNAMEACC = "evy_testaccount01@engagingnetworks.net";
    public static final String PASSWORDACC = "evy579**1";

    public static final String USERNAMESTRIPE = "evy@engagingnetworks.net";
    public static final String PASSWORDSTRIPE = "EasyPass@1241";

    public static final String USERNAMEMONERIS = "demouser";
    public static final String STOREIDMONERIS = "store5";
    public static final String PASSWORDMONERIS = "password";

    public static final String PAYPALUSERNAME = "en_test_buyer@tellamazingstories.com";
    public static final String PAYPALPASSWORD = "Testing123**";

    public static final String PAYSAFEUSERNAME = "engagingnetrep";
    public static final String PAYSAFEPASSWORD = "Engage18!";

    //  Personal Details Fields //
    @FindBy(id = "en__field_supporter_firstName") WebElement field_Firstname;
    @FindBy(id = "en__field_supporter_lastName") WebElement field_Lastname;
    @FindBy(id = "en__field_supporter_title") WebElement field_Title;
    @FindBy(id = "en__field_supporter_emailAddress") WebElement field_EmailAddress;

    //	Address Fields //
    @FindBy(id = "en__field_supporter_address1") WebElement field_Address1;
    @FindBy(id = "en__field_supporter_address2") WebElement field_Address2;
    @FindBy(id = "en__field_supporter_city") WebElement field_City;
    @FindBy(id = "en__field_supporter_region") WebElement field_Region;
    @FindBy(id = "en__field_supporter_postcode") WebElement field_Postcode;
    @FindBy(id = "en__field_supporter_country") WebElement field_Country;

    //  Other Fields //
    @FindBy(id = "en__field_transaction_othamt1") WebElement field_OtherAmt1;
    @FindBy(id = "en__field_transaction_othamt2") WebElement field_OtherAmt2;
    @FindBy(id = "en__field_transaction_othamt3") WebElement field_OtherAmt3;
    @FindBy(id = "en__field_transaction_othamt4") WebElement field_OtherAmt4;
    @FindBy(id = "en__field_supporter_appealCode") WebElement field_Appealcode;
    @FindBy(id = "en__field_transaction_dirgift") WebElement field_Directgift;
    @FindBy(id = "en__field_transaction_comments") WebElement field_Addcomments;
    @FindBy(id = "en__field_transaction_taxdeductible") WebElement field_Taxdeductible;
    @FindBy(id = "en__field_supporter_phoneNumber") WebElement field_phonenum;

    //	Payment Fields //
    @FindBy(id = "en__field_transaction_ccnumber") WebElement field_CCNumber;
    @FindBy(id = "en__field_supporter_creditCardHolderName") WebElement field_CCName;
    @FindBy(id = "en__field_supporter_bankAccountType") WebElement field_BankAccType;
    @FindBy(id = "en__field_supporter_bankAccountNumber") WebElement field_BankAccNumber;
    @FindBy(id = "en__field_supporter_bankRoutingNumber") WebElement field_BankRoutingNumber;
    @FindBy(id = "en__field_transaction_donationAmt") WebElement field_DonationAmt;
    @FindBy(id = "en__field_transaction_paymenttype") WebElement field_PaymentType;
    @FindBy(id = "en__field_transaction_paycurrency") WebElement field_PaymentCurrency;
    @FindBys(value = @FindBy(name = "transaction.ccexpire")) List<WebElement> field_CCExpirySplit;
    @FindBy(id = "en__field_transaction_ccvv") WebElement field_CCV;
    @FindBy(css = "#en__field_transaction_recurrpay") WebElement field_RecurSinglePaychkbox;
    @FindBy(id = "en__field_transaction_recurrpay0") WebElement field_RecurPaychkbox;
    @FindBy(id = "en__field_transaction_recurrpay1") WebElement field_No_RecurPaychebox;
    @FindBy(id = "en__field_transaction_recurrday") WebElement field_RecurDay;
    @FindBy(id = "en__field_transaction_recurrstart")WebElement field_RecurStart;
    @FindBy(id = "en__field_transaction_recurrend") WebElement field_RecurEnd;
    @FindBy(id = "en__field_transaction_recurrfreq") WebElement field_RecurFreq;
    @FindBy(id = "en__field_transaction_recurrcnt") WebElement field_RecurCount;
    @FindBy(id = "en__field_transaction_recurprd") WebElement field_RecurPeriod;
    @FindBy(css = ".en__submit button") WebElement field_Submit;

    //   Supporter Transaction Details //
    @FindBy(id = "searchForm-q") WebElement field_SearchSupporter;
    @FindBy(className = "btn-go") WebElement field_SearchSupporterButton;
    @FindBy(className = "icon--search--color") WebElement field_SelectSupporter;
    @FindBy(css = ".gadget__transactionHistory__transaction__field.gadget__transactionHistory__transaction__field__name")
    List<WebElement> field_TransactionDetails;
    @FindBy(css = "gadget__singleDonations__transaction__row")
    List<WebElement> field_SupporterDetailsList;
    // @FindBy(className = "gadget__transactionHistory__transactionDetail__row__field") List<WebElement> txn_details;
    @FindBy(css = ".gadget__transactionHistory__transactionDetail") WebElement field_AllTransactionDetails;
    @FindBy(linkText = "Data & Reports") WebElement field_DataReports;

    //  Paypal login details
    @FindBy(name = "login_email") WebElement field_Paypal_loginemail;
    @FindBy(name = "login_password") WebElement field_Paypal_loginpassword;
    @FindBy(id = "btnNext") WebElement field_Paypal_Next;
    @FindBy(id = "btnLogin") WebElement field_Paypal_Login;
    @FindBy(xpath = "//a[@id='createAccount']") WebElement field_Paypal_CreateAcc;
    @FindBy(id = "confirmButtonTop") WebElement field_Paypal_Confirm;
    @FindBy(id = "paypalLogo") WebElement field_paypal_Logo;
    @FindBy(id = "createAccount") WebElement paypalCreateAccount;

    // Login Fields //
    @FindBy(id = "enLoginUsername") WebElement field_Username;
    @FindBy(id = "enLoginPassword") WebElement field_Password;
    @FindBy(xpath = "//button[@class='button button--login']") WebElement field_Submit1;

    //3D login
    @FindBy(id = "test-source-authorize-3ds") WebElement stripe3D_Complete;
    @FindBy(css = ".txnID") WebElement txn_id;

    //  Moneris login and dashboard details
    @FindBy(xpath = "/html/body/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/form/table/tbody/tr[4]/td[2]/input")
    WebElement field_Username_Moneris;
    @FindBy(xpath = "/html/body/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/form/table/tbody/tr[5]/td[2]/input")
    WebElement field_Store_Moneris;
    @FindBy(xpath = "/html/body/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td/form/table/tbody/tr[6]/td[2]/input")
    WebElement field_Password_Moneris;
    @FindBy(name = "do_login") WebElement field_Moneris_Submit;
    @FindBy(name = "forgot_pass") WebElement field_Moneris_ForgotPass;
    @FindBy(xpath = "//td[contains(text(),'ORDER ID')]")
    public List<WebElement> field_Order_ID;

    // PaySafe login and dashboard details
    @FindBy(id = "j_username") WebElement field_Username_Paysafe;
    @FindBy(id = "j_password") WebElement field_Password_Paysafe;
    @FindBy(id = "loginBtn") WebElement field_Login_Paysafe;
    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]") WebElement field_ForgotPass_Paysafe;
    @FindBy(id = "_menuReports") WebElement field_Reports_PaySafe;
    @FindBy(id = "btnSearch") WebElement field_GenerateSummaryPyaSafe;
    @FindBy(xpath = "//a[contains(text(),'Fully Settled')]") WebElement field_TransactionPaysafe;
    @FindBy(xpath = " //a[contains(text(),'Engagingnetworks')]") WebElement field_AccountPaysafe;
    @FindBy(id = "rowsPerPage") WebElement field_selectRowsPerPage;

    //   Stripe validation details   //
    @FindBy(id = "email") WebElement field_UsernameStripe;
    @FindBy(id = "password") WebElement field_PasswordStripe;
    @FindBy(css = ".button.blue") WebElement field_SubmitStripe;
    @FindBy(xpath = "//span[contains(text(),'Description')]") List<WebElement> field_StripeTransactions;
    @FindBy(css = ".Padding-all--8") WebElement field_StripeTransactionId;
    @FindBy(xpath = "//span[contains(text(),'Forgot your password?')]") WebElement field_ForgotPassword_Stripe;

    //	Ecommerce Symbolic Gift Fields  //
    @FindBy(css = ".en__ecfeature__link>a") WebElement field_featuredprod;
    @FindBy(css = ".en__component.en__component--ecfeature") WebElement field_featureblock;
    @FindBy(css = ".en__component.en__component--ecfeaturelist") WebElement field_featurelist;
    @FindBy(css = ".en__ecnav__list.en__ecnav__list--main li:nth-of-type(2)") WebElement field_nav_cart;
    @FindBy(css = ".en__ecnav__list.en__ecnav__list--main li:nth-of-type(1)") WebElement field_nav_home;
    @FindBy(css = ".en__ecnav__list.en__ecnav__list--main li:nth-of-type(3)") WebElement field_cart;
    @FindBy(css = ".en__productList") WebElement field_ecom_prodlist;
    @FindBy(name = "ec.product.quantity") WebElement field_ecom_prodquantity;
    @FindBy(css = "button[class='en__button']") WebElement field_ecom_addProd;
    @FindBy(css = ".en__component--eccheckout__emptyMessage>p") WebElement field_ecom_emptyCartMsg;
    @FindBy(css = ".en__button.en__component--eccheckout__continue") WebElement field_ecom_continueShopping;
    @FindBy(css = ".en__ecnav__cartCount>span") WebElement field_ecom_cartCount;
    @FindBy(css = ".en__component--eccheckout__submit.en__button") WebElement field_ecom_checkout;
    @FindBy(css = "#en__field__method00") WebElement field_ecom_deliverymethod;
    @FindBy(css = "#en__field__message00") WebElement fields_econ_personal_message;
    @FindBy(id = "en__field_transaction_inmem") WebElement field_InMemoriam;
    @FindBy(id = "en__field_transaction_honname") WebElement field_honoreeName;
    @FindBy(id = "en__field_transaction_infemail") WebElement field_informEmail;
    @FindBy(id = "en__field_transaction_infname") WebElement field_informName;

    //	Premium gift block fields
    @FindBy(css = ".en__pg__name") WebElement field_premgift_itemname;
    @FindBy(css = ".en__pgList") WebElement field_premgift_itemlist;

    //  Event Fields   //
    @FindBy(css = ".en__ticketBlock") WebElement field_event_block;
    @FindBy(css = ".en__ticket__field.en__ticket__field--info") WebElement field_event_tktinfo;
    @FindBy(css = ".en__ticket__field.en__ticket__field--cost") WebElement field_event_tktcost;
    //	@FindBy(css = ".en__ticket__field.en__ticket__field--quantity") WebElement field_event_tktquantity;
    @FindBy(css = ".en__ticket__quantity") WebElement field_event_tktquantity;
    @FindBy(css = ".en__ticket__minus") WebElement field_event_tktminus;
    @FindBy(css = ".en__ticket__plus") WebElement field_event_tktplus;
    @FindBy(css = ".en__additional__input") WebElement field_event_additionalamt;
    @FindBy(css = ".en__additional__promo") WebElement field_event_promo;
    @FindBy(css = ".en__ticketSummary__checkout") WebElement field_event_checkout;

    // Order summary details //
    @FindBy(css = ".en__orderSummary__data.en__orderSummary__data--cost") WebElement field_event_tktamt;
    @FindBy(css = ".en__orderSummary__data.en__orderSummary__data--totalAmount") WebElement field_event_additionaldonatn;
    @FindBy(css = ".en__orderSummary__data.en__orderSummary__data--totalAmount") WebElement field_event_totalamt;

    //  ETT and TWT Fields //
    @FindBy(css = ".en__contact__detail") WebElement field_Targetblock;
    @FindBy(css = ".en__contactMessage--typePlainText") WebElement field_Targetmessageblock;
    @FindBy(css = ".en__tweetContact") WebElement field_Tweetblock;
    @FindBy(css = ".en__tweetButton__send>a[href]") WebElement field_Tweetbutton;

    //  Ecard fields //
    @FindBy(css = ".en__ecarditems__thumb.thumb--active") WebElement field_ecard_thumbnail;
    @FindBy(css = ".en__ecardmessage__default") WebElement field_ecard_personal_msg;
    @FindBy(css = ".en__ecardrecipients__name>input") WebElement field_ecard_friendname;
    @FindBy(css = ".en__ecardrecipients__email>input") WebElement field_ecard_friendemail;
    @FindBy(css = ".en__ecarditems__button.en__ecarditems__addrecipient") WebElement field_ecard_addrecipient;
    @FindBy(css = "en__ecardrecipients__list") WebElement field_ecard_recipientlist;
    @FindBy(css = ".en__ecarditems__button.en__ecarditems__showprev") WebElement field_preview_ecard;
    @FindBy(css = ".en__ecarditems__prevclose") WebElement field_preview_ecard_close;

    //	Questions	//
    @FindBy(id = "en__field_supporter_questions_21190") WebElement field_question_1;
    @FindBy(css = ".en__field--21191") WebElement field_catsordogs;
    @FindBy(id = "en__field_supporter_questions_21192") WebElement field_dietarypref;

    //	Opt-ins	//
    @FindBy(id = "en__field_supporter_questions_1555") WebElement field_optin;
    @FindBy(name = "supporter.questions.29292") WebElement field_optin_radio;

    //   ETT   //
    @FindBy(css = ".en__contactDetail--title") List<WebElement>  field_ETT_Title;
    @FindBy(css = ".en__contactDetail--firstName") List<WebElement>  field_ETT_FirstName;
    @FindBy(css = ".en__contactDetail--lastName") List<WebElement>  field_ETT_LastName;
    @FindBy(css = ".en__contactDetail--organization") List<WebElement>  field_ETT_Organization;
    @FindBy(name = "contact.533.subject") WebElement field_ETT_MessageTitle;
    @FindBy(name = "contact.533.message") WebElement field_ETT_MessageBodySinglePage;
    @FindBy(name = "contact.534.message") WebElement field_ETT_MessageBody;
    @FindBy(name = "contact.11743.message") WebElement field_ETT_PostalMessageBody;
    @FindBy(name = "contact.7382.message") WebElement field_ETT_CongressMessageBody;
    @FindBy(css = ".en__contactMessage__plainText") WebElement field_ETT_MultiMessageBody;
    @FindBy(className = "en__contactMessage__plainTextOriginal") List<WebElement> field_ETT_MultiMessage;
    @FindBy(css = ".en__contact--closed:nth-child(2) > div.en__contact__toggle") WebElement field_ETT_Toggle;
    @FindBy(css = ".en__contact--open:nth-child(1) > div.en__contact__toggle") WebElement field_ETT_ToggleClose;
    @FindBy(css = ".en__contact--closed:nth-child(3) > div.en__contact__toggle") WebElement field_ETT_ToggleOpen;
    @FindBy(className = "en__contact.en__contact--14149.en__contact--open") WebElement field_ETT_Target1;
    @FindBy(css = ".en__contactSubject__field") WebElement field_ETT_MessageData;
    @FindBy(xpath = "//input[@name='contact.subject']") WebElement field_ETT_TargetSubject;
    @FindBy(className = "en__contactSections") WebElement field_ETT_TargetBlock;
    @FindBy(name = "supporter.questions.1555") WebElement field_ETT_OptIn;

    /////////////////////////    ETT FORMS AND FIELDS     /////////////////////////////////

    public void validateETTContactDetailsTitle(String text) {
            for (WebElement title : field_ETT_Title) {
                if (title.getText().equals(text)) {
                    Assert.assertTrue("Title is incorrect/not present", title.getText().contains(text));
                }
            }
    }

    public void validateETTContactDetailsFirstName(String text) {
        for (WebElement firstName : field_ETT_FirstName) {
            if (firstName.getText().equals(text)) {
                Assert.assertTrue("Title is incorrect/not present", firstName.getText().contains(text));
            }
        }
    }

    public void validateETTContactDetailsLastName(String text) {
        for (WebElement lastName : field_ETT_LastName) {
            if (lastName.getText().equals(text)) {
                Assert.assertTrue("Title is incorrect/not present", lastName.getText().contains(text));
            }
        }
    }

    public void validateETTContactDetailsOrganization(String text) {
        for (WebElement org : field_ETT_Organization) {
            if (org.getText().equals(text)) {
                Assert.assertTrue("Title is incorrect/not present", org.getText().contains(text));
            }
        }
    }

    public void validateETTTargetMessage(String text) {
        for (WebElement message : field_ETT_MultiMessage) {
            if (message.getAttribute("value").equals(text)) {
                Assert.assertTrue("Title is incorrect/not present", message.getAttribute("value").contains(text));
            }
        }
    }

    public void validateETTTargetHTMLSingleMessage(String text){
        driver.switchTo().frame(0);
        String targettext = driver.findElement(By.tagName("body")).getText();
        System.out.println(targettext);
        Assert.assertTrue("First Name incorrect/not present", targettext.contains(text));
        Assert.assertTrue("Message Body incorrect/not present", targettext.contains(text));
        Assert.assertTrue("Message default incorrect/not present", targettext.contains(text));
        driver.switchTo().defaultContent();
    }

    public void validateETTTargetHTMLDoubleMessage(String text){
        driver.switchTo().frame(1);
        String targettext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First Name incorrect/not present", targettext.contains(text));
        Assert.assertTrue("Message Body incorrect/not present", targettext.contains(text));
        Assert.assertTrue("Message default incorrect/not present", targettext.contains(text));
        driver.switchTo().defaultContent();
    }

    public void validateETTTargetHTMLTripleMessage(String text){
        driver.switchTo().frame(2);
        String targettext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First Name incorrect/not present", targettext.contains(text));
        Assert.assertTrue("Message Body incorrect/not present", targettext.contains(text));
        Assert.assertTrue("Message default incorrect/not present", targettext.contains(text));
        driver.switchTo().defaultContent();
    }

    public void setETTToggle(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", field_ETT_Toggle);
    }

    public void closeETTToggle(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", field_ETT_ToggleClose);
    }

    public void openETTToggle(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", field_ETT_ToggleOpen);
    }

    public void validateETTDoubleRotationMessage() {
        System.out.println("Subject: " + field_ETT_MessageData.getAttribute("value"));
        if (field_ETT_MessageData.getAttribute("value").contains("Subject  (Message No.1)")){
            //((JavascriptExecutor)driver).executeScript("location.reload()");
            Assert.assertTrue("Subject#1 incorrect/not present", field_ETT_MessageData.getAttribute("value")
                    .contains("Subject  (Message No.1): ETT_1  Custom target (single page) - 2 targets - plain text"));
            field_ETT_MessageData.sendKeys("(Test Message#1)");
        }else{
            Assert.assertTrue("Subject#2 incorrect/not present", field_ETT_MessageData.getAttribute("value")
                    .contains("Subject (Message No.2): ETT_1  Custom target (single page) - 2 targets - plain text"));
            field_ETT_MessageData.sendKeys("(Test Message#2)");
        }
    }

    public void validateETTTripleRotationMessage(){
        if(field_ETT_TargetSubject.getAttribute("value").contains("Subject (default): ")){
            WebElement editText = driver.findElement(By.xpath("//textarea[contains(text(),'Message (default - editable)')]"));
            editText.clear();
            editText.sendKeys("This is default page");
            WebElement uneditText = driver.findElement(By.xpath("//textarea[contains(text(),'Message (default - uneditable)')]"));
            Assert.assertTrue("You are on different page",
                    uneditText.getText().equals("Message (default - uneditable): ETT_8 Postal Database (single page)"));
        }if(field_ETT_TargetSubject.getAttribute("value").contains("Subject No.2: ")){
            WebElement editText = driver.findElement(By.xpath("//textarea[contains(text(),'Message (No.2 - editable)')]"));
            editText.clear();
            editText.sendKeys("This is page #2");
            WebElement uneditText = driver.findElement(By.xpath("//textarea[contains(text(),'Message (No.2 - uneditable)')]"));
            Assert.assertTrue("You are on different page",
                    uneditText.getText().equals("Message (No.2 - uneditable): ETT_8 Postal Database (single page)"));
        }if(field_ETT_TargetSubject.getAttribute("value").contains("Subject No.3: ")) {
            WebElement editText = driver.findElement(By.xpath("//textarea[contains(text(),'Message (No.3 - editable)')]"));
            editText.clear();
            editText.sendKeys("This is page #3");
            WebElement uneditText = driver.findElement(By.xpath("//textarea[contains(text(),'Message (No.3 - uneditable)')]"));
            Assert.assertTrue("You are on different page",
                    uneditText.getText().equals("Message (No.3 - uneditable): ETT_8 Postal Database (single page)"));
        }
    }

    public void validateETTMessageEditable(String text) {
        List<WebElement> message = driver.findElements(By.xpath("//textarea[contains(text(),'Message (default - ')]"));
        for (WebElement edit : message) {
            if (edit.getText().contains("(default - editable)")) {
                edit.clear();
                edit.sendKeys("(editable): This is Test");
            }
        }
        for (WebElement unEdit : message) {
            if (unEdit.getText().contains("(default - uneditable)")) {
                System.out.println("Uneditable: " + unEdit.getText());
                Assert.assertTrue("This is not an uneditable field",
                        unEdit.getText().equals(text));
            }
        }
    }

    public void validateTargetMessage(String text){
        System.out.println("Message: " + field_ETT_MessageBody.getText());
        Assert.assertTrue("Your target message is incorrect/not present", field_ETT_MessageBody.getText().contains(text));
    }

    public void validateTargetMultiMessage(String text){
        Assert.assertTrue("Your target message is incorrect/not present", field_ETT_MultiMessageBody.getText().contains(text));
    }

    public void validateTargetTitle(String text){
        Assert.assertTrue("Your target title is incorrect/not present", field_ETT_TargetSubject.getAttribute("value").contains(text));
    }


    /////////////////////////    LOGGINS     /////////////////////////////////

    public void enLogin() {

        field_Username.sendKeys(USERNAME);
        field_Password.sendKeys(PASSWORD);
        field_Submit1.click();
    }

    public void acLogin() {

        field_Username.sendKeys(USERNAMEACC);
        field_Password.sendKeys(PASSWORDACC);
        field_Submit1.click();
    }

    public void stripeLogin() {

        try {
            if (field_ForgotPassword_Stripe.isDisplayed()) {
                field_UsernameStripe.sendKeys(USERNAMESTRIPE);
                field_PasswordStripe.sendKeys(PASSWORDSTRIPE);
                field_SubmitStripe.submit();
            }
        } catch (NoSuchElementException e) {
        }
    }
    public void monerisLogin() {

        try {
            if (field_Moneris_ForgotPass.isDisplayed()) {
                field_Username_Moneris.sendKeys(USERNAMEMONERIS);
                field_Store_Moneris.sendKeys(STOREIDMONERIS);
                field_Password_Moneris.sendKeys(PASSWORDMONERIS);
                field_Moneris_Submit.click();
            }
        } catch (NoSuchElementException e) {
    }

}

    public void paySafeLogin() {

        try {
            if (field_ForgotPass_Paysafe.isDisplayed()) {
                field_Username_Paysafe.sendKeys(PAYSAFEUSERNAME);
                field_Password_Paysafe.sendKeys(PAYSAFEPASSWORD);
                field_Login_Paysafe.submit();
            }
        } catch (NoSuchElementException e) {

        }
    }

    ///////////////////////// SET PAYPAL LOGIN   /////////////////////////////////

    public void setPaypalEmail() {
        try{
            if(field_Paypal_CreateAcc.isDisplayed()){
                field_Paypal_loginemail.sendKeys(PAYPALUSERNAME);
            }
        } catch (NoSuchElementException e) {
    }
    }

    public void setPaypalPassword() {
        try{
            if(field_Paypal_CreateAcc.isDisplayed()){
        field_Paypal_loginpassword.sendKeys(PAYPALPASSWORD); }
        } catch (NoSuchElementException e) {
        }
    }

    public void submitPaypal() {
        try {
            if (field_Paypal_Next.isDisplayed()) {
                field_Paypal_Next.click();
            } else {
                field_Paypal_Login.click();
            }
        }catch (NoSuchElementException e) {
            }
    }

    public String getStripeTransactionDetails(){
        String transactionID = null;
        for (int i=0; i<1; i++){
            WebElement transID = field_StripeTransactions.get(i);
            transactionID = field_StripeTransactionId.getText();
        }
        return transactionID;
    }

    /////////////////////////    SUPPORTER DETAILS     /////////////////////////////////

    public void searchSupporter(String text, WebDriver driver) throws InterruptedException {
        field_DataReports.click();
        WebElement lookUpSupporter = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.linkText("Lookup supporter")));
        lookUpSupporter.click();
        WebElement searchSupporter = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.id("searchForm-q")));
        searchSupporter.sendKeys(text);
        field_SearchSupporterButton.submit();
    }

    public void selectSupporter(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", field_SelectSupporter);
    }

    public String getSupporterDetails() {
        StringBuilder supp_details = new StringBuilder();

        for (WebElement supp_det : field_SupporterDetailsList) {
            supp_details = supp_details.append(supp_det.getAttribute("value"));
        }
        return supp_details.toString();
    }

    public String getTransactionDetails() throws InterruptedException {
        /// click on the latest txn, get the txn details and return them
        String full_txn_text = null;

        for (int i = 0; i < 1; i++) {
            WebElement txn = field_TransactionDetails.get(i);
            txn.click();
            Thread.sleep(2000);
            full_txn_text = field_AllTransactionDetails.getText();

        }
        return full_txn_text;
    }


    /////////////////////////    MONERIS DASHBOARD REPORT SEARCH    /////////////////////////////////

    public void searchMonerisOrder (String text){
        WebElement Table = driver.findElement(By.id("maintable"));

        List<WebElement> Rows = Table.findElements(By.tagName("a"));

        for (WebElement orderId : Rows) {
            if (text.contains(orderId.getText())) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", orderId);
            }
        }
        for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
            driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
    }

    /////////////////////////    PAYSAFE DASHBOARD REPORT SEARCH    /////////////////////////////////

    public void reportSearchPaySafe(){
        field_Reports_PaySafe.click();
    }

    public void generateReportPaySafe(){
        field_GenerateSummaryPyaSafe.click();
    }

    public void authReportPaySafe(){
        field_TransactionPaysafe.click();
    }

    public void enReportSafe(){
        field_AccountPaysafe.click();
    }

    public void searchPaySafeOrder (String text) {
        Select rowsPerPage = new Select(field_selectRowsPerPage);
        rowsPerPage.selectByValue("50");
        WebElement Table = driver.findElement(By.xpath("//table[@class='rptTable']"));

        List<WebElement> Rows = Table.findElements(By.tagName("td"));
        List<WebElement> Cols = driver.findElements(By.tagName("tr"));
        int sizeOfRaws = Cols.size() - 12;

        for (WebElement orderId : Rows) {
            if (text.contains(orderId.getText())) {
                try{
                    WebElement link = (new WebDriverWait(driver, 20))
                            .until(ExpectedConditions.presenceOfElementLocated
                                    (By.xpath("/html/body/div[7]/div/table[3]/tbody/tr[" + sizeOfRaws + "]/td[2]/a")));
                    JavascriptExecutor executor1 = (JavascriptExecutor) driver;
                    executor1.executeScript("arguments[0].click();", link);
                    break;}
                catch (StaleElementReferenceException e){
                }
                for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
                    driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)
                }
            }
        }

    }

    /////////////////////////    STRIPE DASHBOARD REPORT SEARCH    /////////////////////////////////

    public void searchStripeOrder (String text)  {

        List<WebElement> Rows = driver.findElements(By.tagName("a"));

        for (WebElement orderId : Rows) {
            if (text.contains(orderId.getText())) {
                try{
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", orderId);
                break;}
                catch (StaleElementReferenceException e){
                }
            }
        }
        for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
            driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
    }


    /////////////////////////    SET FIELDS     /////////////////////////////////

    public String getFirstName() {
        if (field_Firstname.getText()!= null) {
            return field_Firstname.getAttribute("value");
        }
        return null;
    }

    public String getLastName() {
        if (field_Lastname.getText()!= null) {
            return field_Lastname.getAttribute("value");
        }
        return null;
    }

    public String getEmail() {
        if (field_EmailAddress.getText()!= null) {
            return field_EmailAddress.getAttribute("value");
        }
        return null;
    }

    public String getAddress1() {
        if (field_Address1.getText()!= null) {
            return field_Address1.getAttribute("value");
        }
        return null;
    }

    public String createEmail(String testID) {
        LocalDate date = LocalDate.now();
        String email = "PB_"+ testID + "_" + date.toString() + "@tellamazingstories.com";
        return email;
    }

    public String createETTEmail(String testID) {
        LocalDate date = LocalDate.now();
        String email = "ETT_"+ testID + "_" + date.toString() + "@restmail.net";
        return email;
    }

    public void setFirstname(String text) {
        if (field_Firstname.getText() != null){
            field_Firstname.clear();
            field_Firstname.sendKeys(text);
        }
        else {
            field_Firstname.sendKeys(text);
        }
    }

    public void setLastname(String text) {
        if (field_Lastname.getText() != null){
            field_Lastname.clear();
            field_Lastname.sendKeys(text);
        }
        else {
            field_Lastname.sendKeys(text);
        }
    }

    public void setEmailAddress(String text) {
        field_EmailAddress.sendKeys(text);
    }

    public void setAddress1(String text) {
        field_Address1.sendKeys(text);
    }

    public void setAddress2(String text) {
        field_Address2.sendKeys(text);
    }

    public void setPostCode(String text) {
        field_Postcode.sendKeys(text);
    }

    public void setCity(String text) {
        field_City.sendKeys(text);
    }

    public void setRegion(String text) {
        field_Region.sendKeys(text);
    }

    public void setCountry(String text) {
        field_Country.sendKeys(text);
    }

    public void clearAddress1() {
        field_Address1.clear();
    }

    public void clearCity() {
        field_City.clear();
    }

    public void clearPostCode() {
        field_Postcode.clear();
    }

    public void setPaymentType(String text) {
        if (field_PaymentType.getText() != null){
            field_PaymentType.clear();
            field_PaymentType.sendKeys(text);
        }
        else {
            field_PaymentType.sendKeys(text);
        }
    }

    public void setCCNUmber(String text) {
        field_CCNumber.sendKeys(text);
    }

    public void setCCName(String text) {
        field_CCName.clear();
        field_CCName.sendKeys(text);
    }

    // split text cc expiry
    public void setCCExpiry(CharSequence[] date_seq) {
        for (int i = 0; i< field_CCExpirySplit.size(); i++)
        {
            field_CCExpirySplit.get(i).sendKeys(date_seq[i]);
        }
    }

    public void setCCV(String text) {
        field_CCV.sendKeys(text);
    }

    public void setBankAccType(String text) {
        if (field_BankAccType.getText() != null){
            field_BankAccType.clear();
            field_BankAccType.sendKeys(text);
        }
        else {
            field_BankAccType.sendKeys(text);
        }
    }

    public void setBankAccNumber(String text) {
        if (field_BankAccNumber.getText() != null){
            field_BankAccNumber.clear();
            field_BankAccNumber.sendKeys(text);
        }
        else {
            field_BankAccNumber.sendKeys(text);
        }
    }

    public void setBankRoutingNumber(String text) {
        if (field_BankRoutingNumber.getText() != null){
            field_BankRoutingNumber.clear();
            field_BankRoutingNumber.sendKeys(text);
        }
        else {
            field_BankRoutingNumber.sendKeys(text);
        }
    }

    public void checkRecurringPaymentchkbox(WebDriver driver) {
        if ( !driver.findElement(By.id("en__field_transaction_recurrpay")).isSelected() )
        {
            driver.findElement(By.id("en__field_transaction_recurrpay")).click();
        }
    }

    public void setRecurDay(String text) {
        field_RecurDay.sendKeys(text);
    }

    public void setRecurStartDate(String text) {

        if (field_RecurStart.getText() != null) {
            field_RecurStart.clear();
            field_RecurStart.sendKeys(text);
        }
        else {
            field_RecurStart.sendKeys(text);
        }
    }

    public void setRecurEndDate(String text) {
        if (field_RecurEnd.getText() != null) {
            field_RecurEnd.clear();
            field_RecurEnd.sendKeys(text);
        }
        else {
            field_RecurEnd.sendKeys(text);
        }
    }

    public void setRecurFreq(String text) {
        if (field_RecurFreq.getText() != null) {
            field_RecurFreq.clear();
            field_RecurFreq.sendKeys(text);
        }
        else {
            field_RecurFreq.sendKeys(text);
        }

    }

    public void clickRecurringPaymentchkbox(){
        field_RecurPaychkbox.click();
    }
    public void clickRecurringSinglePaymentchkbox() {
        field_RecurSinglePaychkbox.click();
    }

    public void clickNoReccuringPaymentcheckbox(){
        field_No_RecurPaychebox.click();
    }

    public void setRecurCount(String text) {
        field_RecurCount.sendKeys(text);
    }

    public void setRecurPeriod(String text) {
        field_RecurPeriod.sendKeys(text);
    }

    public void setOtherAmt1(String text) {
        field_OtherAmt1.sendKeys(text);
    }

    public void setOtherAmt2(String text) {
        field_OtherAmt2.sendKeys(text);
    }

    public void setOtherAmt3(String text) {
        field_OtherAmt3.sendKeys(text);
    }

    public void setOtherAmt4(String text) {
        field_OtherAmt4.sendKeys(text);
    }

    public void setAppealCode(String text) {
        field_Appealcode.sendKeys(text);
    }

    public void setDirectMyGift(String text) {
        field_Directgift.sendKeys(text);
    }

    public void setAdditionalComments(String text) {
        field_Addcomments.sendKeys(text);
    }

    public void setTaxDeductible(String text) {
        field_Taxdeductible.sendKeys(text);
    }


    /////////////////////////    SELECT FROM DROPDOWN METHODS     /////////////////////////////////

    public void selectTitle(String text)
    {
        Select titledropdown = new Select(field_Title);
        titledropdown.selectByValue(text);
    }

    public void selectDonationAmt(String DonationAmt)
    {
        Select donationdropdown = new Select(field_DonationAmt);
        donationdropdown.selectByValue(DonationAmt);
    }

    public void selectCountry(String text)
    {
        Select countrydropdown = new Select(field_Country);
        countrydropdown.selectByValue(text);
    }

    public void selectRegion(String text)
    {
        Select regiondropdown = new Select(field_Region);
        regiondropdown.selectByValue(text);
    }

    public void selectPayCurrency(String text)
    {
        Select currencydropdown = new Select(field_PaymentCurrency);
        currencydropdown.selectByValue(text);
    }

    public void selectPaymentType(String text)
    {
        Select paymenttypedropdown = new Select(field_PaymentType);
        paymenttypedropdown.selectByValue(text);
    }

    public void selectBankAccType(String text)
    {
        Select bankacctypedropdown = new Select(field_BankAccType);
        bankacctypedropdown.selectByValue(text);
    }

    public void submit() throws InterruptedException {
//		field_Submit.click();
        field_Submit.submit();
        Thread.sleep(2000);
    }


    ///////////	      ECOMMERCE SYMBOLIC        /////////////

    public void buyFeaturedItem() {
        field_featuredprod.click();
    }

    public boolean verifyProductListShows() {
        return field_ecom_prodlist.isDisplayed();
    }

    public void clickHomeEcomm(){
        field_nav_home.click();
    }

    public void clickCartEcomm() {
        field_nav_cart.click();
    }

    public String verifyEmptyCartMsg() {
        return field_ecom_emptyCartMsg.getText();
    }

    public void continueShoppingEcomm() {
        field_ecom_continueShopping.click();
    }
    public void addProdEcomm() {
        field_ecom_addProd.click();
    }
    public String returnCartCountEcom() {
        return field_ecom_cartCount.getText();
    }
    public void ecommCheckout() {
        field_ecom_checkout.click();
        waitForPageLoad();
    }

    public void setEcommDeliveryMethod(String value) {
        Select dropdown = new Select(field_ecom_deliverymethod);
        dropdown.selectByValue(value);
    }

    public void senPesonalMessage(String text){
        fields_econ_personal_message.sendKeys(text);
    }


    public void setPhoneNum(String text) {
        field_phonenum.sendKeys(text);
    }

    ////////////           ECARD METHODS			////////////

    public void selectEcardDesign() {
        field_ecard_thumbnail.click();
    }

    public void addEcardMessage(String ecard_message) {
        field_ecard_personal_msg.sendKeys(ecard_message);
    }

    public void addEcardrecipient(String ecard_recipient) {
        field_ecard_friendname.sendKeys(ecard_recipient);
    }

    public void addEcardRecipientEmail(String ecard_recip_email) {
        field_ecard_friendemail.sendKeys(ecard_recip_email);
    }

    public void addEcardRecipienttoList() {
        field_ecard_addrecipient.click();
    }

    public void setInMemoriam(String text) {
        field_InMemoriam.sendKeys(text);
    }

    public void setHonoreeName(String text) {
        field_honoreeName.sendKeys(text);
    }

    public void setInformName(String text) {
        field_informName.sendKeys(text);
    }

    public void setInformEmail(String text) {
        field_informEmail.sendKeys(text);
    }

    public void previewEcard() {
        field_preview_ecard.click();
    }

    public void closeEcardPreview() {
//		driver.switchTo().frame(0);
        field_preview_ecard_close.click();
    }

    ///////////		SET QUESTIONS AND OPT-INS		////////////
    public void setquestion1( String question_text) {
        field_question_1.sendKeys(question_text);
    }

    public void question_radio_cat_dog(int i) {
        List<WebElement> Radio_button_catdog = driver.findElements(By.name("supporter.questions.21191"));
        System.out.println(Radio_button_catdog.size());
        Radio_button_catdog.get(i).click();
    }

    public void selectdiet(String text) {
        Select dietdropdown = new Select(field_dietarypref);
        dietdropdown.selectByValue(text);
    }

    public void setOptIn() {
        field_optin.click();
    }

    public void question_radio_optin(int i) {
        List<WebElement> Radio_button_optin = driver.findElements(By.name("supporter.questions.29292"));
        System.out.println(Radio_button_optin.size());
        Radio_button_optin.get(i).click();
    }

    ////////////			PREMIUM DONATION 	   //////////////

    public String getPremGiftName() {
        if (field_premgift_itemlist.isDisplayed()) {
            return field_premgift_itemname.getText();
        }
        return null;
    }

    ///////////	      EVENT  DETAILS        /////////////

    public void verifyEventblockPresent() {
        if(field_event_block.isDisplayed());{
            field_event_tktplus.click();
        }
    }

    public void addEventTkt() {
        field_event_tktplus.click();
    }

    public void removeEventTkt() {
        field_event_tktminus.click();
    }

    public String getTktQuantity() {
        String quantity = field_event_tktquantity.getAttribute("value");
        return quantity;
    }

    public void additionalAmtEvent(String addamt) {
        field_event_additionalamt.sendKeys(addamt);
    }

    public boolean VerifyEventOrderSummaryBlock() {
        String tktamt = field_event_tktamt.getText();
        tktamt = tktamt.substring(0, tktamt.length() - 7);
        System.out.println(tktamt);
        String adddonation = field_event_additionaldonatn.getText();
        adddonation = adddonation.substring(0, adddonation.length()-7);
        String totalamt = field_event_totalamt.getText();
        totalamt = totalamt.substring(0,totalamt.length()-7);
//		return (Integer.parseInt(totalamt) Integer.parseInt(tktamt)+Integer.parseInt(adddonation));
        System.out.println(Integer.valueOf(totalamt));
        return (Integer.valueOf(totalamt).equals(Integer.valueOf(tktamt)+Integer.valueOf(adddonation)));
    }

    public void eventCheckout() {
        field_event_checkout.click();
    }

    ///////////////////	     ETT  &  TWT  METHODS      //////////////////////

    public boolean verifyTargetblockIsPresent() {
        return field_Targetblock.isDisplayed();
    }

    public boolean verifyTWTblockIsPresent() {
        return field_Tweetblock.isDisplayed();
    }

    public void clickTweetButton() {
        field_Tweetbutton.click();
    }


    /////////////////////////    IF CONDITION    /////////////////////////////////

    public void isPaymentPresent(String text){
        if(field_PaymentType.getText() !=null){
            field_PaymentType.clear();
            field_PaymentType.sendKeys(text);
        }
    }

    public void isCountryFieldEmpty(String text){
        if(field_Country.getText() !=null){
            field_Country.clear();
            field_Country.sendKeys(text);
        }
    }
    public void waitForURLToChange(String expectedurl) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.urlContains(expectedurl));
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
//	public void waitForPageToLoad(WebDriver driver, String ele1) {
////		WebDriverWait wait = new WebDriverWait(driver, 45);
////		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hghg")));
////		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='lefty' AND @value='Submit']")));
////		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
////		WebDriverWait wd = new WebDriverWait(driver, 20);
//
////		new WebDriverWait(driver, 60)
////        .until((<? super WebDriver, V>) ExpectedConditions.visibilityOf(ele1));
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//			    .withTimeout(30, TimeUnit.SECONDS)
//			    .pollingEvery(5, TimeUnit.SECONDS)
//			    .ignoring(NoSuchElementException.class);
//
//			WebElement foo = wait.until(new Function<WebDriver, WebElement>()
//			{
//			  public WebElement apply(WebDriver driver) {
//			  return driver.findElement(By.cssSelector(ele1));
//			}
//			});
//	}

}
