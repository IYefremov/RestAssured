package com.cyberiansoft.test.vnextbo.interactions.leftmenupanel;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.vnextbo.enums.MainMenuItems;
import com.cyberiansoft.test.vnextbo.screens.VNexBOLeftMenuPanel;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VNextBOLeftMenuInteractions {

    public static void selectInspectionsMenu() {
        selectMenuItem(MainMenuItems.OPERATIONS.getMenu(), "Inspections");
    }

    public static void selectInvoicesMenu() {
        selectMenuItem(MainMenuItems.OPERATIONS.getMenu(), "Invoices");
    }

    public static void selectPartsManagementMenu() {
        selectMenuItem(MainMenuItems.OPERATIONS.getMenu(), "Parts Management");
    }

    public static void selectUsersMenu() {
        selectMenuItem(MainMenuItems.SETTINGS.getMenu(), "Users");
    }

    public static void selectServicesMenu() {
        selectMenuItem(MainMenuItems.SETTINGS.getMenu(), "Services");
    }

    public static void selectQuickNotesMenu() {
        selectMenuItem(MainMenuItems.SETTINGS.getMenu(), "Quick Notes");
    }

    public static void selectCompanyInfoMenu() {
        selectMenuItem(MainMenuItems.SETTINGS.getMenu(), "Company Info");
    }

    public static void selectClientsMenu() {
        selectMenuItem(MainMenuItems.SETTINGS.getMenu(), "Clients");
    }

    public static void selectDeviceManagementMenu() {
        selectMenuItem(MainMenuItems.SETTINGS.getMenu(), "Device Management");
    }

    public static void selectRepairOrdersMenu() {
        selectMenuItem(MainMenuItems.MONITOR.getMenu(), "Repair Orders");
    }

    public static void expandMainMenu() {
        final VNexBOLeftMenuPanel leftMenuPanel = new VNexBOLeftMenuPanel();
        if (!leftMenuPanel.getBody().isDisplayed()) Utils.clickElement(leftMenuPanel.getMenuButton());
    }

    public static void collapseMainMenu() {
        final VNexBOLeftMenuPanel leftMenuPanel = new VNexBOLeftMenuPanel();
        if (leftMenuPanel.getBody().isDisplayed()) Utils.clickElement(leftMenuPanel.getMenuButton());
    }

    private static void clickMainMenuItem(String mainMenuItemName) {
        Utils.clickElement(new VNexBOLeftMenuPanel().mainMenuItemByName(mainMenuItemName));
        WaitUtilsWebDriver.getWebDriverWait(4).until(ExpectedConditions.attributeToBe(
                new VNexBOLeftMenuPanel().mainMenuItemByName(mainMenuItemName), "aria-expanded", "true"));
    }

    private static void clickSubMenuItem(String subMenuItemName) {
        Utils.clickElement(new VNexBOLeftMenuPanel().subMenuItemByName(subMenuItemName));
    }

    private static void selectMenuItem(String mainMenuItemName, String subMenuItemName) {
        expandMainMenu();
        clickMainMenuItem(mainMenuItemName);
        WaitUtilsWebDriver.waitUntilPageIsLoadedWithJs();
        clickSubMenuItem(subMenuItemName);
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
        WaitUtilsWebDriver.waitUntilPageIsLoadedWithJs();
        WaitUtilsWebDriver.waitForPendingRequestsToComplete();
    }
}