package com.cyberiansoft.test.vnextbo.validations.repairordersnew;

import com.cyberiansoft.test.baseutils.ConditionWaiter;
import com.cyberiansoft.test.baseutils.CustomDateProvider;
import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.enums.DateUtils;
import com.cyberiansoft.test.vnextbo.interactions.repairorders.VNextBOROPageInteractions;
import com.cyberiansoft.test.vnextbo.screens.repairordersnew.VNextBOROWebPageNew;
import com.cyberiansoft.test.vnextbo.steps.repairordersnew.VNextBORODetailsStepsNew;
import com.cyberiansoft.test.vnextbo.steps.repairordersnew.VNextBOROPageStepsNew;
import com.cyberiansoft.test.vnextbo.validations.VNextBOBaseWebPageValidations;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VNextBOROWebPageValidationsNew extends VNextBOBaseWebPageValidations {

    public static void verifyCustomersAreCorrectInTheTable(String expectedCustomer) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement customer : new VNextBOROWebPageNew().getOrdersCustomersList()) {
                Assert.assertEquals(Utils.getText(customer), expectedCustomer, "Customer hasn't been correct");
            }
        }
    }

    public static void verifySearchResultByService(String expectedServiceName) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            VNextBOROPageStepsNew.openOrderDetailsByNumberInList(0);
            VNextBORODetailsStepsNew.expandAllPhases();
            VNextBORODetailsValidationsNew.verifyServiceOrTaskDescriptionsContainText(expectedServiceName);
            Utils.goToPreviousPage();
        }
    }

    public static void verifyEmployeesAreCorrectInTheTable(String expectedEmployee) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement technician : new VNextBOROWebPageNew().getOrdersTechniciansList()) {
                Assert.assertTrue(Utils.getText(technician).contains(expectedEmployee),
                        "Employee " + Utils.getText(technician) + "hasn't been correct");
            }
        }
    }

    public static void verifyPhasesAreCorrectInTheTable(String expectedPhase) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            WaitUtilsWebDriver.waitForPendingRequestsToComplete();
            for (WebElement phase : new VNextBOROWebPageNew().getOrdersPhasesList()) {
                ConditionWaiter.create(__ -> Utils.getText(phase).equals(expectedPhase)).execute();
                Assert.assertEquals(Utils.getText(phase), expectedPhase, "Phase hasn't been correct");
            }
        }
    }

    public static void verifyTechniciansAreCorrectInTheTable(String expectedTechnician) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            ConditionWaiter.create(__ -> Utils.getText(new VNextBOROWebPageNew().getOrdersTechniciansList().get(0)).contains(expectedTechnician)).execute();
            for (WebElement technician : new VNextBOROWebPageNew().getOrdersTechniciansList()) {
                ConditionWaiter.create(__ -> Utils.getText(technician).contains(expectedTechnician)).execute();
                Assert.assertTrue(Utils.getText(technician).contains(expectedTechnician), "Technician hasn't been correct");
            }
        }
    }

    public static void verifyDepartmentsAreCorrectInTheTable(String expectedDepartment) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement department : new VNextBOROWebPageNew().getOrdersDepartmentsList()) {
                Assert.assertEquals(Utils.getText(department), expectedDepartment, "Department hasn't been correct");
            }
        }
    }

    public static void verifyWoTypesAreCorrectInTheTable(String expectedWoType) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement woType : new VNextBOROWebPageNew().getWoTypesList()) {
                Assert.assertEquals(Utils.getText(woType), expectedWoType, "WO type hasn't been correct");
            }
        }
    }

    public static void verifyWoNumbersAreCorrectInTheTable(String expectedWoNumber) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement woNumber : new VNextBOROWebPageNew().getWoNumbersList()) {
                Assert.assertEquals(Utils.getText(woNumber), expectedWoNumber, "WO number hasn't been correct");
            }
        }
    }

    public static void verifyRoNumbersAreCorrectInTheTable(String expectedRoNumber) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement roNumber : new VNextBOROWebPageNew().getRoNumbersList()) {
                Assert.assertEquals(Utils.getInputFieldValue(roNumber), expectedRoNumber, "RO number hasn't been correct");
            }
        }
    }

    public static void verifyPoNumbersAreCorrectInTheTable(String expectedPoNumber) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement poNumber : new VNextBOROWebPageNew().getPoNumbersList()) {
                Assert.assertEquals(Utils.getInputFieldValue(poNumber), expectedPoNumber, "PO number hasn't been correct");
            }
        }
    }

    public static void verifyStockNumbersAreCorrectInTheTable(String expectedStockNumber) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement stockNumber : new VNextBOROWebPageNew().getStockNumbersList()) {
                Assert.assertEquals(Utils.getInputFieldValue(stockNumber), expectedStockNumber, "Stock number hasn't been correct");
            }
        }
    }

    public static void verifyVinNumbersAreCorrectInTheTable(String expectedVinNumber) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            for (WebElement vinNumber : new VNextBOROWebPageNew().getVinNumbersList()) {
                Assert.assertEquals(Utils.getText(vinNumber), expectedVinNumber, "VIN number hasn't been correct");
            }
        }
    }

    public static void verifyOrderTableContainsRecords() {

        Assert.assertTrue(new VNextBOROWebPageNew().getRepairOrdersTableRowsList().size() > 0, "Orders have not been displayed");
    }

    public static void verifyCorrectRecordsAmountIsDisplayed(int expectedRecordsNumber) {

        Assert.assertEquals(new VNextBOROWebPageNew().getRepairOrdersTableRowsList().size(), expectedRecordsNumber,
                "Orders table has contained incorrect orders amount.");
    }

    public static void verifyOrdersTableAfterSearch() {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else verifyOrderTableContainsRecords();
    }

    public static void verifyProblemIndicatorIsDisplayedForEachRecord() {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed()) verifyNotFoundMessageIsCorrect();
        else {
            Assert.assertEquals(new VNextBOROWebPageNew().getRepairOrdersTableRowsList().size(), new VNextBOROWebPageNew().getProblemIndicatorsList().size(),
                    "Not all orders has had Problems indicator");
        }
    }

    public static void verifyProblemIndicatorIsDisplayedForOrder(String orderNumber) {

        ConditionWaiter.create(__ -> new VNextBOROWebPageNew().problemIndicatorByOrderNumber(orderNumber).isDisplayed()).execute();
        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().problemIndicatorByOrderNumber(orderNumber)),
                "Problems indicator hasn't been displayed for the order " + orderNumber);
    }

    public static void verifyNotFoundMessageIsCorrect() {

        Assert.assertEquals(Utils.getText(new VNextBOROWebPageNew().getNoRecordsFoundMessage()), "No records found. Please refine search criteria ...",
                "No records found message hasn't been displayed or has been incorrect");
    }

    public static void verifySortingByStartDateIsCorrect(List<WebElement> actualStartDatesElementsList, List<Date> expectedStartDatesList) throws ParseException {

        List<Date> actualStartDatesList = new ArrayList<>();
        for (WebElement element : actualStartDatesElementsList) {
            if (!element.getText().isEmpty()) {
                Date parse = new SimpleDateFormat("MM/dd/yyyy").parse(element.getText());
                actualStartDatesList.add(parse);
            }
        }
        Assert.assertEquals(actualStartDatesList, expectedStartDatesList, "Orders have been sorted incorrectly");
    }

    public static void verifyOrdersAreSortedByPriorityAndStartDateFromOldestToNewest() throws ParseException {

        List<WebElement> highPriorityDates = new VNextBOROWebPageNew().getHighPriorityOrdersStartDatesList();
        if (highPriorityDates.size() > 0) {
            verifySortingByStartDateIsCorrect(new VNextBOROWebPageNew().getHighPriorityOrdersStartDatesList(),
                    VNextBOROPageStepsNew.getAscSortedStartDatesListValues(highPriorityDates));
        }

        List<WebElement> normalPriorityDates = new VNextBOROWebPageNew().getNormalPriorityOrdersStartDatesList();
        if (normalPriorityDates.size() > 0) {
            verifySortingByStartDateIsCorrect(new VNextBOROWebPageNew().getNormalPriorityOrdersStartDatesList(),
                    VNextBOROPageStepsNew.getAscSortedStartDatesListValues(normalPriorityDates));
        }

        List<WebElement> lowPriorityDates = new VNextBOROWebPageNew().getLowPriorityOrdersStartDatesList();
        if (highPriorityDates.size() > 0) {
            verifySortingByStartDateIsCorrect(new VNextBOROWebPageNew().getLowPriorityOrdersStartDatesList(),
                    VNextBOROPageStepsNew.getAscSortedStartDatesListValues(lowPriorityDates));
        }
    }

    public static void verifyOrdersAreSortedByPriorityAndStartDateFromNewestToOldest() throws ParseException {

        List<WebElement> highPriorityDates = new VNextBOROWebPageNew().getHighPriorityOrdersStartDatesList();
        if (highPriorityDates.size() > 0) {
            verifySortingByStartDateIsCorrect(new VNextBOROWebPageNew().getHighPriorityOrdersStartDatesList(),
                    VNextBOROPageStepsNew.getDescSortedStartDatesListValues(highPriorityDates));
        }

        List<WebElement> normalPriorityDates = new VNextBOROWebPageNew().getNormalPriorityOrdersStartDatesList();
        if (normalPriorityDates.size() > 0) {
            verifySortingByStartDateIsCorrect(new VNextBOROWebPageNew().getNormalPriorityOrdersStartDatesList(),
                    VNextBOROPageStepsNew.getDescSortedStartDatesListValues(normalPriorityDates));
        }

        List<WebElement> lowPriorityDates = new VNextBOROWebPageNew().getLowPriorityOrdersStartDatesList();
        if (highPriorityDates.size() > 0) {
            verifySortingByStartDateIsCorrect(new VNextBOROWebPageNew().getLowPriorityOrdersStartDatesList(),
                    VNextBOROPageStepsNew.getDescSortedStartDatesListValues(lowPriorityDates));
        }
    }

    public static void verifyOrdersAfterSearchByTimeFrame(LocalDate dateBeforeCurrentDate) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed())
            Assert.assertEquals(Utils.getText(new VNextBOROWebPageNew().getNoRecordsFoundMessage()), "No records found. Please refine search criteria ...",
                    "No records found message hasn't been displayed or has been incorrect");
        else {
            verifyTargetDateForOrders(dateBeforeCurrentDate, VNextBOROPageInteractions.getHighPriorityDates());
            verifyTargetDateForOrders(dateBeforeCurrentDate, VNextBOROPageInteractions.getNormalPriorityDates());
            verifyTargetDateForOrders(dateBeforeCurrentDate, VNextBOROPageInteractions.getLowPriorityDates());
        }
    }

    private static void verifyTargetDateForOrders(LocalDate dateBeforeCurrentDate, List<String> ordersDatesList) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.FULL_DATE_FORMAT.getFormat());
        for (int i = 1; i < ordersDatesList.size(); i++) {

            if (!ordersDatesList.get(i - 1).isEmpty() && !ordersDatesList.get(i).isEmpty()) {
                final LocalDate previous = LocalDate.parse(ordersDatesList.get(i - 1), formatter);
                final LocalDate next = LocalDate.parse(ordersDatesList.get(i), formatter);
                verifyOrderNextTargetDateIsAfterDateStarted(dateBeforeCurrentDate, next);
                if (previous.isEqual(next)) continue;
                verifyOrderTargetDateIsWithinBoundaries(previous, next);
            }
        }
    }

    private static void verifyTargetDateForOrders(LocalDate dateStarted, LocalDate dateFinished, List<String> ordersDatesList) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.FULL_DATE_FORMAT.getFormat());
        for (int i = 1; i < ordersDatesList.size(); i++) {

            if (!ordersDatesList.get(i - 1).isEmpty() && !ordersDatesList.get(i).isEmpty()) {
                final LocalDate previous = LocalDate.parse(ordersDatesList.get(i - 1), formatter);
                final LocalDate next = LocalDate.parse(ordersDatesList.get(i), formatter);
                verifyOrderNextTargetDateIsAfterDateStarted(dateStarted, next);
                if (previous.isEqual(next)) continue;
                verifyOrderTargetDateIsWithinBoundaries(previous, next, dateFinished);
            }
        }
    }

    private static void verifyOrderNextTargetDateIsAfterDateStarted(LocalDate dateStarted, LocalDate next) {

        Assert.assertTrue(next.isAfter(dateStarted),
                "The order target date is not within the given date boundaries");
    }

    private static void verifyOrderTargetDateIsWithinBoundaries(LocalDate previous, LocalDate next, LocalDate dateFinished) {

        if (previous.isBefore(next) || next.isAfter(dateFinished)) {
            Assert.fail("The order target date is not within the given date boundaries");
        }
    }

    private static void verifyOrderTargetDateIsWithinBoundaries(LocalDate previous, LocalDate next) {
        if (previous.isBefore(next)) {
            Assert.fail("The order target date is not within the given date boundaries");
        }
    }

    public static void verifyOrdersAfterSearchByTimeFrame(LocalDate dateStarted, LocalDate dateFinished) {

        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed())
            Assert.assertEquals(Utils.getText(new VNextBOROWebPageNew().getNoRecordsFoundMessage()), "No records found. Please refine search criteria ...",
                    "No records found message hasn't been displayed or has been incorrect");
        else {
            verifyTargetDateForOrders(dateStarted, dateFinished, VNextBOROPageInteractions.getHighPriorityDates());
            verifyTargetDateForOrders(dateStarted, dateFinished, VNextBOROPageInteractions.getNormalPriorityDates());
            verifyTargetDateForOrders(dateStarted, dateFinished, VNextBOROPageInteractions.getLowPriorityDates());
        }
    }

    public static void verifySavedSearchDropDownFieldIdDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getSavedSearchDropDownField()),
                "Saved search field hasn't been displayed");
    }

    public static void verifyDepartmentsTabIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getDepartmentsWideSwitcherTab()),
                "Departments switcher tab hasn't been displayed");
    }

    public static void verifyPhasesTabIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getPhasesWideSwitcherTab()),
                "Phases switcher tab hasn't been displayed");
    }

    public static void verifyDepartmentsFilterTableIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getDepartmentsWideFilterTable()),
                "Departments filter table hasn't been displayed");
    }

    public static void verifyPhasesFilterTableIsDisplayed() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getPhasesWideFilterTable()),
                "Phases filter table hasn't been displayed");
    }

    public static void verifySavedSearchDropDownFieldContainsCorrectValue(String expectedValue) {

        Assert.assertEquals(Utils.getText(new VNextBOROWebPageNew().getSavedSearchDropDownField()), expectedValue,
                "Saved search field has contained incorrect value");
    }

    public static void verifySavedSearchDropDownListContainsSavedSearch(String searchName, boolean shouldBeDisplayed) {

        List<String> savedSearchesList = new VNextBOROWebPageNew().getSavedSearchDropDownList().stream().
                map(WebElement::getText).collect(Collectors.toList());
        if (shouldBeDisplayed)
        {
            ConditionWaiter.create(__ -> savedSearchesList.contains(searchName)).execute();
            Assert.assertTrue(savedSearchesList.contains(searchName),
                    "Saved search hasn't been presented in the saved searches list");
        }
        else  {
            ConditionWaiter.create(__ -> !savedSearchesList.contains(searchName)).execute();
            Assert.assertTrue(!savedSearchesList.contains(searchName),
                    "Saved search hasn't been deleted");
        }
    }

    public static void verifyFirstOrderInvoiceNumberIsCorrect(String expectedInvoiceNumber) {

        final String mainWindow = DriverBuilder.getInstance().getDriver().getWindowHandle();
        VNextBOROWebPageNew ordersPage = new VNextBOROWebPageNew();
        Assert.assertEquals(Utils.getText(ordersPage.getInvoiceNumbersList().get(0)), expectedInvoiceNumber,
                "The invoice number of the first order hasn't been correct");
        Utils.clickElement(ordersPage.getInvoiceNumbersList().get(0));
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
        WaitUtilsWebDriver.waitForNewTab();
        String invoiceWindowHandle = Utils.getNewTab(mainWindow);

        Assert.assertNotEquals(mainWindow, invoiceWindowHandle, "The invoice window hasn't been opened");
        Utils.closeNewTab(mainWindow);
    }

    public static void verifyFirstOrderArbitrationDate(String expectedArbitrationDate) {

        Assert.assertEquals(VNextBOROPageStepsNew.getArbitrationDatesList().get(0), expectedArbitrationDate,
                "The arbitration date of the first order is not correct");
    }

    public static void verifyFirstOrderArbitrationDateIsMoreThanCurrentDate() {

        final LocalDate currentDate = CustomDateProvider.getCurrentDateLocalized();
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DateUtils.THE_SHORTEST_DATE_FORMAT.getFormat());
        Assert.assertTrue(currentDate.isBefore(LocalDate.parse(VNextBOROPageStepsNew.getArbitrationDatesList().get(0), dateFormat)),
                "The arbitration date of the first order is not after the current date");
    }

    public static void verifyOrdersWithArbitrationDatesAreDisplayedBeforeOtherOrders() {

        boolean foundFirstEmptyOrder = false;
        for (String order : VNextBOROPageStepsNew.getArbitrationDatesList()) {
            if (foundFirstEmptyOrder && !order.isEmpty()) {
                Assert.fail("The orders with arbitration dates are not displayed before the orders without them");
            }
            if (!foundFirstEmptyOrder && order.isEmpty()) {
                foundFirstEmptyOrder = true;
            }
        }
        Assert.assertTrue(foundFirstEmptyOrder, "The orders with arbitration dates are not displayed before the orders without them");
    }

    public static void verifyOrdersTableContainsCorrectColumns() {

        List<String> correctTitlesList = Arrays.asList("Order / Type / Department", "Customer / Vehicle", "VIN# / Stock# / RO# / PO# / Invoice / Mileage",
                "Order Amount\nVendor Amount", "Start Date / Target Date", "Technicians", "Current Phase / Days in Phase", "Completed (%)", "Other");
        List<String> columnsTitles = new VNextBOROWebPageNew().getRepairOrdersTableColumnsTitles().stream().
                map(WebElement::getText).collect(Collectors.toList());
        Assert.assertEquals(columnsTitles, correctTitlesList, "Repair orders table has contained incorrect columns");
    }

    public static void verifyDepartmentsAllAmountsIsCorrect() {
        final VNextBOROWebPageNew roPage = new VNextBOROWebPageNew();
        WaitUtilsWebDriver.waitForVisibilityOfAllOptions(roPage.getDepartmentsList(), 3);
        roPage.getOrdersAmountThroughDepartmentsList().forEach(el -> WaitUtilsWebDriver.getShortWait()
                .until((ExpectedCondition<Boolean>) driver -> !Utils.getText(el).isEmpty()));
        List<String> ordersAmountsList = Utils.getText(roPage.getOrdersAmountThroughDepartmentsList());
        int ordersCalculatedAmount = 0;
        for (int i = 1; i < ordersAmountsList.size(); i++) {
            if (!ordersAmountsList.get(i).trim().equals(""))
                ordersCalculatedAmount += Integer.parseInt(ordersAmountsList.get(i).trim());
        }
        Assert.assertEquals(Integer.parseInt(ordersAmountsList.get(0).trim()), ordersCalculatedAmount,
                "\"ALL\" category contains incorrect orders number");
    }

    public static void verifyPhasesAllAmountsIsCorrect() {

        WaitUtilsWebDriver.waitForVisibilityOfAllOptions(new VNextBOROWebPageNew().getOrdersAmountThroughPhasesList(), 2);
        List<String> ordersAmountsList = new VNextBOROWebPageNew().getOrdersAmountThroughPhasesList().stream().
                map(WebElement::getText).collect(Collectors.toList());
        int ordersCalculatedAmount = 0;
        for (int i = 1; i < ordersAmountsList.size(); i++) {
            if (!ordersAmountsList.get(i).trim().equals(""))
                ordersCalculatedAmount += Integer.parseInt(ordersAmountsList.get(i).trim());
        }
        Assert.assertEquals(Integer.parseInt(ordersAmountsList.get(0).trim()), ordersCalculatedAmount,
                "\"ALL\" category contains incorrect orders number");
    }

    public static void verifyOrdersAmountForDepartmentIsCorrect(String department, int expectedNumber) {

        VNextBOROPageStepsNew.openDepartmentsDropDown();
        Assert.assertEquals(VNextBOROPageStepsNew.getOrdersAmountForDepartment(department), expectedNumber,
                "Department " + department + " has contained incorrect orders number in the departments filter dropdown");
        VNextBOROPageStepsNew.switchToFilterTab("Departments");
    }

    public static void verifyOrdersAmountForPhaseInTableIsCorrect(String phase, int expectedNumber) {

        Assert.assertEquals(VNextBOROPageStepsNew.getOrdersAmountForPhaseFromTable(phase), expectedNumber,
                "Phase " + phase + " has contained incorrect orders number in the departments filter dropdown");
    }

    public static void verifyPoNumberFieldIsNotClickableForFirstOrder() {

        Assert.assertFalse(Utils.isElementClickable(new VNextBOROWebPageNew().getPoNumbersList().get(0)),
                "PO# has been clickable for the order");
    }

    public static void verifyCheckInCheckOutActionButtons(boolean checkedIn) {

        VNextBOROWebPageNew ordersPage = new VNextBOROWebPageNew();
        if (checkedIn) {
            Assert.assertTrue(Utils.isElementDisplayed(ordersPage.getCheckOutActionButton()), "\"Check Out\" action button hasn't been displayed");
            Assert.assertFalse(Utils.isElementDisplayed(ordersPage.getCheckInActionButton()), "\"Check In\" action button has been displayed");
        } else {
            Assert.assertFalse(Utils.isElementDisplayed(ordersPage.getCheckOutActionButton()), "\"Check Out\" action button has been displayed");
            Assert.assertTrue(Utils.isElementDisplayed(ordersPage.getCheckInActionButton()), "\"Check In\" action button hasn't been displayed");
        }
    }

    public static void verifyNotePopUpIsDisplayed(boolean shouldBeDisplayed) {

        VNextBOROWebPageNew ordersPage = new VNextBOROWebPageNew();
        if (shouldBeDisplayed) {
            Assert.assertTrue(Utils.isElementDisplayed(ordersPage.getOrderNoteText()), "Order note text hasn't been displayed");
            Assert.assertTrue(Utils.isElementDisplayed(ordersPage.getOrderNoteXIcon()), "Order note x-icon hasn't been displayed");
        } else {
            Assert.assertFalse(Utils.isElementDisplayed(ordersPage.getOrderNoteText()), "Order note text hasn't been hidden");
            Assert.assertFalse(Utils.isElementDisplayed(ordersPage.getOrderNoteXIcon()), "Order note x-icon hasn't been hidden");
        }
    }

    public static void verifyNoteTextIsCorrectForFirstOrder(String noteText, boolean equal) {

        VNextBOROWebPageNew ordersPage = new VNextBOROWebPageNew();
        if (equal) {
            ConditionWaiter.create(__ -> Utils.getText(ordersPage.getOrderNoteText()).equals(noteText)).execute();
            Assert.assertEquals(Utils.getText(ordersPage.getOrderNoteText()), noteText, "Note's text hasn't been correct");
        } else
            Assert.assertNotEquals(noteText, Utils.getText(ordersPage.getOrderNoteText()), "Note's text hasn't been correct");
    }

    public static void verifyPriorityIsCorrectForFirstOrder(String expectedPriority) {

        VNextBOROWebPageNew ordersPage = new VNextBOROWebPageNew();
        switch (expectedPriority) {
            case "Low":
                Assert.assertTrue(Utils.isElementDisplayed(ordersPage.getLowPriorityIcon()), "Low priority icon hasn't been displayed");
                break;
            case "High":
                Assert.assertTrue(Utils.isElementDisplayed(ordersPage.getHighPriorityIcon()), "High priority icon hasn't been displayed");
                break;
            case "Normal":
                try {
                    Assert.assertFalse(Utils.isElementDisplayed(ordersPage.getLowPriorityIcon()), "Low priority icon has been displayed");
                    Assert.assertFalse(Utils.isElementDisplayed(ordersPage.getHighPriorityIcon()), "High priority icon has been displayed");
                } catch (NoSuchElementException ex) {
                }

                break;
        }
    }

    public static void verifySavedSearchEditPencilIconIsDisplayed(boolean shouldBeDisplayed) {

        if (shouldBeDisplayed)
            Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getEditSavedSearchPencilIcon()),
                    "Edit search pencil icon hasn't been displayed");
        else Assert.assertFalse(Utils.isElementDisplayed(new VNextBOROWebPageNew().getEditSavedSearchPencilIcon()),
                "Edit search pencil icon has been displayed");
    }

    public static void verifyOrderFlagIsCorrect(String orderNumber, String expectedBorderColor, String flagColor) {

        Assert.assertEquals(new VNextBOROWebPageNew().orderColumnByOrderNumber(orderNumber).getCssValue("border-left"), expectedBorderColor,
                "Left border has had incorrect color");
        Assert.assertEquals(new VNextBOROWebPageNew().orderRowByOrderNumber(orderNumber).getAttribute("class"), flagColor,
                "Row has had incorrect background color");
    }

    public static void verifyStartPhaseServicesActionButtonIsDisplayed(boolean shouldBeDisplayed) {

        ConditionWaiter.create(__ -> new VNextBOROWebPageNew().getOrdersPhasesList().get(0).isEnabled()).execute();
        Utils.clickElement(new VNextBOROWebPageNew().getOrdersPhasesList().get(0));
        WaitUtilsWebDriver.waitABit(1000);
        if (shouldBeDisplayed) {
            Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getStartPhaseServicesActionButton()),
                    "Start phase services action button hasn't been displayed");
        } else
            Assert.assertFalse(Utils.isElementDisplayed(new VNextBOROWebPageNew().getStartPhaseServicesActionButton()),
                    "Start phase services action button has been displayed");
        Utils.clickElement(new VNextBOROWebPageNew().getOrdersPhasesList().get(0));
    }

    public static void verifyFirstServiceIconInTheCurrentPhaseDropdown(int serviceNumber, String expectedIcon) {

        ConditionWaiter.create(__ -> new VNextBOROWebPageNew().getOrdersPhasesList().get(0).isEnabled()).execute();
        Utils.clickElement(new VNextBOROWebPageNew().getOrdersPhasesList().get(0));
        WaitUtilsWebDriver.waitABit(1000);
        Assert.assertEquals(new VNextBOROWebPageNew().getCurrentPhaseServiceRecords().
                        get(serviceNumber).findElement(By.xpath("./i[not(contains(@style,'display: none;'))]")).getAttribute("class"), expectedIcon,
                "Service icon hasn't been correct");
        Utils.clickElement(new VNextBOROWebPageNew().getOrdersPhasesList().get(0));
    }

    public static void verifyCurrentPhaseDoesNotContainServices() {

        ConditionWaiter.create(__ -> new VNextBOROWebPageNew().getOrdersPhasesList().get(0).isEnabled()).execute();
        Utils.clickElement(new VNextBOROWebPageNew().getOrdersPhasesList().get(0));
        WaitUtilsWebDriver.waitABit(1000);
        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getCurrentPhaseNoServicesMessage()),
                "No services message hasn't been displayed");
        Assert.assertEquals(Utils.getText(new VNextBOROWebPageNew().getCurrentPhaseNoServicesMessage()), "There are no individual services to start or complete!",
                "No services message has contained incorrect text");
        Utils.clickElement(new VNextBOROWebPageNew().getOrdersPhasesList().get(0));
    }

    public static void verifyPinnedSearchIsDisplayed(String savedSearch, boolean shouldBeDisplayed) {

        if (shouldBeDisplayed) {
            ConditionWaiter.create(__ -> new VNextBOROWebPageNew().pinnedSearchBlock(savedSearch).isDisplayed()).execute();
            Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().pinnedSearchBlock(savedSearch)),
                    "Pinned search hasn't been displayed");
        } else Assert.assertFalse(Utils.isElementDisplayed(new VNextBOROWebPageNew().pinnedSearchBlock(savedSearch)),
                "Pinned search has been displayed");
    }

    public static void verifyPinSavedSearchIconBackground(String savedSearch, String expectedColor) {

        VNextBOROPageStepsNew.clickSavedSearchesDropDownField();
        Assert.assertTrue(new VNextBOROWebPageNew().pinSavedSearch(savedSearch).getCssValue("background").contains(expectedColor),
                "Pin icon background hasn't been correct");
        VNextBOROPageStepsNew.clickSavedSearchesDropDownField();
    }

    public static void verifyNotificationMessageIsDisplayedAndCorrect() {

        Assert.assertTrue(Utils.isElementDisplayed(new VNextBOROWebPageNew().getNotificationMessage()),
                "Notification message hasn't been displayed");
        Assert.assertEquals(Utils.getText(new VNextBOROWebPageNew().getNotificationMessage()), "Warning. You cannot add more than 3 pinned searches.",
                "Unpin tooltip text hasn't been correct");
    }

    public static void verifyPinnedSearchCounterNumberIsCorrect(String savedSearch) {

        VNextBOROWebPageNew ordersPage = new VNextBOROWebPageNew();
        if (VNextBOROPageStepsNew.checkIfNoRecordsFoundMessageIsDisplayed())
            Assert.assertEquals(Utils.getText(ordersPage.pinnedSearchCounter(savedSearch)), "0",
                    "Pinned search has contained incorrect counter number");
        else {
            int ordersNumber = new VNextBOROWebPageNew().getRepairOrdersTableRowsList().size();
            Assert.assertEquals(Integer.parseInt(Utils.getText(ordersPage.pinnedSearchCounter(savedSearch))), ordersNumber,
                    "Pinned search has contained incorrect counter number");
        }
    }
}
