package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cyberiansoft.test.ios_client.utils.Helpers;

public class MyInvoicesScreen extends iOSHDBaseScreen {

	@iOSFindBy(accessibility  = "Notes")
    private IOSElement notesmenu;
	
	@iOSFindBy(accessibility  = "Edit")
    private IOSElement editmenu;
	
	@iOSFindBy(accessibility  = "Print")
    private IOSElement printmenu;
	
	@iOSFindBy(accessibility  = "Change Customer")
    private IOSElement changecustomermenu;
	
	@iOSFindBy(accessibility  = "Change PO#")
    private IOSElement changeponumbermenu;
	
	@iOSFindBy(accessibility  = "My Invoices")
    private IOSElement myinvoicesmenu;
	
	@iOSFindBy(accessibility  = "Team Invoices")
    private IOSElement teaminvoicesmenu;
	
	@iOSFindBy(accessibility  = "Summary")
    private IOSElement summarymenu;
	
	@iOSFindBy(accessibility  = "Approve")
    private IOSElement approvemenu;
	
	@iOSFindBy(accessibility  = "Done")
    private IOSElement donebtn;
	
	@iOSFindBy(accessibility  = "Single Email")
    private IOSElement singlemailbtn;
	
	@iOSFindBy(accessibility  = "Send Email")
    private IOSElement sendemailbtn;
	
	@iOSFindBy(xpath = "//UIAPopover[1]/UIANavigationBar[@name=\"Email\"]/UIAButton[@name=\"Send\"] ")
    private IOSElement sendbtn;
	
	@iOSFindBy(xpath = "//UIAPopover[1]/UIAToolbar[1]/UIAButton[@name=\"Add\"] ")
    private IOSElement sendmailaddmailbtn;
	
	@iOSFindBy(xpath = "//UIAButton[@visible=\"true\" and (contains(@name,\"Share\"))] ")
    private IOSElement sharebtn;
	
	public MyInvoicesScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public boolean myInvoiceExists(String invoice) {
		return appiumdriver.findElementsByXPath("//UIATableView/UIATableCell/UIAStaticText[contains(@name, \""
								+ invoice + "\")]").size() > 0;
	}
	
	public void selectInvoice(String invoice) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + invoice + "']")).click();
		//appiumdriver.findElementByAccessibilityId(invoice).click();
	}
	
	public void selectFirstInvoice(String vin) {
		IOSElement tablecell = getFirstInvoice();
		Assert.assertTrue(appiumdriver.findElementByXPath("//UIAElement[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[contains(@name, \"" + vin + "\")]").isEnabled());
		tablecell.click();
	}
	
	public String getPriceForFirstInvoice() {
		return appiumdriver.findElementByXPath("//UIAElement[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[@name='labelInvoiceAmount']").getAttribute("value");
	}
	
	public String getPriceForInvoice(String invoicenumber) {
		return appiumdriver.findElementByXPath("//UIAElement[1]/UIATableView[1]/UIATableCell[@name='" + invoicenumber + "']/UIAStaticText[@name='labelInvoiceAmount']").getAttribute("value");
	}
	
	public boolean isFirstInvoiceHasInvoiceNumberIcon() {
		return appiumdriver.findElementByXPath("//UIAElement[1]/UIATableView[1]/UIATableCell[1]/UIAImage[@name=\"INVOICE_NO\"]").isDisplayed();
	}
	
	public boolean isFirstInvoiceHasInvoiceSharedIcon() {
		return appiumdriver.findElementByXPath("//UIAElement[1]/UIATableView[1]/UIATableCell[1]/UIAImage[@name=\"INVOICE_SHARED\"]").isDisplayed();
	}
	
	public void printInvoice(String invoicenum, String printserver) {
		selectInvoice(invoicenum);
		PrintSelectorPopup printselectorpopup = clickPrintPopup();
		printselectorpopup.checkRemotePrintServerAndSelectPrintServer(printserver);
		printselectorpopup.clickPrintSelectorPrintButton();
		printselectorpopup.clickPrintOptionsPrintButton();
	}
	
	public void changeCustomerForInspection(String invoice, String customer) throws InterruptedException {
		selectInvoice(invoice);
		clickChangeCustomerPopup();
		selectCustomer(customer);
		
	}
	
	public void selectCustomer(String customer) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='CustomerSelectorTable']/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + customer + "']")).click();
		//appiumdriver.findElementByAccessibilityId(customer).click();
	}
	
	public IOSElement getFirstInvoice() {
		return (IOSElement) appiumdriver.findElementByAccessibilityId("InvoicesPageTableLeft").findElement(MobileBy.xpath("//XCUIElementTypeCell[1]"));
		//return (IOSElement) appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]");
	}
	
	public String getFirstInvoiceValue() {
		return getFirstInvoice().getAttribute("name");
	}
	
	public void verifyFirstInvoicePrice(String price) {
		Assert.assertTrue(appiumdriver.findElementByXPath("//UIAElement[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[@name=\"" + price + "\"]").isDisplayed());
	}
	
	public NotesScreen clickNotesPopup() {
		notesmenu.click();
		return new NotesScreen(appiumdriver);
	}
	
	public PrintSelectorPopup clickPrintPopup() {
		printmenu.click();
		return new PrintSelectorPopup(appiumdriver);
	}
	
	public void clickEditPopup() {
		editmenu.click();
		Helpers.waitABit(500);
	}
	
	public void clickChangeCustomerPopup() {
		changecustomermenu.click();
		Helpers.waitABit(500);
	}
	
	public void clickChangePOPopup() {
		changeponumbermenu.click();
	}
	
	public void clickSummaryPopup() {
		summarymenu.click();
	}
	
	protected void clickApprovePopup() {
		approvemenu.click();
	}
	
	public boolean isSummaryPDFExists() {
		return appiumdriver.findElementsByXPath("//UIAStaticText[@name=\"Generating PDF file...\"]").size() > 0;
	}
	
	public void selectInvoiceForApprove(String invoicenumber) throws InterruptedException {
		selectInvoice(invoicenumber);
		clickApprovePopup();
	}
	
	public void changePO(String newpo) {
		((IOSElement) appiumdriver.findElementByXPath("//UIAPopover[1]/UIATextField[1]"))
				.setValue("");
		((IOSElement) appiumdriver.findElementByXPath("//UIAPopover[1]/UIATextField[1]"))
				.setValue(newpo);
		appiumdriver.findElementByXPath("//UIAPopover[1]/UIANavigationBar[1]/UIAButton[@name=\"Done\"]")
				.click();
	}
	
	public boolean myInvoicesIsDisplayed() throws InterruptedException {
		return myinvoicesmenu.isEnabled();
	}
	
	public boolean teamInvoicesIsDisplayed() throws InterruptedException {
		return teaminvoicesmenu.isDisplayed();
	}
	
	public void clickActionButton() {
		sharebtn.click();
	}
	
	public void selectInvoiceForActionByIndex(int invoiceindex) {
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[\"" + invoiceindex + "\"]XCUIElementTypeButton[contains(@name, \"EntityInfoButtonUnchecked\")] ").click();
	}
	
	public void clickDoneButton() {
		donebtn.click();
	}
	
	public void sendEmail(String email) {
		clickSendEmail();
		clickSendEmailAddMailButton();
		enterEmailAddress(email);
		clickSendButton();
	}
	
	public void sendSingleEmail(String email) {
		clickSendEmail();
		clickSendEmailAddMailButton();
		enterEmailAddress(email);
		clickSendButton();
		singlemailbtn.click();
	}
	
	public void clickSendEmail() {
		sendemailbtn.click();
	}
	
	public void clickSendButton() {
		sendbtn.click();
	}
	
	public void clickSendEmailAddMailButton() {
		sendmailaddmailbtn.click();
	}
	
	public void enterEmailAddress(String email) {
		((IOSElement) appiumdriver.findElementByXPath("//UIAAlert[1]/UIAScrollView[1]/UIACollectionView[1]/UIACollectionCell[1]/UIATextField[1]")).setValue(email);
		Helpers.acceptAlert();
	}
	
	public void clickInvoiceApproveButton(String invoicenumber) {
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name=\"" + invoicenumber + "\"]/XCUIElementTypeButton[contains(@name, \"EntityInfoButtonUnchecked\")]").click();		
	}
	
	public boolean isInvoiceApproveButtonExists(String invoicenumber) {
		selectInvoice(invoicenumber);
		boolean approved = appiumdriver.findElements(MobileBy.AccessibilityId("Approve")).size() > 0;
		selectInvoice(invoicenumber);
		return approved;	
	}
	
	public boolean isInvoiceApproveRedButtonExists(String invoicenumber) {
		return appiumdriver.findElementByAccessibilityId("InvoicesPageTableLeft").findElements(MobileBy.
				xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name=\"" + invoicenumber + "\"]/XCUIElementTypeButton[@name=\"EntityInfoButtonUnchecked, ButtonImageId_70\"]")).size() > 0;
	}
	
	public boolean isInvoiceApproveGreyButtonExists(String invoicenumber) {
		return appiumdriver.findElementByAccessibilityId("InvoicesPageTableLeft").findElements(MobileBy.
				xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name=\"" + invoicenumber + "\"]/XCUIElementTypeButton[@name=\"EntityInfoButtonUnchecked, ButtonImageId_32\"]")).size() > 0;
	}
	
	public boolean isInvoicePrintButtonExists(String invoicenumber) {		
		return appiumdriver.findElementByAccessibilityId("InvoicesPageTableLeft").findElements(MobileBy.
				xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + invoicenumber + "']/XCUIElementTypeImage[@name='INVOICE_PRINTED']")).size() > 0;
	}
}
