package com.crm.Vtiger.GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author hi
 *
 */

public class FileUtility {

	/**
	 * to get the common data from property file
	 * @param Key
	 * @return
	 * @throws Throwable
	 */
	public String getPropertyKeyValue(String Key) throws Throwable
	{
		FileInputStream fileInputStream = new FileInputStream(IConstants.filepath);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String value =properties.getProperty(Key);
		return value;
	}
}
