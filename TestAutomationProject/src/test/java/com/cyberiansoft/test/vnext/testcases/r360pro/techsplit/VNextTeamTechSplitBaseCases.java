package com.cyberiansoft.test.vnext.testcases.r360pro.techsplit;

import com.cyberiansoft.test.baseutils.MonitoringDataUtils;
import com.cyberiansoft.test.dataclasses.Employee;
import com.cyberiansoft.test.dataclasses.WorkOrderData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.enums.MenuItems;
import com.cyberiansoft.test.vnext.data.r360pro.VNextProTestCasesDataPaths;
import com.cyberiansoft.test.vnext.enums.ScreenType;
import com.cyberiansoft.test.vnext.factories.inspectiontypes.InspectionTypes;
import com.cyberiansoft.test.vnext.factories.workordertypes.WorkOrderTypes;
import com.cyberiansoft.test.vnext.steps.*;
import com.cyberiansoft.test.vnext.steps.monitoring.SearchSteps;
import com.cyberiansoft.test.vnext.testcases.r360pro.BaseTestCaseTeamEditionRegistration;
import com.cyberiansoft.test.vnext.validations.VehicleInfoScreenValidations;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class VNextTeamTechSplitBaseCases extends BaseTestCaseTeamEditionRegistration {
    private String inspectionId = "";
    private String workOrderId = "";

    @BeforeClass(description = "Tech split base test cases")
    public void beforeClass() {
        JSONDataProvider.dataFile = VNextProTestCasesDataPaths.getInstance().getTechSplitDataPath();
        HomeScreenSteps.openCreateNewInspection();
        InspectionSteps.createInspection(testcustomer, InspectionTypes.O_KRAMAR);
        inspectionId = InspectionSteps.saveInspection();
        InspectionSteps.openInspectionMenu(inspectionId);
        InspectionMenuSteps.approveInspection();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void userCanAssignMultipleTechniciansToOrder(String rowID,
                                                        String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        List<Employee> employeeList = workOrderData.getTechnicianList();

        InspectionSteps.openInspectionMenu(inspectionId);
        InspectionMenuSteps.selectCreateWorkOrder();
        WorkOrderSteps.createWorkOrder(WorkOrderTypes.AUTOMATION_MONITORING);
        VehicleInfoScreenValidations.validateTechnicianSelected(employee);
        VehicleInfoScreenSteps.selectTechnicians(employeeList);
        VehicleInfoScreenValidations.validateTechnicianSelected(employee);
        VehicleInfoScreenValidations.validateTechniciansSelected(employeeList);
        WizardScreenSteps.navigateToWizardScreen(ScreenType.SERVICES);
        AvailableServicesScreenSteps.selectServices(MonitoringDataUtils.getTestSerivceData());
        workOrderId = WorkOrderSteps.saveWorkOrder();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class, dependsOnMethods = "userCanAssignMultipleTechniciansToOrder")
    public void userCanChangeTechAmountViaEdit(String rowID,
                                               String description, JSONObject testData) {
        WorkOrderData workOrderData = JSonDataParser.getTestDataFromJson(testData, WorkOrderData.class);
        List<Employee> employeeList = workOrderData.getTechnicianList();

        SearchSteps.searchByText(workOrderId);
        WorkOrderSteps.openMenu(workOrderId);
        MenuSteps.selectMenuItem(MenuItems.EDIT);
        List<Employee> baseEmployeeList = VehicleInfoScreenSteps.getSelectedTechnicians();
        VehicleInfoScreenValidations.validateTechnicianSelected(employee);
        VehicleInfoScreenSteps.deselectTechnicians(employeeList);
        GeneralSteps.confirmDialog();
        VehicleInfoScreenValidations.validateTechnicianSelected(employee);
        VehicleInfoScreenSteps.selectTechnicians(employeeList);
        GeneralSteps.confirmDialog();
        VehicleInfoScreenValidations.validateTechniciansSelected(baseEmployeeList);
        ScreenNavigationSteps.pressBackButton();
    }
}