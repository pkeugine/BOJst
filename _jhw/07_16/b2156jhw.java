/*
* 문제 : 포도주 시식
* 입력 : 포도주 잔의 개수 n ( 1 <= n <= 10,000 ), 포도주의 양 ( <= 1,000 )
* 출력 : 최대로 마실 주 있는 포도주의 양
* 개념 : dp
* 생각
* - 연속 3잔 불가 -> 두 잔이상 마시기 X
* - 앞에서 부터
* - 반대 : 최소, 3칸 초과 X
* - dp(n) += min(dp(n-3), dp(n-2), dp(n-1))
*
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156 {

    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+2];
        int sum = 0;
        for (int i = 1; i <=n ; i++) {
            dp[i] = Integer.parseInt(br.readLine());
            sum += dp[i];
        }
        for (int i = 3; i <=n+1 ; i++)
            dp[i] = Math.min(Math.min(dp[i-3],dp[i-2]),dp[i-1])+dp[i];

        System.out.println(sum-dp[n+1]);
    }
}
