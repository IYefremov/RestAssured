package com.cyberiansoft.test.vnext.testcases.r360free;

import com.cyberiansoft.test.baseutils.AppiumUtils;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnext.utils.AppContexts;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTestCaseWithoutDeviceRegistration extends VNextBaseTestCase {
    @BeforeClass(description = "Setting up new suite")
    @Parameters({"user.name", "user.psw", "device.license", "selenium.browser"})
    public void settingUp(String deviceuser, String devicepsw, String licensename, String defbrowser) {
        regcode = getDeviceRegistrationCode(deviceOfficeUrl, deviceuser, devicepsw, licensename);
    }

    @AfterClass(description = "Setting up new suite")
    public void tearDown() {
        AppiumUtils.switchApplicationContext(AppContexts.NATIVE_CONTEXT);
        DriverBuilder.getInstance().getAppiumDriver().resetApp();
    }

}
