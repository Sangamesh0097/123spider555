package com.crm.Vtiger.Contacts;

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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.crm.Vtiger.GenericUtility.DataBaseUtility;
import com.crm.Vtiger.GenericUtility.ExcelUtility;
import com.crm.Vtiger.GenericUtility.FileUtility;
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.ContactInfoPage;
import com.crm.Vtiger.ObjectRepository.ContactPage;
import com.crm.Vtiger.ObjectRepository.CreateContactPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContacsUsingNameKarthikTest {

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

		//Fetch the data from excel file(External file)
		String chromeBrowser=eLib.getDataFromExcel("Browsers", 1, 0);
		String firefoxBrowser=eLib.getDataFromExcel("Browsers", 2, 0);
		String msEdgeBrowser = eLib.getDataFromExcel("Browsers", 3, 0);
		String operaBrowser = eLib.getDataFromExcel("Browsers", 5, 0);
		String safariBrowser = eLib.getDataFromExcel("Browsers", 4, 0);
		String dropdownFirstNameoption = eLib.getDataFromExcel("FirstName dropdown", 1, 0);
		String firstName = eLib.getDataFromExcel("Vtiger", 2, 8);
		String lastName = eLib.getDataFromExcel("Vtiger", 2, 9);

		//Execute chrome Browser
		if (BROWSER.equalsIgnoreCase(chromeBrowser)) {
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();

		}
		//Execute Firefox Browser
		else if (BROWSER.equalsIgnoreCase(firefoxBrowser)) {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
		}
		//Execute msedge Browser
		else if (BROWSER.equalsIgnoreCase(msEdgeBrowser)) {
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
		}

		///Execute opera Browser
		else if (BROWSER.equalsIgnoreCase(operaBrowser)) {
			WebDriverManager.operadriver().setup();
			driver =new OperaDriver();
		}

		//Execute safari Browser
		else if (BROWSER.equalsIgnoreCase(safariBrowser)) {
			WebDriverManager.safaridriver().setup();
			driver =new SafariDriver();
		}

		else {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
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

		//click on contacts link
		HomePage homePage = new HomePage(driver);
		homePage.getContactLink().click();

		//click on create contacts link
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateContactName().click();


		//creating random number performance
		int random = jLib.getRandomNum();		

		//enter first name in first name, last name in text field,select dropdown 
		CreateContactPage createContactPage = new CreateContactPage(driver);	
		createContactPage.createContacts(firstName, lastName, dropdownFirstNameoption);;

		//click on save button
		createContactPage.getSaveButton().click();

		//Expected contact name in contact created page
		String expected=firstName;

		//Actual Contact name in Contact created page
		ContactInfoPage contactInfoPage = new ContactInfoPage(driver);
		String actual = contactInfoPage.getCreatedContactName().getText();

		//Verifing Contact created or not
//		if (actual.contains(expected)) {
//			System.out.println("Contact page is verified and Contact is created ");
//
//		}
//		else {
//			System.out.println("Contact page is verified and Contact is not created ");
//		}
		 SoftAssert asert = new SoftAssert();
		asert.assertTrue(actual.contains(expected));
		 Reporter.log("Contact page is verified and Contact is created ",true);

		//mouse on administrater and click on SignOut link
		homePage.logOut(driver);

		//terminate the section
		driver.quit();	

	}

}
