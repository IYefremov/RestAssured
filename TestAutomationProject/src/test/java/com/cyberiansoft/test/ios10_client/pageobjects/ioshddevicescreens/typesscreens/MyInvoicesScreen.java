package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.typesscreens;

import com.cyberiansoft.test.ios10_client.appcontexts.TypeScreenContext;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.NotesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.PrintSelectorPopup;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.wizardscreens.BaseWizardScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.wizardscreens.InvoiceInfoScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class MyInvoicesScreen extends BaseTypeScreenWithTabs {

	private final TypeScreenContext INVOICECONTEXT = TypeScreenContext.INVOICE;

	/*@iOSFindBy(accessibility  = "Notes")
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
	
	@iOSFindBy(accessibility = "Send")
    private IOSElement sendbtn;
	
	@iOSFindBy(accessibility = "Add")
    private IOSElement sendmailaddmailbtn;*/
	
	//@iOSFindBy(xpath = "//XCUIElementTypeButton[@visible=\"true\" and (contains(@name,\"Share\"))] ")
    //private IOSElement sharebtn;
	
	public MyInvoicesScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Invoices")));
	}

	public boolean myInvoiceExists(String invoice) {
		return appiumdriver.findElementsByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + invoice + "']").size() > 0;
	}
	
	public String getInvoiceInfoLabel(String invoicenumber) {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='"
								+ invoicenumber + "']/XCUIElementTypeStaticText[@name='labelInfo1']").getAttribute("value");
	}
	
	public void selectInvoice(String invoiceNumber) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(invoiceNumber)));
		appiumdriver.findElementByAccessibilityId(invoiceNumber).click();
	}
	
	public void selectFirstInvoice(String vin) {
		IOSElement tablecell = getFirstInvoice();
		Assert.assertTrue(appiumdriver.findElementByXPath("//UIAElement[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[contains(@name, \"" + vin + "\")]").isEnabled());
		tablecell.click();
	}
	
	public String getPriceForFirstInvoice() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='labelInvoiceAmount']").getAttribute("value");
	}
	
	public String getPriceForInvoice(String invoicenumber) {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + invoicenumber + "']/XCUIElementTypeStaticText[@name='labelInvoiceAmount']").getAttribute("value");
	}
	
	public boolean isFirstInvoiceHasInvoiceNumberIcon() { 
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeImage[@name=\"INVOICE_NO\"]").isDisplayed();
	}
	
	public boolean isFirstInvoiceHasInvoiceSharedIcon() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeImage[@name=\"INVOICE_SHARED\"]").isDisplayed();
	}
	
	public void printInvoice(String invoicenum, String printserver) {
		selectInvoice(invoicenum);
		PrintSelectorPopup printselectorpopup = clickPrintPopup();
		printselectorpopup.checkRemotePrintServerAndSelectPrintServer(printserver);
		printselectorpopup.clickPrintSelectorPrintButton();
		printselectorpopup.clickPrintOptionsPrintButton();
	}
	
	public void changeCustomerForInvoice(String invoice, String customer) throws InterruptedException {
		selectInvoice(invoice);
		clickChangeCustomerPopup();
		selectCustomer(customer);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan (MobileBy.AccessibilityId("Customers"), 1));
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
	
	public String getInvoicePrice(String invoicenumber) {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='"
				+ invoicenumber + "']/XCUIElementTypeStaticText[@name='labelInvoiceAmount']").getAttribute("value");
	}
	
	public NotesScreen clickNotesPopup() {
		appiumdriver.findElementByAccessibilityId("Notes").click();
		return new NotesScreen(appiumdriver);
	}
	
	public PrintSelectorPopup clickPrintPopup() {
		appiumdriver.findElementByAccessibilityId("Print").click();
		return new PrintSelectorPopup(appiumdriver);
	}
	
	public InvoiceInfoScreen clickEditPopup() {
		appiumdriver.findElementByAccessibilityId("Edit").click();
		BaseWizardScreen.typeContext = INVOICECONTEXT;
		return new InvoiceInfoScreen(appiumdriver);
	}
	
	public void clickChangeCustomerPopup() {
		appiumdriver.findElementByAccessibilityId("Change Customer").click();
	}
	
	public void clickChangePOPopup() {
		appiumdriver.findElementByAccessibilityId("Change PO#").click();
	}
	
	public void clickSummaryPopup() {
		appiumdriver.findElementByAccessibilityId("Summary").click();
	}
	
	protected void clickApprovePopup() {
		appiumdriver.findElementByAccessibilityId("Approve").click();
	}
	
	public boolean isSummaryPDFExists() {
		return appiumdriver.findElementsByAccessibilityId("Generating PDF file...").size() > 0;
	}
	
	public void selectInvoiceForApprove(String invoicenumber) throws InterruptedException {
		selectInvoice(invoicenumber);
		clickApprovePopup();
	}
	
	public void changePO(String newpo) {
		Helpers.waitABit(500);
		WebElement par = appiumdriver.findElementByXPath("//XCUIElementTypeStaticText[@name='PO#']/..");
		par.findElement(By.className("XCUIElementTypeTextField")).clear();
		par.findElement(By.className("XCUIElementTypeTextField")).sendKeys(newpo);
		//((IOSDriver) appiumdriver).getKeyboard().pressKey(newpo);
		appiumdriver.findElementByAccessibilityId("Done").click();
	}
	
	public boolean myInvoicesIsDisplayed() throws InterruptedException {
		return appiumdriver.findElementByAccessibilityId("My Invoices").isEnabled();
	}
	
	public boolean teamInvoicesIsDisplayed() throws InterruptedException {
		return appiumdriver.findElementByAccessibilityId("Team Invoices").isDisplayed();
	}
	
	public void clickActionButton() {
		appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeToolbar").findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'Share')] ")).click();
	}
	
	public void selectInvoiceForActionByIndex(int invoiceindex) {
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[" + invoiceindex + "]/XCUIElementTypeOther[contains(@name, \"EntityInfoButtonUnchecked\")] ").click();
	}
	
	public void clickDoneButton() {
		appiumdriver.findElementByAccessibilityId("Done").click();
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
		appiumdriver.findElementByAccessibilityId("Single Email").click();
	}
	
	public void clickSendEmail() {
		appiumdriver.findElementByAccessibilityId("Send Email").click();
	}
	
	public void clickSendButton() {
		appiumdriver.findElementByAccessibilityId("Send").click();
	}
	
	public void clickSendEmailAddMailButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Add")));
		appiumdriver.findElementByAccessibilityId("Add").click();
	}
	
	public void enterEmailAddress(String email) {
		//((IOSElement) appiumdriver.findElementByXPath("//XCUIElementTypeAlert/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeTextField")).setValue(email);
		((IOSDriver) appiumdriver).getKeyboard().pressKey(email);
		Helpers.acceptAlert();
	}
	
	public void clickInvoiceApproveButton(String invoicenumber) {
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name=\"" + invoicenumber + "\"]/XCUIElementTypeOther[contains(@name, \"EntityInfoButtonUnchecked\")]").click();		
	}
	
	public boolean isInvoiceApproveButtonExists(String invoicenumber) {
		selectInvoice(invoicenumber);
		boolean approved = appiumdriver.findElements(MobileBy.AccessibilityId("Approve")).size() > 0;
		selectInvoice(invoicenumber);
		Helpers.waitABit(500);
		return approved;	
	}
	
	public boolean isInvoiceApproveRedButtonExists(String invoicenumber) {
		return appiumdriver.findElementByAccessibilityId("InvoicesPageTableLeft").findElements(MobileBy.
				xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name=\"" + invoicenumber + "\"]/XCUIElementTypeOther[@name=\"EntityInfoButtonUnchecked, ButtonImageId_70\"]")).size() > 0;
	}
	
	public boolean isInvoiceApproveGreyButtonExists(String invoicenumber) {
		return appiumdriver.findElementByAccessibilityId("InvoicesPageTableLeft").findElements(MobileBy.
				xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name=\"" + invoicenumber + "\"]/XCUIElementTypeOther[@name=\"EntityInfoButtonUnchecked, ButtonImageId_32\"]")).size() > 0;
	}
	
	public boolean isInvoicePrintButtonExists(String invoicenumber) {		
		return appiumdriver.findElementByAccessibilityId("InvoicesPageTableLeft").findElements(MobileBy.
				xpath("//XCUIElementTypeCell[@name='" + invoicenumber + "']/XCUIElementTypeImage[@name='INVOICE_PRINTED']")).size() > 0;
	}

	public void clickVoidInvoiceMenu() {
		appiumdriver.findElementByAccessibilityId("Void").click();
	}

}
