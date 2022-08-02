package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class AssetInfoPage   {
	//intialization
		public AssetInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(xpath = "//*[@class='dvHeaderText']") private WebElement createdAssetName;

		//utilization
		public WebElement getCreatedAssetName() {
			return createdAssetName;
		}

	    
}
