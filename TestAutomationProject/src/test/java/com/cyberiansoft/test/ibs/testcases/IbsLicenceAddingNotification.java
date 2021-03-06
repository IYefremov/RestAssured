package com.cyberiansoft.test.ibs.testcases;

import com.cyberiansoft.test.bo.config.BOConfigInfo;
import com.cyberiansoft.test.bo.pageobjects.webpages.ActiveDevicesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeLoginWebPage;
import com.cyberiansoft.test.dataclasses.ibs.IBSLicenceAddingNotificationData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.ibs.config.IBSConfigInfo;
import com.cyberiansoft.test.ibsBo.IbsBoEditClientPage;
import com.cyberiansoft.test.ibsBo.IbsBoLoginWebPage;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//@Listeners(VideoListener.class)
public class IbsLicenceAddingNotification extends BaseTestCase {

    private static final String DATA_FILE =
            "src/test/java/com/cyberiansoft/test/ibs/data/IBSLicenceAddingNotificationData.json";

    @BeforeClass()
    public void settingUp() {
        JSONDataProvider.dataFile = DATA_FILE;
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyBillingProfilesAreEnabled(String rowID, String description, JSONObject testData) {
        IBSLicenceAddingNotificationData data = JSonDataParser
                .getTestDataFromJson(testData, IBSLicenceAddingNotificationData.class);

        goToWebPage(IBSConfigInfo.getInstance().getIbsBoUrl());
        IbsBoLoginWebPage ibsBoLoginPage = PageFactory.initElements(webdriver, IbsBoLoginWebPage.class);
        IbsBoEditClientPage ibsBoEditClientPage = ibsBoLoginPage.login(IBSConfigInfo.getInstance().getIbsBoName(),
                IBSConfigInfo.getInstance().getIbsBoPassword());
        Assert.assertTrue(ibsBoEditClientPage.isEditClientPageOpened(), "The Edit Client Page has not been opened");
        ibsBoEditClientPage.scrollDownToText(data.getSectionBillingProfiles());
        ibsBoEditClientPage
                .verifyBillingProfileIsDeactivated(data.getSectionBillingProfileItems(), data.getEffectiveDate(), data.getExpirationDate())
                .activateBillingProfile(data.getSectionBillingProfileItems(), data.getEffectiveDate(), data.getExpirationDate());

        Assert.assertTrue(ibsBoEditClientPage.isBillingProfileUpdated(), "The Billing Profile has not been updated");
        Assert.assertTrue(ibsBoEditClientPage.isBillingProfileActivated(),
                "The Billing Profile is not activated");

        goToWebPage(IBSConfigInfo.getInstance().getReconProDevicesUrl());
        BackOfficeLoginWebPage loginPage = PageFactory.initElements(webdriver, BackOfficeLoginWebPage.class);
        loginPage.userLogin(BOConfigInfo.getInstance().getUserName(), BOConfigInfo.getInstance().getUserPassword());

        ActiveDevicesWebPage activeDevicesPage = PageFactory.initElements(webdriver, ActiveDevicesWebPage.class);
        String license = activeDevicesPage
                .clickAddNewDevicesLink()
                .selectLicenseNumber();
        activeDevicesPage.clickDevicesModalDialogOkButton();
        Assert.assertTrue(activeDevicesPage.isAlertNotificationDisplayed(), "The alert is not displayed");
        Assert.assertEquals(activeDevicesPage.getAlertTextDisplayed(), data.getAlertText());
        activeDevicesPage.handleAlert();
        Assert.assertTrue(activeDevicesPage.isLicenseDisplayed(license), "The license has not been added");
        activeDevicesPage.deleteDevices();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyClientAgreementsAreEnabled(String rowID, String description, JSONObject testData) {
        IBSLicenceAddingNotificationData data = JSonDataParser
                .getTestDataFromJson(testData, IBSLicenceAddingNotificationData.class);

        goToWebPage(IBSConfigInfo.getInstance().getIbsBoUrl());
        IbsBoLoginWebPage ibsBoLoginPage = PageFactory.initElements(webdriver, IbsBoLoginWebPage.class);
        IbsBoEditClientPage ibsBoEditClientPage = ibsBoLoginPage.login(IBSConfigInfo.getInstance().getIbsBoName(),
                IBSConfigInfo.getInstance().getIbsBoPassword());
        Assert.assertTrue(ibsBoEditClientPage.isEditClientPageOpened(), "The Edit Client Page has not been opened");
        ibsBoEditClientPage.scrollDownToText(data.getSectionClientAgreements());
        ibsBoEditClientPage
                .clickClientAgreementsEditButton()
                .selectClientAgreementsActiveStatus()
                .clickClientAgreementsUpdateButton();
        Assert.assertTrue(ibsBoEditClientPage.areClientAgreementsUpdated(), "The Client Agreements have not been updated");

        goToWebPage(IBSConfigInfo.getInstance().getReconProDevicesUrl());
        BackOfficeLoginWebPage loginPage = PageFactory.initElements(webdriver, BackOfficeLoginWebPage.class);
        loginPage.userLogin(BOConfigInfo.getInstance().getUserName(), BOConfigInfo.getInstance().getUserPassword());

        ActiveDevicesWebPage activeDevicesPage = PageFactory.initElements(webdriver, ActiveDevicesWebPage.class);
        String license = activeDevicesPage
                .clickAddNewDevicesLink()
                .selectLicenseNumber();
        activeDevicesPage.clickDevicesModalDialogOkButton();
        Assert.assertTrue(activeDevicesPage.isLicenseDisplayed(license), "The license has not been added");
        activeDevicesPage.deleteDevices();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyClientAgreementsAreSuspended(String rowID, String description, JSONObject testData) {
        IBSLicenceAddingNotificationData data = JSonDataParser
                .getTestDataFromJson(testData, IBSLicenceAddingNotificationData.class);

        goToWebPage(IBSConfigInfo.getInstance().getIbsBoUrl());
        IbsBoLoginWebPage ibsBoLoginPage = PageFactory.initElements(webdriver, IbsBoLoginWebPage.class);
        IbsBoEditClientPage ibsBoEditClientPage = ibsBoLoginPage.login(IBSConfigInfo.getInstance().getIbsBoName(),
                IBSConfigInfo.getInstance().getIbsBoPassword());
        Assert.assertTrue(ibsBoEditClientPage.isEditClientPageOpened(), "The Edit Client Page has not been opened");
        ibsBoEditClientPage.scrollDownToText(data.getSectionClientAgreements());
        ibsBoEditClientPage
                .clickClientAgreementsEditButton()
                .selectClientAgreementsSuspendedStatus()
                .clickClientAgreementsUpdateButton();
        Assert.assertTrue(ibsBoEditClientPage.areClientAgreementsUpdated(), "The Client Agreements have not been updated");

        goToWebPage(IBSConfigInfo.getInstance().getReconProDevicesUrl());
        BackOfficeLoginWebPage loginPage = PageFactory.initElements(webdriver, BackOfficeLoginWebPage.class);
        loginPage.userLogin(BOConfigInfo.getInstance().getUserName(), BOConfigInfo.getInstance().getUserPassword());

        ActiveDevicesWebPage activeDevicesPage = PageFactory.initElements(webdriver, ActiveDevicesWebPage.class);
        String license = activeDevicesPage
                .clickAddNewDevicesLink()
                .selectLicenseNumber();
        activeDevicesPage.clickDevicesModalDialogOkButton();
        Assert.assertTrue(activeDevicesPage.isLicenseDisplayed(license), "The license has not been added");
        activeDevicesPage.deleteDevices();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyBillingProfilesAreDisabled(String rowID, String description, JSONObject testData) {
        IBSLicenceAddingNotificationData data = JSonDataParser
                .getTestDataFromJson(testData, IBSLicenceAddingNotificationData.class);

        goToWebPage(IBSConfigInfo.getInstance().getIbsBoUrl());
        IbsBoLoginWebPage ibsBoLoginPage = PageFactory.initElements(webdriver, IbsBoLoginWebPage.class);
        IbsBoEditClientPage ibsBoEditClientPage = ibsBoLoginPage.login(IBSConfigInfo.getInstance().getIbsBoName(),
                IBSConfigInfo.getInstance().getIbsBoPassword());
        Assert.assertTrue(ibsBoEditClientPage.isEditClientPageOpened(), "The Edit Client Page has not been opened");
        ibsBoEditClientPage
                .verifyBillingProfileIsActivated(data.getSectionBillingProfileItems(), data.getEffectiveDate(), data.getExpirationDate())
                .deactivateBillingProfile(data.getSectionBillingProfiles(), data.getEffectiveDate(), data.getExpirationDate());

        goToWebPage(IBSConfigInfo.getInstance().getReconProDevicesUrl());
        BackOfficeLoginWebPage loginPage = PageFactory.initElements(webdriver, BackOfficeLoginWebPage.class);
        loginPage.userLogin(BOConfigInfo.getInstance().getUserName(), BOConfigInfo.getInstance().getUserPassword());

        ActiveDevicesWebPage activeDevicesPage = PageFactory.initElements(webdriver, ActiveDevicesWebPage.class);
        String license = activeDevicesPage
                .clickAddNewDevicesLink()
                .selectLicenseNumber();
        activeDevicesPage.clickDevicesModalDialogOkButton();
        Assert.assertTrue(activeDevicesPage.isLicenseDisplayed(license), "The license has not been added");
        activeDevicesPage.deleteDevices();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void setupSupportForPaymentTermYearly(String rowID, String description, JSONObject testData) {
        IBSLicenceAddingNotificationData data = JSonDataParser
                .getTestDataFromJson(testData, IBSLicenceAddingNotificationData.class);

        goToWebPage(IBSConfigInfo.getInstance().getIbsBoUrl());
        IbsBoLoginWebPage ibsBoLoginPage = PageFactory.initElements(webdriver, IbsBoLoginWebPage.class);
        IbsBoEditClientPage ibsBoEditClientPage = ibsBoLoginPage.login(IBSConfigInfo.getInstance().getIbsBoName(),
                IBSConfigInfo.getInstance().getIbsBoPassword());
        Assert.assertTrue(ibsBoEditClientPage.isEditClientPageOpened(), "The Edit Client Page has not been opened");
        ibsBoEditClientPage.scrollDownToText(data.getSectionBillingProfiles());
        ibsBoEditClientPage
                .clickAddNewBillingProfilesButton()
                .selectPaymentTerm(data.getYearly())
                .clickBillingProfilesInsertButton()
                .clickBillingProfilesEditButton();
        Assert.assertTrue(ibsBoEditClientPage.verifyPaymentTermIsYearly(), "The payment term has not been set to Yearly");
        ibsBoEditClientPage
                .clickCancelButton();
//                .deleteBillingProfilesWithOldInvoicesService(); todo uncomment after bug fix. Hangs up because of the bug #68296
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void setupSupportForPaymentTermMonthly(String rowID, String description, JSONObject testData) {
        IBSLicenceAddingNotificationData data = JSonDataParser
                .getTestDataFromJson(testData, IBSLicenceAddingNotificationData.class);

        goToWebPage(IBSConfigInfo.getInstance().getIbsBoUrl());
        IbsBoLoginWebPage ibsBoLoginPage = PageFactory.initElements(webdriver, IbsBoLoginWebPage.class);
        IbsBoEditClientPage ibsBoEditClientPage = ibsBoLoginPage.login(IBSConfigInfo.getInstance().getIbsBoName(),
                IBSConfigInfo.getInstance().getIbsBoPassword());
        Assert.assertTrue(ibsBoEditClientPage.isEditClientPageOpened(), "The Edit Client Page has not been opened");
        ibsBoEditClientPage.scrollDownToText(data.getSectionBillingProfiles());
        ibsBoEditClientPage
                .clickAddNewBillingProfilesButton()
                .selectPaymentTerm(data.getMonthly())
                .clickBillingProfilesInsertButton()
                .clickBillingProfilesEditButton();
        Assert.assertTrue(ibsBoEditClientPage.verifyPaymentTermIsMonthly(), "The payment term has not been set to Monthly");
        ibsBoEditClientPage
                .clickCancelButton();
//                .deleteBillingProfilesWithOldInvoicesService(); todo uncomment after bug fix. Hangs up because of the bug #68296
    }
}