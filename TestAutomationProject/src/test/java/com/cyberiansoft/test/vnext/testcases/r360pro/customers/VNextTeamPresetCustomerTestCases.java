package com.cyberiansoft.test.vnext.testcases.r360pro.customers;

import com.cyberiansoft.test.dataclasses.InspectionData;
import com.cyberiansoft.test.dataclasses.RetailCustomer;
import com.cyberiansoft.test.dataclasses.WholesailCustomer;
import com.cyberiansoft.test.dataclasses.WorkOrderData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnext.data.r360pro.VNextProTestCasesDataPaths;
import com.cyberiansoft.test.vnext.factories.inspectiontypes.InspectionTypes;
import com.cyberiansoft.test.vnext.factories.workordertypes.WorkOrderTypes;
import com.cyberiansoft.test.vnext.interactions.HelpingScreenInteractions;
import com.cyberiansoft.test.vnext.screens.VNextHomeScreen;
import com.cyberiansoft.test.vnext.screens.VNextNewCustomerScreen;
import com.cyberiansoft.test.vnext.screens.customers.VNextCustomersScreen;
import com.cyberiansoft.test.vnext.screens.typeselectionlists.VNextInspectionTypesList;
import com.cyberiansoft.test.vnext.screens.typeselectionlists.VNextWorkOrderTypesList;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextInspectionsScreen;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextWorkOrdersScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVehicleInfoScreen;
import com.cyberiansoft.test.vnext.steps.VehicleInfoScreenSteps;
import com.cyberiansoft.test.vnext.testcases.r360pro.BaseTestCaseTeamEditionRegistration;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VNextTeamPresetCustomerTestCases extends BaseTestCaseTeamEditionRegistration {

    RetailCustomer retailCustomer1 = new RetailCustomer("Preset1", "RetailCustomer1");
    RetailCustomer retailCustomer2 = new RetailCustomer("Preset2", "RetailCustomer2");

    RetailCustomer defaultRetailCustomer = new RetailCustomer("Retail", "");


    @BeforeClass(description="Team Preset Customer Test Cases")
    public void beforeClass() {
        JSONDataProvider.dataFile = VNextProTestCasesDataPaths.getInstance().getPresetCustomerTestCasesDataPath();
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanPresetRetailCustomer(String rowID,
                                                      String description, JSONObject testData) {
        VNextHomeScreen homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextCustomersScreen customersscreen = homescreen.clickCustomersMenuItem();
        customersscreen.switchToRetailMode();
        if (!customersscreen.isCustomerExists(retailCustomer1)) {
            VNextNewCustomerScreen newCustomerScreen = customersscreen.clickAddCustomerButton();
            newCustomerScreen.createNewCustomer(retailCustomer1);
            customersscreen = new VNextCustomersScreen(DriverBuilder.getInstance().getAppiumDriver());
        }
        customersscreen.setCustomerAsDefault(retailCustomer1);

        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanPresetWholesaleCustomer(String rowID,
                                                         String description, JSONObject testData) {
        VNextHomeScreen homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextCustomersScreen customersscreen = homescreen.clickCustomersMenuItem();
        customersscreen.switchToWholesaleMode();
        customersscreen.setCustomerAsDefault(testwholesailcustomer);

        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), testwholesailcustomer.getFullName());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), testwholesailcustomer.getFullName());
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanChangePresetCustomer(String rowID,
                                                      String description, JSONObject testData) {

        List<RetailCustomer> retailCustomers = new ArrayList<RetailCustomer>() {{
            add(retailCustomer1);
            add(retailCustomer2);
        }};

        VNextHomeScreen homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());

        for (RetailCustomer retailCustomer : retailCustomers) {
            VNextCustomersScreen customersscreen = homescreen.clickCustomersMenuItem();
            customersscreen.switchToRetailMode();
            if (!customersscreen.isCustomerExists(retailCustomer)) {
                VNextNewCustomerScreen newCustomerScreen = customersscreen.clickAddCustomerButton();
                newCustomerScreen.createNewCustomer(retailCustomer);
                customersscreen = new VNextCustomersScreen(DriverBuilder.getInstance().getAppiumDriver());
            }
            customersscreen.setCustomerAsDefault(retailCustomer);

            Assert.assertEquals(customersscreen.getDefaultCustomerValue(), retailCustomer.getFullName());
            customersscreen.clickBackButton();
            homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
            Assert.assertEquals(homescreen.getDefaultCustomerValue(), retailCustomer.getFullName());
        }
    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanCreateInspectionWithPresetCustomer(String rowID,
                                                                    String description, JSONObject testData) {

        InspectionData inspectionData = JSonDataParser.getTestDataFromJson(testData, InspectionData.class);

        VNextHomeScreen homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextCustomersScreen customersscreen = homescreen.clickCustomersMenuItem();
        customersscreen.switchToRetailMode();
        if (!customersscreen.isCustomerExists(retailCustomer1)) {
            VNextNewCustomerScreen newCustomerScreen = customersscreen.clickAddCustomerButton();
            newCustomerScreen.createNewCustomer(retailCustomer1);
            customersscreen = new VNextCustomersScreen(DriverBuilder.getInstance().getAppiumDriver());
        }
        customersscreen.setCustomerAsDefault(retailCustomer1);

        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), retailCustomer1.getFullName());

        VNextInspectionsScreen inspectionsScreen = homescreen.clickInspectionsMenuItem();
        VNextInspectionTypesList inspectionTypesList = inspectionsScreen.clickAddInspectionWithPreselectedCustomerButton();
        inspectionTypesList.selectInspectionType(InspectionTypes.O_KRAMAR);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen();
        HelpingScreenInteractions.dismissHelpingScreenIfPresent();
        VehicleInfoScreenSteps.setVehicleInfo(inspectionData.getVehicleInfo());
        final String inspNumber = vehicleInfoScreen.getNewInspectionNumber();
        vehicleInfoScreen.saveInspectionViaMenu();
        Assert.assertEquals(inspectionsScreen.getInspectionCustomerValue(inspNumber), retailCustomer1.getFullName());
        inspectionsScreen.clickBackButton();

    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanCreateWOWithPresetCustomer(String rowID,
                                                                    String description, JSONObject testData) {

        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);

        VNextHomeScreen homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextCustomersScreen customersscreen = homescreen.clickCustomersMenuItem();
        customersscreen.switchToRetailMode();
        if (!customersscreen.isCustomerExists(retailCustomer1)) {
            VNextNewCustomerScreen newCustomerScreen = customersscreen.clickAddCustomerButton();
            newCustomerScreen.createNewCustomer(retailCustomer1);
            customersscreen = new VNextCustomersScreen(DriverBuilder.getInstance().getAppiumDriver());
        }
        customersscreen.setCustomerAsDefault(retailCustomer1);

        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), retailCustomer1.getFullName());

        VNextWorkOrdersScreen workOrdersScreen = homescreen.clickWorkOrdersMenuItem();
        VNextWorkOrderTypesList workOrderTypesList = workOrdersScreen.clickAddWorkOrdernWithPreselectedCustomerButton();
        workOrderTypesList.selectWorkOrderType(WorkOrderTypes.O_KRAMAR);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen();
        HelpingScreenInteractions.dismissHelpingScreenIfPresent();
        VehicleInfoScreenSteps.setVehicleInfo(workOrderData.getVehicleInfoData());
        final String workOrderNumber = vehicleInfoScreen.getNewInspectionNumber();
        vehicleInfoScreen.saveWorkOrderViaMenu();
        Assert.assertEquals(workOrdersScreen.getWorkOrderCustomerValue(workOrderNumber), retailCustomer1.getFullName());
        workOrdersScreen.clickBackButton();

    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyUserCanCancelPresetCustomer(String rowID,
                                                      String description, JSONObject testData) {
        VNextHomeScreen homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextCustomersScreen customersscreen = homescreen.clickCustomersMenuItem();
        customersscreen.switchToRetailMode();
        if (!customersscreen.isCustomerExists(retailCustomer1)) {
            VNextNewCustomerScreen newCustomerScreen = customersscreen.clickAddCustomerButton();
            newCustomerScreen.createNewCustomer(retailCustomer1);
            customersscreen = new VNextCustomersScreen(DriverBuilder.getInstance().getAppiumDriver());
        }
        customersscreen.setCustomerAsDefault(retailCustomer1);

        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        homescreen.clickCustomersMenuItem();
        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        customersscreen.resetPresetCustomer();
        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), defaultRetailCustomer.getFullName().trim());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), defaultRetailCustomer.getFullName().trim());

    }

    @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
    public void testVerifyPresetCustomerCanceledIfUserGoFromRetailToWholesaleCustomerScreen(String rowID,
                                                      String description, JSONObject testData) {

        WholesailCustomer defaultWholesailCustomer = new WholesailCustomer();
        defaultWholesailCustomer.setCompanyName("Wholesale");
        VNextHomeScreen homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        VNextCustomersScreen customersscreen = homescreen.clickCustomersMenuItem();
        customersscreen.switchToRetailMode();
        if (!customersscreen.isCustomerExists(retailCustomer1)) {
            VNextNewCustomerScreen newCustomerScreen = customersscreen.clickAddCustomerButton();
            newCustomerScreen.createNewCustomer(retailCustomer1);
            customersscreen = new VNextCustomersScreen(DriverBuilder.getInstance().getAppiumDriver());
        }
        customersscreen.setCustomerAsDefault(retailCustomer1);

        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), retailCustomer1.getFullName());
        homescreen.clickCustomersMenuItem();

        customersscreen.switchToWholesaleMode();
        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), defaultWholesailCustomer.getFullName());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), defaultWholesailCustomer.getFullName());

        homescreen.clickCustomersMenuItem();
        customersscreen.switchToRetailMode();
        Assert.assertEquals(customersscreen.getDefaultCustomerValue(), defaultRetailCustomer.getFullName().trim());
        customersscreen.clickBackButton();
        homescreen = new VNextHomeScreen(DriverBuilder.getInstance().getAppiumDriver());
        Assert.assertEquals(homescreen.getDefaultCustomerValue(), defaultRetailCustomer.getFullName().trim());
    }
}
