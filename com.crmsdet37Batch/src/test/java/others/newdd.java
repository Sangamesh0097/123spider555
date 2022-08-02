package others;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class newdd {
	
	public static void main(String[] args) {
		String [] str= {"hi","hello","bye"};
		for(int i=0;i<str.length;i++){
			String s=str[i];
			char [] ch=s.toCharArray();
			
			for (int j = ch.length-1; j <ch.length; j++) {
				char a=ch[ch.length-1];
				
				if (a=='a'||a=='e'||a=='i'||a=='o'||a=='u') {
					System.out.println(a);
					System.out.println(s);
				}
			}
		}
	}
	

}
