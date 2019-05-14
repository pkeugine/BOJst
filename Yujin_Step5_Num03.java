package step5;

import java.util.Scanner;

public class Num03 {

	public static void main(String[] args) {
		
		// n��° �ٱ��� �� ���. n=3*(2^k) �̰� k<=10

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		star(n);

	}

	public static void star(int n) {

		String tree[] = new String[n];
		tree[0] = "  *  ";
		tree[1] = " * * ";
		tree[2] = "*****";

		String space = "   ";

		for (int i = 3, k = 1; i < n; i *= 2, k *= 2) {
			for (int j = 0; j < i; j++) {
				tree[i + j] = tree[j] + " " + tree[j];	//Ʈ���� �׸���.
				tree[j] = space + tree[j] + space;		//Ʈ���� ��ġ�� �����.
			}

			for (int j = 0; j < k; j++) {
				space += "   "; //Ʈ���� ����(n)�� Ŀ�� ���� �翷 ������ ���̵� 3ĭ�� �������.
			}
		}

		for (int i = 0; i < n; i++)
			System.out.println(tree[i]);
	}

}
