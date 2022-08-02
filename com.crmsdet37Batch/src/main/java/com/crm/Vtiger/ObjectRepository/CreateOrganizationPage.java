package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility {
	//intialization
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(name = "accountname") private WebElement organizationNameEdt ;

	@FindBy(name = "industry") private WebElement industryDropdown ;

	@FindBy(name = "accounttype") private WebElement typeDropdown ;

	@FindBy(name = "button") private WebElement saveButton ;

	//utilization
	public WebElement getOrganizationNameEdt() {
		return organizationNameEdt;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public void getOrganizationName(String organizationName) {
		organizationNameEdt.sendKeys(organizationName);
	}

	public void selectTypeDropDown(String typeDropdownValue) {
		select(typeDropdown, typeDropdownValue);
	}
	
	public void selectIndustryDropDown(String dropdownValue) {
		select(industryDropdown, dropdownValue);
	}
	


}
