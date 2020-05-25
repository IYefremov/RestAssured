package com.cyberiansoft.test.vnextbo.validations.servicerequests.details;

import com.cyberiansoft.test.vnextbo.steps.servicerequests.details.VNextBOSRGeneralInfoSteps;
import org.testng.Assert;

public class VNextBOSRGeneralInfoValidations {

    public static void verifyRoIsDisplayed(String expected) {
        Assert.assertEquals(VNextBOSRGeneralInfoSteps.getRONum(), expected, "The RO# hasn't been displayed properly");
    }
}
