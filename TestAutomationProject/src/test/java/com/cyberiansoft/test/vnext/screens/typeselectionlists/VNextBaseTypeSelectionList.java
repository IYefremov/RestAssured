package com.cyberiansoft.test.vnext.screens.typeselectionlists;

import com.cyberiansoft.test.vnext.screens.VNextBaseScreen;
import com.cyberiansoft.test.vnext.steps.SearchSteps;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class VNextBaseTypeSelectionList extends VNextBaseScreen {

    @FindBy(xpath = "//*[@data-automation-id='search-icon']")
    private WebElement searchbtn;

    @FindBy(xpath = "//*[@data-autotests-id='search-input']")
    private WebElement searchfld;

    @FindBy(xpath = "//*[@action='item']")
    private List<WebElement> typeList;

    public VNextBaseTypeSelectionList(WebDriver appiumdriver) {
        super(appiumdriver);
        PageFactory.initElements(appiumdriver, this);
    }

    public VNextBaseTypeSelectionList() {
    }

    public void selectType(String typeName) {
        SearchSteps.textSearch(typeName);
        WaitUtils.getGeneralFluentWait().until(driver -> {
            typeList.stream()
                    .filter((webElement) -> webElement.getText().split("\n")[0].contains(typeName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Type not found in list " + typeName))
                    .click();
            return true;
        });
    }

    public void clickSearchButton() {
        tap(searchbtn);
    }
}
