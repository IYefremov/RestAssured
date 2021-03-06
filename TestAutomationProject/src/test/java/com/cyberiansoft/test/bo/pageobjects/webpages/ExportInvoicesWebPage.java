package com.cyberiansoft.test.bo.pageobjects.webpages;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExportInvoicesWebPage extends BaseWebPage {
	
	@FindBy(id = "ctl00_Main_report_ctl05_ctl04_ctl00_ButtonLink")
	WebElement exportButton;
	
	@FindBy(id = "ctl00_Main_report_ctl05_ctl00_Next_ctl00_ctl00")
	WebElement nextPageButton;

	public ExportInvoicesWebPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
	}

	public String countInvoicesToExport() {
		return driver.findElement(By.id("ctl00_Main_report_ctl05_ctl00_TotalPages")).getText();
	}

	public boolean allInvoicesAreAbleToExport() {
		waitABit(20000);
		int invoicesPages = Integer.parseInt(driver.findElement(By.id("ctl00_Main_report_ctl05_ctl00_TotalPages")).getText());
		for(int i =0; i<invoicesPages; i++){
			exportButton.click();
			waitABit(10000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Word')]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Excel')]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'PDF')]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'PowerPoint')]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'TIFF file')]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'CSV (comma delimited)')]")));

			if(i +1 == invoicesPages)
				break;
			
			nextPageButton.click();
			waitForLoading();
		}
		return true;
	}
}
