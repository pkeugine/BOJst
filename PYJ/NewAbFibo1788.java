import java.util.Scanner;

public class NewAbFibo1788 {
  public void main(String[] args) {
    int billion = 1000000000;
    Scanner sc = new Scanner(System.in);
    int count = sc.nextInt();
    long[] fibo = new long[1000001];
    fibo[0] = 0;
    fibo[1] = 1;
    for(int i=2; i<=count; i++) {
      fibo[i] =fibo[i-2]+fibo[i-1];
    }
    result = (fibo[count]<0) ? -1*fibo[count] : fibo[count];
    absolute 
  }
}
