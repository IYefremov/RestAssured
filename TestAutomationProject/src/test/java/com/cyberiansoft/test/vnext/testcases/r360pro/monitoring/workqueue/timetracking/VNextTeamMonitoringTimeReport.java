package com.cyberiansoft.test.vnext.testcases.r360pro.monitoring.workqueue.timetracking;

import com.cyberiansoft.test.baseutils.MonitoringDataUtils;
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
import com.cyberiansoft.test.vnext.steps.services.AvailableServicesScreenSteps;
import com.cyberiansoft.test.vnext.testcases.r360pro.BaseTestCaseTeamEditionRegistration;
import com.cyberiansoft.test.vnext.validations.MenuValidations;
import com.cyberiansoft.test.vnext.validations.TimeReportScreenVerifications;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VNextTeamMonitoringTimeReport extends BaseTestCaseTeamEditionRegistration {
    private String inspectionId = "";
    private String workOrderId = "";

    @BeforeClass(description = "Team Monitoring Basic Flow Test")
    public void beforeClass() {
        JSONDataProvider.dataFile = VNextProTestCasesDataPaths.getInstance().getMonitoringTimeReportDataPath();
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
    public void userCanSeeTimeReportOnServiceLevel(String rowID,
                                                   String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        ServiceData serviceDto = workOrderData.getServiceData();

        MonitorSteps.editOrder(workOrderId);
        EditOrderSteps.openElementMenu(serviceDto.getServiceName());
        MenuValidations.menuItemShouldBeEnabled(MenuItems.TIME_REPORT, false);
        MenuSteps.selectMenuItem(MenuItems.START);
        GeneralSteps.confirmDialog();
        EditOrderSteps.openElementMenu(serviceDto.getServiceName());
        MenuSteps.selectMenuItem(MenuItems.TIME_REPORT);
        TimeReportScreenVerifications.startDateShouldBePresent(true);
        TimeReportScreenVerifications.endDateShouldBePresent(false);
        ScreenNavigationSteps.pressBackButton();
        EditOrderSteps.openElementMenu(serviceDto.getServiceName());
        MenuSteps.selectMenuItem(MenuItems.STOP);
        GeneralSteps.confirmDialog();
        EditOrderSteps.openElementMenu(serviceDto.getServiceName());
        MenuSteps.selectMenuItem(MenuItems.TIME_REPORT);
        TimeReportScreenVerifications.startDateShouldBePresent(true);
        TimeReportScreenVerifications.endDateShouldBePresent(true);
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void userCanSeeTimeReportOnPhaseLevel(String rowID,
                                                 String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        OrderPhaseDto phaseDto = workOrderData.getMonitoring().getOrderPhaseDto();

        MonitorSteps.editOrder(workOrderId);
        EditOrderSteps.openElementMenu(phaseDto);
        MenuValidations.menuItemShouldBeEnabled(MenuItems.TIME_REPORT, false);
        MenuSteps.selectMenuItem(MenuItems.START);
        GeneralSteps.confirmDialog();
        EditOrderSteps.openElementMenu(phaseDto);
        MenuSteps.selectMenuItem(MenuItems.TIME_REPORT);
        TimeReportScreenVerifications.startDateShouldBePresent(true);
        TimeReportScreenVerifications.endDateShouldBePresent(false);
        ScreenNavigationSteps.pressBackButton();
        EditOrderSteps.openElementMenu(phaseDto);
        MenuSteps.selectMenuItem(MenuItems.STOP);
        GeneralSteps.confirmDialog();
        EditOrderSteps.openElementMenu(phaseDto);
        MenuSteps.selectMenuItem(MenuItems.TIME_REPORT);
        TimeReportScreenVerifications.startDateShouldBePresent(true);
        TimeReportScreenVerifications.endDateShouldBePresent(true);
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
        ScreenNavigationSteps.pressBackButton();
    }
}