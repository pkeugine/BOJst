import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class b2217 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int w = 0;
		
		Integer[] rope = new Integer[num];
		
		for(int i = 0; i < num; i++) {
			rope [i] = scan.nextInt();
		}
		
		Arrays.sort(rope, Collections.reverseOrder());
		
		for(int i = 1; i <= num; i++) {
			 w = Math.max(w, rope[i-1]*i); 
			 }
		
		System.out.println(w);
	}
}