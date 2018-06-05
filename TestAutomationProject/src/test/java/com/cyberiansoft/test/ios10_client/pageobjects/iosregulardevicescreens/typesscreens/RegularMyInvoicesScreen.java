package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.typesscreens;

import com.cyberiansoft.test.ios10_client.appcontexts.TypeScreenContext;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularNotesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularBaseWizardScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularInvoiceInfoScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegularMyInvoicesScreen extends RegularBaseTypeScreenWithTabs {

	private final TypeScreenContext INVOICECONTEXT = TypeScreenContext.INVOICE;

	/*@iOSFindBy(accessibility = "Notes")
    private IOSElement notesmenu;
	
	@iOSFindBy(accessibility = "Edit")
    private IOSElement editmenu;
	
	@iOSFindBy(accessibility = "Change\nCustomer")
    private IOSElement changecustomermenu;
	
	@iOSFindBy(accessibility = "Change\nPO#")
    private IOSElement changeponumbermenu;
	
	@iOSFindBy(accessibility = "My Invoices")
    private IOSElement myinvoicesmenu;
	
	@iOSFindBy(accessibility = "Team Invoices")
    private IOSElement teaminvoicesmenu;
	
	@iOSFindBy(accessibility = "Summary")
    private IOSElement summarymenu;
	
	@iOSFindBy(accessibility = "Done")
    private IOSElement donebtn;
	
	@iOSFindBy(accessibility = "Single Email")
    private IOSElement singlemailbtn;
	
	@iOSFindBy(accessibility = "Send\nEmail")
    private IOSElement sendemailbtn;
	
	@iOSFindBy(accessibility = "Approve")
    private IOSElement approvepopupmenu;
	
	@iOSFindBy(accessibility = "Send")
    private IOSElement sendbtn;
	
	@iOSFindBy(accessibility = "Add")
    private IOSElement sendmailaddmailbtn;
	
	@iOSFindBy(xpath = "//UIAToolbar[1]/UIAButton[@visible=\"true\" and (contains(@name,\"Share\"))] ")
    private IOSElement sharebtn;*/
	
	public RegularMyInvoicesScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("InvoicesTable")));
	}

	public boolean myInvoiceExists(String invoice) {
		return appiumdriver.findElements(MobileBy.AccessibilityId(invoice)).size() > 0;
	}
	
	public String getWOsForInvoice(String invoice) {
		
		WebElement invoicestable = appiumdriver.findElementByAccessibilityId("InvoicesTable");
		return invoicestable.findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + invoice + "']/XCUIElementTypeStaticText[@name='labelDetails']")).getAttribute("value");
	}
	
	public void selectInvoice(String invoice) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(invoice)));
		appiumdriver.findElement(MobileBy.AccessibilityId(invoice)).click();
	}
	
	/*public void selectFirstInvoice(String invoice) {
		getFirstInvoice().click();
	}*/
	
	public String getPriceForFirstInvoice() {
		return appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[1]/UIAStaticText[3]").getAttribute("name");
	}
	
	public boolean isInvoiceHasInvoiceNumberIcon(String invoice) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='InvoicesTable']/XCUIElementTypeCell[@name='" + invoice + "']/XCUIElementTypeImage[@name='INVOICE_NO']")).isDisplayed();
	}
	
	public boolean isInvoiceHasInvoiceSharedIcon(String invoice) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='InvoicesTable']/XCUIElementTypeCell[@name='" + invoice + "']/XCUIElementTypeImage[@name='INVOICE_SHARED']")).isDisplayed();
	}
	
	public void changeCustomerForInvoive(String invoice, String customer) throws InterruptedException {
		selectInvoice(invoice);
		clickChangeCustomerPopup();
		selectCustomer(customer);
		new RegularMyInvoicesScreen(appiumdriver);
	}
	
	public void selectCustomer(String customer) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Customers")));
		appiumdriver.findElementByName(customer).click();
	}
	
	public IOSElement getFirstInvoice() {
		return (IOSElement) appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]"));
	}
	
	public String getFirstInvoiceValue() {
		return getFirstInvoice().getAttribute("name");
	}
	
	public String getInvoicePrice(String invoicenumber) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='InvoicesTable']/XCUIElementTypeCell[@name='" + invoicenumber + "']/XCUIElementTypeStaticText[@name='labelInvoiceAmount']")).getAttribute("label");
	}
	
	public RegularNotesScreen clickNotesPopup() {
		appiumdriver.findElementByAccessibilityId("Notes").click();
		return new RegularNotesScreen(appiumdriver);
	}
	
	public RegularInvoiceInfoScreen clickEditPopup() {
		appiumdriver.findElementByAccessibilityId("Edit").click();
		RegularBaseWizardScreen.typeContext = INVOICECONTEXT;
		return new RegularInvoiceInfoScreen(appiumdriver);
	}
	
	public void clickChangeCustomerPopup() {
		appiumdriver.findElementByAccessibilityId("Change\nCustomer").click();
	}
	
	public void clickChangePOPopup() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Change\nPO#")));
		appiumdriver.findElementByAccessibilityId("Change\nPO#").click();
	}
	
	public void clickSummaryPopup() {
		appiumdriver.findElementByAccessibilityId("Summary").click();
	}
	
	protected void clickApproveInvoiceButton() {
		appiumdriver.findElementByAccessibilityId("Approve").click();
	}
	
	public boolean isSummaryPDFExists() {
		return appiumdriver.findElementsByXPath("//UIAStaticText[@name=\"Generating PDF file...\"]").size() > 0;
	}
	
	public void changePO(String newpo) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.className("XCUIElementTypeCollectionView")));
		appiumdriver.findElementByClassName("XCUIElementTypeCollectionView").findElement(By.className("XCUIElementTypeTextField")).clear();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(newpo);
		appiumdriver.switchTo().alert().accept();
	}
	
	public boolean myInvoicesIsDisplayed() throws InterruptedException {
		return appiumdriver.findElementByAccessibilityId("My Invoices").isDisplayed();
	}
	
	public boolean teamInvoicesIsDisplayed() throws InterruptedException {
		return appiumdriver.findElementByAccessibilityId("Team Invoices").isDisplayed();
	}
	
	public void clickActionButton() {
		appiumdriver.findElement(MobileBy.AccessibilityId("Share")).click();
		//sharebtn.click();
	}
	
	public void selectInvoiceForApprove(String inspnumber) {
		selectInvoice(inspnumber);
		clickApproveInvoiceButton();
	}
	
	public void selectEmployee(String employee) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10); 
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Employees")));
		appiumdriver.findElementByName(employee).click();
	}
	
	public void selectEmployeeAndTypePassword(String employee, String password) {
		selectEmployee( employee);
		((IOSElement) appiumdriver.findElementByXPath("//UIASecureTextField[@value=\"Enter password here\"]")).setValue(password);
		Helpers.acceptAlert();
	}
	
	public void selectInvoiceForActionByIndex(int invoiceindex) {
		appiumdriver.findElementByXPath("//XCUIElementTypeTable[@name='InvoicesTable']/XCUIElementTypeCell[" + invoiceindex + "]/XCUIElementTypeOther").click();
		//appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[\"" + invoiceindex + "\"]/UIAButton[@name=\"EntityInfoButtonUnchecked\"] ").click();
	}
	
	public boolean isInvoiceApproveButtonExists(String invoicenumber) {
		
		appiumdriver.findElementByAccessibilityId(invoicenumber).click();
		boolean approved = appiumdriver.findElementsByAccessibilityId("Approve").size() > 0;
		clickCancel();
		new RegularMyInvoicesScreen(appiumdriver);
		return approved;		
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
	
	public RegularMyInvoicesScreen sendSingleEmail(String email) {
		clickSendEmail();
		clickSendEmailAddMailButton();
		enterEmailAddress(email);
		clickSendButton();
		appiumdriver.findElementByAccessibilityId("Single Email").click();
		return this;
	}
	
	public void clickSendEmail() {
		appiumdriver.findElementByAccessibilityId("Send\nEmail").click();
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
		IOSElement invoicetable = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeAlert");
		((IOSElement) invoicetable.findElementByClassName("XCUIElementTypeTextField")).click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(email);
		Helpers.acceptAlert();
	}
	
	public String getPriceForInvoice(String invoicenumber) {
		return appiumdriver.findElementByAccessibilityId("labelInvoiceAmount").getAttribute("value");
	}
	
	public void selectInvoices(int numberInvoicesToSelect) {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);

		IOSElement invoicetable = (IOSElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.name("InvoicesTable"))); 
		List<MobileElement> invoices = invoicetable.findElementsByClassName("XCUIElementTypeCell");
		for (int i = 0; i < numberInvoicesToSelect; i++)
			invoices.get(i).click();
	}

}
