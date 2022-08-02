package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	//intialization
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//img[@title='Create Organization...']") private WebElement createOrganizationLink;

	//utilization
	public WebElement getCreateOrganizationLink() {
		return createOrganizationLink;
	}

}
