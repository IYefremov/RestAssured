package com.cyberiansoft.test.vnext.testcases.r360pro.monitoring.timetracking;

import com.cyberiansoft.test.baseutils.MonitoringDataUtils;
import com.cyberiansoft.test.dataclasses.Employee;
import com.cyberiansoft.test.dataclasses.ServiceData;
import com.cyberiansoft.test.dataclasses.WorkOrderData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.enums.MenuItems;
import com.cyberiansoft.test.vnext.data.r360pro.VNextProTestCasesDataPaths;
import com.cyberiansoft.test.vnext.dto.OrderPhaseDto;
import com.cyberiansoft.test.vnext.enums.ScreenType;
import com.cyberiansoft.test.vnext.factories.inspectiontypes.InspectionTypes;
import com.cyberiansoft.test.vnext.factories.workordertypes.WorkOrderTypes;
import com.cyberiansoft.test.vnext.steps.*;
import com.cyberiansoft.test.vnext.steps.monitoring.EditOrderSteps;
import com.cyberiansoft.test.vnext.steps.monitoring.MonitorSteps;
import com.cyberiansoft.test.vnext.steps.monitoring.ProblemReportingSteps;
import com.cyberiansoft.test.vnext.steps.services.AvailableServicesScreenSteps;
import com.cyberiansoft.test.vnext.testcases.r360pro.BaseTestClass;
import com.cyberiansoft.test.vnext.validations.MenuValidations;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VNextTeamMonitoringTimetrackingVisibility extends BaseTestClass {
    private String inspectionId = "";
    private String workOrderId = "";

    @BeforeClass(description = "Team Monitoring Basic Flow Test")
    public void beforeClass() {
        JSONDataProvider.dataFile = VNextProTestCasesDataPaths.getInstance().getMonitoringBaseCaseDataPath();
        HomeScreenSteps.openCreateMyInspection();
        InspectionSteps.createInspection(testcustomer, InspectionTypes.O_KRAMAR);
        inspectionId = InspectionSteps.saveInspection();
        InspectionSteps.openInspectionMenu(inspectionId);
        InspectionMenuSteps.approveInspection();
        InspectionSteps.openInspectionMenu(inspectionId);
        InspectionMenuSteps.selectCreateWorkOrder();
        WorkOrderSteps.createWorkOrder(WorkOrderTypes.AUTOMATION_MONITORING);
        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);
        AvailableServicesScreenSteps.selectServices(MonitoringDataUtils.getTestSerivceData());
        workOrderId = WorkOrderSteps.saveWorkOrder();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void nonLocationManagerCannotStartWO(String rowID,
                                                String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        ServiceData serviceDto = workOrderData.getServiceData();
        Employee nonLocationManagerEmployee = new Employee();
        nonLocationManagerEmployee.setEmployeeFirstName("Test");
        nonLocationManagerEmployee.setEmployeeLastName("Test");

        HomeScreenSteps.logOut();
        GeneralSteps.logIn(nonLocationManagerEmployee);
        MonitorSteps.editOrder(workOrderId);
        MenuSteps.selectMenuItem(MenuItems.EDIT);
        EditOrderSteps.openServiceMenu(serviceDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.START, true);
        MenuSteps.closeMenu();
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
        HomeScreenSteps.logOut();
        GeneralSteps.logIn(employee);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, dependsOnMethods = "nonLocationManagerCannotStartWO")
    public void anotherLocationManagerCanSeeStartOnStartedService(String rowID,
                                                                  String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        ServiceData serviceDto = workOrderData.getServiceData();
        OrderPhaseDto phaseDto = workOrderData.getMonitoring().getOrderPhaseDto();
        Employee locationManagerEmployee = new Employee();
        locationManagerEmployee.setEmployeeFirstName("Test");
        locationManagerEmployee.setEmployeeLastName("Test");

        MonitorSteps.editOrder(workOrderId);
        MenuSteps.selectMenuItem(MenuItems.EDIT);
        EditOrderSteps.openServiceMenu(serviceDto);
        MenuSteps.selectMenuItem(MenuItems.START);
        GeneralSteps.confirmDialog();
        EditOrderSteps.openPhaseMenu(phaseDto);
        MenuSteps.selectMenuItem(MenuItems.START);
        GeneralSteps.confirmDialog();
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
        HomeScreenSteps.logOut();
        GeneralSteps.logIn(locationManagerEmployee);
        MonitorSteps.editOrder(workOrderId);
        MenuSteps.selectMenuItem(MenuItems.EDIT);
        EditOrderSteps.openServiceMenu(serviceDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.START, true);
        MenuSteps.closeMenu();
        EditOrderSteps.openPhaseMenu(phaseDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.START, true);
        MenuSteps.closeMenu();
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
        HomeScreenSteps.logOut();
        GeneralSteps.logIn(employee);
        MonitorSteps.editOrder(workOrderId);
        MenuSteps.selectMenuItem(MenuItems.EDIT);
        EditOrderSteps.openServiceMenu(serviceDto);
        MenuSteps.selectMenuItem(MenuItems.STOP);
        GeneralSteps.confirmDialog();
        EditOrderSteps.openPhaseMenu(phaseDto);
        MenuSteps.selectMenuItem(MenuItems.STOP);
        GeneralSteps.confirmDialog();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, dependsOnMethods = "anotherLocationManagerCanSeeStartOnStartedService")
    public void verifyStartStopVisibleOnlyInActiveStateServiceLevel(String rowID,
                                                                    String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        ServiceData serviceDto = workOrderData.getServiceData();
        OrderPhaseDto phaseDto = workOrderData.getMonitoring().getOrderPhaseDto();

        EditOrderSteps.openServiceMenu(serviceDto);
        MenuSteps.selectMenuItem(MenuItems.REPORT_PROBLEM);
        ProblemReportingSteps.setProblemReason(phaseDto.getProblemReason());
        EditOrderSteps.openServiceMenu(serviceDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.START, false);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.STOP, false);
        MenuSteps.selectMenuItem(MenuItems.RESOLVE_PROBLEM);
        ProblemReportingSteps.resolveProblem();
        EditOrderSteps.openServiceMenu(serviceDto);
        MenuSteps.selectMenuItem(MenuItems.COMPLETE);
        GeneralSteps.confirmDialog();
        EditOrderSteps.openServiceMenu(serviceDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.START, false);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.STOP, false);
        MenuSteps.closeMenu();
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, dependsOnMethods = "anotherLocationManagerCanSeeStartOnStartedService")
    public void verifyStartStopVisibleOnlyInActiveStatePhaseLevel(String rowID,
                                                                  String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        OrderPhaseDto phaseDto = workOrderData.getMonitoring().getOrderPhaseDto();

        EditOrderSteps.openPhaseMenu(phaseDto);
        MenuSteps.selectMenuItem(MenuItems.REPORT_PROBLEM);
        ProblemReportingSteps.setProblemReason(phaseDto.getProblemReason());
        EditOrderSteps.openPhaseMenu(phaseDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.START, false);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.STOP, false);
        MenuSteps.selectMenuItem(MenuItems.RESOLVE_PROBLEM);
        ProblemReportingSteps.resolveProblem();
        EditOrderSteps.openPhaseMenu(phaseDto);
        MenuSteps.selectMenuItem(MenuItems.COMPLETE);
        GeneralSteps.confirmDialog();
        MonitorSteps.toggleFocusMode(MenuItems.FOCUS_MODE_ON);
        EditOrderSteps.openPhaseMenu(phaseDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.START, false);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.STOP, false);
        MenuSteps.closeMenu();
    }
}
