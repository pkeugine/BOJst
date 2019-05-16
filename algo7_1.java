package java_algo;

import java.util.Scanner;

public class algo7_1 {
	//아스키 코드값 출력
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		char ch;
		ch = s.next().charAt(0);
		s.close();
		System.out.println(Integer.valueOf(ch));	
	}
}
