package com.cyberiansoft.test.ios10_client.regularvalidations;

import com.cyberiansoft.test.dataclasses.ServiceData;
import com.cyberiansoft.test.dataclasses.ServiceStatus;
import com.cyberiansoft.test.enums.OrderMonitorStatuses;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularOrderMonitorScreen;
import org.testng.Assert;

public class RegularOrderMonitorScreenValidations {

    public static void verifyServiceStatus(ServiceData serviceData, OrderMonitorStatuses expectedStatus) {
        RegularOrderMonitorScreen orderMonitorScreen = new RegularOrderMonitorScreen();
        Assert.assertEquals(orderMonitorScreen.getPanelStatus(serviceData), expectedStatus.getValue());
    }
}
