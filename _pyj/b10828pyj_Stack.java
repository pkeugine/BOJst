import java.util.Scanner;

public class Stack10828 {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int count = sc.nextInt();
    int num;
    int section = 0;
    int[] stack = new int[count];
    String command;
    for(int i=0; i<count; i++) {
      command = sc.next();
      if(command.equals("push")) {
        num = sc.nextInt();
        stack[section] = num;
        section++;
      } else if(command.equals("top")) {
        num = (section==0) ? -1 : stack[section-1];
        System.out.println(num);
      } else if(command.equals("size")) {
        System.out.println(section);
      } else if(command.equals("empty")) {
        num = (section==0) ? 1 :  0;
        System.out.println(num);
      } else if(command.equals("pop")) {
        num = (section==0) ? -1 : stack[section-1];
        System.out.println(num);
        if(section > 0) section--;
      }
    }
  }
}
