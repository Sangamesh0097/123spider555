package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	//intialization
	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactName;

	//utilization
	public WebElement getCreateContactName() {
		return createContactName;
	}


}
