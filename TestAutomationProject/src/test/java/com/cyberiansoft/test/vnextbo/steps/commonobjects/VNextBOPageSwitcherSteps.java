package com.cyberiansoft.test.vnextbo.steps.commonobjects;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.baseutils.WaitUtilsWebDriver;
import com.cyberiansoft.test.vnextbo.screens.commonobjects.VNextBOPageSwitcherElements;
import com.cyberiansoft.test.vnextbo.steps.VNextBOBaseWebPageSteps;

public class VNextBOPageSwitcherSteps extends VNextBOBaseWebPageSteps {

    public static void clickHeaderNextPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getHeaderNextPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void clickFooterNextPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getFooterNextPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void clickHeaderPreviousPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getHeaderPreviousPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void clickFooterPreviousPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getFooterPreviousPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void clickHeaderLastPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getHeaderLastPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void clickFooterLastPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getFooterLastPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void clickHeaderFirstPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getHeaderFirstPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void clickFooterFirstPageButton() {

        Utils.clickElement(new VNextBOPageSwitcherElements().getFooterFirstPageBtn());
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static void openPageByNumber(int pageNumber) {

        Utils.clickElement(new VNextBOPageSwitcherElements().specificPageButton(pageNumber));
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static String getActivePageNumberFromHeaderPager() {

        return Utils.getText(new VNextBOPageSwitcherElements().getHeaderActivePageNumber());
    }

    public static String getActivePageNumberFromFooterPager() {

        return Utils.getText(new VNextBOPageSwitcherElements().getFooterActivePageNumber());
    }

    public static void changeItemsPerPage(String itemsPerPage) {

        VNextBOPageSwitcherElements switcherElements = new VNextBOPageSwitcherElements();
        Utils.clickElement(switcherElements.getHeaderItemsPerPageField());
        Utils.clickWithJS(switcherElements.itemsPerPageOption(itemsPerPage));
        WaitUtilsWebDriver.waitForSpinnerToDisappear();
    }

    public static String getItemsPerPageNumberFromTopElement() {

        return Utils.getText(new VNextBOPageSwitcherElements().getHeaderItemsPerPageField());
    }

    public static String getItemsPerPageNumberFromBottomElement() {

        return Utils.getText(new VNextBOPageSwitcherElements().getFooterItemsPerPageField());
    }
}