package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateVendorsPage {
	
	//intialization
		public CreateVendorsPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(name="vendorname") private WebElement vendorsNameEdt;
		
		@FindBy(name="email") private WebElement emailEdt;

		@FindBy(name="button") private WebElement saveButton;

		//utilization
		public WebElement getVendorsNameEdt() {
			return vendorsNameEdt;
		}

		public WebElement getEmailEdt() {
			return emailEdt;
		}

		public WebElement getSaveButton() {
			return saveButton;
		}
		
		public void createVendors(String vendorsName,String emailId) {
			vendorsNameEdt.sendKeys(vendorsName);
			emailEdt.sendKeys(emailId);
		}


}
