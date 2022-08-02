package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesOrderInfoPage {
	//intialization
	public SalesOrderInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//*[@class='lvtHeaderText']") private WebElement createdSalesOrderName;

	//utilization
	public WebElement getCreatedSalesOrderName() {
		return createdSalesOrderName;
	}


}
