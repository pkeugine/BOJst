import java.util.Arrays;
import java.util.Scanner;

public class b11399 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int [] ATM = new int [num];
		int [] Time = new int[num];
		int sum = 0;
		for ( int i =0; i < num; i++) {
			ATM[i] = scan.nextInt();
		}
	
		Arrays.sort(ATM);
		
		sum = ATM[0];
		
		for( int i = 1; i < num; i++) {
			ATM[i] += ATM[i-1];
			sum += ATM[i];
		}
		System.out.println(sum);
	}

}
