package com.cyberiansoft.test.vnextbo.interactions.repairorders;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.screens.repairorders.VNextBORODetailsPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.cyberiansoft.test.baseutils.Utils.setAttributeWithJS;

public class VNextBORODetailsPageInteractions {

    public static List<String> getVendorPricesValuesList() {
        WaitUtilsWebDriver.waitABit(1000);
        List<String> vendorPricesValuesList = new ArrayList<>();
        for (WebElement vendorPrice : new VNextBORODetailsPage().getVendorPricesList()) {
            vendorPricesValuesList.add(vendorPrice.getAttribute("value"));
        }
        return vendorPricesValuesList;
    }

    public static String getRoStatus() {
        return Utils.getText(new VNextBORODetailsPage().getRoStatusElement());
    }

    public static void clickFlagIcon() {
        WaitUtilsWebDriver.waitForLoading();
        Utils.clickElement(new VNextBORODetailsPage().getFlagIcon());
    }

    public static String getPhaseVendorPriceValue() {
        return Utils.getText(new VNextBORODetailsPage().getPhaseVendorPrice());
    }

    public static void setStatus(String status) {
        clickStatusBox();
        selectStatus(status);
    }

    public static void setPhaseStatus(String phase, String status) {
        clickPhaseStatusBox(phase);
        selectPhaseStatus(status);
    }

    private static void clickPhaseStatusBox(String phase) {
        WaitUtilsWebDriver.waitForLoading();
        Utils.clickElement(new VNextBORODetailsPage().getPhaseStatusBox(phase));
        WaitUtilsWebDriver.waitABit(1000);
    }

    private static void clickStatusBox() {
        WaitUtilsWebDriver.waitForLoading();
        Utils.clickElement(new VNextBORODetailsPage().getStatusListBox());
        WaitUtilsWebDriver.waitABit(1000);
    }

    private static void selectStatus(String status) {
        final VNextBORODetailsPage detailsPage = new VNextBORODetailsPage();
        Utils.selectOptionInDropDown(
                detailsPage.getStatusDropDownContainer(), detailsPage.getPhaseStatusListBoxOptions(), status);
    }

    private static void selectPhaseStatus(String status) {
        //Utils.selectOptionInDropDown(new VNextBORODetailsPage().getDropDownContainer(),
        //        new VNextBORODetailsPage().getPhaseStatusListBoxOptions(), status, true);
        Utils.clickWithJS(new VNextBORODetailsPage().getPhaseStatusOptionByName(status));
        WaitUtilsWebDriver.waitABit(500);
    }

    public static String getRoStatusValue() {
        try {
            return WaitUtilsWebDriver.waitForVisibility(new VNextBORODetailsPage().getRoStatusElement()).getText();
        } catch (Exception ignored) {
            return "";
        }
    }

    public static void updateRoStatusValue(String previousStatus) {
        try {
            WaitUtilsWebDriver
                    .getShortWait()
                    .until((ExpectedCondition<Boolean>) driver ->
                            !getRoStatusValue().equals(previousStatus));
        } catch (Exception ignored) {
        }
    }

    public static String getServiceId(String description) {
        final WebElement serviceElement = new VNextBORODetailsPage().getServiceByName(description);
        if (serviceElement != null) {

            final String id = WaitUtilsWebDriver.getWait()
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(serviceElement))
                    .findElement(By.xpath(".//../..")).getAttribute("data-order-service-id");
            System.out.println(id);
            return id;
        }
        return "";
    }

    public static List<String> getAllServicesId(String description) {
        final List<WebElement> allServicesByName = new VNextBORODetailsPage().getAllServicesByName(description);
        WaitUtilsWebDriver.waitForVisibilityOfAllOptions(allServicesByName);
        try {
            return allServicesByName.stream().map(element -> WaitUtilsWebDriver.getWait()
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element))
                    .findElement(By.xpath(".//../..")).getAttribute("data-order-service-id")).collect(Collectors.toList());
        } catch (Exception ignored) {
            return Collections.emptyList();
        }
    }

    public static void expandPhasesTable() {
        final VNextBORODetailsPage detailsPage = new VNextBORODetailsPage();
        Utils.clickElement(detailsPage.getServicesExpandArrow());
        WaitUtilsWebDriver.waitForInvisibilityIgnoringException(detailsPage.getServicesExpandArrow(), 3);
    }

    public static void expandPhasesTable(String phase) {
        final WebElement arrow = new VNextBORODetailsPage().getPhaseButton(phase);
        if (Utils.isElementDisplayed(arrow)) {
            Utils.clickElement(arrow);
            WaitUtilsWebDriver.waitForInvisibilityIgnoringException(arrow, 3);
        }
    }

    public static int getNumberOfVendorTechnicianOptionsByName(String name) {
        try {
            return WaitUtilsWebDriver
                    .getWait()
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-template='" +
                            "order-service-item-template']//div[@class='clmn_4']//span[@class='k-input' and text()='" +
                            name + "']"))).size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setServiceStatusForService(String serviceId, String status) {
        clickServiceStatusBox(serviceId);
        selectServiceStatus(status);
    }

    public static void setServiceStatusForService(int order, String status) {
        clickServiceStatusBox(order);
        selectServiceStatus(status);
    }

    private static void clickServiceStatusBox(int order) {
        WaitUtilsWebDriver.waitForPageToBeLoaded();
        final WebElement serviceStatus = new VNextBORODetailsPage().getServicesStatusWidgetList().get(order);
        Utils.clickElement(serviceStatus);
    }

    public static void clickServiceStatusBox(String serviceId) {
        final WebElement service = new VNextBORODetailsPage().getServiceStatusBoxByServiceId(serviceId);
        Utils.clickWithActions(service);
    }

    public static void selectServiceStatus(String status) {
        Utils.selectOptionInDropDown(new VNextBORODetailsPage().getServiceStatusDropDown(),
                new VNextBORODetailsPage().getServiceStatusListBoxOptions(), status);
    }

    public static String getServiceStatusValue(String serviceId) {
        final WebElement serviceStatus = new VNextBORODetailsPage().getServiceStatusByServiceId(serviceId);
        WaitUtilsWebDriver.waitForElementNotToBeStale(serviceStatus);
        return serviceStatus.getText();
    }

    public static String waitForServiceStatusToBeChanged(String serviceId, String service) {
        try {
            WaitUtilsWebDriver.getWait().until((ExpectedCondition<Boolean>) driver ->
                    getServiceStatusValue(serviceId).equals(service));
        } catch (Exception ignored) {}
        return getServiceStatusValue(serviceId);
    }

    public static String getServiceDescription(String serviceId) {
        return WaitUtilsWebDriver
                .getWait()
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//div[@class='serviceRow' and @data-order-service-id='"
                                + serviceId + "']/div[@class='clmn_2']/div[@title]")))
                .getText();
    }

    public static List<String> getServiceAndTaskDescriptionsList() {
        final List<WebElement> serviceAndTaskDescriptionsList = new VNextBORODetailsPage().getServiceAndTaskDescriptionsList();
        WaitUtilsWebDriver.waitForVisibilityOfAllOptions(serviceAndTaskDescriptionsList, 3);
        return serviceAndTaskDescriptionsList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static String getServiceQuantity(String serviceId) {
        System.out.println(serviceId);
        return getTextValue(serviceId, "/div[@class='clmn_2_1 grid__number']/span", ".000");
    }

    public static String getServiceLaborTime(String serviceId) {
        return getTextValue(serviceId, "/div[@class='clmn_2_1 grid__number']/span", ".00 hr");
    }

    public static String getServicePrice(String serviceId) {
        String result = getTextValue(serviceId, "/div[@class='clmn_3 grid__number']/span");
        return StringUtils.substringBefore(result, ",");
    }

    public static String getServiceVendorPrice(String serviceId) {
        final WebElement vendorPrice = new VNextBORODetailsPage().getElementInServicesTable(
                serviceId, "/div[@class='clmn_3_1']/input");
        return Utils.getInputFieldValue(vendorPrice, 3);
    }

    public static void setServiceVendorPrice(String serviceId, String serviceDescription, String newValue) {
        setTextValue(serviceId, serviceDescription, "/div[@class='clmn_3_1']/input", newValue);
    }

    public static void setServiceVendorPrice(String serviceId, String newValue) {
        setTextValue(serviceId, "/div[@class='clmn_3_1']/input", newValue);
    }

    public static void setServicePrice(String serviceId, String newValue) {
        setTextValue(serviceId, "//div[@class='clmn_3 grid__number']/input", newValue);
    }

    public static void setServiceQuantity(String serviceId, String newValue) {
        setTextValue(serviceId, "//div[@class='clmn_2_1 grid__number']/input", newValue);
    }

    private static void setTextValue(String serviceId, String xpath, String newValue) {
        final WebElement element = new VNextBORODetailsPage().getElementInServicesTable(serviceId, xpath);
        Utils.clearAndTypeUsingKeyboard(element, newValue);
        Utils.clickElement(new VNextBORODetailsPage().getVendorPriceTitle());
//		clickServiceDescriptionName(serviceDescription);
        WaitUtilsWebDriver.waitABit(500);
    }

    private static String getTextValue(String serviceId, String xpath, String replacement) {
        final WebElement element = new VNextBORODetailsPage().getElementInServicesTable(serviceId, xpath);
        setAttributeWithJS(element, "style", "display: block;");
        WaitUtilsWebDriver.waitABit(1000);
        final String text = WaitUtilsWebDriver.waitForVisibility(element).getText().replace(replacement, "");
        setAttributeWithJS(element, "style", "display: none;");
        return text;
    }

    private static String getTextValue(String serviceId, String xpath) {
        final WebElement element = new VNextBORODetailsPage().getElementInServicesTable(serviceId, xpath);
        setAttributeWithJS(element, "style", "display: block;");
        final String text = WaitUtilsWebDriver.waitForVisibility(element).getText();
        setAttributeWithJS(element, "style", "display: none;");
        return text;
    }

    private static void setTextValue(String serviceId, String serviceDescription, String xpath, String newValue) {
        final WebElement element = new VNextBORODetailsPage().getElementInServicesTable(serviceId, xpath);
        Utils.clearAndTypeWithJS(element, newValue);
        clickServiceDescriptionName(serviceDescription);
        WaitUtilsWebDriver.waitABit(1000);
    }

    private static void clickServiceDescriptionName(String serviceDescription) {
        Objects.requireNonNull(WaitUtilsWebDriver
                .waitForElementToBeClickable(new VNextBORODetailsPage().getServiceByName(serviceDescription)))
                .click();
    }

    public static void openActionsDropDownForPhase() {
        handleActionsButton(new VNextBORODetailsPage().getPhaseActionsDropDown());
    }

    public static void openActionsDropDownForPhase(String phase) {
        Utils.clickElement(new VNextBORODetailsPage().getActionsTriggerForPhase(phase));
    }

    public static void closeActionsDropDownForPhase() {
        handleActionsButton(new VNextBORODetailsPage().getPhaseActionsDropDownHidden());
    }

    private static void handleActionsButton(WebElement phaseActionsDropDown) {
        try {
            WaitUtilsWebDriver.waitForVisibility(phaseActionsDropDown, 1);
        } catch (Exception e) {
            Utils.clickElement(new VNextBORODetailsPage().getPhaseActionsTrigger());
        }
    }

    private static void setOption(WebElement option) {
        Utils.clickElement(option);
        WaitUtilsWebDriver.waitForPageToBeLoaded(5);
    }

    public static void clickReportProblemForPhase(String phase) {
        setOption(new VNextBORODetailsPage().getPhaseActionsReportProblemOption(phase));
    }

    public static void clickResolveProblemForPhase(String phase) {
        setOption(new VNextBORODetailsPage().getPhaseActionsResolveProblemOption(phase));
    }

    public static void clickCompleteCurrentPhaseForPhase(String phase) {
        setOption(new VNextBORODetailsPage().getCompleteCurrentPhaseActionsOption(phase));
    }

    public static void clickCheckInOptionForPhase() {
        setOption(new VNextBORODetailsPage().getPhaseActionsCheckInOption());
    }

    public static void clickCheckOutOptionForPhase() {
        setOption(new VNextBORODetailsPage().getPhaseActionsCheckOutOption());
    }

    public static void openNotesDialog(String serviceId) {
        new VNextBORODetailsPage().clickActionsIcon(serviceId);
        Utils.clickElement(By
                .xpath("//div[@class='serviceRow' and @data-order-service-id='" + serviceId
                        + "']//div[@class='clmn_7']/div[contains(@class, 'order-service-menu')]//label[text()='Notes']"));
        WaitUtilsWebDriver.waitForLoading();
    }

    public static void clickActionsIcon(String serviceId) {
        WaitUtilsWebDriver.waitABit(500);
        Utils.clickWithJS(new VNextBORODetailsPage().getActionIconForServiceId(serviceId));
    }

    public static void clickResetStartDate(String serviceId) {
        final VNextBORODetailsPage detailsPage = new VNextBORODetailsPage();
        final boolean visible = WaitUtilsWebDriver.elementShouldBeVisible(
                detailsPage.getResetStartDateButton(serviceId), true, 3);
        if (visible) {
            Utils.clickElement(detailsPage.getResetStartDateButton(serviceId));
            WaitUtilsWebDriver.waitForPageToBeLoaded();
            WaitUtilsWebDriver.waitForVisibility(detailsPage.getStartServiceButton(serviceId));
        }
    }

    public static List<String> getPhaseStatusValues() {
        try {
            return WaitUtilsWebDriver.waitForVisibilityOfAllOptions(new VNextBORODetailsPage().getPhaseStatusListBoxOptions())
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } catch (Exception ignored) {
            return null;
        }
    }

    public static void clickResolveProblemForService(String serviceId) {
        setOption(new VNextBORODetailsPage().getServiceResolveProblemOption(serviceId));
    }

    public static void clickReportProblemForService(String serviceId) {
        setOption(new VNextBORODetailsPage().getServiceReportProblemOption(serviceId));
    }

    public static void selectFlagColor(String color) {
        final WebElement flagColorElement = WaitUtilsWebDriver
                .waitForVisibility(new VNextBORODetailsPage().getFlagColorElement(color));
        try {
            Utils.clickElement(flagColorElement);
            WaitUtilsWebDriver.waitForAttributeToBe(flagColorElement, "class", "active");
            WaitUtilsWebDriver.waitForInvisibility(new VNextBORODetailsPage().getFlagsDropDown());
        } catch (Exception e) {
            Assert.fail("The flag " + color + " hasn't been selected", e);
        }
    }

    public static String getRepairOrderActivePhaseStatus() {
        return Utils.getText(new VNextBORODetailsPage().getOrderDetails().findElement(By.id("serviceName"))).trim();
    }

    public static String getRepairOrderCompletedValue() {
        return Utils.getText(new VNextBORODetailsPage().getOrderDetails().findElement(By.id("progressBarText"))).trim();
    }

    public static void expandRepairOrderServiceDetailsTable() {
        final WebElement orderServiceArrowDown = WaitUtilsWebDriver.waitForVisibility(
                new VNextBORODetailsPage().getOrderServiceArrowDown());
        if (Utils.isElementDisplayed(orderServiceArrowDown)) {
            Utils.clickElement(orderServiceArrowDown);
            WaitUtilsWebDriver.waitForVisibility(new VNextBORODetailsPage().getOrderServiceArrowUp(), 25);
        }
    }

    public static void clickStartOrderButton() {
        Utils.clickElement(new VNextBORODetailsPage().getStartOrderButton());
        WaitUtilsWebDriver.waitForInvisibility(new VNextBORODetailsPage().getStartOrderButton(), 25);
        WaitUtilsWebDriver.waitABit(1000);
    }

    public static String getRepairOrderServicesPhaseStatus() {
        return Utils.getText(new VNextBORODetailsPage().getOrderServicesTable().findElement(By.xpath(".//div[@class='clmn_5']/div/div"))).trim();
    }

    public static String getRepairOrderServicesStatus(String serviceName) {
        return Utils.getText(new VNextBORODetailsPage().getRepairOrderServiceColumn(serviceName)
                .findElement(By.xpath(".//div[@class='clmn_5']/div"))).trim();
    }

    public static void changeStatusForRepairOrderService(String serviceName, String newStatus) {
        WaitUtilsWebDriver.waitABit(2000);
        final VNextBORODetailsPage detailsPage = new VNextBORODetailsPage();
        WebElement serviceRow = detailsPage.getRepairOrderServiceColumn(serviceName);
        Utils.clickElement(serviceRow.findElement(By.xpath(".//div[@class='clmn_5']/div/span/span")));
        serviceRow = detailsPage.getRepairOrderServiceColumn(serviceName);
        Utils.clickElement(serviceRow.findElement(By.xpath(".//div[@class='clmn_5']/div/span/span")));
        WaitUtilsWebDriver.waitABit(2000);
        List<WebElement> popups = detailsPage.getRepairOrderServicePopups();
        for (WebElement pp : popups)
            if (pp.isDisplayed()) {
                pp.findElement(By.xpath("./li[text()='" + newStatus + "']")).click();

            }
        WaitUtilsWebDriver.waitForLoading();
        WaitUtilsWebDriver.waitABit(1000);
    }

    public static void clickRepairOrdersBackwardsLink() {
        Utils.clickElement(new VNextBORODetailsPage().getMainBreadCrumbsLink());
        WaitUtilsWebDriver.waitForLoading();
    }

    public static void typeStockNumber(String stockNumber) {
        clearAndTypeOrderNumber(new VNextBORODetailsPage().getStockNumInputField(), stockNumber);
    }

    public static void typeRoNumber(String roNumber) {
        clearAndTypeOrderNumber(new VNextBORODetailsPage().getRoNumInputField(), roNumber);
    }

    private static void clearAndTypeOrderNumber(WebElement inputField, String roNumber) {
        Utils.clearAndType(inputField, roNumber);
        Utils.clickElement(new VNextBORODetailsPage().getPhaseTextElement());
    }

    public static void setVendor(String serviceId, String vendor) {
        clickVendorBox(serviceId);
        selectVendor(vendor);
    }

    private static void clickVendorBox(String serviceId) {
        WebElement element = new VNextBORODetailsPage().getVendorBox(serviceId);
        Utils.clickWithActions(element);
    }

    private static void selectVendor(String vendor) {
        final VNextBORODetailsPage detailsPage = new VNextBORODetailsPage();
        Utils.selectOptionInDropDownWithJs(detailsPage.getListBoxOptions().get(0),
                detailsPage.getListBoxOptions(), vendor);
    }

    public static void setTechnician(String serviceId, String technician) {
        clickTechnicianBox(serviceId);
        selectTechnician(technician);
    }

    public static String setTechnician(String serviceId) {
        clickTechnicianBox(serviceId);
        return selectTechnician();
    }

    private static void clickTechnicianBox(String serviceId) {
        WebElement element = new VNextBORODetailsPage().getTechnicianBox(serviceId);
        Utils.clickWithActions(element);
    }

    private static void selectTechnician(String technician) {
        Utils.selectOptionInDropDownWithJs(new VNextBORODetailsPage().getListBoxOptions().get(0),
                new VNextBORODetailsPage().getListBoxOptions(), technician);
    }

    private static String selectTechnician() {
        return Utils.selectOptionInDropDown(new VNextBORODetailsPage().getListBoxOptions().get(0),
                new VNextBORODetailsPage().getListBoxOptions());
    }

    public static void setPriority(String priority) {
        clickPriorityBox();
        selectPriority(priority);
    }

    private static void clickPriorityBox() {
        WaitUtilsWebDriver.waitForLoading();
        Utils.clickElement(new VNextBORODetailsPage().getPriorityListBox());
    }

    private static void selectPriority(String priority) {
        final List<WebElement> priorityListBoxOptions = new VNextBORODetailsPage().getPriorityListBoxOptions();
        Utils.selectOptionInDropDown(priorityListBoxOptions.get(0), priorityListBoxOptions, priority);
    }

    public static List<String> getServicesTableHeaderValues() {
        final List<WebElement> servicesTableColumns = new VNextBORODetailsPage().getServicesTableColumns();
        WaitUtilsWebDriver.waitForVisibilityOfAllOptions(servicesTableColumns);
        final List<String> stringCollection = servicesTableColumns
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return stringCollection.subList(1, stringCollection.size());
    }

    public static void clickLogInfoButton() {
        Utils.clickElement(new VNextBORODetailsPage().getLogInfoButton());
        WaitUtilsWebDriver.waitForLoading();
    }

    public static void openMoreInformation() {
        clickMoreInformationLink();
        WaitUtilsWebDriver.waitForVisibility(new VNextBORODetailsPage().getLessInformationLink());
    }

    public static void closeMoreInformation() {
        clickLessInformationLink();
        WaitUtilsWebDriver.waitForInvisibility(new VNextBORODetailsPage().getMoreInformationLink());
    }

    private static void clickMoreInformationLink() {
        Utils.clickElement(new VNextBORODetailsPage().getMoreInformationLink());
    }

    private static void clickLessInformationLink() {
        Utils.clickElement(new VNextBORODetailsPage().getLessInformationLink());
    }

    public static List<String> getMoreInformationFieldsText() {
        return WaitUtilsWebDriver.waitForVisibilityOfAllOptions(new VNextBORODetailsPage().getMoreInformationFields())
                .stream()
                .map(WebElement::getText)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public static String getServiceStartedDate(String serviceId) {
        return Utils.getText(By.xpath("//div[@data-order-service-id='" + serviceId
                        + "']//div[@class='clmn_6']//span[text()][1]"));
    }

    public static String getServiceCompletedDate(String serviceId) {
        return Utils.getText(By.xpath("//div[@data-order-service-id='" + serviceId
                        + "']//div[@class='clmn_6']//span[text()][2]"));
    }

    public static void hoverOverServiceHelperIcon(String serviceId) {
        try {
//            final WebElement helpInfo = driver.findElement(By.xpath("//div[@data-order-service-id='" + serviceId
//                    + "']//span[@class='helpInfo']/.."));
            final WebElement helpInfo = DriverBuilder.getInstance().getDriver()
                    .findElement(By.xpath("//div[@data-order-service-id='" + serviceId
                            + "']//i[@class='help']"));
            WaitUtilsWebDriver.waitForVisibility(helpInfo);
            Utils.moveToElement(helpInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getServiceIdContainingName(String description) {
        return getService(new VNextBORODetailsPage().getServiceContainingName(description));
    }

    private static String getService(WebElement serviceElement) {
        System.out.println(serviceElement);
        if (serviceElement != null) {
            WaitUtilsWebDriver.waitForElementNotToBeStale(serviceElement);
            final String id = WaitUtilsWebDriver.waitForVisibility(serviceElement)
                    .findElement(By.xpath(".//../..")).getAttribute("data-order-service-id");
            System.out.println(id);
            return id;
        }
        return "";
    }

    public static void clickAddNewServiceButton() {
        Utils.clickElement(new VNextBORODetailsPage().getAddNewServiceButton());
        WaitUtilsWebDriver.waitForLoading();
    }

    public static void clickPhaseVendorTechnicianLink() {
        Utils.clickElement(new VNextBORODetailsPage().getPhaseVendorTechnician());
    }

    public static String getPhaseNameValue() {
        return Utils.getText(new VNextBORODetailsPage().getPhaseName());
    }

    public static String getPhaseVendorTechnicianValue() {
        return Utils.getText(new VNextBORODetailsPage().getPhaseVendorTechnician());
    }

    public static String getPhaseStatusValue() {
        return Utils.getText(new VNextBORODetailsPage().getPhaseStatusesList().get(0));
    }

    public static String getOrderCurrentPhase() {
        final WebElement orderDetailsPhaseName = new VNextBORODetailsPage().getOrderDetailsPhaseName();
        Utils.moveToElement(orderDetailsPhaseName);
        return Utils.getText(orderDetailsPhaseName);
    }

    public static String getTotalServicesPrice() {
        final WebElement totalServicePrice = new VNextBORODetailsPage().getTotalServicePrice();
        Utils.moveToElement(totalServicePrice);
        return Utils.getText(totalServicePrice);
    }

    public static void updateTotalServicePrice(String totalPrice) {
        final WebElement totalServicePrice = new VNextBORODetailsPage().getTotalServicePrice();
        try {
            WaitUtilsWebDriver.waitForVisibility(totalServicePrice);
            Utils.moveToElement(totalServicePrice);
            WaitUtilsWebDriver.getWebDriverWait(20)
                    .until((ExpectedCondition<Boolean>) driver -> !totalPrice.equals(getTotalServicesPrice()));
        } catch (Exception ignored) {}
    }

    public static String getFirstPartIdFromPartsList() {
        return WaitUtilsWebDriver.waitForVisibilityOfAllOptions(new VNextBORODetailsPage().getPartsList())
                .get(0).getAttribute("data-order-service-id");
    }

    public static void openNotesDialogForPart(WebElement partsAction) {
        Utils.clickElement(partsAction.findElement(By.xpath(".//label[text()='Notes']")));
        WaitUtilsWebDriver.waitForLoading();
    }

    public static List<String> getPhasesServicesId(String phase) {
        return WaitUtilsWebDriver.waitForVisibilityOfAllOptions(new VNextBORODetailsPage()
                .getPhaseServicesList(phase), 2)
                .stream()
                .map(e -> e.getAttribute("data-order-service-id"))
                .collect(Collectors.toList());
    }

    public static List<String> getPhasesServicesStatusesId(String phase) {
        return Utils.getText(new VNextBORODetailsPage().getPhaseServicesStatusesList(phase));
    }

    public static void waitForPhaseActionsTriggerToBeDisplayed() {
        WaitUtilsWebDriver.elementShouldBeVisible(new VNextBORODetailsPage().getPhaseActionsTrigger(), true, 2);
    }

    public static void waitForPhaseActionsTriggerToBeDisplayed(String phase) {
        WaitUtilsWebDriver.elementShouldBeVisible(new VNextBORODetailsPage().getPhaseActionsTrigger(phase), true, 5);
    }

    public static void waitForPhaseActionsCheckInOption() {
        WaitUtilsWebDriver.elementShouldBeVisible(new VNextBORODetailsPage().getPhaseActionsCheckInOption(), true, 1);
    }

    public static void waitForPhaseActionsCheckOutOption() {
        WaitUtilsWebDriver.elementShouldBeVisible(new VNextBORODetailsPage().getPhaseActionsCheckOutOption(), true, 1);
    }
}