package com.crm.Vtiger.GenericUtility;
/**
 * hi
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.crm.Vtiger.ObjectRepository.HomePage;
import com.crm.Vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 

{
	public static WebDriver sdriver;
	public WebDriver driver;
	public DataBaseUtility dLib=new DataBaseUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public FileUtility fLib=new FileUtility();
	public WebDriverUtility  wLib=new WebDriverUtility();
	public JavaUtility jLib=new JavaUtility();

	/**
	 * connecting to database
	 */
	@BeforeSuite(groups = "smokeTesting")
	public void dbConfig()
	{
		//dLib.connectToDB();;
	}
	/**
	 * launching the browser
	 * @throws Throwable
	 */
	//@Parameters("BROWSER")
	@BeforeClass(groups = "smokeTesting")
	public void launchTheBrowser()
	{  
		String BROWSER = null;
		try {
			BROWSER = fLib.getPropertyKeyValue("browser");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println(BROWSER);
		String URL = null;
		try {
			URL = fLib.getPropertyKeyValue("url");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			//	ChromeOptions chromeOptions=new ChromeOptions();
			//	chromeOptions.setBinary("C:\\Users\\SanjayBabu\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			//	driver=new ChromeDriver(chromeOptions);
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else {
			driver=new ChromeDriver();
		}

		System.out.println("Browser successfully launched");
		//implicitly wait
		sdriver=driver;
		wLib.waitTillPageGetsLoad(driver);
		//enter the URL of the Application
		sdriver.get(URL);
		//maximize the screen
		driver.manage().window().maximize();
	}
	/**
	 * login to application
	 */
	@BeforeMethod(groups = "smokeTesting")
	public void loginToAppln()
	{
		String USERNAME = null;
		try {
			USERNAME = fLib.getPropertyKeyValue("username");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		String PASSWORD = null;
		try {
			PASSWORD = fLib.getPropertyKeyValue("password");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		LoginPage lpage = new LoginPage(driver);
		lpage.loginToApp(USERNAME, PASSWORD);
		System.out.println("Login successful");
	}
	/**
	 * logout from application
	 */
	@AfterMethod(groups = "smokeTesting")
	public void logoutFromAppln()
	{
		HomePage homePage = new HomePage(driver);
		homePage.logOut(driver);;
		System.out.println("Logout successful");
	}
	/**
	 * close the browser
	 */
	@AfterClass(groups = "smokeTesting")
	public void closeTheBrowser()
	{
		driver.quit();
		System.out.println("Browser successfully closed");
	}
	/**
	 * close database configuration
	 */
	@AfterSuite(groups = "smokeTesting")
	public void closeDBconfig()
	{
		//dLib.closeDB();
	}


}