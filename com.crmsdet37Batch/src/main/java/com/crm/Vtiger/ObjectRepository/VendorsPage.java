package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsPage {
	//intialization
	public VendorsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//img[@title='Create Vendor...']") private WebElement createVendorsLink;

	//utilization
	public WebElement getCreateVendorsLink() {
		return createVendorsLink;
	}


}
