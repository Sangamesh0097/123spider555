package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	
	//intialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//a[.='Organizations']") private WebElement organizationLink;
	
	@FindBy(xpath = "//td[8]//a[.='Contacts']") private WebElement contactLink;
	
	@FindBy(xpath="//td[14]//a[.='Documents']") private WebElement documentsLink;
	
	@FindBy(xpath = "//a[.='Email']") private WebElement emailLink;
	 
	@FindBy (xpath ="//a[.='More']") private WebElement moreLink;
	
	@FindBy (xpath ="//a[.='Sign Out']") private WebElement signOutLink;
	
	@FindBy (name ="Sales Order") private WebElement salesOredrLink;
	
	@FindBy (name ="Assets") private WebElement assetLink;
	
	@FindBy (name ="Campaigns") private WebElement campaignsLink;
	
	@FindBy (name ="Invoice") private WebElement invoiceLink;
	
	@FindBy (name ="Vendors") private WebElement vendorsLink;
	
	
	

	@FindBy (xpath ="//td[12]//a[.='Products']") private WebElement productLinkHome;
	
	@FindBy(xpath="//td[10]//a[.='Opportunities']") private WebElement opportunityLink;
	
	//utilization
	
	public WebElement getProductLinkHome() {
		return productLinkHome;
	}
	public WebElement getOpportunityLink() {
		return opportunityLink;
	}

	public WebElement getSalesOredrLink() {
		return salesOredrLink;
	}

	public WebElement getAssetLink() {
		return assetLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getInvoiceLink() {
		return invoiceLink;
	}

	public WebElement getVendorsLink() {
		return vendorsLink;
	}

	public WebElement getAdministraterLink() {
		return administraterLink;
	}

	@FindBy (xpath="//td[contains(@onmouseover,'admin123@gmail.com')]") private WebElement administraterLink;
	
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getDocumentsLink() {
		return documentsLink;
	}

	public WebElement getEmailLink() {
		return emailLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	//mouse over on more
	public void mouseOverOnMore(WebDriver driver) {
		mouseOverOnElement(driver, moreLink);
	}

	//logout
	public void logOut(WebDriver driver) {
		mouseOverOnElement(driver, administraterLink);
		signOutLink.click();
	}
	
	
}
