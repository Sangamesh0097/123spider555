package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	//intialization
	public ProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//img[@title='Create Product...']") private WebElement productLink;

	//utilization
	public WebElement getProductLink() {
		return productLink;
	}

}
