import java.util.Arrays;
import java.util.Scanner;


public class b1026 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("배열의 크기를 입력하시오 : ");
		int N = scan.nextInt();
		int sum = 0;
		
		int[] A = new int [N];
		int[] B = new int [N];
		
		System.out.print("A 배열에 들어갈 수를 입력하시오. ");
			for(int i = 0; i<N; i++) {
					A[i] = scan.nextInt();
				} // for 1;
			
		System.out.println("B 배열에 들어갈 수를 입력하시오.");	
			for ( int i = 0; i<N; i++) {
					B[i] = scan.nextInt();
				} // for 2;
		 
			Arrays.sort(A); // 내림차순으로 정리해주는 함수. 
			Arrays.sort(B);
		
		for ( int i = 0; i < N; i++) {
			sum += A[i] * B[N-1-i];
		}
	
		
		System.out.println(sum);
		
		
	}

}
