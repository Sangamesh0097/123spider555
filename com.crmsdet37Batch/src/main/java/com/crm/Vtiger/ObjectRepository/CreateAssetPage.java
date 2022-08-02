package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class CreateAssetPage extends WebDriverUtility {
	//intialization
	public CreateAssetPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//tr[4]//td[2]//input[@name='serialnumber']") private WebElement serialNumberEdt;

	@FindBy(name="datesold") private WebElement soldDateCalender;

	@FindBy(name="dateinservice") private WebElement serviceDateCalender;

	@FindBy(xpath="//tr[7]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectInvoiceLink;

	@FindBy(xpath="//tr[3]//td[4]//img[@src='themes/softed/images/select.gif']") private WebElement selectProductLink;

	@FindBy(xpath="//select[@name='search_field']") private WebElement searchDropDown;

	@FindBy(xpath="//tr[9]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectOrganizationLink;

	@FindBy(name="assetname") private WebElement assetNameEdt;

	@FindBy(name="button") private WebElement saveButton;

	@FindBy(id="search_txt") private WebElement searchTF;

	@FindBy(name="search") private WebElement searchNowButton;


	//utilization
	public WebElement getSerialNumberEdt() {
		return serialNumberEdt;
	}

	public WebElement getSoldDateCalender() {
		return soldDateCalender;
	}

	public WebElement getServiceDateCalender() {
		return serviceDateCalender;
	}

	public WebElement getSelectInvoiceLink() {
		return selectInvoiceLink;
	}

	public WebElement getSelectProductLink() {
		return selectProductLink;
	}

	public WebElement getSearchDropDown() {
		return searchDropDown;
	}

	public WebElement getSelectOrganizationLink() {
		return selectOrganizationLink;
	}

	public WebElement getAssetNameEdt() {
		return assetNameEdt;
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

	public void createAsset(String serialNumber,String assetName,String soldDate,String serviceDate) {
		serialNumberEdt.sendKeys(serialNumber);
		soldDateCalender.clear();
		soldDateCalender.sendKeys(soldDate);
		serviceDateCalender.clear();
		serviceDateCalender.sendKeys(serviceDate);
		assetNameEdt.sendKeys(assetName);		
	}

	public void selectOrganization(WebDriver driver,String OrgName) {
		selectOrganizationLink.click();
		switchToWindow(driver, "Accounts&action");
		searchTF.sendKeys(OrgName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
		switchToWindow("Assets&action", driver);
	}


	public void selectProduct(WebDriver driver,String productName) {
		selectProductLink.click();
		switchToWindow(driver, "Products&action");
		searchTF.sendKeys(productName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
		switchToWindow("Assets&action", driver);
	}

	public void selectInvoice(WebDriver driver,String invoiceName,String dropdownValue) {
		selectInvoiceLink.click();
		switchToWindow(driver, "Invoice&action");
		searchTF.sendKeys(invoiceName);	
		select(dropdownValue, searchDropDown);
		//select(searchDropDown, dropdownValue);			
		searchNowButton.click();
		driver.findElement(By.xpath("//a[contains(.,'"+invoiceName+"')]")).click();
		switchToWindow("Assets&action", driver);
	}
}
