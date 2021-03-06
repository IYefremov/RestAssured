package com.cyberiansoft.test.vnext.testcases.r360free.registrationandnavigation;

import com.cyberiansoft.test.baseutils.AppiumUtils;
import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.baseutils.WebDriverUtils;
import com.cyberiansoft.test.core.MobilePlatform;
import com.cyberiansoft.test.dataclasses.MatrixServiceData;
import com.cyberiansoft.test.driverutils.ChromeDriverProvider;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.driverutils.WebdriverInicializator;
import com.cyberiansoft.test.email.getnada.NadaEMailService;
import com.cyberiansoft.test.ibs.pageobjects.webpages.IBSLoginWebPage;
import com.cyberiansoft.test.vnext.enums.ScreenType;
import com.cyberiansoft.test.vnext.enums.VehicleDataField;
import com.cyberiansoft.test.vnext.factories.environments.EnvironmentType;
import com.cyberiansoft.test.vnext.interactions.HelpingScreenInteractions;
import com.cyberiansoft.test.vnext.interactions.VehicleInfoScreenInteractions;
import com.cyberiansoft.test.vnext.screens.*;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextAvailableServicesScreen;
import com.cyberiansoft.test.vnext.steps.*;
import com.cyberiansoft.test.vnext.steps.customers.CustomersScreenSteps;
import com.cyberiansoft.test.vnext.steps.services.AvailableServicesScreenSteps;
import com.cyberiansoft.test.vnext.steps.services.SelectedServicesScreenSteps;
import com.cyberiansoft.test.vnext.testcases.r360free.VNextBaseTestCase;
import com.cyberiansoft.test.vnext.utils.AppContexts;
import com.cyberiansoft.test.vnext.utils.VNextAlertMessages;
import com.cyberiansoft.test.vnext.utils.VNextAppUtils;
import com.cyberiansoft.test.vnext.utils.VNextWebServicesUtils;
import com.cyberiansoft.test.vnext.validations.SelectedServicesScreenValidations;
import com.cyberiansoft.test.vnextbo.screens.VNexBOLeftMenuPanel;
import com.cyberiansoft.test.vnextbo.screens.VNextBOApproveAccountWebPage;
import com.cyberiansoft.test.vnextbo.steps.login.VNextBOLoginSteps;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


//todo: This suite should be removed!
public class VNextUserRegistrationTestCases extends VNextBaseTestCase {


    private String userregmail = "test.cyberiansoft11@banit.me";
    final private String confirmpsw = "111111";

    final private String userregphone = "6267477899";
    final private String userphonecountrycode = "1";

    @BeforeClass(description = "Setting up new suite")
    public void settingUp() {
        AppiumUtils.setAndroidNetworkOn();
    }

    @AfterClass(description = "Setting up new suite")
    public void tearDown() {
        AppiumUtils.switchApplicationContext(AppContexts.NATIVE_CONTEXT);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().resetApp();
    }

    @BeforeMethod(description = "Setting up new suite")
    public void resetApk() throws IOException, UnirestException {

        EnvironmentType envType = EnvironmentType.QC;
        NadaEMailService nada = new NadaEMailService();
        nada.setEmailId(userregmail);
        nada.deleteAllMessages();
        VNextAppUtils.resetApp();
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);

        VNextEditionsScreen editionsScreen = new VNextEditionsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        VNextEnvironmentSelectionScreen environmentSelectionScreen = editionsScreen.selectEdition("Repair360");
        environmentSelectionScreen.selectEnvironment(envType);
        AppiumUtils.switchApplicationContext(AppContexts.NATIVE_CONTEXT);
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);
    }

    @Test(testName = "Test Case 44318:vNext: verify creating BO with JumpStart Edition (PDR)",
            description = "Verify creating BO with JumpStart Edition (PDR)")
    public void testVerifyCreatingBOWithJumpStartEdition_PDR() throws Exception {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String boeditionname = "Repair360 Free";
        final String bolineofbusiness = "PDR/Hail";
        final String userstate = "California";

        //userregmail = usermailprefix + UUID.randomUUID() + usermailpostbox;
        //userregmail = usermailprefix + "99999111" + usermailpostbox;

        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");

        regscreen.clickDoneButton();
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");
		
		/*BaseUtils.waitABit(2000);
		ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
		BaseUtils.waitABit(5000);*/
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        BaseUtils.waitABit(2000);
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(boeditionname, userstate);
        BaseUtils.waitABit(2000);
        newuserpersonalinfoscreen.clickDoneButton();

        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), userfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), userlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), boeditionname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();
		/*verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
		String regcode = VNextWebServicesUtils.getDeviceRegistrationCode(userregmail).replaceAll("\"", "");
		verificationscreen.setDeviceRegistrationCode(regcode);
		verificationscreen.clickVerifyButton();*/
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);
        NadaEMailService nada = new NadaEMailService();
        nada.setEmailId(userregmail);
        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
                new NadaEMailService.MailSearchParametersBuilder()
                        .withSubject("Repair360 Free: REGISTRATION");
        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
        nada.deleteMessageWithSubject("Repair360 Free: REGISTRATION");
        String linkText = "Click here";
        List<String> allMatches = nada.getUrlsFromMessage(mailmessage, linkText);

        String newbourl = allMatches.get(0).substring(0, allMatches.get(0).indexOf("\" style"));

        WebDriver
                webdriver = WebdriverInicializator.getInstance().initWebDriver(browsertype);
        WebDriverUtils.webdriverGotoWebPage(newbourl);
        VNextBOApproveAccountWebPage approvedaccountwebpage = PageFactory.initElements(
                webdriver, VNextBOApproveAccountWebPage.class);
        VNextBOLoginSteps.userLogin(userregmail, confirmpsw);
        VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
                VNexBOLeftMenuPanel.class);
        Assert.assertFalse(leftmenu.isUsersMenuItemExists());
        webdriver.quit();

    }

    @Test(testName = "Test Case 44329:vNext: verify creating BO with Technician Edition (PDR)",
            description = "Verify creating BO with Technician Edition (PDR)")
    public void testVerifyCreatingBOWithTechnicianEdition_PDR() throws Exception {

        final String newuserfirstname = "TestTech";
        final String newuserlastname = "User";
        final String newusercompanyname = "PDR";
        final String newuseraddress1 = "Address1";
        final String newuseraddress2 = "Address2";
        final String newusercity = "Lviv";
        final String newuserzip = "79000";
        final String newusercountry = "Canada";
        final String newuserstate = "Alberta";
        final String userpaymentname = newuserfirstname + " " + newuserlastname;
        final String usercardnumber = "4242424242424242";
        final String securitycode = "123";
        final String expmonth = "11";
        final String expyear = "2019";

        final String boeditionname = "Repair360";
        final String bolineofbusiness = "PDR/Hail";


        //userregmail = usermailprefix + "99999111" + usermailpostbox;
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(newuserfirstname, newuserlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");
        regscreen.clickDoneButton();
        BaseUtils.waitABit(3000);
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);

        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(newusercompanyname,
                newuseraddress1, newuseraddress2, newusercity, newuserzip, newusercountry, newuserstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(2000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPaymentInfoScreen registrationpaymentinfoscreen = new VNextRegistrationPaymentInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationpaymentinfoscreen.setUserPaiymentInfo(userpaymentname, usercardnumber,
                expmonth, expyear, securitycode);
        registrationpaymentinfoscreen.clickUseRegistrationAddress();

        Assert.assertEquals(registrationpaymentinfoscreen.getUserAddress1Value(), newuseraddress1);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserAddress2Value(), newuseraddress2);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserCityValue(), newusercity);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserZipValue(), newuserzip);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserCountryValue(), newusercountry);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserStateValue(), newuserstate);
        registrationpaymentinfoscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), newuserfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), newuserlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), newusercompanyname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        //registrationoverviewscreen.waitABit(10000);
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.agreePaymentTerms();
        BaseUtils.waitABit(1000);
        Assert.assertEquals(registrationoverviewlegalinfoscreen.getPaymentPriceValue(), "$ 60.00");
        registrationoverviewlegalinfoscreen.clickPayNowButton();


        NadaEMailService nada = new NadaEMailService();
        nada.setEmailId(userregmail);
        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
                new NadaEMailService.MailSearchParametersBuilder()
                        .withSubject("PDR: REGISTRATION");
        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);

        String linkText = "Click here";
        List<String> allMatches = nada.getUrlsFromMessage(mailmessage, linkText);


        String newbourl = allMatches.get(0).substring(0, allMatches.get(0).indexOf("\" style"));

        WebDriver
                webdriver = WebdriverInicializator.getInstance().initWebDriver(browsertype);
        WebDriverUtils.webdriverGotoWebPage(newbourl);
        VNextBOApproveAccountWebPage approvedaccountwebpage = PageFactory.initElements(
                webdriver, VNextBOApproveAccountWebPage.class);
        VNextBOLoginSteps.userLogin(userregmail, confirmpsw);
        VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
                VNexBOLeftMenuPanel.class);
        Assert.assertTrue(leftmenu.isUsersMenuItemExists());
        webdriver.quit();
    }

    @Test(testName = "Test Case 44272:vNext: verify 'Phone doesn't match this email. Email me my phone number' error for non-existing phone",
            description = "Verify 'Phone doesn't match this email. Email me my phone number' error for non-existing phone")
    public void testVerifyPhoneDoesntMatchThisEmailEmailMeMyPhoneNumberErrorForNonExistingPhone() throws Exception {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String boeditionname = "Repair360 Free";
        final String bolineofbusiness = "PDR/Hail";
        final String userstate = "California";

        //userregmail = usermailprefix + UUID.randomUUID() + usermailpostbox;
        //userregmail = usermailprefix + "99999111" + usermailpostbox;
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");

        regscreen.clickDoneButton();
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(boeditionname, userstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), userfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), userlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), boeditionname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();

        BaseUtils.waitABit(60 * 1000);
        VNextAppUtils.resetApp();
        BaseUtils.waitABit(10 * 1000);

        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);

        VNextEditionsScreen editionsScreen = new VNextEditionsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        VNextEnvironmentSelectionScreen environmentSelectionScreen = editionsScreen.selectEdition("Repair360");
        environmentSelectionScreen.selectEnvironment(envType);
        //BaseUtils.waitABit(15*1000);
        AppiumUtils.switchApplicationContext(AppContexts.NATIVE_CONTEXT);
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);
        regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setFirstName(userfirstname);
        regscreen.setLastName(userlastname);
        regscreen.setPhoneNumber("3182324555");
        regscreen.setEmail(userregmail);
        regscreen.clickDoneButton();
        VNextPhoneMismatchDialog phonemismatchdlg = new VNextPhoneMismatchDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertTrue(phonemismatchdlg.getInformationDialogBodyMessage().contains("Phone doesn't match this email"));
        phonemismatchdlg.clickEmailMeMyPhoneButton();


        NadaEMailService nada = new NadaEMailService();
        nada.setEmailId(userregmail);
        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
                new NadaEMailService.MailSearchParametersBuilder()
                        .withSubject("Phone Number Reminder");
        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
        //nada.deleteMessageWithSubject("Phone Number Reminder");

        String userphone = "";
        if (!mailmessage.equals(""))
            userphone = mailmessage.substring(mailmessage.indexOf("+") + 1, mailmessage.lastIndexOf(".<br>"));
        Assert.assertEquals(userphone, userphonecountrycode + userregphone);
    }

    @Test(testName = "Test Case 44273:vNext: verify 'Email doesn't match this phone' error for non-existing email",
            description = "Verify 'Email doesn't match this phone' error for non-existing email")
    public void testVerifyEmailDoesntMatchThisPhoneErrorForNonExistingEmail() throws IOException {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String boeditionname = "Repair360 Free";
        final String bolineofbusiness = "PDR/Hail";
        final String userstate = "California";

        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");

        regscreen.clickDoneButton();
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(boeditionname, userstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), userfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), userlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), boeditionname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();

        BaseUtils.waitABit(60 * 1000);
        VNextAppUtils.resetApp();
        BaseUtils.waitABit(10 * 1000);
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));

        VNextEditionsScreen editionsScreen = new VNextEditionsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        VNextEnvironmentSelectionScreen environmentSelectionScreen = editionsScreen.selectEdition("Repair360");
        environmentSelectionScreen.selectEnvironment(envType);
        //BaseUtils.waitABit(15*1000);
        AppiumUtils.switchApplicationContext(AppContexts.NATIVE_CONTEXT);
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);

        regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setFirstName(userfirstname);
        regscreen.setLastName(userlastname);
        regscreen.setPhoneNumber(userregphone);
        regscreen.setEmail("nonexistent@gmail.com");
        regscreen.clickDoneButton();
        VNextEmailMismatchDialog mailmismatchdlg = new VNextEmailMismatchDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertTrue(mailmismatchdlg.getInformationDialogBodyMessage().contains("Email doesn't match this phone"));
        mailmismatchdlg.clickTextEmailAddressButton();
    }

    @Test(testName = "Test Case 44316:vNext: verify 'Phone number or email address doesn't match the user's account information.' error for existing email_phone",
            description = "Verify 'Phone number or email address doesn't match the user's account information.' error for existing email_phone")
    public void testVerifyPhoneNumberOrEmailAddressDoesntMatchTheUsersAccountInformationErrorForExistingEmailPhone() throws IOException {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String boeditionname = "Repair360 Free";
        final String bolineofbusiness = "PDR/Hail";
        final String userstate = "California";

        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");

        regscreen.clickDoneButton();
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(boeditionname, userstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), userfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), userlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), boeditionname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();

        BaseUtils.waitABit(60 * 1000);
        VNextAppUtils.resetApp();
        BaseUtils.waitABit(10 * 1000);
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);
        VNextEditionsScreen editionsScreen = new VNextEditionsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        VNextEnvironmentSelectionScreen environmentSelectionScreen = editionsScreen.selectEdition("Repair360");
        environmentSelectionScreen.selectEnvironment(envType);
        AppiumUtils.switchApplicationContext(AppContexts.NATIVE_CONTEXT);
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setFirstName(userfirstname);
        regscreen.setLastName(userlastname);
        regscreen.setPhoneNumber("3182324584");
        regscreen.setEmail("mpstart@gmail.com");
        regscreen.clickDoneButton();
        VNextEmailMismatchDialog mailmismatchdlg = new VNextEmailMismatchDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertTrue(mailmismatchdlg.getInformationDialogBodyMessage().contains("Email doesn't match this phone"));
        mailmismatchdlg.clickTextEmailAddressButton();

        BaseUtils.waitABit(1000 * 15);
        regscreen.setEmail(userregmail);
        regscreen.setPhoneNumber("3127641152");
        regscreen.clickDoneButton();
        VNextPhoneMismatchDialog phonemismatchdialog = new VNextPhoneMismatchDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(phonemismatchdialog.getInformationDialogBodyMessage(), "Warning\nPhone doesn't match this email Email me my phone number");
        phonemismatchdialog.clickEmailMeMyPhoneButton();
    }

    @Test(testName = "Test Case 54272:vNext: User can't create password for IBS after creating password for vNext BO",
            description = "User can't create password for IBS after creating password for vNext BO")
    public void testUserCantCreatePasswordForIBSAfterCreatingPasswordForVNextBO() throws Exception {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String boeditionname = "Repair360 Free";
        final String bolineofbusiness = "PDR/Hail";
        final String userstate = "California";
        final String ibsStartSearchPhrase = "Please use your Repair360 login to access the <a href=";
        final String ibsEndSearchPhrase = " title=\"Client profile";

        //userregmail = usermailprefix + UUID.randomUUID() + usermailpostbox;
        //userregmail = usermailprefix + "99999111" + usermailpostbox;
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");

        regscreen.clickDoneButton();
        BaseUtils.waitABit(2000);
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(boeditionname, userstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), userfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), userlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), boeditionname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();
        AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);


        NadaEMailService nada = new NadaEMailService();
        nada.setEmailId(userregmail);
        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
                new NadaEMailService.MailSearchParametersBuilder()
                        .withSubject("Repair360 Free: REGISTRATION");
        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
        String linkText = "Click here";
        List<String> allMatches = nada.getUrlsFromMessage(mailmessage, linkText);

        String newbourl = allMatches.get(0).substring(0, allMatches.get(0).indexOf("\" style"));

        WebDriver
                webdriver = WebdriverInicializator.getInstance().initWebDriver(browsertype);
        WebDriverUtils.webdriverGotoWebPage(newbourl);
        VNextBOApproveAccountWebPage approvedaccountwebpage = PageFactory.initElements(
                webdriver, VNextBOApproveAccountWebPage.class);
        VNextBOLoginSteps.userLogin(userregmail, confirmpsw);
        VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
                VNexBOLeftMenuPanel.class);
        Assert.assertFalse(leftmenu.isUsersMenuItemExists());
        webdriver.quit();


        searchParametersBuilder =
                new NadaEMailService.MailSearchParametersBuilder()
                        .withSubject("Welcome to Client Portal");
        mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);


        String ibsurl = mailmessage.substring(mailmessage.indexOf(ibsStartSearchPhrase) + ibsStartSearchPhrase.length() + 1, mailmessage.indexOf(ibsEndSearchPhrase) - 1);

        webdriver = WebdriverInicializator.getInstance().initWebDriver(browsertype);
        WebDriverUtils.webdriverGotoWebPage(ibsurl);
        IBSLoginWebPage ibsloginpage = PageFactory.initElements(
                webdriver, IBSLoginWebPage.class);
        ibsloginpage.UserLogin(userregmail, confirmpsw);
        webdriver.quit();
    }


    @Test(testName = "Test Case 63558:Verify user can create Repair 360 edition,"
            + "Test Case 63590:Verify Repair360 user has ability to choose matrix type",
            description = "Verify user can create Repair 360 edition,"
                    + "Verify Repair360 user has ability to choose matrix type")
    public void testVerifyUserCanCreateRepair360Edition() throws IOException {

        final String newuserfirstname = "TestTech";
        final String newuserlastname = "User";
        final String newusercompanyname = "PDR";
        final String newuseraddress1 = "Address1";
        final String newuseraddress2 = "Address2";
        final String newusercity = "LA";
        final String newuserzip = "5214BA63";
        final String newusercountry = "United States of America";
        final String newusercountryselected = "United States";
        final String newuserstate = "California";
        final String userpaymentname = newuserfirstname + " " + newuserlastname;
        final String usercardnumber = "4242424242424242";
        final String securitycode = "123";
        final String expmonth = "11";
        final String expyear = "2019";

        final String boeditionname = "Repair360";
        final String bolineofbusiness = "PDR/Hail";

        final String firstname = "Eric";
        final String lastname = "Burn";
        final String customeraddress = "Stryis'ka, 223";
        final String customercity = "L'viv";
        final String customerzip = "79051";
        final String customeremail = "test.cyberiansoft+408222@gmail.com";

        final String testVIN = "1FMCU0DG4BK830800";
        final String matrixservice = "Hail Repair";
        final String[] availablepricematrixes = {"Nationwide Insurance", "Progressive", "State Farm"};
        final String vehiclepartname = "Hood";
        final String vehiclepartsize = "Dime";
        final String[] vehiclepartseverities = {"6-15", "Light 6 to 15", "Light 6 to 15"};

        //userregmail = usermailprefix + "99999111" + usermailpostbox;
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(newuserfirstname, newuserlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");
        regscreen.clickDoneButton();
        BaseUtils.waitABit(3000);
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(newusercompanyname,
                newuseraddress1, newuseraddress2, newusercity, newuserzip, newusercountry, newuserstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(2000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPaymentInfoScreen registrationpaymentinfoscreen = new VNextRegistrationPaymentInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationpaymentinfoscreen.setUserPaiymentInfo(userpaymentname, usercardnumber,
                expmonth, expyear, securitycode);
        registrationpaymentinfoscreen.clickUseRegistrationAddress();

        Assert.assertEquals(registrationpaymentinfoscreen.getUserAddress1Value(), newuseraddress1);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserAddress2Value(), newuseraddress2);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserCityValue(), newusercity);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserZipValue(), newuserzip);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserCountryValue(), newusercountryselected);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserStateValue(), newuserstate);
        registrationpaymentinfoscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), newuserfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), newuserlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), newusercompanyname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.agreePaymentTerms();
        Assert.assertEquals(registrationoverviewlegalinfoscreen.getPaymentPriceValue(), "$ 60.00");
        registrationoverviewlegalinfoscreen.clickPayNowButton();
        BaseUtils.waitABit(25000);

        VNextAppUtils.restartApp();

        WebDriverWait wait = new WebDriverWait(ChromeDriverProvider.INSTANCE.getMobileChromeDriver(), 240);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Data has been successfully downloaded']")));
        VNextInformationDialog informationdlg = new VNextInformationDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        informationdlg.clickInformationDialogOKButton();
        HomeScreenSteps.openCreateMyInspection();
        CustomersScreenSteps.clickAddCustomerButton();
        VNextNewCustomerScreen newCustomerScreen = new VNextNewCustomerScreen();
        newCustomerScreen.setCustomerFirstName(firstname);
        newCustomerScreen.setCustomerLastName(lastname);
        newCustomerScreen.setCustomerEmail(customeremail);
        newCustomerScreen.setCustomerAddress(customeraddress);
        newCustomerScreen.setCustomerCity(customercity);
        newCustomerScreen.setCustomerZIP(customerzip);
        newCustomerScreen.clickSaveCustomerButton();
        HelpingScreenInteractions.dismissHelpingScreenIfPresent();
        VehicleInfoScreenInteractions.setDataFiled(VehicleDataField.VIN, testVIN);
        WizardScreenSteps.navigateToWizardScreen(ScreenType.CLAIM);
        ClaimInfoSteps.selectInsuranceCompany("Test Insurance Company");
        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen();
        for (int i = 0; i < availablepricematrixes.length; i++) {
            MatrixServiceData matrixServiceData = new MatrixServiceData();
            AvailableServicesScreenSteps.selectMatrixService(matrixServiceData);
            VNextVehiclePartsScreen vehiclePartsScreen = new VNextVehiclePartsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
            vehiclePartsScreen.selectVehiclePart(vehiclepartname);
            VNextVehiclePartInfoScreen vehiclePartInfoPage = new VNextVehiclePartInfoScreen();
            vehiclePartInfoPage.selectVehiclePartSize(vehiclepartsize);
            vehiclePartInfoPage.selectVehiclePartSeverity(vehiclepartseverities[i]);
            vehiclePartInfoPage.clickSaveVehiclePartInfo();
            vehiclePartsScreen.clickVehiclePartsSaveButton();
            SelectedServicesScreenSteps.switchToSelectedService();
            SelectedServicesScreenValidations.validateMatrixServiceIsSelectedForService(matrixservice, availablepricematrixes[i]);
            AvailableServicesScreenSteps.switchToAvailableServices();
        }
        InspectionSteps.cancelInspection();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(testName = "Test Case 63612:Verify user can create Repair Free Edition, "
            + "Test Case 63613:Verify Repair 360 Free Edition user can select only one matrix type",
            description = "Verify user can create Repair Free Edition, "
                    + "Verify Repair 360 Free Edition user can select only one matrix type")
    public void testVerifyUserCanCreateRepair360FreeEdition() throws IOException {

        final String newuserfirstname = "TestTech";
        final String newuserlastname = "User";
        final String newusercompanyname = "PDR";
        final String newuseraddress1 = "Address1";
        final String newuseraddress2 = "Address2";
        final String newusercity = "LA";
        final String newuserzip = "5214BA63";
        final String newusercountry = "United States of America";
        final String newuserstate = "California";

        final String boeditionname = "Repair360 Free";
        final String bolineofbusiness = "PDR/Hail";

        final String firstname = "Eric";
        final String lastname = "Burn";
        final String customeraddress = "Stryis'ka, 223";
        final String customercity = "L'viv";
        final String customerzip = "79051";
        final String customeremail = "test.cyberiansoft+408222@gmail.com";

        final String testVIN = "1FMCU0DG4BK830800";
        final String matrixservice = "Hail Repair";
        final String availablepricematrix = "State Farm";


        //userregmail = usermailprefix + "99999111" + usermailpostbox;
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(newuserfirstname, newuserlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");
        regscreen.clickDoneButton();
        BaseUtils.waitABit(3000);
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(newusercompanyname,
                newuseraddress1, newuseraddress2, newusercity, newuserzip, newusercountry, newuserstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();

        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), newuserfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), newuserlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), newusercompanyname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        Assert.assertEquals(registrationoverviewscreen.getAddress1Value(), newuseraddress1);
        Assert.assertEquals(registrationoverviewscreen.getAddress2Value(), newuseraddress2);
        Assert.assertEquals(registrationoverviewscreen.getCityValue(), newusercity);
        Assert.assertEquals(registrationoverviewscreen.getZipValue(), newuserzip);
        Assert.assertEquals(registrationoverviewscreen.getStateValue(), newuserstate);
        Assert.assertEquals(registrationoverviewscreen.getCountryValue(), newusercountry);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();

        BaseUtils.waitABit(10000);
        VNextAppUtils.restartApp();
        WebDriverWait wait = new WebDriverWait(ChromeDriverProvider.INSTANCE.getMobileChromeDriver(), 90);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Data has been successfully downloaded']")));
        VNextInformationDialog informationdlg = new VNextInformationDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        informationdlg.clickInformationDialogOKButton();
        VNextHomeScreen homescreen = new VNextHomeScreen();
        Assert.assertTrue(homescreen.isUpgrateToProBannerVisible());


        //todo: Test Case 63613:Verify Repair 360 Free Edition user can select only one matrix type not valid anymore
		/*VNextInspectionsScreen inspectionsscreen = homescreen.clickInspectionsMenuItem();
		VNextCustomersScreen customersScreen = inspectionsscreen.clickAddInspectionButton();
		VNextNewCustomerScreen newCustomerScreen = customersScreen.clickAddCustomerButton();
		newCustomerScreen.setCustomerFirstName(firstname);
		newCustomerScreen.setCustomerLastName(lastname);
		newCustomerScreen.setCustomerEmail(customeremail);
		newCustomerScreen.setCustomerAddress(customeraddress);
		newCustomerScreen.setCustomerCity(customercity);
		newCustomerScreen.setCustomerZIP(customerzip);
		newCustomerScreen.clickSaveCustomerButton();
		VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen();
		GeneralSteps.dismissHelpingScreenIfPresent();
		VehicleInfoScreenInteractions.setVin(testVIN);
		vehicleinfoscreen.swipeScreenLeft();
		VNextClaimInfoScreen claimInfoScreen = new VNextClaimInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
		claimInfoScreen.selectInsuranceCompany("Test Insurance Company");		
		vehicleinfoscreen.swipeScreensLeft(2);			
		VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
		availableServicesScreen.selectMatrixService(matrixservice);
		VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
		Assert.assertEquals(selectedServicesScreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), availablepricematrix);

		Assert.assertTrue(selectedServicesScreen.isServiceSelected(matrixservice));
		Assert.assertEquals(selectedServicesScreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), availablepricematrix);
		
		inspectionsscreen = selectedServicesScreen.cancelInspection();
		inspectionsscreen.clickBackButton();*/
    }

    @Test(testName = "Test Case 64228:R360: Submit Customer Feedback from Repair360 Free ediition",
            description = "Submit Customer Feedback from Repair360 Free ediition")
    public void testSubmitCustomerFeedbackFromRepair360FreeEdiition() throws Exception {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String boeditionname = "Repair360 Free";
        final String bolineofbusiness = "PDR/Hail";
        final String userstate = "California";

        final String feedbackType = "Feature Request";
        final String feedbackArea = "Customers";
        final String subArea = "Create/Edit customer";
        final String feedbackSubject = "Test Feedback Repair360";
        final String feedbackDesc = "Testing kayako ticket creation from feedback send from Repair360 Free";


        //userregmail = usermailprefix + UUID.randomUUID() + usermailpostbox;
        //userregmail = usermailprefix + "99999111" + usermailpostbox;
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");

        regscreen.clickDoneButton();
        BaseUtils.waitABit(4000);
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(boeditionname, userstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        //reglineofbusinessscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), userfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), userlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), boeditionname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();

        BaseUtils.waitABit(15000);
        VNextAppUtils.restartApp();
        WebDriverWait wait = new WebDriverWait(ChromeDriverProvider.INSTANCE.getMobileChromeDriver(), 90);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Data has been successfully downloaded']")));
        VNextInformationDialog informationdlg = new VNextInformationDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        informationdlg.clickInformationDialogOKButton();
        HomeScreenSteps.openStatus();
        StatusScreenSteps.openFeedBackScreen();
        VNextFeedbackScreen feedbackscreen = new VNextFeedbackScreen();
        feedbackscreen.selectFeedbackType(feedbackType);
        feedbackscreen.selectArea(feedbackArea, subArea);
        feedbackscreen.setFeedbackSubject(feedbackSubject);
        feedbackscreen.setFeedbackDescription(feedbackDesc);
        feedbackscreen.clickSendButton();

        NadaEMailService nada = new NadaEMailService();
        nada.setEmailId(userregmail);
        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
                new NadaEMailService.MailSearchParametersBuilder()
                        .withSubject("Test Feedback Repair360");
        Assert.assertTrue(nada.waitForMessage(searchParametersBuilder));

    }

    @Test(testName = "Test Case 64408:R360: submit Customer Feedback from Repair360 ediition",
            description = "Submit Customer Feedback from Repair360 ediition")
    public void testSubmitCustomerFeedbackFromRepair360Ediition() throws IOException {

        final String newuserfirstname = "TestTech";
        final String newuserlastname = "User";
        final String newusercompanyname = "PDR";
        final String newuseraddress1 = "Address1";
        final String newuseraddress2 = "Address2";
        final String newusercity = "Lviv";
        final String newuserzip = "79000";
        final String newusercountry = "Canada";
        final String newuserstate = "Alberta";
        final String userpaymentname = newuserfirstname + " " + newuserlastname;
        final String usercardnumber = "4242424242424242";
        final String securitycode = "123";
        final String expmonth = "11";
        final String expyear = "2019";

        final String boeditionname = "Repair360";
        final String bolineofbusiness = "PDR";

        final String feedbackType = "Feature Request";
        final String feedbackArea = "Customers";
        final String subArea = "Create/Edit customer";
        final String feedbackSubject = "Test Feedback Repair360";
        final String feedbackDesc = "Testing kayako ticket creation from feedback send from Repair360 Free";

        //userregmail = usermailprefix + "99999111" + usermailpostbox;
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(newuserfirstname, newuserlastname, userphonecountrycode, userregphone, userregmail);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");
        regscreen.clickDoneButton();
        BaseUtils.waitABit(3000);
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();
        //registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        //Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "Your phone has been verified");

        BaseUtils.waitABit(2000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.setNewUserPersonaInfo(newusercompanyname,
                newuseraddress1, newuseraddress2, newusercity, newuserzip, newusercountry, newuserstate);
        newuserpersonalinfoscreen.clickDoneButton();
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        reglineofbusinessscreen.selectEdition(boeditionname);
        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);
        reglineofbusinessscreen.clickDoneButton();
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(2000);
        //ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().frame(ChromeDriverProvider.INSTANCE.getMobileChromeDriver().findElement(By.xpath("//iframe")));
        VNextRegistrationPaymentInfoScreen registrationpaymentinfoscreen = new VNextRegistrationPaymentInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationpaymentinfoscreen.setUserPaiymentInfo(userpaymentname, usercardnumber,
                expmonth, expyear, securitycode);
        registrationpaymentinfoscreen.clickUseRegistrationAddress();

        Assert.assertEquals(registrationpaymentinfoscreen.getUserAddress1Value(), newuseraddress1);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserAddress2Value(), newuseraddress2);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserCityValue(), newusercity);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserZipValue(), newuserzip);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserCountryValue(), newusercountry);
        Assert.assertEquals(registrationpaymentinfoscreen.getUserStateValue(), newuserstate);
        registrationpaymentinfoscreen.clickDoneButton();
        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), newuserfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), newuserlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), newusercompanyname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        //Assert.assertEquals(registrationoverviewscreen.getUserPhoneValue(), userregphoneformatted);
        registrationoverviewscreen.clickDoneButton();
        //registrationoverviewscreen.waitABit(10000);
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.agreePaymentTerms();
        Assert.assertEquals(registrationoverviewlegalinfoscreen.getPaymentPriceValue(), "$ 19.00");
        registrationoverviewlegalinfoscreen.clickPayNowButton();

        BaseUtils.waitABit(10000);
        VNextAppUtils.restartApp();

        WebDriverWait wait = new WebDriverWait(ChromeDriverProvider.INSTANCE.getMobileChromeDriver(), 90);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Data has been successfully downloaded']")));
        VNextInformationDialog informationdlg = new VNextInformationDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        informationdlg.clickInformationDialogOKButton();
        HomeScreenSteps.openStatus();
        StatusScreenSteps.openFeedBackScreen();
        VNextFeedbackScreen feedbackscreen = new VNextFeedbackScreen();
        feedbackscreen.selectFeedbackType(feedbackType);
        feedbackscreen.selectArea(feedbackArea, subArea);
        feedbackscreen.setFeedbackSubject(feedbackSubject);
        feedbackscreen.setFeedbackDescription(feedbackDesc);
        feedbackscreen.clickSendButton();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(testName = "Test Case 83887:Repair360 - Key verification (invalid key)," +
            "Test Case 83891:Repair360 - Key verification (empty), Test Case 83893:Repair360 - Validate verification code is not cleared after validation",
            description = "Key verification (invalid key), Key verification (empty)," +
                    "Validate verification code is not cleared after validation")
    public void testKeyVerificationInvalidKey() {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String wrongRegCode = "QAQA";
        final String emptyString = "";

        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");

        regscreen.clickDoneButton();
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(emptyString);
        verificationscreen.clickVerifyButton();
        Assert.assertTrue(verificationscreen.isRegCodeErrorMessageVisible());
        Assert.assertEquals(verificationscreen.getRegCodeErrorMessage(), VNextAlertMessages.VERIFICATION_CODE_IS_REQUIRED);

        verificationscreen.setDeviceRegistrationCode(wrongRegCode);
        verificationscreen.clickVerifyButton();

        VNextModalDialog modalDialog = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(modalDialog.clickInformationDialogOKButtonAndGetMessage(),
                VNextAlertMessages.VERIFICATION_CODE_IS_NOT_VALID);
        Assert.assertEquals(verificationscreen.getEnteredDeviceRegistrationCodeValue(), wrongRegCode);
        verificationscreen.setDeviceRegistrationCode(emptyString);
        verificationscreen.clickVerifyButton();
        Assert.assertTrue(verificationscreen.isRegCodeErrorMessageVisible());
        Assert.assertEquals(verificationscreen.getRegCodeErrorMessage(), VNextAlertMessages.VERIFICATION_CODE_IS_REQUIRED);

    }

    @Test(testName = "Test Case 83897:Repair360: Verify 'Email doesn't match this phone' and user get correct email",
            description = "Verify 'Email doesn't match this phone' and user get correct email")
    public void testVerifyEmailDoesntMatchThisPhoneAndUserGetCorrectEmail() {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String wrongRegCode = "QAQA";

        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");
        regscreen.setPhoneNumber("6667777999");
        //regscreen.setEmail("test@cyberiansoft.com");

        regscreen.clickDoneButton();


        VNextModalDialog modalDialog = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        System.out.println("==============" + modalDialog.clickInformationDialogOKButtonAndGetMessage());

    }


    @Test(testName = "Test Case 83899:Repair360 - Verify 'Phone is required'",
            description = "Verify 'Phone is required'")
    public void testVerifyPhoneIsRequired() {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String emptyString = "";

        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");
        regscreen.setPhoneNumber(emptyString);
        regscreen.clickDoneButton();

        Assert.assertEquals(regscreen.getPhoneErrorMessage(), VNextAlertMessages.PHONE_IS_REQUIRED);
        Assert.assertTrue(regscreen.isPhoneErrorMessageVisible());

        regscreen.setPhoneNumber(userregphone);
        regscreen.setEmail(emptyString);
        Assert.assertEquals(regscreen.getEmailErrorMessage(), VNextAlertMessages.EMAIL_IS_REQUIRED);
        Assert.assertTrue(regscreen.isEMailErrorMessageVisible());

        regscreen.setEmail(userregmail);
        regscreen.setFirstName(emptyString);
        Assert.assertEquals(regscreen.getFirstNameErrorMessage(), VNextAlertMessages.FIRST_NAME_IS_REQUIRED);
        Assert.assertTrue(regscreen.isFirstNameErrorMessageVisible());

        regscreen.setEmail(emptyString);
        regscreen.setPhoneNumber(emptyString);
        Assert.assertTrue(regscreen.isFirstNameErrorMessageVisible());
        Assert.assertTrue(regscreen.isEMailErrorMessageVisible());
        Assert.assertTrue(regscreen.isPhoneErrorMessageVisible());
    }

    @Test(testName = "Test Case 83903:Repair360 - Verify 'State/Province' is required",
            description = "Verify 'State/Province' is required")
    public void testVerifyStateProvinceIsRequired() throws IOException {

        final String userfirstname = "QA";
        final String userlastname = "QA";
        final String newusercompanyname = "PDR";
        final String newuseraddress1 = "Address1";
        final String newuseraddress2 = "Address2";
        final String newusercity = "LA";
        final String newuserzip = "5214BA63";
        final String newusercountry = "United States of America";
        final String newuserstate = "California";

        final String bolineofbusiness = "PDR/Hail";

        VNextRegistrationPersonalInfoScreen regscreen = new VNextRegistrationPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        regscreen.setUserRegistrationInfo(userfirstname, userlastname, userphonecountrycode, userregphone, userregmail);
        regscreen.clickClearUserButton();
        VNextModalDialog registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(), "User " + userregmail + " has been deleted");
        regscreen.clickDoneButton();
        BaseUtils.waitABit(4000);
        VNextVerificationScreen verificationscreen = new VNextVerificationScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        verificationscreen.setDeviceRegistrationCode(VNextWebServicesUtils.getVerificationCodeByPhone(userphonecountrycode + userregphone).replaceAll("\"", ""));
        verificationscreen.clickVerifyButton();

        BaseUtils.waitABit(4000);
        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(5000);
        VNextRegistrationNewUserPersonalInfoScreen newuserpersonalinfoscreen = new VNextRegistrationNewUserPersonalInfoScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        newuserpersonalinfoscreen.clickDoneButton();
        Assert.assertEquals(newuserpersonalinfoscreen.getStateProvinceErrorMessage(),
                VNextAlertMessages.PLEASE_SELECT_A_STATE_OR_PROVINCE);
        Assert.assertTrue(newuserpersonalinfoscreen.isStateProvinceErrorMessageVisible());

        newuserpersonalinfoscreen.setNewUserPersonaInfo(newusercompanyname,
                newuseraddress1, newuseraddress2, newusercity, newuserzip, newusercountry, newuserstate);
        newuserpersonalinfoscreen.clickDoneButton();
        BaseUtils.waitABit(4000);
        VNextRegistrationLineOfBusinessScreen reglineofbusinessscreen = new VNextRegistrationLineOfBusinessScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        reglineofbusinessscreen.clickDoneButton();

        registrationinformationdlg = new VNextModalDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationinformationdlg.clickInformationDialogOKButtonAndGetMessage(),
                VNextAlertMessages.PLEASE_SELECT_LINE_OF_BUSINESS);


        reglineofbusinessscreen.selectLineOfBusiness(bolineofbusiness);

        ChromeDriverProvider.INSTANCE.getMobileChromeDriver().switchTo().defaultContent();
        BaseUtils.waitABit(2000);


        VNextRegistrationOverviewScreen registrationoverviewscreen = new VNextRegistrationOverviewScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertEquals(registrationoverviewscreen.getUserFirstNameValue(), userfirstname);
        Assert.assertEquals(registrationoverviewscreen.getUserLastNameValue(), userlastname);
        Assert.assertEquals(registrationoverviewscreen.getUserCompanyNameValue(), newusercompanyname);
        Assert.assertEquals(registrationoverviewscreen.getUserEmailValue(), userregmail);
        Assert.assertEquals(registrationoverviewscreen.getAddress1Value(), newuseraddress1);
        Assert.assertEquals(registrationoverviewscreen.getAddress2Value(), newuseraddress2);
        Assert.assertEquals(registrationoverviewscreen.getCityValue(), newusercity);
        Assert.assertEquals(registrationoverviewscreen.getZipValue(), newuserzip);
        Assert.assertEquals(registrationoverviewscreen.getStateValue(), newuserstate);
        Assert.assertEquals(registrationoverviewscreen.getCountryValue(), newusercountry);
        registrationoverviewscreen.clickDoneButton();
        VNextRegistrationOverviewLegalInfosScreen registrationoverviewlegalinfoscreen =
                new VNextRegistrationOverviewLegalInfosScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        registrationoverviewlegalinfoscreen.agreetermsAndconditions();
        registrationoverviewlegalinfoscreen.clickSubmitButton();
        BaseUtils.waitABit(25 * 1000);
        if (DriverBuilder.getInstance().getMobilePlatform().equals(MobilePlatform.ANDROID)) {
            AppiumUtils.switchApplicationContext(AppContexts.NATIVE_CONTEXT);
            DriverBuilder.getInstance().getAppiumDriver().closeApp();
            DriverBuilder.getInstance().getAppiumDriver().launchApp();
            AppiumUtils.switchApplicationContext(AppContexts.WEBVIEW_CONTEXT);
        }

        WebDriverWait wait = new WebDriverWait(ChromeDriverProvider.INSTANCE.getMobileChromeDriver(), 340);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Data has been successfully downloaded']")));
        VNextInformationDialog informationdlg = new VNextInformationDialog(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        informationdlg.clickInformationDialogOKButton();
    }
}
