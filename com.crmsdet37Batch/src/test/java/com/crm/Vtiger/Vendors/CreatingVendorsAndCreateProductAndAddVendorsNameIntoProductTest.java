package com.crm.Vtiger.Vendors;

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

import com.crm.Vtiger.GenericUtility.DataBaseUtility;
import com.crm.Vtiger.GenericUtility.ExcelUtility;
import com.crm.Vtiger.GenericUtility.FileUtility;
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.CreateProductPage;
import com.crm.Vtiger.ObjectRepository.CreateVendorsPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;
import com.crm.Vtiger.ObjectRepository.OrganizationInfoPage;
import com.crm.Vtiger.ObjectRepository.ProductInfoPage;
import com.crm.Vtiger.ObjectRepository.ProductPage;
import com.crm.Vtiger.ObjectRepository.VendorsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatingVendorsAndCreateProductAndAddVendorsNameIntoProductTest {

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

		//enter username ,password and click on submit button
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);

		//Click on click on more and  vendors link	
		HomePage homePage = new HomePage(driver);
		homePage.getMoreLink().click();
		homePage.getVendorsLink().click();

		//fetch data from excel file       
		String vendorsName = eLib.getDataFromExcel("Vtiger", 1, 6);
		String email = eLib.getDataFromExcel("Vtiger", 1, 27);
		String amount = eLib.getDataFromExcel("Vtiger", 1, 10);
		String productName = eLib.getDataFromExcel("Vtiger", 1, 4);


		//click on create vendors
		VendorsPage vendorsPage = new VendorsPage(driver);	
		vendorsPage.getCreateVendorsLink().click();


		//enter vendors name and emailId
		CreateVendorsPage createVendorsPage = new CreateVendorsPage(driver);
		createVendorsPage.createVendors(vendorsName, email);

		//click on save button
		createVendorsPage.getSaveButton().click();
		
		//click on product link
		homePage.getProductLinkHome().click();


		//click on create product
		ProductPage productPage = new ProductPage(driver);
		productPage.getProductLink().click();


		//enter product name
		CreateProductPage CreateProductPage = new CreateProductPage(driver);
		CreateProductPage.getProductNameEdt().sendKeys(productName);


		//select Vendors
		CreateProductPage.selectVendors(driver, vendorsName);

		//click on save button
		CreateProductPage.getSaveButton().click();
		
		//Expected product name in product created page
		String expected=productName;

		//Actual product name in product created page
		ProductInfoPage productInfoPage = new ProductInfoPage(driver);

		String actual = productInfoPage.getCreatedProductName().getText();

		//Verify product created or not
		if (actual.contains(expected)) {
			System.out.println("Product page is verified and Product is created ");

		}
		else {
			System.out.println("Product page is verified and Product is not created ");
		}


		//mouse over on administration and click on save button
		homePage.logOut(driver);

		//terminate the section
		driver.quit();
	}

}
