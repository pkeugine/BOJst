import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;


public class b11651 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int [][]xy = new int[n][2]; // 2-d array
		
		
		for ( int i = 0; i <n; i++) {
			xy[i][0] = scan.nextInt();
			xy[i][1] = scan.nextInt();
		} // input for loop end
		
		
		Arrays.sort(xy, new Comparator<int[]>() {
			public int compare(int a[], int b[]) {
				if ( a[1] == b[1]) {
					return Integer.compare(a[0],b[0]);
				}
				else
					return Integer.compare(a[1], b[1]);
			}
		});
		
		
		for ( int i =0; i < n; i++) {
			System.out.println(xy[i][0]+" "+ xy[i][1]);
		}
	}

}
