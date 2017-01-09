package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import com.cyberiansoft.test.ios10_client.utils.Helpers;

public class ServiceRequestsScreen extends iOSHDBaseScreen {
	
	@iOSFindBy(accessibility  = "Add")
    private IOSElement addbtn;
	
	@iOSFindBy(accessibility  = "Refresh")
    private IOSElement refreshbtn;
	
	@iOSFindBy(accessibility  = "Create Inspection")
    private IOSElement createinspectionmenu;
	
	@iOSFindBy(accessibility  = "Create Work Order")
    private IOSElement createworkordermenu;
	
	@iOSFindBy(accessibility  = "Appointments")
    private IOSElement appointmentmenu;
	
	@iOSFindBy(accessibility  = "Check In")
    private IOSElement checkinmenu;
	
	@iOSFindBy(accessibility  = "Undo Check In")
    private IOSElement undocheckinmenu;
	
	@iOSFindBy(accessibility  = "Close")
    private IOSElement closemenu;
	
	@iOSFindBy(accessibility  = "Reject")
    private IOSElement rejectmenu;
	
	@iOSFindBy(accessibility  = "Edit")
    private IOSElement editmenu;
	
	@iOSFindBy(accessibility  = "Cancel")
    private IOSElement cancelmenu;
	
	//Appointment
	@iOSFindBy(accessibility = "Add")
    private IOSElement addappointmentbtn;
	
	@iOSFindBy(accessibility  = "From")
    private IOSElement fromfld;
	
	@iOSFindBy(accessibility  = "To")
    private IOSElement tofld;
	
	@iOSFindBy(accessibility  = "Done")
    private IOSElement donebtn;
	
	@iOSFindBy(accessibility  = "Subject")
    private IOSElement subjectfld;
	
	@iOSFindBy(accessibility  = "Address")
    private IOSElement addressfld;
	
	@iOSFindBy(accessibility  = "City")
    private IOSElement cityfld;
	
	@iOSFindBy(accessibility  = "Summary")
    private IOSElement summarybtn;
	
	@iOSFindBy(accessibility = "Search")
    private IOSElement searchbtn;
	
	@iOSFindBy(accessibility = "Not Checked In")
    private IOSElement notcheckedinfilteritem;
	
	@iOSFindBy(accessibility = "Delete")
    private IOSElement deletefilterbtn;
	
	@iOSFindBy(accessibility = "Save")
    private IOSElement savefilterbtn;
	
	@iOSFindBy(accessibility  = "ServiceRequestSummaryInspectionsButton")
    private IOSElement srsummaryinspectionsbtn;
	
	@iOSFindBy(accessibility  = "Work Orders")
    private IOSElement srsummaryordrersbtn;
	
	@iOSFindBy(accessibility  = "Save")
    private IOSElement savebtn;
	
	public ServiceRequestsScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickRefreshButton() throws InterruptedException {
		refreshbtn.click();
		Thread.sleep(3000);
	}
	
	public void clickAddButton() {
		addbtn.click();
	}
	
	public void clickSearchButton() {
		searchbtn.click();
	}
	
	public void clickNotCheckedInFilterItem() {
		notcheckedinfilteritem.click();
	}
	
	public void clickSaveFilter() {
		savefilterbtn.click();
	}
	
	public void applyNotCheckedInFilter() throws InterruptedException {
		clickSearchButton();
		clickNotCheckedInFilterItem();
		clickSaveFilter();
		Thread.sleep(3000);
	}
	
	public void resetFilter() throws InterruptedException {
		clickSearchButton();
		deletefilterbtn.click();
		clickSaveFilter();
		Thread.sleep(3000);
	}
	
	public void selectServiceRequestType (String srtype) {
		Helpers.waitABit(500);
		TouchAction action = new TouchAction(appiumdriver);
		action.press(appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + srtype + "']")).waitAction(300).release().perform();
	}

	public void selectServiceRequest(String servicerequest) {
		Helpers.waitABit(5000);
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + servicerequest + "']").click();
		//Helpers.text(servicerequest).click();
	}

	public void selectCreateInspectionRequestAction() {
		createinspectionmenu.click();
	}
	
	public void selectCreateWorkOrderRequestAction() {
		createworkordermenu.click();
	}
	
	public void selectAppointmentRequestAction() {
		appointmentmenu.click();
	}
	
	public void selectCheckInMenu() throws InterruptedException {
		checkinmenu.click();
		Thread.sleep(3000);
	}
	
	public void selectUndoCheckMenu() throws InterruptedException {
		undocheckinmenu.click();
		Thread.sleep(3000);
	}
	
	public void selectRejectAction() {
		rejectmenu.click();
	}
	
	public void selectCloseAction() {
		closemenu.click();
	}
	
	public boolean isRejectActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Reject").size() > 0;
	}
	
	public boolean isCloseActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Close").size() > 0;
	}
	
	public boolean isServiceRequestExists(String srnumber) {
		return appiumdriver.findElementsByAccessibilityId(srnumber).size() > 0;
	}
	
	public void selectEditServiceRequestAction() {
		editmenu.click();
	}
	
	public void selectCancelAction() {
		cancelmenu.click();
	}

	public void selectInspectionType(String inspectiontype) {
		TouchAction action = new TouchAction(appiumdriver);
		action.press(appiumdriver.findElementByAccessibilityId(inspectiontype)).waitAction(300).release().perform();
	}
	
	public String getWorkOrderNumber() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[3]").getText();
	}
	
	public String getInspectionNumber() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[3]").getText();
	}
	
	public boolean isFirstServiceRequestOnHold() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name=\"On Hold\"]").isDisplayed();
	}
	
	public boolean isFirstServiceRequestContainsCorrectCompany(String companyname) {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name=\""
						+ companyname + "\"]").isDisplayed();
	}
	
	public boolean isFirstServiceRequestContainsCorrectEmployee(String employee) {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[contains(@name,\""
						+ employee + "\")]").isDisplayed();
	}
	
	public boolean isFirstServiceRequestContainsCorrectVIN(String VIN) {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[contains(@name,\""
						+ VIN + "\")]").isDisplayed();
	}
	
	public String getFirstServiceRequestStatus() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]").getAttribute("name");
	}
	
	public String getServiceRequestStatus(String srnumber) {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell['" + srnumber + "']/XCUIElementTypeStaticText[1]").getAttribute("name");
	}
	
	public String getFirstServiceRequestOnHoldNumber() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name=\"On Hold\"]/XCUIElementTypeStaticText[2]").getAttribute("name");
	}
	
	public String getFirstServiceRequestNumber() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]").getAttribute("name");
	}
	
	//Appointmets
	public void clickAddAppointmentButton() {
		addappointmentbtn.click();
	}
	
	public void selectTodayFromAppointmet() {
		fromfld.click();
		donebtn.click();
	}
	
	public String getFromAppointmetValue() {
		return appiumdriver.findElementByXPath("//UIAPopover[1]/UIATableView[1]/UIATableCell[@name=\"From\"]/UIATextField[1]").getAttribute("value");
	}
	
	public void selectTodayToAppointmet() {
		tofld.click();
		donebtn.click();
	}
	
	public String getToAppointmetValue() {
		return appiumdriver.findElementByXPath("//UIAPopover[1]/UIATableView[1]/UIATableCell[@name=\"To\"]/UIATextField[1]").getAttribute("value");
	}
	
	public void setSubjectAppointmet(String _subject) throws InterruptedException {
		subjectfld.click();
		Helpers.keyboadrType(_subject+"\n");
	}
	
	public void setAddressAppointmet(String _address) throws InterruptedException {
		addressfld.click();
		Helpers.keyboadrType(_address+"\n");
	}
	
	public void setCityAppointmet(String _city) throws InterruptedException {
		cityfld.click();
		Helpers.keyboadrType(_city+"\n");
	}
	
	public void selectSummaryRequestAction() {
		summarybtn.click();
	}

	public String getSummaryAppointmentsInformation() {
		return appiumdriver.findElementByXPath("//UIATableView[2]/UIATableCell[@name=\"Appointments\"]/UIAStaticText[2]").getAttribute("value");
	}
	
	public boolean isSRSummaryAppointmentsInformation() {
		return appiumdriver.findElementByXPath("//UIATableView[2]/UIATableCell[@name=\"Appointments\"]").isDisplayed();
	}
	
	public void clickServiceRequestSummaryInspectionsButton() {
		srsummaryinspectionsbtn.click();
	}
	
	public MyWorkOrdersScreen clickServiceRequestSummaryOrdersButton() {
		srsummaryordrersbtn.click();
		return new MyWorkOrdersScreen(appiumdriver);
	}
	
	public void saveAppointment() {
		savebtn.click();
	}
	
	//Close reason UIAPicker
	public void clickCancelCloseReasonDialog() {
		appiumdriver.findElement(MobileBy.AccessibilityId("Cancel")).click();
	}
			
	public void clickDoneCloseReasonDialog() {
		appiumdriver.findElement(MobileBy.AccessibilityId("Done")).click();
	}
			
	public void selectDoneReason(String selectreason) throws InterruptedException {
		selectUIAPickerValue(selectreason);
		clickDoneCloseReasonDialog();
	}
			
	public void clickCloseSR() {
		appiumdriver.findElement(MobileBy.name("Close SR")).click();
		Helpers.waitABit(3000);
	}
}
