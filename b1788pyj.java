import java.util.Scanner;

public class AbFibo1788 {
  public static void main(String[] args) {

    int million=1000000;
    long billion=1000000000;
    
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int absolute=0;
    long result=0;

    long[] fibo = new long[2000001];
    int number=n+million;
    fibo[0+million]=0;
    fibo[1+million]=1;

    if(n == 0 || n == 1) {
      result = fibo[number];
      absolute = (n==0) ? 0 : 1;
    } else if(n>=2) {
      for(int i=million+2; i<=number; i++) {
        fibo[i] = fibo[i-2] + fibo[i-1];
      }
      result = fibo[number]%billion;
      absolute = 1;
    } else if(n<0) {
      for(int i=million-1; i>=number; i--) {
        fibo[i] = fibo[i+2] - fibo[i+1];
      }
      result = (fibo[number]<0) ? -1*fibo[number]%billion : fibo[number]%billion;
      absolute = (fibo[number]<0) ? -1 : 1;
    }
    
    System.out.println(absolute);
    System.out.println(result);
  }
}
