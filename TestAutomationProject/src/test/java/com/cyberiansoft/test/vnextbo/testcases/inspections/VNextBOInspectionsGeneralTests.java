package com.cyberiansoft.test.vnextbo.testcases.inspections;

import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.vnextbo.config.VNextBOTestCasesDataPaths;
import com.cyberiansoft.test.vnextbo.interactions.leftmenupanel.VNextBOLeftMenuInteractions;
import com.cyberiansoft.test.vnextbo.screens.VNextBOModalDialog;
import com.cyberiansoft.test.vnextbo.steps.dialogs.VNextBOModalDialogSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsAdvancedSearchSteps;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsPageSteps;
import com.cyberiansoft.test.vnextbo.testcases.BaseTestCase;
import com.cyberiansoft.test.vnextbo.validations.dialogs.VNextBOModalDialogValidations;
import com.cyberiansoft.test.vnextbo.validations.inspections.VNextBOInspectionsAdvancedSearchValidations;
import com.cyberiansoft.test.vnextbo.validations.inspections.VNextBOInspectionsPageValidations;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class VNextBOInspectionsGeneralTests extends BaseTestCase {

    private List<String> expectedAdvancedSearchFields =
            Arrays.asList("Customer", "PO#", "RO#", "Stock#", "VIN",
                    "Status", "Inspection#", "Timeframe");

    @BeforeClass
    public void settingUp() {

        JSONDataProvider.dataFile = VNextBOTestCasesDataPaths.getInstance().getInspectionsTD();
        VNextBOLeftMenuInteractions.selectInspectionsMenu();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyInspectionPageCanBeOpened(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageValidations.verifyTermsAndConditionsLinkIsDisplayed();
        VNextBOInspectionsPageValidations.verifyPrivacyPolicyLinkIsDisplayed();
        VNextBOInspectionsPageValidations.verifyInspectionsListIsDisplayed();
        VNextBOInspectionsPageValidations.verifySearchFieldIsDisplayed();
        VNextBOInspectionsPageValidations.verifyInspectionDetailsPanelIsDisplayed();
        VNextBOInspectionsPageValidations.verifyIntercomButtonIsDisplayed();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyTermsAndConditionsCanBeOpenedAndClosedOkBtn(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageSteps.clickTermsAndConditionsLink();
        VNextBOModalDialog vNextBOTermsAndConditionsDialog = new VNextBOModalDialog();
        VNextBOModalDialogValidations.verifyDialogIsDisplayed();
        VNextBOModalDialogValidations.verifyOkButtonIsDisplayed();
        Assert.assertEquals(VNextBOModalDialogSteps.getDialogHeader(),
                "AMT Service Agreement Terms and Conditions",
                "Dialog header hasn't been correct");
        VNextBOModalDialogSteps.clickOkButton();
        VNextBOInspectionsPageValidations.verifySearchFieldIsDisplayed();
        VNextBOModalDialogValidations.verifyDialogIsClosed(vNextBOTermsAndConditionsDialog);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyTermsAndConditionsCanBeOpenedAndClosedCloseBtn(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageSteps.clickTermsAndConditionsLink();
        VNextBOModalDialog vNextBOTermsAndConditionsDialog = new VNextBOModalDialog();
        VNextBOModalDialogValidations.verifyDialogIsDisplayed();
        VNextBOModalDialogValidations.verifyOkButtonIsDisplayed();
        Assert.assertEquals(VNextBOModalDialogSteps.getDialogHeader(),
                "AMT Service Agreement Terms and Conditions",
                "Dialog header hasn't been correct");
        VNextBOModalDialogSteps.clickCloseButton();
        VNextBOModalDialogValidations.verifyDialogIsClosed(vNextBOTermsAndConditionsDialog);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyPrivacyPolicyCanBeOpenedAndClosedOkBtn(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageSteps.clickPrivacyPolicyLink();
        VNextBOModalDialog vNextBOTermsAndConditionsDialog = new VNextBOModalDialog();
        VNextBOModalDialogValidations.verifyDialogIsDisplayed();
        VNextBOModalDialogValidations.verifyOkButtonIsDisplayed();
        Assert.assertEquals(VNextBOModalDialogSteps.getDialogHeader(),
                "Privacy Policy",
                "Dialog header hasn't been correct");
        VNextBOModalDialogSteps.clickOkButton();
        VNextBOModalDialogValidations.verifyDialogIsClosed(vNextBOTermsAndConditionsDialog);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyPrivacyPolicyCanBeOpenedAndClosedCloseBtn(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageSteps.clickPrivacyPolicyLink();
        VNextBOModalDialog vNextBOTermsAndConditionsDialog = new VNextBOModalDialog();
        VNextBOModalDialogValidations.verifyDialogIsDisplayed();
        VNextBOModalDialogValidations.verifyOkButtonIsDisplayed();
        Assert.assertEquals(VNextBOModalDialogSteps.getDialogHeader(),
                "Privacy Policy",
                "Dialog header hasn't been correct");
        VNextBOModalDialogSteps.clickCloseButton();
        VNextBOModalDialogValidations.verifyDialogIsClosed(vNextBOTermsAndConditionsDialog);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyIntercomMessengerCanBeOpenedClosed(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageSteps.openIntercomMessenger();
        VNextBOInspectionsPageValidations.verifyIntercomMessengerIsOpened();
        VNextBOInspectionsPageSteps.closeIntercom();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyAdvancedSearchFields(String rowID, String description, JSONObject testData) {

        VNextBOInspectionsPageSteps.openAdvancedSearchForm();
        VNextBOInspectionsAdvancedSearchValidations.verifyAdvancedSearchFormIsDisplayed();
        Assert.assertEquals(VNextBOInspectionsAdvancedSearchSteps.getAllAdvancedSearchFieldsLabels(),
                expectedAdvancedSearchFields);
        VNextBOInspectionsAdvancedSearchValidations.verifySearchButtonIsDisplayed();
        VNextBOInspectionsAdvancedSearchSteps.clickCloseButton();
    }
}