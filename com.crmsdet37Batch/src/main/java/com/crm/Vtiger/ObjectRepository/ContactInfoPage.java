package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	//intialization
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//*[@class='dvHeaderText']") private WebElement createdContactName;

	//utilization
	public WebElement getCreatedContactName() {
		return createdContactName;
	}


}
