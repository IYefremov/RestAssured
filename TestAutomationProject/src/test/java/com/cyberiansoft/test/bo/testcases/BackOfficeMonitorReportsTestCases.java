package com.cyberiansoft.test.bo.testcases;

import com.cyberiansoft.test.baseutils.WebDriverUtils;
import com.cyberiansoft.test.bo.config.BOConfigInfo;
import com.cyberiansoft.test.bo.pageobjects.webpages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackOfficeMonitorReportsTestCases extends BaseTestCase {

    private BackOfficeHeaderPanel backOfficeHeader;

    @BeforeMethod
    public void BackOfficeLogin(Method method) {
        System.out.printf("\n* Starting test : %s Method : %s\n", getClass(), method.getName());
        WebDriverUtils.webdriverGotoWebPage(BOConfigInfo.getInstance().getBackOfficeURL());
        BackOfficeLoginWebPage loginPage = PageFactory.initElements(webdriver, BackOfficeLoginWebPage.class);
        loginPage.UserLogin(BOConfigInfo.getInstance().getUserName(), BOConfigInfo.getInstance().getUserPassword());
        backOfficeHeader = PageFactory.initElements(webdriver, BackOfficeHeaderPanel.class);
    }

    @AfterMethod
    public void BackOfficeLogout() {
        backOfficeHeader.clickLogout();
    }

    //todo fails the whole class
	@Test(testName="Average Repair Time Report preconditions", description = "Average Repair Time Report preconditions")
	public void testOperationInvoiceSearch() throws InterruptedException {

        final String repairlocationname = "Time_Reports_01";
		final String approxrepairtime = "8.0";
		final String workingday = "Monday";
		final String starttime = "9:00 AM";
		final String finishtime = "5:00 PM";
		final String WOType = "01ZalexWO_tp";
		
		backOfficeHeader
                .clickMonitorLink()
                .clickRepairLocationsLink()
		        .addNewRepairLocation(repairlocationname, approxrepairtime, workingday, starttime, finishtime, true)
                .makeSearchPanelVisible()
		        .setSearchLocation(repairlocationname)
		        .clickFindButton()
		        .addPhaseForRepairLocation(repairlocationname, "Start", "Pre-Repair", approxrepairtime, approxrepairtime, true)
		        .addPhaseForRepairLocation(repairlocationname, "On Hold", "On Hold", "111.0", "111.0", false)
                .assignServiceForRepairLocation(repairlocationname, WOType, "AMoneyFlatFee_VacuumCleaner", "On Hold")
		        .assignServiceForRepairLocation(repairlocationname, WOType, "AMoneyVehicleFF_Washing", "Start");

        MonitorWebPage monitorpage = backOfficeHeader.clickMonitorLink();
        VendorsTeamsWebPage vendorsteamspage = monitorpage.clickVendorsTeamsLink();
        vendorsteamspage.createNewVendorTeam("02_TimeRep_team", "Pacific Standard Time", "TF145", "ClockIn / ClockOut", repairlocationname, repairlocationname);
	}
	
	@Test(testName="Test Case 25488:Monitor- Reports - Average Repair Time Report (Detailed automation - Part 1)", description = "Monitor- Reports - Average Repair Time Report (Detailed automation - Part 1)")
	public void testMonitorReportsAverageRepairTimeReport_Part1() throws Exception {
		
		final String addsrvalue = "01_Alex2SRT";
		final String VIN = "WAUBC044XMN771407";
		final String _make = "Audi";
		final String _model = "100";
		final String newservicerequest= "SASHAZ";
		
		
		OperationsWebPage operationsPage = backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage servicerequestslistpage = operationsPage.clickNewServiceRequestLink();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(addsrvalue);
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(newservicerequest);
		servicerequestslistpage.clickDoneButton();
		
		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(VIN);
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(_make, _model);
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(VIN);
		servicerequestslistpage.clickFindButton();
		String wonumber = servicerequestslistpage.getWOForFirstServiceRequestFromList();
		
		MonitorWebPage monitorpage = backOfficeHeader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.makeSearchPanelVisible();
//		repairorderspage.selectSearchLocation("Time_Reports_01");
		repairorderspage.setSearchWoNumber(wonumber);
		repairorderspage.clickFindButton();
		
		Assert.assertTrue(repairorderspage.isRepairOrderPresentInTable(wonumber));
		
		monitorpage = backOfficeHeader.clickMonitorLink();
		AverageRepairTimeReportWebPage averagerepairtimereportpage = monitorpage.clickAverageRepairTimeReportLink();
		averagerepairtimereportpage.makeSearchPanelVisible();
		averagerepairtimereportpage.selectSearchLocation("Time_Reports_01");
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");

		 LocalDateTime now = LocalDateTime.now();
		 LocalDateTime then = now.minusDays(7);
		 LocalDateTime after = now.plusDays(1);
		 averagerepairtimereportpage.setSearchFromDate(then.format(format));
		 averagerepairtimereportpage.setSearchToDate(after.format(format));
		        
		averagerepairtimereportpage.clickFindButton();
		//Assert.assertFalse(averagerepairtimereportpage.isLocationResultsPresent("Time_Reports_01"));
		
		PrintWriter writer = new PrintWriter("data/averagerpairtimewonubers.txt", "UTF-8");
		writer.println(wonumber);
		writer.close();
	}
	
	@Test(testName="Test Case 25524:Monitor- Reports - Average Repair Time Report (Detailed automation - Part 2)", description = "Monitor- Reports - Average Repair Time Report (Detailed automation - Part 2)")
	public void testMonitorReportsAverageRepairTimeReport_Part2() {
		
		final String VIN = "WAUBC044XMN771407";
		final String _make = "Audi";
		final String _model = "100";
		final String _year = "1991";
		//final String wonumber = "O-000-01895";
		
		/*BufferedReader in = new BufferedReader(new FileReader("data/averagerpairtimewonubers.txt"));
		String wonumber = in.readLine();
		
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.makeSearchPanelVisible();
		repairorderspage.selectSearchLocation("Time_Reports_01");
		repairorderspage.setSearchWoNumber(wonumber);
		repairorderspage.clickFindButton();
		
		repairorderspage.verifyTableOderTypeColumnValuesAreVisible(wonumber);
		VendorOrderServicesWebPage vendorordersservicespage = repairorderspage.clickOnWorkOrderLinkInTable(wonumber);
		vendorordersservicespage.setStartPhaseStatus("Completed");
		vendorordersservicespage.setServicesStatus("Completed");
		repairorderspage = vendorordersservicespage.clickBackToROLink();*/
		
		
		MonitorWebPage monitorpage = backOfficeHeader.clickMonitorLink();
		AverageRepairTimeReportWebPage averagerepairtimereportpage = monitorpage.clickAverageRepairTimeReportLink();
		averagerepairtimereportpage.makeSearchPanelVisible();
		averagerepairtimereportpage.selectSearchLocation("Time_Reports_01");
		averagerepairtimereportpage.selectSearchWOType("01ZalexWO_tp");
		
		DateTimeFormatter formatting =
		            DateTimeFormatter.ofPattern("MM/dd/yyyy");

		 LocalDateTime now = LocalDateTime.now();
		 LocalDateTime then = now.minusDays(5);
		 LocalDateTime after = now.plusDays(2);
		 averagerepairtimereportpage.setSearchFromDate(then.format(formatting));
		 averagerepairtimereportpage.setSearchToDate(after.format(formatting));
		        
		averagerepairtimereportpage.clickFindButton();
		//todo fails here
		averagerepairtimereportpage.verifySearchResults("Time_Reports_01", "01ZalexWO_tp");
		
		averagerepairtimereportpage.checkShowDetails();
		averagerepairtimereportpage.clickFindButton();
		averagerepairtimereportpage.verifyDetailReportSearchResults("Time_Reports_01", "01ZalexWO_tp", VIN, _make, _model, _year);
	}
	
	@Test(testName="Test Case 25578:Monitor- Reports - Repair Location Time Tracking(Detailed automation - Part 1)", description = "Monitor- Reports - Repair Location Time Tracking(Detailed automation - Part 1)")
	public void testMonitorReportsRepairLocationTimeTracking_Part1() throws Exception {
		
		final String addsrvalue = "01_Alex2SRT";
		final String VIN = "WAUBC044XMN771407";
		final String _make = "Audi";
		final String _model = "100";
		final String newservicerequest= "SASHAZ";
		

		OperationsWebPage operatonspage = backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage servicerequestslistpage = operatonspage.clickNewServiceRequestLink();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(addsrvalue);
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(newservicerequest);
		servicerequestslistpage.clickDoneButton();
		
		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(VIN);
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(_make, _model);
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(VIN);
		servicerequestslistpage.clickFindButton();
		String wonumber = servicerequestslistpage.getWOForFirstServiceRequestFromList();
		
		MonitorWebPage monitorpage = backOfficeHeader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.makeSearchPanelVisible();
		repairorderspage.selectSearchLocation("Time_Reports_01");
		repairorderspage.setSearchWoNumber(wonumber);
        //todo fails here
		repairorderspage.clickFindButton();
		Assert.assertTrue(repairorderspage.isRepairOrderPresentInTable(wonumber));
		
		monitorpage = backOfficeHeader.clickMonitorLink();
		AverageRepairTimeReportWebPage averagerepairtimereportpage = monitorpage.clickAverageRepairTimeReportLink();
		averagerepairtimereportpage.makeSearchPanelVisible();
		averagerepairtimereportpage.selectSearchLocation("Time_Reports_01");
		
		DateTimeFormatter format =
		            DateTimeFormatter.ofPattern("d/MM/yyyy");

		 LocalDateTime now = LocalDateTime.now();
		 LocalDateTime then = now.minusDays(7);
		 LocalDateTime after = now.plusDays(1);
		 averagerepairtimereportpage.setSearchFromDate(then.format(format));
		 averagerepairtimereportpage.setSearchToDate(after.format(format));
		        
		averagerepairtimereportpage.clickFindButton();
		//Assert.assertFalse(averagerepairtimereportpage.isLocationResultsPresent("Time_Reports_01"));
		
		PrintWriter writer = new PrintWriter("data/repairlocationtimetrackingwonubers.txt", "UTF-8");
		writer.println(wonumber);
		writer.close();
	}
	
	@Test(testName="Test Case 25579:Monitor- Reports - Repair Location Time Tracking (Detailed automation - Part 2)", description = "Monitor- Reports - Repair Location Time Tracking (Detailed automation - Part 2)")
	public void testMonitorReportsRepairLocationTimeTracking_Part2() throws Exception {
		
		final String VIN = "WAUBC044XMN771407";
		final String _make = "Audi";
		final String _model = "100";
		final String _year = "1991";
		
		BufferedReader in = new BufferedReader(new FileReader("data/repairlocationtimetrackingwonubers.txt"));
		String wonumber = in.readLine();
		
		MonitorWebPage monitorpage = backOfficeHeader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.makeSearchPanelVisible();
		repairorderspage.selectSearchLocation("Time_Reports_01");
		repairorderspage.setSearchWoNumber(wonumber);
        //todo fails here
		repairorderspage.clickFindButton();
		
		Assert.assertTrue(repairorderspage.isRepairOrderPresentInTable(wonumber));
		VendorOrderServicesWebPage vendorordersservicespage = repairorderspage.clickOnWorkOrderLinkInTable(wonumber);
		vendorordersservicespage.setServicesStatus("Completed");
		repairorderspage = vendorordersservicespage.clickBackToROLink();
		
		monitorpage = backOfficeHeader.clickMonitorLink();
		RepairLocationTimeTrackingWebPage repairlocationtimetrackingpage = monitorpage.clickRepairLocationTimeTrackingLink();
		repairlocationtimetrackingpage.makeSearchPanelVisible();
		repairlocationtimetrackingpage.selectSearchLocation("Time_Reports_01");
		
		DateTimeFormatter format =
		            DateTimeFormatter.ofPattern("MM/dd/yyyy");

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.minusDays(5);
		LocalDateTime after = now.plusDays(2);		 
		 
		repairlocationtimetrackingpage.setSearchFromDate(then.format(format));
		repairlocationtimetrackingpage.setSearchToDate(after.format(format));
		        
		repairlocationtimetrackingpage.clickFindButton();
		
		Assert.assertTrue(repairlocationtimetrackingpage.searchWorkOrderInTable(wonumber));
	}

}