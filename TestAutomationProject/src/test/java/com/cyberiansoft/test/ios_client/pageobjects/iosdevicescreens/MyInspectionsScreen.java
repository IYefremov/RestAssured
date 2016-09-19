package com.cyberiansoft.test.ios_client.pageobjects.iosdevicescreens;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cyberiansoft.test.ios_client.utils.Helpers;

public class MyInspectionsScreen extends iOSHDBaseScreen {
	
	final String firstinspxpath = "//UIATableView[1]/UIATableCell[1]";
	private By discardbtnxpath = By.name("Discard");
	
	@iOSFindBy(uiAutomator = ".navigationBar().buttons()[\"Add\"]")
    private IOSElement addinspbtn;
	
	@iOSFindBy(name = "Edit")
    private IOSElement editpopupmenu;
	
	@iOSFindBy(name = "Approve")
    private IOSElement approvepopupmenu;
	
	@iOSFindBy(name = "Create Work Order")
    private IOSElement createwopopupmenu;
	
	@iOSFindBy(name = "Send Email")
    private IOSElement sendmailpopupmenu;
	
	@iOSFindBy(name = "Copy")
    private IOSElement copypopupmenu;
	
	@iOSFindBy(xpath = "//UIAPopover[1]/UIATableView[1]/UIATableCell[@name='Notes']")
    private IOSElement notespopupmenu;
	
	@iOSFindBy(name = "Archive")
    private IOSElement archivepopupmenu;
	
	@iOSFindBy(name = "Change Customer")
    private IOSElement changecustomerpopupmenu;
	
	@iOSFindBy(name = "Service Request")
    private IOSElement backservicerequestsbtn;
	
	@iOSFindBy(name = "Show Work Orders")
    private IOSElement showwospopupmenu;
	
	@iOSFindBy(xpath = "//UIAPopover[1]/UIAToolbar[1]/UIAButton[@name=\"Status Reason\"]")
    private IOSElement statusreasonbtn;
	
	@iOSFindBy(xpath = "//UIAPopover[1]/UIAToolbar[1]/UIAButton[@name=\"Done\"]")
    private IOSElement popupdonebtn;
	
	@iOSFindBy(xpath = firstinspxpath)
    private IOSElement firstinspection;
	
	@iOSFindBy(xpath = firstinspxpath + "/UIAStaticText[1]")
    private IOSElement firstinspectionnumber;
	
	@iOSFindBy(xpath = firstinspxpath + "/UIAStaticText[3]")
    private IOSElement firstinspectionprice;
	
	@iOSFindBy(xpath = firstinspxpath + "/UIAStaticText[4]")
    private IOSElement firstinspectiontotalprice;
	
	@iOSFindBy(uiAutomator = ".popover().navigationBar().buttons()[\"Close\"]")
    private IOSElement closeflterpopupbtn;
	
	@iOSFindBy(uiAutomator = ".popover().navigationBar().buttons()[\"Save\"]")
    private IOSElement saveflterpopupbtn;
	
	@iOSFindBy(name = "Done")
    private IOSElement toolbardonebtn;
	
	public MyInspectionsScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickAddInspectionButton() {
		addinspbtn.click();
		if (Helpers.elementExists(discardbtnxpath)) {
			element(discardbtnxpath).click();
		}
	}
	
	public void clickBackServiceRequest() {
		backservicerequestsbtn.click();
	}

	public VehicleScreen clickEditInspectionButton() {
		editpopupmenu.click();
		return new VehicleScreen(appiumdriver);
	}
	
	public void selectInspectionForEdit(String inspnumber)  {
		selectInspectionInTable (inspnumber);
		clickEditInspectionButton();
	}
	
	public void selectInspectionForCreatingWO(String inspnumber) {
		selectInspectionInTable (inspnumber);
		clickCreateWOButton();
	}

	public void selectInspectionForApprove(String inspnumber) {
		selectInspectionInTable (inspnumber);
		clickApproveInspectionButton();
	}
	
	public void selectEmployee(String employee) {
		appiumdriver.findElementByName(employee).click();
	}
	
	public void selectInspectionForCopy(String inspnumber) throws InterruptedException {
		selectInspectionInTable (inspnumber);
		clickCopyInspection();
	}
	
	protected void clickApproveInspectionButton() {
		approvepopupmenu.click();
	}

	public void clickCreateWOButton() {
		createwopopupmenu.click();
	}
	
	public void clickRegularCreateWOButton() {
		appiumdriver.findElementByXPath("//UIAScrollView[2]/UIAButton[4]").click();
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

	public void archive Inspection(String inpection, String reason)
			throws InterruptedException {
		selectInspectionInTable (inpection);
		clickArchive InspectionButton();
		selectReasonToArchive(reason);

	}

	public void selectReasonToArchive(String reason)
			throws InterruptedException {

		selectUIAPickerValue(reason);
		statusreasonbtn.click();
		Thread.sleep(1000);
		popupdonebtn.click();
	}

	public VehicleScreen selectDefaultInspectionType() {
		text("Default inspection type").click();
		return new VehicleScreen(appiumdriver);
	}

	public void selectInspectionType (String inspectiontype) {
		Helpers.scroolToByXpath("//UIAPopover[1]/UIATableView[1]/UIATableCell[@name=\"" + inspectiontype + "\"]");
		appiumdriver.findElementByXPath("//UIAPopover[1]/UIATableView[1]/UIATableCell/UIAStaticText[@name=\"" + inspectiontype + "\"]").click();
	}

	public void selectInspectionInTable (String inspectiontype) {
		appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\"" + inspectiontype + "\"]").click();
	}
	
	public void selectFirstInspection() {
		firstinspection.click();
	}

	public String getFirstInspectionNumberValue() {		
		return firstinspectionnumber.getAttribute("label");
	}
	
	public String getFirstInspectionPriceValue() {		
		return firstinspectionprice.getAttribute("label");
	}
	
	public String getFirstInspectionTotalPriceValue() {		
		return firstinspectiontotalprice.getAttribute("label");
	}

	public void assertInspectionDoesntExists(String inspection)  {
		Assert.assertTrue(appiumdriver.findElementsByName(inspection).size() < 1);
	}

	public void assertInspectionExists(String inspection) {
		Assert.assertTrue(appiumdriver.findElementsByName(inspection).size() > 0);
	}

	public ApproveInspectionsScreen selectFirstInspectionToApprove() {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] ").click();
		return new ApproveInspectionsScreen(appiumdriver);
	}
	
	public void selectInspectionToApprove(String inspection) {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[contains(@name,\""
						+ inspection
						+ "\")]/UIAButton[@name=\"approve disabled\"] ").click();
	}

	public void clickActionButton() {
		appiumdriver.findElementByXPath("//UIAButton[@visible=\"true\" and (contains(@name,\"Share\"))] ").click();
	}

	public void clickFilterButton() {
		appiumdriver.findElementByXPath("//UIAButton[@visible=\"true\" and (contains(@name,\"filter\"))] ").click();
	}

	public boolean assertFilterIsApplied() {
		return appiumdriver.findElementByXPath("//UIAButton[@visible=\"true\" and (contains(@name,\"filter\"))] ").getAttribute("name").equals("filter pressed");
	}

	public void clearFilter() {
		clickFilterButton();
		appiumdriver.findElementByXPath("//UIAButton[@visible=\"true\" and (contains(@name,\"Clear\"))] ").click();
	}

	public void clickStatusFilter() {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[@visible=\"true\" and (contains(@name,\"Status\"))] ").click();
	}

	public void assertFilterStatusIsSelected(String filterstatus) {
		Assert.assertEquals(appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[@name=\""
								+ filterstatus + "\"] ").getAttribute("value"),"1");
	}

	public void clickFilterStatus(String filterstatus) {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAPopover[1]/UIATableView[1]/UIATableCell[@name=\""
						+ filterstatus + "\"] ").click();
	}
	
	public VehicleScreen showWorkOrdersForInspection(String inpection) {
		selectInspectionInTable (inpection);
		clickShowWorkOrdersButton();
		return new VehicleScreen(appiumdriver);
		
	}
	
	public void clickShowWorkOrdersButton() {
		showwospopupmenu.click();
	}
	
	public void clickRegularShowWorkOrdersButton() {
		appiumdriver.findElementByXPath("//UIAScrollView[2]/UIAButton[7]").click();
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
		Helpers.scroolToByXpath("//UIAPopover[1]/UIATableView[@name='CustomerSelectorTable']/UIATableCell[@name='" + customer + "']");
		
		appiumdriver.tap(1, appiumdriver.findElementByXPath("//UIAPopover[1]/UIATableView[@name='CustomerSelectorTable']/UIATableCell[@name='" + customer + "']"), 200);
	}
	
	public void changeCustomerForInspection(String inspection, String customer) throws InterruptedException {
		selectInspectionInTable (inspection);
		clickChangeCustomerpopupMenu();
		selectCustomer(customer);
	}
	
	public void customersPopupSwitchToWholesailMode() throws InterruptedException {
		if (elementExists(By.name("btnRetail"))) {
			appiumdriver.findElementByName("btnRetail").click();
		}
	}
	
	public void customersPopupSwitchToRetailMode() throws InterruptedException {
		if (elementExists(By.name("btnWholesale"))) {
			appiumdriver.findElementByName("btnWholesale").click();
		}
	}
	
	public String getNumberOfWorkOrdersForIspection() {
		String pickervalue = appiumdriver.findElementByXPath("//UIAPopover/UIAPicker[1]/UIAPickerWheel[1]").getAttribute("name");
		return pickervalue.substring(pickervalue.length()-1, pickervalue.length());
	}
	
	public boolean isWorkOrderForInspectionExists(String wonuber)
			throws InterruptedException {

		MobileElement picker = (MobileElement) appiumdriver.findElementByXPath("//UIAPopover/UIAPicker[1]");
		MobileElement pickerwheel = (MobileElement) appiumdriver.findElementByXPath("//UIAPopover/UIAPicker[1]/UIAPickerWheel[1]");
		boolean result = selectUIAPickerWheelValue(picker, pickerwheel, wonuber);
		appiumdriver.findElementByXPath("//UIAPopover/UIAToolbar[1]/UIAButton[@name=\"Cancel\"]").click();
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
		appiumdriver.findElementByXPath("//UIAPopover[1]/UIATableView[1]/UIATableCell[@name='Approve']").click();
	}
	
	public boolean isApproveInspectionMenuActionExists() {
		return appiumdriver.findElementsByXPath("//UIAPopover[1]/UIATableView[1]/UIATableCell[@name='Approve']").size() > 0;
	}
	
	public boolean isSendEmailInspectionMenuActionExists() {
		return appiumdriver.findElementsByXPath("//UIAPopover[1]/UIATableView[1]/UIATableCell[@name='Send Email']").size() > 0;
	}

	public void clickArchiveInspections() {
		clickActionButton();
		clickArchive InspectionButton();
	}

	public void selectInspectionForAction(String inspection) {
		appiumdriver.findElementByXPath("//UIATableCell[@name=\"" + inspection
						+ "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] ").click();
	}

	public void assertInspectionIsApproved(String inspection) {
		Assert.assertTrue(appiumdriver.findElementByXPath("//UIATableCell[@name=\"" + inspection
				+ "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] ").isDisplayed());
	}
	
	public boolean isInspectionIsApproveButtonExists(String inspection) {
		return appiumdriver.findElementsByXPath("//UIATableCell[@name=\"" + inspection
				+ "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] ").size() > 0;
	}
	
	public boolean isNotesIconPresentForInspection(String inspnumber) {
		return appiumdriver.findElementsByXPath("//UIATableView[1]/UIATableCell[@name=\"" + inspnumber
				+ "\"]/UIAImage[@name=\"ESTIMATION_NOTES\"]").size() > 0;
	}
	
	public NotesScreen openInspectionNotesScreen(String inspnumber) {
		appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\"" + inspnumber
				+ "\"]").click();
		notespopupmenu.click();
		return new NotesScreen(appiumdriver);
	}
}
