package com.cyberiansoft.test.vnextbo.interactions.repairOrders;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.screens.repairOrders.VNextBOCloseRODialog;
import org.openqa.selenium.support.PageFactory;

public class VNextBOCloseRODialogInteractions {

    private VNextBOCloseRODialog closeRODialog;

    public VNextBOCloseRODialogInteractions() {
        closeRODialog = new VNextBOCloseRODialog();
    }

    public void setReason(String reason) {
        clickReasonBox();
        selectReason(reason);
    }

    private void clickReasonBox() {
        Utils.clickElement(closeRODialog.getReasonBox());
    }

    private void selectReason(String reason) {
        Utils.selectOptionInDropDown(closeRODialog.getListBoxDropDown(), closeRODialog.getListBoxOptions(), reason);
    }

    public void clickCloseROButton() {
        Utils.clickElement(closeRODialog.getCloseROButton());
    }
}