import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class b_10845 {

	public static void main(String[] args) {
		Queue<Integer> que = new LinkedList <Integer>();
		Scanner scan = new Scanner(System.in);
		int n1 = 0;
		int n = scan.nextInt();
		
		for ( int i = 0; i < n; i++) {
			String input = scan.next();
			if ( input.contains("push")) {
				n1 = scan.nextInt();
				System.out.println(que.add(n1));
			}

			else if (input.contains("front")) {
				System.out.println(que.isEmpty()?-1:que.element());
			}
			else if (input.contains("front")) {
				System.out.println(que.isEmpty()?-1: n1);
			}
			else if ( input.contains("size")) {
				System.out.println(que.size());
			}
			else if ( input.contains("empty")) {
				System.out.println(que.isEmpty()?1:0);
			}
		}
	}

}
