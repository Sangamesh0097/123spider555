package com.crm.Vtiger.GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

/**
 * 
 * @author hi
 *
 */

public class DataBaseUtility {
	
	static Driver driverRef;
	static Connection connection;
	static ResultSet result;
	/**
	 * this method is used to connect DB
	 * @param DBname
	 */
	public void connectToDB(String DBname) {
		try {
			driverRef=new Driver();
			DriverManager.registerDriver(driverRef);
			connection = DriverManager.getConnection(IConstants.DbUrl+DBname,IConstants.DBUsername,IConstants.DBPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * this method is used to close the connection
	 */
	public void closeDB() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * this method is used to execute query
	 * @param query
	 * @param columnNum
	 * @param expectedData
	 * @return
	 * @throws SQLException
	 */
	public boolean execuiteQuery(String query,int columnNum,String expectedData) throws SQLException {
		result=connection.createStatement().executeQuery(query);
		boolean flag =false;
		while (result.next()) {
			if (result.getString(columnNum).equals(expectedData)) {
				flag=true;
				break;
			}
	
		}
		if (flag) {
			System.out.println("data is present");
			return flag;
		}else {
			System.out.println("data is not present");
			return flag;
		}
	}
	
	
	/**
	 * this method is used to perform executeUpdate
	 * @param query
	 * @throws SQLException
	 */
	public void executeUpdate(String query) throws SQLException {
		int res = connection.createStatement().executeUpdate(query);
		if (res==1) {
			System.out.println("data is updated");
		}else {
			System.out.println("data is not updated ");
		}
	}
	
	

}
