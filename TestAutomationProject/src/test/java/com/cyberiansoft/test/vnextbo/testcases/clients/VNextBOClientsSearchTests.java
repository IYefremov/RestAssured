package com.cyberiansoft.test.vnextbo.testcases.clients;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.config.VNextBOConfigInfo;
import com.cyberiansoft.test.vnextbo.interactions.leftMenuPanel.VNextBOLeftMenuInteractions;
import com.cyberiansoft.test.vnextbo.screens.VNextBOLoginScreenWebPage;
import com.cyberiansoft.test.vnextbo.screens.clients.VNextBOClientsAdvancedSearchForm;
import com.cyberiansoft.test.vnextbo.steps.VNextBOHeaderPanelSteps;
import com.cyberiansoft.test.vnextbo.steps.clients.VNextBOClientsAdvancedSearchSteps;
import com.cyberiansoft.test.vnextbo.steps.clients.VNextBOClientsPageSteps;
import com.cyberiansoft.test.vnextbo.steps.commonobjects.VNextBOSearchPanelSteps;
import com.cyberiansoft.test.vnextbo.testcases.BaseTestCase;
import com.cyberiansoft.test.vnextbo.verifications.clients.VNextBOClientsAdvancedSearchValidations;
import com.cyberiansoft.test.vnextbo.verifications.clients.VNextBOClientsPageValidations;
import com.cyberiansoft.test.vnextbo.verifications.commonobjects.VNextBOPageSwitcherValidations;
import com.cyberiansoft.test.vnextbo.verifications.commonobjects.VNextBOSearchPanelValidations;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.cyberiansoft.test.vnextbo.utils.WebDriverUtils.webdriverGotoWebPage;

public class VNextBOClientsSearchTests extends BaseTestCase {

    private static final String DATA_FILE = "src/test/java/com/cyberiansoft/test/vnextbo/data/clients/VNextBOClientsSearchData.json";
    private VNextBOLoginScreenWebPage loginPage;
    String userName = VNextBOConfigInfo.getInstance().getVNextBONadaMail();
    String userPassword = VNextBOConfigInfo.getInstance().getVNextBOPassword();

    @BeforeClass
    public void settingUp() {

        JSONDataProvider.dataFile = DATA_FILE;
        browserType = BaseUtils.getBrowserType(VNextBOConfigInfo.getInstance().getDefaultBrowser());
        try {
            DriverBuilder.getInstance().setDriver(browserType);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        webdriver = DriverBuilder.getInstance().getDriver();

        webdriverGotoWebPage(VNextBOConfigInfo.getInstance().getVNextBOCompanionappURL());

        loginPage = new VNextBOLoginScreenWebPage();
        loginPage.userLogin(userName, userPassword);
        VNextBOLeftMenuInteractions leftMenuInteractions = new VNextBOLeftMenuInteractions();
        leftMenuInteractions.selectClientsMenu();
    }

    @AfterClass
    public void backOfficeLogout() {
        VNextBOHeaderPanelSteps.logout();

        if (DriverBuilder.getInstance().getDriver() != null) {
            DriverBuilder.getInstance().quitDriver();
        }
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchClients(String rowID, String description, JSONObject testData) {

        VNextBOSearchPanelSteps.searchByText("jack");
        VNextBOClientsPageValidations.verifySearchResultIsCorrectForColumnWithText("Client", "jack");
        VNextBOSearchPanelSteps.clearSearchFilter();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanClickSearchWithEmptyAdvancedSearchFields(String rowID, String description, JSONObject testData) {

        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchValidations.verifyAllElementsDisplayed();
        VNextBOClientsAdvancedSearchSteps.clickSearchButton();
        VNextBOPageSwitcherValidations.verifyItemsPerPageNumberIsCorrect("10");
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCloseAdvancedSearchPopUp(String rowID, String description, JSONObject testData) {

        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchForm advancedSearchForm =
                new VNextBOClientsAdvancedSearchForm();
        VNextBOClientsAdvancedSearchSteps.clickCloseButton();
        VNextBOClientsAdvancedSearchValidations.verifyAdvancedSearchFormIsNotDisplayed(advancedSearchForm);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyAdvancedSearchWindowSavesSearchParameters(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByName("testName");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Name: testName");
        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchValidations.verifyAdvancedSearchFormIsDisplayed();
        VNextBOClientsAdvancedSearchValidations.verifyNameFieldContainsExpectedText("testName");
        VNextBOSearchPanelSteps.clearSearchFilter();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyAdvancedSearchWindowFromActiveAndArchivedTabs(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.openArchivedTab();
        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchValidations.verifyAllElementsDisplayed();
        VNextBOClientsPageSteps.openActiveTab();
        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchValidations.verifyAllElementsDisplayed();
        VNextBOClientsAdvancedSearchSteps.clickCloseButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyAdvancedSearchWindowSavesEnteredValuesWithoutSearching(String rowID, String description, JSONObject testData) {

        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchSteps.setNameField("testName");
        VNextBOClientsAdvancedSearchSteps.setAddressField("testAddress");
        VNextBOClientsAdvancedSearchSteps.setEmailField("testEmail@com");
        VNextBOClientsAdvancedSearchSteps.setPhoneField("03867676767");
        VNextBOClientsAdvancedSearchSteps.clickCloseButton();
        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchValidations.verifyNameFieldContainsExpectedText("testName");
        VNextBOClientsAdvancedSearchValidations.verifyAddressFieldContainsExpectedText("testAddress");
        VNextBOClientsAdvancedSearchValidations.verifyEmailFieldContainsExpectedText("testEmail@com");
        VNextBOClientsAdvancedSearchValidations.verifyPhoneFieldContainsExpectedText("03867676767");
        VNextBOClientsAdvancedSearchSteps.clickCloseButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchByNameNegative(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByName("abracadabra");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Name: abracadabra");
        VNextBOClientsPageValidations.verifyClientsNotFoundMessageIsDisplayed();
        VNextBOSearchPanelSteps.clearSearchFilter();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchByNamePositive(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByName("TEST");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Name: TEST");
        VNextBOClientsPageValidations.verifySearchResultIsCorrectForColumnWithText("Client", "TEST");
        VNextBOSearchPanelSteps.clearSearchFilter();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchByEmail(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByEmail("test@test.com");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Email: test@test.com");
        VNextBOClientsPageValidations.verifySearchResultIsCorrectForColumnWithText("Email", "test@test.com");
        VNextBOSearchPanelSteps.clearSearchFilter();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchByPhone(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByPhone("1111111");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Phone: 1111111");
        VNextBOClientsPageValidations.verifySearchResultIsCorrectForColumnWithText("Phone", "1111111");
        VNextBOSearchPanelSteps.clearSearchFilter();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchByAddress(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByAddress("Hollywood");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Address: Hollywood");
        VNextBOClientsPageValidations.verifySearchResultIsCorrectForColumnWithText("Address", "Hollywood");
        VNextBOSearchPanelSteps.clearSearchFilter();
    }
    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanFillInAllAdvancedSearchFields(String rowID, String description, JSONObject testData) {

        VNextBOSearchPanelSteps.openAdvancedSearchForm();
        VNextBOClientsAdvancedSearchSteps.setNameField("testName");
        VNextBOClientsAdvancedSearchSteps.setAddressField("testAddress");
        VNextBOClientsAdvancedSearchSteps.setEmailField("testEmail@com");
        VNextBOClientsAdvancedSearchSteps.setPhoneField("03867676767");
        VNextBOClientsAdvancedSearchSteps.setTypeDropDownField("Retail");
        VNextBOClientsAdvancedSearchValidations.verifyNameFieldContainsExpectedText("testName");
        VNextBOClientsAdvancedSearchValidations.verifyAddressFieldContainsExpectedText("testAddress");
        VNextBOClientsAdvancedSearchValidations.verifyEmailFieldContainsExpectedText("testEmail@com");
        VNextBOClientsAdvancedSearchValidations.verifyPhoneFieldContainsExpectedText("03867676767");
        VNextBOClientsAdvancedSearchValidations.verifyTypeFieldContainsExpectedText("Retail");
        VNextBOClientsAdvancedSearchSteps.clickCloseButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchByTypeRetail(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByType("Retail");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Client Type: Retail");
        VNextBOClientsPageSteps.getColumnValuesFromColumnWithCheckBoxes("Wholesale");
        VNextBOClientsPageValidations.verifySearchResultIsCorrectForColumnWithCheckboxes("Wholesale", false);
        VNextBOSearchPanelSteps.clearSearchFilter();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSearchByTypeWholesale(String rowID, String description, JSONObject testData) {

        VNextBOClientsPageSteps.searchClientByType("Wholesale");
        VNextBOSearchPanelValidations.verifySearchFilterTextIsCorrect("Client Type: Wholesale");
        VNextBOClientsPageSteps.getColumnValuesFromColumnWithCheckBoxes("Wholesale");
        VNextBOClientsPageValidations.verifySearchResultIsCorrectForColumnWithCheckboxes("Wholesale", true);
        VNextBOSearchPanelSteps.clearSearchFilter();
    }
}