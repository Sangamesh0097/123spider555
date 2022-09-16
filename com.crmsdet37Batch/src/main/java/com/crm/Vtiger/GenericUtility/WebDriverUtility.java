package com.crm.Vtiger.GenericUtility;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.jdbc.Driver;

/**
 * 
 * @author hi
 *
 */

public class WebDriverUtility {


	/**
	 * to maximize the browser window
	 * @param driver
	 */
	public void maximizeTheBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/**
	 * to minimize the browser
	 * @param driver
	 */
	public void minimizeTheBrowser(WebDriver driver) {
		driver.manage().window().minimize();
	}
	/**
	 * to refresh the page
	 * @param driver
	 */
	public void refreshThePage(WebDriver driver) {
		driver.navigate().refresh();
	}
	/**
	 * back to previous page
	 * @param driver
	 */
	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}
	/**
	 * move to next page
	 * @param driver
	 */
	public void forwordToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}
	/**
	 * this method will wait untill the page gets load
	 * @param driver
	 */
	public void waitTillPageGetsLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IConstants.implicitlyWaitDuration));

	}
	/**
	 * this method will wait untill element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitTillElementToClick(WebDriver driver,WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * this method will wait untill element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitTillElementToVisible(WebDriver driver,WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * this method will wait till for title of the page
	 * @param driver
	 * @param title
	 */
	public void waitTillPageLoadTitle(WebDriver driver,String title) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * this method will wait till for url of the page
	 * @param driver
	 * @param URL
	 */
	public void waitTillPageURL(WebDriver driver,String URL) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.urlContains(URL));
	}
	/**
	 * this meyhod will ignore the no such element exception for the particular
	 * @param driver
	 */
	public void ignoreNosuchElementException(WebDriver driver) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.ignoring(NoSuchElementException.class);
	}
	/**
	 * this method will wait till alert msg visible  
	 * @param driver
	 */
	public void waitForAlertMsg(WebDriver driver) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * this method is used to switch to frame using id
	 * @param driver
	 * @param Id
	 */
	public void switchToFrame(WebDriver driver,String Id) {
		driver.switchTo().frame(Id);
	}

	/**
	 * this method is used to switch to frame using element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 *this method is used to switch to frame using index 
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This method is used to control switch back to parent frame
	 * @param driver
	 */
	public void switchToMainFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}

	/**
	 * select the option by using index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index)
	{
		Select select=new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * select the option by using value
	 * @param element
	 * @param value
	 */
	public void select(WebElement element,String value)
	{
		Select select=new Select(element);
		select.selectByValue(value);
	}

	/**
	 * select the option by using visible text
	 * @param text
	 * @param element
	 */
	public void select(String text,WebElement element)
	{
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * get all options in the dropdown
	 * @param element
	 */
	public void getAllTheOptionsFromDropDown(WebElement element)
	{
		Select select=new Select(element);
		java.util.List<WebElement> allOptions = select.getOptions();
		for(WebElement options : allOptions)
		{
			String text = options.getText();
			System.out.println(text);
		}
	}

	/**
	 * perpofrming mouse over on the element
	 * @param driver
	 * @param element
	 */
	public void mouseOverOnElement(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * perpofrming right click on element
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	/**
	 * perpofrming double click on element
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver,WebElement element) 
	{
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * perpofrming click action by using sendkeys()
	 * @param driver
	 */
	public void enterKeyClick(WebDriver driver) 
	{
		Actions action=new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}

	/**
	 * Take webpage screenshot
	 * @param driver
	 * @param ScreenShotName
	 * @return 
	 */

	public static String takeScreenshot(WebDriver driver,String ScreenShotName)
	{
		LocalDateTime localDT = LocalDateTime.now();
		String timeStamp = localDT.toString().replace(':', '-');
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshots/"+ScreenShotName+timeStamp+".PNG");
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return ScreenShotName;
	}

	/**
	 * 
	 * @param driver
	 */
	public void waitAndClickUsingCustomWait(WebDriver driver)
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofSeconds(10));
		wait.ignoring(NoSuchElementException.class);
		try {
			wait.wait(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method is used for custom wait
	 * @param element
	 * @param duration
	 * @param pollingTime
	 */
	public void waitAndClick(WebElement element, int duration, long pollingTime)
	{
		int count=0;
		while(count<duration)
		{
			try 
			{
				element.click();
				break;
			} 
			catch (Exception e2) 
			{

				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			count++;
		}
	}

	/**
	 * this method is used to switch to window using titles
	 * @param driver
	 * @param actualTitle
	 */
	public void switchToWindow(WebDriver driver, String actualTitle)
	{
		Set<String> set = driver.getWindowHandles();
		for (String string : set) 
		{
			driver.switchTo().window(string);
			String title = driver.getTitle();
			if(title.contains(actualTitle))
			{
				break;
			}
		}
	}
	/**
	 * this method is used to switch to window using url
	 * @param actualURL
	 * @param driver
	 */
	public void switchToWindow(String expectedURL, WebDriver driver)
	{
		Set<String> allWindows = driver.getWindowHandles();
		java.util.Iterator<String> it = allWindows.iterator();
		while (it.hasNext()) {
			String window = it.next();
			driver.switchTo().window(window);
			String actualURL = driver.getCurrentUrl();
			if (actualURL.contains(expectedURL)) {
				break;
			}
		}
	}
	/**
	 *  this method is used to switch to alert popup and accept
	 * @param driver
	 * @param expectedmsg
	 */
	public void switchToAlertPopUpAndAccept(WebDriver driver, String expectedmsg)
	{
		Alert al = driver.switchTo().alert();
		if(al.getText().trim().equalsIgnoreCase(expectedmsg))
		{
			System.out.println("alert message verified");
		}
		else 
		{
			System.out.println("alert message is not verified");	
		}
		al.accept();
	}
	/**
	 * this method is used to switch to alert popup and dismiss
	 * @param expmsg
	 * @param driver
	 */
	public void switchToAlertPopUpAndAccept(String expectedmsg,WebDriver driver)
	{
		Alert al = driver.switchTo().alert();
		if(al.getText().trim().equalsIgnoreCase(expectedmsg))
		{
			System.out.println("alert message verified");
		}
		else 
		{
			System.out.println("alert message is not verified");	
		}
		al.dismiss();
	}

}
