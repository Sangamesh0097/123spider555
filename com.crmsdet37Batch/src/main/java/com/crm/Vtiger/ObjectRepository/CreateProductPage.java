package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class CreateProductPage extends WebDriverUtility{
	//intialization
	public CreateProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(name = "productname") private WebElement productNameEdt;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']") private WebElement selectVendorsLink;
	
	@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

	@FindBy(name="search") private WebElement searchNowButton;
	
	@FindBy(name="button") private WebElement saveButton;


	//utilization
	public WebElement getProductNameEdt() {
		return productNameEdt;
	}

	public WebElement getSelectVendorsLink() {
		return selectVendorsLink;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchNowButton() {
		return searchNowButton;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public void selectVendors(WebDriver driver,String vendorsName) {
		selectVendorsLink.click();
		switchToWindow(driver, "Vendors&action");
		searchTF.sendKeys(vendorsName);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+vendorsName+"']")).click();
		switchToWindow("Products&action", driver);
	}


}
