/*
 * 문제 : 쉬운 계단 수
 * 입력 : 길이 N ( 1 <= N <= 100 )
 * 출력 : 계단 수 개수 ( % 1,000,000,000 )
 * 개념 : dp
 * 생각
 * - 길이 1 부터 증가
 * - 0, 9 는 1, 8 만
 *
 *
 *
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844 {

    static int N;
    static int result;
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[101][10];
        for (int i = 1; i <=9 ; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <=N ; i++) {
            dp[i][0] = dp[i-1][1];
            for (int j = 1; j <9 ; j++) {
                dp[i][j] = (dp[i-1][j-1]+dp[i-1][j+1])%1000000000;
            }
            dp[i][9] = dp[i-1][8];
        }
        for (int i = 0; i <=9 ; i++) {
            result += dp[N][i];
            result = result%1000000000;
        }
        System.out.println(result);
    }
}
