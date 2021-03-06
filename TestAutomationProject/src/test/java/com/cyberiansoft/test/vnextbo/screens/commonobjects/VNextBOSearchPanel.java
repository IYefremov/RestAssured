package com.cyberiansoft.test.vnextbo.screens.commonobjects;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.screens.VNextBOBaseWebPage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class VNextBOSearchPanel extends VNextBOBaseWebPage {

    @FindBy(xpath = "//section[@class='view']//div[contains(@class, 'pull-right custom-search')]")
    private WebElement searchPanel;

    @FindBy(xpath = "//section[@class='view']//div[contains(@class, 'custom-search')]//input[contains(@id, 'Text')]")
    private WebElement searchInputField;

    @FindBy(xpath = "//section[@class='view']//div[contains(@class, 'custom-search')]//i[@class='icon-search']")
    private WebElement searchLoupeIcon;

    @FindBy(xpath = "//section[@class='view']//div[contains(@class, 'custom-search')]//*[contains(@class, 'caret')]")
    private WebElement advancedSearchCaret;

    @FindBy(xpath = "//section[@class='view']//div[contains(@class, 'custom-search')]//i[contains(@class, 'cancel')]")
    private WebElement searchXIcon;

    @FindBy(xpath = "//section[@class='view']//div[contains(@class, 'custom-search')]//div[contains(@class, 'info-text')] | //section[@class='view']//div[contains(@class, 'custom-search')]//span[contains(@data-bind, 'search.info')]")
    private WebElement filterInfoText;

    public VNextBOSearchPanel() {
        super(DriverBuilder.getInstance().getDriver());
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
    }
}