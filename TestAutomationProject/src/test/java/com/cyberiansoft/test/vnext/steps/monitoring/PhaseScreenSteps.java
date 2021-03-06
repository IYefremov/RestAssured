package com.cyberiansoft.test.vnext.steps.monitoring;

import com.cyberiansoft.test.baseutils.ConditionWaiter;
import com.cyberiansoft.test.dataclasses.ServiceData;
import com.cyberiansoft.test.dataclasses.ServiceTechnician;
import com.cyberiansoft.test.dataclasses.partservice.PartServiceData;
import com.cyberiansoft.test.enums.MenuItems;
import com.cyberiansoft.test.vnext.dto.OrderPhaseDto;
import com.cyberiansoft.test.vnext.enums.ServiceOrTaskStatus;
import com.cyberiansoft.test.vnext.interactions.PhaseScreenInteractions;
import com.cyberiansoft.test.vnext.screens.monitoring.PhasesScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextAvailableServicesScreen;
import com.cyberiansoft.test.vnext.steps.GeneralListSteps;
import com.cyberiansoft.test.vnext.steps.GeneralSteps;
import com.cyberiansoft.test.vnext.steps.MenuSteps;
import com.cyberiansoft.test.vnext.steps.commonobjects.TopScreenPanelSteps;
import com.cyberiansoft.test.vnext.steps.services.AvailableServicesScreenSteps;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import org.openqa.selenium.By;

public class PhaseScreenSteps {

    public static void startService(ServiceData serviceData) {
        EditOrderSteps.openServiceMenu(serviceData);
        MenuSteps.selectMenuItem(MenuItems.START);
        GeneralSteps.confirmDialog();
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void stopService(ServiceData serviceData) {
        EditOrderSteps.openServiceMenu(serviceData);
        MenuSteps.selectMenuItem(MenuItems.STOP);
        GeneralSteps.confirmDialog();
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void deleteService(ServiceData serviceData) {
        EditOrderSteps.openServiceMenu(serviceData);
        MenuSteps.selectMenuItem(MenuItems.DELETE);
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void deleteService(String serviceName) {
        PhaseScreenInteractions.openServiceElementMenu(PhaseScreenInteractions.getServiceElements(serviceName));
        MenuSteps.selectMenuItem(MenuItems.DELETE);
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void completeService(ServiceData serviceData) {
        EditOrderSteps.openServiceMenu(serviceData);
        MenuSteps.selectMenuItem(MenuItems.COMPLETE);
        GeneralSteps.confirmDialog();
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void startServices() {
        PhaseScreenInteractions.clickStartServices();
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void stopServices() {
        PhaseScreenInteractions.clickStopServices();
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void completeServices() {
        PhaseScreenInteractions.clickCompleteServices();
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void selectTechnician(ServiceTechnician serviceTechnician) {
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
        GeneralListSteps.selectListItem(serviceTechnician.getTechnicianFullName());
    }

    public static void clickAddButton() {
        PhasesScreen phasesScreen = new PhasesScreen();
        phasesScreen.getAddButton().click();
    }

    public static void addTask() {
        clickAddButton();
        PhasesScreen phasesScreen = new PhasesScreen();
        phasesScreen.getTaskButton().click();
    }

    public static void addServices() {
        clickAddButton();
        PhasesScreen phasesScreen = new PhasesScreen();
        phasesScreen.getAllServicesButton().click();
        VNextAvailableServicesScreen availableServicesScreen = new VNextAvailableServicesScreen();
        WaitUtils.waitUntilElementIsClickable(availableServicesScreen.getAllServicesList().getRootElement());
    }

    public static void addPartService(PartServiceData serviceData) {
        addServices();
        AvailableServicesScreenSteps.addPartService(serviceData);
    }

    public static void openSelectStatusScreen(String serviceName) {

        PhaseScreenInteractions.openServiceElementMenu(
                PhaseScreenInteractions.getServiceElements(serviceName));
        MenuSteps.selectMenuItem(MenuItems.CHANGE_STATUS);
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void changeServiceOrTaskStatus(String serviceName, ServiceOrTaskStatus newStatus) {

        PhaseScreenSteps.openSelectStatusScreen(serviceName);
        SelectStatusScreenSteps.selectStatus(newStatus);
        TopScreenPanelSteps.saveChanges();
    }

    public static void changePhaseTechnician(OrderPhaseDto phaseData) {

        EditOrderSteps.openPhaseMenu(phaseData);
        MenuSteps.selectMenuItem(MenuItems.ASSIGN_TECH);
        SelectTechnicianScreenSteps.selectTechnician("1111 2222");
        TopScreenPanelSteps.saveChanges();
    }

    public static void reopenOrderPhaseScreen() {

        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
        TopScreenPanelSteps.saveChanges();
        MonitorSteps.tapOnFirstOrder();
        MenuSteps.selectMenuItem(MenuItems.EDIT);
    }

    public static void waitUntilPhaseScreenIsLoaded() {

        ConditionWaiter.create(__ -> new PhasesScreen().getRootElement().isDisplayed()).execute();
        ConditionWaiter.create(__ -> new PhasesScreen().getPhaseListElements().size() > 0).execute();
    }
}
