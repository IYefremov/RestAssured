package com.cyberiansoft.test.vnext.screens.wizardscreens;

import com.cyberiansoft.test.vnext.factories.workordertypes.WorkOrderTypeData;
import com.cyberiansoft.test.vnext.screens.VNextInformationDialog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VNextWorkOrderSummaryScreen extends VNextBaseWizardScreen {
	
	@FindBy(xpath="//div[@data-page='summary']")
	private WebElement wosummaryscreen;
	
	@FindBy(xpath="//*[@action='auto-invoice']")
	private WebElement autoinvoicecreateoption;
	
	@FindBy(xpath="//*[@action='save']")
	private WebElement savebtn;

    public VNextWorkOrderSummaryScreen(WebDriver appiumdriver) {
		super(appiumdriver);
        PageFactory.initElements(appiumdriver, this);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOf(autoinvoicecreateoption));
	}
	
	/*public void clickCreateInvoiceOption() {
		tap(autoinvoicecreateoption);
	}*/
	
	public void clickWorkOrderSaveButton() {
		tap(savebtn);
		if (workOrderType != null)
			if (new WorkOrderTypeData(workOrderType).isCanBeDraft()) {
				VNextInformationDialog informationDialog = new VNextInformationDialog(appiumdriver);
				informationDialog.clickFinalButton();
			}
	}

	public void clickCreateInvoiceOptionAndSaveWO() {
		tap(autoinvoicecreateoption);
		tap(savebtn);
		/*if (workOrderType != null)
			if (new WorkOrderTypeData(workOrderType).isCanBeDraft()) {
				VNextInformationDialog informationDialog = new VNextInformationDialog(appiumdriver);
				informationDialog.clickFinalButton();
			}*/
	}

}
