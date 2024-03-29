package com.cyberiansoft.test.vnext.screens.typeselectionlists;

import com.cyberiansoft.test.vnext.factories.invoicestypes.InvoiceTypes;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextTypeScreenContext;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextBaseWizardScreen;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VNextInvoiceTypesList extends VNextBaseTypeSelectionList {

    @FindBy(xpath="//div[@data-page='invoices-types']")
    private WebElement rootElement;

    public VNextInvoiceTypesList() {
    }

    public void selectInvoiceType(InvoiceTypes invoiceType) {
        WaitUtils.elementShouldBeVisible(rootElement, true);
        WaitUtils.waitUntilElementIsClickable(rootElement);
        selectType(invoiceType.getInvoiceTypeName());
        VNextBaseWizardScreen.typeScreenContext = VNextTypeScreenContext.INVOICE;
    }
}
