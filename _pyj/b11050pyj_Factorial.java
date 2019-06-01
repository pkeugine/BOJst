import java.util.Scanner;

public class Eeha11050 {
  public static void main(String[] args) {
  
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int r = sc.nextInt();

    System.out.println(Factorial(n,r));
  }
  
  public static int Factorial(int num, int rum) {
    if(num == rum || rum == 0)
      return 1;
    else
      return Factorial(num-1, rum-1) + Factorial(num-1, rum);
  }
}
