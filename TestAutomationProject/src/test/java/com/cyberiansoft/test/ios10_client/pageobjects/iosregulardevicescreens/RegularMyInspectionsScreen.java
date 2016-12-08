package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularNotesScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;

public class RegularMyInspectionsScreen extends iOSRegularBaseScreen {
	
	final String firstinspxpath = ".tableViews()[0].cells()[0]";

	@iOSFindBy(accessibility = "Add")
	//@iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[1]/XCUIElementTypeButton[@accessibilityid='Add']")
    private IOSElement addinspbtn;
	
	@iOSFindBy(accessibility = "Edit")
    private IOSElement editpopupmenu;
	
	@iOSFindBy(accessibility = "Approve")
    private IOSElement approvepopupmenu;
	
	@iOSFindBy(xpath ="Send\nEmail")
    private IOSElement sendmailpopupmenu;
	
	@iOSFindBy(accessibility = "Copy")
    private IOSElement copypopupmenu;
	
	@iOSFindBy(accessibility = "Archive")
    private IOSElement archivepopupmenu;
	
	@iOSFindBy(accessibility ="Notes")
    private IOSElement notespopupmenu;
	
	@iOSFindBy(accessibility = "Change\nCustomer")
    private IOSElement changecustomerpopupmenu;
	
	@iOSFindBy(xpath = "//UIAScrollView[2]/UIAButton[7]")
    private IOSElement showwospopupmenu;
	
	@iOSFindBy(accessibility = "Create\nWO")
    private IOSElement createwopopupmenu;
	
	@iOSFindBy(accessibility = "Status Reason")
    private IOSElement statusreasonbtn;
	
	@iOSFindBy(accessibility = "Summary")
    private IOSElement summarybtn;
	
	@iOSFindBy(accessibility = "Assign")
    private IOSElement assignbtn;
	
	@iOSFindBy(accessibility = "Done")
    private IOSElement popupdonebtn;
	
	@iOSFindBy(uiAutomator = firstinspxpath)
    private IOSElement firstinspection;
	
	@iOSFindBy(accessibility = "Close")
    private IOSElement closeflterpopupbtn;
	
	@iOSFindBy(accessibility = "Save")
    private IOSElement saveflterpopupbtn;
	
	@iOSFindBy(accessibility = "Done")
    private IOSElement toolbardonebtn;
	
	public RegularMyInspectionsScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickAddInspectionButton() throws InterruptedException {
		addinspbtn.click();
		if (isAlertExists()) {
			appiumdriver.findElementByAccessibilityId("Discard").click();
			Thread.sleep(1000);
		}
	}

	public RegularVehicleScreen clickEditInspectionButton() {
		editpopupmenu.click();
		return new RegularVehicleScreen(appiumdriver);
	}
	
	public void selectInspectionForEdit(String inspnumber) {
		appiumdriver.findElementByAccessibilityId(inspnumber).click();
		clickEditInspectionButton();
	}

	public void selectInspectionForApprove(String inspnumber) {
		//appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='" + inspnumber + "']")).click();
		//appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeStaticText[@value='" + inspnumber + "']")).click();
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + inspnumber + "']")).click();
		//appiumdriver.findElement(MobileBy.AccessibilityId(inspnumber)).click();
		clickApproveInspectionButton();
	}
	
	public void openInspectionSummary(String inspnumber) {
		appiumdriver.findElementByAccessibilityId(inspnumber).click();
		summarybtn.click();
	}
	
	public void selectEmployee(String employee) {
		appiumdriver.findElementByAccessibilityId(employee).click();
	}
	
	public void selectEmployeeAndTypePassword(String employee, String password) {
		selectEmployee( employee);
		((IOSElement) appiumdriver.findElementByAccessibilityId("Enter password here")).setValue(password);
		Helpers.acceptAlert();
		Helpers.waitABit(5000);
	}
	
	public void clickOnInspection(String inspnumber) {
		appiumdriver.findElementByAccessibilityId(inspnumber).click();
	}
	
	public void selectInspectionForCopy(String inspnumber) {
		appiumdriver.findElementByAccessibilityId(inspnumber).click();
		clickCopyInspection();
	}
	
	public void selectInspectionForCreatingWO(String inspnumber) {
		appiumdriver.findElementByAccessibilityId(inspnumber).click();
		clickCreateWOButton();
	}
	
	protected void clickApproveInspectionButton() {
		approvepopupmenu.click();
	}
	
	public void clickCreateWOButton() {
		createwopopupmenu.click();
	}
	
	public void clickSendEmail() {
		sendmailpopupmenu.click();
	}
	
	public void clickCopyInspection() {
		copypopupmenu.click();
	}

	public boolean isCreateWOButtonDisplayed() {
		return createwopopupmenu.isDisplayed();
	}

	public void clickArchive InspectionButton() {
		archivepopupmenu.click();
		waitForAlert();
		acceptAlert();
	}

	public void archive Inspection(String inspection, String reason)
			throws InterruptedException {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='MyInspectionsTable']/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='" + inspection + "']")).click();
		clickArchive InspectionButton();
		selectReasonToArchive(reason);

	}

	public void selectReasonToArchive(String reason)
			throws InterruptedException {

		selectUIAPickerValue(reason);
		//statusreasonbtn.click();
		//Thread.sleep(1000);
		Helpers.waitABit(1000);
		appiumdriver.findElement(MobileBy.AccessibilityId("Done")).click();
	}

	public RegularVehicleScreen selectDefaultInspectionType() {
		appiumdriver.findElement(MobileBy.AccessibilityId("Default")).click();
		return new RegularVehicleScreen(appiumdriver);
	}

	public void selectInspectionType (String inspectiontype) {
		Helpers.waitABit(1000);
		appiumdriver.findElement(MobileBy.AccessibilityId(inspectiontype)).click();
	}

	public void selectFirstInspection() {
		WebDriverWait wait = new WebDriverWait(appiumdriver,10);
        wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByXPath("//XCUIElementTypeTable[@name='MyInspectionsTable']/XCUIElementTypeCell[1]"))).click();;
		//appiumdriver.findElementByXPath("//XCUIElementTypeTable[@name='MyInspectionsTable']/XCUIElementTypeCell[1]").click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeTable[@name='MyInspectionsTable']/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='labelInspectionNumber']").click();
		//firstinspection.click();
	}

	public String getFirstInspectionNumberValue() {		
		return appiumdriver.findElement(By.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='labelInspectionNumber']")).getAttribute("label");
	}
	
	public String getFirstInspectionPriceValue() {
		return appiumdriver.findElement(By.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='labelInspectionAmount']")).getAttribute("label");
		//return firstinspectionprice.getAttribute("label");
	}
	
	public String getInspectionPriceValue(String inspectionnumber) {
		return appiumdriver.findElement(By.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + inspectionnumber + "']/XCUIElementTypeStaticText[@name='labelInspectionAmount']")).getAttribute("label");
		//return firstinspectionprice.getAttribute("label");
	}
	
	public String getInspectionApprovedPriceValue(String inspectionnumber) {
		return appiumdriver.findElement(By.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + inspectionnumber + "']/XCUIElementTypeStaticText[@name='labelInspectionApprovedAmount']")).getAttribute("label");
		//return firstinspectionprice.getAttribute("label");
	}
	
	public String getFirstInspectionAprovedPriceValue() {
		return appiumdriver.findElement(By.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='labelInspectionApprovedAmount']")).getAttribute("label");	
	}

	public void assertInspectionDoesntExists(String inspection)  {
		Assert.assertTrue(appiumdriver.findElementsByName(inspection).size() < 1);
	}

	public void assertInspectionExists(String inspection) {
		Assert.assertTrue(appiumdriver.findElementsByName(inspection).size() > 0);
	}

	public RegularApproveInspectionsScreen selectFirstInspectionToApprove() {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] ").click();
		return new RegularApproveInspectionsScreen(appiumdriver);
	}
	
	public void selectInspectionToApprove(String inspection) {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[contains(@name,\""
						+ inspection
						+ "\")]/UIAButton[@name=\"approve disabled\"] ").click();
	}

	public void clickActionButton() {
		appiumdriver.findElementByAccessibilityId("Share").click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeToolbar/XCUIElementTypeButton[contains(@label,'Share')] ").click();
	}

	public void clickFilterButton() {
		appiumdriver.findElementByAccessibilityId("filter").click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeToolbar/XCUIElementTypeButton[contains(@name,'filter')] ").click();
	}

	public boolean assertFilterIsApplied() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeToolbar/XCUIElementTypeButton[contains(@name,'filter')] ").getAttribute("name").equals("filter pressed");
	}

	public void clearFilter() {
		clickFilterButton();
		appiumdriver.findElementByXPath("//UIAButton[@visible=\"true\" and (contains(@name,\"Clear\"))] ").click();
	}

	public void clickStatusFilter() {
		//WebElement par = getParentNode("Status");
		appiumdriver.findElementByAccessibilityId("Status").click();
		//appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@visible=\"true\" and (contains(@name,\"Status\"))] ").click();
	}

	public void assertFilterStatusIsSelected(String filterstatus) {
		Assert.assertEquals(appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\""
								+ filterstatus + "\"] ").getAttribute("value"),"1");
	}

	public void clickFilterStatus(String filterstatus) {
		appiumdriver.findElementByAccessibilityId(filterstatus).click();
	}
	
	public RegularVehicleScreen showWorkOrdersForInspection(String inspection) {
		appiumdriver.findElement(MobileBy.AccessibilityId(inspection)).click();
		clickShowWorkOrdersButton();
		return new RegularVehicleScreen(appiumdriver);
		
	}
	
	public void clickShowWorkOrdersButton() {
		showwospopupmenu.click();
	}

	public void clickCloseFilterDialogButton() {
		closeflterpopupbtn.click();
	}

	public void clickSaveFilterDialogButton() {
		saveflterpopupbtn.click();
	}
	
	public void clickChangeCustomerpopupMenu() {
		changecustomerpopupmenu.click();
	}
	
	public void selectCustomer(String customer) {
		appiumdriver.findElementByAccessibilityId(customer).click();
	}
	
	public void changeCustomerForInspection(String inspection, String customer) {
		appiumdriver.findElement(MobileBy.AccessibilityId(inspection)).click();
		clickChangeCustomerpopupMenu();
		selectCustomer(customer);
	}
	
	public void customersPopupSwitchToWholesailMode() throws InterruptedException {
		if (elementExists(MobileBy.AccessibilityId("btnRetail"))) {
			appiumdriver.findElementByAccessibilityId("btnRetail").click();
		}
	}
	
	public void customersPopupSwitchToRetailMode() throws InterruptedException {
		Helpers.waitABit(500);
		if (elementExists(MobileBy.AccessibilityId("btnWholesale"))) {
			appiumdriver.findElementByAccessibilityId("btnWholesale").click();
		}
	}
	
	public int getNumberOfWorkOrdersForIspection() {
		return appiumdriver.findElementsByXPath("//UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell").size();
	}
	
	public boolean isWorkOrderForInspectionExists(String wonuber) {
		boolean result = Helpers.elementExists(By.xpath("//UIAActionSheet[1]/UIACollectionView[1]/UIACollectionCell[@name=\""
						+ wonuber + "\"]"));
		appiumdriver.findElementByXPath("//UIAActionSheet[1]/UIAButton[@name=\"Cancel\"]").click();
		return result;
	}
	
	public boolean selectUIAPickerWheelValue(MobileElement picker,
			MobileElement pickerwheel, String value) throws InterruptedException {
		int defaultwheelnumer = 10;
		int clicks = 0;
		boolean result = false;
		while (!(pickerwheel.getAttribute("name").contains(value))) {
			appiumdriver.tap(1, pickerwheel.getLocation().getX()
					+ picker.getSize().getWidth() - 50, pickerwheel
					.getLocation().getY() + picker.getSize().getHeight() + 10, 50);
			Thread.sleep(1000);
			if (pickerwheel.getAttribute("name").contains(value)) {
				result = true;
			}
			clicks = clicks+1;
			if (clicks > defaultwheelnumer)
				return false;
		}
		return result;
	}
	

	public void clickDoneButton() {
		toolbardonebtn.click();
	}

	public void clickApproveInspections() {
		clickActionButton();
		approvepopupmenu.click();
	}

	public void clickArchiveInspections() {
		clickActionButton();
		clickArchive InspectionButton();
	}

	public void selectInspectionForAction(String inspnumber) {
		WebElement par = appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='" + inspnumber + "']/.."));
		
		par.findElement(MobileBy.xpath("./XCUIElementTypeButton[contains(@name, 'EntityInfoButtonUnchecked')] ")).click();
	}
	
	public WebElement getParentNode(String nodename) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='MyInspectionsTable']/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='" + nodename + "']/.."));
	}

	public void assertInspectionIsApproved(String inspnumber) {
		Assert.assertTrue(appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + inspnumber + "']/XCUIElementTypeButton")).getAttribute("name").equals("EntityInfoButtonUnchecked"));
	}
	
	public boolean isInspectionIsApproveButtonExists(String inspnumber) {
		WebElement par = appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='" + inspnumber + "']/.."));
		
		return par.findElements(MobileBy.xpath(".//XCUIElementTypeButton")).size() > 0;
	}
	
	public boolean isNotesIconPresentForInspection(String inspnumber) {
		return appiumdriver.findElements(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + inspnumber + "']/XCUIElementTypeImage[@name='ESTIMATION_NOTES']")).size() > 0;
	}
	
	public boolean isDraftIconPresentForInspection(String inspnumber) {
		return appiumdriver.findElements(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + inspnumber + "']/XCUIElementTypeImage[@name='ESTIMATION_DRAFT']")).size() > 0;
	}
	
	public RegularNotesScreen openInspectionNotesScreen(String inspnumber) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + inspnumber + "']")).click();
		TouchAction action = new TouchAction(appiumdriver);
		action.press(notespopupmenu).waitAction(1000).release().perform();
		//notespopupmenu.click();
		return new RegularNotesScreen(appiumdriver);
	}
	
	public void selectInspectionToAssign(String inspnumber) {
		clickOnInspection(inspnumber);
		assignbtn.click();
	}
	
	public boolean isAssignButtonExists() {
		return appiumdriver.findElementsByAccessibilityId("Assign").size() > 0;
	}
	
	public boolean isApproveInspectionMenuActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Approve").size() > 0;
	}
	
	public boolean isSendEmailInspectionMenuActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Send\nEmail").size() > 0;
	}
	
	public boolean isCreateWOInspectionMenuActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Create\nWO").size() > 0;
	}

	public boolean isCreateServiceRequestInspectionMenuActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Create\nService Request").size() > 0;
	}
	
	public boolean isCopyInspectionMenuActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Copy").size() > 0;
	}
	
}
