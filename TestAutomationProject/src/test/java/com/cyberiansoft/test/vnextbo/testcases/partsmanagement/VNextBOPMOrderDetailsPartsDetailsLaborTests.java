package com.cyberiansoft.test.vnextbo.testcases.partsmanagement;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.dataprovider.JSONDataProvider;
import com.cyberiansoft.test.vnextbo.config.VNextBOTestCasesDataPaths;
import com.cyberiansoft.test.vnextbo.interactions.breadcrumb.VNextBOBreadCrumbInteractions;
import com.cyberiansoft.test.vnextbo.interactions.leftmenupanel.VNextBOLeftMenuInteractions;
import com.cyberiansoft.test.vnextbo.screens.VNextBOModalDialog;
import com.cyberiansoft.test.vnextbo.steps.commonobjects.VNextBOSearchPanelSteps;
import com.cyberiansoft.test.vnextbo.steps.dialogs.VNextBOModalDialogSteps;
import com.cyberiansoft.test.vnextbo.steps.partsmanagement.VNextBOPartsDetailsPanelSteps;
import com.cyberiansoft.test.vnextbo.steps.partsmanagement.modaldialogs.VNextBOAddLaborPartsDialogSteps;
import com.cyberiansoft.test.vnextbo.testcases.BaseTestCase;
import com.cyberiansoft.test.vnextbo.validations.dialogs.VNextBOModalDialogValidations;
import com.cyberiansoft.test.vnextbo.validations.partsmanagement.VNextBOAddLaborPartsDialogValidations;
import com.cyberiansoft.test.vnextbo.validations.partsmanagement.VNextBOPartsDetailsPanelValidations;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VNextBOPMOrderDetailsPartsDetailsLaborTests extends BaseTestCase {

    String laborServiceName = "Labor AM (Bumper Accent Piece)";

    @BeforeClass
    public void settingUp() {

        JSONDataProvider.dataFile = VNextBOTestCasesDataPaths.getInstance().getPMOrderDetailsPartsDetailsLaborTD();
        VNextBOLeftMenuInteractions.selectPartsManagementMenu();
        VNextBOBreadCrumbInteractions.setLocation("automationMonitoring");
        WaitUtilsWebDriver.waitForSpinnerToDisappear(3);
        VNextBOSearchPanelSteps.searchByTextWithSpinnerLoading("O-444-00531");
    }

    @AfterMethod
    public void refreshPage() {

        Utils.refreshPage();
        VNextBOSearchPanelSteps.clearSearchFilterWithSpinnerLoading();
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
        VNextBOSearchPanelSteps.searchByTextWithSpinnerLoading("O-444-00531");
        WaitUtilsWebDriver.waitForPendingRequestsToComplete();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserMinimizeMaximizeThePartTab(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelValidations.verifyLaborBlockIsDisplayed(0, true);
        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelValidations.verifyLaborBlockIsDisplayed(0, false);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCancelAddingLaborXIcon(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.clickAddLaborButtonForPartByNumberInList(0);
        VNextBOAddLaborPartsDialogValidations.verifyDialogIsDisplayed(true);
        VNextBOAddLaborPartsDialogSteps.closeDialogWithXIcon();
        VNextBOAddLaborPartsDialogValidations.verifyDialogIsDisplayed(false);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCancelAddingLaborCancelButton(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.clickAddLaborButtonForPartByNumberInList(0);
        VNextBOAddLaborPartsDialogValidations.verifyDialogIsDisplayed(true);
        VNextBOAddLaborPartsDialogSteps.closeDialogWithCancelButton();
        VNextBOAddLaborPartsDialogValidations.verifyDialogIsDisplayed(false);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanTypeLaborServiceNameAddingLabor(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.clickAddLaborButtonForPartByNumberInList(0);
        VNextBOAddLaborPartsDialogSteps.populateLaborServiceField("Paint");
        VNextBOAddLaborPartsDialogValidations.verifyLaborServiceDropDownIsDisplayed();
        VNextBOAddLaborPartsDialogValidations.verifyClearLaborServiceFieldIconIsDisplayed();
        VNextBOAddLaborPartsDialogValidations.verifyLaborServiceFieldContainsCorrectValue("Paint");
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanClearTypedNameOfLaborServiceClickingOnXIcon(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.clickAddLaborButtonForPartByNumberInList(0);
        VNextBOAddLaborPartsDialogSteps.populateLaborServiceField("Paint");
        VNextBOAddLaborPartsDialogSteps.clearLaborServiceField();
        VNextBOAddLaborPartsDialogValidations.verifyLaborServiceFieldContainsCorrectValue("");
        VNextBOAddLaborPartsDialogValidations.verifyLaborServiceDropDownIsDisplayed();
        VNextBOAddLaborPartsDialogSteps.closeDialogWithXIcon();
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanAddLabor(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        int laborsAmountBeforeAdding = VNextBOPartsDetailsPanelSteps.getLaborsAmountForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.addLaborForPartByNumberInList(0, "Labor AM");
        refreshPage();
        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelValidations.verifyLaborsAmountIsCorrect(0, laborsAmountBeforeAdding + 1);
        VNextBOPartsDetailsPanelValidations.verifyPartContainsLaborByPartNumberAndLaborServiceName(0, laborServiceName);
        VNextBOPartsDetailsPanelSteps.clickDeleteLaborButtonForPartByNumberInListAndServiceName(0, laborServiceName);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCancelDeletingLaborXIcon(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.addLaborForPartByNumberInList(0, "Labor AM");
        refreshPage();
        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.clickDeleteLaborButtonForPartByNumberInListAndServiceName(0, laborServiceName);
        VNextBOModalDialog confirmationDialog = new VNextBOModalDialog();
        VNextBOModalDialogValidations.verifyDialogIsDisplayed();
        VNextBOModalDialogSteps.clickCloseButton();
        VNextBOModalDialogValidations.verifyDialogIsClosed(confirmationDialog);
        VNextBOPartsDetailsPanelValidations.verifyPartContainsLaborByPartNumberAndLaborServiceName(0, laborServiceName);
        VNextBOPartsDetailsPanelSteps.clickDeleteLaborButtonForPartByNumberInListAndServiceName(0, laborServiceName);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanCancelDeletingLaborNoButton(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.addLaborForPartByNumberInList(0, "Labor AM");
        refreshPage();
        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.clickDeleteLaborButtonForPartByNumberInListAndServiceName(0, laborServiceName);
        VNextBOModalDialog confirmationDialog = new VNextBOModalDialog();
        VNextBOModalDialogValidations.verifyDialogIsDisplayed();
        VNextBOModalDialogSteps.clickNoButton();
        VNextBOModalDialogValidations.verifyDialogIsClosed(confirmationDialog);
        VNextBOPartsDetailsPanelValidations.verifyPartContainsLaborByPartNumberAndLaborServiceName(0, laborServiceName);
        VNextBOPartsDetailsPanelSteps.clickDeleteLaborButtonForPartByNumberInListAndServiceName(0, laborServiceName);
    }

    @Test(dataProvider = "fetchData_JSON", dataProviderClass = JSONDataProvider.class)
    public void verifyUserCanDeleteLabor(String rowID, String description, JSONObject testData) {

        VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        if (VNextBOPartsDetailsPanelSteps.getLaborsAmountForPartByNumberInList(0) == 0) {
            VNextBOPartsDetailsPanelSteps.addLaborForPartByNumberInList(0, "Labor AM");
            VNextBOSearchPanelSteps.searchByTextWithSpinnerLoading("O-444-00531");
            VNextBOPartsDetailsPanelSteps.expandLaborBlockForPartByNumberInList(0);
        }
        final int laborsAmountBeforeDeleting = VNextBOPartsDetailsPanelSteps.getLaborsAmountForPartByNumberInList(0);
        VNextBOPartsDetailsPanelSteps.deleteLaborForPartByNumberInListAndLaborServiceName(0, laborServiceName);
        VNextBOPartsDetailsPanelSteps.refreshPageWhileLaborIsUpdated("O-444-00531", 0, laborsAmountBeforeDeleting - 1);
        VNextBOPartsDetailsPanelValidations.verifyLaborsAmountIsCorrect(0, laborsAmountBeforeDeleting -1);
    }
}