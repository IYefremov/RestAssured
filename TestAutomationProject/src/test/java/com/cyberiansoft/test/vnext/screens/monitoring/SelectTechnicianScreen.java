package com.cyberiansoft.test.vnext.screens.monitoring;

import com.cyberiansoft.test.vnext.webelements.customers.ListElement;
import com.cyberiansoft.test.vnext.webelements.decoration.FiledDecorator;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class SelectTechnicianScreen extends MonitorScreen {

    @FindBy(xpath = "//div[@class='pages']/div[contains(@data-page,'tech')]")
    private WebElement rootElement;

    @FindBy(xpath = "//div[contains(@data-autotests-id,'tech')]/div")
    private List<ListElement> techniciansRecordsList;

    public SelectTechnicianScreen() {
        PageFactory.initElements(new FiledDecorator(webDriver), this);
    }
}
