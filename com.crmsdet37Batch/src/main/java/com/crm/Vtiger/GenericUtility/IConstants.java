package com.crm.Vtiger.GenericUtility;

public interface IConstants 
{
	String filepath= ".\\src\\test\\resources\\CommunData.properties";
	String excelpath= ".\\src\\test\\resources\\Excel.xlsx";
	String chromeKey= "webdriver.chrome.driver";
	String chromeValue=".\\src\\test\\resources\\chromedriver.exe";
	String DbUrl="jdbc:mysql://localhost:3306/";
	String DBUsername="root";
	String DBPassword="root";
	int implicitlyWaitDuration=10;
	int explicitWaitDuration=10;
	String browserNameForChrome="chrome";
	String browserNameForFirefox="firefox";
	String browserNameForEdge="edge";
}