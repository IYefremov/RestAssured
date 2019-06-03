package com.cyberiansoft.test.vnext.testcases.r360free.inspections;

import com.cyberiansoft.test.baseutils.AppiumUtils;
import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeHeaderPanel;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeLoginWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.CompanyWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.ServicePackagesWebPage;
import com.cyberiansoft.test.bo.utils.BackOfficeUtils;
import com.cyberiansoft.test.dataclasses.InspectionData;
import com.cyberiansoft.test.dataclasses.ServiceData;
import com.cyberiansoft.test.dataclasses.VehicleInfoData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.driverutils.WebdriverInicializator;
import com.cyberiansoft.test.vnext.data.r360free.VNextFreeTestCasesDataPaths;
import com.cyberiansoft.test.vnext.enums.ScreenType;
import com.cyberiansoft.test.vnext.screens.*;
import com.cyberiansoft.test.vnext.screens.customers.VNextCustomersScreen;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextInspectionsScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextClaimInfoScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVehicleInfoScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVisualScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVisualServicesScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextAvailableServicesScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextSelectedServicesScreen;
import com.cyberiansoft.test.vnext.testcases.r360free.BaseTestCaseWithDeviceRegistrationAndUserLogin;
import com.cyberiansoft.test.vnext.utils.VNextAlertMessages;
import com.cyberiansoft.test.vnextbo.screens.VNexBOLeftMenuPanel;
import com.cyberiansoft.test.vnextbo.screens.VNextBOInspectionsWebPage;
import com.cyberiansoft.test.vnextbo.screens.VNextBOLoginScreenWebPage;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VNextInspectionServicesTestCases extends BaseTestCaseWithDeviceRegistrationAndUserLogin {

    @BeforeClass(description="R360 Inspection Services Test Cases")
    public void beforeClass() {
        JSONDataProvider.dataFile = VNextFreeTestCasesDataPaths.getInstance().getInspectionServicesTestCasesDataPath();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testShowSelectedServicesAfterInspectionIsSaved(String rowID,
                                                               String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());

        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        final String inspNumber = availableServicesScreen.getNewInspectionNumber();
        inspectionsScreen = availableServicesScreen.saveInspectionViaMenu();
        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        inspectionsScreen = availableServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testShowSelectedServicesForInspectionWhenNavigatingFromServicesScreen(String rowID,
                                                               String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        availableServicesScreen.swipeScreenLeft();
        availableServicesScreen.swipeScreenRight();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        availableServicesScreen.swipeScreenLeft();
        availableServicesScreen.swipeScreenRight();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        inspectionsScreen = availableServicesScreen.saveInspectionViaMenu();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testAddOneServiceToAlreadySelectedServicesWhenInspectionIsEdited(String rowID,
                                                                                      String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());

        availableServicesScreen.selectService(inspectionData.getServiceNameByIndex(0));
        availableServicesScreen.selectService(inspectionData.getServiceNameByIndex(1));
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getServiceNameByIndex(0)));
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getServiceNameByIndex(1)));
        final String inspNumber = selectedServicesScreen.getNewInspectionNumber();
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();

        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getServiceNameByIndex(0)));
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getServiceNameByIndex(1)));

        availableServicesScreen = availableServicesScreen.switchToAvalableServicesView();
        availableServicesScreen.selectService(inspectionData.getServiceNameByIndex(2));
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getServiceNameByIndex(2)));
        inspectionsScreen = availableServicesScreen.saveInspectionViaMenu();
        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());;
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testAddSeveralServicesToAlreadySelectedServicesWhenInspectionIsEdited(String rowID,
                                                                                 String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);
        final int firstPartNumber = 2;

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        for (int i =0; i< firstPartNumber; i++)
            availableServicesScreen.selectService(inspectionData.getServiceNameByIndex(i));
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (int i =0; i< firstPartNumber; i++)
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getServiceNameByIndex(i)));
        final String inspNumber = selectedServicesScreen.getNewInspectionNumber();
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();
        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (int i =0; i< firstPartNumber; i++)
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getServiceNameByIndex(i)));
        availableServicesScreen = availableServicesScreen.switchToAvalableServicesView();
        for (int i =firstPartNumber; i< inspectionData.getServicesList().size(); i++)
            availableServicesScreen.selectService(inspectionData.getServiceNameByIndex(i));
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();
        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifySelectedServicesAreSavedToBO(String rowID,
                                                                                      String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        final String inspNumber = availableServicesScreen.getNewInspectionNumber();
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();
        inspectionsScreen.clickBackButton();
        BaseUtils.waitABit(30000);
        WebDriver
                webdriver = WebdriverInicializator.getInstance().initWebDriver(browsertype);
        webdriver.get(deviceOfficeUrl);
        VNextBOLoginScreenWebPage loginPage = PageFactory.initElements(webdriver,
                VNextBOLoginScreenWebPage.class);
        loginPage.userLogin(deviceuser, devicepsw);
        VNexBOLeftMenuPanel leftMenu = PageFactory.initElements(webdriver,
                VNexBOLeftMenuPanel.class);
        VNextBOInspectionsWebPage inspectionsWebPage = leftMenu.selectInspectionsMenu();
        inspectionsWebPage.selectInspectionInTheList(inspNumber);
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(inspectionsWebPage.isServicePresentForSelectedInspection(service.getServiceName()));
        webdriver.quit();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyLettersIOQAreTrimmedWhileManualEntry(String rowID,
                                                       String description, JSONObject testData) {

        final String vinNumber = "AI0YQ56ONJ";
        final String vinNumberverify = "A0Y56NJ";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(vinNumber);
        Assert.assertEquals(vehicleInfoScreen.getVINFieldValue(), vinNumberverify);
        inspectionsScreen = vehicleInfoScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyNotAllowedCharactersAreTrimmedWhileManualEntry(String rowID,
                                                               String description, JSONObject testData) {

        final String vinNumber = "*90%$2~!$!`\":;\'<>?,./+=_-)(*&^#@\\|";
        final String vinNumberVerify = "902";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(vinNumber);
        Assert.assertEquals(vehicleInfoScreen.getVINFieldValue(), vinNumberVerify);
        inspectionsScreen = vehicleInfoScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyLettersAreCapitalizedWhileManualEntry(String rowID,
                                                                         String description, JSONObject testData) {

        final String vinNumber = "abc458yhgd8bn";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(vinNumber);
        Assert.assertEquals(vehicleInfoScreen.getVINFieldValue(), vinNumber.toUpperCase());
        inspectionsScreen = vehicleInfoScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyServicesAreSavedWhenSaveInspectionOptionWasUsedFromHumburgerMenu(String rowID,
                                                                String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        final String inspNumber = vehicleInfoScreen.getNewInspectionNumber();
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        selectedServicesScreen.saveInspectionViaMenu();

        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();

    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyVehicleClaimAreSavedWhenSaveInspectionOptionWasUsedFromHumburgerMenu(String rowID,
                                                                                           String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());

        final String inspNumber = vehicleInfoScreen.getNewInspectionNumber();
        final VehicleInfoData vehicleInfoData = inspectionData.getVehicleInfo();
        vehicleInfoScreen.setMilage(vehicleInfoData.getMileage());
        vehicleInfoScreen.setLicPlate(vehicleInfoData.getVehicleLicensePlate());
        vehicleInfoScreen.setStockNo(vehicleInfoData.getStockNumber());
        vehicleInfoScreen.setRoNo(vehicleInfoData.getRoNumber());
        vehicleInfoScreen.swipeScreenLeft();

        inspectionsScreen = vehicleInfoScreen.saveInspectionViaMenu();
        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        Assert.assertEquals(vehicleInfoScreen.getVINFieldValue(), vehicleInfoData.getVINNumber());
        Assert.assertEquals(vehicleInfoScreen.getMakeInfo(), vehicleInfoData.getVehicleMake());
        Assert.assertEquals(vehicleInfoScreen.getModelInfo(), vehicleInfoData.getVehicleModel());
        Assert.assertEquals(vehicleInfoScreen.getYear(), vehicleInfoData.getVehicleYear());
        Assert.assertEquals(vehicleInfoScreen.getType(), vehicleInfoData.getVehicleType());
        Assert.assertEquals(vehicleInfoScreen.getMilage(), vehicleInfoData.getMileage());
        Assert.assertEquals(vehicleInfoScreen.getLicPlate(), vehicleInfoData.getVehicleLicensePlate());
        Assert.assertEquals(vehicleInfoScreen.getStockNo(), vehicleInfoData.getStockNumber());
        Assert.assertEquals(vehicleInfoScreen.getRoNo(), vehicleInfoData.getRoNumber());

        vehicleInfoScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testExitCancelInspectionStateCalledFromHumburgerMenu_FirstStep(String rowID,
                                                                                               String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.clickCancelMenuItem();
        VNextInformationDialog informationDialog = new VNextInformationDialog(DriverBuilder.getInstance().getAppiumDriver());
        String msg = informationDialog.clickInformationDialogNoButtonAndGetMessage();
        Assert.assertTrue(msg.contains(VNextAlertMessages.CANCEL_INSPECTION_ALERT));
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.swipeScreenLeft();
        vehicleInfoScreen.clickCancelMenuItem();
        new VNextInformationDialog(DriverBuilder.getInstance().getAppiumDriver());
        AppiumUtils.clickHardwareBackButton();
        AppiumUtils.clickHardwareBackButton();
        AppiumUtils.clickHardwareBackButton();
        informationDialog = new VNextInformationDialog(DriverBuilder.getInstance().getAppiumDriver());
        msg = informationDialog.clickInformationDialogNoButtonAndGetMessage();
        Assert.assertTrue(msg.contains(VNextAlertMessages.CANCEL_INSPECTION_ALERT));
        vehicleInfoScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testShowAllAssignedToServicePackageServicesAsAvailableOnes(String rowID,
                                                                               String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        WebDriver
                webdriver = WebdriverInicializator.getInstance().initWebDriver(browsertype);
        webdriver.get(deviceOfficeUrl);
        BackOfficeLoginWebPage loginPage = PageFactory.initElements(webdriver,
                BackOfficeLoginWebPage.class);
        loginPage.userLogin(deviceuser, devicepsw);
        BackOfficeHeaderPanel backofficeHeader = PageFactory.initElements(webdriver,
                BackOfficeHeaderPanel.class);
        CompanyWebPage companypage = backofficeHeader.clickCompanyLink();
        ServicePackagesWebPage servicepckgspage = companypage.clickServicePackagesLink();
        String mainWindowHandle = webdriver.getWindowHandle();
        servicepckgspage.clickServicesLinkForServicePackage("All Services");
        List<WebElement> allServices = servicepckgspage.getAllServicePackageItems();
        List<String> allServicesTxt = new ArrayList<>();
        for (WebElement lst : allServices)
            allServicesTxt.add(lst.getText());
        servicepckgspage.closeNewTab(mainWindowHandle);
        webdriver.quit();

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        List<WebElement> services = availableServicesScreen.getServicesListItems();
        List<String> servicesTxt = new ArrayList<>();
        for (WebElement lst : services)
            servicesTxt.add(availableServicesScreen.getServiceListItemName(lst));

        for (String srv : allServicesTxt) {
            Assert.assertTrue(servicesTxt.contains(srv));
        }
        AppiumUtils.clickHardwareBackButton();
        vehicleInfoScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyDefaultPriceForServiceIsShownCorrectly(String rowID,
                                                                           String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());

        availableServicesScreen.selectServices(inspectionData.getMoneyServicesList());
        availableServicesScreen.selectServices(inspectionData.getPercentageServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        for (ServiceData moneyService : inspectionData.getMoneyServicesList())
            Assert.assertEquals(selectedServicesScreen.getSelectedServicePriceValue(moneyService.getServiceName()), moneyService.getServicePrice());
        for (ServiceData percService : inspectionData.getPercentageServicesList())
            Assert.assertEquals(selectedServicesScreen.getSelectedServicePriceValue(percService.getServiceName()), percService.getServicePrice());

        selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testAddTheSameServiceMultipleTimes(String rowID,
                                                                 String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);
        final int servicesNumberSelected = 2;

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(service.getServiceName()));
        availableServicesScreen = selectedServicesScreen.switchToAvalableServicesView();
        availableServicesScreen.selectServices(inspectionData.getServicesList());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertEquals(selectedServicesScreen.getQuantityOfSelectedService(service.getServiceName()), servicesNumberSelected);

        final String inspNumber = selectedServicesScreen.getNewInspectionNumber();
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();

        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData service : inspectionData.getServicesList())
            Assert.assertEquals(selectedServicesScreen.getQuantityOfSelectedService(service.getServiceName()), servicesNumberSelected);

        inspectionsScreen = availableServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testEditInspectionServices(String rowID,
                                                   String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);
        final String inspPrice = "$242.00";
        final String firstMoneyServicePrice = "5";
        final String secondMoneyServicePrice = "84.55";
        final String secondMoneyServiceQty = "9.15";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        final String inspNumberber = vehicleInfoScreen.getNewInspectionNumber();
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData serviceData : inspectionData.getServicesList()) {
            selectedServicesScreen.setServiceAmountValue(serviceData.getServiceName(), serviceData.getServicePrice());
        }
        Assert.assertEquals(selectedServicesScreen.getInspectionTotalPriceValue(), inspPrice);
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();

        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumberber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        selectedServicesScreen.uselectService(inspectionData.getServicesList().get(0).getServiceName());

        availableServicesScreen = selectedServicesScreen.switchToAvalableServicesView();
        availableServicesScreen.selectServices(inspectionData.getMoneyServicesList());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        for (ServiceData serviceData : inspectionData.getMoneyServicesList()) {
            selectedServicesScreen.setServiceAmountValue(serviceData.getServiceName(), serviceData.getServicePrice());
        }

        selectedServicesScreen.setServiceAmountValue(inspectionData.getMoneyServicesList().get(0).getServiceName(),
                firstMoneyServicePrice);
        selectedServicesScreen.setServiceAmountValue(inspectionData.getMoneyServicesList().get(1).getServiceName(),
                secondMoneyServicePrice);
        selectedServicesScreen.setServiceQuantityValue(inspectionData.getMoneyServicesList().get(1).getServiceName(),
                secondMoneyServiceQty);

        Assert.assertEquals(selectedServicesScreen.getInspectionTotalPriceValue(),
                BackOfficeUtils.getFormattedServicePriceValue(BackOfficeUtils.getServicePriceValue("$1000.63")));
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyPriceMatrixAddedToServicePackageIsAvailableToChooseWhenAddEditInspection(String rowID,
                                                   String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String matrixservice = "Hail Dent Repair";
        final String[] availablepricematrixes = {"Nationwide Insurance", "Progressive", "State Farm"};
        final String vehiclepartname = "Hood";
        final String vehiclepartsize = "Dime";
        final String vehiclepartseverity = "Light 6 to 15";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextPriceMatrixesScreen priceMatrixesScreen = availableServicesScreen.openMatrixServiceDetails(matrixservice);
        VNextVehiclePartsScreen vehiclePartsScreen = priceMatrixesScreen.selectHailMatrix(availablepricematrixes[2]);
        VNextVehiclePartInfoPage vehiclePartInfoScreen = vehiclePartsScreen.selectVehiclePart(vehiclepartname);
        vehiclePartInfoScreen.selectVehiclePartSize(vehiclepartsize);
        vehiclePartInfoScreen.selectVehiclePartSeverity(vehiclepartseverity);
        vehiclePartInfoScreen.clickSaveVehiclePartInfo();
        vehiclePartsScreen = new VNextVehiclePartsScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen = vehiclePartsScreen.clickVehiclePartsSaveButton();
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(matrixservice));
        Assert.assertEquals(selectedServicesScreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), availablepricematrixes[2]);
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyListOfAvailablePriceMatricesIsLoadedWhenChoosingMatrixService(String rowID,
                                                                                                   String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String matrixservice = "Hail Dent Repair";
        final String[] availablepricematrixes = {"Nationwide Insurance", "Progressive", "State Farm"};
        final String vehiclepartname = "Hood";
        final String vehiclepartsize = "Dime";
        final String vehiclepartseverity = "Light 6 to 15";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextPriceMatrixesScreen priceMatrixesScreen = availableServicesScreen.openMatrixServiceDetails(matrixservice);
        for (String priceMatrix : availablepricematrixes)
            Assert.assertTrue(priceMatrixesScreen.isPriceMatrixExistsInTheList(priceMatrix));
        VNextVehiclePartsScreen vehiclePartsScreen = priceMatrixesScreen.selectHailMatrix(availablepricematrixes[2]);
        VNextVehiclePartInfoPage vehiclePartInfoScreen = vehiclePartsScreen.selectVehiclePart(vehiclepartname);
        vehiclePartInfoScreen.selectVehiclePartSize(vehiclepartsize);
        vehiclePartInfoScreen.selectVehiclePartSeverity(vehiclepartseverity);
        vehiclePartInfoScreen.clickSaveVehiclePartInfo();
        vehiclePartsScreen = new VNextVehiclePartsScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen = vehiclePartsScreen.clickVehiclePartsSaveButton();
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(matrixservice));
        Assert.assertEquals(selectedServicesScreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), availablepricematrixes[2]);
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyPriceMatrixNameIsShownOnSelectServicesScreenAfterSelection(String rowID,
                                                                                        String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String matrixservice = "Hail Dent Repair";
        final String[] availablepricematrixes = {"Nationwide Insurance", "Progressive", "State Farm"};
        final String vehiclepartname = "Hood";
        final String vehiclepartsize = "Dime";
        final String vehiclepartseverity = "Light 6 to 15";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextPriceMatrixesScreen priceMatrixesScreen = availableServicesScreen.openMatrixServiceDetails(matrixservice);
        VNextVehiclePartsScreen vehiclePartsScreen = priceMatrixesScreen.selectHailMatrix(availablepricematrixes[2]);
        VNextVehiclePartInfoPage vehiclePartInfoScreen = vehiclePartsScreen.selectVehiclePart(vehiclepartname);
        vehiclePartInfoScreen.selectVehiclePartSize(vehiclepartsize);
        vehiclePartInfoScreen.selectVehiclePartSeverity(vehiclepartseverity);
        vehiclePartInfoScreen.clickSaveVehiclePartInfo();
        availableServicesScreen = vehiclePartsScreen.clickVehiclePartsSaveButton();
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(matrixservice));
        Assert.assertEquals(selectedServicesScreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), availablepricematrixes[2]);
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyCorrectPriceIsShownInServicesListAfterEditingServicePricePercentage(String rowID,
                                                                                     String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String servicetoselect = "555";
        final String serviceprice = "0.015%";
        final String servicelastsymbol = "8";
        final String newserviceprice = "0.018%";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectService(servicetoselect);
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(servicetoselect));
        Assert.assertEquals(selectedServicesScreen.getSelectedServicePriceValue(servicetoselect), serviceprice);
        selectedServicesScreen.setServiceAmountValue(servicetoselect, servicelastsymbol);
        Assert.assertEquals(selectedServicesScreen.getSelectedServicePriceValue(servicetoselect), newserviceprice);
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testEditServicePriceOnVisualsScreenePercentage(String rowID,
                                                                                              String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String selectdamage = "Price Adjustment";
        final String servicepercentage = "Dent on Body Line";
        final String amount = "55";
        final String amountvalue = "55.000";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.VISUAL);
        VNextVisualScreen visualScreen = new VNextVisualScreen(DriverBuilder.getInstance().getAppiumDriver());
        visualScreen.clickAddServiceButton();
        VNextSelectDamagesScreen selectdamagesscreen = visualScreen.clickOtherServiceOption();
        selectdamagesscreen.selectAllDamagesTab();
        VNextVisualServicesScreen visualservicesscreen = selectdamagesscreen.clickCustomDamageType(selectdamage);
        visualScreen = visualservicesscreen.selectCustomService(servicepercentage);
        visualScreen.clickCarImage();
        BaseUtils.waitABit(1000);
        VNextServiceDetailsScreen servicedetailsscreen = visualScreen.clickCarImageMarker();
        servicedetailsscreen.setServiceAmountValue(amount);
        Assert.assertEquals(servicedetailsscreen.getServiceAmountValue(), amountvalue);
        servicedetailsscreen.clickServiceDetailsDoneButton();
        visualScreen = new VNextVisualScreen(DriverBuilder.getInstance().getAppiumDriver());
        inspectionsScreen = visualScreen.saveInspectionViaMenu();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyCorrectTotalIsShownAfterEditingPercentageService(String rowID,
                                                               String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String moneyservice = "Bug";
        final String selectdamage = "Price Adjustment";
        final String servicepercentage = "Dent on Body Line";
        final String serviceprice = "$222.00";
        final String amount = "50";
        final String amountvalue = "50.000";
        final String inspprice = "$333.00";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectService(moneyservice);
        Assert.assertEquals(availableServicesScreen.getInspectionTotalPriceValue(), serviceprice);
        availableServicesScreen.changeScreen(ScreenType.VISUAL);
        VNextVisualScreen visualScreen = new VNextVisualScreen(DriverBuilder.getInstance().getAppiumDriver());
        visualScreen.clickAddServiceButton();
        VNextSelectDamagesScreen selectdamagesscreen = visualScreen.clickOtherServiceOption();
        selectdamagesscreen.selectAllDamagesTab();
        VNextVisualServicesScreen visualservicesscreen = selectdamagesscreen.clickCustomDamageType(selectdamage);
        visualScreen = visualservicesscreen.selectCustomService(servicepercentage);
        visualScreen.clickCarImage();
        BaseUtils.waitABit(1000);
        VNextServiceDetailsScreen servicedetailsscreen = visualScreen.clickCarImageMarker();
        servicedetailsscreen.setServiceAmountValue(amount);
        Assert.assertEquals(servicedetailsscreen.getServiceAmountValue(), amountvalue);
        servicedetailsscreen.clickServiceDetailsDoneButton();
        visualScreen = new VNextVisualScreen(DriverBuilder.getInstance().getAppiumDriver());
        visualScreen.clickDamageCancelEditingButton();
        Assert.assertEquals(visualScreen.getInspectionTotalPriceValue(), inspprice);
        inspectionsScreen = visualScreen.saveInspectionViaMenu();
        Assert.assertEquals(inspectionsScreen.getFirstInspectionPrice(), inspprice);
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyCorrectPriceIsShownInTotalOnVisualsScreenAfterEditingServicePrice_Money(String rowID,
                                                                           String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String selectdamage = "Miscellaneous";
        final String amount = "999999.99";
        final String inspprice = "$999999.99";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.VISUAL);
        VNextVisualScreen visualScreen = new VNextVisualScreen(DriverBuilder.getInstance().getAppiumDriver());
        visualScreen.clickAddServiceButton();
        visualScreen.clickDefaultDamageType(selectdamage);
        visualScreen.clickCarImage();
        BaseUtils.waitABit(1000);
        VNextServiceDetailsScreen servicedetailsscreen = visualScreen.clickCarImageMarker();
        servicedetailsscreen.setServiceAmountValue(amount);
        Assert.assertEquals(servicedetailsscreen.getServiceAmountValue(), amount);
        servicedetailsscreen.clickServiceDetailsDoneButton();
        visualScreen = new VNextVisualScreen(DriverBuilder.getInstance().getAppiumDriver());
        visualScreen.clickDamageCancelEditingButton();
        Assert.assertEquals(visualScreen.getInspectionTotalPriceValue(), inspprice);
        inspectionsScreen = visualScreen.saveInspectionViaMenu();
        Assert.assertEquals(inspectionsScreen.getFirstInspectionPrice(), inspprice);
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testEditServicePricePercentage(String rowID,
                                                                                                  String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String moneyservice = "Dent Repair";
        final String percentageservice = "Facility Fee";
        final String quantitylastdigit = "8";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());;
        availableServicesScreen.selectService(moneyservice);
        availableServicesScreen.selectService(percentageservice);
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        selectedServicesScreen.setServiceAmountValue(percentageservice, quantitylastdigit);
        Assert.assertEquals(selectedServicesScreen.getSelectedServicePriceValue(percentageservice), "28.000%");
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testEditServicePriceMoney(String rowID,
                                               String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String moneyservice = "Dent Repair";
        final String pricevalue = "3.20";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectService(moneyservice);
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        selectedServicesScreen.setServiceAmountValue(moneyservice, pricevalue);
        Assert.assertEquals(selectedServicesScreen.getSelectedServicePriceValue(moneyservice), BackOfficeUtils.getServicePriceValue(pricevalue));
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testDeleteServiceFromServicesScreen(String rowID,
                                          String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        String inspNumberber = vehicleInfoScreen.getNewInspectionNumber();
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getMoneyServicesList());
        availableServicesScreen.selectServices(inspectionData.getPercentageServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();

        selectedServicesScreen.uselectService(inspectionData.getMoneyServicesList().get(0).getServiceName());
        selectedServicesScreen.uselectService(inspectionData.getPercentageServicesList().get(0).getServiceName());
        inspectionsScreen = selectedServicesScreen.saveInspectionViaMenu();

        vehicleInfoScreen = inspectionsScreen.clickOpenInspectionToEdit(inspNumberber);
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(inspectionData.getMoneyServicesList().get(1).getServiceName()));
        Assert.assertFalse(selectedServicesScreen.isServiceSelected(inspectionData.getMoneyServicesList().get(0).getServiceName()));
        Assert.assertFalse(selectedServicesScreen.isServiceSelected(inspectionData.getPercentageServicesList().get(0).getServiceName()));
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testInspectionTotalPriceShouldChangeWhenUselectSomeOfTheSelectedServiceOnServicesScreen(String rowID,
                                                    String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);
        final String editedPrice = "$10.00";

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getMoneyServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData serviceData : inspectionData.getMoneyServicesList())
            selectedServicesScreen.setServiceAmountValue(serviceData.getServiceName(), serviceData.getServicePrice());
        Assert.assertEquals(selectedServicesScreen.getInspectionTotalPriceValue(), inspectionData.getInspectionPrice());

        for (ServiceData serviceData : inspectionData.getMoneyServicesList())
            selectedServicesScreen.uselectService(serviceData.getServiceName());
        Assert.assertEquals(selectedServicesScreen.getInspectionTotalPriceValue(), editedPrice);
        selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testServicesArentBecameSelectedIfUserUnselectThemBeforeClickingBackButtonOnServicesScreen(String rowID,
                                                                                                        String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen.selectServices(inspectionData.getMoneyServicesList());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (ServiceData serviceData : inspectionData.getMoneyServicesList())
            selectedServicesScreen.uselectService(serviceData.getServiceName());

        for (ServiceData serviceData : inspectionData.getMoneyServicesList())
            Assert.assertFalse(selectedServicesScreen.isServiceSelected(serviceData.getServiceName()));
        selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testTotalIsNotSetTo0IfUserAddsMatrixAdditionalServiceWithNegativePercentageService(String rowID,
                                                                                                          String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String matrixservice = "Hail Repair";
        final String pricematrix = "State Farm";
        final String vehiclepartname = "Hood";
        final String vehiclepartsize = "Dime";
        final String vehiclepartseverity = "Light 6 to 15";
        final String additionalservicename = "Aluminum Upcharge";
        final String additionalservicenprice = "-25";


        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextPriceMatrixesScreen priceMatrixesScreen = availableServicesScreen.openMatrixServiceDetails(matrixservice);
        VNextVehiclePartsScreen vehiclePartsScreen = priceMatrixesScreen.selectHailMatrix(pricematrix);
        VNextVehiclePartInfoPage vehiclePartInfoScreen = vehiclePartsScreen.selectVehiclePart(vehiclepartname);
        vehiclePartInfoScreen.selectVehiclePartSize(vehiclepartsize);
        vehiclePartInfoScreen.selectVehiclePartSeverity(vehiclepartseverity);
        //vehiclePartInfoScreen.selectVehiclePartAdditionalService(additionalservicename);
        VNextServiceDetailsScreen serviceDetailsScreen = vehiclePartInfoScreen.openServiceDetailsScreen(additionalservicename);
        serviceDetailsScreen.setServiceAmountValue(additionalservicenprice);
        serviceDetailsScreen.clickServiceDetailsDoneButton();
        vehiclePartInfoScreen = new VNextVehiclePartInfoPage(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(vehiclePartInfoScreen.getMatrixServiceTotalPriceValue(), inspectionData.getInspectionPrice());
        vehiclePartInfoScreen.clickSaveVehiclePartInfo();
        vehiclePartsScreen = new VNextVehiclePartsScreen(DriverBuilder.getInstance().getAppiumDriver());
        availableServicesScreen = vehiclePartsScreen.clickVehiclePartsSaveButton();
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        Assert.assertTrue(selectedServicesScreen.isServiceSelected(matrixservice));
        Assert.assertEquals(selectedServicesScreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), pricematrix);

        Assert.assertEquals(selectedServicesScreen.getInspectionTotalPriceValue(), inspectionData.getInspectionPrice());
        inspectionsScreen = selectedServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }


    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyServicesAreSavedCorrectlyWhenSavingInspectionFromVisualScreen(String rowID,
                                                                                                   String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        final String[] selectdamages = {"Miscellaneous", "Dent Repair"};
        final String[] selectedservices = {"Prior Damage", "Dent Repair"};

        VNextHomeScreen homeScreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextInspectionsScreen inspectionsScreen = homeScreen.clickInspectionsMenuItem();
        VNextCustomersScreen customersScreen = inspectionsScreen.clickAddInspectionButton();
        customersScreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        vehicleInfoScreen.setVIN(inspectionData.getVinNumber());
        vehicleInfoScreen.changeScreen(ScreenType.CLAIM);
        VNextClaimInfoScreen claimInfoScreen = new VNextClaimInfoScreen(DriverBuilder.getInstance().getAppiumDriver());
        claimInfoScreen.selectInsuranceCompany("Test Insurance Company");
        claimInfoScreen.changeScreen(ScreenType.VISUAL);
        VNextVisualScreen visualScreen = new VNextVisualScreen(DriverBuilder.getInstance().getAppiumDriver());
        for (int i = 0; i < selectdamages.length; i++) {
            visualScreen.clickAddServiceButton();
            visualScreen.clickDefaultDamageType(selectdamages[i]);
            if (i == 0)
                visualScreen.clickCarImage();
            else
                visualScreen.clickCarImageSecondTime();
            BaseUtils.waitABit(1000);
        }
        visualScreen.changeScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextSelectedServicesScreen selectedServicesScreen = availableServicesScreen.switchToSelectedServicesView();
        for (String serviceName : selectedservices) {
            Assert.assertTrue(selectedServicesScreen.isServiceSelected(serviceName));
        }

        inspectionsScreen = availableServicesScreen.cancelInspection();
        inspectionsScreen.clickBackButton();
    }
}