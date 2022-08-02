package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	//intialization
	public OrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//span[@class='dvHeaderText']") private WebElement createdOrganizationName;

	//utilization
	public WebElement getCreatedOrganizationName() {
		return createdOrganizationName;
	}


}
