package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v101.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityInfoPage {
	//intialization
	public OpportunityInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//span[@class='dvHeaderText']") private WebElement createdOpportunityName;

	//utilization
	public WebElement getCreatedOpportunityName() {
		return createdOpportunityName;
	}


}
