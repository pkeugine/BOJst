import java.util.Scanner;

public class b11047 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("���� ���� ������ �Է� �Ͻÿ�.");
		int variable = sc.nextInt(); // ������ ����
		System.out.println("����ؾ��� ������ �Է� �Ͻÿ�.");
		int cost = sc.nextInt(); // ���� ����.
		int[] coin = new int [variable]; // ����.
		int count = 0; // �ּ� ���� ����.
		
		System.out.println("������ ������ �Է� �Ͻÿ�.");
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
