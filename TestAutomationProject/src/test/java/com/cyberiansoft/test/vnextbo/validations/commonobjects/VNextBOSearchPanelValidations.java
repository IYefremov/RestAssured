package com.cyberiansoft.test.vnextbo.validations.commonobjects;

import com.cyberiansoft.test.baseutils.ConditionWaiter;
import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.vnextbo.screens.commonobjects.VNextBOSearchPanel;
import com.cyberiansoft.test.vnextbo.steps.commonobjects.VNextBOSearchPanelSteps;
import com.cyberiansoft.test.vnextbo.validations.VNextBOBaseWebPageValidations;
import org.testng.Assert;

public class VNextBOSearchPanelValidations extends VNextBOBaseWebPageValidations {

    public static void verifySearchPanelIsDisplayed() {

        VNextBOSearchPanel searchPanel = new VNextBOSearchPanel();
        Assert.assertTrue(Utils.isElementDisplayed(searchPanel.getSearchPanel()),
                "Search panel hasn't been displayed.");
        Assert.assertTrue(Utils.isElementDisplayed(searchPanel.getSearchInputField()),
                "Search input field hasn't been displayed.");
        Assert.assertTrue(Utils.isElementDisplayed(searchPanel.getSearchLoupeIcon()),
                "Search loupe icon hasn't been displayed.");
        Assert.assertTrue(Utils.isElementDisplayed(searchPanel.getAdvancedSearchCaret()),
                "Advanced search drop down caret hasn't been displayed.");
    }

    public static void verifySearchFieldIsDisplayed() {

        VNextBOSearchPanel searchPanel = new VNextBOSearchPanel();
        ConditionWaiter.create(__-> searchPanel.getSearchInputField().isDisplayed()).execute();
        Assert.assertTrue(Utils.isElementDisplayed(searchPanel.getSearchInputField()),
                "Search input field hasn't been displayed.");
        Assert.assertTrue(Utils.isElementDisplayed(searchPanel.getSearchLoupeIcon()),
                "Search loupe icon hasn't been displayed.");
    }

    public static void verifySearchFilterTextIsCorrect(String text) {

        Assert.assertTrue(VNextBOSearchPanelSteps.getSearchFilterText().contains(text),
                "Search option under Search field hasn't been correct");
    }

    public static void verifySearchFieldIsEmpty() {

        Assert.assertEquals(VNextBOSearchPanelSteps.getSearchFilterText(), "", "Search field hasn't been cleared");
    }

    public static void verifyXIconIsDisplayed(boolean shouldBeDisplayed) {

        if (shouldBeDisplayed) Assert.assertTrue(Utils.isElementDisplayed(new VNextBOSearchPanel().getSearchXIcon()),
                "Search XIcon hasn't been displayed");
        else Assert.assertFalse(Utils.isElementDisplayed(new VNextBOSearchPanel().getSearchXIcon()),
                "Search XIcon has been displayed");
    }
}