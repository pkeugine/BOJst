import java.util.Scanner;

public class Pisa2749 {
  public static void main(String[] args) {
    
    int mod = 1000000;
    int p = mod/10*15;
    int fibo[] = new int[p];
    fibo[0] = 0;
    fibo[1] = 1;

    for(int i=2; i<p; i++) {
      fibo[i] = fibo[i-2] + fibo[i-1];
      fibo[i] %= mod;
    }
    Scanner sc = new Scanner(System.in);
    long num = sc.nextLong();
    
    num %= p;
    System.out.println(fibo[(int)num]);

  }
}
