package com.cyberiansoft.test.vnextbo.steps.repairordersnew;

import com.cyberiansoft.test.baseutils.ConditionWaiter;
import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.vnextbo.screens.repairordersnew.VNextBOROCompleteCurrentPhaseDialogNew;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VNextBOROCompleteCurrentPhaseDialogStepsNew {

    public static void closeDialogWithCancelButton() {

        VNextBOROCompleteCurrentPhaseDialogNew completeCurrentPhaseDialog = new VNextBOROCompleteCurrentPhaseDialogNew();
        Utils.clickElement(completeCurrentPhaseDialog.getCancelButton());
        WaitUtilsWebDriver.getShortWait().until(ExpectedConditions.invisibilityOf(completeCurrentPhaseDialog.getCompleteCurrentPhaseDialog()));
    }

    public static void completeCurrentPhase() {

        VNextBOROCompleteCurrentPhaseDialogNew completeCurrentPhaseDialog = new VNextBOROCompleteCurrentPhaseDialogNew();
        Utils.clickElement(completeCurrentPhaseDialog.getCompleteCurrentPhaseButton());
        ConditionWaiter.create(__ -> !completeCurrentPhaseDialog.getCompleteCurrentPhaseDialog().isDisplayed()).execute();
        WaitUtilsWebDriver.waitForPageToBeLoaded();
    }

    public static void resolveProblemForService(String service) {

        VNextBOROCompleteCurrentPhaseDialogNew completeCurrentPhaseDialog = new VNextBOROCompleteCurrentPhaseDialogNew();
        ConditionWaiter.create(__ -> completeCurrentPhaseDialog.resolveButtonByServiceName(service).isEnabled()).execute();
        Utils.clickElement(completeCurrentPhaseDialog.resolveButtonByServiceName(service));
    }
}
