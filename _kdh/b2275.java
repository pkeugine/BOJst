import java.util.*;

public class b2275 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][]apartment = new int[15][15];
		
		for ( int i = 0; i < 15; i++) {
			apartment[i][0] = 1;
			apartment[0][i] = i+1;
		}
		
		for ( int i = 1; i < 15; i++) {
			for ( int j  = 1; j < 15; j++) {
				apartment[i][j] = apartment[i-1][j]+apartment[i][j-1]; 
			}
		}
		
		
		int T = scan.nextInt();
		
		for ( int i = 0; i <T; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt()-1;
			System.out.println(apartment[a][b]);
		}
		
		
	}

}
