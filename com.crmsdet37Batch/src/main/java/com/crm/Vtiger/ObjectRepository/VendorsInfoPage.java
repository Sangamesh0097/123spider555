package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsInfoPage {

	//intialization
	public VendorsInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//*[@class='dvHeaderText']") private WebElement createdVendorsName;

	//utilization	
	public WebElement getCreatedVendorsName() {
		return createdVendorsName;
	}


}
