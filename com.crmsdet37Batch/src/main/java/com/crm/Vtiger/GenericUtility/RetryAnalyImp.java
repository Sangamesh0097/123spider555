package com.crm.Vtiger.GenericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyImp implements IRetryAnalyzer{

	
	int count=0;
	int retryLimits=3;
	public boolean retry(ITestResult result) {
		
		if (count<retryLimits) {
			count++;
			return true;
		}
		return false;
	}

}
