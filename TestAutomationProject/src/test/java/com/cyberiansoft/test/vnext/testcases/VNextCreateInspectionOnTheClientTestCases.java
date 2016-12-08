package com.cyberiansoft.test.vnext.testcases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyberiansoft.test.ios_client.utils.MailChecker;
import com.cyberiansoft.test.ios_client.utils.PDFReader;
import com.cyberiansoft.test.ios_client.utils.PricesCalculations;
import com.cyberiansoft.test.vnext.screens.VNextCustomersScreen;
import com.cyberiansoft.test.vnext.screens.VNextEmailScreen;
import com.cyberiansoft.test.vnext.screens.VNextHomeScreen;
import com.cyberiansoft.test.vnext.screens.VNextInspectionServicesScreen;
import com.cyberiansoft.test.vnext.screens.VNextInspectionsScreen;
import com.cyberiansoft.test.vnext.screens.VNextNewCustomerScreen;
import com.cyberiansoft.test.vnext.screens.VNextPriceMatrixesScreen;
import com.cyberiansoft.test.vnext.screens.VNextSelectDamagesScreen;
import com.cyberiansoft.test.vnext.screens.VNextSelectServicesScreen;
import com.cyberiansoft.test.vnext.screens.VNextServiceDetailsScreen;
import com.cyberiansoft.test.vnext.screens.VNextVehicleInfoScreen;
import com.cyberiansoft.test.vnext.screens.VNextVehiclePartInfoPage;
import com.cyberiansoft.test.vnext.screens.VNextVehiclePartsScreen;
import com.cyberiansoft.test.vnext.screens.VNextVisualScreen;
import com.cyberiansoft.test.vnextbo.screens.VNexBOLeftMenuPanel;
import com.cyberiansoft.test.vnextbo.screens.VNextBOInspectionInfoWebPage;
import com.cyberiansoft.test.vnextbo.screens.VNextBOInspectionsWebPage;
import com.cyberiansoft.test.vnextbo.screens.VNextBOLoginScreenWebPage;

public class VNextCreateInspectionOnTheClientTestCases extends BaseTestCaseWithDeviceRegistrationAndUserLogin {
	
	final String testcustomer = "Retail Automation";
	final String VIN = "19UUA66278A050105";
	
	String inspnumbertc47229 = "";
	String inspnumbertc46975 = "";
	String inspnumbertc47233 = "";
	
	@AfterMethod
	public void tearDown() throws Exception {
		if (webdriver != null)
			webdriver.quit();
	}
	
	@Test(testName= "Test Case 46975:vNext: Check Approved ammount is calculated correctly for Approved inspection", 
			description = "Check Approved ammount is calculated correctly for Approved inspection")
	@Parameters({ "usercapi.mail", "usercapi.psw"})
	public void testCheckApprovedAmmountIsCalculatedCorrectlyForApprovedInspection(String usermail, String userpsw) throws InterruptedException, IOException { 
		
		final String _make = "Acura";
		final String _model = "TL";
		final String _year = "2008";
		final String percservices = "Aluminum Upcharge"; 
		final String moneyservices = "Dent Repair"; 
		final String matrixservice = "Hail Dent Repair";
		final String matrixsubservice = "State Farm";
		final String moneyserviceprice = "58";
		final String moneyservicequant = "1";
		final String vehiclepartname = "Hood";
		final String vehiclepartsize = "Dime";	
		final String vehiclepartseverity = "Light 6 to 15";	
		final String additionalservice = "Aluminum Upcharge";
		final String insppriceexp = "267.81";
		
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextInspectionsScreen inspectionsscreen = homescreen.clickInspectionsMenuItem();
		VNextCustomersScreen customersscreen = inspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(testcustomer);
		VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
		vehicleinfoscreen.setVIN(VIN);
		vehicleinfoscreen.waitABit(1000);
		Assert.assertEquals(vehicleinfoscreen.getMakeInfo(), _make);
		Assert.assertEquals(vehicleinfoscreen.getModelInfo(), _model);
		Assert.assertEquals(vehicleinfoscreen.getYear(), _year);
		inspnumbertc46975 = vehicleinfoscreen.getNewInspectionNumber();
		VNextInspectionServicesScreen inspservicesscreen = vehicleinfoscreen.goToInspectionServicesScreen();
		VNextSelectServicesScreen selectservicesscreen = inspservicesscreen.clickAddServicesButton();
		selectservicesscreen.selectService(percservices);
		selectservicesscreen.selectService(moneyservices);
		VNextPriceMatrixesScreen pricematrixesscreen = selectservicesscreen.openMatrixServiceDetails(matrixservice);
		selectservicesscreen = pricematrixesscreen.selectPriceMatrix(matrixsubservice);
		Assert.assertEquals(selectservicesscreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), matrixsubservice);
		selectservicesscreen.clickSaveSelectedServicesButton();
		inspservicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		
		VNextServiceDetailsScreen servicedetailsscreen = inspservicesscreen.openServiceDetailsScreen(moneyservices);
		servicedetailsscreen.setServiceAmountValue(moneyserviceprice);
		servicedetailsscreen.setServiceQuantityValue(moneyservicequant);
		servicedetailsscreen.clickServiceDetailsDoneButton();
		inspservicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		
		VNextVehiclePartsScreen vehiclepartsscreen = inspservicesscreen.openMatrixServiceVehiclePartsScreen(matrixservice);
		VNextVehiclePartInfoPage vehiclepartinfoscreen = vehiclepartsscreen.selectVehiclePart(vehiclepartname);
		vehiclepartinfoscreen.selectVehiclePartSize(vehiclepartsize);
		vehiclepartinfoscreen.selectVehiclePartSeverity(vehiclepartseverity);
		vehiclepartinfoscreen.selectVehiclePartAdditionalService(additionalservice);
		vehiclepartinfoscreen.clickSaveVehiclePartInfo();
		vehiclepartsscreen = new VNextVehiclePartsScreen(appiumdriver);
		inspservicesscreen = vehiclepartsscreen.clickVehiclePartsBackButton();

		inspectionsscreen = inspservicesscreen.saveInspectionViaMenu();
		Assert.assertEquals(inspectionsscreen.getInspectionPriceValue(inspnumbertc46975), PricesCalculations.getPriceRepresentation(insppriceexp));
		VNextEmailScreen emailscreen = inspectionsscreen.clickOnInspectionToEmail(inspnumbertc46975);
		if (!emailscreen.getToEmailFieldValue().equals(usermail))
			emailscreen.sentToEmailAddress(usermail);
		
		emailscreen.clickSendEmailsButton();
		emailscreen.waitABit(60*1000);
		inspectionsscreen = new VNextInspectionsScreen(appiumdriver);
		
		Thread.sleep(60*1000);
		boolean search = false;
		final String inspectionreportfilenname = inspnumbertc46975 + ".pdf";
		for (int i= 0; i < 3; i++) {
			if (!MailChecker.searchEmailAndGetAttachment(usermail, userpsw, "Estimate #" + inspnumbertc46975 + " from ReconPro vNext Dev", "ReconPro@cyberiansoft.com", inspectionreportfilenname)) {
				Thread.sleep(60*1000); 
			} else {
				
				search = true;
				break;
			}
		}
		if (search) {
			File pdfdoc = new File(inspectionreportfilenname);
			String pdftext = PDFReader.getPDFText(pdfdoc);
			Assert.assertTrue(pdftext.contains(VIN));
			Assert.assertTrue(pdftext.contains(percservices));
			Assert.assertTrue(pdftext.contains(moneyservices));
			Assert.assertTrue(pdftext.contains(insppriceexp));
		} else {
			Assert.assertTrue(search, "Can't find email with " + inspnumbertc46975 + " inspection");
		}
	}
	
	@Test(testName= "Test Case 47229:vNext mobile: Create Inspection which contains breakage service with big quantity", 
			description = "Create Inspection which contains breakage service with big quantity")
	public void testCreateInspectionWhichContainsBreakageServiceWithBigQuantity() {
		
		
		final String selectdamage = "Dent";
		final String amountvalue = "1577.20";
		final String finalprice = "$1733.45";
		
		final String matrixservice = "Hail Dent Repair";
		final String pricematrix = "State Farm";
		final String vehiclepartname = "Hood";
		final String vehiclepartsize = "Dime";	
		final String vehiclepartseverity = "Light 6 to 15";	
		final String additionalservice = "Aluminum Upcharge";
		
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextInspectionsScreen inspectionsscreen = homescreen.clickInspectionsMenuItem();
		VNextCustomersScreen customersscreen = inspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(testcustomer);
		VNextVehicleInfoScreen inspinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
		inspinfoscreen.setVIN(VIN);
		inspnumbertc47229 = inspinfoscreen.getNewInspectionNumber();
		inspinfoscreen.swipeScreenLeft();
		VNextVisualScreen visualscreen = new VNextVisualScreen(appiumdriver);
		VNextSelectDamagesScreen selectdamagesscreen = visualscreen.clickAddServiceButton();
		visualscreen = selectdamagesscreen.clickDefaultDamageType(selectdamage);
		visualscreen.clickCarImage();
		visualscreen.waitABit(1000);
		VNextServiceDetailsScreen servicedetailsscreen = visualscreen.clickCarImageMarker();
		servicedetailsscreen.setServiceAmountValue(amountvalue);
		Assert.assertEquals(servicedetailsscreen.getServiceAmountValue(), amountvalue);
		servicedetailsscreen.setServiceQuantityValue("1");
		servicedetailsscreen.clickServiceDetailsDoneButton();
		visualscreen = new VNextVisualScreen(appiumdriver);
		VNextInspectionServicesScreen inspservicesscreen = inspinfoscreen.goToInspectionServicesScreen();
		VNextSelectServicesScreen selectservicesscreen = inspservicesscreen.clickAddServicesButton();
		VNextPriceMatrixesScreen pricematrixesscreen = selectservicesscreen.openMatrixServiceDetails(matrixservice);
		selectservicesscreen = pricematrixesscreen.selectPriceMatrix(pricematrix);
		Assert.assertEquals(selectservicesscreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), pricematrix);
		selectservicesscreen.clickSaveSelectedServicesButton();
		inspservicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		Assert.assertTrue(inspservicesscreen.isServiceAdded(matrixservice));
		VNextVehiclePartsScreen vehiclepartsscreen = inspservicesscreen.openMatrixServiceVehiclePartsScreen(matrixservice);
		VNextVehiclePartInfoPage vehiclepartinfoscreen = vehiclepartsscreen.selectVehiclePart(vehiclepartname);
		vehiclepartinfoscreen.selectVehiclePartSize(vehiclepartsize);
		vehiclepartinfoscreen.selectVehiclePartSeverity(vehiclepartseverity);
		vehiclepartinfoscreen.selectVehiclePartAdditionalService(additionalservice);
		vehiclepartinfoscreen.clickSaveVehiclePartInfo();
		vehiclepartsscreen = new VNextVehiclePartsScreen(appiumdriver);
		inspservicesscreen = vehiclepartsscreen.clickVehiclePartsBackButton();

		inspectionsscreen = visualscreen.saveInspectionViaMenu();
		Assert.assertEquals(inspectionsscreen.getInspectionPriceValue(inspnumbertc47229), finalprice);
		homescreen = inspectionsscreen.clickBackButton();
	}
	
	@Test(testName= "Test Case 47231:vNext mobile: Create Inspection with full populated customer info", 
			description = "Create Inspection with full populated customer info")
	public void testCreateInspectionWhithFullPopulatedCustomerInfo() {
		
		final String firstname = "CustomerFirstName";
		final String lastname = "CustomerLastName";
		final String companyname = "";
		final String customeremail = "osmak.oksana+408222@gmail.com";
		final String customerphone = "978385064";
		final String customeraddress = "";
		final String customercountry = "";
		final String customerstate = "";
		
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextInspectionsScreen inspectionsscreen = homescreen.clickInspectionsMenuItem();
		VNextCustomersScreen customersscreen = inspectionsscreen.clickAddInspectionButton();
		VNextNewCustomerScreen newcustomerscreen = customersscreen.clickAddCustomerButton();
		newcustomerscreen.createNewCustomer(firstname, lastname, companyname, customeremail, customerphone, customeraddress, customercountry, customerstate);
		
		VNextVehicleInfoScreen inspinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
		inspinfoscreen.setVIN(VIN);
		final String inspectionnumber = inspinfoscreen.getNewInspectionNumber();
		
		inspectionsscreen = inspinfoscreen.saveInspectionViaMenu();
		Assert.assertEquals(inspectionsscreen.getInspectionPriceValue(inspectionnumber), "$0.00");
		homescreen = inspectionsscreen.clickBackButton();
	}
	
	@Test(testName= "Test Case 47233:vNext mobile: Create Inspection with customer with First name only", 
			description = "Create Inspection with customer with First name only")
	public void testCreateInspectionWhithCustomerWithFirstNameOnly() {
		
		final String firstname = "CustomerFirstName";
		final String lastname = "";
		final String companyname = "";
		final String customeremail = "osmak.oksana+408222@gmail.com";
		final String customerphone = "978385064";
		final String customeraddress = "";
		final String customercountry = "";
		final String customerstate = "";
		
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextInspectionsScreen inspectionsscreen = homescreen.clickInspectionsMenuItem();
		VNextCustomersScreen customersscreen = inspectionsscreen.clickAddInspectionButton();
		VNextNewCustomerScreen newcustomerscreen = customersscreen.clickAddCustomerButton();
		newcustomerscreen.createNewCustomer(firstname, lastname, companyname, customeremail, customerphone, customeraddress, customercountry, customerstate);
		
		VNextVehicleInfoScreen inspinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
		inspinfoscreen.setVIN(VIN);
		inspnumbertc47233 = inspinfoscreen.getNewInspectionNumber();
		
		inspectionsscreen = inspinfoscreen.saveInspectionViaMenu();
		Assert.assertEquals(inspectionsscreen.getInspectionPriceValue(inspnumbertc47233), "$0.00");
		homescreen = inspectionsscreen.clickBackButton();
	}
	
	@Test(testName= "Test Case 40794:vNext: verify displaying inspection which contains breakage service with big quantity", 
			description = "Verify displaying inspection which contains breakage service with big quantity",
			dependsOnMethods = { "testCreateInspectionWhichContainsBreakageServiceWithBigQuantity" })
	@Parameters({ "backofficecapi.url", "usercapi.name", "usercapi.psw"})
	public void testVerifyDisplayingInspectionWhichContainsBreakageServiceWithBigQuantity(String bourl, String username, String userpsw) {
		
		initiateWebDriver();
		webdriverGotoWebPage(bourl);
		VNextBOLoginScreenWebPage loginpage = PageFactory.initElements(webdriver,
				VNextBOLoginScreenWebPage.class);
		loginpage.userLogin(username, userpsw);
		VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
				VNexBOLeftMenuPanel.class);
		VNextBOInspectionsWebPage inspectionspage = leftmenu.selectInspectionsMenu();
		inspectionspage.selectInspectionInTheList(inspnumbertc47229);
		Assert.assertEquals(inspectionspage.getSelectedInspectionCustomerName(), testcustomer);
		Assert.assertTrue(inspectionspage.isServicePresentForSelectedInspection("Dent Repair"));
		Assert.assertTrue(inspectionspage.isServicePresentForSelectedInspection("Hail Dent Repair"));
		Assert.assertTrue(inspectionspage.isImageLegendContainsBreakageIcon("Hail Damage"));
		Assert.assertTrue(inspectionspage.isImageLegendContainsBreakageIcon("Dent"));
	}
	
	@Test(testName= "Test Case 47236:vNext: verify displaying approved amount for Inspection", 
			description = "Verify displaying approved amount for Inspection",
			dependsOnMethods = { "testCheckApprovedAmmountIsCalculatedCorrectlyForApprovedInspection" })
	@Parameters({ "backofficecapi.url", "usercapi.name", "usercapi.psw"})
	public void testVerifyDisplayingApprovedAmountForInspection(String bourl, String username, String userpsw) {
		
		final String insppriceexp = "$ 267.81";
		
		initiateWebDriver();
		webdriverGotoWebPage(bourl);
		VNextBOLoginScreenWebPage loginpage = PageFactory.initElements(webdriver,
				VNextBOLoginScreenWebPage.class);
		loginpage.userLogin(username, userpsw);
		VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
				VNexBOLeftMenuPanel.class);
		VNextBOInspectionsWebPage inspectionspage = leftmenu.selectInspectionsMenu();
		inspectionspage.selectInspectionInTheList(inspnumbertc46975);
		Assert.assertEquals(inspectionspage.getSelectedInspectionTotalAmauntValue(), insppriceexp);
		String mainWindowHandle = webdriver.getWindowHandle();	
		
		VNextBOInspectionInfoWebPage inspectioninfopage = inspectionspage.clickSelectedInspectionPrintIcon();
		System.out.println("++++++" +  inspectioninfopage.getInspectionTotalPriceValue());
		inspectioninfopage.closeNewTab(mainWindowHandle);
	}
	
	@Test(testName= "Test Case 47237:vNext: verify displaying Inspection with customer with First name only", 
			description = "Verify displaying inspection with customer with First name only",
			dependsOnMethods = { "testCreateInspectionWhithCustomerWithFirstNameOnly" })
	@Parameters({ "backofficecapi.url", "usercapi.name", "usercapi.psw"})
	public void testVerifyDisplayingInspectionWithCustomerWithFirstNameOnly(String bourl, String username, String userpsw) {
		final String firstname = "CustomerFirstName";
		
		initiateWebDriver();
		webdriverGotoWebPage(bourl);
		VNextBOLoginScreenWebPage loginpage = PageFactory.initElements(webdriver,
				VNextBOLoginScreenWebPage.class);
		loginpage.userLogin(username, userpsw);
		VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
				VNexBOLeftMenuPanel.class);
		VNextBOInspectionsWebPage inspectionspage = leftmenu.selectInspectionsMenu();
		inspectionspage.selectInspectionInTheList(inspnumbertc47233);
		Assert.assertEquals(inspectionspage.getSelectedInspectionCustomerName(), firstname);
	}

}