import java.util.Scanner;

public class MeEe11051 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int r = sc.nextInt();

    System.out.println(Factorial(n,r));
  }

  public static int Factorial(int num, int rum) {
    int[][] Memo = new int[num+1][1001];
    Memo[0][0] = 1;
    Memo[1][0] = 1;
    Memo[1][1] = 1;
    for(int i=2; i <= num; i++) {
      for(int j=0; j<= num; j++) {
        if(j==0 || i == j)
          Memo[i][j] = 1;
        else
          Memo[i][j] = Memo[i-1][j-1] + Memo[i-1][j];
        Memo[i][j] %= 10007;
      }
    }
    return Memo[num][rum];
  }
}
