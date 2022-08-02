package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.Vtiger.GenericUtility.WebDriverUtility;

public class ComposeMailPage extends WebDriverUtility {

	//intialization
	public ComposeMailPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(xpath = "//td[2]//input[@name='parent_name']") private WebElement ccMailTF;

	@FindBy(xpath = "//tr//td[2]//input[@name='bccmail']") private WebElement bccMailTF;

	@FindBy(xpath = "//tr//td[2]//input[@name='subject']") private WebElement subjectTF;

	@FindBy(name = "file_0") private WebElement fileUpLoad;

	@FindBy(name = "Send") private WebElement sendButton;

	@FindBy(name = "parent_type") private WebElement dropdown;

	@FindBy(xpath ="//img[@title='Select']") private WebElement selectLink;

	@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

	@FindBy(name="search") private WebElement searchNowButton;

	@FindBy(xpath ="//select[@name='search_field']") private WebElement emailDropdown;


	@FindBy(xpath = "//a[.='Compose']") private WebElement composeLink;

	//utilization
	public WebElement getComposeLink() {
		return composeLink;
	}

	public WebElement getFileUpLoad() {
		return fileUpLoad;
	}

	public WebElement getEmailDropdown() {
		return emailDropdown;
	}


	public WebElement getCcMailTF() {
		return ccMailTF;
	}

	public WebElement getBccMailTF() {
		return bccMailTF;
	}

	public WebElement getSubjectTF() {
		return subjectTF;
	}

	public WebElement getSendButton() {
		return sendButton;
	}

	public WebElement getDropdown() {
		return dropdown;
	}

	public WebElement getSelectLink() {
		return selectLink;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchNowButton() {
		return searchNowButton;
	}

	public void composeAMail(String bccMail,String ccMail,String subject,String filePath,WebDriver driver) {
		composeLink.click();
		switchToWindow("Emails&action=Emails", driver);
		bccMailTF.sendKeys(bccMail);
		ccMailTF.sendKeys(ccMail);
		subjectTF.sendKeys(subject);
		fileUpLoad.sendKeys(filePath);
		//switchToWindow("Emails&action=index", driver);
	}

	public void selectDropdown(String dropdownValue) {
		select(dropdown, dropdownValue);
	}

	public void selectMail(String emailID,String dropdownValue,WebDriver driver,String vendorName) {
		/*composeLink.click();
		switchToWindow("Emails&action=Emails", driver);*/
		selectLink.click();
		switchToWindow("Vendors&action", driver);
		searchTF.sendKeys(emailID);
		select(emailDropdown, dropdownValue);
		searchNowButton.click();
		driver.findElement(By.xpath("//a[.='"+vendorName+"']")).click();
		switchToWindow("Emails&action=Emails", driver);
	}

}
