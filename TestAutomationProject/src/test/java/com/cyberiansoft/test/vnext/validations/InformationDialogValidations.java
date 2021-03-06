package com.cyberiansoft.test.vnext.validations;

import com.cyberiansoft.test.vnext.screens.VNextInformationDialog;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

public class InformationDialogValidations {

    public static void clickOKAndVerifyMessage(String expectedMessage) {
        VNextInformationDialog informationDialog = new VNextInformationDialog();
        String msg = informationDialog.clickInformationDialogOKButtonAndGetMessage();
        Assert.assertEquals(msg, expectedMessage);
    }

    public static void clickStartAndVerifyMessage(String expectedMessage) {
        VNextInformationDialog informationDialog = new VNextInformationDialog();
        String msg = informationDialog.clickInformationDialogStartButton();
        Assert.assertEquals(msg, expectedMessage);
        WaitUtils.waitUntilElementInvisible(By.xpath("//*[@data-autotests-id='preloader']"));
    }

    public static void clickStopAndVerifyMessage(String expectedMessage) {
        VNextInformationDialog informationDialog = new VNextInformationDialog();
        String msg = informationDialog.clickInformationDialogStopButton();
        Assert.assertEquals(msg, expectedMessage);
    }

    public static void clickYesAndVerifyMessage(String expectedMessage) {
        VNextInformationDialog informationDialog = new VNextInformationDialog();
        String msg = informationDialog.clickInformationDialogYesButtonAndGetMessage();
        Assert.assertEquals(msg, expectedMessage);
    }
}
