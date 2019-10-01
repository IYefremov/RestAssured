package com.cyberiansoft.test.bo.testcases;

import com.cyberiansoft.test.baseutils.CustomDateProvider;
import com.cyberiansoft.test.bo.config.BOConfigInfo;
import com.cyberiansoft.test.bo.pageobjects.webpages.*;
import com.cyberiansoft.test.dataclasses.bo.BOoperationsSRdata;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.email.getnada.NadaEMailService;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

//@Listeners(VideoListener.class)
public class BackOfficeOperationsServiceRequestsTestCases extends BaseTestCase {

	private static final String DATA_FILE = "src/test/java/com/cyberiansoft/test/bo/data/BOoperationsSRdata.json";
	private NadaEMailService nada;

	@BeforeClass
	public void settingUp() {
		JSONDataProvider.dataFile = DATA_FILE;
		nada = new NadaEMailService();
		nada.setEmailId(BOConfigInfo.getInstance().getUserNadaName());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationNewServiceRequestAppointmentWholesale(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();

		ServiceRequestsListWebPage servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickGeneralInfoEditButton();

		servicerequestslistpage.setServiceRequestGeneralInfo(data.getTeamName(), data.getAssignedTo(), data.getPoNum(), data.getRoNum());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(data.getNewServiceRequest());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickClaimInfoEditButton();
		servicerequestslistpage.selectServiceRequestInsurance(data.getInsurance());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.setServiceRequestLabel(data.getLabel());
		servicerequestslistpage.setServiceRequestDescription(data.getLabel());
		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getNewServiceRequest());
		servicerequestslistpage.clickFindButton();
		servicerequestslistpage.acceptFirstServiceRequestFromList();
		SRAppointmentInfoPopup appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		appointmentpopup.setFromDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setStartTimeValue(data.getStartTime());
		appointmentpopup.setEndTimeValue(data.getEndTime());
		Assert.assertEquals(appointmentpopup.getSubjectValue(), data.getClientName());
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getClientName());
		Assert.assertEquals(appointmentpopup.getTechnicianFieldValue(), data.getTechnicianFieldValue());
		String appointmentfromdate = appointmentpopup.getFromDateValue();
		String appointmentstarttime = appointmentpopup.getStartTimeValue();
		appointmentpopup.clickAddAppointment();
		servicerequestslistpage
				.appointmentExistsForFirstServiceRequestFromList(appointmentfromdate + " " + appointmentstarttime);
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationNewServiceRequestAppointmentRetail(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();

		ServiceRequestsListWebPage servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickGeneralInfoEditButton();

		servicerequestslistpage.setServiceRequestGeneralInfo(data.getTeamName(), data.getAssignedTo(), data.getPoNum(), data.getRoNum());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(data.getNewServiceRequest());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickClaimInfoEditButton();
		servicerequestslistpage.selectServiceRequestInsurance(data.getInsurance());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.setServiceRequestLabel(data.getLabel());
		servicerequestslistpage.setServiceRequestDescription(data.getLabel());
		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getNewServiceRequest());
		servicerequestslistpage.clickFindButton();
		servicerequestslistpage.acceptFirstServiceRequestFromList();
		SRAppointmentInfoPopup appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		appointmentpopup.setFromDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setStartTimeValue(data.getStartTime());
		appointmentpopup.setEndTimeValue(data.getEndTime());
		Assert.assertEquals(appointmentpopup.getSubjectValue(), data.getNewServiceRequest());
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());
		Assert.assertEquals(appointmentpopup.getTechnicianFieldValue(), data.getTechnicianFieldValue());
		String appointmentfromdate = appointmentpopup.getFromDateValue();
		String appointmentstarttime = appointmentpopup.getStartTimeValue();
		appointmentpopup.clickAddAppointment();
		servicerequestslistpage
				.appointmentExistsForFirstServiceRequestFromList(appointmentfromdate + " " + appointmentstarttime);
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationNewServiceRequestAppointmentLocationTypeCustom(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();

		ServiceRequestsListWebPage servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickGeneralInfoEditButton();

		servicerequestslistpage.setServiceRequestGeneralInfo(data.getTeamName(), data.getAssignedTo(), data.getPoNum(), data.getRoNum());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(data.getNewServiceRequest());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickClaimInfoEditButton();
		servicerequestslistpage.selectServiceRequestInsurance(data.getInsurance());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.setServiceRequestLabel(data.getLabel());
		servicerequestslistpage.setServiceRequestDescription(data.getLabel());
		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getNewServiceRequest());
		servicerequestslistpage.clickFindButton();

		Assert.assertTrue(servicerequestslistpage.isAcceptIconPresentForFirstServiceRequestFromList());
		servicerequestslistpage.acceptFirstServiceRequestFromList();
		Assert.assertEquals(servicerequestslistpage.getStatusOfFirstServiceRequestFromList(), data.getStatus());

		SRAppointmentInfoPopup appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();

		appointmentpopup.setFromDateValue(CustomDateProvider.getDayAfterTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setToDateValue(CustomDateProvider.getDayAfterTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setStartTimeValue(data.getStartTime());
		appointmentpopup.setEndTimeValue(data.getEndTime());
		Assert.assertEquals(appointmentpopup.getTechnicianValue(), data.getAssignedTo());
		String appointmentfromdate = appointmentpopup.getFromDateValue();
		String appointmentstarttime = appointmentpopup.getStartTimeValue();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());

		appointmentpopup.selectLocationType(data.getLocationType());
		appointmentpopup.setClientAddressValue(data.getClientAddress());
		appointmentpopup.setClientCityValue(data.getClientCity());
		appointmentpopup.setClientZipValue(data.getClientZip());
		appointmentpopup.clickAddAppointment();

		servicerequestslistpage
				.appointmentExistsForFirstServiceRequestFromList(appointmentfromdate + " " + appointmentstarttime);
		appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());

		Assert.assertEquals(appointmentpopup.getClientAddressValue(), data.getClientAddress());
		Assert.assertEquals(appointmentpopup.getClientCityValue(), data.getClientCity());
		Assert.assertTrue(appointmentpopup.getClientZipValue().equals(data.getClientZip())
				|| appointmentpopup.getClientZipValue().equals(data.getClientZip2()));
		appointmentpopup.clickAddAppointment();
		servicerequestslistpage.closeFirstServiceRequestFromTheList();
	}

	//    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationNewServiceRequestAppointmentLocationTypeCustomer(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();

		ServiceRequestsListWebPage servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickGeneralInfoEditButton();

		servicerequestslistpage.setServiceRequestGeneralInfo(data.getTeamName(), data.getAssignedTo(), data.getPoNum(), data.getRoNum());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(data.getNewServiceRequest());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickClaimInfoEditButton();
		servicerequestslistpage.selectServiceRequestInsurance(data.getInsurance());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.setServiceRequestLabel(data.getLabel());
		servicerequestslistpage.setServiceRequestDescription(data.getLabel());
		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getNewServiceRequest());
		servicerequestslistpage.clickFindButton();
//todo  org.openqa.selenium.interactions.Actions moveToElement
//INFO: When using the W3C Action commands, offsets are from the center of element
		Assert.assertTrue(servicerequestslistpage.isAcceptIconPresentForFirstServiceRequestFromList());
		servicerequestslistpage.acceptFirstServiceRequestFromList();
		Assert.assertEquals(servicerequestslistpage.getStatusOfFirstServiceRequestFromList(), data.getStatus());

		SRAppointmentInfoPopup appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		appointmentpopup.setFromDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setToDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setStartTimeValue(data.getStartTime());
		appointmentpopup.setEndTimeValue(data.getEndTime());
		Assert.assertEquals(appointmentpopup.getTechnicianValue(), data.getAssignedTo());
		String appointmentfromdate = appointmentpopup.getFromDateValue();
		String appointmentstarttime = appointmentpopup.getStartTimeValue();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());

		appointmentpopup.selectLocationType(data.getLocationType());
		appointmentpopup.clickAddAppointment();
		servicerequestslistpage
				.appointmentExistsForFirstServiceRequestFromList(appointmentfromdate + " " + appointmentstarttime);
		appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());

		appointmentpopup.clickAddAppointment();
		servicerequestslistpage.closeFirstServiceRequestFromTheList();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationNewServiceRequestAppointmentLocationTypeOwner(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();

		ServiceRequestsListWebPage servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickGeneralInfoEditButton();

		servicerequestslistpage.setServiceRequestGeneralInfo(data.getTeamName(), data.getAssignedTo(), data.getPoNum(), data.getRoNum());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(data.getNewServiceRequest());
		servicerequestslistpage.selectServiceRequestOwner(data.getNewServiceRequest());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickClaimInfoEditButton();
		servicerequestslistpage.selectServiceRequestInsurance(data.getInsurance());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.setServiceRequestLabel(data.getLabel());
		servicerequestslistpage.setServiceRequestDescription(data.getLabel());
		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getNewServiceRequest());
		servicerequestslistpage.clickFindButton();

		Assert.assertTrue(servicerequestslistpage.isAcceptIconPresentForFirstServiceRequestFromList());
		servicerequestslistpage.acceptFirstServiceRequestFromList();
		Assert.assertEquals(servicerequestslistpage.getStatusOfFirstServiceRequestFromList(), data.getStatus());

		SRAppointmentInfoPopup appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		appointmentpopup.setFromDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setToDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setStartTimeValue(data.getStartTime());
		appointmentpopup.setEndTimeValue(data.getEndTime());
		Assert.assertEquals(appointmentpopup.getTechnicianValue(), data.getAssignedTo());
		String appointmentfromdate = appointmentpopup.getFromDateValue();
		String appointmentstarttime = appointmentpopup.getStartTimeValue();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());
		appointmentpopup.selectLocationType(data.getLocationType());
		appointmentpopup.clickAddAppointment();
		servicerequestslistpage
				.appointmentExistsForFirstServiceRequestFromList(appointmentfromdate + " " + appointmentstarttime);
		appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationNewServiceRequestAppointmentLocationTypeRepairLocation(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();

		ServiceRequestsListWebPage servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickGeneralInfoEditButton();

		servicerequestslistpage.setServiceRequestGeneralInfo(data.getTeamName(), data.getAssignedTo(), data.getPoNum(), data.getRoNum());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(data.getNewServiceRequest());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.clickClaimInfoEditButton();
		servicerequestslistpage.selectServiceRequestInsurance(data.getInsurance());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.setServiceRequestLabel(data.getLabel());
		servicerequestslistpage.setServiceRequestDescription(data.getLabel());
		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getNewServiceRequest());
		// servicerequestslistpage.clickFindButton();

		Assert.assertTrue(servicerequestslistpage.isAcceptIconPresentForFirstServiceRequestFromList());
		servicerequestslistpage.acceptFirstServiceRequestFromList();
		Assert.assertEquals(servicerequestslistpage.getStatusOfFirstServiceRequestFromList(), data.getStatus());

		SRAppointmentInfoPopup appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		appointmentpopup.setFromDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setToDateValue(CustomDateProvider.getTomorrowLocalizedDateFormattedShort());
		appointmentpopup.setStartTimeValue(data.getStartTime());
		appointmentpopup.setEndTimeValue(data.getEndTime());
		Assert.assertEquals(appointmentpopup.getTechnicianValue(), data.getAssignedTo());
		String appointmentfromdate = appointmentpopup.getFromDateValue();
		String appointmentstarttime = appointmentpopup.getStartTimeValue();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());

		appointmentpopup.selectLocationType(data.getLocationType());
		appointmentpopup.selectLocation(data.getLocation());
		appointmentpopup.clickAddAppointment();
		servicerequestslistpage
				.appointmentExistsForFirstServiceRequestFromList(appointmentfromdate + " " + appointmentstarttime);
		appointmentpopup = new SRAppointmentInfoPopup(webdriver);
		servicerequestslistpage.clickAddAppointmentToFirstServiceRequestFromList();
		Assert.assertEquals(appointmentpopup.getClientInfoNameValue(), data.getNewServiceRequest());

		appointmentpopup.clickAddAppointment();
		servicerequestslistpage.closeFirstServiceRequestFromTheList();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationsCLUserItNotPossibleToAcceptSR_OptionIsNotPresent(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		backOfficeHeader.clickLogout();
		BackOfficeLoginWebPage loginpage = new BackOfficeLoginWebPage(webdriver);
		loginpage.userLogin(BOConfigInfo.getInstance().getAlternativeUserName(), BOConfigInfo.getInstance().getAlternativeUserPassword());
		HomeWebPage homepage = new HomeWebPage(webdriver);
		backOfficeHeader.clickHomeLink();
		ServiceRequestsListWebPage servicerequestslistpage = homepage.clickNewServiceRequestLink();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getVIN());
		servicerequestslistpage.clickFindButton();
		Assert.assertFalse(servicerequestslistpage.isAcceptIconPresentForFirstServiceRequestFromList());
		servicerequestslistpage.rejectFirstServiceRequestFromList();
		Assert.assertEquals(servicerequestslistpage.getStatusOfFirstServiceRequestFromList(), data.getStatus());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationsCLUserVerifyThatAcceptedSRIsInReadOnlyMode_NotPossibleToEdit(String rowID, String description, JSONObject testData) throws InterruptedException {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		backOfficeHeader.clickLogout();
		BackOfficeLoginWebPage loginpage = new BackOfficeLoginWebPage(webdriver);
		loginpage.userLogin(BOConfigInfo.getInstance().getAlternativeUserName(), BOConfigInfo.getInstance().getAlternativeUserPassword());
		HomeWebPage homepage = new HomeWebPage(webdriver);
		backOfficeHeader.clickHomeLink();
		Thread.sleep(1000);
		ServiceRequestsListWebPage servicerequestslistpage = homepage.clickNewServiceRequestLink();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();
		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.saveNewServiceRequest();

		backOfficeHeader.clickLogout();
		loginpage = PageFactory.initElements(webdriver, BackOfficeLoginWebPage.class);
		loginpage.userLogin(BOConfigInfo.getInstance().getUserNadaName(), BOConfigInfo.getInstance().getUserNadaPassword());
		backOfficeHeader = PageFactory.initElements(webdriver, BackOfficeHeaderPanel.class);
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getVIN());
		servicerequestslistpage.clickFindButton();
		Assert.assertTrue(servicerequestslistpage.isAcceptIconPresentForFirstServiceRequestFromList());
		servicerequestslistpage.acceptFirstServiceRequestFromList();
		Assert.assertEquals(servicerequestslistpage.getStatusOfFirstServiceRequestFromList(), data.getStatus());

		backOfficeHeader.clickLogout();
		loginpage = PageFactory.initElements(webdriver, BackOfficeLoginWebPage.class);

		loginpage.userLogin(BOConfigInfo.getInstance().getAlternativeUserName(), BOConfigInfo.getInstance().getAlternativeUserPassword());
		backOfficeHeader = PageFactory.initElements(webdriver, BackOfficeHeaderPanel.class);
		homepage = new HomeWebPage(webdriver);
		backOfficeHeader.clickHomeLink();
		Thread.sleep(1000);
		servicerequestslistpage = homepage.clickNewServiceRequestLink();
		Thread.sleep(2000);
		servicerequestslistpage.makeSearchPanelVisible();

		servicerequestslistpage.setSearchFreeText(data.getVIN());
		servicerequestslistpage.clickFindButton();
		servicerequestslistpage.selectFirstServiceRequestFromList();
		servicerequestslistpage.switchToServiceRequestInfoFrame();
		Assert.assertFalse(servicerequestslistpage.getGeneralInfoEditButton().isDisplayed());
		Assert.assertFalse(servicerequestslistpage.getCustomerEditButton().isDisplayed());
		Assert.assertFalse(servicerequestslistpage.getVehicleInfoEditButton().isDisplayed());
		servicerequestslistpage.clickCloseServiceRequestButton();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationsCLUserItNotPossibleToAddLabelsWhenCreateSR(String rowID, String description, JSONObject testData) throws InterruptedException {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		backOfficeHeader.clickLogout();
		BackOfficeLoginWebPage loginpage = new BackOfficeLoginWebPage(webdriver);
		loginpage.userLogin(BOConfigInfo.getInstance().getAlternativeUserName(), BOConfigInfo.getInstance().getAlternativeUserPassword());
		HomeWebPage homepage = new HomeWebPage(webdriver);
		backOfficeHeader.clickHomeLink();
		Thread.sleep(1000);
		ServiceRequestsListWebPage servicerequestslistpage = homepage.clickNewServiceRequestLink();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();

		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		Assert.assertFalse(servicerequestslistpage.getServiceRequestLabelField().isDisplayed());

	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationsSRListVerifyThatCheckInButtonIsNotPresentWhenCreateSR(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage servicerequestslistpage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		servicerequestslistpage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		servicerequestslistpage.clickAddServiceRequestButton();

		servicerequestslistpage.clickCustomerEditButton();
		servicerequestslistpage.selectServiceRequestCustomer(data.getCustomer());
		servicerequestslistpage.clickDoneButton();
		servicerequestslistpage.clickVehicleInforEditButton();
		servicerequestslistpage.setServiceRequestVIN(data.getVIN());
		servicerequestslistpage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		servicerequestslistpage.clickDoneButton();

		servicerequestslistpage.saveNewServiceRequest();
		servicerequestslistpage.makeSearchPanelVisible();
		servicerequestslistpage.setSearchFreeText(data.getVIN());
		servicerequestslistpage.clickFindButton();
		servicerequestslistpage.selectFirstServiceRequestFromList();
		Assert.assertFalse(servicerequestslistpage.isCheckInButtonDisplayedForSelectedSR());
		servicerequestslistpage.rejectFirstServiceRequestFromList();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationsVerifyThatCheckInButtonAppearsWhenSRIsSaved(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsListPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsListPage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		serviceRequestsListPage.clickAddServiceRequestButton();

		serviceRequestsListPage.clickCustomerEditButton();
		serviceRequestsListPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsListPage.clickDoneButton();
		serviceRequestsListPage.clickVehicleInforEditButton();
		serviceRequestsListPage.setServiceRequestVIN(data.getVIN());
		serviceRequestsListPage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		serviceRequestsListPage.clickDoneButton();

		serviceRequestsListPage.saveNewServiceRequest();
        serviceRequestsListPage.makeSearchPanelVisible();
        serviceRequestsListPage.setSearchFreeText(data.getVIN());
		serviceRequestsListPage.clickFindButton();
		serviceRequestsListPage.selectFirstServiceRequestFromList();
		Assert.assertFalse(serviceRequestsListPage.isCheckInButtonDisplayedForSelectedSR());
		serviceRequestsListPage.acceptFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsListPage.isCheckInButtonVisible());
		serviceRequestsListPage.closeFirstServiceRequestFromTheList();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testOperationsSRListVerifyThatCheckInButtonIsChangedToUndoCheckInAfterPressingAndViceVersa(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        backOfficeHeader.clickOperationsLink();
        ServiceRequestsListWebPage serviceRequestsListPage = new ServiceRequestsListWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
		serviceRequestsListPage.selectAddServiceRequestsComboboxValue(data.getAddServiceRequestValue());
		serviceRequestsListPage.clickAddServiceRequestButton();

		serviceRequestsListPage.clickCustomerEditButton();
		serviceRequestsListPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsListPage.clickDoneButton();
		serviceRequestsListPage.clickVehicleInforEditButton();
		serviceRequestsListPage.setServiceRequestVIN(data.getVIN());
		serviceRequestsListPage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		serviceRequestsListPage.clickDoneButton();

		serviceRequestsListPage.saveNewServiceRequest();
		serviceRequestsListPage.makeSearchPanelVisible();
		serviceRequestsListPage.setSearchFreeText(data.getVIN());
		serviceRequestsListPage.clickFindButton();
		serviceRequestsListPage.selectFirstServiceRequestFromList();
		Assert.assertFalse(serviceRequestsListPage.isCheckInButtonVisible());
		serviceRequestsListPage.acceptFirstServiceRequestFromList();
//		serviceRequestsListPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsListPage.isCheckInButtonVisible());
		Assert.assertEquals(serviceRequestsListPage.getCheckInButtonValueForSelectedSR(), data.getCheckInButton());
		serviceRequestsListPage.clickCheckInButtonForSelectedSR();
		Assert.assertEquals(serviceRequestsListPage.getCheckInButtonValueForSelectedSR(), data.getUndoCheckInButton());
		serviceRequestsListPage.clickCheckInButtonForSelectedSR();
		Assert.assertEquals(serviceRequestsListPage.getCheckInButtonValueForSelectedSR(), data.getCheckInButton());
		serviceRequestsListPage.closeFirstServiceRequestFromTheList();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestDescription(String rowID, String description, JSONObject testData) {
		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.setServiceRequestDescription(description);
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkTimeOfLastDescription());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequest(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.addTags(data.getTags());
		Assert.assertTrue(serviceRequestsWebPage.addTags(data.getTags()[data.getTags().length - 1]));
		serviceRequestsWebPage.addTags(data.getSymbol());
		Assert.assertTrue(serviceRequestsWebPage.removeFirtsTag());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkTags(data.getTags()));
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestDescriptionInExistingSR(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.setServiceRequestDescription(data.getDescriptions()[0]);
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.addNewDescriptionAndCheckOld(data.getDescriptions()[1],
				data.getDescriptions()[0]));
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testShownSRDuringCreation(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		Assert.assertFalse(serviceRequestsWebPage.checkIfDescriptionIconsVisible());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testCreatingSRWithDifferentDescriptions(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.setServiceRequestDescription(data.getDescriptions()[0]);
		serviceRequestsWebPage.setServiceRequestDescription(data.getDescriptions()[1]);
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkServiceDescription(data.getDescriptions()[1]));
	}


	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkDescriptionDocument(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkServiceRequestDocumentIcon());
		serviceRequestsWebPage.clickDocumentButton();
		Assert.assertTrue(serviceRequestsWebPage.checkElementsInDocument());
		Assert.assertTrue(serviceRequestsWebPage.clickAddImageBTN());
		//serviceRequestsWebPage.addImage();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkMultiTechInSR(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
        serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.addAppointmentFromSRlist(data.getFirstDay(), data.getSecondDay()));
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(
				serviceRequestsWebPage.checkDefaultAppointmentValuesAndAddAppointmentFomSREdit());
		Assert.assertTrue(serviceRequestsWebPage.checkStatus(data.getStatus()));
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkMultiTechInSRShowHideTech(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.addAppointmentFromSRlist(data.getFirstDay(), data.getSecondDay()));
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkShowHideTechs(data.getFirstDay(), data.getSecondDay()));
		Assert.assertTrue(serviceRequestsWebPage.checkStatus(data.getStatus()));
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkMultiTechInSideScrollbar(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkStatus(data.getStatus()));
		Assert.assertTrue(serviceRequestsWebPage.checkDefaultAppointmentValuesFromCalendar(
				data.getFirstDay(), data.getSecondDay(), data.getCustomer()));
		Assert.assertTrue(serviceRequestsWebPage.checkStatus(data.getNewStatus()));
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRappointmentSchedulerWeek(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.checkSchedulerByDateWeek(data.getFirstDay(), data.isDateShifted());
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.setSuggestedStartDate(data.getFirstDay());
		Assert.assertTrue(serviceRequestsWebPage.checkDefaultAppointmentDateFromSRedit(data.getFirstDay()));
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRappointmentSchedulerMonth(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
        int prevRequestsCount = serviceRequestsWebPage.checkSchedulerByDateMonth(data.getFirstDay());
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.setSuggestedStartDate(data.getFirstDayTime());
		Assert.assertTrue(serviceRequestsWebPage.checkDefaultAppointmentDateFromSRedit(data.getFirstDay()));
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.reloadPage();
		int afterRequestsCount = serviceRequestsWebPage.checkSchedulerByDateMonth(data.getFirstDay());
		Assert.assertEquals(afterRequestsCount - prevRequestsCount, 1);
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRAppointmentSchedulerMultiTechniciansFilterOf5(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
        serviceRequestsWebPage.goToMonthInScheduler();
		Assert.assertTrue(serviceRequestsWebPage.checkTechniciansFromScheduler());
		Assert.assertEquals(5, serviceRequestsWebPage.getMaximumTechniciansListSize(),
                "The maximum technicians list size is not 5");
		Assert.assertTrue(serviceRequestsWebPage.applyAndCheck5TechniciansFromScheduler());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSchedulerTechniciansFilter(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		// serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.setSuggestedStartDate(data.getFirstDayTime());
		Assert.assertTrue(serviceRequestsWebPage.checkDefaultAppointmentDateFromSRedit(data.getFirstDay()));
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.goToMonthInScheduler();
		Assert.assertTrue(serviceRequestsWebPage.checkTechniciansFromScheduler());
		serviceRequestsWebPage.applyTechniciansFromScheduler();
		serviceRequestsWebPage.countSR();
        System.out.println(serviceRequestsWebPage.countSR());
//        serviceRequestsWebPage.selectTechnicianFromSchedulerByIndex(0);
		serviceRequestsWebPage.applyTechniciansFromScheduler();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRMultiTechReset(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		// serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.setSuggestedStartDate(data.getFirstDayTime());
		Assert.assertTrue(serviceRequestsWebPage.checkDefaultAppointmentDateFromSRedit(data.getFirstDay()));
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.goToMonthInScheduler();
		Assert.assertTrue(serviceRequestsWebPage.checkTechniciansFromScheduler());
		Assert.assertEquals(5, serviceRequestsWebPage.getMaximumTechniciansListSize(),
                "The maximum technicians list size is not 5");
		Assert.assertTrue(serviceRequestsWebPage.applyAndCheck5TechniciansFromScheduler());
		serviceRequestsWebPage.resetAndCheckTecniciansFromScheduler();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRCreation(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		// serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.setSuggestedStartDate(data.getFirstDay());
		Assert.assertTrue(serviceRequestsWebPage.checkDefaultAppointmentDateFromSRedit(data.getFirstDay()));
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRLCnoEntry(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		Assert.assertFalse(serviceRequestsWebPage.checkLifeCycleBTN());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRLCEstimate(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.setSuggestedStartDate(data.getFirstDayTime());
		Assert.assertTrue(serviceRequestsWebPage.checkDefaultAppointmentDateFromSRedit(data.getFirstDay()));
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.clickAddAppointmentButtonFromSREdit();
		serviceRequestsWebPage.clickGetAppointmentButton();
		Assert.assertTrue(serviceRequestsWebPage.checkStatus(data.getStatus()));
		Assert.assertTrue(serviceRequestsWebPage.checkLifeCycleDate());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRLCafterCreation(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.goToLifeCycle();
		Assert.assertTrue(serviceRequestsWebPage.isLifeCycleContentDisplayed());
		serviceRequestsWebPage.goToDocumentLinkFromLC();
		Assert.assertTrue(serviceRequestsWebPage.checkLifeCycleDocumentsContent());
		Assert.assertTrue(serviceRequestsWebPage.checkDocumentDownloadingInLC());
		Assert.assertTrue(serviceRequestsWebPage.clickAddImageBTN());
		//	serviceRequestsWebPage.addImage();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRLifeCycleWOAutoCreation(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.goToSRmenu();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getVehicleStock(), data.getVehicleVIN());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.goToLifeCycle();
		Assert.assertTrue(serviceRequestsWebPage.goToWOfromLifeCycle());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRLCapproved(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getVehicleStock(), data.getVehicleVIN());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkStatus(data.getNewStatus()));
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.goToLifeCycle();
		Assert.assertTrue(serviceRequestsWebPage.checkAcceptanceOfSRinLC());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRLCrejected(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getVehicleStock(), data.getVehicleVIN());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.rejectFirstServiceRequestFromList();
		Assert.assertTrue(serviceRequestsWebPage.checkStatus(data.getNewStatus()));
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.goToLifeCycle();
		Assert.assertTrue(serviceRequestsWebPage.checkRejectOfSRinLC());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void checkSRLCclosed(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.makeSearchPanelVisible();
		Assert.assertTrue(serviceRequestsWebPage.checkSRsearchCriterias());
		// serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		// serviceRequestsWebPage.clickAddServiceRequestButton();
		// serviceRequestsWebPage.clickCustomerEditButton();
		// serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		// serviceRequestsWebPage.clickDoneButton();
		// serviceRequestsWebPage.clickVehicleEditButton();
		// serviceRequestsWebPage.setVehicleInfo(data.getStock123() , data.getStock123());
		// serviceRequestsWebPage.clickDoneButton();
		// serviceRequestsWebPage.saveNewServiceRequest();
		// serviceRequestsWebPage.rejectFirstServiceRequestFromList();
		// Assert.assertTrue(serviceRequestsWebPage.checkStatus(newStatus));
		// serviceRequestsWebPage.selectFirstServiceRequestFromList();
		// serviceRequestsWebPage.goToLifeCycle();
		// Assert.assertTrue(serviceRequestsWebPage.checkClosedOfSRinLC());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestAccepted(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWordWasCreated())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getEmailNotification());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWordWasCreated());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
	}

	//    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsSRCreated(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWord())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getEmailNotification());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();

		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

		//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWord());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		// eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestCheckedIn(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWord())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getNotificationDropDown());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.clickCheckInButtonForSelectedSR();
		serviceRequestsWebPage.switchToServiceRequestInfoFrame();
		serviceRequestsWebPage.saveNewServiceRequest();
//		Assert.assertTrue(mailChecker.checkEmails(data.getEmailKeyWord()));

		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWord());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsAppointmentCreated(String rowID, String description, JSONObject testData) throws Exception {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWordWasCreated())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getAlert());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getAlert());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.addAppointmentFromSRlist(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
//        Assert.assertTrue(mailChecker.checkEmails(data.getEmailKeyWordWasCreated()) || mailChecker.checkTestEmails());

// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWordWasCreated());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getAlert());
		// eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsAppointmentFailed(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWordWasCreated())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getAlert());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getAlert());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.addAppointmentFromSRlist(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWordWasCreated());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getAlert());
		// eventsWebPage.deleteSelectedEvent();
	}

	//    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestAppointmentCreated(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWord())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//		Assert.assertTrue(
//				mailChecker.checkTestEmails() || mailChecker.checkEmails(data.getEmailKeyWord()));
//todo fails
//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWord());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		// eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestAcceptedByTech(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWord())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();

		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//		Assert.assertTrue(mailChecker.checkTestEmails()
//				|| mailChecker.checkEmails(data.getEmailKeyWord()));

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWord());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		// eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestEstimationCreated(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWordWasCreated())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();

		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//		Assert.assertTrue(mailChecker.checkEmails(data.getEmailKeyWordWasCreated()));

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWordWasCreated());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestIsMonitored(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWordRemainder())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//		Assert.assertTrue(mailChecker.checkEmails(data.getEmailKeyWordRemainder()) || mailChecker.checkTestEmails());

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWordRemainder());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		// eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestOrderCreated(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWordWasCreated())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();

		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//		Assert.assertTrue(mailChecker.checkEmails(data.getEmailKeyWordWasCreated()));

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWordWasCreated());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		// eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestRejected(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWordWasCreated())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getEvent());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationDropDownForSelected(data.getSelected());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.rejectFirstServiceRequestFromList();
		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//		Assert.assertTrue(mailChecker.checkEmails(data.getEmailKeyWordWasCreated()) || mailChecker.checkTestEmails());

//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWordWasCreated());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		// eventsWebPage.deleteSelectedEvent();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testMiscellaneousEventsServiceRequestCheckIn(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

//        EmailUtils.MailSearchParametersBuilder mailSearchParameters = new EmailUtils.MailSearchParametersBuilder()
//                .withSubject(data.getEmailKeyWord())
//                .unreadOnlyMessages(true).maxMessagesToSearch(5);

		MiscellaneousWebPage miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		EventsWebPage eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.clickAddEventButton();
		eventsWebPage.selectEvent(data.getNotificationDropDown());
		eventsWebPage.setAlertNewName(data.getEventNewName());
		Assert.assertTrue(eventsWebPage.saveNewEvent());
		eventsWebPage.selectEventRowByName(data.getEventNewName());
		eventsWebPage.setEmailNotificationCheckBoxForSelected();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo());
		serviceRequestsWebPage.addAppointmentWithTechnician(data.getFirstDay(), data.getSecondDay(), data.getTechnician());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.clickCheckInButtonForSelectedSR();
		serviceRequestsWebPage.switchToServiceRequestInfoFrame();
		serviceRequestsWebPage.saveNewServiceRequest();
		// todo uncomment after BO will be configured.
//        NadaEMailService.MailSearchParametersBuilder searchParametersBuilder =
//                new NadaEMailService.MailSearchParametersBuilder()
//                        .withSubject(data.getEmailKeyWordWasCreated());
//        String mailmessage = nada.getMailMessageBySubjectKeywords(searchParametersBuilder);
//        System.out.println("MESSAGE:\n"+ mailmessage);

//		Assert.assertTrue(mailChecker.checkEmails(data.getEmailKeyWord()));
//        Assert.assertTrue(emailUtils.waitForMessageWithSubjectInFolder(mailSearchParameters),
//                "Could not find email message with subject containing " + data.getEmailKeyWord());

		miscellaneouspage = new MiscellaneousWebPage(webdriver);
		backOfficeHeader.clickMiscellaneousLink();
		eventsWebPage = new EventsWebPage(webdriver);
		miscellaneouspage.clickEventsLink();
		eventsWebPage.selectEventRowByName(data.getEventNewName());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestTypeDuplicateSearchIssue(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		Assert.assertEquals(serviceDialog.countAvailableServices(), 2);
		serviceRequestsWebPage.scrollWindow("-300");
		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickErrorWithBLockingRadioButton();
		serviceRequestTypesPage.selectStockRoVinOptions();
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		serviceRequestTypesPage.getTableRowWithServiceRequestType(data.getServiceRequestType());
		String currentWindow = serviceRequestTypesPage.getCurrentWindow();
		ServiceRequestTypesVehicleInfoSettingsPage settingsPage = new ServiceRequestTypesVehicleInfoSettingsPage(webdriver);
		serviceRequestTypesPage.clickSettingsVehicleInfo(data.getServiceRequestType());
		serviceRequestTypesPage.switchToSecondWindow(currentWindow);
		settingsPage.unselectCheckBox(data.getVIN());
		settingsPage.unselectCheckBox(data.getStockNum());
		settingsPage.unselectCheckBox(data.getRoNum());
		settingsPage.clickUpdateButton();
		settingsPage.closeNewTab(currentWindow);
		serviceRequestTypesPage.switchToSecondWindow(currentWindow);
		operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleEditButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickNoneRadioButton();
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		serviceRequestTypesPage.getTableRowWithServiceRequestType(data.getServiceRequestType());
		currentWindow = serviceRequestTypesPage.getCurrentWindow();
		settingsPage = new ServiceRequestTypesVehicleInfoSettingsPage(webdriver);
		serviceRequestTypesPage.clickSettingsVehicleInfo(data.getServiceRequestType());
		serviceRequestTypesPage.switchToSecondWindow(currentWindow);
		settingsPage.selectCheckBox(data.getVIN());
		settingsPage.selectCheckBox(data.getStockNum());
		settingsPage.selectCheckBox(data.getRoNum());
		settingsPage.clickUpdateButton();
		settingsPage.closeNewTab(currentWindow);
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestTypeDuplicateNotificationRO(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		Assert.assertEquals(serviceDialog.countAvailableServices(), 2);
		serviceRequestsWebPage.scrollWindow("-300");
		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickWarningOnlyRadioButton();

		serviceRequestTypesPage.unselectOption(data.getVIN());
		serviceRequestTypesPage.selectOption(data.getRoNum());
		serviceRequestTypesPage.unselectOption(data.getStockNum());
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		String randomRO = Integer.toString(new Random().nextInt());
		serviceRequestsWebPage.setRO(randomRO);
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setRO(randomRO);
		serviceRequestsWebPage.clickDoneButton();
		Assert.assertTrue(serviceRequestsWebPage.saveNewServiceRequest());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestTypeDuplicateErrorVIN(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		Assert.assertEquals(serviceDialog.countAvailableServices(), 2);
		serviceRequestsWebPage.scrollWindow("-300");
		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickWarningOnlyRadioButton();
		serviceRequestTypesPage.unselectOption(data.getRoNum());
		serviceRequestTypesPage.selectOption(data.getVIN());
		serviceRequestTypesPage.unselectOption(data.getStockNum());
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		String randomVIN = Integer.toString(new Random().nextInt());
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), randomVIN);
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), randomVIN);
		serviceRequestsWebPage.clickDoneButton();
		Assert.assertTrue(serviceRequestsWebPage.saveNewServiceRequest());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestTypeDuplicateErrorRO(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		Assert.assertEquals(serviceDialog.countAvailableServices(), 2);
		serviceRequestsWebPage.scrollWindow("-300");
		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickWarningOnlyRadioButton();
		serviceRequestTypesPage.unselectOption(data.getVIN());
		serviceRequestTypesPage.selectOption(data.getRoNum());
		serviceRequestTypesPage.unselectOption(data.getStockNum());
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		String randomRO = Integer.toString(new Random().nextInt());
		serviceRequestsWebPage.setRO(randomRO);
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setRO(randomRO);
		serviceRequestsWebPage.clickDoneButton();
		Assert.assertTrue(serviceRequestsWebPage.saveNewServiceRequest());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestTypeDuplicateNotificationVIN(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		Assert.assertEquals(serviceDialog.countAvailableServices(), 2);
		serviceRequestsWebPage.scrollWindow("-300");
		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickWarningOnlyRadioButton();
		serviceRequestTypesPage.unselectOption(data.getRoNum());
		serviceRequestTypesPage.selectOption(data.getVIN());
		serviceRequestTypesPage.unselectOption(data.getStockNum());
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		String randomVIN = Integer.toString(new Random().nextInt());
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), randomVIN);
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(data.getStock123(), randomVIN);
		serviceRequestsWebPage.clickDoneButton();
		Assert.assertTrue(serviceRequestsWebPage.saveNewServiceRequest());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestTypeDuplicateErrorStock(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		Assert.assertEquals(serviceDialog.countAvailableServices(), 2);
		serviceRequestsWebPage.scrollWindow("-300");
		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickWarningOnlyRadioButton();
		serviceRequestTypesPage.unselectOption(data.getVIN());
		serviceRequestTypesPage.selectOption(data.getStockNum());
		serviceRequestTypesPage.unselectOption(data.getRoNum());
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		String randonStock = Integer.toString(new Random().nextInt());
		serviceRequestsWebPage.setVehicleInfo(randonStock, data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(randonStock, data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		Assert.assertTrue(serviceRequestsWebPage.saveNewServiceRequest());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestTypeDuplicateNotificationStock(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		Assert.assertEquals(serviceDialog.countAvailableServices(), 2);
		serviceRequestsWebPage.scrollWindow("-300");
		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		serviceRequestTypesPage.openGeneralSettingsTab();
		serviceRequestTypesPage.clickWarningOnlyRadioButton();
		serviceRequestTypesPage.unselectOption(data.getRoNum());
		serviceRequestTypesPage.selectOption(data.getStockNum());
		serviceRequestTypesPage.unselectOption(data.getVIN());
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		String randonStock = Integer.toString(new Random().nextInt());
		serviceRequestsWebPage.setVehicleInfo(randonStock, data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();

		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setVehicleInfo(randonStock, data.getStock123());
		serviceRequestsWebPage.clickDoneButton();
		Assert.assertTrue(serviceRequestsWebPage.saveNewServiceRequest());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestUndoReject(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		CompanyWebPage companyPage = new CompanyWebPage(webdriver);
		backOfficeHeader.clickCompanyLink();
		ServiceRequestTypesWebPage serviceRequestTypesPage = new ServiceRequestTypesWebPage(webdriver);
		companyPage.clickServiceRequestTypesLink();
		serviceRequestTypesPage.clickEditServiceRequestType(data.getServiceRequestType());
		Assert.assertTrue(serviceRequestTypesPage.isAllowUndoRejectChecked());
		serviceRequestTypesPage.clickEditServiceRequestTypeOkButton();
		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.makeSearchPanelVisible();
		Assert.assertTrue(serviceRequestsWebPage.checkSRsearchCriterias());
		serviceRequestsWebPage.selectAddServiceRequestsComboboxValue(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickGeneralInfoEditButton();
		serviceRequestsWebPage.setServiceRequestGeneralInfo(data.getServiceRequestGeneralInfo(),
				data.getAssignedTo(), data.getPoNum(), data.getRoNum());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getClientName());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickVehicleInforEditButton();
		serviceRequestsWebPage.setServiceRequestVIN(data.getVIN());
		serviceRequestsWebPage.decodeAndVerifyServiceRequestVIN(data.getMake(), data.getModel());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickClaimInfoEditButton();
		serviceRequestsWebPage.selectServiceRequestInsurance(data.getInsurance());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.setServiceRequestLabel(data.getTestDescription());
		serviceRequestsWebPage.setServiceRequestDescription(data.getTestDescription());
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.makeSearchPanelVisible();
		serviceRequestsWebPage.setSearchFreeText(data.getClientName());
		serviceRequestsWebPage.clickFindButton();
		serviceRequestsWebPage.rejectFirstServiceRequestFromList();
		serviceRequestsWebPage.clickRejectUndoButton();
		serviceRequestsWebPage.acceptFirstServiceRequestFromList();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void testServiceRequestAdviserListing(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

		OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
		backOfficeHeader.clickOperationsLink();
		ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		operationsPage.clickNewServiceRequestList();
		serviceRequestsWebPage.clickAddServiceRequestButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		Assert.assertTrue(serviceRequestsWebPage.checkPresenceOfServiceAdvisersByFilter(data.getTestDescription()));
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.clickCustomerEditButton();
		serviceRequestsWebPage.selectServiceRequestCustomer(data.getCustomer());
		serviceRequestsWebPage.clickDoneButton();
		serviceRequestsWebPage.saveNewServiceRequest();
		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		Assert.assertEquals(serviceRequestsWebPage.getFirstServiceAdviserName(), data.getCustomer());
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void verifyVehiclePartCanBeAssignedToServicesInSR(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsWebPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsWebPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsWebPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		serviceDialog.checkRandomServiceOption();
		serviceDialog.clickAddServiceOption();
		Assert.assertTrue(serviceDialog.isSelectedServiceContainerDisplayed(), "The service container is not displayed");
        serviceDialog.clickVehiclePart();
        ServiceRequestListServiceVehiclePartDialog vehiclePartDialog = new ServiceRequestListServiceVehiclePartDialog(webdriver);
		int availableVehiclePartOptions = vehiclePartDialog.getAvailableVehiclePartOptions();
		int assignedVehiclePartOptions = vehiclePartDialog.getAssignedVehiclePartOptions();
		vehiclePartDialog.selectRandomAvailableVehiclePartOption();
		vehiclePartDialog.clickMoveToTheRight();
		Assert.assertEquals(availableVehiclePartOptions - 1, vehiclePartDialog.getAvailableVehiclePartOptions(),
				"The available vehicle Part Options have not been reduced after moving to the right");
		Assert.assertEquals(assignedVehiclePartOptions + 1, vehiclePartDialog.getAssignedVehiclePartOptions(),
				"The assigned vehicle Part Options have not been increased after moving to the right");
		vehiclePartDialog.clickServiceVehiclePartOkButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickDoneServicesButton();
		serviceRequestsWebPage.saveNewServiceRequest();

		serviceRequestsWebPage.selectFirstServiceRequestFromList();
		serviceRequestsWebPage.clickServiceEditButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickCancelServicesButton();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void verifyVehiclePartCanBeUnassignedFromServicesInSR(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsListPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsListPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsListPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsListPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		serviceDialog.checkRandomServiceOption();
		serviceDialog.clickAddServiceOption();
		Assert.assertTrue(serviceDialog.isSelectedServiceContainerDisplayed(), "The service container is not displayed");
        serviceDialog.clickVehiclePart();
        ServiceRequestListServiceVehiclePartDialog vehiclePartDialog = new ServiceRequestListServiceVehiclePartDialog(webdriver);
		int availableVehiclePartOptions = vehiclePartDialog.getAvailableVehiclePartOptions();
		int assignedVehiclePartOptions = vehiclePartDialog.getAssignedVehiclePartOptions();
		vehiclePartDialog.selectRandomAvailableVehiclePartOption();
		vehiclePartDialog.clickMoveToTheRight();
		vehiclePartDialog.clickServiceVehiclePartOkButton();
		serviceDialog.clickVehiclePart();
		Assert.assertEquals(availableVehiclePartOptions - 1, vehiclePartDialog.getAvailableVehiclePartOptions(),
				"The available vehicle Part Options have not been reduced after moving to the right");
		Assert.assertEquals(assignedVehiclePartOptions + 1, vehiclePartDialog.getAssignedVehiclePartOptions(),
				"The assigned vehicle Part Options have not been increased after moving to the right");
		vehiclePartDialog.clickServiceVehiclePartOkButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickVehiclePart();

		vehiclePartDialog.selectRandomAssignedVehiclePartOption();
		vehiclePartDialog.clickMoveToTheLeft();
		vehiclePartDialog.clickServiceVehiclePartOkButton();
		serviceDialog.clickVehiclePart();

		Assert.assertEquals(availableVehiclePartOptions, vehiclePartDialog.getAvailableVehiclePartOptions(),
				"The available vehicle Part Options have not been increased after moving to the left");
		Assert.assertEquals(assignedVehiclePartOptions, vehiclePartDialog.getAssignedVehiclePartOptions(),
				"The assigned vehicle Part Options have not been reduced after moving to the left");

		vehiclePartDialog.clickServiceVehiclePartCancelButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickDoneServicesButton();
		serviceRequestsListPage.saveNewServiceRequest();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void verifyAllVehiclePartsCanBeAssignedToServicesInSR(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsListPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsListPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsListPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsListPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		serviceDialog.checkRandomServiceOption();
		serviceDialog.clickAddServiceOption();
		Assert.assertTrue(serviceDialog.isSelectedServiceContainerDisplayed(), "The service container is not displayed");
        serviceDialog.clickVehiclePart();
        ServiceRequestListServiceVehiclePartDialog vehiclePartDialog = new ServiceRequestListServiceVehiclePartDialog(webdriver);
		int availableVehiclePartOptions = vehiclePartDialog.getAvailableVehiclePartOptions();
		int assignedVehiclePartOptions = vehiclePartDialog.getAssignedVehiclePartOptions();
		vehiclePartDialog.clickMoveAllToTheRight();
		Assert.assertEquals(vehiclePartDialog.getAvailableVehiclePartOptions(), assignedVehiclePartOptions,
				"The available vehicle Part Options have not been reduced by the number of assigned options " +
						"after moving all options to the right");
		Assert.assertEquals(vehiclePartDialog.getAssignedVehiclePartOptions(), availableVehiclePartOptions,
				"The assigned vehicle Part Options have not been increased by the number of available options" +
						"after moving all options to the right");
		vehiclePartDialog.clickServiceVehiclePartOkButton();
		Assert.assertEquals(serviceDialog.getNumberOfSelectedServiceContainersDisplayed(), availableVehiclePartOptions,
				"The number of service displayed containers differs from the number of assigned options");
		serviceDialog.clickDoneServicesButton();
		serviceRequestsListPage.saveNewServiceRequest();


		serviceRequestsListPage.selectFirstServiceRequestFromList();
		serviceRequestsListPage.clickServiceEditButton();

		Assert.assertEquals(serviceDialog.getNumberOfSelectedServiceContainersDisplayed(), availableVehiclePartOptions,
				"The number of service displayed containers differs from the number of assigned options");
		serviceDialog.clickCancelServicesButton();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void verifyAllVehiclePartsCanBeUnassignedFromServicesInSR(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsListPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsListPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsListPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsListPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		serviceDialog.checkRandomServiceOption();
		serviceDialog.clickAddServiceOption();
		Assert.assertTrue(serviceDialog.isSelectedServiceContainerDisplayed(), "The service container is not displayed");
        serviceDialog.clickVehiclePart();
        ServiceRequestListServiceVehiclePartDialog vehiclePartDialog = new ServiceRequestListServiceVehiclePartDialog(webdriver);
		int availableVehiclePartOptions = vehiclePartDialog.getAvailableVehiclePartOptions();
		vehiclePartDialog.clickMoveAllToTheRight();
		Assert.assertEquals(vehiclePartDialog.getAvailableVehiclePartOptions(), 0,
				"The available vehicle Part Options have not been reduced by the number of assigned options " +
						"after moving all options to the right");
		Assert.assertEquals(vehiclePartDialog.getAssignedVehiclePartOptions(), availableVehiclePartOptions,
				"The assigned vehicle Part Options have not been increased by the number of available options" +
						"after moving all options to the right");

		vehiclePartDialog.clickMoveAllToTheLeft();
		Assert.assertEquals(vehiclePartDialog.getAvailableVehiclePartOptions(), availableVehiclePartOptions,
				"The available vehicle Part Options have not been increased by the number of assigned options " +
						"after moving all options to the left");
		Assert.assertEquals(vehiclePartDialog.getAssignedVehiclePartOptions(), 0,
				"The assigned vehicle Part Options have not been reduced by the number of available options" +
						"after moving all options to the left");


		vehiclePartDialog.clickServiceVehiclePartOkButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickDoneServicesButton();
		serviceRequestsListPage.saveNewServiceRequest();


		serviceRequestsListPage.selectFirstServiceRequestFromList();
		serviceRequestsListPage.clickServiceEditButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickCancelServicesButton();
	}

	@Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
	public void verifyVehiclePartWillRemainUnassignedToServicesInSRAfterClickingTheCancelButton(String rowID, String description, JSONObject testData) {

		BOoperationsSRdata data = JSonDataParser.getTestDataFromJson(testData, BOoperationsSRdata.class);
		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);

        backOfficeHeader.clickOperationsLink();
        OperationsWebPage operationsPage = new OperationsWebPage(webdriver);
        operationsPage.clickNewServiceRequestList();
        ServiceRequestsListWebPage serviceRequestsListPage = new ServiceRequestsListWebPage(webdriver);
		serviceRequestsListPage.selectAddServiceRequestDropDown(data.getServiceRequestType());
		serviceRequestsListPage.clickAddServiceRequestButton();
		ServiceRequestListServiceDialog serviceDialog = new ServiceRequestListServiceDialog(webdriver);
		serviceRequestsListPage.clickServiceEditButton();
		serviceDialog.openServicesDropDown();
		serviceDialog.checkRandomServiceOption();
		serviceDialog.clickAddServiceOption();
		Assert.assertTrue(serviceDialog.isSelectedServiceContainerDisplayed(), "The service container is not displayed");
        serviceDialog.clickVehiclePart();
        ServiceRequestListServiceVehiclePartDialog vehiclePartDialog = new ServiceRequestListServiceVehiclePartDialog(webdriver);
		int availableVehiclePartOptions = vehiclePartDialog.getAvailableVehiclePartOptions();
		int assignedVehiclePartOptions = vehiclePartDialog.getAssignedVehiclePartOptions();

		vehiclePartDialog.selectRandomAvailableVehiclePartOption();
		vehiclePartDialog.clickMoveToTheRight();
		Assert.assertEquals(availableVehiclePartOptions - 1, vehiclePartDialog.getAvailableVehiclePartOptions(),
				"The available vehicle Part Options have not been reduced after moving to the right");
		Assert.assertEquals(assignedVehiclePartOptions + 1, vehiclePartDialog.getAssignedVehiclePartOptions(),
				"The assigned vehicle Part Options have not been increased after moving to the right");


		vehiclePartDialog.clickServiceVehiclePartCancelButton();
		serviceDialog.clickVehiclePart();
		Assert.assertEquals(availableVehiclePartOptions, vehiclePartDialog.getAvailableVehiclePartOptions(),
				"The available vehicle Part Options have been changed after clicking the 'Cancel' button");
		Assert.assertEquals(assignedVehiclePartOptions, vehiclePartDialog.getAssignedVehiclePartOptions(),
				"The assigned vehicle Part Options have been changed after clicking the 'Cancel' button");
		vehiclePartDialog.clickServiceVehiclePartOkButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickDoneServicesButton();
		serviceRequestsListPage.saveNewServiceRequest();


		serviceRequestsListPage.selectFirstServiceRequestFromList();
		serviceRequestsListPage.clickServiceEditButton();
		serviceDialog.verifyOneServiceContainerIsDisplayed();
		serviceDialog.clickCancelServicesButton();
	}

//	//TODO
//	//@Test(testName = "Test Case 65521:Operation - Service Request - Services add notes")
//	public void testServicerequestServicesAddNotes() {
//		BackOfficeHeaderPanel backOfficeHeader = new BackOfficeHeaderPanel(webdriver);
//		OperationsWebPage operationsPage = backOfficeHeader.clickOperationsLink();
//		ServiceRequestsListWebPage serviceRequestsWebPage = operationsPage.clickNewServiceRequestList();
//		serviceRequestsWebPage.selectAddServiceRequestsComboboxValue(data.getServiceRequestType());
//		serviceRequestsWebPage.clickAddServiceRequestButton();
//		ServiceRequestListServiceDialog serviceDialog = serviceRequestsWebPage.clickServiceEditButton();
//		serviceRequestsWebPage.addServicesToServiceRequest("Zak_Money_Multiple","Zak_Labor_Multiple");
//		ServiceRequestListServiceDialog serviceDialog = serviceRequestsWebPage.clickServiceEditButton();
//		Assert.assertTrue(serviceRequestsWebPage.checkAddedServices("Zak_Money_Multiple","Zak_Labor_Multiple"));
//	}
}