package others;

import org.testng.annotations.Test;

public class DataProvider {
	
	@Test(dataProvider ="bookingTickets")
	public void getData(String src,String dest,int price) {
		System.out.println("From=  "+src+"To"+dest+"Price"+price);
		
	}
	
	@org.testng.annotations.DataProvider
	public Object[][] bookingTickets(){
		 Object[][] objArr = new Object [3][3];
		 objArr[0][0]="Bangalore";
		 objArr[0][1]="mysore";
		 objArr[0][2]=4000;
		 objArr[1][0]="mysore";
		 objArr[1][1]="Bangalore";
         objArr[1][2]=2000;
         objArr[2][0]="goa";
		 objArr[2][1]="Bangalore";
         objArr[2][2]=5000;
         return objArr;
	}
	
	

}
