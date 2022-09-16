package com.crm.Vtiger.Invoice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.crm.Vtiger.GenericUtility.DataBaseUtility;
import com.crm.Vtiger.GenericUtility.ExcelUtility;
import com.crm.Vtiger.GenericUtility.FileUtility;
import com.crm.Vtiger.GenericUtility.IConstants;
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.CreateInvoicePage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.InvoiceInfoPage;
import com.crm.Vtiger.ObjectRepository.InvoicePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateInvoice {

	
	@Test(groups="smoketesting")
	
	public  void main() throws Throwable {
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
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
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

		//Click on click on invoice link	
		HomePage homePage = new HomePage(driver);
		homePage.getMoreLink().click();
		homePage.getInvoiceLink().click();


		//click on create invoice
		InvoicePage invoicePage = new InvoicePage(driver);
		invoicePage.getCreateInvoiceLink().click();

		//get random number
		int random = jLib.getRandomNum();

		//fetch data from excel file       
		String subject = eLib.getDataFromExcel("Vtiger", 2, 12);
		String quantity = eLib.getDataFromExcel("Vtiger", 1, 11);
		String amount = eLib.getDataFromExcel("Vtiger", 1, 10);
		String lastName = eLib.getDataFromExcel("Vtiger", 3, 9);
		String productName = eLib.getDataFromExcel("Vtiger", 1, 4);
		String billingAddress = eLib.getDataFromExcel("Vtiger", 1, 15);
		String shippingAddress = eLib.getDataFromExcel("Vtiger", 1, 16);
		String organizationName = eLib.getDataFromExcel("Vtiger", 1, 2);
		String invoiceDate = eLib.getDataFromExcel("Vtiger", 2, 26);


		/*//enter subject name
		driver.findElement(By.name("subject")).sendKeys(subject);*/

		//select contact
		CreateInvoicePage createInvoicePage = new CreateInvoicePage(driver);
		createInvoicePage.selectContact(driver, lastName);
		
		//select organization
		createInvoicePage.selectOrganization(driver, organizationName);

		//enter  subject ,billing address and shipping address,date also
		createInvoicePage.createInvoiceDetails(subject, billingAddress, shippingAddress, invoiceDate);
	
		//select product
		createInvoicePage.selectProduct(driver, productName);
		
		//enter qty and amount
		createInvoicePage.createInvoiceProductDetails(quantity, amount);

		//click on save button
		createInvoicePage.getSaveButton().click();
		
		//expected result
		String expected=subject;

		//actual result
		InvoiceInfoPage invoiceInfoPage = new InvoiceInfoPage(driver);
		String actual = invoiceInfoPage.getCreatedinvoiceName().getText();


		//verify Invoice created or not
		if (actual.contains(expected)) {
			System.out.println("Invoice page is verified and Invoice is created");

		}
		else {
			System.out.println("Invoice page is verified and Invoice is not created");
		}

		//mouse over on administration and click on save button
		homePage.logOut(driver);


		//terminate the section
		driver.quit();		 


	}

}
