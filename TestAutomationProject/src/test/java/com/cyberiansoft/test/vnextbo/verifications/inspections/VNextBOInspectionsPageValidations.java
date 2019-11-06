package com.cyberiansoft.test.vnextbo.verifications.inspections;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.screens.inspections.VNextBOInspectionsWebPage;
import com.cyberiansoft.test.vnextbo.steps.inspections.VNextBOInspectionsPageSteps;
import com.cyberiansoft.test.vnextbo.verifications.VNextBOBaseWebPageValidations;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class VNextBOInspectionsPageValidations extends VNextBOBaseWebPageValidations {

    public static void verifyClearFilterIconIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().clearFilterBtn),
                "Clear filter button hasn't been displayed");
    }

    public static void verifyClearFilterIconIsNotDisplayed() {

        Assert.assertTrue(Utils.isElementNotDisplayed(new VNextBOInspectionsWebPage().clearFilterBtn),
                "Clear filter button has been displayed");
    }

    public static void verifyEditAdvancedSearchIconIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().editAdvancedSearchIcon),
                "Edit advanced search pencil icon hasn't been displayed");
    }

    public static boolean verifySavedAdvancedSearchFilterExists(String filterName) {

        VNextBOInspectionsWebPage inspectionsPage =
                new VNextBOInspectionsWebPage();
        WaitUtilsWebDriver.waitForVisibilityOfAllOptions(inspectionsPage.savedSearchesList);
        for (WebElement searchName: inspectionsPage.savedSearchesList
                ) {
            if (Utils.getText(searchName).equals(filterName))
                return true;
        }
        return false;
    }

    public static void verifySearchFieldContainsText(String value) {

        VNextBOInspectionsWebPage inspectionsPage =
                new VNextBOInspectionsWebPage();
        WaitUtilsWebDriver.waitForInputFieldValueIgnoringException(inspectionsPage.searchFld, value);
        Assert.assertEquals(VNextBOInspectionsPageSteps.getSearchFieldValue(), value,
                "Search field hasn't contained " + value);
    }

    public static void verifyCustomerNameIsCorrect(String expectedCustomerName) {

        Assert.assertEquals(VNextBOInspectionsPageSteps.getSelectedInspectionCustomerName(),
                expectedCustomerName, "Customer name hasn't been correct");
    }

    public static void verifyHowToCreateInspectionLinkTextIsCorrect(String actualResult) {

        Assert.assertEquals(actualResult,  "Click here to learn how to create your first inspection",
                "\"Click here to learn how to create your first inspection\" link hasn't been displayed");
    }

    public static boolean verifyHowToCreateInspectionLinkIsDisplayed() {

        return Utils.isElementDisplayed(new VNextBOInspectionsWebPage().howToCreateInspectionLink);
    }

    public static void verifyInspectionStatusIsCorrect(String inspectionNumber, String expectedStatus) {

        Assert.assertEquals(VNextBOInspectionsPageSteps.getInspectionStatus(inspectionNumber),
                expectedStatus, "Inspection status hasn't been changed to " + expectedStatus);
    }

    public static void verifyArchiveIconIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().archiveIcon),
                "Archive icon hasn't been displayed.");
    }

    public static void verifyUnArchiveIconIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().unArchiveIcon),
                "Unarchive icon hasn't been displayed.");
    }

    public static void verifyInspectionImageZoomIconIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().inspectionImageZoomIcon),
                "Inspection's image hasn't had Zoom icon");
    }

    public static void verifyInspectionNotesIconIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().inspectionNotesIcon),
                "Notes icon hasn't been displayed");
    }

    public static void verifyPrintSupplementButtonIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().printSupplementIcon),
                "Print supplement button hasn't been displayed");
    }

    public static void verifyPrintInspectionButtonIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().printInspectionIcon),
                "Print inspection button hasn't been displayed");
    }

    public static void verifyPrintWindowIsOpened() {

        String parentHandle = Utils.getParentTab();
        new VNextBOInspectionsWebPage().waitForNewTab();
        String newWindow = Utils.getNewTab(parentHandle);
        DriverBuilder.getInstance().getDriver().switchTo().window(parentHandle);
        boolean isPrintWindowOpened = false;
        if (!parentHandle.equals(newWindow)) isPrintWindowOpened = true;
        Assert.assertTrue(isPrintWindowOpened, "Print supplement window hasn't been opened");
    }

    public static void verifyInspectionsListIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().inspectionsList),
                "Inspection list hasn't been displayed");
    }

    public static void verifySearchFieldIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().searchFld),
                "Search field hasn't been displayed");
    }

    public static void verifyInspectionDetailsPanelIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOInspectionsWebPage().inspectionDetailsPanel),
                "Inspection details panel hasn't been displayed");
    }
}