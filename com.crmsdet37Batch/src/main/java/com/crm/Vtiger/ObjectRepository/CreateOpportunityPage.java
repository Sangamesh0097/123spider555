package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class CreateOpportunityPage extends WebDriverUtility{
	//intialization
	public CreateOpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(name = "potentialname") private WebElement opportunityNameEdt;

	@FindBy(name="related_to_type") private WebElement reletedToDropdown;

	@FindBy(xpath="//tr[4]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectOption;

	@FindBy(name="button") private WebElement saveButton;

	@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

	@FindBy(name="search") private WebElement searchNowButton;

	@FindBy(name="amount") private WebElement amountTF;

	@FindBy(xpath="//input[@name='closingdate']") private WebElement calenderTF;

	@FindBy(xpath="//tr[8]//img[@src='themes/softed/images/select.gif']") private WebElement selectCampaignSource;

	//utilization
	public WebElement getOpportunityNameEdt() {
		return opportunityNameEdt;
	}

	public WebElement getReletedToDropdown() {
		return reletedToDropdown;
	}

	public WebElement getSelectOption() {
		return selectOption;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchNowButton() {
		return searchNowButton;
	}

	public WebElement getAmountTF() {
		return amountTF;
	}

	public WebElement getCalenderTF() {
		return calenderTF;
	}

	public WebElement getSelectCampaignSource() {
		return selectCampaignSource;
	}

	public void createOpportunity(String oppurtunityName,String amount,String date,String relatedDropDownValue) {
		opportunityNameEdt.sendKeys(oppurtunityName);
		amountTF.sendKeys(amount);
		calenderTF.clear();
		calenderTF.sendKeys(date);
		select(reletedToDropdown, relatedDropDownValue);
	}

	public void selectContacts(WebDriver driver,String contactsName) {
		selectOption.click();
		switchToWindow("Contacts&action", driver);
		searchTF.sendKeys(contactsName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='Sanjay kumar']")).click();
		switchToWindow("Potentials&action", driver);
	}

	public void selectOrganization(WebDriver driver,String OrgName) {
		selectOption.click();
		switchToWindow(driver, "Accounts&action");
		searchTF.sendKeys(OrgName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
		switchToWindow("Potentials&action", driver);
	}

	public void selectCampaignSource(WebDriver driver,String CampaignName) {
		selectCampaignSource.click();
		switchToWindow(driver, "Campaigns&action");
		searchTF.sendKeys(CampaignName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+CampaignName+"']")).click();
		switchToWindow("Potentials&action", driver);
	}

}
