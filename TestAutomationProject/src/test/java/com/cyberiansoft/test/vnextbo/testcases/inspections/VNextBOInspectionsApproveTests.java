package com.cyberiansoft.test.vnextbo.testcases.inspections;

import com.cyberiansoft.test.dataclasses.vNextBO.inspections.VNextBOInspectionsDetailsData;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.dataprovider.JSonDataParser;
import com.cyberiansoft.test.vnextbo.config.VNextBOTestCasesDataPaths;
import com.cyberiansoft.test.vnextbo.interactions.leftmenupanel.VNextBOLeftMenuInteractions;
import com.cyberiansoft.test.vnextbo.screens.VNextBOModalDialog;
import com.cyberiansoft.test.vnextbo.steps.dialogs.VNextBOModalDialogSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsPageSteps;
import com.cyberiansoft.test.vnextbo.testcases.BaseTestCase;
import com.cyberiansoft.test.vnextbo.validations.dialogs.VNextBOModalDialogValidations;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VNextBOInspectionsApproveTests extends BaseTestCase {

    @BeforeClass
    public void settingUp() {
        JSONDataProvider.dataFile = VNextBOTestCasesDataPaths.getInstance().getInspectionsApproveTD();
        VNextBOLeftMenuInteractions.selectInspectionsMenu();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCancelApprovingWithNoButton(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsDetailsData data = JSonDataParser.getTestDataFromJson(testData, VNextBOInspectionsDetailsData.class);
        VNextBOInspectionsPageSteps.searchInspectionByCustomTimeFrameAndNumber(data.getInspectionId(), data.getFromDate(), data.getToDate());
        VNextBOInspectionsPageSteps.clickInspectionApproveButton();
        VNextBOModalDialog confirmationDialog = new VNextBOModalDialog();
        VNextBOModalDialogValidations.verifyYesButtonIsDisplayed();
        VNextBOModalDialogValidations.verifyNoButtonIsDisplayed();
        VNextBOModalDialogValidations.verifyCloseButtonIsDisplayed();
        VNextBOModalDialogSteps.clickNoButton();
        VNextBOModalDialogValidations.verifyDialogIsClosed(confirmationDialog);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCancelApprovingWithCloseButton(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageSteps.clickInspectionApproveButton();
        VNextBOModalDialog confirmationDialog = new VNextBOModalDialog();
        VNextBOModalDialogSteps.clickCloseButton();
        VNextBOModalDialogValidations.verifyDialogIsClosed(confirmationDialog);
    }
}