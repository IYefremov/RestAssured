package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.vnext.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class VNextApproveScreen extends VNextBaseScreen {
	
	@FindBy(xpath="//div[@data-automation-id='approve']")
	private WebElement approcescreen;
	
	@FindBy(id="approve-signature-canvas")
	private WebElement drawcanvas;
	
	@FindBy(xpath="//*[@action='clear']")
	private WebElement clearsignaturebtn;
	
	@FindBy(xpath="//*[@action='save']")
	private WebElement savebtn;

    @FindBy(xpath = "//div[contains(@class,'approve-total')]")
    private WebElement totalApprovePrice;

	public VNextApproveScreen(){
        PageFactory.initElements(appiumdriver, this);
	}
	
	public void clickSignatureCanvas() {
		tap(drawcanvas);
	}
	
	public void clickClearSignatureButton() {
		tap(clearsignaturebtn);
	}
	
	public void drawSignature() {
		clickSignatureCanvas();
	}
	
	public void clickSaveButton() {
		tap(savebtn);
	}

	
	public boolean isClearButtonVisible() {
		return clearsignaturebtn.isDisplayed();
	}

	public String getApprovePriceValue() {
        WaitUtils.elementShouldBeVisible(totalApprovePrice, true);
        return totalApprovePrice.getText();
	}


}
