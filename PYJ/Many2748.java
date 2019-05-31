import java.util.Scanner;

public class Many2748 {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int count = sc.nextInt();

    int ones[] = new int[41];
    ones[0] = 0;
    ones[1] = 1;
    int zeros[] = new int[41];
    zeros[0] = 1;
    zeros[1] = 0;
    
    for(int j=2; j<=40; j++) {
      ones[j] = ones[j-2] + ones[j-1];
      zeros[j] = zeros[j-2] + zeros[j-1]; 
    }

    for(int i=0; i<count; i++) {
      int num = sc.nextInt();
      System.out.println(zeros[num] + " " + ones[num]);
    }
  }
}
