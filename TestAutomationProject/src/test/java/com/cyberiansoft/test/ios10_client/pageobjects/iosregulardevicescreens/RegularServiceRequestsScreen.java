package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import com.cyberiansoft.test.ios10_client.utils.Helpers;

public class RegularServiceRequestsScreen extends iOSRegularBaseScreen {
	
	@iOSFindBy(accessibility = "Add")
    private IOSElement addbtn;
	
	@iOSFindBy(accessibility = "Refresh")
    private IOSElement refreshbtn;
	
	@iOSFindBy(accessibility = "Create\nInspection")
    private IOSElement createinspectionmenu;
	
	@iOSFindBy(accessibility = "Edit")
    private IOSElement editmenu;
	
	@iOSFindBy(accessibility = "Create\nWork Order")
    private IOSElement createworkordermenu;
	
	@iOSFindBy(accessibility = "Appointments")
    private IOSElement appointmentmenu;
	
	@iOSFindBy(accessibility = "Check In")
    private IOSElement checkinmenu;
	
	@iOSFindBy(accessibility = "Close")
    private IOSElement closemenu;
	
	@iOSFindBy(accessibility = "Reject")
    private IOSElement rejectmenu;
	
	@iOSFindBy(accessibility = "Cancel")
    private IOSElement cancelmenu;
	
	//Appointment
	@iOSFindBy(accessibility = "From")
    private IOSElement fromfld;
	
	@iOSFindBy(accessibility = "To")
    private IOSElement tofld;
	
	@iOSFindBy(accessibility = "Done")
    private IOSElement donebtn;
	
	@iOSFindBy(accessibility = "Subject")
    private IOSElement subjectfld;
	
	@iOSFindBy(accessibility = "Address")
    private IOSElement addressfld;
	
	@iOSFindBy(accessibility = "City")
    private IOSElement cityfld;
	
	@iOSFindBy(accessibility = "Summary")
    private IOSElement summarybtn;
	
	@iOSFindBy(accessibility = "ServiceRequestSummaryInspectionsButton")
    private IOSElement srsummaryinspectionsbtn;
	
	@iOSFindBy(accessibility = "Work Orders")
    private IOSElement srsummaryordrersbtn;
	
	@iOSFindBy(accessibility = "Save")
    private IOSElement savebtn;
	
	public RegularServiceRequestsScreen(AppiumDriver driver) {
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
	
	public void selectServiceRequestType (String srtype) {
		appiumdriver.findElementByAccessibilityId(srtype).click();
	}

	public void selectServiceRequest(String servicerequest) {
		Helpers.waitABit(6000);
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='ServiceRequestsTable']/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + servicerequest + "']")).click();
		Helpers.waitABit(500);
	}

	public void selectCreateInspectionRequestAction() {
		createinspectionmenu.click();
	}
	
	public void selectEditServiceRequestAction() {
		editmenu.click();
	}
	
	public void selectCreateWorkOrderRequestAction() {
		createworkordermenu.click();
	}
	
	public void selectAppointmentRequestAction() {
		appointmentmenu.click();
	}
	
	public void selectCheckInAction() {
		checkinmenu.click();
		Helpers.waitABit(1000);
	}
	
	public boolean isUndoCheckInActionExists() {
		return appiumdriver.findElementsByAccessibilityId("Undo\nCheck In").size() > 0;
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
		return Helpers.elementExists(MobileBy.name(srnumber));
	}
	
	public void selectCancelAction() {
		cancelmenu.click();
	}

	public void selectInspectionType(String inspectiontype) {
		//Helpers.scroolTo(inspectiontype);
		//Helpers.text_exact(inspectiontype).click();
		//Helpers.scroolTo(inspectiontype);
		//appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()[0].cells()['" + inspectiontype + "']")).click();
		appiumdriver.findElementByAccessibilityId(inspectiontype).click();
	}
	
	public String getWorkOrderNumber() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeStaticText[contains(@name,\"O-00\")]").getText();
	}
	
	public String getInspectionNumber() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeStaticText[contains(@name,\"E-00\")]").getText();
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
		WebElement par = getSRTableParentNode(srnumber);		
		return par.findElement(MobileBy.xpath("./XCUIElementTypeStaticText[1]")).getAttribute("value");
	}
	
	public String getFirstServiceRequestOnHoldNumber() {
		WebElement par = getSRTableParentNode("On Hold");		
		return par.findElement(MobileBy.xpath("./XCUIElementTypeStaticText[2]")).getAttribute("value");
		
	}
	
	public String getFirstServiceRequestNumber() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[@name='ServiceRequestsTable']/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]").getAttribute("value");
	}
	
	//Appointmets
	
	
	public void selectTodayFromAppointmet() {
		fromfld.click();
		donebtn.click();
	}
	
	public String getFromAppointmetValue() {
		WebElement par = getSRTableParentNode("From");		
		return par.findElement(MobileBy.xpath(".//XCUIElementTypeTextField[1]")).getAttribute("value");
	}
	
	public void selectTodayToAppointmet() {
		tofld.click();
		donebtn.click();
	}
	
	public String getToAppointmetValue() {
		WebElement par = getSRTableParentNode("To");		
		return par.findElement(MobileBy.xpath(".//XCUIElementTypeTextField[1]")).getAttribute("value");
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
		WebElement par = getSRTableParentNode("Appointments");		
		return par.findElement(MobileBy.xpath(".//XCUIElementTypeStaticText[2]")).getAttribute("value");
	}
	
	public boolean isSRSummaryAppointmentsInformation() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[@name=\"ServiceRequestSummaryTable\"]/XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"Appointments\"]").isDisplayed();
	}
	
	public void clickServiceRequestSummaryInspectionsButton() {
		new TouchAction(appiumdriver).tap(appiumdriver.findElementByAccessibilityId("Inspections")).perform() ;
		//new TouchAction(appiumdriver).tap(srsummaryinspectionsbtn).perform() ;
		//srsummaryinspectionsbtn.click();
	}
	
	public void clickServiceRequestSummaryOrdersButton() {
		srsummaryordrersbtn.click();
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
	
	public WebElement getSRTableParentNode(String cellname) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='" + cellname + "']/.."));
	}

}
