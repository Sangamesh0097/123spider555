package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage {

	//intialization
	public InvoicePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//declaretion
	@FindBy(xpath = "//img[@title='Create Invoice...']") private WebElement createInvoiceLink;
	

	//utilization
	public WebElement getCreateInvoiceLink() {
		return createInvoiceLink;
	}
}
