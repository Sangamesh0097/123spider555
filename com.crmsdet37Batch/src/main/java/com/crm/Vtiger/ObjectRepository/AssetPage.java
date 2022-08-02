package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssetPage {

	//intialization
	public AssetPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//img[@title='Create Asset...']") private WebElement createAssetName;

	//utilization
	public WebElement getCreateAssetName() {
		return createAssetName;
	}



}
