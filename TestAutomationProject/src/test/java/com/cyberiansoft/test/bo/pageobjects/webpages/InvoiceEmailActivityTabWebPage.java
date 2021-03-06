package com.cyberiansoft.test.bo.pageobjects.webpages;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import com.cyberiansoft.test.bo.webelements.WebTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.cyberiansoft.test.bo.utils.WebElementsBot.click;

public class InvoiceEmailActivityTabWebPage extends BaseWebPage {
	
	@FindBy(id = "ctl00_Content_gv_ctl00")
	private WebTable mailactivitytable;
	
	public InvoiceEmailActivityTabWebPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);	
	}
	
	public List<WebElement> getInvoiceEmailActivityTableRows() {
		click(wait.until(ExpectedConditions.elementToBeClickable(mailactivitytable.getWrappedElement())));
		return mailactivitytable.getTableRows();
	}
	
	public String getFirstRowSentTimeValue() {
		waitABit(4000);
		return getInvoiceEmailActivityTableRows().get(0).findElement(By.xpath("./td[1]")).getText();
	}
	
	public String getFirstRowRecipientsValue() {
		return getInvoiceEmailActivityTableRows().get(0).findElement(By.xpath("./td[2]")).getText();
	}
	
	public String getFirstRowSendCheckboxValue() {
		return getInvoiceEmailActivityTableRows().get(0).findElement(By.xpath("./td[3]/span/input")).getAttribute("checked");
	}

}
