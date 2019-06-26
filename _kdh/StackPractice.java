import java.util.Scanner;
import java.util.Stack;

public class StackPractice {

   public static void main(String[] args) {
	        Stack <Integer> set = new Stack<Integer>();
	        Scanner scan = new Scanner(System.in);
	        
	        int n = scan.nextInt(); // 명령의 수 입력
	        
	        for ( int i = 0; i < n; i++) {
	        	String input = scan.next(); // stack에 대한 명령을 받을 string값 설정.
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
