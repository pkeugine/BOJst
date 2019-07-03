import java.util.Scanner;

public class b11047 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("동전 종류 개수를 입력 하시오.");
		int variable = sc.nextInt(); // 종류의 개수
		System.out.println("계산해야할 가격을 입력 하시오.");
		int cost = sc.nextInt(); // 계산될 가격.
		int[] coin = new int [variable]; // 동전.
		int count = 0; // 최소 동전 개수.
		
		System.out.println("동전의 종류를 입력 하시오.");
		for ( int i = 0; i < variable; i++) {
			coin[i] = sc.nextInt();
		}
		
		for ( int i = variable-1; i >= 0; i--) {
			if ( cost >= coin[i]) {
				count += cost / coin[i];
				cost = cost % coin[i];
				}
			}
		
			System.out.println(count);
		
		}
	}
