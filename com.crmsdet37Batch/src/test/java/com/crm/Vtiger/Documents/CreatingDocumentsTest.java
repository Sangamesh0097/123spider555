package com.crm.Vtiger.Documents;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.Vtiger.GenericUtility.DataBaseUtility;
import com.crm.Vtiger.GenericUtility.ExcelUtility;
import com.crm.Vtiger.GenericUtility.FileUtility;
import com.crm.Vtiger.GenericUtility.IConstants;
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.CreateDocumentPage;
import com.crm.Vtiger.ObjectRepository.DocumentInfoPage;
import com.crm.Vtiger.ObjectRepository.DocumentPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;
import com.crm.Vtiger.ObjectRepository.OrganizationInfoPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatingDocumentsTest {
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		WebDriver driver=null;FileUtility fLib = new FileUtility();
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

		// click on document link	
		HomePage homePage = new HomePage(driver);
		homePage.getDocumentsLink().click();

		//click on create document
		DocumentPage document = new DocumentPage(driver);
		document.getCreateDocument().click();

		//get random numbers
		int random = jLib.getRandomNum();

		//Fetch title name from Excel		
		String title = eLib.getDataFromExcel("Vtiger", 1, 19)+random;

		//enter the title ,send document path and enter something in frame
		CreateDocumentPage createDocumentPage = new CreateDocumentPage(driver);
		createDocumentPage.createDocument(title, "C:\\Users\\hi\\Downloads\\123.docx", "Document is created");

		//click on save button
		createDocumentPage.getSaveButton().click();

		//Expected organization name in organization created page
		String expected=title;

		//Actual Document name in Document created page
		DocumentInfoPage documentInfoPage = new DocumentInfoPage(driver);
		String actual = documentInfoPage.getCreatedDocumentTitle().getText();

		//Verifing Document created or not
		if (actual.contains(expected)) {
			System.out.println("Document page is verified and Document is created ");

		}
		else {
			System.out.println("Document page is verified and Document is not created ");
		}
		//mouse over on administration and click on save button
		homePage.logOut(driver);


		//terminate the section
		driver.quit();		 

	}
}
