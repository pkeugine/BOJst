package step5;

import java.util.Scanner;

public class Num03 {

	public static void main(String[] args) {
		
		// n번째 줄까지 별 출력. n=3*(2^k) 이고 k<=10

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
				tree[i + j] = tree[j] + " " + tree[j];	//트리를 그린다.
				tree[j] = space + tree[j] + space;		//트리의 위치를 맞춘다.
			}

			for (int j = 0; j < k; j++) {
				space += "   "; //트리의 높이(n)가 커질 수록 양옆 공백의 길이도 3칸씩 길어진다.
			}
		}

		for (int i = 0; i < n; i++)
			System.out.println(tree[i]);
	}

}
