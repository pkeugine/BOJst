import java.util.Scanner;

public class Fibo2747 {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int result = fibonacci(n);

    System.out.println(result);
  }
  
  public static int fibonacci(int num) {
    if(num<=1)
      return num;
    else
      return fibonacci(num-2) + fibonacci(num-1);
  }
}
