package com.cyberiansoft.test.vnextbo.steps.repairOrders;

import com.cyberiansoft.test.vnextbo.interactions.repairOrders.VNextBOROAdvancedSearchDialogInteractions;
import com.cyberiansoft.test.vnextbo.verifications.repairOrders.VNextBOROPageVerifications;

public class VNextBOROAdvancedSearchDialogSteps {

    private VNextBOROAdvancedSearchDialogInteractions advancedSearchDialogInteractions;

    public VNextBOROAdvancedSearchDialogSteps() {
        advancedSearchDialogInteractions = new VNextBOROAdvancedSearchDialogInteractions();
    }

    public void searchByActivePhase(String phase, String phaseStatus, String timeFrame) {
        advancedSearchDialogInteractions.setPhase(phase);
        advancedSearchDialogInteractions.setPhaseStatus(phaseStatus);
        advancedSearchDialogInteractions.setTimeFrame(timeFrame);
        advancedSearchDialogInteractions.clickSearchButton();
    }

    public void closeAdvancedSearchDialog() {
        advancedSearchDialogInteractions.closeAdvancedSearchDialog();
        new VNextBOROPageVerifications().verifyAdvancedSearchDialogIsClosed();
    }
}