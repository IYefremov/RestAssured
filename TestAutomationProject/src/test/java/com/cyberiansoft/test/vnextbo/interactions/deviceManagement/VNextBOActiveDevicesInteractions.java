package com.cyberiansoft.test.vnextbo.interactions.deviceManagement;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.screens.deviceManagement.VNextBOActiveDevicesWebPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class VNextBOActiveDevicesInteractions {

    private VNextBOActiveDevicesWebPage activeDevicesWebPage;
    private VNextBODeviceManagementInteractions deviceManagementInteractions;

    public VNextBOActiveDevicesInteractions() {
        activeDevicesWebPage = PageFactory.initElements(
                DriverBuilder.getInstance().getDriver(), VNextBOActiveDevicesWebPage.class);
        deviceManagementInteractions = new VNextBODeviceManagementInteractions();
    }

    public void verifyActiveDevicesTabIsOpened() {
        if (!deviceManagementInteractions.isActiveDevicesTabOpened()) {
            deviceManagementInteractions.clickActiveDevicesTab();
        }
    }

    public void verifyReplaceButtonIsDisplayedForDevice(String deviceName) {
        if (!isReplaceButtonDisplayedForDevice(deviceName)) {
            clickRegistrationNumberClearButtonForDevice(deviceName);
        }
    }

    public void clickReplaceButtonByDeviceName(String deviceName) {
        Assert.assertTrue(isReplaceButtonDisplayedForDevice(deviceName),
                "The 'Replace' button hasn't been displayed for the device name " + deviceName);
        Utils.clickElement(activeDevicesWebPage.getReplaceButtonByDeviceName(deviceName));
        WaitUtilsWebDriver.waitABit(1500);
    }

    public boolean isReplaceButtonDisplayedForDevice(String deviceName) {
        try {
            WaitUtilsWebDriver.waitForVisibility(activeDevicesWebPage.getReplaceButtonByDeviceName(deviceName), 7);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public String getRegistrationNumberForDevice(String deviceName) {
        try {
            return WaitUtilsWebDriver
                    .waitForVisibility(activeDevicesWebPage.getRegistrationNumberByDeviceName(deviceName))
                    .getText();
        } catch (Exception ignored) {
            return "";
        }
    }

    public void clickRegistrationNumberClearButtonForDevice(String deviceName) {
        Utils.clickElement(activeDevicesWebPage.getRegistrationNumberClearButtonForDevice(deviceName));
        WaitUtilsWebDriver.waitABit(1500);
    }
}