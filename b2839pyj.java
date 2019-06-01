import java.util.Scanner;

public class Sugar2839 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int hand = -1;
    for(int i=0; i<=1000; i++) {
      if(i*5 > n) break;
      if((n-(i*5))%3 == 0)
        hand = i+(n-(i*5))/3;
    }
    System.out.println(hand);
  }
}
