package com.cyberiansoft.test.vnext.testcases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyberiansoft.test.ios_client.utils.MailChecker;
import com.cyberiansoft.test.ios_client.utils.PDFReader;
import com.cyberiansoft.test.ios_client.utils.PricesCalculations;
import com.cyberiansoft.test.vnext.screens.VNextCustomersScreen;
import com.cyberiansoft.test.vnext.screens.VNextEmailScreen;
import com.cyberiansoft.test.vnext.screens.VNextHomeScreen;
import com.cyberiansoft.test.vnext.screens.VNextInspectionServicesScreen;
import com.cyberiansoft.test.vnext.screens.VNextInspectionsMenuScreen;
import com.cyberiansoft.test.vnext.screens.VNextInspectionsScreen;
import com.cyberiansoft.test.vnext.screens.VNextInvoiceInfoScreen;
import com.cyberiansoft.test.vnext.screens.VNextInvoicesScreen;
import com.cyberiansoft.test.vnext.screens.VNextNotesScreen;
import com.cyberiansoft.test.vnext.screens.VNextPriceMatrixesScreen;
import com.cyberiansoft.test.vnext.screens.VNextSelectDamagesScreen;
import com.cyberiansoft.test.vnext.screens.VNextSelectServicesScreen;
import com.cyberiansoft.test.vnext.screens.VNextServiceDetailsScreen;
import com.cyberiansoft.test.vnext.screens.VNextVehicleInfoScreen;
import com.cyberiansoft.test.vnext.screens.VNextVehiclePartInfoPage;
import com.cyberiansoft.test.vnext.screens.VNextVehiclePartsScreen;
import com.cyberiansoft.test.vnext.screens.VNextVisualScreen;
import com.cyberiansoft.test.vnext.screens.VNextWorkOrderSummaryScreen;
import com.cyberiansoft.test.vnext.screens.VNextWorkOrdersScreen;
import com.cyberiansoft.test.vnextbo.screens.VNexBOLeftMenuPanel;
import com.cyberiansoft.test.vnextbo.screens.VNextBOInvoicesWebPage;
import com.cyberiansoft.test.vnextbo.screens.VNextBOLoginScreenWebPage;

public class VNextInvoicesTestCases  extends BaseTestCaseWithDeviceRegistrationAndUserLogin {
	
	final String testcustomer = "Retail Automation";
	final String testVIN = "1FMCU0DG4BK830800";
	
	String invoicenumbertc48094 = "";
	String invoicenumbertc46415 = "";
	final String notetext = "Invoice text note";
	
	@Test(testName= "Test Case 45878:vNext: Create Invoice with matrix service", 
			description = "Create Invoice with matrix service")
	public void testCreateInvoiceWithMatrixService() {
		final String VIN = "19UUA66278A050105";
		final String _make = "Acura";
		final String _model = "TL";
		final String color = "Red";
		final String year = "2008";
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
		final String ponumber = "123po";
		final String wopriceexp = "$267.81";
		
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextVehicleInfoScreen vehicleinfoscreen = homescreen.openCreateWOWizard(testcustomer);
		vehicleinfoscreen.populateVehicleInfoDataOnCreateWOWizard(VIN, color);
		
		Assert.assertEquals(vehicleinfoscreen.getMakeInfo(), _make);
		Assert.assertEquals(vehicleinfoscreen.getModelInfo(), _model);
		Assert.assertEquals(vehicleinfoscreen.getYear(), year);
		vehicleinfoscreen.swipeScreenLeft();
		VNextInspectionServicesScreen servicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		VNextSelectServicesScreen selectservicesscreen = servicesscreen.clickAddServicesButton();
		selectservicesscreen.selectService(percservices);
		selectservicesscreen.selectService(moneyservices);
		VNextPriceMatrixesScreen pricematrixesscreen = selectservicesscreen.openMatrixServiceDetails(matrixservice);
		selectservicesscreen = pricematrixesscreen.selectPriceMatrix(matrixsubservice);
		Assert.assertEquals(selectservicesscreen.getSelectedPriceMatrixValueForPriceMatrixService(matrixservice), matrixsubservice);
		selectservicesscreen.clickSaveSelectedServicesButton();
		servicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		VNextServiceDetailsScreen servicedetailsscreen = servicesscreen.openServiceDetailsScreen(moneyservices);
		servicedetailsscreen.setServiceAmountValue(moneyserviceprice);
		servicedetailsscreen.setServiceQuantityValue(moneyservicequant);
		servicedetailsscreen.clickServiceDetailsDoneButton();
		servicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		VNextVehiclePartsScreen vehiclepartsscreen = servicesscreen.openMatrixServiceVehiclePartsScreen(matrixservice);
		VNextVehiclePartInfoPage vehiclepartinfoscreen = vehiclepartsscreen.selectVehiclePart(vehiclepartname);
		vehiclepartinfoscreen.selectVehiclePartSize(vehiclepartsize);
		vehiclepartinfoscreen.selectVehiclePartSeverity(vehiclepartseverity);
		vehiclepartinfoscreen.selectVehiclePartAdditionalService(additionalservice);
		vehiclepartinfoscreen.clickSaveVehiclePartInfo();
		vehiclepartsscreen = new VNextVehiclePartsScreen(appiumdriver);
		servicesscreen = vehiclepartsscreen.clickVehiclePartsBackButton();
		final String wonumber = servicesscreen.getNewInspectionNumber();
		VNextWorkOrdersScreen workordersscreen = servicesscreen.saveWorkOrderViaMenu();
		final String woprice = workordersscreen.getWorkOrderPriceValue(wonumber);
		Assert.assertEquals(woprice, wopriceexp);
		VNextInvoiceInfoScreen invoiceinfoscreen = workordersscreen.clickCreateInvoiceFromWorkOrder(wonumber);
		invoiceinfoscreen.setInvoicePONumber(ponumber);
		invoiceinfoscreen.addQuickNoteToInvoice("Alum Deck");
		final String invoicenumber = invoiceinfoscreen.getInvoiceNumber();
		VNextInvoicesScreen invoicesscreen = invoiceinfoscreen.saveInvoice();
		Assert.assertEquals(invoicesscreen.getInvoicePriceValue(invoicenumber), woprice);
		homescreen = invoicesscreen.clickBackButton();
	}
	
	@Test(testName= "Test Case 46415:vNext: Create Invoice with image and text note", 
			description = "Create Invoice with matrix service")
	public void testCreateInvoiceWithTextNote() {
		final String VIN = "19UUA66278A050105";
		final String _make = "Acura";
		final String _model = "TL";
		final String color = "Red";
		final String year = "2008";
		final String wonote = "Only text Note"; 
		 
		final String ponumber = "123po";
		
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextVehicleInfoScreen vehicleinfoscreen = homescreen.openCreateWOWizard(testcustomer);
		vehicleinfoscreen.populateVehicleInfoDataOnCreateWOWizard(VIN, color);
		
		Assert.assertEquals(vehicleinfoscreen.getMakeInfo(), _make);
		Assert.assertEquals(vehicleinfoscreen.getModelInfo(), _model);
		Assert.assertEquals(vehicleinfoscreen.getYear(), year);
		
		VNextNotesScreen notesscreen = vehicleinfoscreen.clickInspectionNotesOption();
		notesscreen.setNoteText(wonote);
		notesscreen.clickNotesBackButton();
		vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
		final String wonumber = vehicleinfoscreen.getNewInspectionNumber();
		VNextWorkOrdersScreen workordersscreen = vehicleinfoscreen.saveWorkOrderViaMenu();
		Assert.assertEquals(workordersscreen.getWorkOrderPriceValue(wonumber), "$0.00");
		VNextInvoiceInfoScreen invoiceinfoscreen = workordersscreen.clickCreateInvoiceFromWorkOrder(wonumber);
		invoiceinfoscreen.setInvoicePONumber(ponumber);
		invoiceinfoscreen.addTextNoteToInvoice(notetext);
		invoicenumbertc46415 = invoiceinfoscreen.getInvoiceNumber();
		VNextInvoicesScreen invoicesscreen = invoiceinfoscreen.saveInvoice();
		Assert.assertEquals(invoicesscreen.getInvoicePriceValue(invoicenumbertc46415), "$0.00");
		homescreen = invoicesscreen.clickBackButton();
		homescreen.waitABit(60*1000);
	}
	
	@Test(testName= "Test Case 48094:vNext mobile: Create Invoice which contains price services with decimal quantity", 
			description = "Create Invoice which contains price services with decimal quantity")
	@Parameters({ "usercapi.mail", "usercapi.psw"})
	public void testCreateInvoiceWhichContainsPriceServicesWithDecimalQuantity(String usermail, String userpsw) throws IOException {
		
		final String dentdamage = "Dent"; 
		final String amountvalue = "0.99"; 
		final String moneyservicename = "Bumper Repair";
		final String insppriceexp = "1.96";
		final String ponumber = "po123";
			
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextInspectionsScreen inspectionsscreen = homescreen.clickInspectionsMenuItem();
		VNextCustomersScreen customersscreen = inspectionsscreen.clickAddInspectionButton();
		customersscreen.selectCustomer(testcustomer);
		VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
		vehicleinfoscreen.setVIN(testVIN);
		String inspnumbertc = vehicleinfoscreen.getNewInspectionNumber();
		vehicleinfoscreen.swipeScreenLeft();
		VNextVisualScreen visualscreen = new VNextVisualScreen(appiumdriver);
		VNextSelectDamagesScreen selectdamagesscreen = visualscreen.clickAddServiceButton();
		visualscreen = selectdamagesscreen.clickDefaultDamageType(dentdamage);
		visualscreen.clickCarImage();
		visualscreen.waitABit(1000);
		VNextServiceDetailsScreen servicedetailsscreen = visualscreen.clickCarImageMarker();
		servicedetailsscreen.setServiceAmountValue(amountvalue);
		Assert.assertEquals(servicedetailsscreen.getServiceAmountValue(), amountvalue);
		servicedetailsscreen.setServiceQuantityValue(amountvalue);
		servicedetailsscreen.clickServiceDetailsDoneButton();
		visualscreen = new VNextVisualScreen(appiumdriver);
		VNextInspectionServicesScreen inspservicesscreen = vehicleinfoscreen.goToInspectionServicesScreen();
		VNextSelectServicesScreen selectservicesscreen = inspservicesscreen.clickAddServicesButton();
		selectservicesscreen.selectService(moneyservicename);
		selectservicesscreen.clickSaveSelectedServicesButton();
		inspservicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		
		servicedetailsscreen = inspservicesscreen.openServiceDetailsScreen(moneyservicename);
		servicedetailsscreen.setServiceAmountValue(amountvalue);
		servicedetailsscreen.setServiceQuantityValue(amountvalue);
		servicedetailsscreen.clickServiceDetailsDoneButton();
		inspservicesscreen = new VNextInspectionServicesScreen(appiumdriver);
		inspectionsscreen = inspservicesscreen.saveInspectionViaMenu();
		Assert.assertEquals(inspectionsscreen.getInspectionPriceValue(inspnumbertc), PricesCalculations.getPriceRepresentation(insppriceexp));
		
		VNextInspectionsMenuScreen inspmenuscreen = inspectionsscreen.clickOnInspectionByInspNumber(inspnumbertc);
		vehicleinfoscreen = inspmenuscreen.clickCreateWorkOrderInspectionMenuItem();
		VNextWorkOrderSummaryScreen wosummaryscreen = vehicleinfoscreen.goToWorkOrderSummaryScreen();
		wosummaryscreen.clickCreateInvoiceOption();
		wosummaryscreen.clickWorkOrderSaveButton();
		
		VNextInvoiceInfoScreen invoiceinfoscreen = new VNextInvoiceInfoScreen(appiumdriver);
		invoiceinfoscreen.setInvoicePONumber(ponumber);
		invoicenumbertc48094  = invoiceinfoscreen.getInvoiceNumber();
		VNextInvoicesScreen invoicesscreen = invoiceinfoscreen.saveInvoice();
		Assert.assertEquals(invoicesscreen.getInvoicePriceValue(invoicenumbertc48094), PricesCalculations.getPriceRepresentation(insppriceexp));
		VNextEmailScreen emailscreen = invoicesscreen.clickOnInvoiceToEmail(invoicenumbertc48094);
		if (!emailscreen.getToEmailFieldValue().equals(usermail))
			emailscreen.sentToEmailAddress(usermail);
		
		emailscreen.clickSendEmailsButton();
		emailscreen.waitABit(60*1000);
		boolean search = false;
		final String inspectionreportfilenname = invoicenumbertc48094 + ".pdf";
		for (int i= 0; i < 5; i++) {
			if (!MailChecker.searchEmailAndGetAttachment(usermail, userpsw, "Invoice #" + invoicenumbertc48094 + " from ReconPro vNext Dev", "ReconPro@cyberiansoft.com", inspectionreportfilenname)) {
				emailscreen.waitABit(30*1000); 
			} else {
				
				search = true;
				break;
			}
		}
		if (search) {
			File pdfdoc = new File(inspectionreportfilenname);
			String pdftext = PDFReader.getPDFText(pdfdoc);
			System.out.println("++++++" + unEscapeString(pdftext));
			Assert.assertTrue(pdftext.contains("Dent Repair \n$0.98"));
			Assert.assertTrue(pdftext.contains("Bumper Repair $0.98 \n$1.96"));
		} else {
			Assert.assertTrue(search, "Can't find email with " + invoicenumbertc48094 + " inspection");
		}

		emailscreen.waitABit(60*1000);
		invoicesscreen = new VNextInvoicesScreen(appiumdriver);
	}

	@Test(testName= "Test Case 38638:vNext: Verify Customer Info on Invoice detail", 
			description = "Verify Customer Info on Invoice detail",
	dependsOnMethods = { "testCreateInvoiceWhichContainsPriceServicesWithDecimalQuantity" })
	@Parameters({ "backofficecapi.url", "usercapi.name", "usercapi.psw"})
	public void testVerifyCustomerInfoOnInvoiceDetail(String bourl, String username, String userpsw) {
		initiateWebDriver();
		webdriverGotoWebPage(bourl);
		VNextBOLoginScreenWebPage loginpage = PageFactory.initElements(webdriver,
				VNextBOLoginScreenWebPage.class);
		loginpage.userLogin(username, userpsw);
		VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
				VNexBOLeftMenuPanel.class);
		VNextBOInvoicesWebPage invoiceswebpage = leftmenu.selectInvoicesMenu();
		invoiceswebpage.selectInvoiceInTheList(invoicenumbertc48094);
		Assert.assertEquals(invoiceswebpage.getSelectedInvoiceCustomerName(), testcustomer);		
	}
	
	@Test(testName= "Test Case 38642:vNext: Verify text note on Invoice detail", 
			description = "Verify text note on Invoice detail",
	dependsOnMethods = { "testCreateInvoiceWithTextNote" })
	@Parameters({ "backofficecapi.url", "usercapi.name", "usercapi.psw"})
	public void testVerifyTextNoteOnInvoiceDetail(String bourl, String username, String userpsw) {
		initiateWebDriver();
		webdriverGotoWebPage(bourl);
		VNextBOLoginScreenWebPage loginpage = PageFactory.initElements(webdriver,
				VNextBOLoginScreenWebPage.class);
		loginpage.userLogin(username, userpsw);
		VNexBOLeftMenuPanel leftmenu = PageFactory.initElements(webdriver,
				VNexBOLeftMenuPanel.class);
		VNextBOInvoicesWebPage invoiceswebpage = leftmenu.selectInvoicesMenu();
		invoiceswebpage.selectInvoiceInTheList(invoicenumbertc46415);
		Assert.assertEquals(invoiceswebpage.getSelectedInvoiceNote(), notetext);		
	}
	
	public static String unEscapeString(String s){
	    StringBuilder sb = new StringBuilder();
	    for (int i=0; i<s.length(); i++)
	        switch (s.charAt(i)){
	            case '\n': sb.append("\\n"); break;
	            case '\t': sb.append("\\t"); break;
	            case '\r': sb.append("\\r"); break;
	            case '\f': sb.append("\\f"); break;
	            // ... rest of escape characters
	            default: sb.append(s.charAt(i));
	        }
	    return sb.toString();
	}

}