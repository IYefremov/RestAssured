package com.cyberiansoft.test.vnextbo.testcases;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.baseutils.WebDriverUtils;
import com.cyberiansoft.test.bo.testcases.BaseTestCase;
import com.cyberiansoft.test.dataclasses.vNextBO.VNextBOServicesData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.config.VNextBOConfigInfo;
import com.cyberiansoft.test.vnextbo.screens.*;
import com.cyberiansoft.test.vnextbo.utils.VNextPriceCalculations;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VNextBOServicesTestCases extends BaseTestCase {

    private static final String DATA_FILE = "src/test/java/com/cyberiansoft/test/vnextbo/data/VNextBOServicesData.json";

    @BeforeClass
    public void settingUp() {
        JSONDataProvider.dataFile = DATA_FILE;
    }

    private String userName;
    private String userPassword;
    private VNextBOLoginScreenWebPage loginpage;
    private VNexBOLeftMenuPanel leftMenu;

    @BeforeMethod
    public void BackOfficeLogin() {
        browserType = BaseUtils.getBrowserType(VNextBOConfigInfo.getInstance().getDefaultBrowser());
        try {
            DriverBuilder.getInstance().setDriver(browserType);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        webdriver = DriverBuilder.getInstance().getDriver();

        WebDriverUtils.webdriverGotoWebPage(VNextBOConfigInfo.getInstance().getVNextBOURL());
        userName = VNextBOConfigInfo.getInstance().getVNextBONadaMail();
        userPassword = VNextBOConfigInfo.getInstance().getVNextBOPassword();

        loginpage = PageFactory.initElements(webdriver, VNextBOLoginScreenWebPage.class);
        loginpage.userLogin(userName, userPassword);
        leftMenu = PageFactory.initElements(webdriver, VNexBOLeftMenuPanel.class);
    }

    @AfterMethod
    public void BackOfficeLogout() {
        VNextBOHeaderPanel headerpanel = PageFactory.initElements(webdriver,
                VNextBOHeaderPanel.class);
        if (headerpanel.logOutLinkExists())
            headerpanel.userLogout();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testAddMoneyService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName() + data.getServiceEdited())
                .clickAddNewServiceButton()
                .addNewService(data.getPriceServiceName(), data.getServiceType(), data.getServiceDescription(),
                        data.getServicePriceType(), data.getServicePrice())
                .searchServiceByServiceName(data.getPriceServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()));
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testAddPercentageService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName() + data.getServiceEdited());

        VNextBOAddNewServiceDialog addnewservicedialog = servicespage.clickAddNewServiceButton();
        servicespage = addnewservicedialog.addNewPercentageService(data.getPercentageServiceName(),
                data.getServiceType(), data.getPercentageServiceDescription(), data.getServicePercentageType(),
                data.getServicePrice());
        servicespage.searchServiceByServiceName(data.getPercentageServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()));
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testEditMoneyService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName() + data.getServiceEdited())
                .clickAddNewServiceButton()
                .addNewService(data.getPriceServiceName(), data.getServiceType(), data.getServiceDescription(),
                        data.getServicePriceType(), data.getServicePrice())
                .searchServiceByServiceName(data.getPriceServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()));

        VNextBOAddNewServiceDialog addnewservicedialog = servicespage
                .clickEditServiceByServiceName(data.getPriceServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceName(), data.getPriceServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceType(), data.getServiceType());
//        Assert.assertEquals(addnewservicedialog.getServiceDescription(), data.getServiceDescription()); //todo verify getServiceDescription.getText() works
        Assert.assertEquals(addnewservicedialog.getServicePricePercentageValueTxtField().getAttribute("value"),
                VNextPriceCalculations.getPriceRepresentation(data.getServicePrice()));
        Assert.assertTrue(addnewservicedialog.isServicePriceTypeVisible());

        addnewservicedialog.setServiceName(data.getPriceServiceName() + data.getServiceEdited());
        addnewservicedialog.selectServiceType(data.getServiceTypeEdited());
        addnewservicedialog.setServiceDescription(data.getServiceDescription() + data.getServiceEdited());
        addnewservicedialog.setServicePriceValue(data.getServicePriceEdited());
        servicespage = addnewservicedialog.saveNewService();
        servicespage.searchServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName() +
                data.getServiceEdited()));

        Assert.assertEquals(servicespage.getServiceTypeValue(data.getPriceServiceName() +
                data.getServiceEdited()), data.getServiceTypeEdited());
        Assert.assertEquals(servicespage.getServicePriceValue(data.getPriceServiceName() +
                data.getServiceEdited()), VNextPriceCalculations.getPriceRepresentation(data.getServicePriceEdited()));
        Assert.assertEquals(servicespage.getServiceDescriptionValue(data.getPriceServiceName() +
                data.getServiceEdited()), data.getServiceDescription() + data.getServiceEdited());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testRemoveMoneyService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName() + data.getServiceEdited())
                .clickAddNewServiceButton()
                .addNewService(data.getPriceServiceName(), data.getServiceType(), data.getServiceDescription(),
                        data.getServicePriceType(), data.getServicePrice());

        leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()));
        VNextBOAddNewServiceDialog addNewServiceDialog = servicespage
                .clickEditServiceByServiceName(data.getPriceServiceName());
        Assert.assertEquals(addNewServiceDialog.getServiceName(), data.getPriceServiceName());
        Assert.assertEquals(addNewServiceDialog.getServiceType(), data.getServiceType());
//        Assert.assertEquals(addNewServiceDialog.getServiceDescription(), data.getServiceDescription());
        Assert.assertEquals(addNewServiceDialog.getServicePricePercentageValueTxtField().getAttribute("value"),
                VNextPriceCalculations.getPriceRepresentation(data.getServicePrice()));
        Assert.assertTrue(addNewServiceDialog.isServicePriceTypeVisible());

        addNewServiceDialog.setServiceName(data.getPriceServiceName() + data.getServiceEdited());
        String serviceTypeEdited = data.getServiceTypeEdited();
        addNewServiceDialog.selectServiceType(serviceTypeEdited);
        addNewServiceDialog.setServiceDescription(data.getServiceDescription() + data.getServiceEdited());
        addNewServiceDialog.setServicePriceValue(data.getServicePriceEdited());
        servicespage = addNewServiceDialog.clickSaveNewServiceButton();
        servicespage.searchServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName() +
                data.getServiceEdited()));

        Assert.assertEquals(servicespage.getServiceTypeValue(data.getPriceServiceName() +
                data.getServiceEdited()), serviceTypeEdited);
        System.out.println(servicespage.getServicePriceValue(data.getPriceServiceName() + data.getServiceEdited()));
        System.out.println(data.getServicePriceEdited());
        Assert.assertEquals(servicespage.getServicePriceValue(data.getPriceServiceName() + data.getServiceEdited()),
                VNextPriceCalculations.getPriceRepresentation(data.getServicePriceEdited()));
        Assert.assertEquals(servicespage.getServiceDescriptionValue(data.getPriceServiceName() +
                data.getServiceEdited()), data.getServiceDescription() + data.getServiceEdited());

        leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()
                + data.getServiceEdited()));
        servicespage.deleteServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertFalse(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()
                + data.getServiceEdited()));
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testEditPercentageService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName() + data.getServiceEdited());

        VNextBOAddNewServiceDialog addnewservicedialog = servicespage.clickAddNewServiceButton();
        servicespage = addnewservicedialog.addNewPercentageService(data.getPercentageServiceName(),
                data.getServiceType(), data.getPercentageServiceDescription(), data.getServicePercentageType(),
                data.getServicePrice());
        servicespage.searchServiceByServiceName(data.getPercentageServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()));

        addnewservicedialog = servicespage
                .clickEditServiceByServiceName(data.getPercentageServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceName(), data.getPercentageServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceType(), data.getServiceType());
//        Assert.assertEquals(addnewservicedialog.getServiceDescription(), data.getPercentageServiceDescription());
        Assert.assertEquals(addnewservicedialog.getServicePricePercentageValueTxtField().getAttribute("value"),
                VNextPriceCalculations.getPercentageRepresentation(data.getServicePrice()).replace("%", ""));
        Assert.assertTrue(addnewservicedialog.isServicePriceTypeVisible());

        addnewservicedialog.setServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        addnewservicedialog.selectServiceType(data.getServiceTypeEdited());
        addnewservicedialog.setServiceDescription(data.getPercentageServiceDescription()
                + data.getServiceEdited());
        addnewservicedialog.setServicePercentageValue(data.getServicePriceEdited());
        servicespage = addnewservicedialog.saveNewService();
        servicespage.searchServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));

        Assert.assertEquals(servicespage.getServiceTypeValue(data.getPercentageServiceName()
                + data.getServiceEdited()), data.getServiceTypeEdited());
        Assert.assertEquals(servicespage.getServicePriceValue(data.getPercentageServiceName()
                + data.getServiceEdited()), VNextPriceCalculations.getPercentageRepresentation(data.getServicePriceEdited()));
        Assert.assertEquals(servicespage.getServiceDescriptionValue(data.getPercentageServiceName()
                + data.getServiceEdited()), data.getPercentageServiceDescription() + data.getServiceEdited());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testRemovePercentageService(String rowID, String description, JSONObject testData) {

        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);
        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName() + data.getServiceEdited());

        VNextBOAddNewServiceDialog addnewservicedialog = servicespage.clickAddNewServiceButton();
        servicespage = addnewservicedialog.addNewPercentageService(data.getPercentageServiceName(),
                data.getServiceType(), data.getPercentageServiceDescription(), data.getServicePercentageType(),
                data.getServicePrice());
        servicespage.searchServiceByServiceName(data.getPercentageServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()));

        addnewservicedialog = servicespage
                .clickEditServiceByServiceName(data.getPercentageServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceName(), data.getPercentageServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceType(), data.getServiceType());
//        Assert.assertEquals(addnewservicedialog.getServiceDescription(), data.getPercentageServiceDescription());
        Assert.assertEquals(addnewservicedialog.getServicePricePercentageValueTxtField().getAttribute("value"),
                VNextPriceCalculations.getPercentageRepresentation(data.getServicePrice()).replace("%", ""));
        Assert.assertTrue(addnewservicedialog.isServicePriceTypeVisible());

        addnewservicedialog.setServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        addnewservicedialog.selectServiceType(data.getServiceTypeEdited());
        addnewservicedialog.setServiceDescription(data.getPercentageServiceDescription()
                + data.getServiceEdited());
        addnewservicedialog.setServicePercentageValue(data.getServicePriceEdited());
        servicespage = addnewservicedialog.saveNewService();
        servicespage.searchServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));

        Assert.assertEquals(servicespage.getServiceTypeValue(data.getPercentageServiceName()
                + data.getServiceEdited()), data.getServiceTypeEdited());
        Assert.assertEquals(servicespage.getServicePriceValue(data.getPercentageServiceName()
                + data.getServiceEdited()), VNextPriceCalculations.getPercentageRepresentation(data.getServicePriceEdited()));
        Assert.assertEquals(servicespage.getServiceDescriptionValue(data.getPercentageServiceName()
                + data.getServiceEdited()), data.getPercentageServiceDescription() + data.getServiceEdited());
        leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));
        servicespage.deleteServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertFalse(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));
        }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testResumeRemovedMoneyService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName())
                .deleteServiceIfPresent(data.getPriceServiceName() + data.getServiceEdited())
                .clickAddNewServiceButton()
                .addNewService(data.getPriceServiceName(), data.getServiceType(), data.getServiceDescription(),
                        data.getServicePriceType(), data.getServicePrice());

        leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()));
        VNextBOAddNewServiceDialog addNewServiceDialog = servicespage
                .clickEditServiceByServiceName(data.getPriceServiceName());
        Assert.assertEquals(addNewServiceDialog.getServiceName(), data.getPriceServiceName());
        Assert.assertEquals(addNewServiceDialog.getServiceType(), data.getServiceType());
//        Assert.assertEquals(addNewServiceDialog.getServiceDescription(), data.getServiceDescription());
        Assert.assertEquals(addNewServiceDialog.getServicePricePercentageValueTxtField().getAttribute("value"),
                VNextPriceCalculations.getPriceRepresentation(data.getServicePrice()));
        Assert.assertTrue(addNewServiceDialog.isServicePriceTypeVisible());

        addNewServiceDialog.setServiceName(data.getPriceServiceName() + data.getServiceEdited());
        String serviceTypeEdited = data.getServiceTypeEdited();
        addNewServiceDialog.selectServiceType(serviceTypeEdited);
        addNewServiceDialog.setServiceDescription(data.getServiceDescription() + data.getServiceEdited());
        addNewServiceDialog.setServicePriceValue(data.getServicePriceEdited());
        servicespage = addNewServiceDialog.clickSaveNewServiceButton();
        servicespage.searchServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName() +
                data.getServiceEdited()));

        Assert.assertEquals(servicespage.getServiceTypeValue(data.getPriceServiceName() +
                data.getServiceEdited()), serviceTypeEdited);
        System.out.println(servicespage.getServicePriceValue(data.getPriceServiceName() + data.getServiceEdited()));
        System.out.println(data.getServicePriceEdited());
        Assert.assertEquals(servicespage.getServicePriceValue(data.getPriceServiceName() + data.getServiceEdited()),
                VNextPriceCalculations.getPriceRepresentation(data.getServicePriceEdited()));
        Assert.assertEquals(servicespage.getServiceDescriptionValue(data.getPriceServiceName() +
                data.getServiceEdited()), data.getServiceDescription() + data.getServiceEdited());

        leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()
                + data.getServiceEdited()));
        servicespage.deleteServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertFalse(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()
                + data.getServiceEdited()));

        leftMenu.selectServicesMenu();
        servicespage.advancedSearchService(data.getPriceServiceName() + data.getServiceEdited(), false);
        VNextConfirmationDialog confirmdialog = servicespage
                .clickUnarchiveButtonForService(data.getPriceServiceName() + data.getServiceEdited());
        Assert.assertEquals(confirmdialog.clickNoAndGetConfirmationDialogMessage(),
                "Are you sure you want to restore \"" + data.getPriceServiceName() + data.getServiceEdited()
                        + "\" service?");
        servicespage.unarchiveServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
        servicespage.advancedSearchService(data.getPriceServiceName() + data.getServiceEdited(), true);
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPriceServiceName()
                + data.getServiceEdited()));
        servicespage.deleteServiceByServiceName(data.getPriceServiceName() + data.getServiceEdited());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testResumeRemovedPercentageService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);
        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName())
                .deleteServiceIfPresent(data.getPercentageServiceName() + data.getServiceEdited());

        VNextBOAddNewServiceDialog addnewservicedialog = servicespage.clickAddNewServiceButton();
        servicespage = addnewservicedialog.addNewPercentageService(data.getPercentageServiceName(),
                data.getServiceType(), data.getPercentageServiceDescription(), data.getServicePercentageType(),
                data.getServicePrice());
        servicespage.searchServiceByServiceName(data.getPercentageServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()));

        addnewservicedialog = servicespage.clickEditServiceByServiceName(data.getPercentageServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceName(), data.getPercentageServiceName());
        Assert.assertEquals(addnewservicedialog.getServiceType(), data.getServiceType());
//        Assert.assertEquals(addnewservicedialog.getServiceDescription(), data.getPercentageServiceDescription());
        Assert.assertEquals(addnewservicedialog.getServicePricePercentageValueTxtField().getAttribute("value"),
                VNextPriceCalculations.getPercentageRepresentation(data.getServicePrice()).replace("%", ""));
        Assert.assertTrue(addnewservicedialog.isServicePriceTypeVisible());

        addnewservicedialog.setServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        addnewservicedialog.selectServiceType(data.getServiceTypeEdited());
        addnewservicedialog.setServiceDescription(data.getPercentageServiceDescription()
                + data.getServiceEdited());
        addnewservicedialog.setServicePercentageValue(data.getServicePriceEdited());
        servicespage = addnewservicedialog.saveNewService();
        servicespage.searchServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));

        Assert.assertEquals(servicespage.getServiceTypeValue(data.getPercentageServiceName()
                + data.getServiceEdited()), data.getServiceTypeEdited());
        Assert.assertEquals(servicespage.getServicePriceValue(data.getPercentageServiceName()
                + data.getServiceEdited()), VNextPriceCalculations.getPercentageRepresentation(data.getServicePriceEdited()));
        Assert.assertEquals(servicespage.getServiceDescriptionValue(data.getPercentageServiceName()
                + data.getServiceEdited()), data.getPercentageServiceDescription() + data.getServiceEdited());
        leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));
        servicespage.deleteServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertFalse(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));

        servicespage = leftMenu.selectServicesMenu();
        servicespage.advancedSearchService(data.getPercentageServiceName()
                + data.getServiceEdited(), false);
        VNextConfirmationDialog confirmdialog = servicespage
                .clickUnarchiveButtonForService(data.getPercentageServiceName() + data.getServiceEdited());
        Assert.assertEquals(confirmdialog.clickNoAndGetConfirmationDialogMessage(),
                "Are you sure you want to restore \"" + data.getPercentageServiceName()
                        + data.getServiceEdited() + "\" service?");
        servicespage.unarchiveServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
        servicespage.advancedSearchService(data.getPercentageServiceName()
                + data.getServiceEdited(), true);
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getPercentageServiceName()
                + data.getServiceEdited()));
        servicespage.deleteServiceByServiceName(data.getPercentageServiceName() + data.getServiceEdited());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testVerifyErrorMessagesOnCreateEditServiceDialog(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu.selectServicesMenu();
        VNextBOAddNewServiceDialog addnewservicedialog = servicespage.clickAddNewServiceButton();
        addnewservicedialog.clickServiceAddButton();
        Assert.assertEquals(addnewservicedialog.getErrorMessage(), "Service name is required!");
        addnewservicedialog.setServiceName(data.getEmptyServiceName());
        addnewservicedialog.clickServiceAddButton();
        Assert.assertEquals(addnewservicedialog.getErrorMessage(), "Service name is required!");
        addnewservicedialog.setServiceName(data.getServiceName());
        addnewservicedialog.saveNewService();
        servicespage.searchServiceByServiceName(data.getServiceName());
        addnewservicedialog = servicespage.clickEditServiceByServiceName(data.getServiceName());
        addnewservicedialog.setServiceName(data.getEmptyServiceName());
        addnewservicedialog.clickServiceAddButton();
        Assert.assertEquals(addnewservicedialog.getErrorMessage(), "Service name is required!");
        servicespage = addnewservicedialog.closeNewServiceDialog();
        servicespage.deleteServiceByServiceName(data.getServiceName());
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testEditMatrixService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicespage = leftMenu
                .selectServicesMenu()
                .advancedSearchServiceByServiceType(data.getMatrixServiceType());
        String firstServiceNameInTableRow = servicespage.getFirstServiceNameInTableRow();
        servicespage
                .clickEditServiceByServiceName(firstServiceNameInTableRow)
                .setServiceName(data.getNewMatrixServiceName())
                .setServiceDescription(data.getServiceDescription())
                .saveNewService()
                .searchServiceByServiceName(data.getNewMatrixServiceName());
        Assert.assertTrue(servicespage.isServicePresentOnCurrentPageByServiceName(data.getNewMatrixServiceName()));
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanAddLaborPriceService(String rowID, String description, JSONObject testData) {
        VNextBOServicesData data = JSonDataParser.getTestDataFromJson(testData, VNextBOServicesData.class);

        VNextBOServicesWebPage servicesPage = leftMenu
                .selectServicesMenu()
                .searchServiceByServiceName(data.getServiceName())
                .deleteServiceIfPresent(data.getServiceName())
                .clickAddNewServiceButton()
                .setServiceName(data.getServiceName())
                .setServiceDescription(data.getServiceDescription())
                .selectServicePriceType(data.getServicePriceType())
                .setServicePriceValue(data.getServicePrice())
                .clickSaveNewServiceButton()
                .searchServiceByServiceName(data.getServiceName());
        Assert.assertTrue(servicesPage.isServicePresentOnCurrentPageByServiceName(data.getServiceName()));
    }
}