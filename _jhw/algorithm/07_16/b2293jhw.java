/*
* 문제 : 동전 1
* 입력 : 동전의 종류 n ( 1<= n <= 100 ), 가치의 합 k ( 1 <= k <= 10,000 ), 동전의 가치
* 출력 : 경우의 수
* 개념 : dp
* 생각
* - 동전 한 가지 종류만 사용부터 생각
* * 복사할 필요 X ( 2차원 배열 필요 X )
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {

    static int n,k;
    static int[] values;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        values = new int[n];
        dp = new int[k+1][n+1];
        for (int i = 0; i <n ; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
        dp[0][0] = 1;
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <=k; j++) {
                if(j+values[i]<=k) dp[j+values[i]][i] += dp[j][i];
                dp[j][i+1] = dp[j][i];
            }
        }
        System.out.println(dp[k][n]);
    }
}
