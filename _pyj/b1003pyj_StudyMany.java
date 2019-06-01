import java.util.Scanner;

public class StudyMany2748 {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    
    System.out.println("횟수를 입력하고 숫자를 입력하면 그 숫자를 얻기 위해 0과 1을 각각 몇 번 더하는지 알려줍니다 :)");
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
      System.out.println(num + "은 0을 " + zeros[num] + " 번 그리고 1을  " + ones[num] + " 번 더합니다 ㄷㄷㄷ ");
    }
  }
}
