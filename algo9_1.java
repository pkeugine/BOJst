
//�Ϸ�
package java_algo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class algo9_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//N���� ���� �־������� , �̸� ������������ �����ϴ� ���α׷�
		Scanner s = new Scanner(System.in);
		ArrayList<Integer> Array = new ArrayList<Integer>(); 
		int n = s.nextInt();
		for(int i = 0; i < n; i++) {
			int num = s.nextInt();
			Array.add(num);
		}
		s.close();
		Collections.sort(Array);
		
		for(int i = 0; i < Array.size(); i++) {
			System.out.println(Array.get(i));
		}
	}

}
