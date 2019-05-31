import java.util.Scanner;
public class VPS9012 {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      String line[] = new String[n+1];
      for(int i=0; i<n; i++) line[i] = sc.next();
      for(int i=0; i<n; i++) cal(line[i]);
  }
  public static void cal(String s) {
      int x=0;
      for(int i=0; i<s.length(); i++) {
          if(s.toCharArray()[i]=='(') x++;
          else x--;
          if(x<0) {
              System.out.println("NO");
              return;
          }
      }
      if(x==0) System.out.println("YES");
      else System.out.println("NO");
  }
}
