package com.cyberiansoft.test.vnextbo.validations.partsmanagement;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.vnextbo.interactions.partsmanagement.modaldialogs.VNextBOAddNewPartDialogInteractions;
import com.cyberiansoft.test.vnextbo.screens.VNextBOAddNewServiceMonitorDialog;
import com.cyberiansoft.test.vnextbo.screens.partsmanagement.modaldialogs.VNextBOAddNewPartDialog;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

public class VNextBOAddNewPartDialogValidations {

    public static void verifyDialogIsDisplayed(boolean dialogDisplayed) {

        final VNextBOAddNewPartDialog addNewPartDialog = new VNextBOAddNewPartDialog();
        WaitUtilsWebDriver.elementShouldBeVisible(addNewPartDialog.getDialogContent(), dialogDisplayed, 5);
        Assert.assertEquals(Utils.isElementDisplayed(addNewPartDialog.getDialogContent()), dialogDisplayed,
                "Add new part dialog hasn't been displayed");
    }

    public static void verifyServiceFieldIsCorrect(String serviceName) {

        Assert.assertEquals(Utils.getInputFieldValue(new VNextBOAddNewPartDialog().getServiceField()), serviceName,
                "Service field has contained incorrect value");
    }

    public static void verifySelectedPartsCounterValueIsCorrect(String expectedCounterValue) {

        Assert.assertEquals(Utils.getText(new VNextBOAddNewPartDialog().getSelectedPartsCounter()), expectedCounterValue,
                "Selected parts counter value hasn't been correct");
    }

    public static void verifyPartsListSizeIsCorrect(int expectedPartsSize) {
        int size = 0;
        try {
            WaitUtilsWebDriver.getWebDriverWait(2).until((ExpectedCondition<Boolean>) driver ->
                            VNextBOAddNewPartDialogInteractions.getPartsListSize() == expectedPartsSize);
            size = VNextBOAddNewPartDialogInteractions.getPartsListSize();
        } catch (Exception ignored) {}
        Assert.assertEquals(size, expectedPartsSize, "The parts list size isn't correct");
    }

    public static void verifyAllPartsListOptionsContainText(String text) {
        final boolean door = VNextBOAddNewPartDialogInteractions.getDisplayedPartsListOptionsNames()
                .stream()
                .anyMatch(part -> !part.contains(text));
        Assert.assertFalse(door, "The parts list options don't contain the filtering word " + text);
    }

    public static void verifyServiceOptionsInDropDownContainText(String text) {
        final boolean contains = Utils.getText(new VNextBOAddNewServiceMonitorDialog().getServiceListBoxOptions())
                .stream()
                .allMatch(t -> t.contains(text));
        Assert.assertTrue(contains, "The service options in dropdown do not contain text '" + text + "'");
    }
}
