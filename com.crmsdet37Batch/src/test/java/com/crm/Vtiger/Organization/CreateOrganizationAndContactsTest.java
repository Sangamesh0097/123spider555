package com.crm.Vtiger.Organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.Vtiger.GenericUtility.DataBaseUtility;
import com.crm.Vtiger.GenericUtility.ExcelUtility;
import com.crm.Vtiger.GenericUtility.FileUtility;
import com.crm.Vtiger.GenericUtility.IConstants;
import com.crm.Vtiger.GenericUtility.JavaUtility;
import com.crm.Vtiger.GenericUtility.WebDriverUtility;
import com.crm.Vtiger.ObjectRepository.ContactInfoPage;
import com.crm.Vtiger.ObjectRepository.ContactPage;
import com.crm.Vtiger.ObjectRepository.CreateContactPage;
import com.crm.Vtiger.ObjectRepository.CreateOrganizationPage;
import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;
import com.crm.Vtiger.ObjectRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationAndContactsTest {

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

		//creating random number perpuse   
		int random = jLib.getRandomNum();


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


		//fetch data from excel file       
		String organizationName = eLib.getDataFromExcel("Vtiger", 1, 2);
		String insurance = eLib.getDataFromExcel("Industry dropdown", 19, 0);
		String Customer = eLib.getDataFromExcel("Type Dropdown", 3, 0);
		String lastName = eLib.getDataFromExcel("Vtiger", 3, 9);
		String firstName = eLib.getDataFromExcel("Vtiger", 3, 8);
		String organizationNameRandom=organizationName+random;
		String dropdownFirstNameoption = eLib.getDataFromExcel("FirstName dropdown", 1, 0);

		//Enter organization name into organization name text field
		CreateOrganizationPage createOrganization = new CreateOrganizationPage(driver);
		createOrganization.getOrganizationName(organizationNameRandom);

		//select dropdown option
		createOrganization.selectTypeDropDown(Customer);

		//select dropdown option
		createOrganization.selectIndustryDropDown(insurance);


		//click on save button
		createOrganization.getSaveButton().click();


		Thread.sleep(3000);

		boolean flag = false;
		while (false==flag) {
			//click on contacts link
			homePage.getContactLink().click();
			break;
		}

		//click on create contacts link
		ContactPage contactPage = new ContactPage(driver);
		contactPage.getCreateContactName().click();

		//Enter first name,last name,and firstname dropdown option	
		CreateContactPage createContactPage = new CreateContactPage(driver);
		createContactPage.createContacts(firstName, lastName, dropdownFirstNameoption);;

		//select organization
		createContactPage.selectOrganization(driver, organizationNameRandom);

		//click on save button
		createContactPage.getSaveButton().click();

		//Expected data
		String expectedCreatedData = firstName;

		//Actual data
		ContactInfoPage contactInfoPage = new ContactInfoPage(driver);
		String actualcreatedData=contactInfoPage.getCreatedContactName().getText();

		//verify project created or not
		if (actualcreatedData.contains(expectedCreatedData)) {
			System.out.println("Organization page is verified and Organization is created And contact also added");
		}
		else {
			System.out.println("Organization page is verified and Organization is not created And contact also not added to Project");
		}

		//mouse over on administration and click on save button
		homePage.logOut(driver);


		//terminate session
		driver.quit();
	}
}
