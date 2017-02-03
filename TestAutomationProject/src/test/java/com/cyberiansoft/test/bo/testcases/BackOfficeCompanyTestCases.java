package com.cyberiansoft.test.bo.testcases;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeHeaderPanel;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeLoginWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.CompanyWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.EmployeesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.ExpensesTypesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.InsuranceCompaniesWePpage;
import com.cyberiansoft.test.bo.pageobjects.webpages.InvoiceTypesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.JobsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.NewInvoiceTypeDialogWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.PriceMatricesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.QuestionsFormsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.ServiceAdvisorsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.ServicesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.SuppliesWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.TeamsWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.UsersWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.VehiclePartsWebPage;

public class BackOfficeCompanyTestCases extends BaseTestCase {
		
		@BeforeMethod
		@Parameters({ "backoffice.url", "user.name", "user.psw" })
		public void BackOfficeLogin(String backofficeurl,
				String userName, String userPassword) throws InterruptedException {
			webdriverGotoWebPage(backofficeurl);
			BackOfficeLoginWebPage loginpage = PageFactory.initElements(webdriver,
					BackOfficeLoginWebPage.class);
			loginpage.UserLogin(userName, userPassword);
			Thread.sleep(2000);
		}
		
		@AfterMethod
		public void BackOfficeLogout() throws InterruptedException {
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			backofficeheader.clickLogout();
			//Thread.sleep(3000);
		}
		
		@Test(description = "Test Case 15245:Company-Users: Search")
		public void testCompanyUsersSearch() throws Exception {

			final String userfirstname = "Delete";
			final String userlastname = "Test";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			UsersWebPage userspage = companypage.clickUsersLink();
			
			userspage.verifyTabsAreVisible();
			userspage.verifyUsersTableColumnsAreVisible();
			
			Assert.assertEquals("1", userspage.getCurrentlySelectedPageNumber());
			Assert.assertEquals("1", userspage.getGoToPageFieldValue());
			
			userspage.setPageSize("1");
			Assert.assertEquals(1, userspage.getUsersTableRowCount());
			
			String lastpagenumber = userspage.getLastPageNumber();
			userspage.clickGoToLastPage();
			Assert.assertEquals(lastpagenumber, userspage.getGoToPageFieldValue());
			
			userspage.clickGoToFirstPage();
			Thread.sleep(1000);
			Assert.assertEquals("1", userspage.getGoToPageFieldValue());
			
			userspage.clickGoToNextPage();
			Assert.assertEquals("2", userspage.getGoToPageFieldValue());
			
			userspage.clickGoToPreviousPage();
			Assert.assertEquals("1", userspage.getGoToPageFieldValue());

			userspage.setPageSize("999");
			Assert.assertEquals(userspage.MAX_TABLE_ROW_COUNT_VALUE, Integer.valueOf(userspage.getUsersTableRowCount()));
			
			List<String> usernamesact = userspage.getActiveUserNames();
			userspage.clickArchivedTab();
			Assert.assertEquals("", userspage.verifyUserNamesDuplicatesArchived(usernamesact));
			userspage.clickActiveTab();
			
			userspage.makeSearchPanelVisible();
			userspage.setSearchUserParameter(userfirstname);
			userspage.clickFindButton();
			userspage.archiveUser(userfirstname, userlastname);
			userspage.clickArchivedTab();
			Assert.assertTrue(userspage.isUserArchived(userfirstname, userlastname));
			userspage.unarchiveUser(userfirstname, userlastname);
			userspage.clickActiveTab();
			Assert.assertTrue(userspage.isUserActive(userfirstname, userlastname));
			
			userspage.makeSearchPanelVisible();
			userspage.setSearchUserParameter(userfirstname.substring(0, 4));
			userspage.clickFindButton();
			
			Assert.assertTrue(Integer.valueOf(userspage.getUsersTableRowCount()) > 0);
			userspage.isUserActive(userfirstname, userlastname);
		}
		
		@Test(description = "Test Case 15265:Company-Employees: Search")
		public void testCompanyEmployeesSearch() throws Exception {

			final String employeefirstname = "Employee";
			final String employeelastname = "Employee";
			final String team = "Test Team";
			final String employeename = employeefirstname + " " + employeelastname;
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			EmployeesWebPage employeespage = companypage.clickEmployeesLink();
			
			employeespage.verifyTabsAreVisible();
			employeespage.verifyEmployeesTableColumnsAreVisible();
			
			Assert.assertEquals("1", employeespage.getCurrentlySelectedPageNumber());
			Assert.assertEquals("1", employeespage.getGoToPageFieldValue());
			
			employeespage.setPageSize("1");
			Assert.assertEquals(1, employeespage.getEmployeesTableRowCount());
			
			String lastpagenumber = employeespage.getLastPageNumber();
			employeespage.clickGoToLastPage();
			Assert.assertEquals(lastpagenumber, employeespage.getGoToPageFieldValue());
			
			employeespage.clickGoToFirstPage();
			Thread.sleep(1000);
			Assert.assertEquals("1", employeespage.getGoToPageFieldValue());
			
			employeespage.clickGoToNextPage();
			Assert.assertEquals("2", employeespage.getGoToPageFieldValue());
			
			employeespage.clickGoToPreviousPage();
			Assert.assertEquals("1", employeespage.getGoToPageFieldValue());

			employeespage.setPageSize("999");
			Assert.assertEquals(employeespage.MAX_TABLE_ROW_COUNT_VALUE, Integer.valueOf(employeespage.getEmployeesTableRowCount()));
			
			employeespage.archiveEmployee(employeefirstname, employeelastname);		
			employeespage.clickArchivedTab();
			employeespage.makeSearchPanelVisible();
			employeespage.setSearchUserParameter(employeelastname);
			employeespage.clickFindButton();
			Assert.assertTrue(employeespage.isArchivedEmployeeExists(employeefirstname, employeelastname));
			employeespage.unarchiveEmployee(employeefirstname, employeelastname);
			employeespage.clickActiveTab();
			Assert.assertTrue(employeespage.isActiveEmployeeExists(employeefirstname, employeelastname));
			
			employeespage.makeSearchPanelVisible();
			employeespage.selectSearchTeam(team);
			employeespage.setSearchUserParameter(employeename.substring(0, 4).toLowerCase());
			employeespage.clickFindButton();
			
			Assert.assertTrue(Integer.valueOf(employeespage.getEmployeesTableRowCount()) > 0);
			employeespage.isActiveEmployeeExists(employeefirstname, employeelastname);
		}
		

		@Test(description = "Test Case 15323:Company- Services: Search")
		public void testCompanyServicesSearch() throws Exception {

			final String pricetype = "Percentage";
			final String servicetype = "Detail";
			final String servicename = "Marg_For_Bundle";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			ServicesWebPage servicespage = companypage.clickServicesLink();
			
			servicespage.verifyServicesTableColumnsAreVisible();
			
			Assert.assertEquals("1", servicespage.getCurrentlySelectedPageNumber());
			Assert.assertEquals("1", servicespage.getGoToPageFieldValue());
			
			servicespage.setPageSize("1");
			Assert.assertEquals(1, servicespage.getServicesTableRowsCount());
			
			String lastpagenumber = servicespage.getLastPageNumber();
			servicespage.clickGoToLastPage();
			Assert.assertEquals(lastpagenumber, servicespage.getGoToPageFieldValue());
			
			servicespage.clickGoToFirstPage();
			Thread.sleep(1000);
			Assert.assertEquals("1", servicespage.getGoToPageFieldValue());
			
			servicespage.clickGoToNextPage();
			Assert.assertEquals("2", servicespage.getGoToPageFieldValue());
			
			servicespage.clickGoToPreviousPage();
			Assert.assertEquals("1", servicespage.getGoToPageFieldValue());

			servicespage.setPageSize("999");
			Assert.assertEquals(servicespage.MAX_TABLE_ROW_COUNT_VALUE, Integer.valueOf(servicespage.getServicesTableRowsCount()));
			
			servicespage.makeSearchPanelVisible();
			servicespage.selectSearchServiceType(servicetype);
			servicespage.selectSearchPriceType(pricetype);
			servicespage.setServiceSearchCriteria(servicename.substring(0, 4).toLowerCase());
			servicespage.clickFindButton();
			
			servicespage.isActiveServiceExists(servicename);
		}
		
		@Test(description = "Test Case 15539:Company - Teams: Search")
		public void testCompanyTeamsSearch() throws Exception {

			final String teamlocation = "Test Team";
			final String _type = "Internal";
			final String timezone = "Pacific Standard Time";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			TeamsWebPage teamspage = companypage.clickTeamsLink();

			teamspage.verifyTeamsTableColumnsAreVisible();
			
			Assert.assertEquals("1", teamspage.getCurrentlySelectedPageNumber());
			Assert.assertEquals("1", teamspage.getGoToPageFieldValue());
			
			teamspage.setPageSize("1");
			Assert.assertEquals(1, teamspage.getTeamsTableRowsCount());
			
			String lastpagenumber = teamspage.getLastPageNumber();
			teamspage.clickGoToLastPage();
			Assert.assertEquals(lastpagenumber, teamspage.getGoToPageFieldValue());
			
			teamspage.clickGoToFirstPage();
			Assert.assertEquals("1", teamspage.getGoToPageFieldValue());
			
			teamspage.clickGoToNextPage();
			Assert.assertEquals("2", teamspage.getGoToPageFieldValue());
			
			teamspage.clickGoToPreviousPage();
			Assert.assertEquals("1", teamspage.getGoToPageFieldValue());

			if (Integer.valueOf(lastpagenumber) < 50) {
				teamspage.setPageSize(lastpagenumber);
				Assert.assertEquals(Integer.valueOf(lastpagenumber), Integer.valueOf(teamspage.getTeamsTableRowsCount()));
			} else {
				teamspage.setPageSize("999");
				Assert.assertEquals(teamspage.MAX_TABLE_ROW_COUNT_VALUE, Integer.valueOf(teamspage.getTeamsTableRowsCount()));				
			}
			
			teamspage.makeSearchPanelVisible();
			teamspage.setTeamLocationSearchCriteria(teamlocation.substring(0, 8).toLowerCase());
			teamspage.selectSearchType(_type);
			teamspage.selectSearchTimeZone(timezone);
			teamspage.clickFindButton();
			
			Assert.assertTrue(Integer.valueOf(teamspage.getTeamsTableRowsCount()) >= 1);
			teamspage.verifySearchResultsByTeamLocation(teamlocation);
		}
		
		@Test(description = "Test Case 15541:Company - Jobs: Search")
		public void testCompanyJobsSearch() throws Exception {

			final String customer = "001 - Test Company";
			final String _job = "Alex2";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			JobsWebPage jobspage = companypage.clickJobsLink();

			jobspage.verifyJobsTableColumnsAreVisible();
			
			Assert.assertEquals("1", jobspage.getCurrentlySelectedPageNumber());
			Assert.assertEquals("1", jobspage.getGoToPageFieldValue());
			
			jobspage.setPageSize("1");
			Assert.assertEquals(1, jobspage.getJobsTableRowsCount());
			
			String lastpagenumber = jobspage.getLastPageNumber();
			jobspage.clickGoToLastPage();
			Assert.assertEquals(lastpagenumber, jobspage.getGoToPageFieldValue());
			
			jobspage.clickGoToFirstPage();
			Assert.assertEquals("1", jobspage.getGoToPageFieldValue());
			
			jobspage.clickGoToNextPage();
			Assert.assertEquals("2", jobspage.getGoToPageFieldValue());
			
			jobspage.clickGoToPreviousPage();
			Assert.assertEquals("1", jobspage.getGoToPageFieldValue());
			if (Integer.valueOf(lastpagenumber) < 50) {
				jobspage.setPageSize(lastpagenumber);
				Assert.assertEquals(Integer.valueOf(lastpagenumber), Integer.valueOf(jobspage.getJobsTableRowsCount()));
			} else {
				jobspage.setPageSize("999");
				Assert.assertEquals(jobspage.MAX_TABLE_ROW_COUNT_VALUE, Integer.valueOf(jobspage.getJobsTableRowsCount()));				
			}

			jobspage.makeSearchPanelVisible();
			jobspage.setJobSearchCriteria(_job.substring(0, 2).toLowerCase());
			jobspage.selectSearchCustomer(customer);
			jobspage.clickFindButton();
			
			Assert.assertEquals(Integer.valueOf(1), Integer.valueOf(jobspage.getJobsTableRowsCount()));
			jobspage.isJobExists(_job);
		}

		@Test(description = "Test Case 17284:Company - Insurance Companies")
		public void testCompanyJobsInsuranceCompanies() throws Exception {

			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			InsuranceCompaniesWePpage insurancecompaniespage = companypage.clickInsuranceCompaniesLink();

			insurancecompaniespage.verifyInsuranceCompaniesTableColumnsAreVisible();			
			Assert.assertTrue(insurancecompaniespage.isAddInsuranceCompanyButtonExists());

		}
		
		@Test(description = "Test Case 18092:Company -Service Advisors")
		public void testCompanyServiceAdvisors() throws Exception {

			final String _client = "Sterling Collision";
			final String firstname = "Cameron";
			final String lastname = "Minor";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			ServiceAdvisorsWebPage serviceadvisorspage = companypage.clickServiceAdvisorsLink();

			serviceadvisorspage.verifyServiceAdvisorsTableColumnsAreVisible();
			
			Assert.assertEquals("1", serviceadvisorspage.getCurrentlySelectedPageNumber());
			Assert.assertEquals("1", serviceadvisorspage.getGoToPageFieldValue());
			
			serviceadvisorspage.setPageSize("1");
			Assert.assertEquals(1, serviceadvisorspage.getServiceAdvisorsTableRowsCount());
			
			String lastpagenumber = serviceadvisorspage.getLastPageNumber();
			serviceadvisorspage.clickGoToLastPage();
			Assert.assertEquals(lastpagenumber, serviceadvisorspage.getGoToPageFieldValue());
			
			serviceadvisorspage.clickGoToFirstPage();
			Assert.assertEquals("1", serviceadvisorspage.getGoToPageFieldValue());
			
			serviceadvisorspage.clickGoToNextPage();
			Assert.assertEquals("2", serviceadvisorspage.getGoToPageFieldValue());
			
			serviceadvisorspage.clickGoToPreviousPage();
			Assert.assertEquals("1", serviceadvisorspage.getGoToPageFieldValue());

			serviceadvisorspage.setPageSize("999");
			Assert.assertEquals(serviceadvisorspage.MAX_TABLE_ROW_COUNT_VALUE, Integer.valueOf(serviceadvisorspage.getServiceAdvisorsTableRowsCount()));
			
			
			serviceadvisorspage.makeSearchPanelVisible();
			serviceadvisorspage.setUserSearchCriteria(firstname);
			serviceadvisorspage.selectSearchClient(_client);
			serviceadvisorspage.clickFindButton();
			
			Assert.assertTrue(serviceadvisorspage.getServiceAdvisorsTableRowsCount() > 0);
			serviceadvisorspage.isServiceAdvisorExists(firstname, lastname);
		}
		
		@Test(description = "Test Case 18799:Company- Question Forms")
		public void testCompanyQuestionForms() throws Exception {

			final String questionsectionname = "Test Question Section";
			final String questionname = "New Question";
			final String questionformname = "Test Question Form";
			
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			QuestionsFormsWebPage questionsformspage = companypage.clickQuestionsFormsLink();
			questionsformspage.verifyQuestionFormsTableColumnsAreVisible();
			questionsformspage.verifyQuestionSectionsTableColumnsAreVisible();
			questionsformspage.verifyPrintTemplatesTableColumnsAreVisible();	
			questionsformspage.createQuestionSection(questionsectionname);
			questionsformspage.addQuestionForQuestionSection(questionsectionname, questionname);
			
			if (questionsformspage.isQuestionFormExists(questionformname)) {
				questionsformspage.deleteQuestionForm(questionformname);
			}
			questionsformspage.createQuestionForm(questionformname);
			questionsformspage.editAndAssignSectionToQuestionForm(questionformname, questionsectionname);
			questionsformspage.verifyQuestionIsAssignedToQuestionFormViaPreview(questionformname, questionname);
			
			questionsformspage.deleteQuestionForm(questionformname);
			Assert.assertFalse(questionsformspage.isQuestionFormExists(questionformname));
			questionsformspage.deleteQuestionSections(questionsectionname);
			Assert.assertFalse(questionsformspage.isQuestionSectionExists(questionsectionname));
		}
		
		@Test(description = "Test Case 18800:Company -Supplies")
		public void testCompanySupplies() throws Exception {

			final String supplyname = "Test Supply";
			final String supplynameedited = "Test Supply Edited";
			
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			SuppliesWebPage suppliespage = companypage.clickSuppliesLink();
			
			suppliespage.verifySuppliesTableColumnsAreVisible();
			suppliespage.createNewSupply(supplyname);
			suppliespage.setSupplyNewName(supplyname, supplynameedited);
			suppliespage.deleteSupply(supplynameedited);
			Assert.assertFalse(suppliespage.isSupplyExists(supplynameedited));
		}
		
		@Test(description = "Test Case 18801:Company -Expenses Types")
		public void testCompanyExpensesTypes() throws Exception {

			final String expensetype = "Test Type";
			final String newexpensetypename = "Test Type Edited";
						
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			ExpensesTypesWebPage expensestypespage = companypage.clickExpensesTypesLink();
			
			expensestypespage.verifyExpensesTypesColumnsAreVisible();
			expensestypespage.createNewExpenseType(expensetype);
			expensestypespage.setExpenseTypeNewName(expensetype, newexpensetypename);
			expensestypespage.deleteExpenseType(newexpensetypename);			
			Assert.assertFalse(expensestypespage.isExpenseTypeExists(newexpensetypename));
			if (expensestypespage.isExpenseTypeExists(expensetype)) {
				expensestypespage.deleteExpenseType(expensetype);			
				Assert.assertFalse(expensestypespage.isExpenseTypeExists(expensetype));
			}
		}
		
		@Test(description = "Test Case 18802:Company -Vehicle Parts")
		public void testCompanyVehicleParts() throws Exception {

			final String vehiclepart = "Test Part";
			final String newvehiclepartname = "Test Vehicle Part";
						
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();
			VehiclePartsWebPage vehiclepartspage = companypage.clickVehiclePartsLink();
			vehiclepartspage.verifyVehiclePartsColumnsAreVisible();
			vehiclepartspage.createNewVehicleParWithAllServicesSelected(vehiclepart);
			vehiclepartspage.setVehiclePartNewName(vehiclepart, newvehiclepartname);
			vehiclepartspage.deleteVehiclePart(newvehiclepartname);
			Assert.assertFalse(vehiclepartspage.isVehiclePartExists(newvehiclepartname));
		}
		
		@Test(testName = "Test Case 26726:Company- Employees: Archive", description = "Company- Employees: Archive")
		public void testCompanyEmployeesArchive() throws Exception {

			final String employeefirstname = "archive";
			final String employeelastname = "unarchive";
			final String employeename = employeefirstname + " " + employeelastname;
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			EmployeesWebPage employeespage = companypage.clickEmployeesLink();
			
			employeespage.makeSearchPanelVisible();
			employeespage.setSearchUserParameter(employeename.substring(0, 5));
			employeespage.clickFindButton();
			for (int i = 0; i < 3; i++) {
				Thread.sleep(2000);
				employeespage.archiveEmployee(employeefirstname, employeelastname);		
				employeespage.clickArchivedTab();
			
				employeespage.setSearchUserParameter(employeename.substring(0, 5));
				employeespage.clickFindButton();
				Assert.assertTrue(employeespage.isArchivedEmployeeExists(employeefirstname, employeelastname));
				employeespage.unarchiveEmployee(employeefirstname, employeelastname);
				employeespage.clickActiveTab();
			
				employeespage.setSearchUserParameter(employeename.substring(0, 5));
				employeespage.clickFindButton();
				Assert.assertTrue(employeespage.isActiveEmployeeExists(employeefirstname, employeelastname));
			}
		}
		
		@Test(testName = "Test Case 26727:Company- Services: Archive", description = "Company- Services: Archive")
		public void testCompanyServicesArchive() throws Exception {

			final String servicename = "test12";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			ServicesWebPage servicespage = companypage.clickServicesLink();
			servicespage.makeSearchPanelVisible();
			servicespage.setServiceSearchCriteria(servicename);
			servicespage.clickFindButton();
			
			for (int i = 0; i < 3; i++) {
				Thread.sleep(1000);
				servicespage.archiveService(servicename);
				servicespage.clickArchivedTab();
				Assert.assertTrue(servicespage.isArchivedServiceExists(servicename));
				servicespage.unarchiveService(servicename);
			
				servicespage.clickActiveTab();
				Assert.assertTrue(servicespage.isActiveServiceExists(servicename));
			}
		}
		
		@Test(testName = "Test Case 26730:Company- Users: Archive", description = "Company- Users: Archive")
		public void testCompanyUsersArchive() throws Exception {

			final String userfirstname = "Archive";
			final String userlastname = "User";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			UsersWebPage userspage = companypage.clickUsersLink();
			userspage.makeSearchPanelVisible();
			userspage.setSearchUserParameter(userlastname);
			userspage.clickFindButton();
			if (!userspage.isUserActive(userfirstname, userlastname)) {
				userspage.clickArchivedTab();
				userspage.unarchiveUser(userfirstname, userlastname);
			}
			
			
			for (int i = 0; i < 3; i++) {
				userspage.archiveUser(userfirstname, userlastname);
				userspage.clickArchivedTab();
				Assert.assertTrue(userspage.isUserArchived(userfirstname, userlastname));
				userspage.unarchiveUser(userfirstname, userlastname);
			
				userspage.clickActiveTab();
				Assert.assertTrue(userspage.isUserActive(userfirstname, userlastname));
			}
		}
		
		@Test(testName = "Test Case 27455:Company - Invoice type: Verify dependency bwn Visible and Required options", description = "Company - Invoice type: Verify dependency bwn Visible and Required options")
		public void testCompanyInvoiceTypeVerifyDependencyBetweenVisibleAndRequiredOptions() throws Exception {
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();

			InvoiceTypesWebPage invoicestypespage = companypage.clickInvoiceTypesLink();
			NewInvoiceTypeDialogWebPage newinvoicetypedialog = invoicestypespage.clickAddInvoiceTypeButton();
			Thread.sleep(1000);
			Assert.assertFalse(newinvoicetypedialog.isRequiredCheckBoxVisible());
			newinvoicetypedialog.waitABit(500);
			newinvoicetypedialog.selectVisibleCheckBox();
			Assert.assertTrue(newinvoicetypedialog.isRequiredCheckBoxVisible());
			newinvoicetypedialog.unselectVisibleCheckBox();
			Assert.assertFalse(newinvoicetypedialog.isRequiredCheckBoxVisible());
			newinvoicetypedialog.clickCancelAddInvoiceTypeButton();
		}
		
		@Test(testName = "Test Case 24998:Company - Price Matrix: Verify that on Matrix panel Admin can see in Available services only services selected on Vehicle parts", description = "Company - Price Matrix: Verify that on Matrix panel Admin can see in Available services only services selected on Vehicle parts")
		public void testCompanyPriceMatrixVerifyThatOnMatrixPanelAdminCanSeeInAvailableServicesOnlyServicesSelectedOnVehicleParts() throws Exception {
			
			final String vehiclepart = "Vehicle Part 1";
			final String pricematrix = "Test Matrix mobile1";
			
			final String servicename1 = "Test service zayats";
			final String servicename2 = "VPS1";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();
			VehiclePartsWebPage vehiclepartspage = companypage.clickVehiclePartsLink();
			vehiclepartspage.clickEditButtonForVehiclePart(vehiclepart);
			Assert.assertEquals(vehiclepart, vehiclepartspage.getVehiclePartNameField().getAttribute("value"));
			Assert.assertEquals(0, vehiclepartspage.getAssignedServicesList().size());
			vehiclepartspage.cancelNewVehiclePart();
			
			companypage = backofficeheader.clickCompanyLink();
			PriceMatricesWebPage pricematriceswebpage = companypage.clickPriceMatricesLink();
			pricematriceswebpage.clickPricesForPriceMatrix(pricematrix);
			vehiclepartspage = pricematriceswebpage.clickPricesVehiclePartLink(vehiclepart);
			Assert.assertEquals(0, vehiclepartspage.getAssignedServicesList().size());
			vehiclepartspage.cancelNewVehiclePart();
			
			companypage = backofficeheader.clickCompanyLink();
			vehiclepartspage = companypage.clickVehiclePartsLink();
			vehiclepartspage.clickEditButtonForVehiclePart(vehiclepart);
			vehiclepartspage.assignServiceForVehiclePart(servicename1);
			vehiclepartspage.assignServiceForVehiclePart(servicename2);
			vehiclepartspage.saveNewVehiclePart();
			
			companypage = backofficeheader.clickCompanyLink();
			pricematriceswebpage = companypage.clickPriceMatricesLink();
			pricematriceswebpage.clickPricesForPriceMatrix(pricematrix);
			vehiclepartspage = pricematriceswebpage.clickPricesVehiclePartLink(vehiclepart);
			Assert.assertEquals(2, vehiclepartspage.getAvailableServicesList().size());
			vehiclepartspage.selectAvailableServiceForVehiclePart(servicename1);
			vehiclepartspage.selectAvailableServiceForVehiclePart(servicename2);
			vehiclepartspage.cancelNewVehiclePart();
			
			companypage = backofficeheader.clickCompanyLink();
			vehiclepartspage = companypage.clickVehiclePartsLink();
			vehiclepartspage.clickEditButtonForVehiclePart(vehiclepart);
			vehiclepartspage.unassignServiceForVehiclePart(servicename1);
			vehiclepartspage.unassignServiceForVehiclePart(servicename2);
			vehiclepartspage.saveNewVehiclePart();
		}
		
		@Test(testName = "Test Case 25004:Company - Price Matrix: verify that By default all selected services on Vehicle Part will be assigned to Matrix Panel", description = "Company - Price Matrix: verify that By default all selected services on Vehicle Part will be assigned to Matrix Panel")
		public void testCompanyPriceMatrixVerifyThatByDefaultAllSelectedServicesOnVehiclePartWillBeAssignedToMatrixPanel() throws Exception {
			
			final String vehiclepart = "VP with assigned services";
			
			final String servicename1 = "Dye";
			final String servicename2 = "VPS1";
			final String servicename3 = "Wheel Repair1";
			
			final String pricematrix = "New Matrix with assigned services";
			final String pricematrixservice = "Matrix Service";
			final String[] damagesevereties = {"LIGHT", "MEDIUM"};
			final String[] damagesizes = {"DIME", "NKL"};
			final String pricematrixvehiclepart = "VP with assigned services";
			
			BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
					BackOfficeHeaderPanel.class);
			CompanyWebPage companypage = backofficeheader.clickCompanyLink();
			VehiclePartsWebPage vehiclepartspage = companypage.clickVehiclePartsLink();
			
			vehiclepartspage.clickEditButtonForVehiclePart(vehiclepart);
			Assert.assertEquals(vehiclepart, vehiclepartspage.getVehiclePartNameField().getAttribute("value"));
			vehiclepartspage.selectAssignedServiceForVehiclePart(servicename1);
			vehiclepartspage.selectAssignedServiceForVehiclePart(servicename2);
			vehiclepartspage.selectAssignedServiceForVehiclePart(servicename3);
			Assert.assertEquals(3, vehiclepartspage.getAssignedServicesList().size());
			vehiclepartspage.saveNewVehiclePart();
			
			companypage = backofficeheader.clickCompanyLink();
			PriceMatricesWebPage pricematriceswebpage = companypage.clickPriceMatricesLink();
			pricematriceswebpage.clickAddPriceMarixButton();
			pricematriceswebpage.setPriceMarixName(pricematrix);
			pricematriceswebpage.selectPriceMarixService(pricematrixservice);
			for (int i = 0; i < damagesevereties.length; i++) {
				pricematriceswebpage.assignPriceMatrixDamageSeverity(damagesevereties[i]);
			}
			for (int i = 0; i < damagesizes.length; i++) {
				pricematriceswebpage.assignPriceMatrixDamageSize(damagesizes[i]);
			}
			pricematriceswebpage.assignPriceMatrixVehiclePart(pricematrixvehiclepart);
			pricematriceswebpage.saveNewPriceMatrix();
			
			pricematriceswebpage.clickPricesForPriceMatrix(pricematrix);
			vehiclepartspage = pricematriceswebpage.clickPricesVehiclePartLink(vehiclepart);
			vehiclepartspage.selectAssignedServiceForVehiclePart(servicename1);
			vehiclepartspage.selectAssignedServiceForVehiclePart(servicename2);
			vehiclepartspage.selectAssignedServiceForVehiclePart(servicename3);
			Assert.assertEquals(3, vehiclepartspage.getAssignedServicesList().size());
			Assert.assertEquals(0, vehiclepartspage.getAvailableServicesList().size());
			vehiclepartspage.cancelNewVehiclePart();
			
			companypage = backofficeheader.clickCompanyLink();
			pricematriceswebpage = companypage.clickPriceMatricesLink();
			pricematriceswebpage.deletePriceMatrix(pricematrix);
		}
}
