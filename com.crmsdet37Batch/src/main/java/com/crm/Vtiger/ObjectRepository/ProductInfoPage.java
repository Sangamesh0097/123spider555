package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
	//intialization
	public ProductInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//span[@class='lvtHeaderText']") private WebElement createdProductName;

	//utilization
	public WebElement getCreatedProductName() {
		return createdProductName;
	}


}
