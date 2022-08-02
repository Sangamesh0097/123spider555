package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class CreateSalesOrderPage extends WebDriverUtility {

	//intialization
	public CreateSalesOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(name = "subject") private WebElement subjectEdt;

	@FindBy(name="customerno") private WebElement contactNoEdt;

	@FindBy(xpath = "//tr[6]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectContactLink;

	@FindBy(name = "sostatus") private WebElement statusDropdown;

	@FindBy(name="duedate") private WebElement calenderEdt;

	@FindBy(xpath = "//tr[9]//td[4]//img[@src='themes/softed/images/select.gif']") private WebElement selectOrganizationLink;

	@FindBy(name="button") private WebElement saveButton;

	@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

	@FindBy(name="search") private WebElement searchNowButton;

	@FindBy(name="bill_street") private WebElement billingAddressEdt;

	@FindBy(name="ship_street") private WebElement shippingAddressEdt;

	@FindBy(id ="searchIcon1") private WebElement selectProductLink;

	@FindBy(id="qty1") private WebElement qtyEdt;

	@FindBy(id="listPrice1") private WebElement amountEdt;

	@FindBy(xpath ="//tr[3]//td[4]//img[@src='themes/softed/images/select.gif']") private WebElement selectOpportunityLink;

	//utilization
	public WebElement getSelectOpportunityLink() {
		return selectOpportunityLink;
	}


	public WebElement getSubjectEdt() {
		return subjectEdt;
	}

	public WebElement getContactNoEdt() {
		return contactNoEdt;
	}

	public WebElement getSelectContactLink() {
		return selectContactLink;
	}

	public WebElement getStatusDropdown() {
		return statusDropdown;
	}

	public WebElement getCalenderEdt() {
		return calenderEdt;
	}

	public WebElement getSelectOrganizationLink() {
		return selectOrganizationLink;
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

	public WebElement getBillingAddressEdt() {
		return billingAddressEdt;
	}

	public WebElement getShippingAddressEdt() {
		return shippingAddressEdt;
	}

	public WebElement getSelectProductLink() {
		return selectProductLink;
	}

	public WebElement getQtyEdt() {
		return qtyEdt;
	}

	public WebElement getAmountEdt() {
		return amountEdt;
	}

	public void createSalesOrderInformation(String subject,String dropDownvalue,String date,String contactNo) {

		subjectEdt.sendKeys(subject);
		calenderEdt.clear();
		calenderEdt.sendKeys(date);
		contactNoEdt.sendKeys(contactNo);
		select(statusDropdown, dropDownvalue);	
	}

	public void createSalesOrderAddressInfo(String billingAddress,String shippingAddress) {
		billingAddressEdt.sendKeys(billingAddress);
		shippingAddressEdt.sendKeys(shippingAddress);
	}
	public void createSalesOrderItemInfo(String amount, String qty) {
		amountEdt.clear();
		amountEdt.sendKeys(amount);
		qtyEdt.sendKeys(qty);	
	}


	public void selectOrganization(WebDriver driver,String OrgName) {
		selectOrganizationLink.click();
		switchToWindow(driver, "Accounts&action");
		searchTF.sendKeys(OrgName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
		driver.switchTo().alert().accept();
		switchToWindow("SalesOrder&action", driver);
	}

	public void selectContacts(WebDriver driver,String contactLastName) {
		selectContactLink.click();
		switchToWindow(driver, "Contacts&action");
		searchTF.sendKeys(contactLastName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='Sanjay kumar']")).click();
		driver.switchTo().alert().accept();
		switchToWindow("SalesOrder&action", driver);
	}

	public void selectProduct(WebDriver driver,String productName) {
		selectProductLink.click();
		switchToWindow(driver, "Products&action");
		searchTF.sendKeys(productName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
	//	driver.switchTo().alert().accept();
		switchToWindow("SalesOrder&action", driver);
	}

	public void selectOpportunity(WebDriver driver,String opportunityName) {
		selectOpportunityLink.click();
		switchToWindow(driver, "Potentials&action");
		searchTF.sendKeys(opportunityName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+opportunityName+"']")).click();
		switchToWindow("SalesOrder&action", driver);
	}


	public void statusDropdown(String dropdownValue) {
		select(statusDropdown, dropdownValue);
	}

}
