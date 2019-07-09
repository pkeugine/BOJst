/*
 * 그리디 알고리즘 / 백준 11047번 / 동전 0
 * 가지고 있는 동전 N종류 , 무수한 n개가지고 있음
 * 그 가치의 합을 K로 만들려고 함
 * 필요한 동전 개수의 최솟값을 구하는 프로그램 
 * 
 * N과 K 입력 > N개의 줄에 동전의 가치가 오름차순으로 주어짐
 */
package java_algo;

import java.util.Scanner;

public class algo_11047 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int maxCoin = 0;
		
		int[] coin = new int[n];
		int rslt = 0;
		for(int i = 0; i < n; i++) {
			coin[i] = sc.nextInt();
			if(coin[i] <= k)
				maxCoin = i;
		}
		for(int i = maxCoin; i >= 0; i--) {
			rslt += k / coin[i];
			k %= coin[i];
		}
		System.out.println(rslt);
		sc.close();
	}
}
