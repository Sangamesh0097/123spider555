package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesOrderPage {
	//intialization
	public SalesOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//img[@title='Create Sales Order...']") private WebElement createSalesOrderLink;

	//utilization
	public WebElement getCreateSalesOrderLink() {
		return createSalesOrderLink;
	}


}
