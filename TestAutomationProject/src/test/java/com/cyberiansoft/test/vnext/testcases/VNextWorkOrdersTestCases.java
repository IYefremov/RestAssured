package com.cyberiansoft.test.vnext.testcases;

import com.cyberiansoft.test.dataclasses.RetailCustomer;
import com.cyberiansoft.test.vnext.screens.*;
import com.cyberiansoft.test.vnext.screens.menuscreens.VNextWorkOrdersMenuScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextAvailableServicesScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.services.VNextSelectedServicesScreen;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextWorkOrdersScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVehicleInfoScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VNextWorkOrdersTestCases extends BaseTestCaseWithDeviceRegistrationAndUserLogin {
	
	final RetailCustomer testcustomer = new RetailCustomer("Oksana", "Osmak");
	final String testVIN = "1FMCU0DG4BK830800";
	
	@Test(testName= "Test Case 43334:vNext - Show selected services after WO is saved", 
			description = "Show selected services after WO is saved")
	public void testShowSelectedServicesAfterWOIsSaved() { 
	
		final String[] services = { "Bumper Repair", "Other" }; 
		
		VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
		VNextWorkOrdersScreen workordersscreen = homescreen.clickWorkOrdersMenuItem();
		VNextCustomersScreen customersscreen = workordersscreen.clickAddWorkOrderButton();
		customersscreen.selectCustomer(testcustomer);
		VNextVehicleInfoScreen vehicleinfoscreen = new VNextVehicleInfoScreen(appiumdriver);
		vehicleinfoscreen.setVIN(testVIN);
		vehicleinfoscreen.swipeScreenLeft();
		VNextAvailableServicesScreen servicesscreen = new VNextAvailableServicesScreen(appiumdriver);
		servicesscreen.selectServices(services);
		workordersscreen = servicesscreen.saveWorkOrderViaMenu();
		final String wonumber = workordersscreen.getFirstWorkOrderNumber();
		VNextWorkOrdersMenuScreen menuscreen = workordersscreen.clickOnWorkOrderByNumber(wonumber);
		vehicleinfoscreen = menuscreen.clickEditWorkOrderMenuItem();
		vehicleinfoscreen.swipeScreenLeft();
		servicesscreen = new VNextAvailableServicesScreen(appiumdriver);
		VNextSelectedServicesScreen selectedServicesScreen = servicesscreen.switchToSelectedServicesView();
		for (String servicename : services)
			Assert.assertTrue(selectedServicesScreen.isServiceSelected(servicename));
		workordersscreen = servicesscreen.saveWorkOrderViaMenu();
		homescreen = workordersscreen.clickBackButton();
	}

}
