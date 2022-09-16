package com.crm.Vtiger.Compaigns;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.Vtiger.GenericUtility.BaseClass;
import com.crm.Vtiger.GenericUtility.DataBaseUtility;
import com.crm.Vtiger.GenericUtility.ExcelUtility;
import com.crm.Vtiger.GenericUtility.FileUtility;
import com.crm.Vtiger.GenericUtility.IConstants;
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(com.crm.Vtiger.GenericUtility.ItestListenerImtn.class)


public class CreatingCompaignsAddProductIntoCompaignstTest extends BaseClass {

	
	@Test(groups = "smokeTesting")
	public static void test() throws Throwable {
		// TODO Auto-generated method stub

		WebDriver driver=null;

		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		ExcelUtility eLib = new ExcelUtility();
		DataBaseUtility dLib = new DataBaseUtility();

		//Fetch the data from properties file(External file)
		String URL = fLib.getPropertyKeyValue("url");
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");

		//Execute chrome browser
		if (BROWSER.equalsIgnoreCase(IConstants.browserNameForChrome)) {
			driver=WebDriverManager.chromedriver().create();
		}

		//Execute firefox browser
		else if (BROWSER.equalsIgnoreCase(IConstants.browserNameForFirefox)) {
			driver=WebDriverManager.firefoxdriver().create();
		}

		//Execute MSEdge browser
		else if (BROWSER.equalsIgnoreCase(IConstants.browserNameForEdge)) {
			driver=WebDriverManager.edgedriver().create();
		}

		//Execute chrome browser
		else {
			driver=WebDriverManager.chromedriver().create();
		}

		//maximize the window
		wLib.maximizeTheBrowser(driver);

		//create implicitly wait for 10sec
		wLib.waitTillPageGetsLoad(driver);

		
		//Enter url of application 
		driver.get(URL);

		//Enter user name,password and click on submit button
				LoginPage login = new LoginPage(driver);
				login.loginToApp(USERNAME, PASSWORD);

		//fetch data from excel
		String campaignName=eLib.getDataFromExcel("Vtiger", 1, 5);
		String productName=eLib.getDataFromExcel("Vtiger", 1, 4);

		//create actions object for mouse operating perpose
		wLib.mouseOverOnElement(driver, driver.findElement(By.xpath("//a[.='More']")));

		//mouse overing on more option
		HomePage homePage = new HomePage(driver);
		homePage.mouseOverOnMore(driver);
		

		//click on Campaigns
		homePage.getCampaignsLink().click();

		
		//click on create Campaigns
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();

		//enter Campaignsname
		driver.findElement(By.name("campaignname")).sendKeys(campaignName);

		driver.findElement(By.xpath("//img[@alt=\"Select\"]")).click();

		//get parent window id
		String parentwindow = driver.getWindowHandle();

		//handled diffrent windows		
		wLib.switchToWindow("Products&action", driver);
		
	   //enter product name in search textfield
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(productName);
		
		//click on search
		driver.findElement(By.name("search")).click();
		
		//click on laptop
		driver.findElement(By.xpath("//a[.='Laptop']")).click();
		
		
		//switch back to parent window
		driver.switchTo().window(parentwindow);

		//click on save button
		driver.findElement(By.name("button")).click();
		
		System.out.println("Compaigns is created and Product is added to compaigns");
		driver.findElement(By.xpath("//td[contains(@onmouseover,'admin123@gmail.com')]")).click();
		
		//SignOut the vtiger
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		//terminate the section
		driver.quit();
	}

}
