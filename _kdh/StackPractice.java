import java.util.Scanner;
import java.util.Stack;

public class StackPractice {

   public static void main(String[] args) {
	        Stack <Integer> set = new Stack<Integer>();
	        Scanner scan = new Scanner(System.in);
	        
	        int n = scan.nextInt(); // ����� �� �Է�
	        
	        for ( int i = 0; i < n; i++) {
	        	String input = scan.next(); // stack�� ���� ����� ���� string�� ����.
	        	if ( input.contains("push")) {
	        		int n1 = scan.nextInt();
	        		set.push(n1);
	        	} //  if
	        	else if (input.contains("empty")) {
	        		System.out.println(set.isEmpty()?1:0);
	        	}
	        	else if ( input.contains("size")) {
	        		System.out.println(set.size());
	        	}
	        	else if ( input.contains("pop")) {
	        		System.out.println(set.isEmpty()?-1:set.pop());
	        	}
	        	else if ( input.contains("top")) {
	        		System.out.println(set.isEmpty()?-1:set.peek());
	        	}
	        }
		}
	}
