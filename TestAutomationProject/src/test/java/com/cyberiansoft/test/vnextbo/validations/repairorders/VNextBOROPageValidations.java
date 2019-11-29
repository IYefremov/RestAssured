package com.cyberiansoft.test.vnextbo.validations.repairorders;

import com.cyberiansoft.test.baseutils.DataUtils;
import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.vnextbo.interactions.repairorders.VNextBOROPageInteractions;
import com.cyberiansoft.test.vnextbo.screens.repairorders.VNextBOROAdvancedSearchDialog;
import com.cyberiansoft.test.vnextbo.screens.repairorders.VNextBOROWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VNextBOROPageValidations {

    public static void verifyAnotherTechnicianIsDisplayed(String woNumber, String prevTechnician) {
        Assert.assertNotEquals(VNextBOROPageInteractions.getTechniciansValueForWO(woNumber), prevTechnician,
                "The technician hasn't been changed");
    }

    public static void verifyAdvancedSearchDialogIsDisplayed() {
        Assert.assertTrue(isAdvancedSearchDialogDisplayed(), "The advanced search dialog is not opened");
    }

    public static void verifyAdvancedSearchDialogIsClosed() {
        Assert.assertTrue(isAdvancedSearchDialogClosed(), "The advanced search dialog is not closed");
    }

    private static boolean isAdvancedSearchDialogDisplayed() {
        return Utils.isElementWithAttributeContainingValueDisplayed(
                new VNextBOROAdvancedSearchDialog().getAdvancedSearchDialogContainer(), "style", "display: block", 5);
    }

    private static boolean isAdvancedSearchDialogClosed() {
        return Utils.isElementWithAttributeContainingValueDisplayed(
                new VNextBOROAdvancedSearchDialog().getAdvancedSearchDialogContainer(), "style", "display: none", 5);
    }

    public static boolean isNoteForWorkOrderDisplayed(String woNumber) {
        return isArrowDisplayed(woNumber, "/../../..//div[@class='dark box']");
    }

    public static void verifyNoteTextIsDisplayed(String noteMessage) {
        Assert.assertEquals(VNextBOROPageInteractions.getOrderNoteText(), noteMessage,
                "The order note text hasn't been displayed");
    }

    public static boolean isSavedSearchEditIconDisplayed() {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getSavedSearchEditIcon(), 5);
    }

    public static boolean isWorkOrderDisplayedByVin(String vin) {
        return isWorkOrderDisplayed(vin);
    }

    private static boolean isWorkOrderDisplayed(String text) {
        return Utils.isElementDisplayed(By.xpath("//strong[contains(text(), \"" + text + "\")]"));
    }

    public static boolean areTableHeaderTitlesDisplayed(List<String> titles, List<String> repeaterTitles) {
        List<String> extracted = new ArrayList<>();
        WaitUtilsWebDriver.waitForVisibilityOfAllOptions(new VNextBOROWebPage().getTableHeader())
                .forEach((title) -> extracted.add(title.getText()));

        final String[] strings = extracted.get(3).split("\n");
        extracted.remove(3);

        for (int i = 0; i < titles.size(); i++) {
            System.out.println(extracted.get(i));
            System.out.println(titles.get(i));
            System.out.println(extracted.get(i).equals(titles.get(i)));
            System.out.println("&&&&&&&&&&&&\n");
        }
        return extracted.containsAll(titles) && Arrays.asList(strings).containsAll(repeaterTitles);
    }

    public static boolean isPrevButtonDisabled() {
        return Utils.isElementWithAttributeContainingValueDisplayed(new VNextBOROWebPage().getPrevButton(),
                "disabled", "true", 5);
    }

    public static boolean isSavedSearchContainerDisplayed() {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getSavedSearchContainer());
    }

    public static boolean isDepartmentDropdownDisplayed() {
        return isElementForDifferentResolutionsDisplayed(new VNextBOROWebPage().getDepartmentWideDropdownActive(),
                new VNextBOROWebPage().getDepartmentNarrowDropdownActive());
    }

    private static boolean isElementForDifferentResolutionsDisplayed(WebElement wideScreen,WebElement narrowScreen) {
        try {
            WaitUtilsWebDriver.waitForVisibility(wideScreen);
            return true;
        } catch (Exception e) {
            return Utils.isElementDisplayed(narrowScreen);
        }
    }

    public static boolean isPhasesDropdownDisplayed() {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getPhasesWideDropdownActive());
    }

    public static boolean isSearchInputFieldDisplayed() {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getSearchInput());
    }

    public static boolean isPhasesTabDisplayed() {
        return isElementForDifferentResolutionsDisplayed(new VNextBOROWebPage().getPhasesWideTab(), new VNextBOROWebPage().getPhasesNarrowTab());
    }

    public static boolean isDepartmentsTabDisplayed() {
        return isElementForDifferentResolutionsDisplayed(new VNextBOROWebPage().getDepartmentsWideTab(), new VNextBOROWebPage().getDepartmentsNarrowTab());
    }

    public static boolean isWorkOrderDisplayedByOrderNumber(String orderNumber) {
        return isWorkOrderDisplayed(orderNumber);
    }

    public static boolean isWorkOrderDisplayedByRoNumber(String roNumber) {
        return isWorkOrderDisplayed(roNumber);
    }

    public static boolean isWorkOrderDisplayedByFirstName(String firstName) {
        return isWorkOrderDisplayedByPartialText(firstName);
    }

    public static boolean isWorkOrderDisplayedByLastName(String lastName) {
        return isWorkOrderDisplayedByPartialText(lastName);
    }

    public static boolean isWorkOrderDisplayedByName(String name) {
        return isWorkOrderDisplayed(name);
    }

    public static boolean isWorkOrderDisplayedAfterSearchByEmail(String name) {
        return isWorkOrderDisplayed(name);
    }

    public static boolean isWorkOrderDisplayedAfterSearchByCompanyName(String name) {
        return isWorkOrderDisplayed(name);
    }

    private static boolean isWorkOrderDisplayedByPartialText(String text) {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getTableBody()
                .findElement(By.xpath(".//strong[contains(text(), '" + text + "')]")));
    }

    public static boolean isWoTypeDisplayed(String woType) {
        return WaitUtilsWebDriver.waitForVisibility(new VNextBOROWebPage()
                .getTableBody())
                .findElements(By.xpath(".//div"))
                .stream()
                .anyMatch(e -> e
                        .getText()
                        .contains(woType));
    }

    public static boolean isArrowDownDisplayed(String wo) {
        return isArrowDisplayed(wo, "//i[@class='icon-arrow-down']");
    }

    public static boolean isArrowUpDisplayed(String wo) {
        return isArrowDisplayed(wo, "//i[@class='icon-arrow-up']");
    }

    private static boolean isArrowDisplayed(String wo, String arrow) {
        return Utils.isElementDisplayed(By.xpath("//strong[text()='" +
                wo + "']/../../.." + arrow));
    }

    public static boolean isRepairOrderPresentInTable(String orderNumber) {
        return new VNextBOROWebPage().getRepairOrdersTableBody().findElements(By.xpath(".//strong[text()='" + orderNumber + "']")).size() > 0;
    }

    public static boolean isPoNumClickable() {
        return Utils.isElementClickable(new VNextBOROWebPage().getPoNumTitle(), 5);
    }

    public static boolean areValuesDisplayed(List<WebElement> values) {
        try {
            WaitUtilsWebDriver.getWait().until((ExpectedCondition<Boolean>) sum -> !values.get(0).getText().equals(""));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDepartmentNarrowScreenClickable() {
        return isScreenClickable(new VNextBOROWebPage().getDepartmentsNarrowScreen());
    }

    public static boolean isPhasesNarrowScreenClickable() {
        return isScreenClickable(new VNextBOROWebPage().getPhasesNarrowScreen());
    }

    private static boolean isScreenClickable(WebElement phasesNarrowScreen) {
        return Utils.isElementClickable(phasesNarrowScreen);
    }

    public static boolean isTableDisplayed() {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getTable(), 5);
    }

    public static boolean isTextNoRecordsDisplayed() {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getNoRecordsFound(), 5);
    }

    public static boolean isAdvancedSearchCaretDisplayed() {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getAdvancedSearchCaret());
    }

    public static boolean isProblemIndicatorDisplayedForOrder(String order) {
        return Utils.isElementDisplayed(new VNextBOROWebPage().getProblemIndicatorForOrder(order), 4);
    }

    private static void verifyDateOrNotStartedPhaseForOrderWithoutDate(LocalDate dateBeforeCurrentDate) {
        final List<String> ordersDatesList = VNextBOROPageInteractions.getOrdersDatesList();
        final List<String> ordersCurrentPhaseList = VNextBOROPageInteractions.getOrdersCurrentPhaseList();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DataUtils.FULL_DATE_FORMAT.getData());

        ordersDatesList.forEach(System.out::println);
        ordersCurrentPhaseList.forEach(System.out::println);
        for (int i = 0; i < ordersDatesList.size(); i++) {
            if (ordersDatesList.get(i).isEmpty()) {
                Assert.assertEquals("Not Started", ordersCurrentPhaseList.get(i),
                        "The current phase of the order should be 'Not started'");
            } else {
                Assert.assertTrue(LocalDate.parse(ordersDatesList.get(i), formatter).isAfter(dateBeforeCurrentDate),
                        "The order started date is not within the given date boundaries");
            }
        }
    }

    private static void verifyCurrentPhaseForOrder(String currentPhase) {
        final List<String> ordersCurrentPhaseList = VNextBOROPageInteractions.getOrdersCurrentPhaseList();

        ordersCurrentPhaseList.forEach(phase -> {
            System.out.println(phase);
            if (!phase.equals("Not Started")) {
                Assert.assertEquals(currentPhase, phase,
                        "The current phase is not equal to the phase from the search criterion");
            }
        });
    }

    public static void verifyOrdersAfterSearchByDate(LocalDate dateBeforeCurrentDate) {
        if (!isTableDisplayed()) {
            Assert.assertTrue(isTextNoRecordsDisplayed(), "The text notification is not displayed");
        } else {
            verifyDateOrNotStartedPhaseForOrderWithoutDate(dateBeforeCurrentDate);
        }
    }

    public static void verifyOrdersAfterSearchByPhase(String currentPhase) {
        if (!isTableDisplayed()) {
            Assert.assertTrue(isTextNoRecordsDisplayed(), "The text notification is not displayed");
        } else {
            verifyCurrentPhaseForOrder(currentPhase);
        }
    }

    public static void verifyAllOrdersHaveProblemIndicators() {
        if (!isTableDisplayed()) {
            Assert.assertTrue(isTextNoRecordsDisplayed(), "The text notification is not displayed");
        } else {
            final VNextBOROWebPage roPage = new VNextBOROWebPage();
            Assert.assertEquals(roPage.getProblemIndicatorsList().size(), roPage.getOrdersDisplayedOnPage().size(),
                    "The problem indicators are not displayed for all orders");
        }
    }
}