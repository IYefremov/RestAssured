package com.cyberiansoft.test.ios10_client.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyberiansoft.test.bo.pageobjects.webpages.ActiveDevicesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeLoginWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.InspectionsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.InvoicesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.WorkOrderInfoTabWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.WorkOrdersWebPage;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.LoginScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularApproveInspectionsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularCustomersScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularHomeScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularInspectionToolBar;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularInvoiceInfoScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMainScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMyInspectionsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMyInvoicesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMyWorkOrdersScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularOrderSummaryScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularPriceMatrixScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularQuestionsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularSelectedServiceDetailsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularServiceRequestsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularServicesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularVehicleScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularVisualInteriorScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import com.cyberiansoft.test.ios_client.pageobjects.iosregulardevicescreens.RegularSelectedServiceBundleScreen;
import com.cyberiansoft.test.ios_client.utils.AlertsCaptions;
import com.cyberiansoft.test.ios_client.utils.ExcelUtils;
import com.cyberiansoft.test.ios_client.utils.PricesCalculations;
import com.cyberiansoft.test.ios_client.utils.UtilConstants;
import com.cyberiansoft.test.ios_client.utils.iOSInternalProjectConstants;

import io.appium.java_client.MobileBy;

public class iOSRegularCalculationsTestCases extends BaseTestCase {
	
	private String regCode;
	private RegularHomeScreen homescreen;
	
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
		appiumdriverInicialize();	
		appiumdriver.removeApp(bundleid);
		//appiumdriverInicialize();
		appiumdriver.installApp(app.getAbsolutePath());
		appiumdriver.launchApp();
		LoginScreen loginscreen = new LoginScreen(appiumdriver);
		loginscreen.assertRegisterButtonIsValidCaption();
		loginscreen.registeriOSDevice(regCode);
		RegularMainScreen mainscr = new RegularMainScreen(appiumdriver);
		homescreen = mainscr.userLogin(iOSInternalProjectConstants.USERSIMPLE_LOGIN, iOSInternalProjectConstants.USER_PASSWORD);
	}
	
	//Test Case 8553:Create inspection on the device with approval required
	@Test(testName = "Test Case 8553:Create inspection on the device with approval required", description = "Create Inspection On The Device With Approval Required")
	public void testCreateInspectionOnTheDeviceWithApprovalRequired()
				throws Exception {
		final String VIN = "TESTVINNO";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Black";
			
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToRetailMode();
		customersscreen.clickHomeButton();

		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(iOSInternalProjectConstants.JOHN_RETAIL_CUSTOMER);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_NOTLA_TS_INSPTYPE);
		myinspectionsscreen.selectNextScreen(UtilConstants.CLAIM_SCREEN_CAPTION);
		myinspectionsscreen.selectNextScreen(RegularVisualInteriorScreen
					.getVisualInteriorCaption());
		myinspectionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveButton();
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("VIN# is required"));
			
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.clickSaveButton();
		alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("Make is required"));
			
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		vehiclescreeen.setTech(iOSInternalProjectConstants.EMPLOYEE_TECHNICIAN);
		String inpectionnum = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.clickSaveButton();
			
		Thread.sleep(1000);
		
		myinspectionsscreen.selectInspectionForApprove(inpectionnum);
		//testlogger.log(LogStatus.INFO, "After approve", testlogger.addScreenCapture(createScreenshot(appiumdriver, iOSLogger.loggerdir)));
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
		Helpers.waitABit(1000);
		myinspectionsscreen.assertInspectionIsApproved(inpectionnum);
			
		myinspectionsscreen.clickHomeButton();
	}

	String inspection8434 = "";
	//Test Case 8435:Create Retail Inspection (HD Single page)
	//Test Case 8434:Add Services to visual inspection
	@Test(testName = "Test Case 8434:Add Services to visual inspection", description = "Add Services To Visual Inspection")
	public void testAddServicesToVisualInspection() throws Exception {
		final String VIN = "ZWERTYASDFEWSDRZG";
		final String _make = "Acura";
		final String _model = "1.6 EL";
		final String _color = "Red";
		final String _inspectionprice = "275";
			
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
		inspection8434 = vehiclescreeen.getInspectionNumber();
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
		
		vehiclescreeen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForEdit(inspection8434);
		myinspectionsscreen.selectNextScreen(RegularVisualInteriorScreen
					.getVisualInteriorCaption());
		RegularVisualInteriorScreen visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.assertDefaultInteriorServicesPresent();
		visualinteriorscreen.switchToCustomTab();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		visualinteriorscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);
		RegularVisualInteriorScreen.tapInterior();
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
		Thread.sleep(1000);
		Assert.assertEquals(myinspectionsscreen.getInspectionPriceValue(inspection8434), PricesCalculations.getPriceRepresentation(_inspectionprice));
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 8433:Change Quantity of services in Visual Inspection", description = "Change Quantity Of Services In Visual Inspection")
	public void testChangeQuantityOfServicesInVisualInspection()
			throws Exception {
		final String _quantity = "3.00";
		final String _quantityexterior = "2.00";
		final String _inspectionpricevisual = "275";
		final String _inspectionprice = "325";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.selectInspectionForEdit(inspection8434);
		myinspectionsscreen.selectNextScreen(RegularVisualInteriorScreen
				.getVisualInteriorCaption());
		RegularVisualInteriorScreen visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		/*visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.switchToCustomTab();
		visualinteriorscreen.selectService(iOSInternalProjectConstants.MISCELLANEOUS_SERVICE);
		visualinteriorscreen.selectSubService(iOSInternalProjectConstants.DYE_SERVICE);*/
		RegularVisualInteriorScreen.tapInterior();
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
	
	@Test(testName="Test Case 28580:WO: Regular - If fee bundle item price policy = 'Panel' then it will be added once for many associated service instances with same vehicle part.", 
			description = "WO: Regular - If fee bundle item price policy = 'Panel' then it will be added once for many associated service instances with same vehicle part.")
	public void testRegularIfFeeBundleItemPricePolicyPanelThenItWillBeAddedOnceForManyAssociatedServiceInstancesWithSameVehiclePart()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_TEST_FEE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails("Oksi_Service_PP_Panel");
		servicedetailsscreen.setServicePriceValue("12");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Grill");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.assertTotalAmauntIsCorrect("$33.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectService("Oksi_Service_PP_Panel");
		servicedetailsscreen.setServicePriceValue("13");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Grill");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$34.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectService("Oksi_Service_PP_Panel");
		servicedetailsscreen.setServicePriceValue("12");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Driver Seat");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$67.00");
		
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}

	@Test(testName="Test Case 28583:WO: Regular - If one fee bundle item is related to 2 or more fee bundle packages and assigned service is selected in WO then amount of the fee will be multiple to package quantity", 
			description = "WO: Regular - If one fee bundle item is related to 2 or more fee bundle packages and assigned service is selected in WO then amount of the fee will be multiple to package quantity")
	@Parameters({ "user.name", "user.psw" })
	public void testRegularIfOneFeeBundleItemIsRelatedTo2OrMoreFeeBundlePackagesAndAssignedServiceIsSelectedInWOThenAmountOfTheFeeWillBeMultipleToPackageQuantity(String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FOR_FEE_ITEM_IN_2_PACKS);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails("Service_in_2_fee_packs");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.assertTotalAmauntIsCorrect("$36.00");
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("1");
		ordersummaryscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
		Helpers.waitABit(10000);
		webdriverInicialize();
		
		webdriverGotoWebPage("https://reconpro-devqa.cyberianconcepts.com/Company/Orders.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		WorkOrdersWebPage wopage = PageFactory.initElements(webdriver,
				WorkOrdersWebPage.class);
		wopage.makeSearchPanelVisible();
		wopage.setSearchOrderNumber(wonumber);
		wopage.unselectInvoiceFromDeviceCheckbox();
		wopage.clickFindButton();
		String mainWindowHandle = webdriver.getWindowHandle();
		
		WorkOrderInfoTabWebPage woinfotab = wopage.clickWorkOrderInTable(wonumber);
		Assert.assertTrue(woinfotab.isServicePriceCorrectForWorkOrder("$36.00"));
		Assert.assertTrue(woinfotab.isServiceSelectedForWorkOrder("Service_in_2_fee_packs"));
		Assert.assertTrue(woinfotab.isServiceSelectedForWorkOrder("Oksi_Test1"));
		Assert.assertTrue(woinfotab.isServiceSelectedForWorkOrder("Oksi_Test2"));
		woinfotab.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Test(testName="Test Case 28585:WO: Regular - Verify that package price of fee bundle item is override the price of wholesale and retail prices", 
			description = "WO: Regular - Verify that package price of fee bundle item is override the price of wholesale and retail prices")
	public void testRegularVerifyThatPackagePriceOfFeeBundleItemIsOverrideThePriceOfWholesaleAndRetailPrices()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_FEE_PRICE_OVERRIDE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails("Service_for_override");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.assertTotalAmauntIsCorrect("$27.00");
		
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 28600:WO: Regular - If fee bundle item price policy = 'Vehicle' then it will be added once for many associated service instances", 
			description = "WO: Regular - If fee bundle item price policy = 'Vehicle' then it will be added once for many associated service instances")
	public void testRegularIfFeeBundleItemPricePolicyEqualsVehicleThenItWillBeAddedOnceForManyAssociatedServiceInstances()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_TEST_FEE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		servicedetailsscreen.setServicePriceValue("13");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Back Glass");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.answerQuestion2("A3");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.assertTotalAmauntIsCorrect("$34.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		servicedetailsscreen.setServicePriceValue("14");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Back Glass");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.answerQuestion2("A3");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$35.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		servicedetailsscreen.setServicePriceValue("14");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Left Headlight");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.answerQuestion2("A3");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$35.00");
		
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 28601:WO: Regular -  If fee bundle item price policy = 'Service' or 'Flat Fee' then it will be added to WO every time when associated service instance will add to WO.", 
			description = "WO: Regular -  If fee bundle item price policy = 'Service' or 'Flat Fee' then it will be added to WO every time when associated service instance will add to WO.")
	public void testRegularIfFeeBundleItemPricePolicyServiceOrFlatFeeThenItWillBeAddedToWOEveryTimeWhenAssociatedServiceInstanceWillAddToWO()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_TEST_FEE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails("Oksi_Service_PP_Service");
		servicedetailsscreen.setServicePriceValue("10");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Hood");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.assertTotalAmauntIsCorrect("$22.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectSubService("Oksi_Service_PP_Service");
		servicedetailsscreen.setServicePriceValue("12");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Hood");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$46.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectSubService("Oksi_Service_PP_Service");
		servicedetailsscreen.setServicePriceValue("10");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Cowl, Other");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$68.00");
		
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 28602:WO: Regular - Verify that for Wholesale and Retail customers fee is added depends on the price accordingly to price of the fee bundle item", 
			description = "WO: Regular - Verify that for Wholesale and Retail customers fee is added depends on the price accordingly to price of the fee bundle item")
	public void testRegularVerifyThatForWholesaleAndRetailCustomersFeeIsAddedDependsOnThePriceAccordinglyToPriceOfTheFeeBundleItem()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_TEST_FEE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.verifyMakeModelyearValues("Mitsubishi", "Montero Sport", "2000");
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen servicedetailsscreen = servicesscreen.openCustomServiceDetails("Oksi_Service_PP_Panel");
		servicedetailsscreen.setServicePriceValue("12");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Grill");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.assertTotalAmauntIsCorrect("$33.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectService("Oksi_Service_PP_Panel");
		servicedetailsscreen.setServicePriceValue("13");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Grill");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$34.00");
		
		servicesscreen.clickToolButton();
		servicesscreen.selectService("Oksi_Service_PP_Panel");
		servicedetailsscreen.setServicePriceValue("12");
		servicedetailsscreen.clickVehiclePartsCell();
		servicedetailsscreen.selectVehiclePart("Driver Seat");
		servicedetailsscreen.saveSelectedServiceDetails();
		servicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$67.00");
		
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 29398:WO: Regular - Verify that Fee Bundle services is calculated for additional matrix services", 
			description = "Verify that Fee Bundle services is calculated for additional matrix services")
	@Parameters({ "user.name", "user.psw" })
	public void testVerifyThatFeeBundleServicesIsCalculatedForAdditionalMatrixServices(String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String _pricematrix  = "Roof";
		final String price  = "54";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_TEST_FEE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("All Services");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService("SR_S5_Matrix_DE_TE");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix);
		pricematrix.switchOffOption("PDR");
		pricematrix.setPrice(price);
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.SR_S1_MONEY);
		pricematrix.selectDiscaunt("SR_S5_Mt_Money");
		pricematrix.selectDiscaunt("SR_S5_Mt_Upcharge_25");
		Assert.assertEquals(pricematrix.getPriceMatrixVehiclePartSubTotalPrice(), "$2,117.50");
		pricematrix.clickSaveButton();
		Assert.assertEquals(pricematrix.getInspectionSubTotalPrice(), "$2,117.50");
		pricematrix.clickServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$2,127.50");
		servicesscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
		Helpers.waitABit(30*1000);
		webdriverInicialize();
		
		webdriverGotoWebPage("https://reconpro-devqa.cyberianconcepts.com/Company/Orders.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		WorkOrdersWebPage wopage = PageFactory.initElements(webdriver,
				WorkOrdersWebPage.class);
		wopage.makeSearchPanelVisible();
		wopage.setSearchOrderNumber(wonumber);
		wopage.clickFindButton();
		String mainWindowHandle = webdriver.getWindowHandle();
		WorkOrderInfoTabWebPage woinfowebpage = wopage.clickWorkOrderInTable(wonumber);
		Assert.assertTrue(woinfowebpage.isServicePriceCorrectForWorkOrder("$2,127.50"));
		Assert.assertTrue(woinfowebpage.isServiceSelectedForWorkOrder("SR_S6_FeeBundle"));

		woinfowebpage.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Test(testName="Test Case 30672:WO: Regular - Verify that money value of some percentage service is rounds up after 0.095", 
			description = "Regular - Verify that money value of some percentage service is rounds up after 0.095")
	public void testRegularVerifyThatMoneyValueOfSomePercentageServiceIsRoundsUpAfter0_095()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String srs1moneyprice  = "40";
		final String srs1moneyflatfeeprice  = "8.25";
		final String discountvalue = "6";
		final String servicetotalprice = "$2.90";
		
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
		//servicesscreen.clickToolButton();
		servicesscreen.selectSubService(iOSInternalProjectConstants.SR_S1_MONEY);
		RegularSelectedServiceDetailsScreen regularselectedservicedetailsscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		regularselectedservicedetailsscreen.setServicePriceValue(srs1moneyprice);
		regularselectedservicedetailsscreen.clickVehiclePartsCell();
		regularselectedservicedetailsscreen.selectVehiclePart("Grill");
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_FLATFEE);
		regularselectedservicedetailsscreen.setServicePriceValue(srs1moneyflatfeeprice);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		regularselectedservicedetailsscreen.setServicePriceValue(discountvalue);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		Assert.assertEquals(regularselectedservicedetailsscreen.getServiceDetailsPriceValue(), servicetotalprice);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 30675:WO: Regular - Verify that money value of some percentage service is rounds down after 0.003", 
			description = "Regular - Verify that money value of some percentage service is rounds down after 0.003")
	public void testRegularVerifyThatMoneyValueOfSomePercentageServiceIsRoundsUpAfter0_003()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String srs1moneyprice  = "40";
		final String srs1moneyflatfeeprice  = "1.38";
		final String discountvalue = "6";
		final String servicetotalprice = "$2.48";
		
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
		//servicesscreen.clickToolButton();
		servicesscreen.selectSubService(iOSInternalProjectConstants.SR_S1_MONEY);
		RegularSelectedServiceDetailsScreen regularselectedservicedetailsscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		regularselectedservicedetailsscreen.setServicePriceValue(srs1moneyprice);
		regularselectedservicedetailsscreen.clickVehiclePartsCell();
		regularselectedservicedetailsscreen.selectVehiclePart("Grill");
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_FLATFEE);
		regularselectedservicedetailsscreen.setServicePriceValue(srs1moneyflatfeeprice);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		regularselectedservicedetailsscreen.setServicePriceValue(discountvalue);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		Assert.assertEquals(regularselectedservicedetailsscreen.getServiceDetailsPriceValue(), servicetotalprice);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 30676:WO: Regular - Verify that money value of some percentage service is rounds up after 0.005", 
			description = "Regular - Verify that money value of some percentage service is rounds up after 0.005")
	public void testRegularVerifyThatMoneyValueOfSomePercentageServiceIsRoundsUpAfter0_005()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String srs1moneyprice  = "20";
		final String srs1moneyflatfeeprice  = "8.09";
		final String discountvalue = "6";
		final String servicetotalprice = "$1.69";
		
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
		//servicesscreen.clickToolButton();
		servicesscreen.selectSubService(iOSInternalProjectConstants.SR_S1_MONEY);
		RegularSelectedServiceDetailsScreen regularselectedservicedetailsscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		regularselectedservicedetailsscreen.setServicePriceValue(srs1moneyprice);
		regularselectedservicedetailsscreen.clickVehiclePartsCell();
		regularselectedservicedetailsscreen.selectVehiclePart("Grill");
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_FLATFEE);
		regularselectedservicedetailsscreen.setServicePriceValue(srs1moneyflatfeeprice);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		regularselectedservicedetailsscreen.setServicePriceValue(discountvalue);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		
		regularselectedservicedetailsscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		Assert.assertEquals(regularselectedservicedetailsscreen.getServiceDetailsPriceValue(), servicetotalprice);
		regularselectedservicedetailsscreen.saveSelectedServiceDetails();
		servicesscreen.cancelOrder();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 31498:WO: Regular - Verify that amount is calculated and rounded correctly", 
			description = "Verify that amount is calculated and rounded correctly")
	@Parameters({ "user.name", "user.psw" })
	public void testVerifyThatAmountIsCalculatedAndRoundedCorrectly(String userName, String userPassword)
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		final String[] prices  = { "160", "105", "400", "195", "2400", "180", "160", "105", "300" };
		final String discount  = "-25";
		
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
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		for (String _price : prices) {
			servicesscreen.clickToolButton();
			RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");
			selectedservicedetailscreen.setServicePriceValue(_price);
			selectedservicedetailscreen.saveSelectedServiceDetails();
			servicesscreen.clickAddServicesButton();
		}
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		selectedservicedetailscreen.setServicePriceValue(discount);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService("Tax discount");
		servicesscreen.assertTotalAmauntIsCorrect("$3,153.94");
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		
		ordersummaryscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
		
		Helpers.waitABit(60*1500);
		webdriverInicialize();
		
		webdriverGotoWebPage("https://reconpro-devqa.cyberianconcepts.com/Company/Orders.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		WorkOrdersWebPage wopage = PageFactory.initElements(webdriver,
				WorkOrdersWebPage.class);
		wopage.makeSearchPanelVisible();
		wopage.setSearchOrderNumber(wonumber);
		wopage.clickFindButton();
		String mainWindowHandle = webdriver.getWindowHandle();
		WorkOrderInfoTabWebPage woinfowebpage = wopage.clickWorkOrderInTable(wonumber);
		Assert.assertTrue(woinfowebpage.isServicePriceCorrectForWorkOrder("$3,153.94"));

		woinfowebpage.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Test(testName = "Test Case 32226:Inspections: Regular - Verify that inspection is saved as declined when all services are skipped or declined", 
			description = "Verify that inspection is saved as declined when all services are skipped or declined")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testRegularVerifyThatInspectionIsSavedAsDeclinedWhenAllServicesAreSkippedOrDeclined(String backofficeurl, String userName, String userPassword) throws Exception {

		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		final String[] pricematrixes = { "Hood", "ROOF" };
		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType ("Insp_for_auto_WO_line_appr_simple");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		
		questionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Default");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		for(String pricemrx : pricematrixes) {
			pricematrix.selectPriceMatrix(pricemrx);
			pricematrix.setSizeAndSeverity("DIME", "VERY LIGHT");
			pricematrix.clickSaveButton();
		}
		pricematrix.clickSaveButton();
		myinspectionsscreen.selectInspectionForAction(inspectionnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		Helpers.waitABit(5000);
		approveinspscreen.clickSkipAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.clickCancelStatusReasonButton();
		
		approveinspscreen.clickDeclineAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.selectStatusReason("Decline 1");
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
		myinspectionsscreen.clickHomeButton();
		Thread.sleep(15000);
		webdriverInicialize();
		webdriverGotoWebPage("http://reconpro-devqa.cyberianconcepts.com/Company/Inspections.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InspectionsWebPage inspectionspage = PageFactory.initElements(
				webdriver, InspectionsWebPage.class);
		inspectionspage.makeSearchPanelVisible();
		inspectionspage.selectSearchStatus("Declined");
		inspectionspage.searchInspectionByNumber(inspectionnumber);
		Assert.assertEquals(inspectionspage.getInspectionAmountApproved(inspectionnumber), "$0.00");
		Assert.assertEquals(inspectionspage.getInspectionReason(inspectionnumber), "Decline 1");
		Assert.assertEquals(inspectionspage.getInspectionStatus(inspectionnumber), "Declined");
		getWebDriver().quit();
	}
	
	@Test(testName = "Test Case 32286:Inspections: Regular - Verify that amount of approved services are shown on BO > inspections list > column ApprovedAmount", 
			description = "Verify that amount of approved services are shown on BO > inspections list > column ApprovedAmount")
	@Parameters({ "backoffice.url", "user.name", "user.psw" })
	public void testRegularVerifyThatAmountOfApprovedServicesAreShownOnBOInspectionsListInColumnApprovedAmount(String backofficeurl, String userName, String userPassword) throws Exception {

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
		myinspectionsscreen.selectInspectionType ("Inspection_for_auto_WO_line_appr");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Left Rear Door");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		selectedservicedetailscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicedetailscreen.clickVehiclePartsCell();
		selectedservicedetailscreen.selectVehiclePart("Front Bumper");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicedetailscreen.clickVehiclePartsCell();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForAction(inspectionnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		String servicetoapprove = iOSInternalProjectConstants.SR_S1_MONEY + " (Grill)";
		String servicetodecline = iOSInternalProjectConstants.SR_S1_MONEY_PANEL + " (Left Rear Door)";
		String servicetoskip = iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE + " (Front Bumper)";
		Helpers.waitABit(6000);
		approveinspscreen.selectInspectionServiceToApprove(servicetoapprove);
		approveinspscreen.selectInspectionServiceToDecline(servicetodecline);
		approveinspscreen.selectInspectionServiceToSkip(servicetoskip);
		approveinspscreen.clickSaveButton();
		
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
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
		inspectionspage.searchInspectionByNumber(inspectionnumber);		
		Assert.assertEquals(inspectionspage.getInspectionAmountApproved(inspectionnumber), "$2,000.00");
		Assert.assertEquals(inspectionspage.getInspectionStatus(inspectionnumber), "Approved");
		getWebDriver().quit();
	}
	
	@Test(testName = "Test Case 32287:Inspections: Regular - Verify that amount of skipped/declined services are not calc go approved amount BO > inspections list > column ApprovedAmount", 
			description = "Verify that amount of skipped/declined services are not calc go approved amount BO > inspections list > column ApprovedAmount")
	@Parameters({ "user.name", "user.psw" })
	public void testVerifyThatAmountOfSkippedDeclinedServicesAreNotCalc(String userName, String userPassword) throws Exception {
		
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
		approveinspscreen.clickSkipAllServicesButton();		
		approveinspscreen.clickSaveButton();
		approveinspscreen.clickDoneStatusReasonButton();
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
		
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
		inspectionspage.selectSearchStatus("Declined");
		inspectionspage.searchInspectionByNumber(inspnumber);		
		Assert.assertEquals(inspectionspage.getInspectionAmountApproved(inspnumber), "$0.00");
		Assert.assertEquals(inspectionspage.getInspectionApprovedTotal(inspnumber), "$0.00");
		getWebDriver().quit();
	}
	
	@Test(testName = "Test Case 33664:Inspections: Regular - Verify that services are marked as strikethrough when exclude from total", 
			description = "Inspections: Regular - Verify that services are marked as strikethrough when exclude from total")
	public void testInspectionVerifyServicesAreMarkedAsStrikethroughWhenExcludeFromTotal() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
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
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_VEHICLE);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$1,050.00");
		for (int i = 0; i < 2; i++) {
			servicesscreen.clickToolButton();
			selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
			selectedservicedetailscreen.saveSelectedServiceDetails();
			selectedservicedetailscreen.selectVehiclePart("Right Door Mirror");
			selectedservicedetailscreen.saveSelectedServiceDetails();
			selectedservicedetailscreen.saveSelectedServiceDetails();
			servicesscreen.clickAddServicesButton();
		}
		servicesscreen.assertTotalAmauntIsCorrect("$2,050.00");
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForAction(inspectionnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);	
		approveinspscreen.clickApproveAllServicesButton();
		approveinspscreen.clickSaveButton();
		Assert.assertEquals(approveinspscreen.getInspectionTotalAmount(), "$2,050.00");
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 38748:Inspections: Regular - Verify that value selected on price matrix step is saved and shown during edit mode", 
			description = "Verify that value selected on price matrix step is saved and shown during edit mode")
	public void testRegularVerifyThatValueSelectedOnPriceMatrixStepIsSavedAndShownDuringEditMode() throws Exception {
			
		final String VIN = "111111111111111";
		final String _make = "Acura";
		final String _model = "CL";
		final String _color = "Black";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.TEST_COMPANY_CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_TYPE_FOR_PRICE_MATRIX);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		vehiclescreeen.setMakeAndModel(_make, _model);
		vehiclescreeen.setColor(_color);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		
		vehiclescreeen.selectNextScreen("Price Matrix Zayats");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("VP1 zayats");
		pricematrix.setSizeAndSeverity("CENT", "LIGHT");
		pricematrix.selectDiscaunt(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		pricematrix.clickSaveButton();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);		
		Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$55.00");
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$55.00");
		
		
		pricematrix.selectNextScreen("Zayats test pack");
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		servicesscreen.clickAddServicesButton();
		servicesscreen.assertTotalAmauntIsCorrect("$60.00");
		
		servicesscreen.selectNextScreen("Hail Matrix");
		pricematrix.selectPriceMatrix("ROOF");
		pricematrix.setSizeAndSeverity("DIME", "LIGHT");
		pricematrix.setPrice("123");
		pricematrix.clickSaveButton();
		Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$123.00");
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$183.00");
		
		pricematrix.selectNextScreen("Hail Damage");
		pricematrix.selectPriceMatrix("Grill");
		pricematrix.setSizeAndSeverity("DIME", "LIGHT");
		pricematrix.clickSaveButton();
		Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$10.00");
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$193.00");
		
		pricematrix.clickSaveButton();
		Helpers.acceptAlert();
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		
		vehiclescreeen.clickSaveButton();
		for (int i = 0; i<2; i++) {
			myinspectionsscreen.selectInspectionForEdit(inspectionnumber);
			pricematrix.selectNextScreen("Price Matrix Zayats");
			Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$55.00");
			Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$193.00");
		
			pricematrix.selectNextScreen("Zayats test pack");
			servicesscreen.assertSubTotalAmauntIsCorrect("$5.00");
			servicesscreen.assertTotalAmauntIsCorrect("$193.00");
		
			pricematrix.selectNextScreen("Hail Matrix");
			Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$123.00");
			Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$193.00");
		
			pricematrix.selectNextScreen("Hail Damage");
			Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$10.00");
			Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$193.00");
			vehiclescreeen.clickSaveButton();
		}
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 42184:WO: Regular - Verify that message is shown total is over limitation 999999999.999", 
			description = "Verify that message is shown total is over limitation 999999999.999")
	public void testWOVerifyThatMessageIsShownTotalIsOverLimitation()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);

		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		
		//customer approval ON
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_SMOKE_TEST);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.setServicePriceValue("999999.00");
		selectedservicedetailscreen.setServiceQuantityValue("98765");
		
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		ordersummaryscreen.clickSaveButton();
		
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Total amount of work order is huge."));
		Assert.assertTrue(alerttext.contains("Maximum allowed total amount is $999,999,999.99"));
		ordersummaryscreen.swipeScreenLeft();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.setServiceQuantityValue("987");
		
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 42183:Invoices: Regular - Verify that message is shown total is over limitation 999,999,999.999", 
			description = "Verify that message is shown total is over limitation 999,999,999.999")
	public void testInvoicesVerifyThatMessageIsShownTotalIsOverLimitation()
			throws Exception {
		
		final String VIN  = "JA4LS31H8YP047397";
		
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
		String wo1 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.setServicePriceValue("999999.00");
		selectedservicedetailscreen.setServiceQuantityValue("987");
		
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen.clickAddOrderButton();
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wo2 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A1");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.setServicePriceValue("999999.00");
		selectedservicedetailscreen.setServiceQuantityValue("987");
		
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		ordersummaryscreen.clickSaveButton();
		myworkordersscreen.approveWorkOrder(wo1, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen.clickCreateInvoiceIconForWO(wo1);
		myworkordersscreen.clickInvoiceIcon();
		
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType("Invoice_Custom1");
		invoiceinfoscreen.selectNextScreen("Info");
		invoiceinfoscreen.setPO("123");
		String invoicenum = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsDraft();
		
		myworkordersscreen.approveWorkOrder(wo2, iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		myworkordersscreen.clickHomeButton();
		
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		myinvoicesscreen.selectInvoice(invoicenum);
		myinvoicesscreen.clickEditPopup();
		invoiceinfoscreen.selectNextScreen("Info");
		invoiceinfoscreen.addWorkOrder(wo2);
		invoiceinfoscreen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttext.contains("Total amount of invoice is huge."));
		Assert.assertTrue(alerttext.contains("Maximum allowed total amount is $999,999,999.99"));
		Helpers.waitABit(1000);
		ordersummaryscreen.swipeScreenLeft();
		invoiceinfoscreen.clickChangeScreen();
		invoiceinfoscreen.cancelInvoice();
		myinvoicesscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 42182:Inspection: Regular - Verify that message is shown total is over limitation 999999999.999", 
			description = "Inspection: Verify that message is shown total is over limitation 999999999.999")
	public void testInspectionVerifyThatMessageIsShownTotalIsOverLimitation() throws Exception {
		
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
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.setServicePriceValue("999999.00");
		selectedservicedetailscreen.setServiceQuantityValue("98765");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();
		String alerttext = Helpers.getAlertTextAndAccept();
		
		Assert.assertTrue(alerttext.contains("Total amount of inspection is huge."));
		Assert.assertTrue(alerttext.contains("Maximum allowed total amount is $999,999,999.99"));
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.setServiceQuantityValue("987");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickSaveAsDraft();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName="Test Case 42372:Inspections: Regular - Verify approved amount for Inspection created from SR", description = "Verify approved amount for Inspection created from SR")
	public void testVerifyApprovedAmountForInspectionCreatedFromSR() throws Exception {
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularServiceRequestsScreen servicerequestsscreen = homescreen.clickServiceRequestsButton();
		Thread.sleep(3000);
		servicerequestsscreen.clickAddButton();
		servicerequestsscreen.selectInspectionType(iOSInternalProjectConstants.SR_ALL_PHASES);
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
		Thread.sleep(3000);
		servicerequestsscreen = new RegularServiceRequestsScreen(appiumdriver);
		String srnumber = servicerequestsscreen.getFirstServiceRequestNumber();
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectCreateInspectionRequestAction();
		Thread.sleep(2000);
		servicerequestsscreen.selectInspectionType("Insp_for_auto_WO_line_appr_multiselect");
		servicerequestsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		vehiclescreeen = new RegularVehicleScreen(appiumdriver);

		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A2");
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY);
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveButton();	
		
		servicerequestsscreen.selectServiceRequest(srnumber);
		servicerequestsscreen.selectSummaryRequestAction();
		Thread.sleep(3000);
		servicerequestsscreen.clickServiceRequestSummaryInspectionsButton();
		Thread.sleep(4000);
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen(appiumdriver);
		
		myinspectionsscreen.selectInspectionForApprove(inspectionnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.selectInspectionServiceToApprove(iOSInternalProjectConstants.SR_S1_MONEY + " (Hood)");
		approveinspscreen.selectInspectionServiceToDecline(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		approveinspscreen.clickSaveButton();		
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
		Helpers.waitABit(3000);
		myinspectionsscreen.assertInspectionIsApproved(inspectionnumber);
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionAprovedPriceValue(), "$2,000.00");
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionPriceValue(), "$2,050.00");
		myinspectionsscreen.clickHomeButton();
		servicerequestsscreen.clickHomeButton();
		Thread.sleep(3000);
		servicerequestsscreen.clickHomeButton();
	}
	
	String inspnumber47249 = "";
	
	@Test(testName = "Test Case 47249:Inspections: Regular - Verify that on Price matrix step sub total value is shown correctly", 
			description = "Verify that on Price matrix step sub total value is shown correctly")
	public void testVerifyThatOnPriceMatrixStepSubTotalValueIsShownCorrectly() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";		
		final String _pricematrix1 = "Hood";
		final String defprice = "100";
		final String timevalue = "20";
		
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
		inspnumber47249 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		myinspectionsscreen.selectNextScreen("Default");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix1);
		pricematrix.switchOffOption("PDR");
		pricematrix.setPrice(defprice);
		pricematrix.clickDiscaunt("SR_S5_Mt_Upcharge_20");
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.saveSelectedServiceDetails();	
		pricematrix.clickSaveButton();
		Assert.assertEquals(pricematrix.getInspectionSubTotalPrice(), "$120.00");
		pricematrix.selectPriceMatrix(_pricematrix1);
		pricematrix.clickDiscaunt("SR_S5_Mt_Upcharge_25");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		Assert.assertEquals(pricematrix.getInspectionSubTotalPrice(), "$145.00");
		pricematrix.selectPriceMatrix(_pricematrix1);
		pricematrix.clickDiscaunt("SR_S5_Mt_Discount_10");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		Assert.assertEquals(pricematrix.getInspectionSubTotalPrice(), "$130.50");
		
		myinspectionsscreen.selectNextScreen("Matrix Labor");
		pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix(_pricematrix1);
		pricematrix.setSizeAndSeverity("DIME", "VERY LIGHT");
		Helpers.waitABit(500);
		pricematrix.setTime(timevalue);
		pricematrix.clickDiscaunt("SR_S5_Mt_Discount_10");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		Assert.assertEquals(pricematrix.getInspectionSubTotalPrice(), "$90.00");
		pricematrix.selectPriceMatrix(_pricematrix1);
		pricematrix.clickDiscaunt("SR_S5_Mt_Upcharge_25");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		Assert.assertEquals(pricematrix.getInspectionSubTotalPrice(), "$112.50");
		pricematrix.clickSaveButton();
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionPriceValue(), "$293.00");
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 47257:WO: Regular - Verify that on Price Matrix step sub total is shown correctly", 
			description = "Verify that on Price Matrix step sub total is shown correctly"/*,
			dependsOnMethods = { "testVerifyThatOnPriceMatrixStepSubTotalValueIsShownCorrectly" }*/)
	public void testVerifyThatOnPriceMatrixStepSubTotalIsShownCorrectly() throws Exception {
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.selectInspectionForApprove(inspnumber47249);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		
		approveinspscreen.approveInspectionApproveAllAndSignature();
		myinspectionsscreen.selectInspectionForCreatingWO(inspnumber47249);
		Helpers.waitABit(1000);
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.WO_SMOKE_MONITOR);
		myinspectionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.assertTotalAmauntIsCorrect("$293.00");
		servicesscreen.searchServiceByName("Dent Removal");
		servicesscreen.assertServiceIsSelectedWithServiceValues("Dent Removal", "$112.50");
		servicesscreen.assertServiceIsSelectedWithServiceValues("Dent Removal", "$130.50");
		servicesscreen.cancelOrder();
		myinspectionsscreen.clickHomeButton();
	}
	
	String inspnumber48543 = "";
	
	@Test(testName = "Test Case 48543:Inspections: Regular - Verify that part services with different configurations are correctly shown for inspection", 
			description = "Inspections: Regular - Verify that part services with different configurations are correctly shown for inspection")
	public void testInspectionVerifyThatPartServicesWithDifferentConfigurationsAreCorrectlyShownForInspection() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_WITH_PART_SERVICES);
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		inspnumber48543 = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveAsDraft();
		
		myinspectionsscreen.selectInspectionForEdit(inspnumber48543);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_Category");
		selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Engine");
		selectedservicescreen.selectServicePartSubcategory("Filters");
		selectedservicescreen.selectServicePartSubcategoryPart("Engine Oil Filter");
		selectedservicescreen.selectServicePartSubcategoryPosition("Oil Cooler");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.setServicePriceValue("2.35");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Driver Seat");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_Name");
		//selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartValue(), "Engine Coolant Outlet Housing Bolt (N/A)");
		selectedservicescreen.setServicePriceValue("2.5");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("VP1 zayats");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_None");
		selectedservicescreen.clickServicePartCell();
		selectedservicescreen.selectServicePartCategory("Body");
		selectedservicescreen.selectServicePartSubcategory("Bumper");
		selectedservicescreen.selectServicePartSubcategoryPart("Bumper");
		selectedservicescreen.selectServicePartSubcategoryPosition("Rear Upper");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.setServicePriceValue("5.09");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Back Glass");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.answerQuestionCheckButton();		
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_several");
		selectedservicescreen.clickServicePartCell();
		selectedservicescreen.selectCategory("Air and Fuel Delivery");
		selectedservicescreen.selectServicePartSubcategory("Filters");
		selectedservicescreen.selectServicePartSubcategoryPart("Air Filter");
		selectedservicescreen.selectServicePartSubcategoryPosition("Inner");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.setServicePriceValue("7");
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_SubCategory");
		selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Body");
		Assert.assertEquals(selectedservicescreen.getServicePartSubCategoryValue(), "Bumper");
		selectedservicescreen.selectServicePartSubcategoryPart("Bumper Air Shield");
		selectedservicescreen.selectServicePartSubcategoryPosition("Front Lower");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.setServicePriceValue("4.31");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("VP1 zayats");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		vehiclescreeen.selectNextScreen("Future Audi Car");		
		RegularVisualInteriorScreen visualinteriorscreen = new RegularVisualInteriorScreen(appiumdriver);
		visualinteriorscreen.clickServicesToolbarButton();
		visualinteriorscreen.switchToCustomTab();
		visualinteriorscreen.selectService("None");
		visualinteriorscreen.selectSubService("Oksi_Part_SubCategory");
		Helpers.tapRegularCarImage();
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Body");
		Assert.assertEquals(selectedservicescreen.getServicePartSubCategoryValue(), "Bumper");
		selectedservicescreen.selectServicePartSubcategoryPart("Bumper Assembly");
		selectedservicescreen.selectServicePartSubcategoryPosition("Front");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.setServicePriceValue("6.43");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("VP1 zayats");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		vehiclescreeen.selectNextScreen("PM_New");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("VP1 zayats");
		pricematrix.switchOffOption("PDR");
		pricematrix.setPrice("10");
		pricematrix.clickDiscaunt("Oksi_Part_Category");
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Engine");
		selectedservicescreen.selectServicePartSubcategory("Electrical Connectors");
		selectedservicescreen.selectServicePartSubcategoryPart("Engine Brake Relay Connector");
		selectedservicescreen.saveSelectedServiceDetails();
		Assert.assertEquals(selectedservicescreen.getServicePartValue(), "Engine Brake Relay Connector (N/A)");
		selectedservicescreen.setServicePriceValue("12.44");
		selectedservicescreen.saveSelectedServiceDetails();
		
		pricematrix.clickDiscaunt("Oksi_Part_Name");
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		Assert.assertEquals(selectedservicescreen.getServicePartValue(), "Engine Coolant Outlet Housing Bolt (N/A)");
		selectedservicescreen.clickServicePartCell();		
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Belts and Cooling");
		Assert.assertEquals(selectedservicescreen.getServicePartSubCategoryValue(), "Hardware");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.setServicePriceValue("2.13");
		selectedservicescreen.saveSelectedServiceDetails();
		Assert.assertEquals(pricematrix.getPriceMatrixVehiclePartSubTotalPrice(), "$24.57");		
		
		pricematrix.clickDiscaunt("Oksi_Part_Category");
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.setServiceQuantityValue("3");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);	
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$89.13");
		Assert.assertEquals(toolaber.getInspectionSubTotalPrice(), "$49.45");
		
		servicesscreen.clickSaveAsFinal();
		Helpers.acceptAlert();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Test Service with req question");
		selectedservicescreen.answerQuestion2("A3");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.selectVehiclePart("Front Bumper");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.saveSelectedServiceDetails();	
		servicesscreen.clickSaveAsFinal();
		Assert.assertEquals(myinspectionsscreen.getInspectionPriceValue(inspnumber48543), "$89.13");
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 48562:WO: Regular - Verify that part services are copied from insp to order", 
			description = "WO: Regular - Verify that part services are copied from insp to order")
	public void testWOVerifyThatPartServicesAreCopiedFromInspToOrder() throws Exception {
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.selectInspectionForAction(inspnumber48543);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.clickApproveAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
	
		myinspectionsscreen.selectInspectionForCreatingWO(inspnumber48543);
		RegularMyWorkOrdersScreen myworkordersscreen = new RegularMyWorkOrdersScreen(appiumdriver);
		myworkordersscreen.selectWorkOrderType("WO_with_part_service");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		String wonumber = vehiclescreeen.getInspectionNumber();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$77.13");
		vehiclescreeen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		
		ordersummaryscreen.clickSaveButton();
		homescreen = myinspectionsscreen.clickHomeButton();
		myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		Assert.assertEquals(myworkordersscreen.getPriceValueForWO(wonumber), "$77.13");
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 48563:WO: Regular - Verify that part services with different configurations are correctly shown for WO", 
			description = "WO: Regular - Verify that part services with different configurations are correctly shown for WO")
	public void testWOVerifyThatPartServicesWithDifferentConfigurationsAreCorrectlyShownForWO() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);			
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
			
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		
		myworkordersscreen.selectWorkOrderType("WO_with_part_service");
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		
		ordersummaryscreen.clickSaveButton();
		
		
		myworkordersscreen.selectWorkOrderForEidt(wonumber);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_Category");
		selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Engine");
		selectedservicescreen.selectServicePartSubcategory("Filters");
		selectedservicescreen.selectServicePartSubcategoryPart("Engine Oil Filter");
		selectedservicescreen.selectServicePartSubcategoryPosition("Oil Cooler");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.setServicePriceValue("2.35");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Driver Seat");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_Name");
		//selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartValue(), "Engine Coolant Outlet Housing Bolt (N/A)");
		selectedservicescreen.setServicePriceValue("2.5");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("VP1 zayats");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_None");
		selectedservicescreen.clickServicePartCell();
		selectedservicescreen.selectServicePartCategory("Body");
		selectedservicescreen.selectServicePartSubcategory("Bumper");
		selectedservicescreen.selectServicePartSubcategoryPart("Bumper");
		selectedservicescreen.selectServicePartSubcategoryPosition("Rear Upper");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.setServicePriceValue("5.09");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Back Glass");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.answerQuestionCheckButton();		
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_several");
		selectedservicescreen.clickServicePartCell();
		selectedservicescreen.selectCategory("Air and Fuel Delivery");
		selectedservicescreen.selectServicePartSubcategory("Filters");
		selectedservicescreen.selectServicePartSubcategoryPart("Air Filter");
		selectedservicescreen.selectServicePartSubcategoryPosition("Inner");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.setServicePriceValue("7");
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.clickToolButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails("Oksi_Part_SubCategory");
		selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Body");
		Assert.assertEquals(selectedservicescreen.getServicePartSubCategoryValue(), "Bumper");
		selectedservicescreen.selectServicePartSubcategoryPart("Bumper Air Shield");
		selectedservicescreen.selectServicePartSubcategoryPosition("Front Lower");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.setServicePriceValue("4.31");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("VP1 zayats");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectService("PM_New");	
		servicesscreen.selectService("VP1 zayats");		
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.switchOffOption("PDR");
		pricematrix.setPrice("10");
		pricematrix.clickDiscaunt("Oksi_Part_Category");
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.clickServicePartCell();
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Engine");
		selectedservicescreen.selectServicePartSubcategory("Electrical Connectors");
		selectedservicescreen.selectServicePartSubcategoryPart("Engine Brake Relay Connector");
		selectedservicescreen.saveSelectedServiceDetails();
		Assert.assertEquals(selectedservicescreen.getServicePartValue(), "Engine Brake Relay Connector (N/A)");
		selectedservicescreen.setServicePriceValue("12.44");
		selectedservicescreen.saveSelectedServiceDetails();
		
		pricematrix.clickDiscaunt("Oksi_Part_Name");
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		Assert.assertEquals(selectedservicescreen.getServicePartValue(), "Engine Coolant Outlet Housing Bolt (N/A)");
		selectedservicescreen.clickServicePartCell();		
		Assert.assertEquals(selectedservicescreen.getServicePartCategoryValue(), "Belts and Cooling");
		Assert.assertEquals(selectedservicescreen.getServicePartSubCategoryValue(), "Hardware");
		selectedservicescreen.saveSelectedServiceDetails();	
		selectedservicescreen.setServicePriceValue("2.13");
		selectedservicescreen.saveSelectedServiceDetails();
		Assert.assertEquals(pricematrix.getPriceMatrixVehiclePartSubTotalPrice(), "$24.57");		
		
		pricematrix.clickDiscaunt("Oksi_Part_Category");
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.setServiceQuantityValue("3");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		Assert.assertEquals(pricematrix.getPriceMatrixVehiclePartSubTotalPrice(), "$49.45");
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);	
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$70.70");
		servicesscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 45100:WO: Regular - Verify rounding in calculation script with price matrix", 
			description = "WO: Regular - Verify rounding in calculation script with price matrix")
	public void testWOVerifyRoundingInCalculationScriptWithPriceMatrix() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);			
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
			
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectService("Price Matrix Zayats");
		servicesscreen.selectService("Grill");	
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.switchOffOption("PDR");
		pricematrix.setPrice("975");
		pricematrix.clickDiscaunt(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.setServicePriceValue("25");
		selectedservicescreen.saveSelectedServiceDetails();
		
		pricematrix.clickDiscaunt(iOSInternalProjectConstants.SR_S1_MONEY_PANEL);
		selectedservicescreen.setServicePriceValue("192");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		selectedservicescreen.setServicePriceValue("-30");
		selectedservicescreen.saveSelectedServiceDetails();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);		
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$987.52");
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType("Invoice_Default_Template");
		invoiceinfoscreen.setPO("12345");
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveButton();
		Helpers.acceptAlert();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		servicesscreen.clickSaveAsFinal();
		myworkordersscreen.clickFilterButton();
		myworkordersscreen.setFilterBilling("All");
		myworkordersscreen.clickSaveFilter();
		
		Assert.assertEquals(myworkordersscreen.getPriceValueForWO(wonumber), "$987.52");
		homescreen = myworkordersscreen.clickHomeButton();
		RegularMyInvoicesScreen myinvoicesscreen = homescreen.clickMyInvoices();
		Assert.assertEquals(myinvoicesscreen.getPriceForInvoice(invoicenumber), "$987.52");
		myinvoicesscreen.clickHomeButton();		
	}
	
	@Parameters({ "user.name", "user.psw" })
	@Test(testName = "Test Case 45224:WO: Regular - Verify calculation with price matrix Labor type", 
			description = "WO: Regular - Verify calculation with price matrix Labor type")
	public void testWOVerifyCalculationWithPriceMatrixLaborType(String userName, String userPassword) throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);			
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
			
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");
		selectedservicedetailscreen.setServicePriceValue("100");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.BUNDLE1_DISC_EX);
		RegularSelectedServiceBundleScreen selectedservicebundlescreen = new RegularSelectedServiceBundleScreen(appiumdriver);
		selectedservicebundlescreen.selectBundle(iOSInternalProjectConstants.DYE_SERVICE);
		selectedservicebundlescreen.openBundleInfo(iOSInternalProjectConstants.WHEEL_SERVICE);
		selectedservicedetailscreen.setServicePriceValue("90");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.changeAmountOfBundleService("100");
		
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.DISCOUNT_5_10_SERVICE);
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		selectedservicedetailscreen.setServicePriceValue("25");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails("Tax discount");
		selectedservicedetailscreen.setServicePriceValue("10");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.selectSubService("Matrix Service");
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.selectPriceMatrix("Test Matrix Labor");
		pricematrix.selectPriceMatrix("123");
		pricematrix.switchOffOption("PDR");
		pricematrix.setTime("100");
		pricematrix.clickDiscaunt(iOSInternalProjectConstants.SR_DISC_20_PERCENT);
		selectedservicedetailscreen.setServicePriceValue("25");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		pricematrix.clickDiscaunt(iOSInternalProjectConstants.TEST_SERVICE_ZAYATS);
		selectedservicedetailscreen.setServicePriceValue("100");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		ordersummaryscreen.checkApproveAndCreateInvoice();
		ordersummaryscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		ordersummaryscreen.clickSaveButton();
		Helpers.waitABit(1000);
		RegularInvoiceInfoScreen invoiceinfoscreen = ordersummaryscreen.selectInvoiceType("Invoice_AutoWorkListNet");
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		questionsscreen.selectNextScreen("Info");
		invoiceinfoscreen.setPO("12345");
		String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsFinal();
		
		myworkordersscreen.clickFilterButton();
		myworkordersscreen.setFilterBilling("All");
		myworkordersscreen.clickSaveFilter();
		
		Assert.assertEquals(myworkordersscreen.getPriceValueForWO(wonumber), "$542.68");
		homescreen = myworkordersscreen.clickHomeButton();
		
		webdriverInicialize();
		webdriverGotoWebPage("https://reconpro-devqa.cyberianconcepts.com/Company/Invoices.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InvoicesWebPage invoicespage = PageFactory.initElements(
				webdriver, InvoicesWebPage.class);
		
		invoicespage.setSearchInvoiceNumber(invoicenumber);
		invoicespage.clickFindButton();
		String mainWindowHandle = webdriver.getWindowHandle();
		invoicespage.clickInvoicePrintPreview(invoicenumber);
		Assert.assertEquals(invoicespage.getPrintPreviewTestMartrixLaborServiceListValue("Matrix Service"), "$100.00");
		Assert.assertEquals(invoicespage.getPrintPreviewTestMartrixLaborServiceNetValue("Matrix Service"), "$112.50");
		Assert.assertEquals(invoicespage.getPrintPreviewTestMartrixLaborServiceListValue("Test service zayats"), "$100.00");
		Assert.assertEquals(invoicespage.getPrintPreviewTestMartrixLaborServiceNetValue("Test service zayats"), "$112.50");
		Assert.assertEquals(invoicespage.getPrintPreviewTestMartrixLaborServiceListValue("SR_Disc_20_Percent (25.000%)"), "$25.00");
		Assert.assertEquals(invoicespage.getPrintPreviewTestMartrixLaborServiceNetValue("SR_Disc_20_Percent (25.000%)"), "$28.13");
		invoicespage.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Parameters({ "user.name", "user.psw" })
	@Test(testName = "Test Case 42803:Invoices: Regular - Verify rounding money amount values", 
			description = "Invoices: Regular - Verify rounding money amount values")
	public void testInvoicesVerifyRoundingMoneyAmountValues(String userName, String userPassword) throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);			
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
			
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectSubService(iOSInternalProjectConstants.TEST_SERVICE_PRICE_MATRIX);
		servicesscreen.selectService("Price Matrix Zayats");
		servicesscreen.selectService("Grill");	
		RegularPriceMatrixScreen pricematrix = new RegularPriceMatrixScreen(appiumdriver);
		pricematrix.switchOffOption("PDR");
		pricematrix.setPrice("100");
		pricematrix.clickDiscaunt(iOSInternalProjectConstants.OKSI_SERVICE_PP_FLAT_FEE);
		RegularSelectedServiceDetailsScreen selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.setServicePriceValue("23");
		selectedservicescreen.saveSelectedServiceDetails();
		pricematrix.clickSaveButton();
		pricematrix.clickBackButton();
		pricematrix.clickBackButton();
		
		servicesscreen.selectSubService(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		selectedservicescreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicescreen.setServicePriceValue("10");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Back Glass");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.answerQuestion2("A3");
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_BUNDLE_PP);
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_SERVICE);
		selectedservicescreen.setServicePriceValue("25");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Hood");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		
		servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_PANEL);
		selectedservicescreen.setServicePriceValue("5");
		selectedservicescreen.clickVehiclePartsCell();
		selectedservicescreen.selectVehiclePart("Grill");
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.saveSelectedServiceDetails();
		selectedservicescreen.changeAmountOfBundleService("30");
		servicesscreen.clickSaveButton();
		servicesscreen.openCustomServiceDetails("Sales Tax");
		selectedservicescreen.setServicePriceValue("3.8");
		selectedservicescreen.saveSelectedServiceDetails();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);		
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$169.19");
		
		questionsscreen.selectNextScreen(RegularOrderSummaryScreen
				.getOrderSummaryScreenCaption());
		RegularOrderSummaryScreen ordersummaryscreen = new RegularOrderSummaryScreen(appiumdriver);
		ordersummaryscreen.setTotalSale("3");
		ordersummaryscreen.clickSaveButton();
		
		myworkordersscreen.selectWorkOrderForApprove(wonumber);
		myworkordersscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen =  new RegularApproveInspectionsScreen(appiumdriver);
		approveinspscreen.clickApproveButton();
		
		myworkordersscreen.clickCreateInvoiceIconForWO(wonumber);
		myworkordersscreen.clickInvoiceIcon();
		RegularInvoiceInfoScreen invoiceinfoscreen = myworkordersscreen.selectInvoiceType(iOSInternalProjectConstants.DEFAULT_INVOICETYPE);
		invoiceinfoscreen.setPO("12345");
		String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		invoiceinfoscreen.clickSaveAsFinal();
		myworkordersscreen.clickHomeButton();
		
		webdriverInicialize();
		webdriverGotoWebPage("https://reconpro-devqa.cyberianconcepts.com/Company/Invoices.aspx");

		BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
				BackOfficeLoginWebPage.class);
		loginpage.UserLogin(userName, userPassword);
		InvoicesWebPage invoicespage = PageFactory.initElements(
				webdriver, InvoicesWebPage.class);
		
		invoicespage.setSearchInvoiceNumber(invoicenumber);
		invoicespage.clickFindButton();
		String mainWindowHandle = webdriver.getWindowHandle();
		invoicespage.clickInvoiceInternalTechInfo(invoicenumber);
		Assert.assertEquals(invoicespage.getTechInfoServicesTableServiceValue("<Tax>", "Test service price matrix"), "4.67000");
		Assert.assertEquals(invoicespage.getTechInfoServicesTableServiceValue("<Tax>", "Test service price matrix (Sev: None; Size: None)"), "3.79600");
		Assert.assertEquals(invoicespage.getTechInfoServicesTableServiceValue("<Tax>", "Oksi_Service_PP_Flat_Fee"), "0.87400");
		invoicespage.closeNewTab(mainWindowHandle);
		getWebDriver().quit();
	}
	
	@Test(testName = "Test Case 38970:Inspections: Reqular - Verify that updated value for required service with 0 Price is saved when package grouped by panels", 
			description = "Inspections: Reqular - Verify that updated value for required service with 0 Price is saved when package grouped by panels")
	public void testInspectionsVerifyThatUpdatedValueForRequiredServiceWith0PriceIsSavedWhenPackageArouvedByPanels() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		final String serviceprice = "21";
		
		homescreen = new RegularHomeScreen(appiumdriver);
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType (iOSInternalProjectConstants.INSP_WITH_0_PRICE);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		final String inspnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForAction(inspnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);	
		Assert.assertEquals(approveinspscreen.getInspectionServicePrice(iOSInternalProjectConstants.SERVICE_REQ_0_PRICE), "$0.00");
		approveinspscreen.clickCancelButton();
		approveinspscreen.clickCancelButton();
		
		myinspectionsscreen.selectInspectionForEdit(inspnumber);
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.selectService("Other");
		RegularSelectedServiceDetailsScreen selectedservicescreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SERVICE_REQ_0_PRICE);
		selectedservicescreen.setServicePriceValue(serviceprice);
		selectedservicescreen.saveSelectedServiceDetails();
		servicesscreen.clickBackServicesButton();
		servicesscreen.clickSaveButton();
		
		myinspectionsscreen.selectInspectionForAction(inspnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);	
		Assert.assertEquals(approveinspscreen.getInspectionServicePrice(iOSInternalProjectConstants.SERVICE_REQ_0_PRICE), PricesCalculations.getPriceRepresentation(serviceprice));
		approveinspscreen.clickCancelButton();
		approveinspscreen.clickCancelButton();
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 40463:Inspections: Regular - Verify that appoved amount is shown on Inspection list in dark green and total in dark gray when appove inspection", 
			description = "Inspections: Regular - Verify that appoved amount is shown on Inspection list in dark green and total in dark gray when appove inspection")
	public void testInspectionsVerifyThatAppovedAmountIsShownOnInspectionListInDarkGreenAndTotalInDarkGrayWhenAppoveInspection() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";

		
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
		
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");
		selectedservicedetailscreen.setServicePriceValue("25");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen.setServicePriceValue("2000");
		selectedservicedetailscreen.clickVehiclePartsCell();
		selectedservicedetailscreen.selectVehiclePart("Roof");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S4_BUNDLE);
		selectedservicedetailscreen.setServicePriceValue("600");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		selectedservicedetailscreen.setServicePriceValue("13");
		selectedservicedetailscreen.clickVehiclePartsCell();
		selectedservicedetailscreen.selectVehiclePart("Back Glass");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.answerQuestion2("A3");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		RegularInspectionToolBar toolaber = new RegularInspectionToolBar(appiumdriver);		
		Assert.assertEquals(toolaber.getInspectionTotalPrice(), "$2,688.00");
		servicesscreen.clickSaveButton();
		myinspectionsscreen.selectInspectionForAction(inspnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);
		
		approveinspscreen.selectInspectionServiceToApprove(iOSInternalProjectConstants.DISC_EX_SERVICE1);
		approveinspscreen.selectInspectionServiceToApprove(iOSInternalProjectConstants.SR_S1_MONEY + " (Roof)");
		approveinspscreen.selectInspectionServiceToApprove(iOSInternalProjectConstants.SR_S4_BUNDLE);
		approveinspscreen.selectInspectionServiceToSkip("3/4\" - Penny Size");
		approveinspscreen.selectInspectionServiceToSkip(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE + " (Back Glass)");
		approveinspscreen.clickSaveButton();
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
		
		Assert.assertEquals(myinspectionsscreen.getInspectionPriceValue(inspnumber), "$2,688.00");
		Assert.assertEquals(myinspectionsscreen.getInspectionApprovedPriceValue(inspnumber), "$2,650.00");
		myinspectionsscreen.clickHomeButton();
	}
	
	@Test(testName = "Test Case 40466:Inspections: Regular - Verify that appoved amount is shown on Inspection list in dark green and total in dark gray when decline inspection", 
			description = "Verify that appoved amount is shown on Inspection list in dark green and total in dark gray when decline inspection")
	public void testVerifyThatAppovedAmountIsShownOnInspectionListInDarkGreenAndTotalInDarkGrayWhenDeclineInspection() throws Exception {

		final String VIN  = "1D7HW48NX6S507810";		
		homescreen = new RegularHomeScreen(appiumdriver);
		
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O03TEST__CUSTOMER);
		
		RegularMyInspectionsScreen myinspectionsscreen = homescreen.clickMyInspectionsButton();
		myinspectionsscreen.clickAddInspectionButton();
		myinspectionsscreen.selectInspectionType ("Insp_Draft_Mode");
		myinspectionsscreen.selectNextScreen(RegularVehicleScreen.getVehicleScreenCaption());
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String inspectionnumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.clickToolButton();
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails("3/4\" - Penny Size");
		selectedservicedetailscreen.setServicePriceValue("25");
		selectedservicedetailscreen.saveSelectedServiceDetails();		
		
		servicesscreen.selectService(iOSInternalProjectConstants.SR_S1_MONEY);
		selectedservicedetailscreen = new RegularSelectedServiceDetailsScreen(appiumdriver);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S4_BUNDLE);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.selectService(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		selectedservicedetailscreen.clickVehiclePartsCell();
		selectedservicedetailscreen.selectVehiclePart("Back Glass");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.answerQuestion2("A3");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		servicesscreen.clickAddServicesButton();
		servicesscreen.clickSaveAsFinal();
			
		myinspectionsscreen.selectInspectionForAction(inspectionnumber);
		myinspectionsscreen.selectEmployeeAndTypePassword(iOSInternalProjectConstants.MAN_INSP_EMPLOYEE, iOSInternalProjectConstants.USER_PASSWORD);
		RegularApproveInspectionsScreen approveinspscreen = new RegularApproveInspectionsScreen(appiumdriver);		
		approveinspscreen.clickDeclineAllServicesButton();
		approveinspscreen.clickSaveButton();
		approveinspscreen.selectStatusReason("Decline 1");
		approveinspscreen.drawApprovalSignature ();
		approveinspscreen.clickApproveButton();
		Thread.sleep(1000);
		myinspectionsscreen.clickFilterButton();
		myinspectionsscreen.clickStatusFilter();
		myinspectionsscreen.clickFilterStatus("Declined");
		myinspectionsscreen.clickHomeButton();
		myinspectionsscreen.clickSaveFilterDialogButton();
				
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionAprovedPriceValue(), "$0.00");
		Assert.assertEquals(myinspectionsscreen.getFirstInspectionPriceValue(), "$2,638.00");		
		myinspectionsscreen.clickHomeButton();		
	}
	
	@Test(testName = "Test Case 35030:WO: Regular - Verify that for bundle items price policy is applied", 
			description = "WO: Regular - Verify that for bundle items price policy is applied")
	public void testWOVerifyThatForBundleItemsPricePolicyIsApplied() throws Exception {
		
		final String VIN  = "1D7HW48NX6S507810";
		
		homescreen = new RegularHomeScreen(appiumdriver);			
		RegularCustomersScreen customersscreen = homescreen.clickCustomersButton();
		customersscreen.swtchToWholesaleMode();
		customersscreen.selectCustomerWithoutEditing(iOSInternalProjectConstants.O02TEST__CUSTOMER);
			
		RegularMyWorkOrdersScreen myworkordersscreen = homescreen.clickMyWorkOrdersButton();
		myworkordersscreen.clickAddOrderButton();
		
		myworkordersscreen.selectWorkOrderType(iOSInternalProjectConstants.WO_TYPE_FOR_CALC);
		RegularVehicleScreen vehiclescreeen = new RegularVehicleScreen(appiumdriver);
		vehiclescreeen.setVIN(VIN);
		String wonumber = vehiclescreeen.getInspectionNumber();
		vehiclescreeen.selectNextScreen("Zayats Section1");
		RegularQuestionsScreen questionsscreen = new RegularQuestionsScreen(appiumdriver);
		questionsscreen.swipeScreenUp();
		questionsscreen.selectAnswerForQuestion("Question 2", "A3");
		
		questionsscreen.selectNextScreen(RegularServicesScreen.getServicesScreenCaption());
		RegularServicesScreen servicesscreen = new RegularServicesScreen(appiumdriver);
		servicesscreen.openCustomServiceDetails("Oksi_Bundle_PP");
		
		RegularSelectedServiceDetailsScreen selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_VEHICLE);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Back Glass");
		selectedservicedetailscreen.selectVehiclePart("Deck Lid");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		Assert.assertEquals(selectedservicedetailscreen.getServiceDetailsPriceValue(), "$13.00");
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_SERVICE);
		selectedservicedetailscreen.setServiceQuantityValue("3");
		selectedservicedetailscreen.setServicePriceValue("10");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Hood");
		selectedservicedetailscreen.selectVehiclePart("Deck Lid");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		Assert.assertTrue(selectedservicedetailscreen.getVehiclePartValue().contains("Hood"));
		Assert.assertTrue(selectedservicedetailscreen.getVehiclePartValue().contains("Deck Lid"));	
		selectedservicedetailscreen.saveSelectedServiceDetails();
		Assert.assertEquals(selectedservicedetailscreen.getServiceDetailsPriceValue(), "$73.00");
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_PANEL);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Grill");
		selectedservicedetailscreen.selectVehiclePart("Driver Seat");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		Assert.assertEquals(selectedservicedetailscreen.getServiceDetailsPriceValue(), "$97.00");
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.OKSI_SERVICE_PP_LABOR);
		selectedservicedetailscreen.setServiceTimeValue("3");
		selectedservicedetailscreen.setServiceRateValue("25");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		Assert.assertEquals(selectedservicedetailscreen.getServiceDetailsPriceValue(), "$172.00");

		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SR_S5_MT_DISCOUNT_10);
		selectedservicedetailscreen.saveSelectedServiceDetails();
		Assert.assertEquals(selectedservicedetailscreen.getServiceDetailsPriceValue(), "$154.80");
		
		selectedservicedetailscreen = servicesscreen.openCustomServiceDetails(iOSInternalProjectConstants.SERVICE_PP_VEHICLE_NOT_MULTIPLE);
		selectedservicedetailscreen.setServicePriceValue("10");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.selectVehiclePart("Back Glass");
		selectedservicedetailscreen.selectVehiclePart("Deck Lid");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		selectedservicedetailscreen.saveSelectedServiceDetails();
		String alerttxt = Helpers.getAlertTextAndAccept();
		Assert.assertTrue(alerttxt.contains("You can add only one service 'Service_PP_Vehicle_not_multiple'"));
		Helpers.waitABit(1000);
		Assert.assertEquals(selectedservicedetailscreen.getServiceDetailsPriceValue(), "$163.80");
		
		selectedservicedetailscreen.changeAmountOfBundleService("163.80");
		selectedservicedetailscreen.saveSelectedServiceDetails();
		
		servicesscreen.clickSaveButton();
		myworkordersscreen.clickHomeButton();
	}
}
