package com.crm.Vtiger.Email;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.crm.Vtiger.ObjectRepository.ComposeMailPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ComposeAMailAndSendTest {
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

		//creating random number perpuse   
		int random = jLib.getRandomNum();

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

		//Fetch First name from Excel		
		String bccMailId = eLib.getDataFromExcel("Vtiger", 1, 17);
		String ccMailId = eLib.getDataFromExcel("Vtiger", 2, 17);
		String subject = eLib.getDataFromExcel("Vtiger", 2, 12)+random;
		String title = eLib.getDataFromExcel("Vtiger", 1, 19)+random;
		String mailId = eLib.getDataFromExcel("Vtiger", 1, 27);
		String vendorsName = eLib.getDataFromExcel("Vtiger", 1, 6);

		//enter username ,password and click on submit button
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);

		//Click on click on email link	
		HomePage homePage = new HomePage(driver);
		homePage.getEmailLink().click();

		//click on compose
		ComposeMailPage composeMailPage = new ComposeMailPage(driver);
		composeMailPage.getComposeLink().click();

		//Enter bccmailId,ccmailId,subject and pass file path
		composeMailPage.composeAMail(bccMailId, ccMailId, subject, "C:\\Users\\hi\\Downloads\\123.docx", driver);
		
		//select dropdown options
		composeMailPage.selectDropdown("Vendors");
		
		//select Tomail
		composeMailPage.selectMail(mailId, "email", driver, vendorsName);

		System.out.println("Documents is emailed ");

		//mouse over on administration and click on save button
		homePage.logOut(driver);

		//terminate sission
		driver.quit();


	}
}
