package com.cyberiansoft.test.bo.testcases;

import com.automation.remarks.testng.VideoListener;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeHeaderPanel;
import com.cyberiansoft.test.bo.pageobjects.webpages.TimesheetsSectionWebPage;
import com.cyberiansoft.test.bo.pageobjects.webpages.TimesheetsWebPage;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(VideoListener.class)
public class BackOfficeTimesheetsTestCases extends BaseTestCase {

    private static final String DATA_FILE = "src/test/java/com/cyberiansoft/test/bo/data/BOOperationsData.json";

    @BeforeClass()
    public void settingUp() {
        JSONDataProvider.dataFile = DATA_FILE;
    }

	@Test(testName = "Test Case 65561:Timesheets - Timesheets Monday Sunday switch")
	public void checkMondaySundaySwitch(){
		BackOfficeHeaderPanel backofficeheader = PageFactory.initElements(webdriver,
				BackOfficeHeaderPanel.class);
		TimesheetsWebPage timesheetspage = backofficeheader.clickTimesheetsLink();
		TimesheetsSectionWebPage timesheetsSectionPage = timesheetspage.clickTimesheets();
		timesheetsSectionPage.setFromDate("10/02/17");
		timesheetsSectionPage.setToDate("10/08/17");
		timesheetsSectionPage.setTeam("01_TimeRep_team");
		timesheetsSectionPage.clickFindButton();
		timesheetsSectionPage.expandFirstEmployee();
		Assert.assertTrue(timesheetsSectionPage.checkStartingDay("Mon"));
		timesheetsSectionPage.setFromDate("10/03/17");
		timesheetsSectionPage.clickFindButton();
		timesheetsSectionPage.expandFirstEmployee();
		Assert.assertTrue(timesheetsSectionPage.checkStartingDay("Tue"));
	}
}
