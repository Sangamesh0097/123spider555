package others;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.crm.Vtiger.GenericUtility.ExcelUtility;

public class DataProviderFromExcel extends ExcelUtility {
	@Test(dataProvider ="bookingTickets")
	public void getData(String src,String dest,Object price) {
		System.out.println("From=  "+src+"To"+dest+"Price"+price);
		
	}
	
	@org.testng.annotations.DataProvider
	public Object[][] bookingTickets() throws EncryptedDocumentException, FileNotFoundException, IOException{
		 Object[][] objArr = new Object [3][3];
		 objArr[0][0]=getDataFromExcel("Sheet3", 0, 0);
		 System.out.println(objArr[0][0]);
		 objArr[0][1]=getDataFromExcel("Sheet3", 0, 1);
		 System.out.println(objArr[0][1]);
		 objArr[0][2]=getDataFromExcel("Sheet3", 0, 2);
		 System.out.println(objArr[0][2]);
		 objArr[1][0]=getDataFromExcel("Sheet3", 1, 0);
		 System.out.println(objArr[1][0]);
		 objArr[1][1]=getDataFromExcel("Sheet3", 1, 1);
		 System.out.println(objArr[1][1]);
         objArr[1][2]=getDataFromExcel("Sheet3", 1, 2);
         System.out.println(objArr[1][2]);
         objArr[2][0]=getDataFromExcel("Sheet3", 2, 0);
         System.out.println(objArr[2][0]);
		 objArr[2][1]=getDataFromExcel("Sheet3", 2, 1);
		 System.out.println(objArr[2][1]);
         objArr[2][2]=getDataFromExcel("Sheet3", 2, 2);
         System.out.println(objArr[2][2]);
         return objArr;
	}
	
	

}
