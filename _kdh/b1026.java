import java.util.Arrays;
import java.util.Scanner;


public class b1026 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("�迭�� ũ�⸦ �Է��Ͻÿ� : ");
		int N = scan.nextInt();
		int sum = 0;
		
		int[] A = new int [N];
		int[] B = new int [N];
		
		System.out.print("A �迭�� �� ���� �Է��Ͻÿ�. ");
			for(int i = 0; i<N; i++) {
					A[i] = scan.nextInt();
				} // for 1;
			
		System.out.println("B �迭�� �� ���� �Է��Ͻÿ�.");	
			for ( int i = 0; i<N; i++) {
					B[i] = scan.nextInt();
				} // for 2;
		 
			Arrays.sort(A); // ������������ �������ִ� �Լ�. 
			Arrays.sort(B);
		
		for ( int i = 0; i < N; i++) {
			sum += A[i] * B[N-1-i];
		}
	
		
		System.out.println(sum);
		
		
	}

}
