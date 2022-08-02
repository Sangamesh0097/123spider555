package com.crm.Vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	//intialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaretion
	@FindBy(name = "user_name")  private WebElement userNameEdt;
	@FindBy(name="user_password") private WebElement passwordEdt;
	@FindBy(id="submitButton") private WebElement submitButton;
	
	//utilization
	public WebElement getUserNameEdt() {
		return userNameEdt;
	}
	public WebElement getPasswordEdt() {
		return passwordEdt;
	}
	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	public void loginToApp(String userName,String password) {
		userNameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		submitButton.click();
	}

}
