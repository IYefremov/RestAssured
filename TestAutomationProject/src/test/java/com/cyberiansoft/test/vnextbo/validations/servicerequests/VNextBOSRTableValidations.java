package com.cyberiansoft.test.vnextbo.validations.servicerequests;

import com.cyberiansoft.test.baseutils.CustomDateProvider;
import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.dataclasses.vNextBO.alerts.VNextBOAlertMessages;
import com.cyberiansoft.test.dataclasses.vNextBO.servicerequests.VNextBOSRSearchData;
import com.cyberiansoft.test.enums.DateUtils;
import com.cyberiansoft.test.enums.servicerequests.ServiceRequestStatus;
import com.cyberiansoft.test.vnextbo.screens.servicerequests.VNextBOSRTable;
import com.cyberiansoft.test.vnextbo.steps.servicerequests.VNextBOSRTableSteps;
import com.cyberiansoft.test.vnextbo.validations.servicerequests.details.VNextBOSRGeneralInfoValidations;
import com.cyberiansoft.test.vnextbo.validations.servicerequests.details.VNextBOSRVehicleInfoValidations;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VNextBOSRTableValidations {

    private final static String ALL_ACTIVE = "All Active (all not closed)";
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.FULL_DATE_FORMAT.getFormat());

    public static boolean isNotFoundNotificationDisplayed() {
        return VNextBOSRTableSteps.getEmptyTableNotification().equals(VNextBOAlertMessages.NO_RECORDS_FOUND);
    }

    public static void verifyNotFoundNotificationIsDisplayed() {
        Assert.assertEquals(VNextBOSRTableSteps.getEmptyTableNotification(), VNextBOAlertMessages.NO_RECORDS_FOUND,
                "'No records found' notification hasn't been displayed");
    }

    public static void verifyEitherNotificationOrSRsListIsDisplayed() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            verifyNotFoundNotificationIsDisplayed();
        } else {
            Assert.assertTrue(VNextBOSRTableSteps.getSRListSize() > 0, "The SRs haven't been displayed");
        }
    }

    public static void verifySRIsDisplayed(String actual, String expected) {
        Assert.assertEquals(actual, expected, "The SR hasn't been found");
    }

    public static void verifySRContainingValueIsDisplayed(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected), "The SR hasn't been found");
    }

    public static void verifySRStatusIsNotEqual(String actual, ServiceRequestStatus notEqualStatus) {
        Assert.assertNotEquals(actual, notEqualStatus.getValue(),
                "The SR status has been '" + notEqualStatus.getValue() + "'");
    }

    public static void verifySRStatusIsDisplayed(String actual, String expected) {
        if (expected.equals(ALL_ACTIVE)) {
            verifySRStatusIsNotEqual(actual, ServiceRequestStatus.CLOSED);
        } else {
            Assert.assertEquals(actual, expected, "The SR status hasn't been found");
        }
    }

    public static void verifyInspectionsDocumentsListIsEmpty() {
        final boolean contains = Utils.attributeContains(
                new VNextBOSRTable().getDocumentsInspectionsList(), "class", "hasDocuments");
        Assert.assertFalse(contains, "The SRs contain inspections documents");
    }

    public static void verifyInvoicesDocumentsListIsEmpty() {
        final boolean contains = Utils.attributeContains(
                new VNextBOSRTable().getDocumentsInvoicesList(), "class", "hasDocuments");
        Assert.assertFalse(contains, "The SRs contain invoices documents");
    }

    public static void verifyWoListContainsDocuments() {
        final boolean contains = Utils.attributeContains(
                new VNextBOSRTable().getDocumentsWoList(), "class", "noDocuments");
        Assert.assertFalse(contains, "The SRs do not contain WO documents");
    }

    public static void verifyWoDocumentsListIsEmpty() {
        final boolean contains = Utils.attributeContains(
                new VNextBOSRTable().getDocumentsWoList(), "class", "hasDocuments");
        Assert.assertFalse(contains, "The SRs contain WO documents");
    }

    public static void verifyMoreServiceRequestsHaveBeenLoaded(int previousSRListSize) {
        Assert.assertTrue(VNextBOSRTableSteps.getSRListSize() > previousSRListSize,
                "The SR list size han't been increased after clicking the 'Load 20 more items' button");
    }

    public static void verifyTimeFrameToday() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> verifySRIsDisplayed(
                            value, CustomDateProvider.getCurrentDateInFullFormat(true)));
        }
    }

    public static void verifyTimeFrameWeekToDate() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean after = date.isAfter(CustomDateProvider.getWeekStartDate());
                final boolean equal = date.isEqual(CustomDateProvider.getWeekStartDate());
                Assert.assertTrue(equal || after,
                        "The created order date should be more/equal than 00:00 of the current week");
            });
        }
    }

    public static void verifyLastWeekTimeFrame() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean after = date.isAfter(CustomDateProvider.getLastWeekStartDate());
                final boolean before = date.isBefore(CustomDateProvider.getLastWeekEndDate());
                Assert.assertTrue(after && before,
                        "The created order date should be between 00:00 Monday and 23:59 Sunday of the previous week");
            });
        }
    }

    public static void verifyMonthToDateTimeFrame() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean after = date.isAfter(CustomDateProvider.getLastMonthEndDate());
                Assert.assertTrue(after,
                        "The created order date should be not less than 00:00 of the 1st day of the current month");
            });
        }
    }

    public static void verifyLastMonthTimeFrame() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean before = date.isBefore(CustomDateProvider.getMonthStartDate());
                final boolean after = date.isAfter(CustomDateProvider.getLastMonthEndDate().minusMonths(1));
                Assert.assertTrue(before && after, "The created order date should be between 00:00 of 1st day " +
                        "and 23:59 of the last day of the previous month");
            });
        }
    }

    public static void verifyLast30DaysTimeFrame() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean equal = date.isEqual(CustomDateProvider.getCurrentDateLocalized());
                final boolean after = date.isAfter(CustomDateProvider.getLastThirtyDaysStartDate());
                Assert.assertTrue(equal || after, "The created order date should be " +
                        "not less than from 00:00 30 days ago and up to the current date");
            });
        }
    }

    public static void verifyLast90DaysTimeFrame() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean equal = date.isEqual(CustomDateProvider.getCurrentDateLocalized());
                final boolean after = date.isAfter(CustomDateProvider.getThreeMonthsBeforeCurrentDate());
                Assert.assertTrue(equal || after, "The created order date should be " +
                        "not less than from 00:00 30 days ago and up to the current date");
            });
        }
    }

    public static void verifyYearToDateTimeFrame() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean equal = date.isEqual(CustomDateProvider.getCurrentDateLocalized());
                final boolean after = date.isAfter(CustomDateProvider.getYearStartDate());
                Assert.assertTrue(equal || after, "The created order date should be " +
                        "not less than 00:00 of the 1st day of the current year");
            });
        }
    }

    public static void verifyLastYearTimeFrame() {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean after = date.isAfter(CustomDateProvider.getLastYearStartDate());
                final boolean before = date.isBefore(CustomDateProvider.getYearStartDate());
                Assert.assertTrue(before && after, "The created order date should be " +
                        "between 00:00 of 1st day and 23:59 of the last day of the previous year");
            });
        }
    }

    public static void verifyCustomTimeFrame(VNextBOSRSearchData searchData) {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            VNextBOSRTableValidations.verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.getUniqueCreatedDatesFields().forEach(value -> {
                final LocalDate date = LocalDate.parse(value, formatter);
                final boolean after = date.isAfter(LocalDate.parse(searchData.getFromDate(), formatter));
                final boolean before = date.isBefore(LocalDate.parse(searchData.getToDate(), formatter));
                final boolean equalFrom = date.isEqual(LocalDate.parse(searchData.getFromDate(), formatter));
                final boolean equalTo = date.isEqual(LocalDate.parse(searchData.getToDate(), formatter));
                Assert.assertTrue((equalFrom || after) && (equalTo || before), "The created order date should be " +
                        "between 00:00 of " + searchData.getFromDate() + " and 23:59 of " + searchData.getToDate());
            });
        }
    }

    public static void verifyAcceptButtonsAreEnabled() {
        Assert.assertFalse(Utils.attributeContains(
                new VNextBOSRTable().getAcceptButtonsList(), "class", "Disabled"),
                "The 'Accept' buttons are not enabled");
    }

    public static void verifyRejectButtonsAreEnabled() {
        Assert.assertFalse(Utils.attributeContains(
                new VNextBOSRTable().getAcceptButtonsList(), "class", "Disabled"),
                "The 'Accept' buttons are not enabled");
    }

    public static void verifyEitherNotificationOrROIsDisplayed(String roNum) {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.openSRDetailsPage(0);
            VNextBOSRGeneralInfoValidations.verifyRoIsDisplayed(roNum);
        }
    }

    public static void verifyEitherNotificationOrVINIsDisplayed(String vinNum) {
        if (VNextBOSRTableSteps.getSRListSize() == 0) {
            verifyNotFoundNotificationIsDisplayed();
        } else {
            VNextBOSRTableSteps.openSRDetailsPage(0);
            VNextBOSRVehicleInfoValidations.verifyVinIsDisplayed(vinNum);
        }
    }
}
