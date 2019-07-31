package com.cyberiansoft.test.ios10_client.regularvalidations;

import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.typesscreens.RegularMyInspectionsScreen;
import org.testng.Assert;

public class RegularMyInspectionsScreenValidations {

    public static void verifyInspectionTotalPrice(String inspectionID, String expectedTotalPrice) {
        RegularMyInspectionsScreen myInspectionsScreen = new RegularMyInspectionsScreen();
        Assert.assertEquals(myInspectionsScreen.getInspectionPriceValue(inspectionID), expectedTotalPrice);
    }

    public static void verifyInspectionApprovedPrice(String inspectionID, String expectedApprovedPrice) {
        RegularMyInspectionsScreen myInspectionsScreen = new RegularMyInspectionsScreen();
        Assert.assertEquals(myInspectionsScreen.getInspectionApprovedPriceValue(inspectionID), expectedApprovedPrice);
    }
}