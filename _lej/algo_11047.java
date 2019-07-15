/*
 * �׸��� �˰��� / ���� 11047�� / ���� 0
 * ������ �ִ� ���� N���� , ������ n�������� ����
 * �� ��ġ�� ���� K�� ������� ��
 * �ʿ��� ���� ������ �ּڰ��� ���ϴ� ���α׷� 
 * 
 * N�� K �Է� > N���� �ٿ� ������ ��ġ�� ������������ �־���
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
