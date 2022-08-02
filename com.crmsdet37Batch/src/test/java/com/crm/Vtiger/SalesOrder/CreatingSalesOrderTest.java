package com.crm.Vtiger.SalesOrder;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.CreateSalesOrderPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;
import com.crm.Vtiger.ObjectRepository.SalesOrderInfoPage;
import com.crm.Vtiger.ObjectRepository.SalesOrderPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatingSalesOrderTest {

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

		//Execute chrome Browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();			
		}
		//Execute Firefox Browser
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
		}
		//Execute msedge Browser
		else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
		}

		///Execute opera Browser
		else if (BROWSER.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver =new OperaDriver();
		}

		//Execute safari Browser
		else if (BROWSER.equalsIgnoreCase("safari")) {
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

		//Enter username ,password and click on submit button
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);

		//Click on click on salesOrder link	
		HomePage homePage = new HomePage(driver);
		homePage.getMoreLink().click();
		//click on sales order
		homePage.getSalesOredrLink().click();

		//click on create sales order
		SalesOrderPage salesOrder = new SalesOrderPage(driver);
		salesOrder.getCreateSalesOrderLink().click();

		//get random number
		int randomNumber = jLib.getRandomNum();

		//Fetch the data from the excel Sheet
		String subject = eLib.getDataFromExcel("Vtiger", 2, 12)+randomNumber;
		String contactNo = eLib.getDataFromExcel("Vtiger", 2, 13);
		String quantity = eLib.getDataFromExcel("Vtiger", 1, 11);
		String amount = eLib.getDataFromExcel("Vtiger", 1, 10);
		String lastName = eLib.getDataFromExcel("Vtiger", 3, 9);
		String productName = eLib.getDataFromExcel("Vtiger", 1, 4);
		String billingAddress = eLib.getDataFromExcel("Vtiger", 1, 15);
		String shippingAddress = eLib.getDataFromExcel("Vtiger", 1, 16);
		String organizationName = eLib.getDataFromExcel("Vtiger", 2, 2);
		String opportunityName = eLib.getDataFromExcel("Vtiger", 2, 19);
		String date = eLib.getDataFromExcel("Vtiger", 1, 26);
		String statusDropdownValue = eLib.getDataFromExcel("Status Dropdown", 2, 0);




		//enter subject name
		CreateSalesOrderPage createSalesOrder = new CreateSalesOrderPage(driver);
		createSalesOrder.createSalesOrderInformation(subject, statusDropdownValue, date, contactNo);

		//select contact
		createSalesOrder.selectContacts(driver, lastName);

		//select opportunity 
		createSalesOrder.selectOpportunity(driver, opportunityName);

		//select organization
		createSalesOrder.selectOrganization(driver, organizationName);

		//enter billing and shipping address
		createSalesOrder.createSalesOrderAddressInfo(billingAddress, shippingAddress);

		//select product
		createSalesOrder.selectProduct(driver, productName);

		//enter amount and qty
		createSalesOrder.createSalesOrderItemInfo(amount, quantity);

		//click on save button
		createSalesOrder.getSaveButton().click();

		//expected result
		String expected=subject;

		//actual result
		SalesOrderInfoPage salesOrderInfoPage = new SalesOrderInfoPage(driver);
		String actual = salesOrderInfoPage.getCreatedSalesOrderName().getText();

		//verify sales order created or not
		if (actual.contains(expected)) {
			System.out.println("Sales order page is verified and Sales order is created");

		}
		else {
			System.out.println("Sales order page is verified and Sales order is not created");
		}

		//mouse over on administration and click on save button
		homePage.logOut(driver);


		//terminate the section
		driver.quit();		 


	}

}
