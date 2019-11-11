package com.cyberiansoft.test.vnextbo.steps.devicemanagement;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.vnextbo.screens.devicemanagement.VNextBOActiveDevicesWebPage;
import com.cyberiansoft.test.vnextbo.screens.devicemanagement.VNextBODeviceManagementWebPage;
import com.cyberiansoft.test.vnextbo.steps.VNextBOBaseWebPageSteps;

public class VNextBODeviceManagementSteps extends VNextBOBaseWebPageSteps {

    public static void clickAddNewDeviceButton() {

        Utils.clickElement(new VNextBOActiveDevicesWebPage().getAddNewDeviceButton());
        WaitUtilsWebDriver.waitForLoading();
    }

    public static void openActiveDevicesTab() {

        Utils.clickElement(new VNextBODeviceManagementWebPage().getActiveDevicesTab());
        WaitUtilsWebDriver.waitForLoading();
    }

    public static void openPendingRegistrationDevicesTab() {

        Utils.clickElement(new VNextBODeviceManagementWebPage().getPendingRegistrationTab());
        WaitUtilsWebDriver.waitForLoading();
    }
}