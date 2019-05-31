//THIS CODE IS ACTUALLY NOT AN IMPROVEMENT
//LONGER CODE, TAKES LONGER... BUT TAKES LESS MEMORY I GUESS... (28084 < 28448)
import java.util.Scanner;

public class Stack10828Imp {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int count = sc.nextInt();
    int num;
    int section = 0;
    int[] stack = new int[count];
    String command;
    for(int i=0; i<count; i++) {
      command = sc.next();
      switch(command) {
        case "push":
          num = sc.nextInt();
	  stack[section] = num;
          section++;
	  break;
	 case "top":
	   num = (section==0) ? -1 : stack[section-1];
	   System.out.println(num);
	   break;
	 case "size":
	   System.out.println(section);
	   break;
	 case "empty":
	   num = (section==0) ? 1 :  0;
	   System.out.println(num);
	   break;
	 case "pop":
	   num = (section==0) ? -1 : stack[section-1];
	   System.out.println(num);
	   if(section > 0) section--;
	   break;
      }
    }
  }
}
