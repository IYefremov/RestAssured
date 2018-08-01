package com.cyberiansoft.test.vnext.testcases;

import com.cyberiansoft.test.dataclasses.*;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.vnext.factories.InspectionTypes;
import com.cyberiansoft.test.vnext.screens.*;
import com.cyberiansoft.test.vnext.screens.panelandparts.VNextLaborServicePanelsList;
import com.cyberiansoft.test.vnext.screens.panelandparts.VNextLaborServicePartsList;
import com.cyberiansoft.test.vnext.screens.typeselectionlists.VNextInspectionTypesList;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextAvailableServicesScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextSelectedServicesScreen;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextInspectionsScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVehicleInfoScreen;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VNextLaborServiceTestCases extends BaseTestCaseTeamEditionRegistration {

    private static final String DATA_FILE = "src/test/java/com/cyberiansoft/test/vnext/data/team-labor-service-testcases-data.json";

    @BeforeClass(description = "Team Inspections Line Approval Test Cases")
    public void settingUp() {
        JSONDataProvider.dataFile = DATA_FILE;
    }

    @AfterClass()
    public void settingDown() {
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanSelectPanelAndPartsForLaborService(String rowID,
                                                                    String description, JSONObject testData) {

        InspectionData inspdata = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
        VNextInspectionsScreen inspectionscreen = homescreen.clickInspectionsMenuItem();
        inspectionscreen.switchToMyInspectionsView();
        VNextCustomersScreen customersscreen = inspectionscreen.clickAddInspectionButton();
        customersscreen.switchToRetailMode();
        customersscreen.selectCustomer(testcustomer);
        VNextInspectionTypesList insptypeslist = new VNextInspectionTypesList(appiumdriver);
        insptypeslist.selectInspectionType(InspectionTypes.O_KRAMAR);
        VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
        vehicleinfoscreen.setVIN(inspdata.getVinNumber());
        final String inspnumber = vehicleinfoscreen.getNewInspectionNumber();
        vehicleinfoscreen.swipeScreensLeft(2);
        VNextAvailableServicesScreen availableservicesscreen = new VNextAvailableServicesScreen(appiumdriver);
        VNextLaborServiceDetailsScreen laborServiceDetailsScreen = availableservicesscreen.
                selectLaborService(inspdata.getLaborServiceData().getServiceName());
        laborServiceDetailsScreen.selectPanelAndPart(inspdata.getLaborServiceData().getLaborServicePanel(),
                inspdata.getLaborServiceData().getLaborServicePart());
        availableservicesscreen = laborServiceDetailsScreen.saveLaborServiceDetails();
        VNextSelectedServicesScreen selectedServicesScreen = availableservicesscreen.switchToSelectedServicesView();
        Assert.assertEquals(selectedServicesScreen.getSelectedServicePriceValue(inspdata.getLaborServiceData().getServiceName()),
                inspdata.getLaborServiceData().getLaborServicePrice());
        inspectionscreen = selectedServicesScreen.saveInspectionViaMenu();
        inspectionscreen.clickBackButton();

    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCantSelectPartWithoutPanel(String rowID,
                                                                    String description, JSONObject testData) {

        InspectionData inspdata = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
        VNextInspectionsScreen inspectionscreen = homescreen.clickInspectionsMenuItem();
        inspectionscreen.switchToMyInspectionsView();
        VNextCustomersScreen customersscreen = inspectionscreen.clickAddInspectionButton();
        customersscreen.switchToRetailMode();
        customersscreen.selectCustomer(testcustomer);
        VNextInspectionTypesList insptypeslist = new VNextInspectionTypesList(appiumdriver);
        insptypeslist.selectInspectionType(InspectionTypes.O_KRAMAR);
        VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
        vehicleinfoscreen.setVIN(inspdata.getVinNumber());
        final String inspnumber = vehicleinfoscreen.getNewInspectionNumber();
        vehicleinfoscreen.swipeScreensLeft(2);
        VNextAvailableServicesScreen availableservicesscreen = new VNextAvailableServicesScreen(appiumdriver);
        VNextLaborServiceDetailsScreen laborServiceDetailsScreen = availableservicesscreen.
                selectLaborService(inspdata.getLaborServiceData().getServiceName());
        VNextLaborServicePanelsList laborServicePanelsList = laborServiceDetailsScreen.clickSelectPanelCell();
        Assert.assertFalse(laborServicePanelsList.isPartsTabEnabled());
        laborServicePanelsList.clickBackButton();
        laborServiceDetailsScreen = new VNextLaborServiceDetailsScreen(appiumdriver);
                laborServiceDetailsScreen.clickScreenBackButton();
        availableservicesscreen = new VNextAvailableServicesScreen(appiumdriver);

        inspectionscreen = availableservicesscreen.saveInspectionViaMenu();
        inspectionscreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserDoesntNeedToSelectPanelWhenAddLaborServiceInMatrix(String rowID,
                                                                                 String description, JSONObject testData) {

        InspectionData inspdata = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
        VNextInspectionsScreen inspectionscreen = homescreen.clickInspectionsMenuItem();
        inspectionscreen.switchToMyInspectionsView();
        VNextCustomersScreen customersscreen = inspectionscreen.clickAddInspectionButton();
        customersscreen.switchToRetailMode();
        customersscreen.selectCustomer(testcustomer);
        VNextInspectionTypesList insptypeslist = new VNextInspectionTypesList(appiumdriver);
        insptypeslist.selectInspectionType(InspectionTypes.O_KRAMAR);
        VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
        vehicleinfoscreen.setVIN(inspdata.getVinNumber());
        final String inspnumber = vehicleinfoscreen.getNewInspectionNumber();
        vehicleinfoscreen.swipeScreensLeft(2);
        VNextAvailableServicesScreen availableservicesscreen = new VNextAvailableServicesScreen(appiumdriver);
        availableservicesscreen.switchToAvalableServicesView();

        VNextVehiclePartsScreen vehiclepartsscreen = availableservicesscreen.openSelectedMatrixServiceDetails( inspdata.getMatrixServiceData().getMatrixServiceName());
        MatrixPartData matrixPartData = inspdata.getMatrixServiceData().getMatrixPartData();
        VNextVehiclePartInfoPage vehiclepartinfoscreen = vehiclepartsscreen.selectVehiclePart(matrixPartData.getMatrixPartName());
        vehiclepartinfoscreen.selectVehiclePartSize(matrixPartData.getPartSize());
        vehiclepartinfoscreen.selectVehiclePartSeverity(matrixPartData.getPartSeverity());

        vehiclepartinfoscreen.selectVehiclePartAdditionalService(matrixPartData.getMatrixAdditionalLaborService().getServiceName());
        VNextLaborServicePartsList laborServicePartsList = vehiclepartinfoscreen.clickSelectPanelsAndPartsForLaborService(matrixPartData.getMatrixAdditionalLaborService());
        Assert.assertTrue(laborServicePartsList.isPartsTabEnabled());
        laborServicePartsList.clickBackButton();
        vehiclepartinfoscreen = new VNextVehiclePartInfoPage(appiumdriver);

        vehiclepartinfoscreen.clickSaveVehiclePartInfo();
        vehiclepartsscreen = new VNextVehiclePartsScreen(appiumdriver);
        availableservicesscreen = vehiclepartsscreen.clickVehiclePartsSaveButton();

        inspectionscreen = vehicleinfoscreen.saveInspectionViaMenu();
        homescreen = inspectionscreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanSavePartServiceInMatrix(String rowID,
                                                                                 String description, JSONObject testData) {

        InspectionData inspdata = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
        VNextInspectionsScreen inspectionscreen = homescreen.clickInspectionsMenuItem();
        inspectionscreen.switchToMyInspectionsView();
        VNextCustomersScreen customersscreen = inspectionscreen.clickAddInspectionButton();
        customersscreen.switchToRetailMode();
        customersscreen.selectCustomer(testcustomer);
        VNextInspectionTypesList insptypeslist = new VNextInspectionTypesList(appiumdriver);
        insptypeslist.selectInspectionType(InspectionTypes.O_KRAMAR3);
        VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
        vehicleinfoscreen.setVIN(inspdata.getVinNumber());
        final String inspnumber = vehicleinfoscreen.getNewInspectionNumber();
        vehicleinfoscreen.swipeScreensLeft(2);
        VNextAvailableServicesScreen availableservicesscreen = new VNextAvailableServicesScreen(appiumdriver);
        availableservicesscreen.switchToAvalableServicesView();

        VNextVehiclePartsScreen vehiclepartsscreen = availableservicesscreen.openSelectedMatrixServiceDetails( inspdata.getMatrixServiceData().getMatrixServiceName());
        MatrixPartData matrixPartData = inspdata.getMatrixServiceData().getMatrixPartData();
        VNextVehiclePartInfoPage vehiclepartinfoscreen = vehiclepartsscreen.selectVehiclePart(matrixPartData.getMatrixPartName());
        vehiclepartinfoscreen.selectVehiclePartSize(matrixPartData.getPartSize());
        vehiclepartinfoscreen.selectVehiclePartSeverity(matrixPartData.getPartSeverity());

        vehiclepartinfoscreen.selectVehiclePartAdditionalService(matrixPartData.getMatrixAdditionalLaborService().getServiceName());
        VNextLaborServicePartsList laborServicePartsList = vehiclepartinfoscreen.clickSelectPanelsAndPartsForLaborService(matrixPartData.getMatrixAdditionalLaborService());
        Assert.assertTrue(laborServicePartsList.isPartsTabEnabled());
        laborServicePartsList.selectServiceLaborPart(matrixPartData.getMatrixAdditionalLaborService().getLaborServicePart());
        vehiclepartinfoscreen = new VNextVehiclePartInfoPage(appiumdriver);
        Assert.assertEquals(vehiclepartinfoscreen.getLaborServiceRate(matrixPartData.getMatrixAdditionalLaborService()),
                matrixPartData.getMatrixAdditionalLaborService().getLaborServiceRate());
        Assert.assertEquals(vehiclepartinfoscreen.getLaborServiceTime(matrixPartData.getMatrixAdditionalLaborService()),
                matrixPartData.getMatrixAdditionalLaborService().getLaborServiceTime());
        Assert.assertEquals(vehiclepartinfoscreen.getLaborServiceNotes(matrixPartData.getMatrixAdditionalLaborService()),
                matrixPartData.getMatrixAdditionalLaborService().getLaborServiceNotes());
        Assert.assertEquals(vehiclepartinfoscreen.getMatrixServiceTotalPriceValue(),
                inspdata.getInspectionPrice());

        vehiclepartinfoscreen.clickSaveVehiclePartInfo();
        vehiclepartsscreen = new VNextVehiclePartsScreen(appiumdriver);
        availableservicesscreen = vehiclepartsscreen.clickVehiclePartsSaveButton();

        inspectionscreen = vehicleinfoscreen.saveInspectionViaMenu();
        homescreen = inspectionscreen.clickBackButton();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanCancelAddPanelAndParts(String rowID,
                                                                    String description, JSONObject testData) {

        InspectionData inspdata = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
        VNextInspectionsScreen inspectionscreen = homescreen.clickInspectionsMenuItem();
        inspectionscreen.switchToMyInspectionsView();
        VNextCustomersScreen customersscreen = inspectionscreen.clickAddInspectionButton();
        customersscreen.switchToRetailMode();
        customersscreen.selectCustomer(testcustomer);
        VNextInspectionTypesList insptypeslist = new VNextInspectionTypesList(appiumdriver);
        insptypeslist.selectInspectionType(InspectionTypes.O_KRAMAR);
        VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
        vehicleinfoscreen.setVIN(inspdata.getVinNumber());
        final String inspnumber = vehicleinfoscreen.getNewInspectionNumber();
        vehicleinfoscreen.swipeScreensLeft(2);
        VNextAvailableServicesScreen availableservicesscreen = new VNextAvailableServicesScreen(appiumdriver);
        VNextLaborServiceDetailsScreen laborServiceDetailsScreen = availableservicesscreen.
                selectLaborService(inspdata.getLaborServiceData().getServiceName());
        VNextLaborServicePanelsList laborServicePanelsList = laborServiceDetailsScreen.clickSelectPanelCell();
        VNextLaborServicePartsList laborServicePartsList = laborServicePanelsList.selectServiceLaborPanel(inspdata.getLaborServiceData().getLaborServicePanel());
        laborServicePartsList.clickBackButton();
        laborServicePanelsList = new VNextLaborServicePanelsList(appiumdriver);
        laborServicePanelsList.clickBackButton();
        laborServiceDetailsScreen = new VNextLaborServiceDetailsScreen(appiumdriver);
        availableservicesscreen = laborServiceDetailsScreen.clickBackButton();
        inspectionscreen = availableservicesscreen.cancelInspection();
        inspectionscreen.clickBackButton();
    }
}