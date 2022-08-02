package com.crm.Vtiger.Opportunities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.Vtiger.GenericUtility.DataBaseUtility;
import com.crm.Vtiger.GenericUtility.ExcelUtility;
import com.crm.Vtiger.GenericUtility.FileUtility;
import com.crm.Vtiger.GenericUtility.IConstants;
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.CreateOpportunityPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;
import com.crm.Vtiger.ObjectRepository.OpportunityInfoPage;
import com.crm.Vtiger.ObjectRepository.OpportunityPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatingOpportunitiesTest {
	public static void main(String[] args) throws Throwable {
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

		//create explicitly wait for 10sec
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));

		//Enter url of application 
		driver.get(URL);

		//Enter user name,password and click on submit button
		LoginPage login = new LoginPage(driver);
		login.loginToApp(USERNAME, PASSWORD);

		//click on Opportunities
		HomePage homePage = new HomePage(driver);
		homePage.getOpportunityLink().click();

		//click on Create Opportunities
		OpportunityPage opportunityPage = new OpportunityPage(driver);
		opportunityPage.getCreateOpportunityLink().click();

		//fetch data from excel file       
		String opportunityName = eLib.getDataFromExcel("Vtiger", 2, 19);
		String amount = eLib.getDataFromExcel("Vtiger", 1, 10);
		String lastName = eLib.getDataFromExcel("Vtiger", 3, 9);
		String compignName = eLib.getDataFromExcel("Vtiger", 3, 5);
		String opportunitydate = eLib.getDataFromExcel("Vtiger", 1, 26);

		//enter opportunityName,amount,opportunitydate
		CreateOpportunityPage createOpportunityPage = new CreateOpportunityPage(driver);
		createOpportunityPage.createOpportunity(opportunityName, amount, opportunitydate, "Contacts");;

		//select contact
		createOpportunityPage.selectContacts(driver, lastName);

		//select compignSource
		createOpportunityPage.selectCampaignSource(driver, compignName);
		
		//click on save button
		createOpportunityPage.getSaveButton().click();

		//expected data
		String expected=opportunityName;

		//actual data
		OpportunityInfoPage opportunityInfoPage = new OpportunityInfoPage(driver);
		String actual = opportunityInfoPage.getCreatedOpportunityName().getText();

		//verify opportunity creted or not
		if (actual.contains(expected)) {
			System.out.println("opportunity page is verified and opportunity is created");

		}
		else {
			System.out.println("opportunity page is verified and opportunity is not created");
		}

		//mouse on administrater and click on SignOut link
		homePage.logOut(driver);

		//terminate session
		driver.quit();
	}

}
