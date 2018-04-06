package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VNextInvoiceMenuScreen extends VNextBaseScreen {

	@FindBy(xpath="//a[@action='email']/i")
	private WebElement emailinvoicebtn;
	
	@FindBy(xpath="//a[@handler='_approve']")
	private WebElement approveinvoicebtn;
	
	@FindBy(xpath="//a[@handler='_voidInvoice']")
	private WebElement voidinvoicebtn;
	
	@FindBy(xpath="//a[@handler='_changePO']")
	private WebElement invoicechangeponumbtn;
	
	@FindBy(xpath="//a[@handler='_notes']")
	private WebElement invoicenotesbtn;
	
	@FindBy(xpath="//a[@handler='_refreshPictures']")
	private WebElement invoicerfreshpicturesbtn;
	
	@FindBy(xpath="//div[@data-menu='popup']")
	private WebElement invoicemenuscreen;
	
	@FindBy(xpath="//div[@class='close-popup close-actions']")
	private WebElement closebtn;
	
	public VNextInvoiceMenuScreen(AppiumDriver<MobileElement> appiumdriver) {
		super(appiumdriver);
		PageFactory.initElements(new ExtendedFieldDecorator(appiumdriver), this);	
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(invoicemenuscreen));
	}
	
	public VNextEmailScreen clickEmailInvoiceMenuItem() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(emailinvoicebtn));
		tap(emailinvoicebtn);
		return new VNextEmailScreen(appiumdriver);
	}
	
	public VNextApproveScreen clickApproveInvoiceMenuItem() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(approveinvoicebtn));
		tap(approveinvoicebtn);
		return new VNextApproveScreen(appiumdriver);
	}
	
	public void clickVoidInvoiceMenuItem() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(voidinvoicebtn));
		tap(voidinvoicebtn);
	}
	
	public VNextChangeInvoicePONumberDialog clickInvoiceChangePONumberMenuItem() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(invoicechangeponumbtn));
		tap(invoicechangeponumbtn);
		return new VNextChangeInvoicePONumberDialog(appiumdriver);
	}
	
	public VNextNotesScreen clickInvoiceNotesMenuItem() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(invoicenotesbtn));
		tap(invoicenotesbtn);
		return new VNextNotesScreen(appiumdriver);
	}
	
	public void clickInvoiceRefreshPicturesMenuItem() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(invoicerfreshpicturesbtn));
		tap(invoicerfreshpicturesbtn);
	}
	
	public void refreshInvoicePictures() {
		clickInvoiceRefreshPicturesMenuItem();
		VNextInformationDialog informationdlg = new VNextInformationDialog(appiumdriver);
		informationdlg.clickInformationDialogOKButton();
	}
	
	public boolean isInvoiceChangePONumberMenuItemExists() {
		return invoicechangeponumbtn.isDisplayed();
	}
	
	public VNextInvoicesScreen clickCloseInvoiceMenuButton() {
		tap(closebtn);
		return new VNextInvoicesScreen(appiumdriver);
	}

}
