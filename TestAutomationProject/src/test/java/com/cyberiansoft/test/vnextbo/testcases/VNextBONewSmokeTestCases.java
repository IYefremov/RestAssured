package com.cyberiansoft.test.vnextbo.testcases;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.dataclasses.vNextBO.*;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.config.VNextBOConfigInfo;
import com.cyberiansoft.test.vnextbo.interactions.breadcrumb.VNextBOBreadCrumbInteractions;
import com.cyberiansoft.test.vnextbo.interactions.clients.VNextBOAccountInfoBlockInteractions;
import com.cyberiansoft.test.vnextbo.interactions.clients.VNextBOEmailOptionsBlockInteractions;
import com.cyberiansoft.test.vnextbo.interactions.deviceManagement.VNextBOActiveDevicesInteractions;
import com.cyberiansoft.test.vnextbo.interactions.deviceManagement.VNextBODeviceManagementInteractions;
import com.cyberiansoft.test.vnextbo.interactions.deviceManagement.VNextBOEditDeviceDialogInteractions;
import com.cyberiansoft.test.vnextbo.interactions.deviceManagement.VNextBOPendingRegistrationsInteractions;
import com.cyberiansoft.test.vnextbo.interactions.leftMenuPanel.VNextBOLeftMenuInteractions;
import com.cyberiansoft.test.vnextbo.screens.*;
import com.cyberiansoft.test.vnextbo.screens.inspections.VNextBOInspectionsWebPage;
import com.cyberiansoft.test.vnextbo.steps.HomePageSteps;
import com.cyberiansoft.test.vnextbo.steps.VNextBOHeaderPanelSteps;
import com.cyberiansoft.test.vnextbo.steps.clients.VNextBOClientDetailsViewAccordionSteps;
import com.cyberiansoft.test.vnextbo.steps.clients.VNextBOClientsPageSteps;
import com.cyberiansoft.test.vnextbo.steps.commonobjects.VNextBOSearchPanelSteps;
import com.cyberiansoft.test.vnextbo.steps.devicemanagement.VNextBOAddNewDeviceSteps;
import com.cyberiansoft.test.vnextbo.steps.devicemanagement.VNextBODeviceManagementSteps;
import com.cyberiansoft.test.vnextbo.steps.devicemanagement.VNextBOEditDeviceSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsAdvancedSearchSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsApprovalPageSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsPageSteps;
import com.cyberiansoft.test.vnextbo.steps.repairOrders.VNextBORODetailsPageSteps;
import com.cyberiansoft.test.vnextbo.steps.repairOrders.VNextBORONotesPageSteps;
import com.cyberiansoft.test.vnextbo.steps.repairOrders.VNextBOROSimpleSearchSteps;
import com.cyberiansoft.test.vnextbo.steps.repairOrders.VNextBORepairOrdersPageSteps;
import com.cyberiansoft.test.vnextbo.verifications.VNextBOLeftMenuValidations;
import com.cyberiansoft.test.vnextbo.verifications.VNextBONotesPageVerifications;
import com.cyberiansoft.test.vnextbo.verifications.VNextBOPendingRegistrationsValidations;
import com.cyberiansoft.test.vnextbo.verifications.clients.VNextBOClientsPageValidations;
import com.cyberiansoft.test.vnextbo.verifications.repairOrders.VNextBORODetailsPageVerifications;
import com.cyberiansoft.test.vnextbo.verifications.repairOrders.VNextBOROPageValidations;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.cyberiansoft.test.vnextbo.utils.WebDriverUtils.webdriverGotoWebPage;

public class VNextBONewSmokeTestCases extends BaseTestCase {

    private static final String DATA_FILE = "src/test/java/com/cyberiansoft/test/vnextbo/data/VNextBONewSmokeData.json";
    private VNextBOBreadCrumbInteractions breadCrumbInteractions;
    private VNextBOPartsManagementSearchPanel partsManagementSearch;
    private VNextBOPartsOrdersListPanel partsOrdersListPanel;
    private VNextBOPartsDetailsPanel partsDetailsPanel;
    private VNextBOCompanyInfoWebPage companyInfoWebPage;
    private VNextBOHomeWebPage homePage;
    private VNextBOInspectionsWebPage inspectionsWebPage;
    private VNextBOEmailOptionsBlockInteractions emailOptionsBlockInteractions;
    private VNextBOQuickNotesWebPage quickNotesPage;

    @BeforeClass
    public void settingUp() {
        JSONDataProvider.dataFile = DATA_FILE;
    }

    @BeforeMethod
    public void BackOfficeLogin() {
        browserType = BaseUtils.getBrowserType(VNextBOConfigInfo.getInstance().getDefaultBrowser());
        try {
            DriverBuilder.getInstance().setDriver(browserType);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        webdriver = DriverBuilder.getInstance().getDriver();

        webdriverGotoWebPage(VNextBOConfigInfo.getInstance().getVNextBOCompanionappURL());
        final String userName = VNextBOConfigInfo.getInstance().getVNextBONadaMail();
        final String userPassword = VNextBOConfigInfo.getInstance().getVNextBOPassword();

        VNextBOLoginScreenWebPage loginPage = PageFactory.initElements(webdriver, VNextBOLoginScreenWebPage.class);
        loginPage.userLogin(userName, userPassword);

        partsManagementSearch = PageFactory.initElements(webdriver, VNextBOPartsManagementSearchPanel.class);
        partsOrdersListPanel = PageFactory.initElements(webdriver, VNextBOPartsOrdersListPanel.class);
        partsDetailsPanel = PageFactory.initElements(webdriver, VNextBOPartsDetailsPanel.class);
        companyInfoWebPage = PageFactory.initElements(webdriver, VNextBOCompanyInfoWebPage.class);
        homePage = PageFactory.initElements(webdriver, VNextBOHomeWebPage.class);
        inspectionsWebPage = PageFactory.initElements(webdriver, VNextBOInspectionsWebPage.class);
        quickNotesPage = PageFactory.initElements(DriverBuilder.getInstance().getDriver(), VNextBOQuickNotesWebPage.class);
        breadCrumbInteractions = new VNextBOBreadCrumbInteractions();
        emailOptionsBlockInteractions = new VNextBOEmailOptionsBlockInteractions();
    }

    @AfterMethod
    public void BackOfficeLogout() {
        VNextBOHeaderPanelSteps.logout();

        if (DriverBuilder.getInstance().getDriver() != null) {
            DriverBuilder.getInstance().quitDriver();
        }
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanMaximizeMinimizeMenu(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        Assert.assertTrue(VNextBOLeftMenuValidations.isMenuButtonDisplayed(), "The Menu button hasn't been displayed");
        Assert.assertTrue(homePage.isLogoDisplayed(), "The logo hasn't been displayed");
        Assert.assertTrue(homePage.isUserEmailDisplayed(), "The email hasn't been displayed");
        Assert.assertTrue(homePage.isLogoutButtonDisplayed(), "The logout button hasn't been displayed");
        Assert.assertTrue(homePage.isHelpButtonDisplayed(), "The help button hasn't been displayed");
        Assert.assertTrue(homePage.isAccessClientPortalLinkDisplayed(),
                "The access client portal link hasn't been displayed");
        Assert.assertTrue(homePage.isAccessReconProBOLinkDisplayed(),
                "The access ReconPro BO link hasn't been displayed");
        Assert.assertTrue(homePage.isSupportForBOButtonDisplayed(),
                "The support for BO button hasn't been displayed");
        Assert.assertTrue(homePage.isSupportForMobileAppButtonDisplayed(),
                "The support for Mobile App button hasn't been displayed");
        Assert.assertTrue(homePage.isTermsAndConditionsLinkDisplayed(),
                "The Terms And Conditions Link hasn't been displayed");
        Assert.assertTrue(homePage.isPrivacyPolicyLinkDisplayed(),
                "The Privacy Policy Link hasn't been displayed");
        Assert.assertTrue(homePage.isIntercomDisplayed(),
                "The Intercom Link hasn't been displayed");

        VNextBOLeftMenuInteractions.expandMainMenu();
        Assert.assertTrue(VNextBOLeftMenuInteractions.isMainMenuExpanded(), "The main menu hasn't been expanded");
        VNextBOLeftMenuInteractions.collapseMainMenu();
        Assert.assertFalse(VNextBOLeftMenuInteractions.isMainMenuExpanded(), "The main menu hasn't been collapsed");
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanApproveInspection(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        VNextBOLeftMenuInteractions.selectInspectionsMenu();

        VNextBOInspectionsPageSteps.openAdvancedSearchForm();
        VNextBOInspectionsAdvancedSearchSteps.setAdvSearchDropDownField("Status", data.getStatuses()[0]);
        VNextBOInspectionsAdvancedSearchSteps.clickSearchButton();
        final String inspectionNumber = breadCrumbInteractions.getLastBreadCrumbText();
        VNextBOInspectionsApprovalPageSteps.approveInspection(data.getNote());

        VNextBOInspectionsPageSteps.openAdvancedSearchForm();
        VNextBOInspectionsAdvancedSearchSteps.setAdvSearchTextField("Inspection#", inspectionNumber);
        VNextBOInspectionsAdvancedSearchSteps.setAdvSearchDropDownField("Status", data.getStatuses()[1]);
        VNextBOInspectionsAdvancedSearchSteps.clickSearchButton();
        Assert.assertEquals(inspectionsWebPage.getFirstInspectionStatus(), data.getStatuses()[1],
                "The status of inspection hasn't been changed from 'New' to 'Approved'");
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanAddNewParts(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        VNextBOLeftMenuInteractions.selectPartsManagementMenu();
        breadCrumbInteractions.setLocation(data.getLocation());

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();

        Assert.assertEquals(partsOrdersListPanel.getWoNumsListOptions().get(0), data.getWoNum(),
                "The WO order hasn't been displayed after search");
        Assert.assertTrue(partsDetailsPanel.isPartsDetailsTableDisplayed(),
                "Tha Parts details panel hasn't been displayed");

        final VNextBOAddNewPartDialog addNewPartDialog = partsDetailsPanel.clickAddNewPartButton();
        Assert.assertTrue(partsDetailsPanel.isAddNewPartDialogDisplayed(), "The part dialog hasn't been displayed");

        addNewPartDialog.setService(data.getService());
        Assert.assertEquals(addNewPartDialog.getServiceFieldValue(), data.getService(), "The service hasn't been set");

        addNewPartDialog
                .setServiceDescription(data.getServiceDescription())
                .setCategory(data.getServiceCategory())
                .setSubcategory(data.getServiceSubcategory());

        final int partsCounterValueBefore = Integer.valueOf(addNewPartDialog.getPartsCounterValue());

        addNewPartDialog.selectPartsFromPartsList(Arrays.asList(data.getPartItems()));
        final int partsCounterValueAfter = Integer.valueOf(addNewPartDialog.getPartsCounterValue());

        Assert.assertEquals(partsCounterValueBefore + data.getPartItems().length, partsCounterValueAfter,
                "The parts counter value hasn't been recalculated");

        addNewPartDialog.clickSubmitButton();
        Assert.assertTrue(partsDetailsPanel.isAddNewPartDialogNotDisplayed());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanChangeStatusOfThePartToOrdered(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        VNextBOLeftMenuInteractions.selectPartsManagementMenu();
        breadCrumbInteractions.setLocation(data.getLocation());

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();

        Assert.assertEquals(partsOrdersListPanel.getWoNumsListOptions().get(0), data.getWoNum(),
                "The WO order hasn't been displayed after search");
        Assert.assertTrue(partsDetailsPanel.isPartsDetailsTableDisplayed(),
                "Tha Parts details panel hasn't been displayed");

        partsDetailsPanel.verifyStatusIsChanged(data.getStatus());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanDuplicateThePart(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        VNextBOLeftMenuInteractions.selectPartsManagementMenu();
        breadCrumbInteractions.setLocation(data.getLocation());

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();

        Assert.assertEquals(partsOrdersListPanel.getWoNumsListOptions().get(0), data.getWoNum(),
                "The WO order hasn't been displayed after search");
        Assert.assertTrue(partsDetailsPanel.isPartsDetailsTableDisplayed(),
                "Tha Parts details panel hasn't been displayed");

        final int numberOfParts = partsDetailsPanel.getNumberOfParts();

        partsDetailsPanel.clickActionsButton(0);
        Assert.assertTrue(partsDetailsPanel.isActionsPartsMenuDisplayed(0));
        partsDetailsPanel.clickDuplicateActionsButton(0);

        Assert.assertTrue(partsDetailsPanel.isConfirmationPartDialogDisplayed(),
                "The Confirm duplicating dialog hasn't been displayed");

        partsDetailsPanel.clickConfirmationPartButton();

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();
        Assert.assertEquals(numberOfParts + 1, partsDetailsPanel.getNumberOfParts(),
                "The number of parts hasn't been increased by 1 after duplicating");
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanDeleteThePart(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        VNextBOLeftMenuInteractions.selectPartsManagementMenu();
        breadCrumbInteractions.setLocation(data.getLocation());

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();

        Assert.assertEquals(partsOrdersListPanel.getWoNumsListOptions().get(0), data.getWoNum(),
                "The WO order hasn't been displayed after search");
        Assert.assertTrue(partsDetailsPanel.isPartsDetailsTableDisplayed(),
                "Tha Parts details panel hasn't been displayed");

        final int numberOfParts = partsDetailsPanel.getNumberOfParts();
        final int partOrder = partsDetailsPanel.getPartOrderByName(data.getPartItems()[0]);
        System.out.println(partOrder);
        partsDetailsPanel.clickActionsButton(partOrder);

        Assert.assertTrue(partsDetailsPanel.isActionsPartsMenuDisplayed(partOrder));
        partsDetailsPanel.clickDeleteActionsButton(partOrder);

        Assert.assertTrue(partsDetailsPanel.isConfirmationPartDialogDisplayed(),
                "The Confirm deleting part dialog hasn't been displayed");

        partsDetailsPanel.clickConfirmationPartButton();

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();
        Assert.assertEquals(numberOfParts - 1, partsDetailsPanel.getNumberOfParts(),
                "The number of parts hasn't been decreased by 1 after deleting");
    }

    // Company Info
    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanAddAndDeleteLabor(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        VNextBOLeftMenuInteractions.selectPartsManagementMenu();
        breadCrumbInteractions.setLocation(data.getLocation());

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();

        Assert.assertEquals(partsOrdersListPanel.getWoNumsListOptions().get(0), data.getWoNum(),
                "The WO order hasn't been displayed after search");
        Assert.assertTrue(partsDetailsPanel.isPartsDetailsTableDisplayed(),
                "Tha Parts details panel hasn't been displayed");

        partsDetailsPanel.clickPartsArrow(0);
        Assert.assertTrue(partsDetailsPanel.isAddLaborButtonDisplayed(0),
                "The Add Labor button hasn't been displayed");

        final int numberOfLaborBlocksBefore = partsDetailsPanel.getNumberOfLaborBlocks();
        System.out.println("before: " + numberOfLaborBlocksBefore);

        final VNextBOAddLaborPartsDialog laborPartsDialog = partsDetailsPanel.clickAddLaborButton(0);
        Assert.assertTrue(laborPartsDialog.isAddLaborDialogDisplayed(),
                "The Labor dialog hasn't been displayed");

        Assert.assertFalse(laborPartsDialog.isLaborClearIconDisplayed(),
                "The Labor Clear icon has been displayed before selecting the labor");
        laborPartsDialog.setLaborService(data.getLabor());
        Assert.assertTrue(laborPartsDialog.isLaborClearIconDisplayed(), "The Labor Clear icon hasn't been displayed");

        laborPartsDialog.clickAddLaborButtonForDialog();
        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();

        Assert.assertEquals(partsOrdersListPanel.getWoNumsListOptions().get(0), data.getWoNum(),
                "The WO hasn't been displayed after search");

        partsDetailsPanel.clickPartsArrow(0);
        Assert.assertTrue(partsDetailsPanel.isAddLaborButtonDisplayed(0),
                "The Add Labor button hasn't been displayed");
        final int numberOfLaborBlocksAfter = partsDetailsPanel.getNumberOfLaborBlocks();
        System.out.println("after: " + numberOfLaborBlocksAfter);
        Assert.assertEquals(numberOfLaborBlocksAfter, numberOfLaborBlocksBefore + 1,
                "The labor hasn't been added");

        partsDetailsPanel
                .clickDeleteLaborButton(numberOfLaborBlocksBefore)
                .clickConfirmDeletingButton();
        partsDetailsPanel.refreshPage();

        partsManagementSearch
                .setPartsSearchText(data.getWoNum())
                .clickSearchLoupeIcon();

        partsDetailsPanel.clickPartsArrow(0);

        Assert.assertEquals(partsDetailsPanel.getNumberOfLaborBlocks(), numberOfLaborBlocksBefore,
                "The labor hasn't been deleted");
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanChangeCompanyInfoAndSaveIt(String rowID, String description, JSONObject testData) {
        VNextBONewSmokeData data = JSonDataParser.getTestDataFromJson(testData, VNextBONewSmokeData.class);

        VNextBOLeftMenuInteractions.selectCompanyInfoMenu();
        companyInfoWebPage
                .setCompanyName(data.getCompany()[0])
                .setAddressLine1(data.getAddressLine1()[0])
                .setAddressLine2(data.getAddressLine2()[0])
                .setCity(data.getCity()[0])
                .setCountry(data.getCountry()[0])
                .setStateProvince(data.getStateProvince()[0])
                .setZip(data.getZip()[0])
                .setPhoneCode(data.getPhoneCode()[0])
                .setPhone(data.getPhone()[0])
                .setEmail(data.getEmail()[0])
                .clickSaveButton();

        Assert.assertTrue(companyInfoWebPage.isSuccessNotificationDisplayed(),
                "The success notification hasn't been displayed after clicking the 'Save' button");

        Assert.assertEquals(companyInfoWebPage.getCompanyValue(), data.getCompany()[0]);
        Assert.assertEquals(companyInfoWebPage.getAddressLine1Value(), data.getAddressLine1()[0]);
        Assert.assertEquals(companyInfoWebPage.getAddressLine2Value(), data.getAddressLine2()[0]);
        Assert.assertEquals(companyInfoWebPage.getCityValue(), data.getCity()[0]);
        Assert.assertEquals(companyInfoWebPage.getCountryValue(), data.getCountry()[0]);
        Assert.assertEquals(companyInfoWebPage.getStateProvinceValue(), data.getStateProvince()[0]);
        Assert.assertEquals(companyInfoWebPage.getZipValue(), data.getZip()[0]);
        Assert.assertEquals(companyInfoWebPage.getPhoneCodeValue(), data.getPhoneCode()[0]);
        Assert.assertEquals(companyInfoWebPage.getPhoneValue(), data.getPhone()[0]);
        Assert.assertEquals(companyInfoWebPage.getEmailValue(), data.getEmail()[0]);

        companyInfoWebPage
                .setCompanyName(data.getCompany()[1])
                .setAddressLine1(data.getAddressLine1()[1])
                .setAddressLine2(data.getAddressLine2()[1])
                .setCity(data.getCity()[1])
                .setCountry(data.getCountry()[1])
                .setStateProvince(data.getStateProvince()[1])
                .setZip(data.getZip()[1])
                .setPhoneCode(data.getPhoneCode()[1])
                .setPhone(data.getPhone()[1])
                .setEmail(data.getEmail()[1])
                .clickSaveButton();

        Assert.assertTrue(companyInfoWebPage.isSuccessNotificationDisplayed(),
                "The success notification hasn't been displayed after clicking the 'Save' button");

        Assert.assertEquals(companyInfoWebPage.getCompanyValue(), data.getCompany()[1]);
        Assert.assertEquals(companyInfoWebPage.getAddressLine1Value(), data.getAddressLine1()[1]);
        Assert.assertEquals(companyInfoWebPage.getAddressLine2Value(), data.getAddressLine2()[1]);
        Assert.assertEquals(companyInfoWebPage.getCityValue(), data.getCity()[1]);
        Assert.assertEquals(companyInfoWebPage.getCountryValue(), data.getCountry()[1]);
        Assert.assertEquals(companyInfoWebPage.getStateProvinceValue(), data.getStateProvince()[1]);
        Assert.assertEquals(companyInfoWebPage.getZipValue(), data.getZip()[1]);
        Assert.assertEquals(companyInfoWebPage.getPhoneCodeValue(), data.getPhoneCode()[1]);
        Assert.assertEquals(companyInfoWebPage.getPhoneValue(), data.getPhone()[1]);
        Assert.assertEquals(companyInfoWebPage.getEmailValue(), data.getEmail()[1]);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanAddDeleteAndEditQuickNotes(String rowID, String description, JSONObject testData) {
        VNextBOQuickNotesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOQuickNotesData.class);

        VNextBOLeftMenuInteractions.selectQuickNotesMenu();
        final int numberOfQuickNotes = quickNotesPage.getNumberOfQuickNotesDisplayed(data.getQuickNotesDescription());
        quickNotesPage
                .clickAddNotesButton()
                .typeDescription(data.getQuickNotesDescription())
                .clickQuickNotesDialogAddButton();
        Assert.assertEquals(numberOfQuickNotes + 1,
                quickNotesPage.getNumberOfQuickNotesDisplayed(data.getQuickNotesDescription()));

        Assert.assertTrue(quickNotesPage.isQuickNoteDisplayed(data.getQuickNotesDescription()));
        quickNotesPage
                .deleteQuickNote(data.getQuickNotesDescription())
                .deleteQuickNotesIfPresent(data.getQuickNotesDescriptionEdited())
                .clickAddNotesButton()
                .typeDescription(data.getQuickNotesDescription())
                .clickQuickNotesDialogAddButton();
        Assert.assertTrue(quickNotesPage.isQuickNoteDisplayed(data.getQuickNotesDescription()));

        quickNotesPage
                .clickEditQuickNote(data.getQuickNotesDescription())
                .typeDescription(data.getQuickNotesDescriptionEdited())
                .clickQuickNotesDialogUpdateButton();
        Assert.assertTrue(quickNotesPage.isQuickNoteDisplayed(data.getQuickNotesDescriptionEdited()));
        quickNotesPage.deleteQuickNote(data.getQuickNotesDescriptionEdited());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanEditAddressFields(String rowID, String description, JSONObject testData) {
        VNextBOClientsData data = JSonDataParser.getTestDataFromJson(testData, VNextBOClientsData.class);

        VNextBOLeftMenuInteractions.selectClientsMenu();
        VNextBOSearchPanelSteps.searchByText(data.getSearch());
        VNextBOClientsPageValidations.isClientsTableDisplayed();

        VNextBOClientsPageSteps.openClientsDetailsPage(data.getTypes()[0]);
        VNextBOClientDetailsViewAccordionSteps.setClientInfoData(data.getEmployee());

        VNextBOClientDetailsViewAccordionSteps.setAccountInfoData(data.getAccountInfoData());
        Assert.assertFalse(new VNextBOAccountInfoBlockInteractions().isPoNumberUpfrontRequiredCheckboxClickable());
        VNextBOClientDetailsViewAccordionSteps.setAddressData(data.getAddressData());
        VNextBOClientDetailsViewAccordionSteps.setEmailOptionsData(data.getEmailOptionsData(), false);

        Assert.assertFalse(emailOptionsBlockInteractions.isInvoicesCheckboxClickable(),
                "The invoices checkbox is clickable");
        Assert.assertFalse(emailOptionsBlockInteractions.isInspectionsCheckboxClickable(),
                "The inspections checkbox is clickable");
        Assert.assertFalse(emailOptionsBlockInteractions.isIncludeInspectionCheckboxClickable(),
                "The include inspections checkbox is clickable");
        VNextBOClientDetailsViewAccordionSteps.setPreferencesData(data.getDefaultArea());
        VNextBOClientDetailsViewAccordionSteps.setMiscellaneousData(data.getNotes());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanDeleteDeviceFromPendingRegistrationsList(String rowID, String description, JSONObject testData) {
        VNextBODeviceManagementData data = JSonDataParser.getTestDataFromJson(testData, VNextBODeviceManagementData.class);

        VNextBOLeftMenuInteractions.selectDeviceManagementMenu();
        new VNextBODeviceManagementInteractions().clickAddNewDeviceButton();
        final String randomUser = data.getNickname() + " " + RandomStringUtils.randomAlphanumeric(5);

        VNextBOAddNewDeviceSteps.setNewDeviceValuesAndSubmit(data, randomUser);
        final VNextBOPendingRegistrationsInteractions pendingRegistrationsInteractions =
                new VNextBOPendingRegistrationsInteractions();

        Assert.assertTrue(VNextBOPendingRegistrationsValidations.isUserDisplayedInPendingRegistrationTable(randomUser),
                "The user hasn't been displayed in the pending registration table");
        VNextBODeviceManagementSteps.deletePendingRegistrationDeviceByUser(randomUser);
        Assert.assertTrue(VNextBOPendingRegistrationsValidations.isUserNotDisplayedInPendingRegistrationTable(randomUser),
                "The user hasn't disappeared from the pending registration table");
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanUncoverHideNewRegistrationCode(String rowID, String description, JSONObject testData) {
        VNextBODeviceManagementData data = JSonDataParser.getTestDataFromJson(testData, VNextBODeviceManagementData.class);

        VNextBOLeftMenuInteractions.selectDeviceManagementMenu();
        new VNextBODeviceManagementInteractions().clickActiveDevicesTab();
        VNextBOSearchPanelSteps.searchByText(data.getDeviceName());
        Assert.assertTrue(new VNextBOActiveDevicesInteractions().isDeviceDisplayed(data.getDeviceName()),
                "The device hasn't been displayed");
        VNextBODeviceManagementSteps.verifyUserCanUncoverRegistrationCode(data.getDeviceName());
        VNextBODeviceManagementSteps.verifyUserCanHideRegistrationCode(data.getDeviceName());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanEditDeviceSettings(String rowID, String description, JSONObject testData) {
        VNextBODeviceManagementData data = JSonDataParser.getTestDataFromJson(testData, VNextBODeviceManagementData.class);
        final VNextBOEditDeviceSteps editDeviceSteps = new VNextBOEditDeviceSteps();
        final VNextBOEditDeviceDialogInteractions editDeviceDialogInteractions = new VNextBOEditDeviceDialogInteractions();

        VNextBOLeftMenuInteractions.selectDeviceManagementMenu();

        VNextBODeviceManagementSteps.openEditDeviceDialog(data.getDeviceName());
        VNextBOEditDeviceSteps.setAllValuesAndSubmit(data, data.getNickname());
        VNextBOSearchPanelSteps.searchByText(data.getNickname());
        Assert.assertTrue(new VNextBOActiveDevicesInteractions().isDeviceDisplayed(data.getNickname()),
                "The device hasn't been displayed");

        VNextBODeviceManagementSteps.openEditDeviceDialog(data.getNickname());

        System.out.println(editDeviceDialogInteractions.getNickNameValue());
        System.out.println(editDeviceDialogInteractions.getTeamValue());
        System.out.println(editDeviceDialogInteractions.getTimeZoneValue());

        Assert.assertEquals(editDeviceDialogInteractions.getNickNameValue(), data.getNickname(),
                "The nickName hasn't been changed");
        Assert.assertEquals(editDeviceDialogInteractions.getTeamValue(), data.getTeam(),
                "The team hasn't been changed");
        Assert.assertEquals(editDeviceDialogInteractions.getTimeZoneValue(), data.getTimeZone(),
                "The time zone hasn't been changed");

        VNextBOEditDeviceSteps.setNickNameValueAndSubmit(data.getNickname());
    }

    //todo bug 87314 change the serviceVendorPrice test data from 5 to 1 to test the boundary values after the bug fix
    //todo bug fixed, add advanced search options steps
    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanChangeVendorPrice(String rowID, String description, JSONObject testData) {
        VNextBOMonitorData data = JSonDataParser.getTestDataFromJson(testData, VNextBOMonitorData.class);
        final VNextBOROPageValidations roPageVerifications = new VNextBOROPageValidations();

        HomePageSteps.openRepairOrdersMenuWithLocation(data.getLocation());
        VNextBOROSimpleSearchSteps.searchByText(data.getOrderNumber());

//        roPageVerifications.verifyAdvancedSearchDialogIsDisplayed();
//
//        new VNextBOROAdvancedSearchDialogSteps().searchByActivePhase(
//                data.getPhase(), data.getPhaseStatus(), data.getTimeFrame());
        VNextBORepairOrdersPageSteps.openRODetailsPage(data.getOrderNumber());
//        roDetailsPageSteps.openServicesTableForStatus(data.getStatus(), data.getPhase());
//        final String serviceId = roDetailsPageVerifications
//                .verifyServiceIsDisplayedForExpandedPhase(data.getService());
//
//        Arrays.asList(data.getServiceVendorPrices())
//                .forEach(vendorPrice -> roDetailsPageVerifications
//                        .verifyServiceVendorPriceIsSet(serviceId, data.getService(), vendorPrice));
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSeeAndCreateNotes(String rowID, String description, JSONObject testData) {
        VNextBOMonitorData data = JSonDataParser.getTestDataFromJson(testData, VNextBOMonitorData.class);

        HomePageSteps.openRepairOrdersMenuWithLocation(data.getLocation());
        VNextBOROSimpleSearchSteps.searchByText(data.getOrderNumber());
        VNextBORONotesPageSteps.openNoteDialog(data.getOrderNumber());
        Assert.assertTrue(VNextBONotesPageVerifications.isRoEditNotesModalDialogDisplayed(),
                "The edit notes dialog hasn't been opened");

        final String note = data.getNotesMessage() + RandomStringUtils.randomAlphanumeric(5);
        VNextBORONotesPageSteps.setRONoteMessageAndSave(note);
        Assert.assertTrue(VNextBONotesPageVerifications.isRoEditNotesModalDialogHidden(),
                "The edit notes dialog hasn't been closed");
        VNextBOROPageValidations.verifyNoteTextIsDisplayed(note);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCheckInRO(String rowID, String description, JSONObject testData) {
        VNextBOMonitorData data = JSonDataParser.getTestDataFromJson(testData, VNextBOMonitorData.class);

        HomePageSteps.openRepairOrdersMenuWithLocation(data.getLocation());
        VNextBOROSimpleSearchSteps.searchByText(data.getOrderNumber());
        VNextBORepairOrdersPageSteps.openRODetailsPage(data.getOrderNumber());
        Assert.assertTrue(VNextBORODetailsPageVerifications.isPhaseActionsTriggerDisplayed(),
                "The phase actions trigger hasn't been displayed");
        VNextBORODetailsPageVerifications.verifyCheckInOptionIsDisplayedForPhase();
        VNextBORODetailsPageSteps.setCheckInOptionForPhase();
        VNextBORODetailsPageSteps.setCheckOutOptionForPhase();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanReportProblemOnPhaseLevel(String rowID, String description, JSONObject testData) {
        VNextBOMonitorData data = JSonDataParser.getTestDataFromJson(testData, VNextBOMonitorData.class);

        HomePageSteps.openRepairOrdersMenuWithLocation(data.getLocation());
        VNextBOROSimpleSearchSteps.searchByText(data.getOrderNumber());
        VNextBORepairOrdersPageSteps.openRODetailsPage(data.getOrderNumber());
        Assert.assertTrue(VNextBORODetailsPageVerifications.isPhaseActionsTriggerDisplayed(data.getPhase()),
                "The phase actions trigger hasn't been displayed");

        VNextBORODetailsPageSteps.setReportProblemForPhase(data.getPhase(), data.getReason(), data.getProblemDescription());
        VNextBORODetailsPageSteps.setResolveProblemForPhase(data.getPhase());
    }
}