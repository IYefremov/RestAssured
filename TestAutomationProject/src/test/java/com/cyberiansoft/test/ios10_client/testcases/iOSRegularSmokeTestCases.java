package com.cyberiansoft.test.ios10_client.testcases;

import io.appium.java_client.MobileBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.LicensesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.LoginScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.SinglePageInspectionScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularTeamInspectionsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularTeamInvoicesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularAddCustomerScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularApproveInspectionsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularCarHistoryScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularClaimScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularCustomersScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularHomeScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularInspectionToolBar;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularInvoiceInfoScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMainScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMyInspectionsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMyInvoicesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMyWorkOrdersScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularNotesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularOrderMonitorScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularOrderSummaryScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularPriceMatrixScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularQuestionsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularSelectedServiceBundleScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularSelectedServiceDetailsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularServiceRequestsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularServicesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularTeamWorkOrdersScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularTechRevenueScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularVehicleScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularVisualInteriorScreen;
import com.cyberiansoft.test.bo.pageobjects.webpages.ActiveDevicesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeHeaderPanel;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeLoginWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.ClientsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.InspectionsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.InvoicePaymentsTabWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.InvoicesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.MonitorWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.OperationsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.RepairOrdersWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.ServiceRequestsListWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.ServiceRequestsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.VendorOrderServicesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.WorkOrdersWebPage;
import com.cyberiansoft.test.bo.utils.WebConstants;
import com.cyberiansoft.test.ios_client.utils.AlertsCaptions;
import com.cyberiansoft.test.ios_client.utils.ExcelUtils;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import com.cyberiansoft.test.ios_client.utils.PricesCalculations;
import com.cyberiansoft.test.ios_client.utils.UtilConstants;
import com.cyberiansoft.test.bo.utils.WebDriverInstansiator;
import com.cyberiansoft.test.core.IOSRegularDeviceInfo;
import com.cyberiansoft.test.ios_client.utils.iOSInternalProjectConstants;
import com.cyberiansoft.test.ios_client.utils.iOSLogger;
import com.relevantcodes.extentreports.LogStatus;

public class iOSRegularSmokeTestCases extends BaseTestCase {

	private String regCode;
	private RegularHomeScreen homescreen;
	private final String buildtype = "regular";
	
	private final String firstname = "supermy12";
	private final String firstnamenew = "supernewmy12";
	private final String lastname = "super";
	private final String companyname = "supercompany";
	private final String street = "First streer";
	private final String city = "New York";
	private final String zip = "79031";
	private final String phone = "723-1234567";
	private final String mail = "test@cyberiansoft.com";
	private final String state = "Alberta";
	private final String country = "Canada";

	@BeforeClass
	@Parameters({ "backoffice.url", "user.name", "user.psw", "license.name" })
	public void setUpSuite(String backofficeurl, String userName, String userPassword, String licensename) throws Exception {
		initTestUser(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		testGetDeviceRegistrationCode(backofficeurl, userName, userPassword, licensename);
		testRegisterationiOSDdevice();
		ExcelUtils.setDentWizardExcelFile();
	}
	
	public void testGetDeviceRegistrationCode(String backofficeurl,
			String userName, String userPassword, String licensename) throws Exception {

		//webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);

		ActiveDevicesWebPage devicespage = PageFactory.initElements(webdriver,
				ActiveDevicesWebPage.class);

		devicespage.setSearchCriteriaByName(licensename);
		regCode = devicespage.getFirstRegCodeInTable();

		getWebDriver().quit();
		Thread.sleep(2000);
	}

	public void testRegisterationiOSDdevice() throws Exception {
		appiumdriverInicialize(buildtype);	
		appiumdriver.removeApp(IOSRegularDeviceInfo.getInstance().getDeviceBundleId());
		appiumdriver.quit();
		appiumdriverInicialize(buildtype);
		//appiumdriver.removeApp(bundleid);
		//appiumdriverInicialize();
		//appiumdriver.installApp(app.getAbsolutePath());
		//appiumdriver.launchApp();
		LoginScreen loginscreen = new LoginScreen(appiumdriver);
		loginscreen.assertRegisterButtonIsValidCaption();
		loginscreen.registeriOSDevice(regCode);
		RegularMainScreen mainscr = new RegularMainScreen(appiumdriver);
		homescreen = mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
	}
	
	//Test Case 8438:Update database on the device
	@Test(testName = "Test Case 8438:Update database on the device" ,description = "Update Database")
	public void testUpdateDatabase() throws Exception {
		resrtartApplication();
		RegularMainScreen mainscr = new RegularMainScreen(appiumdriver);
		mainscr.updateDatabase();
		RegularHomeScreen homescreen = mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		homescreen.clickStatusButton();
		homescreen.updateDatabase();
		mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		
	}

	//Test Case 8437:Updating VIN decoder
	@Test(testName = "Test Case 8437:Updating VIN decoder", description = "Update VIN")
	public void testUpdateVIN() throws Exception {
		//resrtartApplication();
		//RegularMainScreen mainscr = new RegularMainScreen(appiumdriver);
		RegularMainScreen mainscr = homescreen.clickLogoutButton();
		mainscr.updateVIN();
		RegularHomeScreen homescreen = mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		homescreen.clickStatusButton();
		homescreen.updateVIN();
		homescreen.clickHomeButton();
		//homescreen.clickLogoutButton();
	}

	//Test Case 8441:Add Retail Customer in regular build
	@Test(testName = "Test Case 8441:Add Retail Customer in regular build", description = "Create retail customer")
	public void testCreateRetailCustomer() throws Exception {

		//resrtartApplication();	
		//MainScreen mainscreen = new MainScreen(appiumdriver);
		//HomeScreen homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USER_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		RegularAddCustomerScreen addcustomerscreen = customersscreen.clickAddCustomersButton();

		addcustomerscreen.addCustomer(firstname, lastname, companyname, street,
				city, state, zip, country, phone, mail);
		addcustomerscreen.clickSaveBtn();
		customersscreen.assertCustomerExists(firstname);

		customersscreen.clickHomeButton();
		//homescreen.clickLogoutButton();
	}

	//Test Case 8439:Edit Customer 
	@Test(testName = "Test Case 8439:Edit Customer ", description = "Edit retail customer")
	public void testEditRetailCustomer() throws Exception {
		final String lastname = "superedited";
		final String companyname = "supercompanyedited";
		final String street = "Second streer";
		final String city = "New York";
		final String zip = "79035";
		final String phone = "723-1234576";
		final String mail = "test123@cyberiansoft.com";
		
		//resrtartApplication();	
		//MainScreen mainscreen = new MainScreen(appiumdriver);
		//HomeScreen homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USER_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();

		RegularAddCustomerScreen addcustomerscreen = customersscreen.selectCustomerToEdit(firstname);

		addcustomerscreen.editCustomer(firstnamenew, lastname, companyname,
				street, city, city, zip, zip, phone, mail);
		addcustomerscreen.clickSaveBtn();
		customersscreen.assertCustomerExists(firstnamenew);
		customersscreen.clickHomeButton();
		RegularMainScreen mainscreen = homescreen.clickLogoutButton();
		mainscreen.updateDatabase();
	}

	// Test Case 8460: Delete Customer 
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 8460: Delete Customer ", description = "Delete retail customer")
	public void testDeleteRetailCustomer(String backofficeurl, String userName,
			String userPassword) throws Exception {

		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Admin/Clients.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		ClientsWebPage clientspage = PageFactory.initElements(webdriver,
				ClientsWebPage.class);

		clientspage.deleteUserViaSearch(firstnamenew);

		getWebDriver().quit();
		Thread.sleep(2000);
	
		RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		mainscreen.updateDatabase();
		RegularHomeScreen homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.assertCustomerDoesntExists(firstnamenew);
		customersscreen.clickHomeButton();
		//homescreen.clickLogoutButton();
	}
	
	
	//Test Case 8435:Create Retail Inspection (HD Single page)
	@Test(testName = "Test Case 8435:Create Retail Inspection", description = "Create Retail Inspection")
	public void testCreateRetailInspection() throws Exception {
		final String VIN = "ZWERTYASDFEWSDRZG";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Red";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myinspectionsscreen.selectDefaultInspectionType();
		vehiclescreeen.clickSaveButton();
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("VIN# is required"));
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.clickSaveButton();
		alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("Make is required"));
		
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);
		vehiclescreeen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualInteriorCaption());
		vehiclescreeen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualExteriorCaption());
		String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.clickSaveButton();
		myinspectionsscreen.clickHomeButton();
	}

	//Test Case 8434:Add Services to visual inspection
	@Test(testName = "Test Case 8434:Add Services to visual inspection", description = "Add Services To Visual Inspection")
	public void testAddServicesToVisualInspection() throws Exception {
		final String _inspectionprice = "275";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.selectFirstInspection();
		myinspectionsscreen.clickEditInspectionButton();
		myinspectionsscreen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualInteriorCaption());
		RegularVisualInteriorScreen visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.assertDefaultInteriorServicesPresent();
		visualinteriorscreen.switchToCustomTab();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		visualinteriorscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		visualinteriorscreen.tapInterior();
		RegularVisualInteriorScreen.tapInteriorWithCoords(100, 100);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.clickServicesBackButton();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);
		visualinteriorscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		RegularVisualInteriorScreen.tapInteriorWithCoords(150, 150);
		RegularVisualInteriorScreen.tapInteriorWithCoords(200, 200);
		visualinteriorscreen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualExteriorCaption());
		Thread.sleep(2000);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.switchToCustomTab();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		visualinteriorscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		RegularVisualInteriorScreen.tapExterior();
		Thread.sleep(2000);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.clickServicesBackButton();
		visualinteriorscreen.assertPriceIsCorrect(PricesCalculations.getPriceRepresentation(_inspectionprice));		
		visualinteriorscreen.clickSaveButton();
		Thread.sleep(2000);
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionPriceValue(), PricesCalculations.getPriceRepresentation(_inspectionprice));
		myinspectionsscreen.clickHomeButton();
	}

	//Test Case 8433:Change Quantity of services in Visual Inspection 
	@Test(testName = "Test Case 8433:Change Quantity of services in Visual Inspection", description = "Change Quantity Of Services In Visual Inspection")
	public void testChangeQuantityOfServicesInVisualInspection()
			throws Exception {
		final String _quantity = "3.00";
		final String _quantityexterior = "2.00";
		final String _inspectionpricevisual = "275";
		final String _inspectionprice = "325";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.selectFirstInspection();
		myinspectionsscreen.clickEditInspectionButton();
		myinspectionsscreen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualInteriorCaption());
		RegularVisualInteriorScreen visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		/*visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.switchToCustomTab();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		visualinteriorscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);*/
		visualinteriorscreen.tapInterior();
		Thread.sleep(1000);
		visualinteriorscreen.setCarServiceQuantityValue(_quantity);
		visualinteriorscreen.saveCarServiceDetails();
		visualinteriorscreen.assertPriceIsCorrect(PricesCalculations.getPriceRepresentation(_inspectionpricevisual));
		visualinteriorscreen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualExteriorCaption());
		/*visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.switchToCustomTab();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);
		visualinteriorscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);*/
		RegularVisualInteriorScreen.tapExterior();
		Thread.sleep(3000);
		visualinteriorscreen.setCarServiceQuantityValue(_quantityexterior);
		visualinteriorscreen.saveCarServiceDetails();
		visualinteriorscreen.assertPriceIsCorrect(PricesCalculations.getPriceRepresentation(_inspectionprice));

		visualinteriorscreen.clickSaveButton();
		myinspectionsscreen.clickHomeButton();

	}

	//Test Case 8432:Edit the retail Inspection Notes
	@Test(testName = "Test Case 8432:Edit the retail Inspection Notes", description = "Edit Retail Inspection Notes")
	public void testEditRetailInspectionNotes() throws Exception {
		final String _notes1 = "Test\nTest 2";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.selectFirstInspection();
		RegularVehicleScreen vehiclescreeen = myinspectionsscreen.clickEditInspectionButton();
		vehiclescreeen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualInteriorCaption());
		RegularNotesScreen notesscreen = vehiclescreeen.clickNotesButton();
		notesscreen.setNotes(_notes1);
		//notesscreen.clickDoneButton();
		notesscreen.addQuickNotes();
		//notesscreen.clickDoneButton();

		notesscreen.clickSaveButton();
		vehiclescreeen.clickNotesButton();
		notesscreen.assertNotesAndQuickNotes(_notes1);
		notesscreen.clickSaveButton();
		vehiclescreeen.clickSaveButton();
		myinspectionsscreen.clickHomeButton();

	}

	//Test Case 8431:Approve inspection on device (Not Line Approval)
	@Test(testName = "Test Case 8431:Approve inspection on device (Not Line Approval)", description = "Approve Inspection On Device")
	public void testApproveInspectionOnDevice() throws Exception {

		final String VIN = "CFRTHASDFEWSDRZWM";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Red";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		//customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_NOTLA_TS_INSPTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);
		vehiclescreeen.clickSaveButton();
		Thread.sleep(8000);
		String inspection = myinspectionsscreen.getFirstInspectionNumberValue();
		myinspectionsscreen.selectInspectionForApprove(inspection);
		// approveinspscreen.selectInspectionToApprove();
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspection);
		approveinspscreen.clickApproveButton();
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		// approveinspscreen.clickBackButton();
		myinspectionsscreen.assertInspectionIsApproved(inspection);
		myinspectionsscreen.clickHomeButton();
	}

	// Test Case 8586:Archive and Un-Archive the Inspection
	@Test(testName = "Test Case 8586:Archive and Un-Archive the Inspection", description = "Archive and Un-Archive the Inspection")
	public void testArchiveAndUnArchiveTheInspection() throws Exception {

		final String VIN = "TESTVINNO";
		final String _make = "Acura";
		final String _model = "ILX";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_NOTLA_TS_INSPTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);
		vehiclescreeen.clickSaveButton();
		Helpers.waitABit(1000);
		String myinspetoarchive = myinspectionsscreen.getFirstInspectionNumberValue();
		myinspectionsscreen.clickHomeButton();

		myinspectionsscreen = homescreen.clickMyInspectionsButton();
		Helpers.waitABit(2000);
		myinspectionsscreen.archive Inspection(myinspetoarchive,
				"Reason 2");
		myinspectionsscreen.assertInspectionDoesntExists(myinspetoarchive);
		myinspectionsscreen.clickHomeButton();
		
	}

	//Test Case 8430:Create work order with type is assigned to a specific client
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 8430:Create work order with type is assigned to a specific client", description = "Create work order with type is assigned to a specific client ")
	public void testCreateWorkOrderWithTypeIsAssignedToASpecificClient(String backofficeurl, String userName, String userPassword)
			throws Exception {
		final String VIN = "ZWERTYASDFDDXZBVB";
		final String _po = "1111 2222";
		final String summ = "71.40";
		
		final String license = "Iphone_Test_Spec_Client";
		
		Thread.sleep(1000);
		RegularMainScreen mainscreen = homescreen.clickLogoutButton();
		Thread.sleep(2000);
		LicensesScreen licensesscreen = mainscreen.clickLicenses();
		licensesscreen.clickAddLicenseButtonAndAcceptAlert();

		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);

		ActiveDevicesWebPage devicespage = PageFactory.initElements(webdriver,
				ActiveDevicesWebPage.class);

		devicespage.setSearchCriteriaByName(license);
		regCode = devicespage.getFirstRegCodeInTable();

		getWebDriver().quit();
		appiumdriver.manage().timeouts().implicitlyWait(800, TimeUnit.SECONDS);
		LoginScreen loginscreen = new LoginScreen(appiumdriver);
		loginscreen.registeriOSDevice(regCode);
		Thread.sleep(2000);
		mainscreen.userLogin(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN, iOSInternalProjectConstants.USER_PASSWORD);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.SPECIFIC_CLIENT_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.SPECIFIC_CLIENT_TEST_WO1);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertDefaultServiceIsSelected();
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.assertOrderSummIsCorrect(PricesCalculations.getPriceRepresentation(summ));
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		
		ordersummaryscreen.clickSaveButton();
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("VIN# is required"));
		vehiclescreeen.setVIN(VIN);
		
		vehiclescreeen.clickSaveButton();
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectDefaultInvoiceType();
		invoiceinfoscreen.clickSaveEmptyPO();
		invoiceinfoscreen.setPO(_po);
		//ordersummaryscreen.clickSaveButton();
		invoiceinfoscreen.clickSaveAsDraft();

		myworkordersscreen.clickHomeButton();
	}

	//Test Case 8429:Creating complex calculation WO
	@Test(testName = "Test Case 8429:Creating complex calculation WO,"
			+ "Test Case 8428:Copy services of WO (regular version)", description = "Createing Complex calculation WO,"
					+ "Copy Cervices Of WO")
	public void testCreateWorkOrderWithTeamSharingOption() throws Exception {

		final String VIN = "1FTRX02W35K097028";
		final String summ = "346.23";
		final String summfinal = "91.80";
		// =======================================
		final String _dye_price = "$10.00";
		final String _dye_adjustments = "$0.50";
		// =======================================
		final String _disk_ex_srvc_price = "$100.00";
		final String _disk_ex_srvc_adjustment = "For_SP_Cl";
		final String _disk_ex_srvc_adjustment_value = "%-5.000";
		final String _disk_ex_srvc_adjustment_value_ed = "-$5.00";
		// ======================================
		final String _pricematrix = "HOOD";
		final String _size = "NKL";
		final String _severity = "VERY LIGHT";
		final String _price = "$100.00";
		final String _discaunt_us = "Discount 10-20$";

		//resrtartApplication();	
		//MainScreen mainscreen = new MainScreen(appiumdriver);
		//HomeScreen homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USER_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		homescreen = new RegularHomeScreen(appiumdriver);	
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		vehiclescreeen.clickSaveButton();
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("VIN# is required"));
		
		vehiclescreeen.setVIN(VIN);
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DYE_SERVICE);
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DYE_SERVICE);
		
		selectedservicescreen.assertServicePriceValue(_dye_price);
		selectedservicescreen.assertServiceAdjustmentsValue(_dye_adjustments);
		selectedservicescreen.setServiceQuantityValue("3.00");
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DYE_SERVICE);
		// =====================================
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		selectedservicescreen.assertServicePriceValue(_disk_ex_srvc_price);
		selectedservicescreen.clickAdjustments();
		selectedservicescreen.assertAdjustmentValue(_disk_ex_srvc_adjustment,
				_disk_ex_srvc_adjustment_value);
		selectedservicescreen.selectAdjustment(_disk_ex_srvc_adjustment);
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen
				.assertServiceAdjustmentsValue(_disk_ex_srvc_adjustment_value_ed);
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		// =====================================
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		RegularSelectedServiceBundleScreen selectedservicebundlescreen = new RegularSelectedServiceBundleScreen(appiumdriver);
		selectedservicebundlescreen.assertBundleIsSelected(iOSInternalProjectConstants.WHEEL_SERVICE);
		selectedservicebundlescreen.assertBundleIsNotSelected(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicebundlescreen.selectBundle(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicebundlescreen.openBundleInfo(iOSInternalProjectConstants.WHEEL_SERVICE);
		selectedservicescreen.setServiceQuantityValue("2.00");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		// =====================================
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.TEST_TAX_SERVICE);

		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		// =====================================
		servicesscreen.selectSubService(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		RegularPriceMatrixScreen pricematrix = servicesscreen.selectServicePriceMatrices(iOSInternalProjectConstants.HAIL_MATRIX_SERVICE);
		pricematrix.selectPriceMatrix(_pricematrix);
		pricematrix.setSizeAndSeverity(_size, _severity);
		pricematrix.assertPriceCorrect(_price);
		pricematrix.assertNotesExists();
		pricematrix.clickDiscaunt(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		pricematrix.selectDiscaunt(_discaunt_us);
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.assertOrderSummIsCorrect(PricesCalculations.getPriceRepresentation(summ));
		ordersummaryscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
	
		myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.selectWorkOrder(wonum);
		myworkordersscreen.selectCopyServices();
		customersscreen.selectCustomer(iOSInternalProjectConstants.SPECIFIC_CLIENT_CUSTOMER);
		vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.SPECIFIC_CLIENT_TEST_WO1);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.assertOrderSummIsCorrect(PricesCalculations.getPriceRepresentation(summfinal));
		ordersummaryscreen.clickSaveButton();		
		Thread.sleep(300);
		myworkordersscreen.clickHomeButton();

	}

	//todo
	@Test(testName = "Approve Inspections On Device via Action", description = "Approve Inspections On Device via Action")
	public void testApproveInspectionsOnDeviceViaAction() throws Exception {

		final String VIN = "TESTVINN";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Red";
		String[] inpections = { "", "" };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.clickAddInspectionButton();
			customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
			myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_NOTLA_TS_INSPTYPE);
			RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
			vehiclescreeen.setVIN(VIN);
			vehiclescreeen.setMakeAndModel(_make, _model);
			vehiclescreeen.setColor(_color);
			vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);

			vehiclescreeen.clickSaveButton();
			inpections[i] = myinspectionsscreen.getFirstInspectionNumberValue();
		}

		myinspectionsscreen.clickActionButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.selectInspectionForAction(inpections[i]);
		}
		
		myinspectionsscreen.clickApproveInspections();
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		//Helpers.waitABit(2000);
		
		//Helpers.acceptAlert();
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		for (int i = 0; i < 2; i++) {
			approveinspscreen.selectInspection(inpections[i]);
			approveinspscreen.clickApproveButton();
		}
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		
		// }

		// approveinspscreen.clickBackButton();
		//myinspectionsscreen.clickDoneButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.assertInspectionIsApproved(inpections[i]);
		}
		myinspectionsscreen.clickHomeButton();
	}

	//todo
	@Test(testName = "Archive Inspections On Device via Action", description = "Archive Inspections On Device via Action")
	public void testArchiveInspectionsOnDeviceViaAction() throws Exception {

		final String VIN = "TESTVINN";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Red";
		String[] inpections = { "", "" };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.clickAddInspectionButton();
			customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
			myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_NOTLA_TS_INSPTYPE);
			RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
			vehiclescreeen.setVIN(VIN);
			vehiclescreeen.setMakeAndModel(_make, _model);
			vehiclescreeen.setColor(_color);
			vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);

			vehiclescreeen.clickSaveButton();
			inpections[i] = myinspectionsscreen.getFirstInspectionNumberValue();
		}

		myinspectionsscreen.clickActionButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.selectInspection(inpections[i]);
		}
		myinspectionsscreen.clickArchiveInspections();
		myinspectionsscreen.selectReasonToArchive("Reason 2");
		//myinspectionsscreen.clickDoneButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.assertInspectionDoesntExists(inpections[i]);
		}
		myinspectionsscreen.clickHomeButton();
	}

	//Test Case 8467:Approve inspection on back office (full inspection approval)
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 8467:Approve inspection on back office (full inspection approval)", description = "Approve inspection on back office (full inspection approval)")
	public void testApproveInspectionOnBackOfficeFullInspectionApproval(
			String url, String userName, String userPassword) throws Exception {

		final String VIN = "TESTVINNO";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Red";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_NOTLA_TS_INSPTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		String inpectionnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);

		vehiclescreeen.clickSaveButton();
		myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		String inpection = myinspectionsscreen.getFirstInspectionNumberValue();
		myinspectionsscreen.clickHomeButton();
		RegularMainScreen mainscreen = homescreen.clickLogoutButton();
		mainscreen.updateDatabase();
		

		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Company/Inspections.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InspectionsWebPage inspectionspage = PageFactory.initElements(
				webdriver, InspectionsWebPage.class);

		inspectionspage.approveInspectionByNumber(inpectionnumber);

		getWebDriver().quit();
		
		mainscreen.updateDatabase();
		mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.assertInspectionIsApproved(inpection);

		myinspectionsscreen.clickHomeButton();
		
	}

	//Test Case 8463:Approve inspection on back office (line-by-line approval) 
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 8463:Approve inspection on back office (line-by-line approval) ", description = "Approve inspection on back office (line-by-line approval)")
	public void testApproveInspectionOnBackOfficeLinebylineApproval(String url,
			String userName, String userPassword) throws Exception {

		final String VIN = "TESTVINNO";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Red";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_LA_DA_INSPTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		String inpectionnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);

		vehiclescreeen.clickSaveButton();
		String inpection = myinspectionsscreen.getFirstInspectionNumberValue();
		myinspectionsscreen.clickHomeButton();
		RegularMainScreen mainscreen = homescreen.clickLogoutButton();
		mainscreen.updateDatabase();
		Helpers.waitABit(10000);

		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Company/Inspections.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InspectionsWebPage inspectionspage = PageFactory.initElements(
				webdriver, InspectionsWebPage.class);

		inspectionspage.approveInspectionLinebylineApprovalByNumber(
				inpectionnumber, iOSInternalProjectConstants.DISC_EX_SERVICE1, iOSInternalProjectConstants.DYE_SERVICE);

		getWebDriver().quit();
		Thread.sleep(2000);

		mainscreen.updateDatabase();
		mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.assertInspectionIsApproved(inpection);

		myinspectionsscreen.clickHomeButton();
	}

	// Test Case 8442: Creating Inspection From Service Request
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 8442: Creating Inspection From Service Request", description = "Creating Inspection From Service Request")
	public void testCreatingInspectionFromServiceRequest(String url,
			String userName, String userPassword) throws Exception {

		final String customer = "Company2";
		final String vin = "TESTVNN1";

		// final String servicerequest = "Fn1 Lm1, VIN#:TESTVNN1";
		final String servicerequest = "Fn1 Lm1";
		final String price = "314.15";

		
		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Company/ServiceRequests.aspx");
		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		
		ServiceRequestsWebPage servicerequestpage = PageFactory.initElements(
				webdriver, ServiceRequestsWebPage.class);

		servicerequestpage.clickEditBtn();
		servicerequestpage.clickCustomerTab();
		servicerequestpage.selectCustomer(customer);
		servicerequestpage.clickVehicleInfoTab();
		servicerequestpage.setVIN(vin);
		servicerequestpage.clickServicesTab();
		servicerequestpage.selectSelectServiceType(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicerequestpage.selectSelectServiceType(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicerequestpage.setServiceTypeQuantity(iOSInternalProjectConstants.TEST_TAX_SERVICE, "1");
		servicerequestpage.setServiceTypeQuantity(iOSInternalProjectConstants.WHEEL_SERVICE, "3");
		servicerequestpage.clickSaveBtn();
		getWebDriver().quit();
			
		
		resrtartApplication();
		RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		RegularHomeScreen homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);

		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickRefreshButton();
		servicerequestsscreen.selectServiceRequest(servicerequest);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.INSP_FOR_SR_INSPTYPE);
		String inspnumber = servicerequestsscreen.getInspectionNumber();
		// vehiclescreeen.clickStepsButton();
		// vehiclescreeen.selectServicesAllServicesStep();
		servicerequestsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.assertTotalAmauntIsCorrect(PricesCalculations.getPriceRepresentation(price));
		servicesscreen.clickSaveButton();
		servicerequestsscreen.clickHomeButton();
		homescreen.clickLogoutButton();
		mainscreen.updateDatabase();

		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Company/Inspections.aspx");

		loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InspectionsWebPage inspectionspage = PageFactory.initElements(
				webdriver, InspectionsWebPage.class);

		inspectionspage.assertInspectionPrice(inspnumber, PricesCalculations.getPriceRepresentation(price));
		getWebDriver().quit();
		Thread.sleep(2000);
	}
	
	//Test Case 20786:Creating Service Request with Inspection, WO and Appointment required on device
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 20786:Creating Service Request with Inspection, WO and Appointment required on device", description = "Creating Service Request with Inspection, WO and Appointment required on device")
	public void testCreatingServiceRequestWithInspectionWOAndAppointmentRequiredOnDevice(String backofficeurl, String userName, String userPassword) throws Exception {
		final String VIN = "QWERTYUI123";
		final String _make = "BMW";
		final String _model = "323i U";
		final String _color = "Red";
		final String mileage = "77777";
		final String fueltanklevel = "25";
		final String _type = "Used";
		final String stock = "Stock1";
		final String _ro = "123";	
		final String licplate = "456";
		final String _year = "2012";
		
		final String teamname= "Default team";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		
		servicerequestsscreen.clickAddButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.TEST_COMPANY_CUSTOMER);
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_EST_WO_REQ_SRTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		//vehiclescreeen.setLicensePlate(licplate);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		//servicesscreen.searchServiceByName(iOSInternalProjectConstants.WHEEL_SERVICE);
		
		
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.selectService("Quest_Req_Serv");
		RegularSelectedServiceDetailsScreen selectedservicedetailsscreen =  servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.WHEEL_SERVICE);
		selectedservicedetailsscreen.setServiceQuantityValue("3");
		selectedservicedetailsscreen.saveSelectedServiceDetails();
		//servicesscreen.setSelectedServiceRequestServicesQuantity(iOSInternalProjectConstants.WHEEL_SERVICE, "3");
		servicesscreen.clickAddServicesButton();
		servicesscreen.selectNextScreen(UtilConstants.CLAIM_SCREEN_CAPTION);
		RegularClaimScreen claimscreen = new RegularClaimScreen(appiumdriver);
		claimscreen.selectInsuranceCompany("USG");
		servicesscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Question 'Signature' in section 'Follow up Requested' should be answered.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		//Helpers.swipeRegularScreenUp();
		questionsscreen.drawRegularSignature();
		servicesscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Question 'Tax_Point_1' in section 'BATTERY PERFORMANCE' should be answered.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		Thread.sleep(4000);
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.swipeScreenUp();
		questionsscreen.selectTaxPoint("Test Answer 1");
		servicesscreen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndCancel();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CREATE_APPOINTMENT);
		Thread.sleep(5000);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "On Hold");
		Assert.assertTrue(servicerequestsscreen.getServiceRequestClient(srnumber).contains(iOSInternalProjectConstants.TEST_COMPANY_CUSTOMER));
		Assert.assertTrue(servicerequestsscreen.getServiceRequestEmployee(srnumber).contains(iOSInternalProjectConstants.USERSIMPLE_LOGIN));
		Assert.assertTrue(servicerequestsscreen.getServiceRequestVehicleInfo(srnumber).contains("WERTYU123"));
		servicerequestsscreen.clickHomeButton();
		
		
		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Company/Inspections.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);		
		OperationsWebPage operationspage = backofficeheader.clickOperationsLink();
		
		ServiceRequestsListWebPage servicerequestslistpage = operationspage.clickNewServiceRequestLink();
		servicerequestslistpage.makeSearchPanelVisible();
		
		servicerequestslistpage.verifySearchFieldsAreVisible();
		
		servicerequestslistpage.selectSearchStatus("All On Scheduled");
		servicerequestslistpage.selectSearchTeam(teamname);
		servicerequestslistpage.selectSearchTechnician("Employee Simple 20%");
		servicerequestslistpage.setSearchFreeText("WERTYU123");
		servicerequestslistpage.clickFindButton();
		servicerequestslistpage.verifySearchResultsByServiceName("Test Company (Universal Client)");
		//Assert.assertTrue(servicerequestslistpage.isFirstServiceRequestFromListHasStatusOnHold());
		servicerequestslistpage.selectFirstServiceRequestFromList();
		Assert.assertEquals(servicerequestslistpage.getVINValueForSelectedServiceRequest(), "WERTYU123");
		Assert.assertEquals(servicerequestslistpage.getCustomerValueForSelectedServiceRequest(), "Test Company (Universal Client)");
		Assert.assertEquals(servicerequestslistpage.getEmployeeValueForSelectedServiceRequest(), "Employee Simple 20% (Default team)");
		Assert.assertTrue(servicerequestslistpage.isServiceIsPresentForForSelectedServiceRequest("Bundle1_Disc_Ex $150.00 (1.00)"));
		Assert.assertTrue(servicerequestslistpage.isServiceIsPresentForForSelectedServiceRequest("Quest_Req_Serv $10.00 (1.00)"));
		Assert.assertTrue(servicerequestslistpage.isServiceIsPresentForForSelectedServiceRequest("Wheel $70.00 (3.00)"));
		getWebDriver().quit();
	}
	
	//Test Case 21582:Create Inspection from Service request
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 21582:Create Inspection from Service request", description = "Create Inspection from Service request"/*,
			dependsOnMethods = { "testCreatingServiceRequestWithInspectionWOAndAppointmentRequiredOnDevice" }*/)
	public void testCreateInspectionFromServiceRequest(String backofficeurl, String userName, String userPassword) throws Exception {
		final String summ= "438.60";
			
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		Thread.sleep(3000);
		final String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		Thread.sleep(8000);
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.INSP_FOR_SR_INSPTYPE);
		
		Thread.sleep(3000);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		//Helpers.swipeScreen();
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelectedWithServiceValues(iOSInternalProjectConstants.WHEEL_SERVICE, "$70.00 x 3.00");
		servicesscreen.assertServiceIsSelectedWithServiceValues(iOSInternalProjectConstants.BUNDLE1_DISC_EX, "$150.00");
		servicesscreen.assertServiceIsSelectedWithServiceValues("Quest_Req_Serv", "$10.00 x 1.00");
		RegularSelectedServiceDetailsScreen selectedservicedetailsscreen = servicesscreen.openCustomServiceDetails("Quest_Req_Serv");
		selectedservicedetailsscreen.answerTaxPoint1Question("Test Answer 1");
		selectedservicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickSaveButton();	
		Thread.sleep(5000);
		homescreen = servicerequestsscreen.clickHomeButton();
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.assertInspectionExists(inspnumber);
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionPriceValue(), PricesCalculations.getPriceRepresentation(summ));
		myinspectionsscreen.clickActionButton();
		myinspectionsscreen.selectInspectionForAction(inspnumber);
		
		myinspectionsscreen.clickApproveInspections();
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		//Helpers.acceptAlert();
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspnumber);		
		approveinspscreen.clickApproveButton();
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		//myinspectionsscreen.clickDoneButton();
		myinspectionsscreen.clickHomeButton();
	}
	
	//Test Case 21585:Create WO from Service Request
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	@Test(testName = "Test Case 21585:Create WO from Service Request", description = "Create WO from Service Request"/*,
			dependsOnMethods = { "testCreateInspectionFromServiceRequest" }*/)
	public void testCreateWOFromServiceRequest(String backofficeurl, String userName, String userPassword) throws Exception {

		/*appiumdriver.closeApp();
		Thread.sleep(60*1000*15);
		
		appiumdriverInicialize();
		String srnum = "R-00006200";
		RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		*/
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		homescreen = customersscreen.clickHomeButton();
		
		//test case		
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		Thread.sleep(5000);
		servicerequestsscreen.selectServiceRequest(iOSInternalProjectConstants.TEST_COMPANY_CUSTOMER);
		Thread.sleep(2000);
		servicerequestsscreen.selectCreateWorkOrderRequestAction();
		Thread.sleep(5000);
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.WO_FOR_SR);
		Thread.sleep(2000);
		String wonumber = servicerequestsscreen.getWorkOrderNumber();

		servicerequestsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectService("Other");
		//servicesscreen.assertServiceIsSelectedWithServiceValues(iOSInternalProjectConstants.WHEEL_SERVICE, "$70.00 x 3.00");
		//servicesscreen.assertServiceIsSelectedWithServiceValues(iOSInternalProjectConstants.BUNDLE1_DISC_EX, "$70.00");
		
		RegularSelectedServiceDetailsScreen selectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		selectedservicedetailsscreen.changeAmountOfBundleService("70");
		selectedservicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickBackServicesButton();
		servicesscreen.clickSaveButton();
		
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Thread.sleep(8000);
		servicerequestsscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.woExists(wonumber);
		homescreen = myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 16640:Create Inspection from Invoice with two WOs
	@Test(testName = "Test Case 16640:Create Inspection from Invoice with two WOs", description = "Create Inspection from Invoice with two WOs")
	public void testCreateInspectionFromInvoiceWithTwoWOs() throws Exception {
		
		final String VIN = "QWERTYUI123";
		final String _make = "BMW";
		final String _model = "323i U";
		final String _year = "2014";
		final String _color = "Black";
		final String mileage = "77777";
		final String fueltanklevel = "25";
		final String _type = "Used";	
		final String stock = "Stock1";
		final String _ro = "123";
		
		final String ordersumm = "13.50";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Cowl, Other");
		selectedservicescreen.selectVehiclePart("Hood");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.assertOrderSummIsCorrect(PricesCalculations.getPriceRepresentation(ordersumm));
		
		ordersummaryscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Helpers.waitABit(1000);
		myworkordersscreen.approveWorkOrder(wonumber1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
			
		myworkordersscreen.selectWorkOrder(wonumber1);
		myworkordersscreen.selectCopyVehicle();
		Thread.sleep(1000);
		customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		Assert.assertEquals(vehiclescreeen.getMake(), _make);
		Assert.assertEquals(vehiclescreeen.getModel(), _model);
		Assert.assertEquals(vehiclescreeen.getYear(), _year);
		vehiclescreeen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectDefaultInvoiceType();
		invoiceinfoscreen.setPO("23");
		invoiceinfoscreen.addWorkOrder(wonumber1);
		invoiceinfoscreen.assertOrderSummIsCorrect(PricesCalculations.getPriceRepresentation(ordersumm));
		invoiceinfoscreen.clickSaveAsDraft();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 16664:Create Invoice from WO in "My WOs" list
	@Test(testName = "Test Case 16664:Create Invoice from WO in \"My WOs\" list", description = "Create Invoice from WO in My WOs list")
	public void testCreateInvoiceFromWOInMyWOsList() throws Exception {
		
		final String VIN = "QWERTYUI123";
		final String _make = "BMW";
		final String _model = "323i U";
		final String _year = "2012";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setYear(_year);

		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		Thread.sleep(2000);
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicescreen.setServiceQuantityValue("3");
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		selectedservicescreen.clickAdjustments();
		selectedservicescreen.selectAdjustment("For_SP_Cl");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.assertTotalAmauntIsCorrect("$108.50");
		
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		selectedservicescreen.selectBundle(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicescreen.changeBundleQuantity(iOSInternalProjectConstants.WHEEL_SERVICE, "2");
		
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		RegularPriceMatrixScreen pricematrix = selectedservicescreen.selectMatrics(iOSInternalProjectConstants.HAIL_MATRIX_SERVICE);
		pricematrix.selectPriceMatrix("HOOD");
		pricematrix.setSizeAndSeverity("NKL", "VERY LIGHT");
		pricematrix.assertNotesExists();
		Assert.assertTrue(pricematrix.getTechniciansValue().contains("Employee Simple 20%"));
		pricematrix.assertPriceCorrect("$100.00");
		pricematrix.selectDiscaunt("Discount 10-20$");
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DYE_SERVICE);
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		pricematrix.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Helpers.waitABit(1000);
		myworkordersscreen.approveWorkOrder(wonumber1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen.clickCreateInvoiceIconForWO(wonumber1);
		myworkordersscreen.clickInvoiceIcon();
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.DEFAULT_INVOICETYPE);
		invoiceinfoscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("PO# is required")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		invoiceinfoscreen.setPO(iOSInternalProjectConstants.USER_PASSWORD);
		String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsDraft();
		
		myworkordersscreen.clickHomeButton();
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.myInvoiceExists(invoicenumber);
		myinvoicesscreen.clickHomeButton();
	}
	
	//Test Case 18426:Don't allow to select billed and not billed orders together in multi selection mode
	@Test(testName = "Test Case 18426:Don't allow to select billed and not billed orders together in multi selection mode", description = "Don't allow to select billed and not billed orders together in multi selection mode")
	public void testDontAlowToSelectBilleAandNotBilledOrdersTogetherInMultiSelectionMode() throws Exception {

		final String VIN = "QWERTYUI123";
		final String _make = "BMW";
		final String _model = "323i U";
		final String _year = "2012";
		final String _color = "Black";
		final String mileage = "77777";
		final String fueltanklevel = "25";
		final String _type = "Used";	
		final String stock = "Stock1";
		final String _ro = "123";
		
		//Create WO1
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService("VPS1");
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.clickSaveButton();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Helpers.waitABit(1000);
		//Create WO2
		myworkordersscreen.clickAddOrderButton();
		
		//customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		Helpers.waitABit(1000);
		String wonumber2 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService("VPS1");
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.clickSaveButton();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
			
		//Test case
		myworkordersscreen.approveWorkOrder(wonumber1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen.approveWorkOrder(wonumber2, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		
		myworkordersscreen.clickCreateInvoiceIconForWO(wonumber1);
		myworkordersscreen.clickInvoiceIcon();
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.DEFAULT_INVOICETYPE);
		invoiceinfoscreen.setPO("first123");
		invoiceinfoscreen.clickSaveAsDraft();
		Helpers.waitABit(1000);
		myworkordersscreen.clickFilterButton();
		myworkordersscreen.setFilterBilling("All");
		myworkordersscreen.clickSaveFilter();
		
		myworkordersscreen.clickActionButton();		
		myworkordersscreen.selectWorkOrderForAction(wonumber1);
		//myworkordersscreen.woExistsViaSearch(wonumber1);
		myworkordersscreen.clickDoneButton();
		Helpers.waitABit(1000);
		myworkordersscreen.clickFilterButton();
		myworkordersscreen.setFilterBilling("All");
		myworkordersscreen.clickSaveFilter();
		
		myworkordersscreen.clickActionButton();
		myworkordersscreen.selectWorkOrderForAction(wonumber2);
		//myworkordersscreen.woExistsViaSearch(wonumber2);
		myworkordersscreen.clickDoneButton();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 18436:Don't allow to change billed orders
	@Test(testName = "Test Case 18436:Don't allow to change billed orders", description = "Don't allow to change billed orders")
	public void testDontAlowToChangeBilledOrders() throws Exception {
		
		final String VIN = "QWERTYUI123";
		final String _make = "BMW";
		final String _model = "323i U";
		final String _year = "2012";
		final String _color = "Black";
		final String mileage = "77777";
		final String fueltanklevel = "25";
		final String _type = "Used";	
		final String stock = "Stock1";
		final String _ro = "123";
		final String[] menuitemstoverify = { "Edit" , "Notes", "Change\nstatus", "Delete", "Create\nInvoices" };
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		
		//Create WO1
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService("VPS1");
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.clickSaveButton();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
	
		
		//Test case
		
		myworkordersscreen.approveWorkOrder(wonumber1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);		
		myworkordersscreen.clickCreateInvoiceIconForWO(wonumber1);
		myworkordersscreen.clickInvoiceIcon();
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.DEFAULT_INVOICETYPE);
		invoiceinfoscreen.setPO("first123");
		invoiceinfoscreen.clickSaveAsDraft();
		
		myworkordersscreen.clickFilterButton();
		myworkordersscreen.setFilterBilling("All");
		myworkordersscreen.clickSaveFilter();

		myworkordersscreen.selectWorkOrder(wonumber1);
		for (int i = 0; i < menuitemstoverify.length; i++) {
			myworkordersscreen.isMenuItemForSelectedWOExists(menuitemstoverify[i]);
		}
		myworkordersscreen.clickDetailspopupMenu();
		servicesscreen.clickCancel();	
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 18439:Change customer for invoice
	@Test(testName = "Test Case 18439:Change customer for invoice", description = "Change customer for invoice")
	public void testChangeCustomerForInvoice() throws Exception {
		
		final String VIN = "QWERTYUI123";
		final String _make = "BMW";
		final String _model = "323i U";
		final String _year = "2012";
		final String _color = "Black";
		final String mileage = "77777";
		final String fueltanklevel = "25";
		final String _type = "Used";	
		final String stock = "Stock1";
		final String _ro = "123";	

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		//Create WO1
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService("VPS1");
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.clickSaveButton();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Helpers.waitABit(1000);
		
		//Test case
		
		myworkordersscreen.approveWorkOrder(wonumber1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen.clickCreateInvoiceIconForWO(wonumber1);
		myworkordersscreen.clickInvoiceIcon();
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.DEFAULT_INVOICETYPE);
		invoiceinfoscreen.setPO("first123");
		String invoicenumber = invoiceinfoscreen.getInvoiceNumber(); 
		invoiceinfoscreen.clickSaveAsDraft();
		myworkordersscreen.clickHomeButton();
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		Thread.sleep(60000);
		myinvoicesscreen.changeCustomerForInspection(invoicenumber, iOSInternalProjectConstants.SPECIFIC_CLIENT_CUSTOMER);
		Thread.sleep(4000);
		myinvoicesscreen.selectInvoice(invoicenumber);
		myinvoicesscreen.clickEditPopup();
		Assert.assertEquals(invoiceinfoscreen.getInvoiceCustomer(), iOSInternalProjectConstants.SPECIFIC_CLIENT_CUSTOMER);
		invoiceinfoscreen.clickWO(wonumber1);
 		Assert.assertEquals(vehiclescreeen.getWorkOrderCustomer(), iOSInternalProjectConstants.SPECIFIC_CLIENT_CUSTOMER);
 		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen.cancelOrder();
		Helpers.waitABit(500);
		invoiceinfoscreen.cancelInvoice();
		myinvoicesscreen.clickHomeButton();
	}
	
	//Test Case 10498:Regression test: test bug with crash on "Copy Vehicle" 
	@Test(testName = "Test Case 10498:Regression test: test bug with crash on \"Copy Vehicle\"", description = "Regression test: test bug with crash on Copy Vehicle")
	public void testBugWithCrashOnCopyVehicle() throws Exception {

		final String vin = "SHDHBEVDHDHDGDVDG";
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCarHistoryScreen carhistoryscreen = homescreen.clickCarHistoryButton();
		Helpers.waitABit(1000);
		//carhistoryscreen.searchCar(vin);
			
		carhistoryscreen.clickFirstCarHistoryInTable();
		carhistoryscreen.clickCarHistoryMyWorkOrders();
		RegularMyWorkOrdersScreen myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.selectFirstOrder();
		myworkordersscreen.selectCopyVehicle();
		Thread.sleep(1000);
		customersscreen = new RegularCustomersScreen(appiumdriver);
		//customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		myworkordersscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
		carhistoryscreen.clickHomeButton();
		//carhistoryscreen.clickCancelSearchButton();
		carhistoryscreen.clickHomeButton();
	}
	
	//Test Case 16239:Copy Inspections
	@Test(testName = "Test Case 16239:Copy Inspections", description = "Copy Inspections")
	public void testCopyInspections() throws Exception {
		
		final String VIN = "QWERTYUI123";
		final String _make = "Audi";
		final String _model = "A1";
		final String _year = "2014";
		final String _color = "Black";
		final String mileage = "55555";
		final String fueltanklevel = "25";
		final String _type = "New";	
		final String stock = "Stock1";
		final String _ro = "123";	

		final String visualjetservice = "Price Adjustment";
		
		//Services variables
		final String _dye_price = "$10.00";
		final String _dye_adjustments = "$0.50";
		// =======================================
		final String _disk_ex_srvc_price = "$100.00";
		final String _disk_ex_srvc_adjustment = "For_SP_Cl";
		final String _disk_ex_srvc_adjustment_value = "%-5.000";
		final String _disk_ex_srvc_adjustment_value_ed = "-$5.00";
		// ======================================
		final String _pricematrix = "HOOD";
		final String _size = "NKL";
		final String _severity = "VERY LIGHT";
		final String _price = "$100.00";
		final String _discaunt_us = "Discount 10-20$";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.FOR_COPY_INSP_INSPTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		vehiclescreeen.selectNextScreen(RegularVisualInteriorScreen.getVisualInteriorCaption());
		RegularVisualInteriorScreen visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		visualinteriorscreen.tapInterior();
		
		visualinteriorscreen.selectNextScreen("Future Audi Car");
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);	
		Helpers.tapRegularCarImage();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);	
		visualinteriorscreen.assertPriceIsCorrect("$180.50");
		
		visualinteriorscreen.selectNextScreen(RegularVisualInteriorScreen.getVisualExteriorCaption());
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);	
		RegularVisualInteriorScreen.tapExterior();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);	
		visualinteriorscreen.assertPriceIsCorrect("$250.50");
			
		visualinteriorscreen.selectNextScreen("Futire Jet Car");
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.selectService(visualjetservice);
		Helpers.tapRegularCarImage();
		visualinteriorscreen.selectService(visualjetservice);
		visualinteriorscreen.assertPriceIsCorrect("$240.50");
		
		visualinteriorscreen.selectNextScreen("Test Section");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.setEngineCondition("Really Bad");
		questionsscreen.swipeScreenUp();
		Thread.sleep(2000);
		questionsscreen.swipeScreenUp();
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.setJustOnePossibleAnswer("One");
		
		questionsscreen.selectNextScreen("Follow up Requested");
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.drawRegularSignature();
		Thread.sleep(2000);
		questionsscreen.swipeScreenUp();
		Thread.sleep(2000);
		questionsscreen.swipeScreenUp();
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.setSampleQuestion("Answers 1");
		
		questionsscreen.selectNextScreen("BATTERY PERFORMANCE");
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.setBetteryTerminalsAnswer("Immediate Attention Required");
		questionsscreen.swipeScreenUp();
		questionsscreen.setCheckConditionOfBatteryAnswer("Immediate Attention Required");
		
		questionsscreen.swipeScreenUp();
		Thread.sleep(2000);
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.selectTaxPoint("Test Answer 1");
		
		//Select services
		questionsscreen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DYE_SERVICE);
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicescreen.assertServicePriceValue(_dye_price);
		selectedservicescreen.assertServiceAdjustmentsValue(_dye_adjustments);
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DYE_SERVICE);
		// =====================================
		servicesscreen.selectService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		selectedservicescreen.assertServicePriceValue(_disk_ex_srvc_price);
		selectedservicescreen.clickAdjustments();
		selectedservicescreen.assertAdjustmentValue(_disk_ex_srvc_adjustment,
				_disk_ex_srvc_adjustment_value);
		selectedservicescreen.selectAdjustment(_disk_ex_srvc_adjustment);
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen
				.assertServiceAdjustmentsValue(_disk_ex_srvc_adjustment_value_ed);
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		// =====================================
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		// =====================================
		servicesscreen.selectService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		selectedservicescreen.saveSelectedServiceDetails();		
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		// =====================================
		servicesscreen.selectSubService(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		RegularPriceMatrixScreen pricematrix = servicesscreen.selectServicePriceMatrices(iOSInternalProjectConstants.HAIL_MATRIX_SERVICE);
		Helpers.waitABit(500);
		pricematrix.selectPriceMatrix(_pricematrix);
		pricematrix.setSizeAndSeverity(_size, _severity);
		pricematrix.assertPriceCorrect(_price);
		pricematrix.assertNotesExists();
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DYE_SERVICE);
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		pricematrix.selectDiscaunt(_discaunt_us);
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickSaveButton();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Thread.sleep(8000);
		myinspectionsscreen.selectFirstInspection();
		myinspectionsscreen.clickCopyInspection();
		Thread.sleep(1000);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		Assert.assertEquals(vehiclescreeen.getMake(), _make);
		Assert.assertEquals(vehiclescreeen.getModel(), _model);
		Assert.assertEquals(vehiclescreeen.getYear(), _year);
		
		vehiclescreeen.selectNextScreen(RegularVisualInteriorScreen.getVisualInteriorCaption());		
		vehiclescreeen.selectNextScreen("Future Audi Car");
		vehiclescreeen.selectNextScreen(RegularVisualInteriorScreen.getVisualExteriorCaption());	
		vehiclescreeen.selectNextScreen("Futire Jet Car");
		vehiclescreeen.selectNextScreen("Follow up Requested");
		SinglePageInspectionScreen singlepageinspectionscreen = new SinglePageInspectionScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		Assert.assertTrue(singlepageinspectionscreen.isSignaturePresent());
		vehiclescreeen.selectNextScreen("BATTERY PERFORMANCE");
		questionsscreen.swipeScreenUp();
		Thread.sleep(2000);
		questionsscreen.swipeScreenUp();
		Assert.assertTrue(singlepageinspectionscreen.isAnswerPresent("Test Answer 1"));
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		
		
		servicesscreen.clickSaveButton();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Thread.sleep(10000);
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionPriceValue(), "$837.99");
		myinspectionsscreen.clickHomeButton();
	}
	
	//Test Case 16507:Create inspection from WO
	@Test(testName = "Test Case 16507:Create inspection from WO", description = "Create inspection from WO")
	public void testCreateInspectionFromWO() throws Exception {
		
		final String VIN = "QWERTYUI123";
		final String _make = "Buick";
		final String _model = "Electra";
		final String _year = "2014";
		final String _color = "Black";
		final String mileage = "77777";
		final String fueltanklevel = "25";
		final String _type = "Used";	
		final String stock = "Stock1";
		final String _ro = "123";
		final String pricevalue = "0";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		//Create WO1
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.SPECIFIC_CLIENT_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
	
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		vehiclescreeen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.clickSaveButton();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Helpers.waitABit(2000);
		
		//Test case
		Assert.assertEquals(myworkordersscreen.getPriceValueForWO(wonumber1), PricesCalculations.getPriceRepresentation(pricevalue));
		myworkordersscreen.selectWorkOrderNewInspection(wonumber1);
		vehiclescreeen.verifyMakeModelyearValues(_make, _model, _year);
		vehiclescreeen.cancelOrder();
		myworkordersscreen.clickHomeButton();		
	}
	
	@Test(testName = "Test Case 26266:Create Invoice with two WOs and copy vehicle", description = "Create Invoice with two WOs and copy vehicle")
	public void testCreateInvoiceWithTwoWOsAndCopyVehicle() throws Exception {
		
		final String VIN = "QWERTYUI123";
		final String _make = "Buick";
		final String _model = "Electra";
		final String _year = "2014";
		final String _color = "Black";
		final String mileage = "77777";
		final String fueltanklevel = "25";
		final String _type = "Used";	
		final String stock = "Stock1";
		final String _ro = "123";
		final String[] vehicleparts = { "Cowl, Other", "Hood", };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		//Create WO1
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setYear(_year);
		Thread.sleep(2000);
		vehiclescreeen.setMileage(mileage);
		vehiclescreeen.setFuelTankLevel(fueltanklevel);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setStock(stock);
		vehiclescreeen.setRO(_ro);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicescreen.clickVehiclePartsCell();
		for (int i = 0; i < vehicleparts.length; i++) {
			selectedservicescreen.selectVehiclePart(vehicleparts[i]);
		}
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		selectedservicescreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		Thread.sleep(1000);
		//testlogger.log(LogStatus.INFO, testuser.getTestUserName());
		myworkordersscreen.approveWorkOrder(wonumber1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		//myworkordersscreen.clickCancelSearchButton();
		
		//myworkordersscreen.searchWO(wonumber1);
		myworkordersscreen.selectWorkOrder(wonumber1);
		myworkordersscreen.selectCopyVehicle();
		Thread.sleep(1000);
		customersscreen.selectCustomer(iOSInternalProjectConstants.ZAZ_MOTORS_CUSTOMER);
		vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FORR_MONITOR_WOTYPE);
		
		Assert.assertEquals(vehiclescreeen.getMake(), _make);
		Assert.assertEquals(vehiclescreeen.getModel(), _model);
		Assert.assertEquals(vehiclescreeen.getYear(), _year);
		vehiclescreeen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Warning!")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Yes"))
				.click();
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectDefaultInvoiceType();
		invoiceinfoscreen.setPO("23");
		invoiceinfoscreen.addWorkOrder(wonumber1);
		invoiceinfoscreen.clickSaveAsDraft();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23401:Test 'Change customer' option for inspection
	@Test(testName = "Test Case 23401:Test 'Change customer' option for inspection", description = "Test 'Change customer' option for inspection")
	public void testChangeCustomerOptionForInspection() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		//resrtartApplication();
		//RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		//RegularHomeScreen homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_CHANGE_INSPTYPE);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen(UtilConstants.CLAIM_SCREEN_CAPTION);
		RegularClaimScreen claimscreen = new RegularClaimScreen(appiumdriver);
		claimscreen.selectInsuranceCompany("USG");
		claimscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		
		vehiclescreeen.clickSaveButton();
		Thread.sleep(3000);
		myinspectionsscreen.changeCustomerForInspection(inspectionnumber, iOSInternalProjectConstants.O03TEST__CUSTOMER);
		myinspectionsscreen.selectInspectionForEdit(inspectionnumber);
		Thread.sleep(1000);
		RegularVisualInteriorScreen visualscreen = new RegularVisualInteriorScreen(appiumdriver);
		visualscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		Assert.assertEquals(vehiclescreeen.getInspectionCustomer(), iOSInternalProjectConstants.O03TEST__CUSTOMER);
		vehiclescreeen.clickSaveButton();
		
		myinspectionsscreen.clickHomeButton();
	}
	
	//Test Case 23402:Test 'Change customer' option for Inspection as change 'Wholesale' to 'Retail' and vice versa'
	@Test(testName = "Test Case 23402:Test 'Change customer' option for Inspection as change 'Wholesale' to 'Retail' and vice versa'", description = "Test 'Change customer' option for Inspection as change 'Wholesale' to 'Retail' and vice versa'")
	public void testChangeCustomerOptionForInspectionAsChangeWholesaleToRetailAndViceVersa() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_CHANGE_INSPTYPE);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen(UtilConstants.CLAIM_SCREEN_CAPTION);
		RegularClaimScreen claimscreen = new RegularClaimScreen(appiumdriver);
		claimscreen.selectInsuranceCompany("USG");
		claimscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");	
		vehiclescreeen.clickSaveButton();
		myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		myinspectionsscreen.selectInspection(inspectionnumber);
		myinspectionsscreen.clickChangeCustomerpopupMenu();		
		myinspectionsscreen.customersPopupSwitchToRetailMode();
		Helpers.waitABit(1000);
		myinspectionsscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
				
		myinspectionsscreen.clickHomeButton();
		customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();
		
		myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.selectInspectionForEdit(inspectionnumber);
		RegularVisualInteriorScreen visualscreen = new RegularVisualInteriorScreen(appiumdriver);
		Thread.sleep(1000);
		visualscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		Assert.assertTrue(vehiclescreeen.getInspectionCustomer().contains(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER));
		vehiclescreeen.clickSaveButton();
		
		myinspectionsscreen.clickHomeButton();
	}
	
	//Test Case 23421:Test 'Change customer' option for Inspections based on type with preselected Companies
	@Test(testName = "Test Case 23421:Test 'Change customer' option for Inspections based on type with preselected Companies", description = "Test 'Change customer' option for Inspections based on type with preselected Companies")
	public void testChangeCustomerOptionForInspectionsBasedOnTypeWithPreselectedCompanies() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";

		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.TYPEWITHPRESELECTEDCOMPANIES_INSPTYPE);
		Thread.sleep(1000);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen(UtilConstants.CLAIM_SCREEN_CAPTION);
		RegularClaimScreen claimscreen = new RegularClaimScreen(appiumdriver);
		claimscreen.selectInsuranceCompany("USG");	
		claimscreen.clickSaveButton();
		Thread.sleep(1000);
		myinspectionsscreen.changeCustomerForInspection(inspectionnumber, iOSInternalProjectConstants.O03TEST__CUSTOMER);
		myinspectionsscreen.selectInspectionForEdit(inspectionnumber);
		Thread.sleep(1000);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		Assert.assertEquals(vehiclescreeen.getInspectionCustomer(), iOSInternalProjectConstants.O03TEST__CUSTOMER);
		vehiclescreeen.clickSaveButton();
		
		myinspectionsscreen.clickHomeButton();
	}
	
	//Test Case 23422:Test 'Change customer' option for Work Order
	@Test(testName = "Test Case 23422:Test 'Change customer' option for Work Order", description = "Test 'Change customer' option for Work Order")
	public void testChangeCustomerOptionForWorkOrder() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.WO_CLIENT_CHANGING_ON);
		myworkordersscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");	
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		
		ordersummaryscreen.clickSaveButton();
		Thread.sleep(4000);
		myworkordersscreen.changeCustomerForWorkOrder(wonumber, iOSInternalProjectConstants.O03TEST__CUSTOMER);	
		Thread.sleep(2000);
		myworkordersscreen.openWorkOrderDetails(wonumber);
		Assert.assertEquals(vehiclescreeen.getWorkOrderCustomer(), iOSInternalProjectConstants.O03TEST__CUSTOMER);
		servicesscreen.clickCancel();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23423:Test 'Change customer' option for WO based on type with preselected Companies
	@Test(testName = "Test Case 23423:Test 'Change customer' option for WO based on type with preselected Companies", description = "Test 'Change customer' option for WO based on type with preselected Companies")
	public void testChangeCustomerOptionForWOBasedOnTypeWithPreselectedCompanies() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";

		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.WO_WITH_PRESELECTED_CLIENTS);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");	
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("4");
		
		ordersummaryscreen.clickSaveButton();
		
		Thread.sleep(45000);
		//testlogger.log(LogStatus.INFO, wonumber);
		myworkordersscreen.changeCustomerForWorkOrder(wonumber, iOSInternalProjectConstants.O03TEST__CUSTOMER);	
		Thread.sleep(5000);
		myworkordersscreen.openWorkOrderDetails(wonumber);
		
		Assert.assertEquals(vehiclescreeen.getWorkOrderCustomer(), iOSInternalProjectConstants.O03TEST__CUSTOMER);
		servicesscreen.clickCancel();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23424:Test 'Change customer' option for WO as change 'Wholesale' to 'Retail' and vice versa'
	@Test(testName = "Test Case 23424:Test 'Change customer' option for WO as change 'Wholesale' to 'Retail' and vice versa'", description = "Test 'Change customer' option for WO as change 'Wholesale' to 'Retail' and vice versa'")
	public void testChangeCustomerOptionForWOAsChangeWholesaleToRetailAndViceVersa() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_CLIENT_CHANGING_ON);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");	
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("4");		
		ordersummaryscreen.clickSaveButton();
		
		Thread.sleep(4000);
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.selectWorkOrder(wonumber);	
		myworkordersscreen.clickChangeCustomerPopupMenu();
		myworkordersscreen.customersPopupSwitchToRetailMode();
		myworkordersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		Thread.sleep(4000);
		myworkordersscreen.clickHomeButton();
		customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();
		
		myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.openWorkOrderDetails(wonumber);		
		Assert.assertTrue(vehiclescreeen.getWorkOrderCustomer().contains(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER));
		servicesscreen.clickCancel();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23428:'Block for the same VIN' is ON. Verify 'Duplicate VIN ' message when create 2 WO with one VIN
	@Test(testName = "Test Case 23428:'Block for the same VIN' is ON. Verify 'Duplicate VIN ' message when create 2 WO with one VIN", description = "'Block for the same VIN' is ON. Verify 'Duplicate VIN ' message when create 2 WO with one VIN")
	public void testBlockForTheSameVINIsONVerifyDuplicateVINMessageWhenCreate2WOWithOneVIN() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.WOTYPE_BLOCK_VIN_ON);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("4");
		ordersummaryscreen.clickSaveButton();
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("You can't create Work Order for type '" + iOSInternalProjectConstants.WOTYPE_BLOCK_VIN_ON + "' for VIN '" + VIN + "' because it was already created"));
		ordersummaryscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23459:'Block for the same Services' is ON. Verify 'Duplicate services message' when create 2 WO with one service
	@Test(testName = "Test Case 23459:'Block for the same Services' is ON. Verify 'Duplicate services message' when create 2 WO with one service", description = "'Block for the same Services' is ON. Verify 'Duplicate services message' when create 2 WO with one service")
	public void testBlockForTheSameServicesIsONVerifyDuplicateServicesMessageWhenCreate2WOWithOneService() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.WOTYPE_BLOCK_FOR_THE_SAME_SERVICES_ON);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		Thread.sleep(1000);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		servicesscreen.selectSubService("AMoneyService_AdjustHeadlight");
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");	
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("4");
		ordersummaryscreen.clickSaveButton();
		ordersummaryscreen.closeDublicaterServicesWarningByClickingCancel();
		
		//Helpers.text_exact("Cancel").click();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23432:WO: Test 'Edit' option of 'Duplicate services' message for WO
	@Test(testName="Test Case 23432:WO: Test 'Edit' option of 'Duplicate services' message for WO", description = "'WO: Test 'Edit' option of 'Duplicate services' message for WO")
	public void testEditOptionOfDuplicateServicesMessageForWO() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.WOTYPE_BLOCK_FOR_THE_SAME_SERVICES_ON);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		Thread.sleep(1000);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		servicesscreen.selectSubService("AMoneyService_AdjustHeadlight");
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");	
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("4");
		ordersummaryscreen.clickSaveButton();
		ordersummaryscreen.closeDublicaterServicesWarningByClickingEdit();
		Thread.sleep(1000);
		ordersummaryscreen.swipeScreenLeft();
		ordersummaryscreen.selectNextScreen("All Services");
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.removeSelectedServices("AMoneyService_AdjustHeadlight");
		servicesscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23433:WO: Test 'Override' option of 'Duplicate services' message for WO.
	@Test(testName="Test Case 23433:WO: Test 'Override' option of 'Duplicate services' message for WO", description = "'WO: Test 'Override' option of 'Duplicate services' message for WO.")
	public void testOverrideOptionOfDuplicateServicesMessageForWO() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.WOTYPE_BLOCK_FOR_THE_SAME_SERVICES_ON);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		servicesscreen.selectSubService("AMoneyService_AdjustHeadlight");
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");	
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("4");	
		ordersummaryscreen.clickSaveButton();
		ordersummaryscreen.closeDublicaterServicesWarningByClickingOverride();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		Assert.assertTrue(myworkordersscreen.woExists(wonumber));

		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23434:WO: Test 'Cancel' option of 'Duplicate services' message for WO
	@Test(testName="Test Case 23434:WO: Test 'Cancel' option of 'Duplicate services' message for WO", description = "'WO: Test 'Cancel' option of 'Duplicate services' message for WO.")
	public void testCancelOptionOfDuplicateServicesMessageForWO() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";		
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.WOTYPE_BLOCK_FOR_THE_SAME_SERVICES_ON);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		servicesscreen.selectSubService("AMoneyService_AdjustHeadlight");
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");	
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("4");
		ordersummaryscreen.clickSaveButton();
		ordersummaryscreen.closeDublicaterServicesWarningByClickingCancel();
		Assert.assertFalse(myworkordersscreen.woExists(wonumber));

		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 23966:Inspections: Test saving inspections with three matrix
	//Test Case 24022:Inspections: Test saving inspection copied from one with 3 matrix price
	@Test(testName="Test Case 23966:Inspections: Test saving inspections with three matrix, "
			+ "Test Case 24022:Inspections: Test saving inspection copied from one with 3 matrix price", description = "'Inspections: Test saving inspections with three matrix")
	public void testSavingInspectionsWithThreeMatrix() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		final String _type = "New";
		final String _po = "1111";
		
		final String _pricematrix1 = "Hood";
		final String _pricematrix2 = "Left Roof Rail";
		final String _pricematrix3 = "Back Glass";
		final String _pricematrix4 = "Front Bumper";
		final String _pricematrix5 = "Roof";
	
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.VITALY_TEST_INSPTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setType(_type);
		vehiclescreeen.setPO(_po);
		String inspnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen(UtilConstants.CLAIM_SCREEN_CAPTION);
		RegularClaimScreen claimscreen = new RegularClaimScreen(appiumdriver);
		claimscreen.selectInsuranceCompany("USG");	
		claimscreen.setClaim("QWERTY");
		claimscreen.setAccidentDate();
		claimscreen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualInteriorCaption());
		RegularVisualInteriorScreen visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);
		RegularVisualInteriorScreen.tapInteriorWithCoords(100, 100);
		RegularVisualInteriorScreen.tapInteriorWithCoords(150, 150);
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);
		visualinteriorscreen.assertPriceIsCorrect("$520.00");
		/////////////visualinteriorscreen.assertVisualPriceIsCorrect("$140.00");

		visualinteriorscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualExteriorCaption());
		visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		RegularVisualInteriorScreen.tapExteriorWithCoords(100, 100);
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		visualinteriorscreen.assertPriceIsCorrect("$570.00");
		visualinteriorscreen.assertVisualPriceIsCorrect("$100.00");
		
		visualinteriorscreen.selectNextScreen("Default");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix1);
		pricematrix.switchOffOption("PDR");
		pricematrix.selectDiscaunt("SR_S5_Mt_Money");
		pricematrix.selectDiscaunt("SR_S5_Mt_Money_DE_TE");
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.SR_S1_MONEY);
		pricematrix.clickDiscaunt(iOSInternalProjectConstants.SR_S1_MONEY);
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.setServiceQuantityValue("3");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		Helpers.waitABit(1000);
		Assert.assertEquals(pricematrix.getDiscauntPriceAndValue(iOSInternalProjectConstants.SR_S1_MONEY), "$2,000.00 x 3.00");
		pricematrix.clickSaveButton();
		pricematrix.selectPriceMatrix(_pricematrix2);
		pricematrix.setSizeAndSeverity(RegularPriceMatrixScreen.NKL_SIZE, "VERY LIGHT");
		pricematrix.clickSaveButton();
		pricematrix.selectNextScreen("Matrix Labor");
		pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix3);
		pricematrix.setSizeAndSeverity(RegularPriceMatrixScreen.DIME_SIZE, "LIGHT");
		pricematrix.clickSaveButton();
		
		pricematrix.selectPriceMatrix(_pricematrix4);
		pricematrix.switchOffOption("PDR");
		pricematrix.selectDiscaunt("Little Service");
		pricematrix.selectDiscaunt("Disc_Ex_Service1");
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		Helpers.waitABit(500);
		pricematrix.setTime("1");
		pricematrix.clickSaveButton();

		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		regularselectedservicedetailsscreen.clickVehiclePartsCell();
		regularselectedservicedetailsscreen.selectVehiclePart("Roof");
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		regularselectedservicedetailsscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		regularselectedservicedetailsscreen.clickVehiclePartsCell();
		regularselectedservicedetailsscreen.selectVehiclePart("Front Bumper");
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.selectNextScreen("Test matrix33");
		pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix5);
		pricematrix.switchOffOption("PDR");
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.SR_S1_MONEY);
		pricematrix.clickSaveButton();
		
		pricematrix.selectNextScreen("Test_Package_3PrMatrix");
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService("SR_S2_MoneyDisc_TE");
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.selectNextScreen("New_Test_Image");
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);
		Helpers.tapRegularCarImage();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.WHEEL_REPAIR_SERVICE);
		visualinteriorscreen.assertPriceIsCorrect("$13,145.50");
		visualinteriorscreen.assertVisualPriceIsCorrect("$80.00");
		
		visualinteriorscreen.clickSaveButton();
		Thread.sleep(8000);
		myinspectionsscreen.selectInspectionForEdit(inspnumber);
		myinspectionsscreen.selectNextScreen("Default");
		pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		
		Assert.assertTrue(pricematrix.isPriceMatrixContainsPriceValue(_pricematrix1, "$6,100.00"));
		Assert.assertTrue(pricematrix.isPriceMatrixContainsPriceValue(_pricematrix2, "$75.00"));
		
		pricematrix.selectNextScreen("Matrix Labor");
		Assert.assertTrue(pricematrix.isPriceMatrixContainsPriceValue(_pricematrix3, "$25.00"));
		Assert.assertTrue(pricematrix.isPriceMatrixContainsPriceValue(_pricematrix4, "$1,105.50"));
		pricematrix.selectNextScreen("Test matrix33");
		Assert.assertTrue(pricematrix.isPriceMatrixContainsPriceValue(_pricematrix5, "$2,000.00"));
		
		servicesscreen.cancelOrder();
		myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		myinspectionsscreen.selectInspectionForCopy(inspnumber);
		Thread.sleep(4000);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		String copiedinspnumber = vehiclescreeen.getInspectionNumber();
		servicesscreen.clickSaveButton();
		myinspectionsscreen.assertInspectionExists(copiedinspnumber);	
		myinspectionsscreen.clickHomeButton();
	}
	
	//Test Case 24075:SR: Test that selected services on SR are copied to Inspection based on SR
	@Test(testName="Test Case 24075:SR: Test that selected services on SR are copied to Inspection based on SR", description = "Test that selected services on SR are copied to Inspection based on SR")
	public void testThatSelectedServicesOnSRAreCopiedToInspectionBasedOnSR() throws Exception {
		final String VIN  = "1D7HW48NX6S507810";
		
		/*RegularSettingsScreen settingscreen =  homescreen.clickSettingsButton();
		settingscreen.setShowAllServicesOn();
		homescreen = settingscreen.clickHomeButton();*/
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		homescreen = customersscreen.clickHomeButton();
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		Thread.sleep(3000);
		servicerequestsscreen.clickAddButton();
		customersscreen = new RegularCustomersScreen(appiumdriver);
		customersscreen.selectCustomer(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		servicerequestsscreen.selectInspectionType("SR_only_Acc_Estimate");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Dakota", "2006");
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectService(iOSInternalProjectConstants.VPS1_SERVICE);
		servicesscreen.selectService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.clickAddServicesButton();
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DYE_SERVICE);
		servicedetailsscreen.setServiceQuantityValue("14");
		servicedetailsscreen.saveSelectedServiceDetails();
		//servicesscreen.setSelectedServiceRequestServicePrice(iOSInternalProjectConstants.DYE_SERVICE, "14");

		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name(AlertsCaptions.ALERT_CREATE_APPOINTMENT)).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("No"))
				.click();
		Thread.sleep(3000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		Thread.sleep(8000);
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.INSPTYPE_FOR_SR_INSPTYPE);
		Thread.sleep(3000);
	
		String inspectnumber = vehiclescreeen.getInspectionNumber();		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelectedWithServiceValues(iOSInternalProjectConstants.DYE_SERVICE, "$10.00 x 14.00");
		servicesscreen.assertServiceIsSelectedWithServiceValues(iOSInternalProjectConstants.VPS1_SERVICE, "%20.000");
		servicesscreen.assertServiceIsSelectedWithServiceValues(iOSInternalProjectConstants.WHEEL_SERVICE, "$70.00 x 1.00");
			
		servicesscreen.clickSaveButton();
		Thread.sleep(3000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		Thread.sleep(3000);
		servicerequestsscreen.clickServiceRequestSummaryInspectionsButton();
		Thread.sleep(3000);
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		myinspectionsscreen.assertInspectionExists(inspectnumber);
		myinspectionsscreen.clickHomeButton();
		servicerequestsscreen.clickHomeButton();
		Thread.sleep(3000);
		servicerequestsscreen.clickHomeButton();
	}
	
	//Test Case 24657:WO: Test that auto-saved WO is created correctly
	//Test Case 23435:WO: Test 'Continue' option of 'Auto-saved' WO
	//Test Case 23436:WO: Test 'Discard' option of 'Auto-saved' WO
	@Test(testName="Test Case 24657:WO: Test that auto-saved WO is created correctly, " +
			"Test Case 23435:WO: Test 'Continue' option of 'Auto-saved' WO, " + 
			"Test Case 23436:WO: Test 'Discard' option of 'Auto-saved' WO", description = "WO: Test that auto-saved WO is created correctly")
	public void testThatAutoSavedWOIsCreatedCorrectly()
			throws Exception {
		final String VIN  = "1FMFU18L53LC13897";

		//resrtartApplication();
		//MainScreen mainscreen = new MainScreen(appiumdriver);
		//HomeScreen homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
			
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_CLIENT_CHANGING_ON);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.verifyMakeModelyearValues("Ford", "Expedition", "2003");
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		Helpers.waitABit(30*1000);
		resrtartApplication();
		RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		Assert.assertTrue(myworkordersscreen.isAutosavedWorkOrderExists());
		myworkordersscreen.selectContinueWorkOrder(wonumber);
		Thread.sleep(1000);
		Assert.assertEquals(vehiclescreeen.getInspectionNumber(), wonumber);
		
		resrtartApplication();
		mainscreen = new RegularMainScreen(appiumdriver);
		mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		Assert.assertTrue(myworkordersscreen.isAutosavedWorkOrderExists());
		myworkordersscreen.selectDiscardWorkOrder(wonumber);
		Helpers.waitABit(500);
		Assert.assertFalse(myworkordersscreen.isAutosavedWorkOrderExists());
		myworkordersscreen.clickHomeButton();
	}
	
	//Test Case 21578:SR: Add appointment to Service Request 
	@Test(testName = "Test Case 21578:SR: Add appointment to Service Request", description = "SR: Add appointment to Service Request")
	public void testSRAddAppointmentToServiceRequest() throws Exception {
		final String VIN = "QWERTYUI123";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
					
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
			
		servicerequestsscreen.clickAddButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.TEST_COMPANY_CUSTOMER);
			
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_EST_WO_REQ_SRTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
			
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(MobileBy.name("Close")).click();
	
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Question 'Signature' in section 'Follow up Requested' should be answered."));
		Thread.sleep(3000);
		//Helpers.swipeScreenUp();
		Helpers.drawRegularQuestionsSignature();
		servicesscreen.clickSaveButton();
		Thread.sleep(1000);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Question 'Tax_Point_1' in section 'BATTERY PERFORMANCE' should be answered.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		Thread.sleep(4000);
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		Thread.sleep(2000);
		questionsscreen.swipeScreenUp();		
		questionsscreen.selectTaxPoint("Test Answer 1");
		servicesscreen.clickSaveButton();
		alerttext = Helpers.getAlertTextAndCancel();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CREATE_APPOINTMENT);
		Thread.sleep(5000);
		final String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectAppointmentRequestAction();
		servicerequestsscreen.clickAddButton();
		servicerequestsscreen.selectTodayFromAppointmet();
		servicerequestsscreen.selectTodayToAppointmet();
		final String fromapp = servicerequestsscreen.getFromAppointmetValue();
		final String toapp = servicerequestsscreen.getToAppointmetValue();
		
		servicerequestsscreen.setSubjectAppointmet("SR-APP");
		servicerequestsscreen.setAddressAppointmet("Maidan");
		servicerequestsscreen.setCityAppointmet("Kiev");
		servicerequestsscreen.saveAppointment();
		Thread.sleep(3000);
		homescreen = servicerequestsscreen.clickHomeButton();
		servicerequestsscreen = homescreen.clickServiceRequestsButton();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");	
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectRejectAction();
		alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertEquals(alerttext, "Would you like to reject  selected service request?");
		servicerequestsscreen.clickHomeButton();
	}
	
	//Test Case 24677:SR: Verify 'Summary' action for appointment on SR's calendar
	@Test(testName = "Test Case 24677:SR: Verify 'Summary' action for appointment on SR's calendar", description = "SR: Verify 'Summary' action for appointment on SR's calendar")
	public void testSRVerifySummaryActionForAppointmentOnSRsCalendar() throws Exception {
		final String VIN = "QWERTYUI123";
		final String srappsubject = "SR-APP";
		final String srappaddress = "Maidan";
		final String srappcity = "Kiev";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
				
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.TEST_COMPANY_CUSTOMER);
				
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_EST_WO_REQ_SRTYPE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
				
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("The VIN is invalid.")).isDisplayed());
		appiumdriver.findElement(MobileBy.name("Close")).click();
				
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndCancel();
		Assert.assertTrue(alerttext.contains("Question 'Signature' in section 'Follow up Requested' should be answered."));
		Thread.sleep(3000);
		//Helpers.swipeScreenUp();
		Helpers.drawRegularQuestionsSignature();
		servicesscreen.clickSaveButton();
		Thread.sleep(1000);
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Question 'Tax_Point_1' in section 'BATTERY PERFORMANCE' should be answered.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		Thread.sleep(4000);
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		Thread.sleep(2000);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectTaxPoint("Test Answer 1");
		servicesscreen.clickSaveButton();
		alerttext = Helpers.getAlertTextAndCancel();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CREATE_APPOINTMENT);
		Thread.sleep(5000);
	
		final String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		
		servicerequestsscreen.selectAppointmentRequestAction();
		servicerequestsscreen.clickAddButton();
		servicerequestsscreen.selectTodayFromAppointmet();
		servicerequestsscreen.selectTodayToAppointmet();
		final String fromapp = servicerequestsscreen.getFromAppointmetValue();
		final String toapp = servicerequestsscreen.getToAppointmetValue();
			
		servicerequestsscreen.setSubjectAppointmet(srappsubject);
		servicerequestsscreen.setAddressAppointmet(srappaddress);
		servicerequestsscreen.setCityAppointmet(srappcity);
		servicerequestsscreen.saveAppointment();
		Thread.sleep(3000);
		homescreen = servicerequestsscreen.clickHomeButton();
		servicerequestsscreen = homescreen.clickServiceRequestsButton();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");
		servicerequestsscreen.selectServiceRequest(srnumber);		
		servicerequestsscreen.selectSummaryRequestAction();	
		Thread.sleep(3000);
		//String summaryapp = servicerequestsscreen.getSummaryAppointmentsInformation();
		String expectedsummaryapp = "Subject: " + srappsubject + ", In: " + fromapp + ", Out: " + toapp + ", Location: " + srappaddress 
				+ ", " + srappcity + ", US";
		Assert.assertTrue(servicerequestsscreen.isSRSummaryAppointmentsInformation());
		
		servicerequestsscreen.clickHomeButton();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectRejectAction();
		alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertEquals(alerttext, "Would you like to reject  selected service request?");
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 25403:WO Regular: Verify that only assigned services on Matrix Panel is available as additional services", description = "WO Regular: Verify that only assigned services on Matrix Panel is available as additional services")
	public void testWOVerifyThatOnlyAssignedServicesOnMatrixPanelIsAvailableAsAdditionalServices()
			throws Exception {
		
		final String VIN  = "WDZPE7CD9E5889222";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
			
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_PRICE_MATRIX);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mercedes-Benz", "Sprinter", "2014");
		vehiclescreeen.selectNextScreen("Zayats test pack");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectPriceMatrices("Price Matrix Zayats");	
		servicesscreen.selectPriceMatrices("VP1 zayats");		
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("VP1 zayats");
		pricematrix.switchOffOption("PDR");
		Assert.assertTrue(pricematrix.isDiscauntPresent("Test service zayats"));
		Assert.assertTrue(pricematrix.isDiscauntPresent("Wheel"));		
		pricematrix.clickSaveButton();
		pricematrix.selectPriceMatrix("VP2 zayats");
		pricematrix.switchOffOption("PDR");
		Assert.assertTrue(pricematrix.isDiscauntPresent("Dye"));
		pricematrix.selectDiscaunt("Dye");
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen("Zayats test pack");
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.cancelOrder();
		
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 25011:Inspections: verify that only assigned services on Matrix Panel is available as additional services", description = "Inspections: verify that only assigned services on Matrix Panel is available as additional services")
	public void testInspectionsVerifyThatOnlyAssignedServicesOnMatrixPanelIsAvailableAsAdditionalServices()
			throws Exception {
		
		final String VIN  = "WDZPE7CD9E5889222";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
			
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_TYPE_FOR_PRICE_MATRIX);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mercedes-Benz", "Sprinter", "2014");
		String inspnum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Price Matrix Zayats");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("VP1 zayats");
		pricematrix.switchOffOption("PDR");
		Assert.assertTrue(pricematrix.isDiscauntPresent("Test service zayats"));
		Assert.assertTrue(pricematrix.isDiscauntPresent("Wheel"));		
		pricematrix.clickSaveButton();
		pricematrix.selectPriceMatrix("VP2 zayats");
		pricematrix.switchOffOption("PDR");
		Assert.assertTrue(pricematrix.isDiscauntPresent("Dye"));
		pricematrix.selectDiscaunt("Dye");
		pricematrix.clickSaveButton();
		pricematrix.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		
		vehiclescreeen.clickSaveButton();
		myinspectionsscreen.assertInspectionExists(inspnum);
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 25421:WO: Verify that on Invoice 'Summary' main service of the panel is displayed as first then additional services", description = "WO HD: Verify that on Invoice 'Summary' main service of the panel is displayed as first then additional services")
	public void testWOVerifyThatOnInvoiceSummaryMainServiceOfThePanelIsDisplayedAsFirstThenAdditionalServices()
			throws Exception {
		
		final String VIN  = "2C3CDXBG2EH174681";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INV_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Charger", "2014");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats test pack");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("Test service price matrix");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("Price Matrix Zayats");
		pricematrix.selectPriceMatrix("VP1 zayats");
		pricematrix.switchOffOption("PDR");
		pricematrix.selectDiscaunt("Test service zayats");
		pricematrix.selectDiscaunt("Wheel");		
		pricematrix.clickSaveButton();
		
		pricematrix.selectPriceMatrix("VP2 zayats");
		//pricematrix.switchOffOption("PDR");
		pricematrix.setSizeAndSeverity("CENT", "LIGHT");
			
		pricematrix.selectDiscaunt("Dye");
		pricematrix.selectDiscaunt("Wheel");
		pricematrix.selectDiscaunt("Test service zayats");
		pricematrix.clickSaveButton();
		pricematrix.clickHomeButton();
		pricematrix.clickHomeButton();
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelected("Test service price matrix");
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");	
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.selectWorkOrderForApprove(wonum);
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.clickApproveButton();
		myworkordersscreen.selectWorkOrderForAction(wonum);
		myworkordersscreen.clickInvoiceIcon();
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.DEFAULT_INVOICETYPE);
		invoiceinfoscreen.setPO("12345");
		final String invoicenum = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsFinal();
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		homescreen = myworkordersscreen.clickHomeButton();
		
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.selectInvoice(invoicenum);
		myinvoicesscreen.clickSummaryPopup();
		Assert.assertTrue(myinvoicesscreen.isSummaryPDFExists());
		myinvoicesscreen.clickHomeButton();
		myinvoicesscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 26054:WO Monitor: Regular - Create WO for monitor", description = "WO Monitor: Regular - Create WO for monitor")
	public void testWOMonitorCreateWOForMonitor()
			throws Exception {
		
		final String VIN  = "1D3HV13T19S825733";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Ram Pickup 1500", "2009");
		vehiclescreeen.selectLocation("Test Location ZZZ");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.woExists(wonum);
		
		homescreen = myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 26091:WO Monitor: Regular - Verify that it is not possible to change Service Status before Start Service", description = "WO Monitor: Regular - Verify that it is not possible to change Service Status before Start Service")
	public void testWOMonitorVerifyThatItIsNotPossibleToChangeServiceStatusBeforeStartService()
			throws Exception {
		
		final String VIN  = "1D3HV13T19S825733";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Ram Pickup 1500", "2009");
		vehiclescreeen.selectLocation("Test Location ZZZ");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.woExists(wonum);
		
		homescreen = myworkordersscreen.clickHomeButton();

		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickSearchButton();
		teamworkordersscreen.selectSearchLocation("Test Location ZZZ");
		teamworkordersscreen.clickSearchSaveButton();
		Helpers.waitABit(3000);
		teamworkordersscreen.clickOnWO(wonum);
		
		RegularOrderMonitorScreen ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Helpers.waitABit(3000);
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		Assert.assertTrue(ordermonitorscreen.isStartServiceButtonPresent());
		ordermonitorscreen.clickServiceStatusCell();		
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("You must start the service before you can change its status."));
		ordermonitorscreen.clickStartService();
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		ordermonitorscreen.setCompletedServiceStatus();
		Helpers.waitABit(2000);
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.WHEEL_SERVICE, "Completed");
		teamworkordersscreen = ordermonitorscreen.clickBackButton();
		teamworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 26092:WO Monitor: Regular - Verify that it is not possible to change Phase Status before Start phase", description = "WO Monitor: HD - Verify that it is not possible to change Phase Status before Start phase")
	public void testWOMonitorVerifyThatItIsNotPossibleToChangePhaseStatusBeforeStartPhase()
			throws Exception {
		
		final String VIN  = "1D3HV13T19S825733";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Ram Pickup 1500", "2009");
		vehiclescreeen.selectLocation("Test Location ZZZ");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.woExists(wonum);
		
		homescreen = myworkordersscreen.clickHomeButton();
		
		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickSearchButton();
		teamworkordersscreen.selectSearchLocation("Test Location ZZZ");
		teamworkordersscreen.clickSearchSaveButton();
		Thread.sleep(3000);
		teamworkordersscreen.clickOnWO(wonum);
		
		RegularOrderMonitorScreen ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Thread.sleep(3000);
		Assert.assertTrue(ordermonitorscreen.isRepairPhaseExists());
		Assert.assertTrue(ordermonitorscreen.isStartPhaseButtonExists());
		ordermonitorscreen.clicksRepairPhaseLine();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("You must start the phase before you can change its status."));
		ordermonitorscreen.clickStartPhaseButton();
		
		
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.DYE_SERVICE);
		Assert.assertFalse(ordermonitorscreen.isStartPhaseButtonExists());
		Assert.assertTrue(ordermonitorscreen.isServiceStartDateExists());
		
		ordermonitorscreen.clickServiceStatusCell();
		alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("You cannot change the status of services for this phase. You can only change the status of the whole phase."));
		ordermonitorscreen.clickServiceDetailsDoneButton();
		
		ordermonitorscreen.clicksRepairPhaseLine();
		ordermonitorscreen.clickCompletedPhaseCell();
		
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.DISC_EX_SERVICE1, "Completed");
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.DYE_SERVICE, "Completed");
		teamworkordersscreen = ordermonitorscreen.clickBackButton();
		teamworkordersscreen.clickHomeButton();
	}	
	
	@Test(testName="Test Case 26094:WO Monitor: Regular - Verify that Start date is set when Start Service", description = "WO Monitor: Regular - Verify that Start date is set when Start Service")
	public void testWOMonitorVerifyThatStartDateIsSetWhenStartService()
			throws Exception {
		
		final String VIN  = "1D3HV13T19S825733";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Ram Pickup 1500", "2009");
		vehiclescreeen.selectLocation("Test Location ZZZ");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.woExists(wonum);
		
		homescreen = myworkordersscreen.clickHomeButton();

		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickSearchButton();
		teamworkordersscreen.selectSearchLocation("Test Location ZZZ");
		teamworkordersscreen.clickSearchSaveButton();
		Thread.sleep(3000);
		teamworkordersscreen.clickOnWO(wonum);		
		RegularOrderMonitorScreen ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Thread.sleep(3000);
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		Assert.assertFalse(ordermonitorscreen.isServiceStartDateExists());
		ordermonitorscreen.clickStartService();
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		Assert.assertTrue(ordermonitorscreen.isServiceStartDateExists());
		ordermonitorscreen.clickServiceDetailsDoneButton();
		
		teamworkordersscreen = ordermonitorscreen.clickBackButton();
		teamworkordersscreen.clickHomeButton();
	}
	
	//@Test(testName="Test Case 26016:WO Monitor: Regular - Verify that for % service message about change status is not shown", description = "WO Monitor: Regular - Verify that for % service message about change status is not shown")
	public void testWOMonitorVerifyThatForPercentServiceMessageAboutChangeStatusIsNotShown()
			throws Exception {
		
		final String VIN  = "1D3HV13T19S825733";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Ram Pickup 1500", "2009");
		vehiclescreeen.selectLocation("Test Location ZZZ");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.woExists(wonum);
		
		homescreen = myworkordersscreen.clickHomeButton();

		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickSearchButton();
		teamworkordersscreen.selectSearchLocation("Test Location ZZZ");
		teamworkordersscreen.clickSearchSaveButton();
		Thread.sleep(3000);
		teamworkordersscreen.clickOnWO(wonum);
		System.out.println("++++" + wonum);
		RegularOrderMonitorScreen ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.VPS1_SERVICE);
		ordermonitorscreen.clickServiceDetailsDoneButton();
		
		teamworkordersscreen = ordermonitorscreen.clickBackButton();
		teamworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 26013:WO Monitor: Regular - Verify that when change Status for Phase with 'Do not track individual service statuses' ON Phase status is set to all services assigned to phase", 
			description = "WO Monitor: Regular - Verify that when change Status for Phase with 'Do not track individual service statuses' ON Phase status is set to all services assigned to phase")
	public void testWOMonitorVerifyThatWhenChangeStatusForPhaseWithDoNotTrackIndividualServiceStatusesONPhaseStatusIsSetToAllServicesAssignedToPhase()
			throws Exception {
		
		final String VIN  = "1D3HV13T19S825733";
		final String _pricematrix = "HOOD";
		final String _size = "NKL";
		final String _severity = "VERY LIGHT";
		final String _price = "$100.00";
		final String _discaunt_us = "Discount 10-20$";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Ram Pickup 1500", "2009");
		vehiclescreeen.selectLocation("Test Location ZZZ");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		RegularPriceMatrixScreen pricematrix = servicesscreen.selectServicePriceMatrices(iOSInternalProjectConstants.HAIL_MATRIX_SERVICE);
		pricematrix.selectPriceMatrix(_pricematrix);
		pricematrix.setSizeAndSeverity(_size, _severity);
		pricematrix.assertPriceCorrect(_price);
		pricematrix.assertNotesExists();
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DYE_SERVICE);
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		pricematrix.selectDiscaunt(_discaunt_us);
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.woExists(wonum);
		
		homescreen = myworkordersscreen.clickHomeButton();

		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickSearchButton();
		teamworkordersscreen.selectSearchLocation("Test Location ZZZ");
		teamworkordersscreen.clickSearchSaveButton();
		Thread.sleep(3000);
		teamworkordersscreen.clickOnWO(wonum);
		
		RegularOrderMonitorScreen ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		Assert.assertTrue(ordermonitorscreen.isStartServiceButtonPresent());
		ordermonitorscreen.clickStartService();
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		ordermonitorscreen.setCompletedServiceStatus();
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.WHEEL_SERVICE, "Completed");

		Assert.assertTrue(ordermonitorscreen.isStartPhaseButtonExists());
		ordermonitorscreen.clickStartPhaseButton();
		
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.DYE_SERVICE);
		ordermonitorscreen.clickServiceStatusCell();;
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("You cannot change the status of services for this phase. You can only change the status of the whole phase."));
		ordermonitorscreen.clickServiceDetailsDoneButton();
		
		ordermonitorscreen.clicksRepairPhaseLine();
		ordermonitorscreen.clickCompletedPhaseCell();
		
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE, "Completed");
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.DISC_EX_SERVICE1, "Completed");
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.DYE_SERVICE, "Completed");
				
		
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.DYE_SERVICE);
		ordermonitorscreen.verifyServiceStatusInPopup(iOSInternalProjectConstants.DYE_SERVICE, "Completed");
		Thread.sleep(3000);
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		ordermonitorscreen.verifyServiceStatusInPopup(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE, "Completed");
		
		teamworkordersscreen = ordermonitorscreen.clickBackButton();
		teamworkordersscreen.clickHomeButton();
	}	
	
	@Test(testName="Test Case 26093:WO Monitor: Regular - Verify that %Service is not Started when Start phase", 
			description = "WO Monitor: Regular - Verify that %Service is not Started when Start phase")
	public void testWOMonitorVerifyThatPercentageServiceIsNotStartedWhenStartPhase()
			throws Exception {
		
		final String VIN  = "1D3HV13T19S825733";
		final String _pricematrix = "HOOD";
		final String _size = "NKL";
		final String _severity = "VERY LIGHT";
		final String _price = "$100.00";
		final String _discaunt_us = "Discount 10-20$";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Ram Pickup 1500", "2009");
		vehiclescreeen.selectLocation("Test Location ZZZ");
		String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Package_for_Monitor");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		RegularPriceMatrixScreen pricematrix = servicesscreen.selectServicePriceMatrices(iOSInternalProjectConstants.HAIL_MATRIX_SERVICE);
		pricematrix.selectPriceMatrix(_pricematrix);
		pricematrix.setSizeAndSeverity(_size, _severity);
		pricematrix.assertPriceCorrect(_price);
		pricematrix.assertNotesExists();
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DYE_SERVICE);
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		pricematrix.selectDiscaunt(_discaunt_us);
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.DENT_REMOVAL_SERVICE);
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.woExists(wonum);
		
		homescreen = myworkordersscreen.clickHomeButton();

		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickSearchButton();
		teamworkordersscreen.selectSearchLocation("Test Location ZZZ");
		teamworkordersscreen.clickSearchSaveButton();
		Thread.sleep(3000);
		teamworkordersscreen.clickOnWO(wonum);
		
		RegularOrderMonitorScreen ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		Assert.assertTrue(ordermonitorscreen.isStartServiceButtonPresent());
		ordermonitorscreen.clickStartService();
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.WHEEL_SERVICE);
		ordermonitorscreen.setCompletedServiceStatus();
		//ordermonitorscreen.clickServiceDetailsDoneButton();
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.WHEEL_SERVICE, "Completed");
		
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.DYE_SERVICE);
		Assert.assertTrue(ordermonitorscreen.isStartPhaseButtonPresent());
		ordermonitorscreen.clickPhaseStatusCell();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertEquals(alerttext, "Order Monitor It is impossible to change phase status until you start phase.");
		ordermonitorscreen.clickStartPhase();
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.DYE_SERVICE);
		ordermonitorscreen.setCompletedPhaseStatus();
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.DYE_SERVICE, "Completed");
		ordermonitorscreen.verifyPanelStatus(iOSInternalProjectConstants.DISC_EX_SERVICE1, "Completed");
		
		ordermonitorscreen.selectPanel(iOSInternalProjectConstants.TEST_TAX_SERVICE);
		Assert.assertFalse(ordermonitorscreen.isServiceStartDateExists());
		ordermonitorscreen.verifyPanelStatusInPopup(iOSInternalProjectConstants.TEST_TAX_SERVICE, "Completed");
		
		teamworkordersscreen = ordermonitorscreen.clickBackButton();
		teamworkordersscreen.clickHomeButton();
	}	
	
	@Test(testName="Test Case 28473:Invoices: HD - Verify that red 'A' icon is present for invoice with customer approval ON and no signature", 
			description = "Invoices: Regular - Verify that red 'A' icon is present for invoice with customer approval ON and no signature")
	public void testRegularVerifyThatRedAIconIsPresentForInvoiceWithCustomerApprovalONAndNoSignature()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String _po = "123";

		//RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		//homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INV_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("Zayats test pack");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType(iOSInternalProjectConstants.CUSTOMER_APPROVALON_INVOICETYPE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsFinal();
		myworkordersscreen.clickHomeButton();
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.myInvoiceExists(invoicenumber);
		Assert.assertTrue(myinvoicesscreen.isInvoiceApproveButtonExists(invoicenumber));
		myinvoicesscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 28476:Invoices: HD&Regular - Verify that grey 'A' icon is present for invoice with customer approval OFF and no signature", 
			description = "Invoices: Regular - Verify that grey 'A' icon is present for invoice with customer approval OFF and no signature")
	public void testRegularVerifyThatRedAIconIsPresentForInvoiceWithCustomerApprovalOFFAndNoSignature()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String _po = "123";
		//appiumdriverInicialize();
		//RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		//homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
				
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INV_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("Zayats test pack");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType(iOSInternalProjectConstants.CUSTOMER_APPROVALOFF_INVOICETYPE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsFinal();
		myworkordersscreen.clickHomeButton();
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.myInvoiceExists(invoicenumber);
		Assert.assertTrue(myinvoicesscreen.isInvoiceApproveButtonExists(invoicenumber));
		myinvoicesscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 28478:Invoices: Regular - Verify that 'A' icon is not present for invoice when signature exists", 
			description = "Invoices: Regular - Verify that 'A' icon is not present for invoice when signature exists")
	public void testRegularVerifyThatAIconIsNotPresentForInvoiceWhenSignatureExists()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String _po = "123";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INV_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("Zayats test pack");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType(iOSInternalProjectConstants.CUSTOMER_APPROVALON_INVOICETYPE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumberapproveon = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsFinal();
		myworkordersscreen.clickHomeButton();
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.myInvoiceExists(invoicenumberapproveon);
		Assert.assertTrue(myinvoicesscreen.isInvoiceApproveButtonExists(invoicenumberapproveon));
		myinvoicesscreen.selectInvoiceForApprove(invoicenumberapproveon);
		myinvoicesscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.clickApproveButton();
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		myinvoicesscreen = new RegularMyInvoicesScreen(appiumdriver);
		
		Assert.assertFalse(myinvoicesscreen.isInvoiceApproveButtonExists(invoicenumberapproveon));
		myinvoicesscreen.clickHomeButton();
		
		myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval OFF
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INV_PRINT);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("Zayats test pack");
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		invoiceinfoscreen = ordersummaryscreen.selectInvoiceType(iOSInternalProjectConstants.CUSTOMER_APPROVALOFF_INVOICETYPE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumbeapprovaloff = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsFinal();
		myworkordersscreen.clickHomeButton();
		myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.myInvoiceExists(invoicenumbeapprovaloff);
		myinvoicesscreen.selectInvoiceForApprove(invoicenumbeapprovaloff);
		myinvoicesscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.clickApproveButton();
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		Assert.assertFalse(myinvoicesscreen.isInvoiceApproveButtonExists(invoicenumbeapprovaloff));	
		myinvoicesscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 29022:SR: Regular - Verify that Reject action is displayed for SR in Status Scheduled (Insp or WO) and assign for Tech", 
			description = "Test Case 29022:SR: Regular - Verify that Reject action is displayed for SR in Status Scheduled (Insp or WO) and assign for Tech")
	public void testSRRegularVerifyThatRejectActionIsDisplayedForSRInStatusScheduledInspOrWOAndAssignForTech()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		//Create first SR
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_ONLY_ACC_ESTIMATE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.clickAddServicesButton();
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		questionsscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name(AlertsCaptions.ALERT_CREATE_APPOINTMENT)).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("No"))
				.click();
		Thread.sleep(5000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber1 = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber1);
		servicerequestsscreen.selectRejectAction();
		Helpers.acceptAlert();
		//Create second SR
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_TYPE_WO_AUTO_CREATE);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveButton();
		
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber2 = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber2), "Scheduled");	
		servicerequestsscreen.selectServiceRequest(srnumber2);
		servicerequestsscreen.selectRejectAction();
		Helpers.acceptAlert();
		Thread.sleep(3000);
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 29029:SR: Regular - Verify that Reject action is displayed for SR in Status OnHold (Insp or WO) and assign for Tech", 
			description = "Test Case 29029:SR: Regular - Verify that Reject action is displayed for SR in Status OnHold (Insp or WO) and assign for Tech")
	public void testSRRegularVerifyThatRejectActionIsDisplayedForSRInStatusOnHoldInspOrWOAndAssignForTech()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_ALL_PHASES);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.clickAddServicesButton();
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		questionsscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name(AlertsCaptions.ALERT_CREATE_APPOINTMENT)).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("No"))
				.click();
		Thread.sleep(3000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "On Hold");
		
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertTrue(servicerequestsscreen.isRejectActionExists());
		servicerequestsscreen.selectCreateInspectionRequestAction();
		Thread.sleep(5000);
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.INSP_DRAFT_MODE);
		String inspectnumber = servicerequestsscreen.getInspectionNumber();
		servicerequestsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen.clickSaveAsFinal();
		Thread.sleep(3000);
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		servicerequestsscreen.clickServiceRequestSummaryInspectionsButton();
		Thread.sleep(3000);
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		myinspectionsscreen.assertInspectionExists(inspectnumber);
		myinspectionsscreen.clickActionButton();
		Thread.sleep(300);
		myinspectionsscreen.selectInspectionForAction(inspectnumber);
		
		myinspectionsscreen.clickApproveInspections();
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);

		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspectnumber);
		approveinspscreen.clickApproveAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		
		servicerequestsscreen.clickHomeButton();
		Thread.sleep(4000);
		servicerequestsscreen.clickHomeButton();
		boolean onhold = false;
		for (int i= 0; i < 7; i++) {
			servicerequestsscreen = homescreen.clickServiceRequestsButton();
			if (!servicerequestsscreen.getServiceRequestStatus(srnumber).equals("On Hold")) {
				servicerequestsscreen.clickHomeButton();
				Thread.sleep(30*1000); 
			} else {
				
				onhold = true;
				break;
			}
		}
		
		Assert.assertTrue(onhold);	
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectRejectAction();
		Helpers.acceptAlert();
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 29082:SR: Regular - Verify that Reject action is not displayed for SR in Status OnHold (Insp or WO) and not assign for Tech", 
			description = "Test Case 29082:SR: Regular - Verify that Reject action is not displayed for SR in Status OnHold (Insp or WO) and not assign for Tech")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testSRRegularVerifyThatRejectActionIsNotDisplayedForSRInStatusOnHoldInspOrWOAndNotAssignForTech(String backofficeurl, String userName, String userPassword)
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		final String _make = "Chrysler";
		final String _model = "Town & Country";
		
		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		BackOfficeHeaderPanel boheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		OperationsWebPage operationspage = boheader.clickOperationsLink();
		ServiceRequestsListWebPage srlistwebpage = operationspage.clickNewServiceRequestLink();
		srlistwebpage.selectAddServiceRequestsComboboxValue(iOSInternalProjectConstants.SR_ALL_PHASES);
		srlistwebpage.clickAddServiceRequestButton();
		
		srlistwebpage.clickCustomerEditButton();
		srlistwebpage.selectServiceRequestCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		srlistwebpage.clickDoneButton();
		
		srlistwebpage.clickVehicleInforEditButton();
		srlistwebpage.setServiceRequestVIN(VIN);
		srlistwebpage.decodeAndVerifyServiceRequestVIN(_make, _model);
		srlistwebpage.clickDoneButton();
		
		srlistwebpage.saveNewServiceRequest();
		srlistwebpage.acceptFirstServiceRequestFromList();
		
		getWebDriver().quit();
		
		resrtartApplication();
		RegularMainScreen mainscr = new RegularMainScreen(appiumdriver);
		mainscr.updateDatabase();
		RegularHomeScreen homescreen = mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertFalse(servicerequestsscreen.isRejectActionExists());
		servicerequestsscreen.selectCancelAction();
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 29083:SR: Regular - Verify that Reject action is not displayed for SR in Status Scheduled (Insp or WO) and not assign for Tech", 
			description = "SR: Regular - Verify that Reject action is not displayed for SR in Status Scheduled (Insp or WO) and not assign for Tech")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testSRRegularVerifyThatRejectActionIsNotDisplayedForSRInStatusScheduledInspOrWOAndNotAssignForTech(String backofficeurl, String userName, String userPassword)
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		final String _make = "Chrysler";
		final String _model = "Town & Country";
		final String[] servicestoadd = { "VPS1", "Dye" };
		final String[] servicestoadd2 = { "Oksi_Service_PP_Panel", "Oksi_Service_PP_Vehicle" };
		
		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		BackOfficeHeaderPanel boheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		OperationsWebPage operationspage = boheader.clickOperationsLink();
		ServiceRequestsListWebPage srlistwebpage = operationspage.clickNewServiceRequestLink();
		srlistwebpage.selectAddServiceRequestsComboboxValue(iOSInternalProjectConstants.SR_ONLY_ACC_ESTIMATE);
		srlistwebpage.clickAddServiceRequestButton();
		
		srlistwebpage.clickCustomerEditButton();
		srlistwebpage.selectServiceRequestCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		srlistwebpage.clickDoneButton();
		
		srlistwebpage.clickVehicleInforEditButton();
		srlistwebpage.setServiceRequestVIN(VIN);
		srlistwebpage.decodeAndVerifyServiceRequestVIN(_make, _model);
		srlistwebpage.clickDoneButton();
		srlistwebpage.addServicesToServiceRequest(servicestoadd);
		srlistwebpage.saveNewServiceRequest();
		String srnumber = srlistwebpage.getFirstInTheListServiceRequestNumber();
		srlistwebpage.acceptFirstServiceRequestFromList();
		
		getWebDriver().quit();
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularMainScreen mainscr = homescreen.clickLogoutButton();
		mainscr.updateDatabase();
		RegularHomeScreen homescreen = mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertFalse(servicerequestsscreen.isRejectActionExists());
		servicerequestsscreen.selectCancelAction();
		servicerequestsscreen.clickHomeButton();
		
		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		boheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		operationspage = boheader.clickOperationsLink();
		srlistwebpage = operationspage.clickNewServiceRequestLink();
		srlistwebpage.selectAddServiceRequestsComboboxValue(iOSInternalProjectConstants.SR_TYPE_WO_AUTO_CREATE);
		srlistwebpage.clickAddServiceRequestButton();
		
		srlistwebpage.clickCustomerEditButton();
		srlistwebpage.selectServiceRequestCustomer(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		srlistwebpage.clickDoneButton();
		
		srlistwebpage.clickVehicleInforEditButton();
		srlistwebpage.setServiceRequestVIN(VIN);
		srlistwebpage.decodeAndVerifyServiceRequestVIN(_make, _model);
		srlistwebpage.clickDoneButton();
		srlistwebpage.addServicesToServiceRequest(servicestoadd2);
		srlistwebpage.saveNewServiceRequest();
		srnumber = srlistwebpage.getFirstInTheListServiceRequestNumber();
		srlistwebpage.acceptFirstServiceRequestFromList();
		
		getWebDriver().quit();
		homescreen = new RegularHomeScreen(appiumdriver);
		mainscr = homescreen.clickLogoutButton();
		mainscr.updateDatabase();
		homescreen = mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertFalse(servicerequestsscreen.isRejectActionExists());
		servicerequestsscreen.selectEditServiceRequestAction();
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		Assert.assertTrue(vehiclescreeen.getTechnician() == null);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveButton();
		/*Helpers.getAlertTextAndAccept();
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		servicesscreen.clickSaveButton();
		Helpers.getAlertTextAndCancel();*/
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 33165:WO: Regular - Not multiple Service with required Panels is added one time to WO after selecting", 
			description = "WO: Regular - Not multiple Service with required Panels is added one time to WO after selecting")
	public void testWORegularNotMultipleServiceWithRequiredPanelsIsAddedOneTimeToWOAfterSelecting() throws Exception {
				
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
			
		homescreen = new RegularHomeScreen(appiumdriver);			
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
			
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
			
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SERVICE_PP_VEHICLE_NOT_MULTIPLE);
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.selectVehiclePart("Back Glass");
		selectedservicescreen.selectVehiclePart("Deck Lid");
		selectedservicescreen.saveSelectedServiceDetails();
		String alerttext = selectedservicescreen.saveSelectedServiceDetailsWithAlert();
		servicesscreen.clickAddServicesButton();
		Assert.assertTrue(alerttext.contains("You can add only one service '" + iOSInternalProjectConstants.SERVICE_PP_VEHICLE_NOT_MULTIPLE + "'"));
		Helpers.waitABit(15000);
		Assert.assertEquals(servicesscreen.getNumberOfSelectedServices(), 1);

		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SERVICE_PP_VEHICLE_NOT_MULTIPLE);
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.selectVehiclePart("Back Glass");
		selectedservicescreen.selectVehiclePart("Deck Lid");
		selectedservicescreen.saveSelectedServiceDetails();
		alerttext = selectedservicescreen.saveSelectedServiceDetailsWithAlert();
		Assert.assertTrue(alerttext.contains("You can add only one service '" + iOSInternalProjectConstants.SERVICE_PP_VEHICLE_NOT_MULTIPLE + "'"));
		Assert.assertEquals(servicesscreen.getNumberOfSelectedServices(), 1);
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 34562:WO: Verify that Bundle Items are shown when create WO from inspection", description = "WO: Verify that Bundle Items are shown when create WO from inspection")
	public void testRegularWOVerifyThatBundleItemsAreShownWhenCreateWOFromInspection()
			throws Exception {
		
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";

		homescreen = new RegularHomeScreen(appiumdriver);		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();	
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspnumber = vehiclescreeen.getInspectionNumber();
		
		
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForApprove(inspnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspnumber);
		approveinspscreen.approveInspectionApproveAllAndSignature();
		
		myinspectionsscreen.selectInspectionForCreatingWO(inspnumber);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		myinspectionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		RegularSelectedServiceBundleScreen selectedservicebundlescreen = new RegularSelectedServiceBundleScreen(appiumdriver);
		selectedservicebundlescreen.assertBundleIsSelected(iOSInternalProjectConstants.WHEEL_SERVICE);
		selectedservicebundlescreen.assertBundleIsNotSelected(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicebundlescreen.clickServicesIcon();
		Assert.assertTrue(selectedservicebundlescreen.isBundleServiceExists(iOSInternalProjectConstants.WHEEL_SERVICE));
		Assert.assertTrue(selectedservicebundlescreen.isBundleServiceExists(iOSInternalProjectConstants.DYE_SERVICE));
		selectedservicebundlescreen.clickCloseServicesPopup();
		selectedservicebundlescreen.clickCancel();
		servicesscreen.cancelOrder();
		myinspectionsscreen.clickHomeButton();
	}
	

	@Test(testName="Test Case 31743:SR: Regular - Verify that when option 'Allow to close SR' is set to ON action 'Close' is shown for selected SR on status 'Scheduled' or 'On-Hold'", 
			description = "SR Regular - Verify that when option 'Allow to close SR' is set to ON action 'Close' is shown for selected SR on status 'Scheduled' or 'On-Hold'")
	public void testSRRegularVerifyThatWhenOptionAllowToCloseSRIsSetToONActionCloseIsShownForSelectedSROnStatusScheduledOrOnHold()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_TYPE_ALLOW_CLOSE_SR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		Thread.sleep(5000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");
		
		servicerequestsscreen.selectServiceRequest(srnumber);
		Helpers.waitABit(2000);
		Assert.assertTrue(servicerequestsscreen.isCloseActionExists());
		servicerequestsscreen.clickCancel();
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 31748:SR: Regular - Verify that when option 'Allow to close SR' is set to OFF action 'Close' is not shown for selected SR on status 'Scheduled' or 'On-Hold'", 
			description = "SR: Regular - Verify that when option 'Allow to close SR' is set to OFF action 'Close' is not shown for selected SR on status 'Scheduled' or 'On-Hold'")
	public void testSRRegularVerifyThatWhenOptionAllowToCloseSRIsSetToOFFActionCloseIsNotShownForSelectedSROnStatusScheduledOrOnHold()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_TYPE_DONOT_ALLOW_CLOSE_SR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		Thread.sleep(1000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertFalse(servicerequestsscreen.isCloseActionExists());
		servicerequestsscreen.clickCancel();
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 31749:SR: Regular - Verify that alert message is shown when select 'Close' action for SR - press No alert message is close", 
			description = "SR: Regular - Verify that alert message is shown when select 'Close' action for SR - press No alert message is close")
	public void testSRRegularVerifyThatAlertMessageIsShownWhenSelectCloseActionForSRPressNoAlertMessageIsClose()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_TYPE_ALLOW_CLOSE_SR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		Thread.sleep(5000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertTrue(servicerequestsscreen.isCloseActionExists());
		servicerequestsscreen.selectCloseAction();
		String alerttext = Helpers.getAlertTextAndCancel();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CLOSE_SERVICEREQUEST);
		
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 31750:SR: Regular - Verify that alert message is shown when select 'Close' action for SR - press Yes list of status reasons is shown", 
			description = "SR: Regular - Verify that alert message is shown when select 'Close' action for SR - press Yes list of status reasons is shown")
	public void testSRRegularVerifyThatAlertMessageIsShownWhenSelectCloseActionForSRPressYesListOfStatusReasonsIsShown()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_TYPE_ALLOW_CLOSE_SR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		Thread.sleep(5000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertTrue(servicerequestsscreen.isCloseActionExists());
		servicerequestsscreen.selectCloseAction();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CLOSE_SERVICEREQUEST);
		servicerequestsscreen.clickCancelCloseReasonDialog();
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 31752:SR: Regular - Verify that when Status reason is selected Question section is shown in case it is assigned to reason on BO", 
			description = "SR: Regular - Verify that when Status reason is selected Question section is shown in case it is assigned to reason on BO")
	public void testSRRegularVerifyThatWhenStatusReasonIsSelectedQuestionSectionIsShownInCaseItIsAssignedToReasonOnBO()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_TYPE_ALLOW_CLOSE_SR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		Thread.sleep(3000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertTrue(servicerequestsscreen.isServiceRequestExists(srnumber));
		Assert.assertTrue(servicerequestsscreen.isCloseActionExists());
		servicerequestsscreen.selectCloseAction();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CLOSE_SERVICEREQUEST);
		servicerequestsscreen.selectDoneReason("All work is done. Answer questions");
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.answerQuestion2("A3");
		servicerequestsscreen.clickCloseSR();
		Assert.assertFalse(servicerequestsscreen.isServiceRequestExists(srnumber));
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 31753:SR: Regular - Verify that when Status reason is selected Question section is not shown in case it is not assigned to reason on BO", 
			description = "SR: Regular - Verify that when Status reason is selected Question section is not shown in case it is not assigned to reason on BO")
	public void testSRRegularVerifyThatWhenStatusReasonIsSelectedQuestionSectionIsNotShownInCaseItIsNotAssignedToReasonOnBO()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_TYPE_ALLOW_CLOSE_SR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		Thread.sleep(5000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		Assert.assertEquals(servicerequestsscreen.getServiceRequestStatus(srnumber), "Scheduled");
		servicerequestsscreen.selectServiceRequest(srnumber);
		Assert.assertTrue(servicerequestsscreen.isServiceRequestExists(srnumber));
		Assert.assertTrue(servicerequestsscreen.isCloseActionExists());
		servicerequestsscreen.selectCloseAction();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CLOSE_SERVICEREQUEST);
		servicerequestsscreen.selectDoneReason("All work is done. No Questions");
		Thread.sleep(2000);
		Assert.assertFalse(servicerequestsscreen.isServiceRequestExists(srnumber));
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 30083:SR: Regular - Verify that when create WO from SR message that vehicle parts are required is shown for appropriate services", 
			description = "SR: Regular - Verify that when create WO from SR message that vehicle parts are required is shown for appropriate services")
	public void testSRRegularVerifyThatWhenCreateWOFromSRMessageThatVehiclePartsAreRequiredIsShownForAppropriateServices()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_WO_ONLY);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY);
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		servicesscreen.selectService(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndCancel();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CREATE_APPOINTMENT);
		Thread.sleep(5000);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateWorkOrderRequestAction();
		Thread.sleep(3000);
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_S1_MONEY);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		ordersummaryscreen.clickSaveButton();
		
		for (int i = 0; i < 4; i++) {
			alerttext = Helpers.getAlertTextAndAccept();
			String servicedetails = alerttext.substring(alerttext.indexOf("'")+1, alerttext.indexOf("' require"));
			RegularSelectedServiceDetailsScreen selectedservicedetailsscreen = servicesscreen.openSelectedServiceDetails(servicedetails);
			
			selectedservicedetailsscreen.clickVehiclePartsCell();
			switch (servicedetails) {
			case iOSInternalProjectConstants.SR_DISC_20_PERCENT:
				 selectedservicedetailsscreen.selectVehiclePart("Hood");
	             break;
			case iOSInternalProjectConstants.SR_S1_MONEY_PANEL:
				 selectedservicedetailsscreen.selectVehiclePart("Back Glass");
	             break;     
			case iOSInternalProjectConstants.SR_S1_MONEY:
				 selectedservicedetailsscreen.selectVehiclePart("Hood");
	             break;
			case iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE:
				 selectedservicedetailsscreen.selectVehiclePart("Grill");
	             break;
			}
			
			selectedservicedetailsscreen.saveSelectedServiceDetails();
			selectedservicedetailsscreen.saveSelectedServiceDetails();
			servicesscreen.clickSaveButton();
		}
		Thread.sleep(3000);
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		servicerequestsscreen.clickServiceRequestSummaryOrdersButton();
		Helpers.elementExists(wonumber);
		servicerequestsscreen.clickHomeButton();
		servicerequestsscreen.clickHomeButton();
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 30084:SR: Regular - Verify that when create Inspection from SR message that vehicle parts are required is shown for appropriate services", 
			description = "SR: Regular - Verify that when create Inspection from SR message that vehicle parts are required is shown for appropriate services")
	public void testSRRegularVerifyThatWhenCreateInspectionFromSRMessageThatVehiclePartsAreRequiredIsShownForAppropriateServices()
			throws Exception {
		
		final String VIN = "2A4RR4DE2AR286008";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_INSP_ONLY);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY);
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		servicesscreen.selectService(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndCancel();
		Assert.assertEquals(alerttext, AlertsCaptions.ALERT_CREATE_APPOINTMENT);
		Thread.sleep(5000);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		Thread.sleep(3000);
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.INSP_FOR_CALC);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.verifyMakeModelyearValues("Chrysler", "Town and Country", "2010");
		String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());

		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_S1_MONEY);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		servicesscreen.clickSaveButton();
		
		for (int i = 0; i < 4; i++) {
			alerttext = Helpers.getAlertTextAndAccept();
			String servicedetails = alerttext.substring(alerttext.indexOf("'")+1, alerttext.indexOf("' require"));
			RegularSelectedServiceDetailsScreen selectedservicedetailsscreen = servicesscreen.openSelectedServiceDetails(servicedetails);
			
			selectedservicedetailsscreen.clickVehiclePartsCell();
			switch (servicedetails) {
			case iOSInternalProjectConstants.SR_S1_MONEY_PANEL:
				 selectedservicedetailsscreen.selectVehiclePart("Hood");
	             break;
			case iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE:
				 selectedservicedetailsscreen.selectVehiclePart("Back Glass");
	             break;     
			case iOSInternalProjectConstants.SR_DISC_20_PERCENT:
				 selectedservicedetailsscreen.selectVehiclePart("Hood");
	             break;
			case iOSInternalProjectConstants.SR_S1_MONEY:
				 selectedservicedetailsscreen.selectVehiclePart("Grill");
	             break;
			}
			selectedservicedetailsscreen.saveSelectedServiceDetails();
			selectedservicedetailsscreen.saveSelectedServiceDetails();
			servicesscreen.clickSaveButton();
		}
		
		Thread.sleep(3000);
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		servicerequestsscreen.clickServiceRequestSummaryInspectionsButton();
		Thread.sleep(8000);
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		myinspectionsscreen.assertInspectionExists(inspnumber);
		myinspectionsscreen.clickHomeButton();
		servicerequestsscreen.clickHomeButton();
		Thread.sleep(3000);
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 38749:Inspections: Regular - Verify that on inspection approval screen selected price matrix value is shown", 
			description = "Verify that on inspection approval screen selected price matrix value is shown")
	public void testRegularVerifyThatOnInspectionApprovalScreenSelectedPriceMatrixValueIsShown() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.TEST_COMPANY_CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_TYPE_FOR_PRICE_MATRIX_APP_REQ);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen("Price Matrix Zayats");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("VP2 zayats");
		pricematrix.setSizeAndSeverity("CENT", "LIGHT");
		pricematrix.clickSaveButton();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);		
		Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$100.00");
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$100.00");
		
		pricematrix.selectNextScreen("Hail Matrix");
		pricematrix.selectPriceMatrix("L QUARTER");
		pricematrix.setSizeAndSeverity("DIME", "VERY LIGHT");
		pricematrix.clickSaveButton();
		Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$65.00");
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$165.00");
		
		pricematrix.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.clickSaveButton();
		Helpers.waitABit(1000);
		myinspectionsscreen.selectInspectionForAction(inspectionnumber);
		
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		Helpers.waitABit(7000);
		approveinspscreen.selectInspection(inspectionnumber);
		Assert.assertTrue(approveinspscreen.isInspectionServiceExistsForApprove("Dent Removal"));
		Assert.assertTrue(approveinspscreen.isInspectionServiceExistsForApprove("Test service price matrix"));
		Assert.assertEquals(approveinspscreen.getInspectionServicePrice("Dent Removal"), "$65.00");
		Assert.assertEquals(approveinspscreen.getInspectionServicePrice("Test service price matrix"), "$100.00");
		approveinspscreen.clickCancelButton();
		approveinspscreen.clickCancelButton();
		myinspectionsscreen.selectInspectionForEdit(inspectionnumber);
		myinspectionsscreen.selectNextScreen("Price Matrix Zayats");
		pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("VP2 zayats");
		pricematrix.clearVehicleData();
		//pricematrix.clickBackButton();
		Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$0.00");
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$65.00");
		vehiclescreeen.clickSaveButton();
		Helpers.waitABit(1000);
		myinspectionsscreen.selectInspectionForAction(inspectionnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspectionnumber);
		Assert.assertEquals(approveinspscreen.getInspectionServicePrice("Dent Removal"), "$65.00");
		Assert.assertFalse(approveinspscreen.isInspectionServiceExistsForApprove("Test service price matrix"));
		approveinspscreen.clickCancelButton();
		approveinspscreen.clickCancelButton();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 31451:Inspection - Regular: Verify that question section is shown per service for first selected panel when QF is not required", 
			description = "Verify that question section is shown per service for first selected panel when QF is not required")
	public void testRegularVerifyThatQuestionSectionIsShownPerServiceForFirstSelectedPanelWhenQFIsNotRequired() throws Exception {

		final String[] vehicleparts = { "Front Bumper", "Grill", "Hood", "Left Fender" };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_FOR_CALC);

		myinspectionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		selectedservicedetailscreen.clickSaveButton();
		for (String vehiclepart : vehicleparts)
			selectedservicedetailscreen.selectVehiclePart(vehiclepart);
		selectedservicedetailscreen.clickSaveButton();
		final String selectedhehicleparts = servicesscreen.getListOfSelectedVehicleParts();
		
		for (String vehiclepart : vehicleparts)
			Assert.assertTrue(selectedhehicleparts.contains(vehiclepart));
		servicesscreen.clickSaveButton();
		servicesscreen.clickAddServicesButton();
		for (int i = 0; i < vehicleparts.length; i++) {
			servicesscreen.openServiceDetailsByIndex(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE, i);
			Assert.assertFalse(selectedservicedetailscreen.isQuestionFormCellExists());
			selectedservicedetailscreen.clickSaveButton();
		}
		servicesscreen.clickSaveButton();
		Helpers.acceptAlert();
		servicesscreen.cancelOrder();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 31963:Inspections: Regular - Verify that keyboard is not shown over the VIN when it is entered in case only VIN is present on Vehicle screen", 
			description = "Verify that keyboard is not shown over the VIN when it is entered in case only VIN is present on Vehicle screen")
	public void testRegularVerifyThatKeyboardIsNotShownOverTheVINWhenItIsEnteredInCaseOnlyVINIsPresentOnVehicleScreen() throws Exception {

		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType ("Inspection_VIN_only");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.getVINField().click();
		Assert.assertTrue(vehiclescreeen.getVINField().isDisplayed());
		Helpers.keyboadrType("\n");
		//vehiclescreeen.clickChangeScreen();
		vehiclescreeen.cancelOrder();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 42376:Inspections: Regular - Verify that when edit inspection selected vehicle parts for services are present", 
			description = "Verify that when edit inspection selected vehicle parts for services are present")
	public void testRegularVerifyThatWhenEditInspectionSelectedVehiclePartsForServicesArePresent() throws Exception {

		final String VIN = "1D7HW48NX6S507810";
		final String[] vehicleparts = { "Deck Lid", "Hood", "Roof" };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType ("Inspection_for_auto_WO_line_appr");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Dakota", "2006");
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		for (int i=0; i < vehicleparts.length; i++)
			selectedservicedetailscreen.selectVehiclePart(vehicleparts[i]);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForEdit(inspectionnumber);
		myinspectionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		for (int i=0; i < vehicleparts.length; i++) {
			servicesscreen.openServiceDetailsByIndex(iOSInternalProjectConstants.SR_S1_MONEY, i);
			selectedservicedetailscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
			Assert.assertEquals(selectedservicedetailscreen.getVehiclePartValue(), vehicleparts[i]);
			selectedservicedetailscreen.saveSelectedServiceDetails();
		}
		Helpers.waitABit(1000);
		servicesscreen.cancelOrder();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 42388:Inspections: Regular - Verify that it is possible to save as Final inspection linked to SR", description = "Verify that it is possible to save as Final inspection linked to SR")
	public void testRegularVerifyThatItIsPossibleToSaveAsFinalInspectionLinkedToSR() throws Exception {
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		Thread.sleep(3000);
		servicerequestsscreen.clickAddButton();
		
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_ALL_PHASES);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Dodge", "Dakota", "2006");
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.DYE_SERVICE);
		servicesscreen.clickAddServicesButton();
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		questionsscreen.clickSaveButton();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name(AlertsCaptions.ALERT_CREATE_APPOINTMENT)).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("No"))
				.click();
		Thread.sleep(8000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		Thread.sleep(2000);
		servicerequestsscreen.selectInspectionType("Insp_Draft_Mode");
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		
		
		 vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveAsDraft();		
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		Thread.sleep(5000);
		servicerequestsscreen.clickServiceRequestSummaryInspectionsButton();
		Thread.sleep(3000);
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		myinspectionsscreen.selectInspectionForEdit(inspectionnumber);
		Thread.sleep(2000);
		myinspectionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveAsFinal();
		myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		Assert.assertTrue(myinspectionsscreen.isInspectionIsApproveButtonExists(inspectionnumber));
		myinspectionsscreen.clickHomeButton();
		servicerequestsscreen.clickHomeButton();
		Thread.sleep(3000);
		servicerequestsscreen.clickHomeButton();
	}
	
	
	@Test(testName = "Test Case 33117:Inspections: Regular - Verify that when final inspection is copied servises are copied without statuses (approved, declined, skipped)", 
			description = "Verify that when final inspection is copied servises are copied without statuses (approved, declined, skipped)")
	public void testVerifyThatWhenFinalInspectionIsCopiedServisesAreCopiedWithoutStatuses_Approved_Declined_Skipped() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_DRAFT_MODE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S4_BUNDLE);
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		servicedetailsscreen.answerQuestion2("A1");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.selectVehiclePart("Back Glass");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);	
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("Price Matrix Zayats");
		pricematrix.selectPriceMatrix("VP1 zayats");
		pricematrix.setSizeAndSeverity("CENT", "MEDIUM");
		pricematrix.selectDiscaunt("Test service zayats");
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		Helpers.waitABit(500);
		
		servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_PANEL);
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.selectVehiclePart("Grill");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveAsFinal();
		myinspectionsscreen.clickActionButton();
		myinspectionsscreen.selectInspectionForAction(inspnumber);
		
		myinspectionsscreen.clickApproveInspections();
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspnumber);
		approveinspscreen.isInspectionServiceExistsForApprove(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		approveinspscreen.isInspectionServiceExistsForApprove(iOSInternalProjectConstants.SR_S4_BUNDLE);
		approveinspscreen.isInspectionServiceExistsForApprove("Test service zayats");
		approveinspscreen.isInspectionServiceExistsForApprove(iOSInternalProjectConstants.OKSI_SERVICE_PP_PANEL);
		approveinspscreen.isInspectionServiceExistsForApprove(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		
		approveinspscreen.clickApproveAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		myinspectionsscreen.selectInspectionForCopy(inspnumber);
		vehiclescreeen.selectNextScreen("Test_pack_for_calc");
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.SR_S4_BUNDLE);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.OKSI_SERVICE_PP_PANEL);
		servicesscreen.assertServiceIsSelected(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		servicesscreen.clickSaveAsFinal();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 33116:Inspections: Regular - Verify that text notes are copied to new inspections when use copy action", description = "Verify that text notes are copied to new inspections when use copy action")
	public void testVerifyThatTextNotesAreCopiedToNewInspectionsWhenUseCopyAction() throws Exception {
			
		final String VIN  = "1D7HW48NX6S507810";
		final String _notes = "Test notes for copy";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		RegularVehicleScreen vehiclescreeen = myinspectionsscreen.selectDefaultInspectionType();
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		RegularNotesScreen notesscreen = vehiclescreeen.clickNotesButton();
		notesscreen.setNotes(_notes);
		notesscreen.clickSaveButton();
		vehiclescreeen.clickSaveButton();
		Thread.sleep(2000);
		myinspectionsscreen.selectInspectionForCopy(inspnumber);
		Thread.sleep(2000);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		String copiedinspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.clickSaveButton();
		Assert.assertTrue(myinspectionsscreen.isNotesIconPresentForInspection(copiedinspnumber));
		notesscreen = myinspectionsscreen.openInspectionNotesScreen(copiedinspnumber);
		Assert.assertTrue(notesscreen.isNotesPresent(_notes));
		notesscreen.clickSaveButton();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 33154:Inspections: Regular - Verify that it is possible to approve Team Inspections use multi select", 
			description = "Verify that it is possible to approve Team Inspections use multi select")
	public void testVerifyThatItIsPossibleToApproveTeamInspectionsUseMultiSelect() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		List<String> inspnumbers = new ArrayList<String>();
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		for (int i = 0; i < 3; i++) {
			myinspectionsscreen.clickAddInspectionButton();
			myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_DRAFT_MODE);
			RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
			vehiclescreeen.setVIN(VIN);
			inspnumbers.add(vehiclescreeen.getInspectionNumber());
			vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
			RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
			servicesscreen.clickToolButton();
			servicesscreen.selectService(iOSInternalProjectConstants.SR_S4_BUNDLE);
			servicesscreen.clickAddServicesButton();
			servicesscreen.clickSaveAsFinal();
			Helpers.waitABit(5000);
		}
		myinspectionsscreen.clickHomeButton();
		RegularTeamInspectionsScreen teaminspectionsscreen = homescreen.clickTeamInspectionsButton();
		teaminspectionsscreen.clickActionButton();
		for (int i = 0; i < 3; i++) {
			teaminspectionsscreen.selectInspectionForAction(inspnumbers.get(i));
		}
		
		teaminspectionsscreen.clickApproveInspections();
		teaminspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		for (int i = 0; i < 3; i++) {
			approveinspscreen.selectInspection(inspnumbers.get(i));
			approveinspscreen.clickApproveAllServicesButton();
			approveinspscreen.clickSaveButton();
			Helpers.waitABit(4000);
		}
		approveinspscreen.clickSingnAndDrawApprovalSignature ();		
		approveinspscreen.clickDoneButton();
		
		teaminspectionsscreen.clickActionButton();
		for (int i = 0; i < 3; i++) {
			myinspectionsscreen.selectInspectionForAction(inspnumbers.get(i));
		}
		teaminspectionsscreen.clickActionButton();
		Assert.assertFalse(teaminspectionsscreen.isApproveInspectionMenuActionExists());
		Assert.assertTrue(teaminspectionsscreen.isSendEmailInspectionMenuActionExists());
		teaminspectionsscreen.clickCancel();
		teaminspectionsscreen.clickDoneButton();
		teaminspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 33869:Inspections: Regular - Verify that Services on Service Package are grouped by type selected on Insp type->Wizard", 
			description = "Verify that Services on Service Package are grouped by type selected on Insp type->Wizard")
	public void testVerifyThatServicesOnServicePackageAreGroupedByTypeSelectedOnInspTypeWizard() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		List<String> inspnumbers = new ArrayList<String>();
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType ("Inspection_group_service");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		inspnumbers.add(vehiclescreeen.getInspectionNumber());
		vehiclescreeen.selectNextScreen("Zayats Section1");
		vehiclescreeen.selectNextScreen("Zayats test pack");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		servicesscreen.clickAddServicesButton();
		servicesscreen.selectNextScreen("Test_pack_for_calc");
		servicesscreen.selectPriceMatrices("Back Glass");
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails("Oksi_Service_PP_Vehicle");
		selectedservicedetailscreen.answerQuestion2("A1");	
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickBackServicesButton();
		servicesscreen.selectNextScreen("SR_FeeBundle");
		servicesscreen.selectPriceMatrices("Price Adjustment");
		servicesscreen.clickToolButton();
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails("SR_S6_Bl_I1_Percent");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickBackServicesButton();
		servicesscreen.clickSaveAsFinal();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Question 'Signature' in section 'Follow up Requested' should be answered."));
		Thread.sleep(1000);
		Helpers.drawRegularQuestionsSignature();
		servicesscreen.clickSaveAsFinal();
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Question 'Tax_Point_1' in section 'BATTERY PERFORMANCE' should be answered.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.selectTaxPoint("Test Answer 1");
		servicesscreen.clickSaveAsFinal();
		
		Assert.assertTrue(appiumdriver.findElement(
				MobileBy.name("Question 'Question 2' in section 'Zayats Section1' should be answered.")).isDisplayed());
		appiumdriver.findElement(
				MobileBy.name("Close"))
				.click();
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		servicesscreen.clickSaveAsFinal();
		myinspectionsscreen.assertInspectionExists(inspnumber);
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 44407:Inspections: Regular - Verify that all instances of one service are copied from inspection to WO", 
			description = "Verify that all instances of one service are copied from inspection to WO")
	public void testVerifyThatAllInstancesOfOneServiceAreCopiedFromInspectionToWO() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String firstprice = "43";
		final String secondprice = "33";
		final String secondquantity = "4";
		final String[] vehicleparts = { "Front Bumper", "Grill", "Hood" };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService("3/4\" - Penny Size");
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");
		servicedetailsscreen.setServicePriceValue(firstprice);
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickToolButton();
		servicedetailsscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");
		servicedetailsscreen.setServicePriceValue(secondprice);
		servicedetailsscreen.setServiceQuantityValue(secondquantity);
		servicedetailsscreen.saveSelectedServiceDetails();
		Helpers.waitABit(500);
		servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		servicedetailsscreen.clickVehiclePartsCell();
		for (String vp: vehicleparts)
			servicedetailsscreen.selectVehiclePart(vp);
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForApprove(inspnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspnumber);
		approveinspscreen.approveInspectionApproveAllAndSignature();
		
		myinspectionsscreen.selectInspectionForCreatingWO(inspnumber);
		RegularMyWorkOrdersScreen myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		servicesscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.searchServiceByName("3/4\" - Penny Size");
		servicesscreen.assertServiceIsSelectedWithServiceValues("3/4\" - Penny Size", "$43.00 x 1.00");
		servicesscreen.assertServiceIsSelectedWithServiceValues("3/4\" - Penny Size", "$33.00 x 4.00");
		servicesscreen.searchServiceByName(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		Assert.assertEquals(servicesscreen.getNumberOfServiceSelectedItems(iOSInternalProjectConstants.SR_S1_MONEY_PANEL), vehicleparts.length);
		servicesscreen.cancelOrder();
		myinspectionsscreen.clickHomeButton();
		
	}
	
	@Test(testName = "Test Case 33918:Inspections: Regular - Verify that Assign button is present when select some Tech in case Direct Assign option is set for inspection type", 
			description = "Verify that Assign button is present when select some Tech in case Direct Assign option is set for inspection type")
	public void testVerifyThatAssignButtonIsPresentWhenSelectSomeTechInCaseDirectAssignOptionIsSetForInspectionType() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType ("Inspection_direct_assign");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveAsFinal();
		myinspectionsscreen.selectInspectionToAssign(inspnumber);
		Assert.assertTrue(myinspectionsscreen.isAssignButtonExists());
		myinspectionsscreen.clickHomeButton();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 34285:Inspections: Regular - Verify that during Line approval ''Select All'' buttons are working correctly", 
			description = "Verify that during Line approval ''Select All'' buttons are working correctly")
	public void testVerifyThatDuringLineApprovalSelectAllButtonsAreWorkingCorrectly() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_FOR_AUTO_WO_LINE_APPR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_PANEL);
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Grill");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SERVICE_PP_VEHICLE_NOT_MULTIPLE);
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("123");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		myinspectionsscreen.selectInspectionForAction(inspnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspnumber);
		Assert.assertTrue(approveinspscreen.isInspectionServiceExistsForApprove("Oksi_Service_PP_Panel (Grill)"));
		Assert.assertTrue(approveinspscreen.isInspectionServiceExistsForApprove("Service_PP_Vehicle_not_multiple (123)"));
		approveinspscreen.clickApproveAllServicesButton();
		Assert.assertEquals(approveinspscreen.getNumberOfActiveApproveButtons(), 2);
		approveinspscreen.clickDeclineAllServicesButton();
		Assert.assertEquals(approveinspscreen.getNumberOfActiveDeclineButtons(), 2);
		approveinspscreen.clickSkipAllServicesButton();
		Assert.assertEquals(approveinspscreen.getNumberOfActiveSkipButtons(), 2);
		approveinspscreen.clickApproveAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 30012:Inspections: Regular - Verify that Approve option is not present for approved inspection in multi-select mode", 
			description = "Verify that Approve option is not present for approved inspection in multi-select mode")
	public void testVerifyThatApproveOptionIsNotPresentForApprovedInspectionInMultiselectMode() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		ArrayList<String> inspections = new ArrayList<String>();
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		for (int i = 0; i <2; i++) {
			myinspectionsscreen.clickAddInspectionButton();
			myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSPTYPE_FOR_SR_INSPTYPE);
			RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
			vehiclescreeen.setVIN(VIN);
			inspections.add(vehiclescreeen.getInspectionNumber());
		
			vehiclescreeen.selectNextScreen("Zayats Section1");
			RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
			questionsscreen.swipeScreenUp();
			questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
			vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
			RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
			servicesscreen.clickSaveButton();
			myinspectionsscreen.selectInspectionForAction(inspections.get(i));
			myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
			
			RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
			approveinspscreen.selectInspection(inspections.get(i));
			approveinspscreen.clickApproveAllServicesButton();
			approveinspscreen.clickSaveButton();
			approveinspscreen.clickSingnAndDrawApprovalSignature ();
			approveinspscreen.clickDoneButton();
			Helpers.waitABit(1000);
		}
		
		myinspectionsscreen.clickActionButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.selectInspectionForAction(inspections.get(i));
		}
		myinspectionsscreen.clickActionButton();
		Assert.assertFalse(myinspectionsscreen.isApproveInspectionMenuActionExists());
		myinspectionsscreen.clickCancel();
		myinspectionsscreen.clickDoneButton();
		homescreen = myinspectionsscreen.clickHomeButton();
		RegularTeamInspectionsScreen teaminspectionsscreen = homescreen.clickTeamInspectionsButton();
		Helpers.waitABit(1000);
		teaminspectionsscreen.clickActionButton();
		for (int i = 0; i < 2; i++) {
			teaminspectionsscreen.selectInspectionForAction(inspections.get(i));
		}
		myinspectionsscreen.clickActionButton();
		Assert.assertFalse(teaminspectionsscreen.isApproveInspectionMenuActionExists());
		teaminspectionsscreen.clickCancel();
		teaminspectionsscreen.clickDoneButton();
		teaminspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 30013:Inspections: Regular - Verify that Approve option is present in multi-select mode only one or more not approved inspections are selected", 
			description = "Verify that Approve option is present in multi-select mode only one or more not approved inspections are selected")
	public void testVerifyThatApproveOptionIsPresentInMultiselectModeOnlyOneOrMoreNotApprovedInspectionsAreSelected() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		ArrayList<String> inspections = new ArrayList<String>();
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		for (int i = 0; i <2; i++) {
			myinspectionsscreen.clickAddInspectionButton();
			myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSPTYPE_FOR_SR_INSPTYPE);
			RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
			vehiclescreeen.setVIN(VIN);
			inspections.add(vehiclescreeen.getInspectionNumber());
		
			vehiclescreeen.selectNextScreen("Zayats Section1");
			RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
			questionsscreen.swipeScreenUp();
			questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
			vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
			RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
			servicesscreen.clickSaveButton();
			Helpers.waitABit(3000);
		}
		myinspectionsscreen.selectInspectionForAction(inspections.get(0));
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
			
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspections.get(0));
		approveinspscreen.clickApproveAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		Helpers.waitABit(1000);
		
		myinspectionsscreen.clickActionButton();
		for (int i = 0; i < 2; i++) {
			myinspectionsscreen.selectInspectionForAction(inspections.get(i));
		}
		myinspectionsscreen.clickActionButton();
		Assert.assertTrue(myinspectionsscreen.isApproveInspectionMenuActionExists());
		myinspectionsscreen.clickCancel();
		myinspectionsscreen.clickDoneButton();
		homescreen = myinspectionsscreen.clickHomeButton();
		RegularTeamInspectionsScreen teaminspectionsscreen = homescreen.clickTeamInspectionsButton();
		Helpers.waitABit(1000);
		teaminspectionsscreen.clickActionButton();
		for (int i = 0; i < 2; i++) {
			teaminspectionsscreen.selectInspectionForAction(inspections.get(i));
		}
		myinspectionsscreen.clickActionButton();
		Assert.assertTrue(teaminspectionsscreen.isApproveInspectionMenuActionExists());
		teaminspectionsscreen.clickCancel();
		teaminspectionsscreen.clickDoneButton();
		teaminspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 30853:Inspections: Regular - Verify that when option ''Draft Mode'' is set to ON - when save inspection provide prompt to a user to select either Draft or Final,"
			+ "Test Case 30855:Inspections: Regular - Verify that for Draft inspection following options are not available (Approve, Create WO, Create SR, Copy inspection)", 
			description = "Verify that when option ''Draft Mode'' is set to ON - when save inspection provide prompt to a user to select either Draft or Final,"
					+ "Verify that for Draft inspection following options are not available (Approve, Create WO, Create SR, Copy inspection)")
	public void testVerifyThatWhenOptionDraftModeIsSetToONWhenSaveInspectionProvidePromptToAUserToSelectEitherDraftOrFinal() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_DRAFT_MODE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		regularselectedservicedetailsscreen.clickVehiclePartsCell();
		regularselectedservicedetailsscreen.selectVehiclePart("Hood");
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		regularselectedservicedetailsscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		regularselectedservicedetailsscreen.clickVehiclePartsCell();
		regularselectedservicedetailsscreen.selectVehiclePart("Grill");
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveAsDraft();
		Assert.assertTrue(myinspectionsscreen.isDraftIconPresentForInspection(inspnumber));
		myinspectionsscreen.clickOnInspection(inspnumber);
		Assert.assertFalse(myinspectionsscreen.isApproveInspectionMenuActionExists());
		Assert.assertFalse(myinspectionsscreen.isCreateWOInspectionMenuActionExists());
		Assert.assertFalse(myinspectionsscreen.isCreateServiceRequestInspectionMenuActionExists());
		Assert.assertFalse(myinspectionsscreen.isCopyInspectionMenuActionExists());
		myinspectionsscreen.clickCancel();
		homescreen = myinspectionsscreen.clickHomeButton();
		
		RegularTeamInspectionsScreen teaminspectionsscreen = homescreen.clickTeamInspectionsButton();
		Assert.assertTrue(teaminspectionsscreen.isDraftIconPresentForInspection(inspnumber));
		
		
		teaminspectionsscreen.clickHomeButton();		
	}
	
	@Test(testName = "Test Case 32286:Inspections: Regular - Verify that amount of approved services are shown on BO > inspections list > column ApprovedAmount", 
			description = "Verify that amount of approved services are shown on BO > inspections list > column ApprovedAmount")
	@Parameters({ "user.name", "user.psw" })
	public void testVerifyThatAmountOfApprovedServicesAreShownOnBOInspectionsListColumnApprovedAmount(String userName, String userPassword) throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_FOR_AUTO_WO_LINE_APPR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForApprove(inspnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspnumber);
		approveinspscreen.clickDeclineAllServicesButton();
		approveinspscreen.selectInspectionServiceToApprove(iOSInternalProjectConstants.SR_S1_MONEY + " (Grill)");
		approveinspscreen.clickSaveButton();
		
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		myinspectionsscreen.clickHomeButton();
		Thread.sleep(10000);
		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Company/Inspections.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InspectionsWebPage inspectionspage = PageFactory.initElements(
				webdriver, InspectionsWebPage.class);
		inspectionspage.makeSearchPanelVisible();
		inspectionspage.selectSearchStatus("All active");
		inspectionspage.searchInspectionByNumber(inspnumber);		
		Assert.assertEquals(inspectionspage.getInspectionAmountApproved(inspnumber), "$2,000.00");
		Assert.assertEquals(inspectionspage.getInspectionStatus(inspnumber), "Approved");
		Assert.assertEquals(inspectionspage.getInspectionApprovedTotal(inspnumber), "$2,000.00");
		getWebDriver().quit();
	}
	
	@Test(testName="Test Case 51336:WO: Regular - Verify that approve WO is working correct under Team WO", 
			description = "WO: Regular - Verify that approve WO is working correct under Team WO")
	public void testWOVerifyThatApproveWOIsWorkingCorrectUnderTeamWO()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O04TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		final String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		
		homescreen = myworkordersscreen.clickHomeButton();
		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickSearchButton();
		teamworkordersscreen.setFilterLocation("All locations");
		teamworkordersscreen.clickSaveFilter();
		
		Assert.assertTrue(teamworkordersscreen.woExists(wonumber));
		Assert.assertTrue(teamworkordersscreen.isWorkOrderHasApproveIcon(wonumber));
		teamworkordersscreen.approveWorkOrder(wonumber, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		Assert.assertTrue(teamworkordersscreen.isWorkOrderHasActionIcon(wonumber));
		teamworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 27717:Invoices: Regular - Verify that it is posible to add payment from device for draft invoice", 
			description = "Invoices: Regular - Verify that it is posible to add payment from device for draft invoice")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testInvoicesVerifyThatItIsPosibleToAddPaymentFromDeviceForDraftInvoice(String backofficeurl, String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "WDZPE7CD9E5889222";
		final String _po  = "12345";
		final String cashcheckamount = "100";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);


		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
			
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INVOICE_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectPriceMatrices("Price Matrix Zayats");		
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);			
		pricematrix.selectPriceMatrix("VP2 zayats");
		pricematrix.switchOffOption("PDR");
		pricematrix.selectDiscaunt("Dye");
		pricematrix.selectDiscaunt("Wheel");
		pricematrix.selectDiscaunt("Test service zayats");
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.searchServiceByName(iOSInternalProjectConstants.SR_S4_Bl_I1_M);
		servicesscreen.selectSubService(iOSInternalProjectConstants.SR_S4_Bl_I1_M);
		servicesscreen.searchServiceByName(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword("Zayats", "1111");
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType(iOSInternalProjectConstants.CUSTOMER_APPROVALON_INVOICETYPE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsDraft();
		homescreen = myworkordersscreen.clickHomeButton();
		
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.selectInvoice(invoicenumber);
		myinvoicesscreen.clickEditPopup();
		invoiceinfoscreen = new RegularInvoiceInfoScreen(appiumdriver);
		Assert.assertEquals(invoiceinfoscreen.getInvoicePOValue(), _po);
		invoiceinfoscreen.clickInvoicePayButton();
		invoiceinfoscreen.changePaynentMethodToCashNormal();
		invoiceinfoscreen.setCashCheckAmountValue(cashcheckamount);
		invoiceinfoscreen.clickInvoicePayDialogButon();
		Assert.assertEquals(invoiceinfoscreen.getInvoicePOValue(), _po);
		invoiceinfoscreen.clickSaveAsDraft();
		homescreen = myinvoicesscreen.clickHomeButton();
		Helpers.waitABit(10000);
		
		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		BackOfficeHeaderPanel boheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		OperationsWebPage operationspage = boheader.clickOperationsLink();
		InvoicesWebPage invoiceswebpage = operationspage.clickInvoicesLink();
		invoiceswebpage.selectSearchStatus(WebConstants.InvoiceStatuses.INVOICESTATUS_ALL);
		invoiceswebpage.setSearchInvoiceNumber(invoicenumber);
		invoiceswebpage.clickFindButton();
	
		Assert.assertEquals(invoiceswebpage.getInvoicePONumber(invoicenumber), _po);
		Assert.assertEquals(invoiceswebpage.getInvoicePOPaidValue(invoicenumber), PricesCalculations.getPriceRepresentation(cashcheckamount));
		String mainWindowHandle = webdriver.getWindowHandle();
		InvoicePaymentsTabWebPage invoicepayments = invoiceswebpage.clickInvoicePayments(invoicenumber);
		Assert.assertEquals(invoicepayments.getPaymentsTypeAmountValue("Cash/Check"), PricesCalculations.getPriceRepresentation(cashcheckamount));
		Assert.assertEquals(invoicepayments.getPaymentsTypeCreatedByValue("Cash/Check"), "Employee Simple 20%");
		
		Assert.assertEquals(invoicepayments.getPaymentsTypeAmountValue("PO/RO"), PricesCalculations.getPriceRepresentation("0"));
		Assert.assertEquals(invoicepayments.getPaymentsTypeCreatedByValue("PO/RO"), "Back Office");
		invoicepayments.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Test(testName="Test Case 27739:Invoices: Regular - Verify that payment is send to BO when PO# is changed under My invoice", 
			description = "Invoices: Regular - Verify that payment is send to BO when PO# is changed under My invoice")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testInvoicesVerifyThatPaymentIsSendToBOWhenPONumberIsChangedUnderMyInvoice(String backofficeurl, String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "WDZPE7CD9E5889222";
		final String _po  = "12345";
		final String newpo  = "New test PO";
		final String cashcheckamount = "100";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);


		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
			
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INVOICE_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectPriceMatrices("Price Matrix Zayats");		
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);			
		pricematrix.selectPriceMatrix("VP2 zayats");
		pricematrix.switchOffOption("PDR");
		pricematrix.selectDiscaunt("Dye");
		pricematrix.selectDiscaunt("Wheel");
		pricematrix.selectDiscaunt("Test service zayats");
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.searchServiceByName(iOSInternalProjectConstants.SR_S4_Bl_I1_M);
		servicesscreen.selectSubService(iOSInternalProjectConstants.SR_S4_Bl_I1_M);
		servicesscreen.searchServiceByName(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword("Zayats", "1111");
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType(iOSInternalProjectConstants.CUSTOMER_APPROVALON_INVOICETYPE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsDraft();
		homescreen = myworkordersscreen.clickHomeButton();
		
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.selectInvoice(invoicenumber);
		myinvoicesscreen.clickEditPopup();
		invoiceinfoscreen = new RegularInvoiceInfoScreen(appiumdriver);
		Assert.assertEquals(invoiceinfoscreen.getInvoicePOValue(), _po);
		invoiceinfoscreen.clickInvoicePayButton();
		invoiceinfoscreen.changePaynentMethodToCashNormal();
		invoiceinfoscreen.setCashCheckAmountValue(cashcheckamount);
		invoiceinfoscreen.clickInvoicePayDialogButon();
		Assert.assertEquals(invoiceinfoscreen.getInvoicePOValue(), _po);
		invoiceinfoscreen.clickSaveAsDraft();
		
		myinvoicesscreen.selectInvoice(invoicenumber);
		myinvoicesscreen.clickChangePOPopup();
		myinvoicesscreen.changePO(newpo);
		myinvoicesscreen.clickHomeButton();
		Helpers.waitABit(10000);
		
		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		BackOfficeHeaderPanel boheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		OperationsWebPage operationspage = boheader.clickOperationsLink();
		InvoicesWebPage invoiceswebpage = operationspage.clickInvoicesLink();
		invoiceswebpage.selectSearchStatus(WebConstants.InvoiceStatuses.INVOICESTATUS_ALL);
		invoiceswebpage.setSearchInvoiceNumber(invoicenumber);
		invoiceswebpage.clickFindButton();
	
		Assert.assertEquals(invoiceswebpage.getInvoicePONumber(invoicenumber), newpo);
		Assert.assertEquals(invoiceswebpage.getInvoicePOPaidValue(invoicenumber), PricesCalculations.getPriceRepresentation(cashcheckamount));
		String mainWindowHandle = webdriver.getWindowHandle();
		InvoicePaymentsTabWebPage invoicepayments = invoiceswebpage.clickInvoicePayments(invoicenumber);
		
		Assert.assertEquals(invoicepayments.getPaymentDescriptionTypeAmountValue("PO #: " + newpo), PricesCalculations.getPriceRepresentation("0"));
		invoicepayments.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Test(testName="Test Case 27741:Invoices: Regular - Verify that payment is send to BO when PO# is changed under Team invoice", 
			description = "Invoices: Regular - Verify that payment is send to BO when PO# is changed under Team invoice")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testInvoicesVerifyThatPaymentIsSendToBOWhenPONumberIsChangedUnderTeamInvoice(String backofficeurl, String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "WDZPE7CD9E5889222";
		final String _po  = "12345";
		final String newpo  = "New test PO from Team";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);


		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
			
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INVOICE_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectPriceMatrices("Price Matrix Zayats");		
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);			
		pricematrix.selectPriceMatrix("VP2 zayats");
		pricematrix.switchOffOption("PDR");
		pricematrix.selectDiscaunt("Dye");
		pricematrix.selectDiscaunt("Wheel");
		pricematrix.selectDiscaunt("Test service zayats");
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.searchServiceByName(iOSInternalProjectConstants.SR_S4_Bl_I1_M);
		servicesscreen.selectSubService(iOSInternalProjectConstants.SR_S4_Bl_I1_M);
		servicesscreen.searchServiceByName(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword("Zayats", "1111");
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType(iOSInternalProjectConstants.CUSTOMER_APPROVALON_INVOICETYPE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsDraft();
		homescreen = myworkordersscreen.clickHomeButton();
		
		RegularTeamInvoicesScreen teaminvoicesscreen = homescreen.clickTeamInvoices();
		teaminvoicesscreen.selectInvoice(invoicenumber);
		teaminvoicesscreen.clickChangePOPopup();
		teaminvoicesscreen.changePO(newpo);
		teaminvoicesscreen.clickHomeButton();
		
		Helpers.waitABit(10000);
		
		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		BackOfficeHeaderPanel boheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		OperationsWebPage operationspage = boheader.clickOperationsLink();
		InvoicesWebPage invoiceswebpage = operationspage.clickInvoicesLink();
		invoiceswebpage.selectSearchStatus(WebConstants.InvoiceStatuses.INVOICESTATUS_ALL);
		invoiceswebpage.setSearchInvoiceNumber(invoicenumber);
		invoiceswebpage.clickFindButton();
	
		Assert.assertEquals(invoiceswebpage.getInvoicePONumber(invoicenumber), newpo);
		//Assert.assertEquals(invoiceswebpage.getInvoicePOPaidValue(invoicenumber), PricesCalculations.getPriceRepresentation(cashcheckamount));
		String mainWindowHandle = webdriver.getWindowHandle();
		InvoicePaymentsTabWebPage invoicepayments = invoiceswebpage.clickInvoicePayments(invoicenumber);
		
		Assert.assertEquals(invoicepayments.getPaymentDescriptionTypeAmountValue("PO #: " + newpo), PricesCalculations.getPriceRepresentation("0"));
		invoicepayments.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Test(testName="Test Case 40033:WO Monitor: Verify filter for Team WO that returns only work assigned to tech who is logged in", 
			description = "WO: Regular - Verify filter for Team WO that returns only work assigned to tech who is logged in")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testInvoicesVerifyFilterForTeamWOThatReturnsOnlyWorkAssignedToTechWhoIsLoggedIn(String backofficeurl, String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "WDZPE7CD9E5889222";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
			
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String wonum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		servicesscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicescreen.clickTechniciansIcon();
		selectedservicescreen.selecTechnician("Oksana Zayats");
		selectedservicescreen.unselecTechnician("Employee Simple 20%");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.WHEEL_SERVICE);
		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.WHEEL_SERVICE);
		selectedservicescreen.clickTechniciansIcon();
		selectedservicescreen.selecTechnician("Oksana Zayats");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.VPS1_SERVICE);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		homescreen = myworkordersscreen.clickHomeButton();
		
		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickOnWO(wonum);
		
		RegularOrderMonitorScreen ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Thread.sleep(3000);
		ordermonitorscreen.checkMyWorkCheckbox();
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DISC_EX_SERVICE1));
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.WHEEL_SERVICE));
		Assert.assertFalse(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DYE_SERVICE));
		homescreen = ordermonitorscreen.clickHomeButton();
		homescreen = teamworkordersscreen.clickHomeButton();		
		RegularMainScreen mainscreen = homescreen.clickLogoutButton();
		
		homescreen = mainscreen.userLogin("Zayats", "1111");
		teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickOnWO(wonum);		
		ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Thread.sleep(3000);
		ordermonitorscreen.checkMyWorkCheckbox();
		Assert.assertFalse(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DISC_EX_SERVICE1));
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.WHEEL_SERVICE));
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DYE_SERVICE));
		homescreen = ordermonitorscreen.clickHomeButton();
		homescreen = teamworkordersscreen.clickHomeButton();		
		mainscreen = homescreen.clickLogoutButton();
		

		homescreen = mainscreen.userLogin("Inspector", "12345");
		teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickOnWO(wonum);		
		ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Thread.sleep(3000);
		ordermonitorscreen.checkMyWorkCheckbox();
		Assert.assertFalse(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DISC_EX_SERVICE1));
		Assert.assertFalse(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.WHEEL_SERVICE));
		Assert.assertFalse(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DYE_SERVICE));
		homescreen = ordermonitorscreen.clickHomeButton();
		homescreen = teamworkordersscreen.clickHomeButton();		
		mainscreen = homescreen.clickLogoutButton();
		
		homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		
		webdriverInicialize();
		webdriverGotoWebPage(backofficeurl);

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		BackOfficeHeaderPanel boheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		MonitorWebPage monitorpage = boheader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.makeSearchPanelVisible();
		repairorderspage.setSearchWoNumber(wonum);
		repairorderspage.clickFindButton();
		
		VendorOrderServicesWebPage vendororderservicespage = repairorderspage.clickOnWorkOrderLinkInTable(wonum);
		vendororderservicespage.changeRepairOrderServiceVendor(iOSInternalProjectConstants.DYE_SERVICE, "Device Team");
		vendororderservicespage.waitABit(1000);
		Assert.assertEquals(vendororderservicespage.getRepairOrderServiceTechnician(iOSInternalProjectConstants.DYE_SERVICE), "Oksi User");
		getWebDriver().quit();
		
		
		teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickOnWO(wonum);		
		ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Thread.sleep(3000);
		//ordermonitorscreen.checkMyWorkCheckbox();
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DISC_EX_SERVICE1));
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.WHEEL_SERVICE));
		Assert.assertFalse(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DYE_SERVICE));
		homescreen = ordermonitorscreen.clickHomeButton();
		homescreen = teamworkordersscreen.clickHomeButton();
		mainscreen = homescreen.clickLogoutButton();
		
		homescreen = mainscreen.userLogin("Zayats", "1111");
		teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.clickOnWO(wonum);		
		ordermonitorscreen = teamworkordersscreen.selectWOMonitor();
		Thread.sleep(3000);
		//ordermonitorscreen.checkMyWorkCheckbox();
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DISC_EX_SERVICE1));
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.WHEEL_SERVICE));
		Assert.assertTrue(ordermonitorscreen.isServicePresent(iOSInternalProjectConstants.DYE_SERVICE));
		homescreen = ordermonitorscreen.clickHomeButton();
		homescreen = teamworkordersscreen.clickHomeButton();		
		mainscreen = homescreen.clickLogoutButton();
		homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
	}
	
	@Test(testName="Test Case 45251:SR: Regular - Verify multiple inspections and multiple work orders to be tied to a Service Request", 
			description = "SR: Regular - Verify multiple inspections and multiple work orders to be tied to a Service Request")
	public void testSRVerifyMultipleInspectionsAndMultipleWorkOrdersToBeTiedToAServiceRequest()
			throws Exception {
		
		final String VIN  = "WDZPE7CD9E5889222";
		final String insptype1 = iOSInternalProjectConstants.INS_LINE_APPROVE_OFF;
		final String insptype2 = iOSInternalProjectConstants.INSP_FOR_CALC;
		List<String> inspnumbers = new ArrayList<String>();
		List<String> wonumbers = new ArrayList<String>();
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
		
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		servicerequestsscreen.clickAddButton();
				
		servicerequestsscreen.selectServiceRequestType (iOSInternalProjectConstants.SR_ALL_PHASES);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
				
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		vehiclescreeen.clickSaveButton();
		Helpers.getAlertTextAndCancel();
		
		final String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		servicerequestsscreen.selectInspectionType(insptype1);
		inspnumbers.add(vehiclescreeen.getInspectionNumber());	
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveAsDraft();
		Thread.sleep(3000);
		
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		servicerequestsscreen.selectInspectionType(insptype2);
		inspnumbers.add(vehiclescreeen.getInspectionNumber());	
		vehiclescreeen.selectNextScreen("Zayats Section1");
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.clickSaveButton();
		
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		servicerequestsscreen.clickServiceRequestSummaryInspectionsButton();
		Thread.sleep(3000);
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		for (String inspectnumber : inspnumbers)
			myinspectionsscreen.assertInspectionExists(inspectnumber);
		
		myinspectionsscreen.clickHomeButton();
		Thread.sleep(1000);
		servicerequestsscreen.clickHomeButton();

		
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateWorkOrderRequestAction();
		Thread.sleep(5000);
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.WO_DELAY_START);
		Thread.sleep(2000);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		wonumbers.add(vehiclescreeen.getInspectionNumber());
		vehiclescreeen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		Helpers.waitABit(3000);
		
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateWorkOrderRequestAction();
		Thread.sleep(5000);
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.WO_MONITOR_DEVICE);
		Thread.sleep(2000);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		wonumbers.add(vehiclescreeen.getInspectionNumber());
		vehiclescreeen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		Helpers.waitABit(3000);
		
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		servicerequestsscreen.clickServiceRequestSummaryOrdersButton();
		Thread.sleep(3000);
		RegularMyWorkOrdersScreen myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		for (String wonumber : wonumbers)
			Assert.assertTrue(myworkordersscreen.woExists(wonumber));
		
		myworkordersscreen.clickHomeButton();
		Thread.sleep(1000);
		servicerequestsscreen.clickHomeButton();
		servicerequestsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 45128:Inspections: Regular - Verify that service level notes are copied from Inspection to WO when it is auto created after approval", 
			description = "Verify that service level notes are copied from Inspection to WO when it is auto created after approval")
	public void testInspectionsVerifyThatServiceLevelNotesAreCopiedFromInspectionToWOWhenItIsAutoCreatedAfterApproval() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String _price1  = "10";
		final String _price2  = "100";
		final int timetowaitwo = 4;
		final String _pricematrix1 = "Left Front Door";
		final String _pricematrix2 = "Grill";
		final String inspectionnotes = "Inspection notes";
		final String servicenotes = "Service Notes";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_FOR_AUTO_WO_LINE_APPR_MULTISELECT);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");
		servicedetailsscreen.setServicePriceValue(_price1);
		servicedetailsscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		servicesscreen.selectSubService(iOSInternalProjectConstants.SALES_TAX);
		
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForEdit(inspnumber);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		RegularNotesScreen notesscreen = vehiclescreeen.clickNotesButton();
		notesscreen.setNotes(inspectionnotes);
		notesscreen.clickSaveButton();
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		
		servicedetailsscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");		
		notesscreen = servicedetailsscreen.clickNotesCell();
		notesscreen.setNotes(servicenotes);
		notesscreen.clickSaveButton();
		servicedetailsscreen.saveSelectedServiceDetails();
		Assert.assertTrue(servicesscreen.isNotesIconPresentForSelectedService("3/4\" - Penny Size"));
		
		servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SALES_TAX);		
		notesscreen = servicedetailsscreen.clickNotesCell();
		notesscreen.setNotes(servicenotes);
		notesscreen.clickSaveButton();		
		servicedetailsscreen.saveSelectedServiceDetails();
		Assert.assertTrue(servicesscreen.isNotesIconPresentForSelectedService(iOSInternalProjectConstants.SALES_TAX));
		
		servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);		
		notesscreen = servicedetailsscreen.clickNotesCell();
		notesscreen.setNotes(servicenotes);
		notesscreen.clickSaveButton();
		servicedetailsscreen.saveSelectedServiceDetails();
		Assert.assertTrue(servicesscreen.isNotesIconPresentForSelectedService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE));
		
		servicesscreen.selectNextScreen("Default");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix1);
		pricematrix.setSizeAndSeverity("DIME", "VERY LIGHT");
		pricematrix.setPrice(_price2);
		pricematrix.clickSaveButton();
		
		servicesscreen.selectNextScreen("Matrix Labor");
		pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix2);
		pricematrix.switchOffOption("PDR");
		pricematrix.setTime("12");
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);		
		pricematrix.clickSaveButton();
		pricematrix.clickSaveButton();
		
		Assert.assertTrue(myinspectionsscreen.isNotesIconPresentForInspection(inspnumber));
		myinspectionsscreen.selectInspectionForAction(inspnumber);
		//SelectEmployeePopup selectemployeepopup = new SelectEmployeePopup(appiumdriver);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspection(inspnumber);
		approveinspscreen.clickApproveAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.clickSingnAndDrawApprovalSignature ();
		approveinspscreen.clickDoneButton();
		
		homescreen = myinspectionsscreen.clickHomeButton();
		
		RegularTeamWorkOrdersScreen teamwoscreen = homescreen.clickTeamWorkordersButton();
		homescreen = teamwoscreen.clickHomeButton();
		
		for (int i = 0; i < timetowaitwo; i++) {
			Helpers.waitABit(60*1000);
			teamwoscreen = homescreen.clickTeamWorkordersButton();
			homescreen = teamwoscreen.clickHomeButton();
		}
		teamwoscreen = homescreen.clickTeamWorkordersButton();
		teamwoscreen.clickSearchButton();
		teamwoscreen.setSearchType(iOSInternalProjectConstants.WO_TYPE_FOR_MONITOR);
		teamwoscreen.selectSearchLocation("All locations");
		teamwoscreen.clickSearchSaveButton();
		
		final String wonumber = teamwoscreen.getFirstWorkOrderNumberValue();
		teamwoscreen.selectWorkOrderForEidt(wonumber);
		
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		Assert.assertEquals(vehiclescreeen.getEst(), inspnumber);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		notesscreen = vehiclescreeen.clickNotesButton();
		Assert.assertTrue(notesscreen.getNotesValue().length() > 0);
		notesscreen.clickSaveButton();
		
		servicesscreen = new RegularServicesScreen(appiumdriver);
		/*servicedetailsscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");		
		notesscreen = servicedetailsscreen.clickNotesCell();
		Assert.assertEquals(notesscreen.getNotesValue(), servicenotes);
		notesscreen.clickSaveButton();
		servicedetailsscreen.saveSelectedServiceDetails();*/
		Assert.assertTrue(servicesscreen.isNotesIconPresentForSelectedWorkOrderService("3/4\" - Penny Size"));
		
		/*servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SALES_TAX);		
		notesscreen = servicedetailsscreen.clickNotesCell();
		Assert.assertEquals(notesscreen.getNotesValue(), servicenotes);
		notesscreen.clickSaveButton();		
		servicedetailsscreen.saveSelectedServiceDetails();*/
		Assert.assertTrue(servicesscreen.isNotesIconPresentForSelectedWorkOrderService(iOSInternalProjectConstants.SALES_TAX));
		
		/*servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);		
		notesscreen = servicedetailsscreen.clickNotesCell();
		Assert.assertEquals(notesscreen.getNotesValue(), servicenotes);
		notesscreen.clickSaveButton();
		servicedetailsscreen.saveSelectedServiceDetails();*/
		Assert.assertTrue(servicesscreen.isNotesIconPresentForSelectedWorkOrderService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE));
		servicesscreen.cancelOrder();
		teamwoscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 32989:Inspections: Regular - Verify that question section is shown per service with must panels when questions are required", 
			description = "Verify that question section is shown per service with must panels when questions are required")
	public void testInspectionsVerifyThatQuestionSectionIsShownPerServiceWithMustPanelsWhenQuestionsAreRequired() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);		
		vehiclescreeen.setVIN(VIN);
		
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		servicedetailsscreen.answerQuestion2("A3");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.selectVehiclePart("Back Glass");
		servicedetailsscreen.selectVehiclePart("Deck Lid");
		servicedetailsscreen.selectVehiclePart("Driver Seat");
		servicedetailsscreen.selectVehiclePart("Hood");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.openServiceDetailsByIndex(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE, 0);
		Assert.assertTrue(servicedetailsscreen.isQuestionFormCellExists());
		Assert.assertEquals(servicedetailsscreen.getQuestion2Value(), "A3");
		servicedetailsscreen.saveSelectedServiceDetails();
		
		for (int i = 1; i < 4; i++) {
			servicesscreen.openServiceDetailsByIndex(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE, i);
			Assert.assertFalse(servicedetailsscreen.isQuestionFormCellExists());
			servicedetailsscreen.saveSelectedServiceDetails();
		}
		servicesscreen.clickSaveButton();
		myinspectionsscreen.clickHomeButton();		
	}
	
	@Test(testName = "Test Case 30509:Invoices: Regular - Verify that message 'Invoice PO# shouldn't be empty' is shown for Team Invoices", 
			description = "Verify that message 'Invoice PO# shouldn't be empty' is shown for Team Invoices")
	public void testInvoicesVerifyThatMessageInvoicPONumberShouldntBeEmptyIsShownForTeamInvoices() throws Exception {
		
		final String emptypo = "";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularTeamInvoicesScreen teaminvoicesscreen = homescreen.clickTeamInvoices();
		final String invoicenum = teaminvoicesscreen.getFirstInvoiceValue();
		teaminvoicesscreen.selectInvoice(invoicenum);
		teaminvoicesscreen.clickChangePOPopup();
		teaminvoicesscreen.changePO(emptypo);
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("Invoice PO# shouldn't be empty"));
		teaminvoicesscreen.clickCancel();
		teaminvoicesscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 30510:Invoices: Regular - Verify that message 'Invoice PO# shouldn't be empty' is shown for MY Invoices", 
			description = "Verify that message 'Invoice PO# shouldn't be empty' is shown for MY Invoices")
	public void testInvoicesVerifyThatMessageInvoicPONumberShouldntBeEmptyIsShownForMyInvoices() throws Exception {
		
		final String emptypo = "";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		final String invoicenum = myinvoicesscreen.getFirstInvoiceValue();
		myinvoicesscreen.selectInvoice(invoicenum);
		myinvoicesscreen.clickChangePOPopup();
		myinvoicesscreen.changePO(emptypo);
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("Invoice PO# shouldn't be empty"));
		myinvoicesscreen.clickCancel();
		myinvoicesscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 33115:WO: Regular - Verify that Tech splits is saved in price matrices", 
			description = "Verify that Tech splits is saved in price matrices")
	public void testWOVerifyThatTechSplitsIsSavedInPriceMatrices() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String pricevalue  = "100";
		final String totalsale = "5";
		final String defaulttech  = "Employee Simple 20%";
		final String techname  = "Oksana Zayats";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectPriceMatrices("Price Matrix Zayats");	
		servicesscreen.selectPriceMatrices("VP2 zayats");	
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.setSizeAndSeverity("CENT", "MEDIUM");
		Assert.assertEquals(pricematrix.getTechniciansValue(), defaulttech);
		pricematrix.setPrice(pricevalue);
		pricematrix.clickOnTechnicians();
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.selecTechnician(techname);
		selectedservicescreen.saveSelectedServiceDetails();
		Assert.assertEquals(pricematrix.getTechniciansValue(), defaulttech + ", " + techname);
		pricematrix.clickSaveButton();		
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale(totalsale);
		ordersummaryscreen.clickSaveButton();
		
		RegularTechRevenueScreen techrevenuescreen = myworkordersscreen.selectWorkOrderTechRevenueMenuItem(wonumber);
		Assert.assertTrue(techrevenuescreen.isTechIsPresentInReport(techname));
		Assert.assertTrue(techrevenuescreen.isTechIsPresentInReport(defaulttech));

		techrevenuescreen.clickHomeButton();
		myworkordersscreen.clickHomeButton();	
	}
	
	@Test(testName = "Test Case 34551:WO: Regular - Verify that it is not possible to change default tech via service type split", 
			description = "Verify that it is not possible to change default tech via service type split")
	public void testWOVerifyThatItIsNotPossibleToChangeDefaultTechViaServiceTypeSplit() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String defaulttech  = "Employee Simple 20%";
		final String techname  = "Oksana Zayats";
		final String totalsale = "5";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.SERVICE_WITH_DEFAUT_TECH);
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Back Glass");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.clickTechniciansIcon();
		Assert.assertTrue(selectedservicescreen.isTechnicianIsSelected(defaulttech));
		selectedservicescreen.selecTechnician(techname);
		Assert.assertTrue(selectedservicescreen.isTechnicianIsSelected(defaulttech));
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickTechnicianToolbarIcon();
		servicesscreen.changeTechnician("Dent", techname);
		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SERVICE_WITH_DEFAUT_TECH);
		selectedservicescreen.clickTechniciansIcon();
		Assert.assertTrue(selectedservicescreen.isTechnicianIsSelected(defaulttech));
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale(totalsale);
		ordersummaryscreen.clickSaveButton();
		Assert.assertTrue(myworkordersscreen.woExists(wonumber));
		
		myworkordersscreen.clickHomeButton();	
	}
	
	@Test(testName = "Test Case 45097:WO: Regular - Verify that when use Copy Services action for WO all service instances should be copied", 
			description = "Verify that when use Copy Services action for WO all service instances should be copied")
	public void testWOVerifyThatWhenUseCopyServicesActionForWOAllServiceInstancesShouldBeCopied() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String totalsale = "5";
		final String[] servicestoadd = { iOSInternalProjectConstants.OKSI_SERVICE_PP_PANEL, iOSInternalProjectConstants.TEST_SERVICE_WITH_QF_PP_VEHICLE};
		final String[] vehicleparts = { "Dashboard", "Deck Lid"};
		
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		for (String serviceadd : servicestoadd) {
			//servicesscreen.searchAvailableService(serviceadd);
			servicesscreen.clickToolButton();
			RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(serviceadd);
			selectedservicescreen.clickVehiclePartsCell();
			for (String vehiclepart : vehicleparts) {
				selectedservicescreen.selectVehiclePart(vehiclepart);
			}
			selectedservicescreen.saveSelectedServiceDetails();
			selectedservicescreen.saveSelectedServiceDetails();
			servicesscreen.clickAddServicesButton();
		}

		for (String serviceadd : servicestoadd) {
			servicesscreen.assertServiceIsSelected(serviceadd);
		}
		
		servicesscreen.assertSubTotalAmauntIsCorrect("$44.00");
		servicesscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale(totalsale);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen.selectWorkOrderForCopyServices(wonumber);
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		for (String serviceadd : servicestoadd) {
			Assert.assertEquals(servicesscreen.getNumberOfServiceSelectedItems(serviceadd), servicestoadd.length);
		}
		servicesscreen.assertSubTotalAmauntIsCorrect("$44.00");
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();	
	}
	
	@Test(testName="Test Case 50250:WO: Regular - Verify that WO number is not duplicated", 
			description = "WO: - Verify that WO number is not duplicated")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testWOVerifyThatWONumberIsNotDuplicated(String backofficeurl, String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String _po  = "12345";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O04TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INVOICE_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		
		final String wonumber1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen.approveWorkOrder(wonumber1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen.clickCreateInvoiceIconForWO(wonumber1);
		myworkordersscreen.clickInvoiceIcon();
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.INVOICE_DEFAULT_TEMPLATE);
		invoiceinfoscreen.setPO(_po);
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		invoiceinfoscreen.clickSaveAsFinal();
		Helpers.waitABit(10000);
		
		webdriverInicialize();
		webdriverGotoWebPage("https://reconpro-devqa.cyberianconcepts.com/Company/Invoices.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InvoicesWebPage invoiceswebpage = PageFactory.initElements(
				webdriver, InvoicesWebPage.class);

		invoiceswebpage.setSearchInvoiceNumber(invoicenumber);
		invoiceswebpage.clickFindButton();
		
		invoiceswebpage.archiveInvoiceByNumber(invoicenumber);
		Assert.assertFalse(invoiceswebpage.isInvoiceNumberExists(invoicenumber));
		webdriver.quit();
		
		//Create second WO
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INVOICE_PRINT);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		
		final String wonumber2 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen.approveWorkOrder(wonumber2, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);	
		
		myworkordersscreen.clickCreateInvoiceIconForWO(wonumber2);
		myworkordersscreen.clickInvoiceIcon();
		invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.INVOICE_DEFAULT_TEMPLATE);
		invoiceinfoscreen.setPO(_po);
		invoiceinfoscreen.selectNextScreen("Zayats Section1");
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		invoiceinfoscreen.clickSaveAsFinal();
		homescreen = myworkordersscreen.clickHomeButton();
		
		homescreen.clickStatusButton();
		homescreen.updateDatabase();
		RegularMainScreen mainscreen = new RegularMainScreen(appiumdriver);
		homescreen = mainscreen.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
		
		//Create third WO
		myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INVOICE_PRINT);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		
		final String wonumber3 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		
		ordersummaryscreen.setTotalSale("5");
		ordersummaryscreen.clickSaveButton();
		homescreen = myworkordersscreen.clickHomeButton();
		Helpers.waitABit(10000);
		
		webdriverInicialize();
		webdriverGotoWebPage("https://reconpro-devqa.cyberianconcepts.com/Company/Orders.aspx");

		loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		WorkOrdersWebPage workorderspage = PageFactory.initElements(
				webdriver, WorkOrdersWebPage.class);

		workorderspage.makeSearchPanelVisible();
		workorderspage.setSearchOrderNumber(wonumber3);
		workorderspage.clickFindButton();

		Assert.assertEquals(workorderspage.getWorkOrdersTableRowCount(), 1);
		webdriver.quit();
	}
	
	@Test(testName="Test Case 39573:WO: Regular - Verify that in case valid VIN is decoded, replace existing make and model with new one", 
			description = "WO: - Verify that in case valid VIN is decoded, replace existing make and model with new one")
	public void testWOVerifyThatInCaseValidVINIsDecodedReplaceExistingMakeAndModelWithNewOne()
			throws Exception {
		
		final String[] VINs  = { "2A8GP54L87R279721", "1FMDU32X0PUB50142", "GFFGG"} ;
		final String makes[]  = { "Chrysler", "Ford", null } ;
		final String models[]  = { "Town and Country", "Explorer",  null };

		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O04TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_INVOICE_PRINT);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		for (int i = 0; i < VINs.length; i++) {
			vehiclescreeen.setVIN(VINs[i]);
			Assert.assertEquals(vehiclescreeen.getMake(), makes[i]);
			Assert.assertEquals(vehiclescreeen.getModel(), models[i]);			
			vehiclescreeen.clearVINCode();
		}

		vehiclescreeen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 34626:WO: Regular - Verify that when service do not have questions and select several panels do not underline anyone", 
			description = "WO: - Verify that when service do not have questions and select several panels do not underline anyone")
	public void testWOVerifyThatWhenServiceDoNotHaveQuestionsAndSelectSeveralPanelsDoNotUnderlineAnyone()
			throws Exception {
		
		final String VIN  = "2A8GP54L87R279721";
		final String[] vehicleparts  = { "Center Rear Passenger Seat", "Dashboard" };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_WITHOUT_QUESTIONS_PP_PANEL);
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.clickVehiclePartsCell();
		for (String vehiclepart : vehicleparts) {
			selectedservicescreen.selectVehiclePart(vehiclepart);
		}
		selectedservicescreen.saveSelectedServiceDetails();
		for (String vehiclepart : vehicleparts) {
			Assert.assertTrue(selectedservicescreen.getVehiclePartValue().contains(vehiclepart));
		}
		selectedservicescreen.saveSelectedServiceDetails();
		Assert.assertEquals(servicesscreen.getNumberOfServiceSelectedItems(iOSInternalProjectConstants.TEST_SERVICE_WITHOUT_QUESTIONS_PP_PANEL), vehicleparts.length);
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}

	@Test(testName = "Test Case 31964:WO: Regular - Verify that keyboard is not shown over the VIN when it is entered in case only VIN is present on Vehicle screen", 
				description = "Verify that keyboard is not shown over the VIN when it is entered in case only VIN is present on Vehicle screen")
	public void testWOVerifyThatKeyboardIsNotShownOverTheVINWhenItIsEnteredInCaseOnlyVINIsPresentOnVehicleScreen() throws Exception {
			
		final String VIN = "2A8GP54L87R279721";

			
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.clickHomeButton();
			
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		RegularVehicleScreen vehiclescreeen = myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_VIN_ONLY);
			
		vehiclescreeen.setVIN(VIN);
		Assert.assertTrue(vehiclescreeen.getVINField().isDisplayed());
		vehiclescreeen.clickVINField();
		Assert.assertTrue(vehiclescreeen.getVINField().isDisplayed());
		vehiclescreeen.cancelOrder();
		homescreen = myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 53824:WO: Regular - Verify that message is shown for Money and Labor service when price is changed to 0$ under WO", 
			description = "Verify that message is shown for Money and Labor service when price is changed to 0$ under WO")
	public void testWOVerifyThatMessageIsShownForMoneyAndLaborServiceWhenPriceIsChangedTo0UnderWO() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String totalsale = "5";
		final String servicezeroprice = "0";
		final String servicecalclaborprice = "12";
		
		
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_SMOKE_MONITOR);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale(totalsale);
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen.selectWorkOrderForEidt(wonumber);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectService(iOSInternalProjectConstants.AMONEYVEHICLEFF_WASHING);
		
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.AMONEYVEHICLEFF_WASHING);
		selectedservicescreen.setServicePriceValue(servicezeroprice);
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Order's technician split will be assigned to this order service if you set zero amount."));
		
		selectedservicescreen.clickTechniciansIcon();
		alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Set non-zero amount for service to assign multiple technicians."));
		selectedservicescreen.selecTechnician("Manager 1");
		selectedservicescreen.selecTechnician("Oksana Zayats");
		Assert.assertFalse(selectedservicescreen.isTechnicianIsSelected("Manager 1"));
		Assert.assertTrue(selectedservicescreen.isTechnicianIsSelected("Oksana Zayats"));
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.CALC_LABOR);
		selectedservicescreen.clickTechniciansIcon();
		alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Set non-zero amount for service to assign multiple technicians."));
		
		
		selectedservicescreen.cancelSelectedServiceDetails();
		selectedservicescreen.setServiceRateValue(servicecalclaborprice);
		selectedservicescreen.clickTechniciansIcon();
		selectedservicescreen.selecTechnician("Manager 1");
		selectedservicescreen.selecTechnician("Oksana Zayats");
		Assert.assertTrue(selectedservicescreen.isTechnicianIsSelected("Manager 1"));
		Assert.assertTrue(selectedservicescreen.isTechnicianIsSelected("Oksana Zayats"));
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.cancelOrder();
		homescreen =  myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 45252:WO: Regular - Verify that validation is present for vehicle trim field", 
			description = "Verify that validation is present for vehicle trim field")
	public void testWOVerifyThatValidationIsPresentForVehicleTrimField() throws Exception {
		
		final String VIN  = "TESTVINN";
		final String _make = "Acura";
		final String _model = "CL";
		final String trimvalue = "2.2 Premium";

		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_VEHICLE_TRIM_VALIDATION);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Trim is required"));
		vehiclescreeen.setTrim(trimvalue);
		Assert.assertEquals(vehiclescreeen.getTrim(), trimvalue);
		
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveButton();
		homescreen =  myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 35375:WO: Regular - Verify that Total sale is not shown when checkmark 'Total sale required' is not set to OFF", 
			description = "Verify that Total sale is not shown when checkmark 'Total sale required' is not set to OFF")
	public void testWOVerifyThatTotalSaleIsNotShownWhenCheckmarkTotalSaleRequiredIsNotSetToOFF() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TOTAL_SALE_NOT_REQUIRED);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectService(iOSInternalProjectConstants.TAX_DISCOUNT);
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		Assert.assertFalse(ordersummaryscreen.isTotalSaleFieldPresent());
		ordersummaryscreen.clickSaveButton();
		homescreen = myworkordersscreen.clickHomeButton();
		
		RegularTeamWorkOrdersScreen teamworkordersscreen = homescreen.clickTeamWorkordersButton();
		teamworkordersscreen.selectWorkOrderForEidt(wonumber);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		Assert.assertFalse(ordersummaryscreen.isTotalSaleFieldPresent());
		ordersummaryscreen.clickSaveButton();
		homescreen = teamworkordersscreen.clickHomeButton();
	}

	@Test(testName = "Test Case 40821:WO: Regular - Verify that it is possible to assign tech to order by action Technicians", 
			description = "Verify that it is possible to assign tech to order by action Technicians")
	public void testWOVerifyThatItIsPossibleToAssignTechToOrderByActionTechnicians() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String pricevalue  = "21";
		final String totalsale = "5";
		final String defaulttech  = "Employee Simple 20%";
		final String techname  = "Oksana Zayats";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String wonumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("3/4\" - Penny Size");
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		RegularSelectedServiceBundleScreen selectedservicebundlescreen = new RegularSelectedServiceBundleScreen(appiumdriver);
		selectedservicebundlescreen.selectBundle(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicebundlescreen.openBundleInfo(iOSInternalProjectConstants.WHEEL_SERVICE);
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.setServiceQuantityValue("2.00");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.SR_S1_MONEY_FLATFEE);
		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Front Bumper");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Hood");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();

		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectPriceMatrices("Price Matrix Zayats");	
		servicesscreen.selectPriceMatrices("VP2 zayats");	
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.setSizeAndSeverity("CENT", "LIGHT");
		Assert.assertEquals(pricematrix.getTechniciansValue(), defaulttech);
		pricematrix.setPrice(pricevalue);
		pricematrix.selectDiscaunt("Test service zayats");
		pricematrix.clickSaveButton();		
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.TAX_DISCOUNT);
		servicesscreen.selectSubService(iOSInternalProjectConstants.SALES_TAX);
		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SERVICE_WITH_DEFAUT_TECH);
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Back Glass");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale(totalsale);
		ordersummaryscreen.clickSaveButton();
		
		selectedservicescreen = myworkordersscreen.selectWorkOrderTechniciansMenuItem(wonumber);
		//selectedservicescreen.selecTechnician(defaulttech);
		selectedservicescreen.selecTechnician(techname);
		selectedservicescreen.saveSelectedServiceDetails();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Changing default employees for a work order will change split data for all services."));
		
		myworkordersscreen.selectWorkOrderForEidt(wonumber);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		Assert.assertEquals(vehiclescreeen.getTechnician(), techname + ", " + defaulttech);
		vehiclescreeen.cancelOrder();
		myworkordersscreen.clickHomeButton();	
	}
}