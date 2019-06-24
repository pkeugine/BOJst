import java.util.Scanner;

public class bubblesort2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num;
		int temp;
		num = scan.nextInt();
		
		int [] arr = new int[num];
		
		for (int i = 0; i < num; i++) {
			arr[i]=scan.nextInt();
			}
		
		
		for ( int i = 0; i < arr.length; i++) {
			for ( int j =0; j < arr.length-i-1; j++) {
				if ( arr[j] > arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
	
		for ( int i =0; i < num; i++) {
			System.out.println(arr[i]);
		}
	}

}
