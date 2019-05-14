import java.util.Scanner;
public class DH6 {

	public static void main(String[] args) {
		int score[] = new int[5];
		int sum = 0;
		Scanner sc = new Scanner(System.in);

		for ( int i = 0; i < score.length; i++ ) {
			score[i] = sc.nextInt();
				if ( 0 <= score[i] & score[i] <= 100 & score[i] % 5 ==0 ) {
					if ( score[i] < 40) {
							score[i] = 40;}
							sum += score[i];
			} // if loop
				else 
					System.out.println("You entered wrong number");

	} // for loop
		System.out.println(sum/5);
	}
}