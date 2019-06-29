import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class b11650 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int [][]xy = new int [N][2];
		
		for ( int i =0; i<N; i++) {
			xy[i][0] = scan.nextInt();
			xy[i][1] = scan.nextInt();
		}
		
		Arrays.sort(xy, new Comparator<int[]>() {
			public int compare(int a[], int b[]) {
				if ( a[0] == b[0]) {
					return Integer.compare(a[1],b[1]);
				}
				else
					return Integer.compare(a[0], b[0]);
			}
		});
		
		
		for ( int i =0; i<N; i++) {
			System.out.println(xy[i][0]+" "+xy[i][1]);
		}
	
	}

}
