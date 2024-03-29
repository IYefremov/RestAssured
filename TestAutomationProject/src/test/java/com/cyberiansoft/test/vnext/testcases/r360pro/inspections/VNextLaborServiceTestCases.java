package com.cyberiansoft.test.vnext.testcases.r360pro.inspections;

import com.cyberiansoft.test.dataclasses.InspectionData;
import com.cyberiansoft.test.dataclasses.VehiclePartData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.driverutils.ChromeDriverProvider;
import com.cyberiansoft.test.vnext.data.r360pro.VNextProTestCasesDataPaths;
import com.cyberiansoft.test.vnext.enums.ScreenType;
import com.cyberiansoft.test.vnext.factories.inspectiontypes.InspectionTypes;
import com.cyberiansoft.test.vnext.screens.VNextServiceDetailsScreen;
import com.cyberiansoft.test.vnext.screens.VNextVehiclePartInfoScreen;
import com.cyberiansoft.test.vnext.screens.VNextVehiclePartsScreen;
import com.cyberiansoft.test.vnext.screens.panelandparts.VNextLaborServicePanelsList;
import com.cyberiansoft.test.vnext.screens.panelandparts.VNextLaborServicePartsList;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextAvailableServicesScreen;
import com.cyberiansoft.test.vnext.steps.HomeScreenSteps;
import com.cyberiansoft.test.vnext.steps.InspectionSteps;
import com.cyberiansoft.test.vnext.steps.ScreenNavigationSteps;
import com.cyberiansoft.test.vnext.steps.WizardScreenSteps;
import com.cyberiansoft.test.vnext.steps.services.AvailableServicesScreenSteps;
import com.cyberiansoft.test.vnext.steps.services.SelectedServicesScreenSteps;
import com.cyberiansoft.test.vnext.testcases.r360pro.BaseTestClass;
import com.cyberiansoft.test.vnext.validations.SelectedServicesScreenValidations;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VNextLaborServiceTestCases extends BaseTestClass {

    @BeforeClass(description = "Team Labor Service Test Cases")
    public void settingUp() {
        JSONDataProvider.dataFile = VNextProTestCasesDataPaths.getInstance().getLaborServiceTestCasesDataPath();
    }

    @AfterClass()
    public void settingDown() {
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testVerifyUserCanSelectPanelAndPartsForLaborService(String rowID,
                                                                    String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        HomeScreenSteps.openCreateMyInspection();
        InspectionSteps.createInspection(testcustomer, InspectionTypes.O_KRAMAR, inspectionData);

        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen();
        AvailableServicesScreenSteps.openServiceDetails(inspectionData.getLaborServiceData().getServiceName());
        VNextServiceDetailsScreen serviceDetailsScreen = new VNextServiceDetailsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        serviceDetailsScreen.clickSelectPanelsAndParts();
        VNextLaborServicePanelsList laborServicePanelsList = new VNextLaborServicePanelsList(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        VNextLaborServicePartsList laborServicePartsList = laborServicePanelsList.selectServiceLaborPanel(inspectionData.getLaborServiceData().getLaborServicePanel());
        laborServicePartsList.selectServiceLaborPart(inspectionData.getLaborServiceData().getLaborServicePart());
        laborServicePartsList.saveLaborServiceParts();
        serviceDetailsScreen.clickServiceDetailsDoneButton();
        SelectedServicesScreenSteps.switchToSelectedService();
        SelectedServicesScreenValidations.validateSelectedServicePrice(inspectionData.getLaborServiceData().getServiceName(),
                inspectionData.getLaborServiceData().getLaborServicePrice());
        InspectionSteps.saveInspection();
        ScreenNavigationSteps.pressBackButton();

    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testVerifyUserCantSelectPartWithoutPanel(String rowID,
                                                         String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        HomeScreenSteps.openCreateMyInspection();
        InspectionSteps.createInspection(testcustomer, InspectionTypes.O_KRAMAR, inspectionData);

        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);
        AvailableServicesScreenSteps.openServiceDetails(inspectionData.getLaborServiceData().getServiceName());
        VNextServiceDetailsScreen serviceDetailsScreen = new VNextServiceDetailsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        serviceDetailsScreen.clickSelectPanelsAndParts();
        VNextLaborServicePanelsList laborServicePanelsList = new VNextLaborServicePanelsList(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertFalse(laborServicePanelsList.isPartsTabEnabled());
        ScreenNavigationSteps.pressBackButton();
        InspectionSteps.saveInspection();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testVerifyUserDoesntNeedToSelectPanelWhenAddLaborServiceInMatrix(String rowID,
                                                                                 String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        HomeScreenSteps.openCreateMyInspection();
        InspectionSteps.createInspection(testcustomer, InspectionTypes.O_KRAMAR, inspectionData);

        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);
        AvailableServicesScreenSteps.selectMatrixService(inspectionData.getMatrixServiceData());
        VehiclePartData vehiclePartData = inspectionData.getMatrixServiceData().getVehiclePartData();
        VNextVehiclePartsScreen priceMatrixesScreen = new VNextVehiclePartsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        priceMatrixesScreen.selectVehiclePart(vehiclePartData.getVehiclePartName());
        VNextVehiclePartInfoScreen vehiclePartInfoScreen = new VNextVehiclePartInfoScreen();
        vehiclePartInfoScreen.selectVehiclePartSize(vehiclePartData.getVehiclePartSize());
        vehiclePartInfoScreen.selectVehiclePartSeverity(vehiclePartData.getVehiclePartSeverity());

        vehiclePartInfoScreen.openVehiclePartLaborServiceDetails(vehiclePartData.getMatrixAdditionalLaborService().getServiceName());
        VNextServiceDetailsScreen serviceDetailsScreen = new VNextServiceDetailsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        serviceDetailsScreen.clickSelectPanelsAndParts();
        VNextLaborServicePartsList laborServicePartsList = new VNextLaborServicePartsList(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertTrue(laborServicePartsList.isPartsTabEnabled());
        laborServicePartsList.clickBackButton();
        vehiclePartInfoScreen.clickScreenBackButton();
        serviceDetailsScreen.clickScreenBackButton();
        vehiclePartInfoScreen.clickSaveVehiclePartInfo();

        InspectionSteps.saveInspection();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testVerifyUserCanSavePartServiceInMatrix(String rowID,
                                                         String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        HomeScreenSteps.openCreateMyInspection();
        InspectionSteps.createInspection(testcustomer, InspectionTypes.O_KRAMAR3, inspectionData);
        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);

        AvailableServicesScreenSteps.selectMatrixService(inspectionData.getMatrixServiceData());
        VNextVehiclePartsScreen priceMatrixesScreen = new VNextVehiclePartsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        VehiclePartData vehiclePartData = inspectionData.getMatrixServiceData().getVehiclePartData();
        priceMatrixesScreen.selectVehiclePart(vehiclePartData.getVehiclePartName());
        VNextVehiclePartInfoScreen vehiclePartInfoScreen = new VNextVehiclePartInfoScreen();
        vehiclePartInfoScreen.selectVehiclePartSize(vehiclePartData.getVehiclePartSize());
        vehiclePartInfoScreen.selectVehiclePartSeverity(vehiclePartData.getVehiclePartSeverity());

        vehiclePartInfoScreen.openVehiclePartLaborServiceDetails(vehiclePartData.getMatrixAdditionalLaborService().getServiceName());
        VNextServiceDetailsScreen serviceDetailsScreen = new VNextServiceDetailsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        serviceDetailsScreen.clickSelectPanelsAndParts();
        VNextLaborServicePartsList laborServicePartsList = new VNextLaborServicePartsList(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        Assert.assertTrue(laborServicePartsList.isPartsTabEnabled());
        laborServicePartsList.selectServiceLaborPart(vehiclePartData.getMatrixAdditionalLaborService().getLaborServicePart());
        laborServicePartsList.saveLaborServiceParts();
        serviceDetailsScreen.clickServiceDetailsDoneButton();
        vehiclePartInfoScreen.switchToSelectedServicesView();
        vehiclePartInfoScreen.expandSelectedServiceDetails(vehiclePartData.getMatrixAdditionalLaborService().getServiceName());
        Assert.assertEquals(serviceDetailsScreen.getServiceAmountValue(), vehiclePartData.getMatrixAdditionalLaborService().getLaborServiceRate());
        Assert.assertEquals(serviceDetailsScreen.getServiceQuantityValue(),
                vehiclePartData.getMatrixAdditionalLaborService().getLaborServiceTime());
        Assert.assertEquals(serviceDetailsScreen.getServiceNotesValue(),
                vehiclePartData.getMatrixAdditionalLaborService().getLaborServiceNotes());
        serviceDetailsScreen.clickServiceDetailsDoneButton();
        Assert.assertEquals(vehiclePartInfoScreen.getMatrixServiceTotalPriceValue(),
                inspectionData.getInspectionPrice());
        vehiclePartInfoScreen.clickScreenBackButton();
        vehiclePartInfoScreen.clickSaveVehiclePartInfo();

        InspectionSteps.saveInspection();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void testVerifyUserCanCancelAddPanelAndParts(String rowID,
                                                        String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        HomeScreenSteps.openCreateMyInspection();
        InspectionSteps.createInspection(testcustomer, InspectionTypes.O_KRAMAR, inspectionData);

        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);
        AvailableServicesScreenSteps.openServiceDetails(inspectionData.getLaborServiceData().getServiceName());
        VNextServiceDetailsScreen serviceDetailsScreen = new VNextServiceDetailsScreen(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        serviceDetailsScreen.clickSelectPanelsAndParts();
        VNextLaborServicePanelsList laborServicePanelsList = new VNextLaborServicePanelsList(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        VNextLaborServicePartsList laborServicePartsList = laborServicePanelsList.selectServiceLaborPanel(inspectionData.getLaborServiceData().getLaborServicePanel());
        laborServicePartsList.clickBackButton();
        laborServicePanelsList = new VNextLaborServicePanelsList(ChromeDriverProvider.INSTANCE.getMobileChromeDriver());
        laborServicePanelsList.clickBackButton();
        serviceDetailsScreen.clickScreenBackButton();
        InspectionSteps.saveInspection();
        ScreenNavigationSteps.pressBackButton();
    }
}