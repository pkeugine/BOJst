import java.util.Scanner;

public class Memo2747 {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    System.out.println(fibonacci(n));
  }
  
  public static int fibonacci(int num) {
    int[] Fibo = new int[num+1];
    Fibo[0] = 0;
    Fibo[1] = 1;

    for(int i=2; i<=num; i++) {
      Fibo[i] = Fibo[i-2] + Fibo[i-1];
    } 
    return Fibo[num];
  }
}
