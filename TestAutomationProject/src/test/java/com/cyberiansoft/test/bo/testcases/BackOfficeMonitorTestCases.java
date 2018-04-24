package com.cyberiansoft.test.bo.testcases;

import com.cyberiansoft.test.bo.pageobjects.webpages.*;
import com.cyberiansoft.test.bo.utils.WebConstants;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BackOfficeMonitorTestCases extends BaseTestCase {

	@Test(testName = "Test Case 15266:Monitor-Repair Order: Search", description = "Monitor-Repair Order: Search")
	public void testMonitorRepairOrderSearch() throws Exception {

		final String wonumber = "O-000-149273";

		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);

		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();

		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.repairOrdersTableIsVisible();
		repairorderspage.verifyRepairOrdersTableColumnsAreVisible();

		repairorderspage.selectSearchLocation("Default Location");
		repairorderspage.selectSearchTimeframe("Last Year");
		repairorderspage.clickFindButton();
		Assert.assertEquals("1", repairorderspage.getCurrentlySelectedPageNumber());
		Assert.assertEquals("1", repairorderspage.getGoToPageFieldValue());

		String lastpagenumber = repairorderspage.getLastPageNumber();
		repairorderspage.clickGoToLastPage();
		Assert.assertEquals(lastpagenumber, repairorderspage.getGoToPageFieldValue());

		repairorderspage.clickGoToFirstPage();
		Assert.assertEquals("1", repairorderspage.getGoToPageFieldValue());

		repairorderspage.clickGoToNextPage();
		Assert.assertEquals("2", repairorderspage.getGoToPageFieldValue());

		repairorderspage.clickGoToPreviousPage();
		Assert.assertEquals("1", repairorderspage.getGoToPageFieldValue());

		repairorderspage.setPageSize("1");
		Assert.assertEquals(1, repairorderspage.getRepairOrdersTableRowCount());
		//String numberofrows = repairorderspage.getLastPageNumber();

		repairorderspage.setPageSize("999");
		Assert.assertEquals(new Integer(50), Integer.valueOf(repairorderspage.getRepairOrdersTableRowCount()));

		repairorderspage.selectSearchLocation("Default Location");
		Thread.sleep(1000);
		repairorderspage.selectSearchCustomer("000 15.11 Companey");
		repairorderspage.setSearchVIN("1GCPKSE70CF109506");
		repairorderspage.setSearchWoNumber(wonumber);
		repairorderspage.clickFindButton();
		Assert.assertEquals(Integer.valueOf(1), Integer.valueOf(repairorderspage.getRepairOrdersTableRowCount()));
		Assert.assertTrue(repairorderspage.isRepairOrderPresentInTable(wonumber));
		repairorderspage.verifyTableCustomerAndVinColumnValuesAreVisible("000 15.11 Companey", "1GCPKSE70CF109506");

	}

	@Test(testName = "Test Case 15724:Monitor - Vendor Orders: Search", description = "Monitor - Vendor Orders: Search")
	public void testMonitorVendorOrdersSearch() throws Exception {

		final String wonum = "O-000-149273";
		final String company = "000 15.11 Companey";
		final String VIN = "1GCPKSE70CF109506";
		final String vendor = "Test Team";
		final String deflocation = "Default Location";

		final String servicestatus = "All Services";
		final String employee = "Oksi Manager";

		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);

		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();

		VendorOrdersWebPage vendororderspage = monitorpage.clickVendorOrdersLink();
		vendororderspage.selectSearchTimeFrame(WebConstants.TimeFrameValues.TIMEFRAME_LASTYEAR);
		vendororderspage.selectSearchServiceStatus(servicestatus);
		vendororderspage.clickFindButton();
		vendororderspage.repairOrdersTableIsVisible();
		vendororderspage.verifyVendorOrdersTableColumnsAreVisible();
		Assert.assertEquals("1", vendororderspage.getCurrentlySelectedPageNumber());
		Assert.assertEquals("1", vendororderspage.getGoToPageFieldValue());

		String lastpagenumber = vendororderspage.getLastPageNumber();
		vendororderspage.clickGoToLastPage();
		Assert.assertEquals(lastpagenumber, vendororderspage.getGoToPageFieldValue());

		vendororderspage.clickGoToFirstPage();
		Assert.assertEquals("1", vendororderspage.getGoToPageFieldValue());

		vendororderspage.clickGoToNextPage();
		Assert.assertEquals("2", vendororderspage.getGoToPageFieldValue());

		vendororderspage.clickGoToPreviousPage();
		Assert.assertEquals("1", vendororderspage.getGoToPageFieldValue());

		vendororderspage.setPageSize("1");
		Assert.assertEquals(1, vendororderspage.getRepairOrdersTableRowCount());

		vendororderspage.setPageSize("999");
		Assert.assertEquals(new Integer(50), Integer.valueOf(vendororderspage.getRepairOrdersTableRowCount()));
		
		vendororderspage.selectSearchLocation(deflocation);
		vendororderspage.selectSearchVendor(vendor);
		vendororderspage.setSearchVIN(VIN); 
		vendororderspage.selectSearchCustomer(company);
		//vendororderspage.selectSearchEmployee("Vitaly Lyashenko (VL)");
		//vendororderspage.setSearchRoNumber("fhfhd3646");
		vendororderspage.setSearchWorkorderNumber(wonum);
		
		vendororderspage.clickFindButton();
		Assert.assertEquals(Integer.valueOf(1), Integer.valueOf(vendororderspage.getRepairOrdersTableRowCount()));
		Assert.assertEquals(vendor, vendororderspage.getTeamVendorForVendorOrder(wonum));
		Assert.assertEquals(company, vendororderspage.getCustomerForVendorOrder(wonum));
		
		vendororderspage.isVendorOrderExists(wonum);

		//blank pages
	//	vendororderspage.openOrderNoInformationWindowAndVerifyContent(wonum, VIN, company, employee);
	//	vendororderspage.openServicesInformationByOrderNoWindowAndVerifyContent(wonum, VIN, company);
	}


	//todo fails Friday. Lilia, needs to be discussed
	@Test(testName = "Test Case 15726:Monitor- Reports - Average Repair Time Report", description = "Monitor- Reports - Average Repair Time Report")
	public void testMonitorReportsAverageRepairTimeReport() {

		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		
		AverageRepairTimeReportWebPage averagerepairtimereportpage = monitorpage.clickAverageRepairTimeReportLink();
		averagerepairtimereportpage.makeSearchPanelVisible();
		Assert.assertTrue(averagerepairtimereportpage.searchPanelIsExpanded());
		averagerepairtimereportpage.selectSearchLocation("Default Location");
		averagerepairtimereportpage.selectSearchWOType("Lilia");
		averagerepairtimereportpage.setSearchFromDate("3/1/2013");
		averagerepairtimereportpage.clickFindButton();
		averagerepairtimereportpage.verifySearchResults("Default Location", "Lilia");
	}

	//todo fails friday. The WO numbers are no more valid. No WO are displayed in Default Location section
	@Test(testName = "Test Case 15727:Monitor- Reports - Repair Location Time Tracking", description = "Monitor- Reports - Repair Location Time Tracking")
	public void testMonitorReportsRepairLocationTimeTracking() {

		String[] wonumbersfirstpage = {"O-10000-00090", "O-10000-00091", "O-10000-00106", "O-10000-00107" };
		String[] wonumberslastpage = {"O-000-01595"};
		String[] wonumberssecondpage = {"O-10000-00118", "O-10000-00124", "O-10000-00125", "O-10000-00128"};
		
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		
		RepairLocationTimeTrackingWebPage repairlocationtimetrackingpage = monitorpage.clickRepairLocationTimeTrackingLink();
		
		repairlocationtimetrackingpage.makeSearchPanelVisible();
		Assert.assertTrue(repairlocationtimetrackingpage.searchPanelIsExpanded());
		repairlocationtimetrackingpage.selectSearchLocation("Default Location");
		repairlocationtimetrackingpage.setSearchFromDate("1/1/2015");
		repairlocationtimetrackingpage.setSearchToDate("8/31/2018");

		repairlocationtimetrackingpage.clickFindButton();
		repairlocationtimetrackingpage.verifySearchResults(wonumbersfirstpage);
		repairlocationtimetrackingpage.clickGoToLastPage();
		repairlocationtimetrackingpage.verifySearchResults(wonumberslastpage);
		
		repairlocationtimetrackingpage.clickGoToFirstPage();
		repairlocationtimetrackingpage.verifySearchResults(wonumbersfirstpage);
		
		repairlocationtimetrackingpage.clickGoToNextPage();
		repairlocationtimetrackingpage.verifySearchResults(wonumberssecondpage);
		
		repairlocationtimetrackingpage.clickGoToPreviousPage();
		repairlocationtimetrackingpage.verifySearchResults(wonumbersfirstpage);
		
	}

    //todo fails friday NO data available
	@Test(testName = "Test Case 15728:Monitor- Reports - Trending Report", description = "Monitor- Reports - Trending Report")
	public void testMonitorReportsTrendingReport() {

		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		
		TrendingReportWebPage trendingreportpage = monitorpage.clickTrendingReportLink();
		
		//Assert.assertTrue(trendingreportpage.searchPanelIsExpanded());
		trendingreportpage.selectSearchLocation("Default Location");
		trendingreportpage.selectSearchWOType("Artem order test");
		trendingreportpage.setSearchFromDate("Jan", "2015");
		trendingreportpage.setSearchToDate("Aug", "2015");
		
		trendingreportpage.clickFindButton();
		trendingreportpage.verifySearchResults("Default Location", "Artem order test");
	}

	@Test(description = "Test Case 15948:Monitor-Repair Order: Full Display Version")
	public void testMonitorRepairOrderFullDisplayVersion() throws Exception {

		final String repairorderlocation = "Default Location";
		final String repairordertimeframe = "Last Year";
		
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		
		repairorderspage.makeSearchPanelVisible();
		repairorderspage.searchPanelIsExpanded();
		repairorderspage.searchPanelIsVisible();
		repairorderspage.selectSearchLocation(repairorderlocation);
		repairorderspage.selectSearchTimeframe(repairordertimeframe);
		repairorderspage.clickFindButton();
		repairorderspage.openFullDisplayWOMonitorandVerifyContent();
	}
	
	@Test(testName = "Test Case 28379:Monitor - Verify \"On Hold\" Reason at RO", description = "Monitor - Verify \"On Hold\" Reason at RO")
	public void testMonitorVerifyOnHoldReasonAtRO() {
		
		final String orderstatus = "On Hold";
		final String orderstatusreason = "testreason";
		
		final String repairorderlocation = "Default Location";
		final String repairordertimeframe = "Last Year";
		final String repairordernumber = "O-062-00168";
		
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		MonitorSettingsWebPage monitorsettingspage = monitorpage.clickMonitorSettingsLink();	
		if (monitorsettingspage.isOrderStatusReasonExists(orderstatusreason)) {
			monitorsettingspage.deleteOrderStatusReason(orderstatusreason);
		}		
		monitorsettingspage.createNewOrderStatusReason(orderstatus, orderstatusreason);
		
		monitorpage = backofficeheader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.makeSearchPanelVisible();
		repairorderspage.selectSearchLocation(repairorderlocation);
		repairorderspage.selectSearchTimeframe(repairordertimeframe);
		repairorderspage.setSearchWoNumber(repairordernumber);
		repairorderspage.clickFindButton();
		
		VendorOrderServicesWebPage vendororderservicespage = repairorderspage.clickOnWorkOrderLinkInTable(repairordernumber);
		vendororderservicespage.selectRepairOrderStatus(orderstatus);
		vendororderservicespage.selectRepairOrderReason(orderstatusreason);
		vendororderservicespage.clickBackToROLink();
		
		backofficeheader.clickMonitorLink();
		monitorsettingspage = monitorpage.clickMonitorSettingsLink();
		monitorsettingspage.deleteOrderStatusReason(orderstatusreason);
	}

	@Test(testName = "Test Case 28380:Monitor - Verify \"Closed\" Reason at RO", description = "Monitor - Verify \"Closed\" Reason at RO")
	public void testMonitorVerifyClosedReasonAtRO() {
		
		final String orderstatus = "Closed";
		final String orderstatusreason = "testreason";
		
		final String repairorderlocation = "Default Location";
		final String repairordertimeframe = "Last Year";
		final String repairordernumber = "O-062-00168";
		
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		MonitorSettingsWebPage monitorsettingspage = monitorpage.clickMonitorSettingsLink();	
		if (monitorsettingspage.isOrderStatusReasonExists(orderstatusreason)) {
			monitorsettingspage.deleteOrderStatusReason(orderstatusreason);
		}		
		monitorsettingspage.createNewOrderStatusReason(orderstatus, orderstatusreason);
		
		monitorpage = backofficeheader.clickMonitorLink();
		RepairOrdersWebPage repairorderspage = monitorpage.clickRepairOrdersLink();
		repairorderspage.makeSearchPanelVisible();
		repairorderspage.selectSearchLocation(repairorderlocation);
		repairorderspage.selectSearchTimeframe(repairordertimeframe);
		repairorderspage.setSearchWoNumber(repairordernumber);
		repairorderspage.clickFindButton();
		
		VendorOrderServicesWebPage vendororderservicespage = repairorderspage.clickOnWorkOrderLinkInTable(repairordernumber);
		vendororderservicespage.selectRepairOrderStatus(orderstatus);
		vendororderservicespage.selectRepairOrderReason(orderstatusreason);
		vendororderservicespage.clickBackToROLink();
		
		backofficeheader.clickMonitorLink();
		monitorsettingspage = monitorpage.clickMonitorSettingsLink();
		monitorsettingspage.deleteOrderStatusReason(orderstatusreason);
	}
	
	@Test(testName = "Test Case 28378:Monitor - Monitor Settings: CRUD", description = "Monitor - Monitor Settings: CRUD")
	public void testMonitorSettingsCRUD() {
		
		final String orderstatus = "On Hold";
		final String orderstatusreason = "testreasoncrud";
		
		final String orderstatused = "Closed";
		final String orderstatusreasoned = "testreasoncrud2";
		
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		MonitorSettingsWebPage monitorsettingspage = monitorpage.clickMonitorSettingsLink();
		if (monitorsettingspage.isOrderStatusReasonExists(orderstatusreason)) {
			monitorsettingspage.deleteOrderStatusReason(orderstatusreason);
		}	
		if (monitorsettingspage.isOrderStatusReasonExists(orderstatusreasoned)) {
			monitorsettingspage.deleteOrderStatusReason(orderstatusreasoned);
		}
		monitorsettingspage.createNewOrderStatusReason(orderstatus, orderstatusreason);
		monitorsettingspage.clickEditOrderStatusReason(orderstatusreason);
		Assert.assertEquals(orderstatus, monitorsettingspage.getNewOrderStatus());
		
		monitorsettingspage.setNewOrderStatusReason(orderstatusreasoned);
		monitorsettingspage.selectNewOrderStatus(orderstatused);
		monitorsettingspage.clickNewOrderStatusReasonCancelButton();
		
		monitorsettingspage.clickEditOrderStatusReason(orderstatusreason);
		Assert.assertEquals(orderstatus, monitorsettingspage.getNewOrderStatus());
		
		monitorsettingspage.setNewOrderStatusReason(orderstatusreasoned);
		monitorsettingspage.selectNewOrderStatus(orderstatused);
		monitorsettingspage.clickNewOrderStatusReasonOKButton();
		
		monitorsettingspage.clickEditOrderStatusReason(orderstatusreasoned);
		Assert.assertEquals(orderstatused, monitorsettingspage.getNewOrderStatus());
		monitorsettingspage.clickNewOrderStatusReasonCancelButton();
		
		monitorsettingspage.deleteOrderStatusReason(orderstatusreasoned);
	}
	
	@Test(testName = "Test Case 64914:Monitor - Monitor Settings: Employee Role Settings")
	public void testMonitorSettingsEmployeeRoleSettings(){
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		MonitorSettingsWebPage monitorsettingspage = monitorpage.clickMonitorSettingsLink();
		Assert.assertTrue(monitorsettingspage.checkPresentanceOfTabs("Order Status Reasons","Flags"));
		Assert.assertTrue(monitorsettingspage.checkEmployeeRoleSettingsGridColumnsAndRows());
		Assert.assertTrue(monitorsettingspage.checkEmployeeRoleSettingsGridOnOfFieldsAbility());
	}

	@Test(testName = "Test Case 64965:Monitor - Kanban: Auto Refresh ON OFF")
	public void checkMonitorKanbanAutoRefresh() throws InterruptedException {
		BackOfficeHeaderPanel backofficeHeader = PageFactory.initElements(webdriver, BackOfficeHeaderPanel.class);
		MonitorWebPage monitorPage = backofficeHeader.clickMonitorLink();
		KanbanWebPage kanbanPage = monitorPage.clickKanbanLink();
		kanbanPage.setSearchLocation("Default Location");
		kanbanPage.clickSearchButton();
		Assert.assertTrue(kanbanPage.checkSearhResultColumns());
		Assert.assertFalse(kanbanPage.checkIntervalFieldLessThan(5));
		Assert.assertFalse(kanbanPage.checkIntervalFieldOverThan(720));
		Assert.assertFalse(kanbanPage.checkIntervalFieldInputSymbol("a"));
		Assert.assertTrue(kanbanPage.checkIntervalField(6));
	}

	//test skipped cause of no test data
	//@Test(testName = "Test Case 65435:Monitor: Reports - Active Vehicles by Phase Subscriptions")
	public void checkMonitorReportsActiveVechiclesByPhaseSubscriptions() throws InterruptedException{
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		ActiveVechicleByPhaseWebPage activeVechicleByPhasePage = monitorpage.clickActiveVehiclesByPhaseLink();
		Assert.assertTrue(activeVechicleByPhasePage.checkSearchFields());
		activeVechicleByPhasePage.setLocationFilter("ALM - Recon Facility");
		Assert.assertTrue(activeVechicleByPhasePage.checkTimeFrameField("180"));
		Assert.assertTrue(activeVechicleByPhasePage.checkPhasesInRowCheckBox());
		activeVechicleByPhasePage.clickFindButton();
		Assert.assertTrue(activeVechicleByPhasePage.checkSearchResults("ALM - Recon Facility"));
		
		activeVechicleByPhasePage.setPhase1("PDR Station");
		activeVechicleByPhasePage.setPhase2("PDI");
		activeVechicleByPhasePage.setStatuses1("Active");
		activeVechicleByPhasePage.setStatuses2("Queued","Active","Completed","Audited","Refused","Rework","Skipped");
		activeVechicleByPhasePage.clickPhasesInRow();
		activeVechicleByPhasePage.clickFindButton();
		Assert.assertTrue(activeVechicleByPhasePage.checkThatAllPhasesAreInStatus("PDR Station","Active"));
		SubscriptionsWebPage subscriptionsPege = activeVechicleByPhasePage.clickSubscriptionsButton();
		Assert.assertTrue(subscriptionsPege.checkGrid());
		subscriptionsPege.clickAddButton();
		Assert.assertTrue(subscriptionsPege.checkAddPopUpContent());
		subscriptionsPege.setSubscriptionPopUpLocation("ALM - Recon Facility");
		subscriptionsPege.setSubscriptionPopUpPhase1("PDR Station");
		subscriptionsPege.setSubscriptionPopUpStatuses1("Active");
		subscriptionsPege.setSubscriptionPopUpPhase2("PDI");
		subscriptionsPege.setSubscriptionPopUpStatuses2("Queued","Active","Completed","Audited","Refused","Rework","Skipped");
		subscriptionsPege.setSubscriptionPopUpDescription("test");
		subscriptionsPege.setSubscriptionPopUpStartTime("12:00 AM");
		subscriptionsPege.clickOkAddPopUpButton();
	}
	
	@Test(testName = "Test Case 65432:Monitor: Reports - Service Count")
	public void checkMonitorReportsServiceCount() throws InterruptedException{
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		ServiceCountWebPage serviceCountPage = monitorpage.clickServiceCountLink();
		Assert.assertTrue(serviceCountPage.verifySearchFields());
		serviceCountPage.clickSearchButton();
		Assert.assertTrue(serviceCountPage.verifySearchResultGrid());
	}
	
	@Test(testName = "Test Case 65433:Monitor: Reports - Active Vehicles by Phase General")
	public void checkMonitorReportsActiveVechiclesByPhaseGeneral(){
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		ActiveVechicleByPhaseWebPage activeVechicleByPhasePage = monitorpage.clickActiveVehiclesByPhaseLink();
		Assert.assertTrue(activeVechicleByPhasePage.checkSearchFields());
		activeVechicleByPhasePage.clickFindButton();
		Assert.assertTrue(activeVechicleByPhasePage.countLocationsInResultTable() > 0);
		activeVechicleByPhasePage.setLocationFilter("ALM - Recon Facility");
		Assert.assertTrue(activeVechicleByPhasePage.checkTimeFrameFilter());
		activeVechicleByPhasePage.clickFindButton();
		Assert.assertTrue(activeVechicleByPhasePage.countLocationsInResultTable() == 1);
		Assert.assertTrue(activeVechicleByPhasePage.checkPhasesInRowCheckBox());
		Assert.assertTrue(activeVechicleByPhasePage.checkSearchResults("ALM - Recon Facility"));
	}
	
	@Test(testName = "Test Case 65434:Monitor: Reports - Active Vehicles by Phase Location and Phases")
	public void checkMonitorReportsActiveVechiclesByPhaseLocationAndPhases() throws InterruptedException{
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		MonitorWebPage monitorpage = backofficeheader.clickMonitorLink();
		ActiveVechicleByPhaseWebPage activeVechicleByPhasePage = monitorpage.clickActiveVehiclesByPhaseLink();
		Assert.assertTrue(activeVechicleByPhasePage.checkSearchFields());
		activeVechicleByPhasePage.clickFindButton();
		Assert.assertTrue(activeVechicleByPhasePage.countLocationsInResultTable() > 0);
		activeVechicleByPhasePage.setLocationFilter("ALM - Recon Facility");
		Assert.assertTrue(activeVechicleByPhasePage.checkTimeFrameFilter());
		activeVechicleByPhasePage.clickFindButton();
		Assert.assertTrue(activeVechicleByPhasePage.countLocationsInResultTable() == 1);
		Assert.assertTrue(activeVechicleByPhasePage.checkPhasesInRowCheckBox());
		Assert.assertTrue(activeVechicleByPhasePage.checkSearchResults("ALM - Recon Facility"));
		Assert.assertTrue(activeVechicleByPhasePage.checkGrid());
		
		activeVechicleByPhasePage.setPhase1("PDR Station");
		activeVechicleByPhasePage.setStatuses1("Active");
		activeVechicleByPhasePage.setPhase2("PDI");
		activeVechicleByPhasePage.setStatuses2("Queued","Active","Completed","Audited","Refused","Rework","Skipped");
		activeVechicleByPhasePage.clickFindButton();
		
//		@SuppressWarnings("unused")
//		int rows = activeVechicleByPhasePage.countRowsInResultTable();

		
	}
}
