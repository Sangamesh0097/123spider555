package com.crm.Vtiger.Organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.crm.Vtiger.ObjectRepository.CreateOrganizationPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;
import com.crm.Vtiger.ObjectRepository.OrganizationInfoPage;
import com.crm.Vtiger.ObjectRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationNameQSpiderBasvangudiTest {

	public static void main(String[] args) throws Throwable {

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

		//enter username ,password and click on submit button
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);

		//Click on Organization link
		HomePage homePage = new HomePage(driver);
		homePage.getOrganizationLink().click();

		//Click on create organization link
		OrganizationPage orgPage = new OrganizationPage(driver);
		orgPage.getCreateOrganizationLink().click();

		//get random number
		int random = jLib.getRandomNum();

		//fetch data from excel file       
		String organizationNameRandom = eLib.getDataFromExcel("Vtiger", 2, 2)+random;
		String insurance = eLib.getDataFromExcel("Industry dropdown", 19, 0);
		String Customer = eLib.getDataFromExcel("Type Dropdown", 3, 0);

		//Enter organization name into organization name text field
		CreateOrganizationPage createOrganization = new CreateOrganizationPage(driver);
		createOrganization.getOrganizationName(organizationNameRandom);


		//select insurance option in dropdown
		createOrganization.selectIndustryDropDown(insurance);

		//select customer option in dropdown
		createOrganization.selectTypeDropDown(Customer);

		//click on save button
		createOrganization.getSaveButton().click();

		//Expected organization name in organization created page
		String expected=organizationNameRandom;

		//Actual organization name in organization created page
		OrganizationInfoPage organizationInfoPage = new OrganizationInfoPage(driver);
		String actual = organizationInfoPage.getCreatedOrganizationName().getText();

		//Verifing Organization created or not
		if (actual.contains(expected)) {
			System.out.println("Organization page is verified and Organization is created ");

		}
		else {
			System.out.println("Organization page is verified and Organization is not created ");
		}

		//mouse over on administration and click on save button
		homePage.logOut(driver);

		//terminate the session
		driver.quit();

	}

}
