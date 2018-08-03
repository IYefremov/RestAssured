package com.cyberiansoft.test.vnext.screens.typeselectionlists;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class VNextInvoiceTypesList extends VNextBaseTypeSelectionList {

    public VNextInvoiceTypesList(AppiumDriver<MobileElement> appiumdriver) {
        super(appiumdriver);
        //PageFactory.initElements(new ExtendedFieldDecorator(appiumdriver), this);
        PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
    }

    public void selectInvoiceType(String invoiceType) {
        selectType(invoiceType);
    }
}
