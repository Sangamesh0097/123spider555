package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoiceInfoPage {

	//intialization
	public InvoiceInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//span[@class='lvtHeaderText']") private WebElement createdinvoiceName;

	//utilization
	public WebElement getCreatedinvoiceName() {
		return createdinvoiceName;
	}

}
