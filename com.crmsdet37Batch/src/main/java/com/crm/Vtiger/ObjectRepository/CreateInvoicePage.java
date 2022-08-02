package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class CreateInvoicePage extends WebDriverUtility {

	//intialization
	public CreateInvoicePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(name = "subject") private WebElement subjectNameEdt;

	@FindBy(xpath = "//tr[5]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectContactLink;

	@FindBy(name = "invoicedate") private WebElement calender;

	@FindBy(name="button") private WebElement saveButton;

	@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

	@FindBy(name="search") private WebElement searchNowButton;

	@FindBy(xpath = "//tr[8]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectOrganizationLink;

	@FindBy(name="bill_street") private WebElement billingAddressEdt;

	@FindBy(name="ship_street") private WebElement shippingAddressEdt;

	@FindBy(id="searchIcon1") private WebElement selectProductLink;

	@FindBy(name="qty1") private WebElement qtyEdt;

	@FindBy(id ="listPrice1") private WebElement priceEdt;

	//utilization
	public WebElement getSubjectNameEdt() {
		return subjectNameEdt;
	}

	public WebElement getSelectContactLink() {
		return selectContactLink;
	}

	public WebElement getCalender() {
		return calender;
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

	public WebElement getSelectOrganizationLink() {
		return selectOrganizationLink;
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

	public WebElement getPriceEdt() {
		return priceEdt;
	}
	
	public void createInvoiceDetails(String subject,String billingAddress,String shippingAddress,String date) {
		subjectNameEdt.sendKeys(subject);
		billingAddressEdt.sendKeys(billingAddress);
		shippingAddressEdt.sendKeys(shippingAddress);
		calender.clear();
		calender.sendKeys(date);
				
	}
	public void createInvoiceProductDetails(String qty,String amount) {
		
		priceEdt.clear();;
		priceEdt.sendKeys(amount);
		qtyEdt.sendKeys(qty);		
	}
	
	public void selectOrganization(WebDriver driver,String OrgName) {
		selectOrganizationLink.click();
		switchToWindow(driver, "Accounts&action");
		searchTF.sendKeys(OrgName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
		driver.switchTo().alert().accept();
		switchToWindow("Invoice&action", driver);
	}
	
	public void selectContact(WebDriver driver,String contactName) {
		selectContactLink.click();
		switchToWindow(driver, "Contacts&action");
		searchTF.sendKeys(contactName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='Sanjay kumar']")).click();
		driver.switchTo().alert().accept();
		switchToWindow("Invoice&action", driver);
	}
	
	public void selectProduct(WebDriver driver,String productName) {
		selectProductLink.click();
		switchToWindow(driver, "Products&action");
		searchTF.sendKeys(productName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
		switchToWindow("Invoice&action", driver);
	}

}
