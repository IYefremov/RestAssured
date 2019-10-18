package com.cyberiansoft.test.vnextbo.testcases.Inspections;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.dataclasses.vNextBO.VNextBOInspectionsDetailsData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.config.VNextBOConfigInfo;
import com.cyberiansoft.test.vnextbo.interactions.leftMenuPanel.VNextBOLeftMenuInteractions;
import com.cyberiansoft.test.vnextbo.screens.*;
import com.cyberiansoft.test.vnextbo.screens.Inspections.VNextBOInspectionMaximizedImageDialog;
import com.cyberiansoft.test.vnextbo.screens.Inspections.VNextBOInspectionNoteDialog;
import com.cyberiansoft.test.vnextbo.steps.VNextBOHeaderPanelSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionMaximizedImageDialogSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionNoteDialogSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsPageSteps;
import com.cyberiansoft.test.vnextbo.testcases.BaseTestCase;
import com.cyberiansoft.test.vnextbo.verifications.Inspections.VNextBOInspectionMaximizedImageDialogValidations;
import com.cyberiansoft.test.vnextbo.verifications.Inspections.VNextBOInspectionNoteDialogValidations;
import com.cyberiansoft.test.vnextbo.verifications.Inspections.VNextBOInspectionsPageValidations;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.*;

import static com.cyberiansoft.test.vnextbo.utils.WebDriverUtils.webdriverGotoWebPage;

public class VNextBOInspectionsDetailsTests extends BaseTestCase {

    private static final String DATA_FILE = "src/test/java/com/cyberiansoft/test/vnextbo/data/Inspections/VNextBOInspectionsDetailsData.json";
    private VNextBOLoginScreenWebPage loginPage;

    @BeforeClass
    public void settingUp() {

        JSONDataProvider.dataFile = DATA_FILE;
        browserType = BaseUtils.getBrowserType(VNextBOConfigInfo.getInstance().getDefaultBrowser());
        try {
            DriverBuilder.getInstance().setDriver(browserType);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        webdriver = DriverBuilder.getInstance().getDriver();

        webdriverGotoWebPage(VNextBOConfigInfo.getInstance().getVNextBOCompanionappURL());
        String userName = VNextBOConfigInfo.getInstance().getVNextBONadaMail();
        String userPassword = VNextBOConfigInfo.getInstance().getVNextBOPassword();

        loginPage = new VNextBOLoginScreenWebPage();
        loginPage.userLogin(userName, userPassword);
        VNextBOLeftMenuInteractions leftMenuInteractions = new VNextBOLeftMenuInteractions();
        leftMenuInteractions.selectInspectionsMenu();
    }

    @AfterClass
    public void BackOfficeLogout() {
        new VNextBOHeaderPanelSteps().logout();

        if (DriverBuilder.getInstance().getDriver() != null) {
            DriverBuilder.getInstance().quitDriver();
        }
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSeeInspectionImage(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsDetailsData data = JSonDataParser.getTestDataFromJson(testData, VNextBOInspectionsDetailsData.class);
        VNextBOInspectionsPageSteps.findInspectionByCustomTimeFrameAndNumber(data.getInspectionId(), data.getFromDate(), data.getToDate());
        VNextBOInspectionsPageValidations.isInspectionImageZoomIconDisplayed();
        VNextBOInspectionsPageSteps.clickInspectionImageZoomIcon();
        VNextBOInspectionMaximizedImageDialog vNextBOInspectionMaximizedImageDialog =
                new VNextBOInspectionMaximizedImageDialog(webdriver);
        VNextBOInspectionMaximizedImageDialogValidations.isInspectionZoomedImageDisplayed();
        VNextBOInspectionMaximizedImageDialogSteps.closeInspectionMaximizedImageDialog();
        VNextBOInspectionMaximizedImageDialogValidations.isInspectionZoomedImageClosed(vNextBOInspectionMaximizedImageDialog);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSeeInspectionNotes(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsDetailsData data = JSonDataParser.getTestDataFromJson(testData, VNextBOInspectionsDetailsData.class);
        VNextBOInspectionsPageSteps.findInspectionByCustomTimeFrameAndNumber(data.getInspectionId(), data.getFromDate(), data.getToDate());
        VNextBOInspectionsPageValidations.isInspectionNotesIconDisplayed();
        VNextBOInspectionsPageSteps.clickInspectionNotesIcon();
        VNextBOInspectionNoteDialog vNextBOInspectionNoteDialog = new  VNextBOInspectionNoteDialog(webdriver);
        VNextBOInspectionNoteDialogValidations.isInspectionNoteTextDisplayed();
        VNextBOInspectionNoteDialogSteps.closeInspectionNote();
        VNextBOInspectionNoteDialogValidations.isNoteDialogClosed(vNextBOInspectionNoteDialog);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSeePrintSupplementDetails(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsDetailsData data = JSonDataParser.getTestDataFromJson(testData, VNextBOInspectionsDetailsData.class);
        VNextBOInspectionsPageSteps.findInspectionByCustomTimeFrameAndNumber(data.getInspectionId(), data.getFromDate(), data.getToDate());
        VNextBOInspectionsPageValidations.isPrintSupplementButtonDisplayed();
        VNextBOInspectionsPageSteps.clickPrintSupplementButton();
        VNextBOInspectionsPageValidations.isPrintWindowOpened();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanSeePrintInspectionDetails(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsDetailsData data = JSonDataParser.getTestDataFromJson(testData, VNextBOInspectionsDetailsData.class);
        VNextBOInspectionsPageSteps.findInspectionByCustomTimeFrameAndNumber(data.getInspectionId(), data.getFromDate(), data.getToDate());
        VNextBOInspectionsPageValidations.isPrintInspectionButtonDisplayed();
        VNextBOInspectionsPageSteps.clickPrintInspectionButton();
        VNextBOInspectionsPageValidations.isPrintWindowOpened();
    }
}
