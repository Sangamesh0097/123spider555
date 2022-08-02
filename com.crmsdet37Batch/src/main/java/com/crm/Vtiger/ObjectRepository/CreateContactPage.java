package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility{

	//intialization
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactName;

	@FindBy(name="salutationtype") private WebElement firstNameDropdown;

	@FindBy(xpath = "//input[@name='account_name']/..//img[@title='Select']") private WebElement organizationLink;

	@FindBy(name="firstname") private WebElement firstNameEdt;

	@FindBy(name="lastname") private WebElement lastNameEdt;

	@FindBy(name="button") private WebElement saveButton;

	@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

	@FindBy(name="search") private WebElement searchNowButton;
	//utilization
	public WebElement getCreateContactName() {
		return createContactName;
	}

	public WebElement getFirstNameDropdown() {
		return firstNameDropdown;
	}

	public WebElement getFirstNameEdt() {
		return firstNameEdt;
	}

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchNowButton() {
		return searchNowButton;
	}

	public void createContacts(String firstName,String lastName,String firstNameDropDown) {
		select(firstNameDropdown, firstNameDropDown);
		firstNameEdt.sendKeys(firstName);
		lastNameEdt.sendKeys(lastName);
	}
	public void selectOrganization(WebDriver driver,String OrgName) {
		organizationLink.click();
		switchToWindow(driver, "Accounts&action");
		searchTF.sendKeys(OrgName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
		switchToWindow("Contacts&action", driver);
	}

}
