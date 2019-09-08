/*
 * 문제 : 2xn 타일링 2
 * 입력 : n ( 1 <= n <= 1,000 )
 * 출력 : 방법의 수 % 10,007
 * 생각
 * - dp(n) = dp(n-1) + 2*dp(n-2)
 *
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11727 {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];


        dp[1] = dp[0] = 1;
        for (int i = 2; i <=n ; i++) {
            dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
        }
        System.out.println(dp[n]);
    }
}
