/*
* 문제 : RGB거리
* 입력 : 집의 수 N ( <= 1,000 ), 비용 ( <= 1,000 )
* 출력 : 모든 집을 칠할 때 비용의 최솟값
* 개념 : DP
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

    static int N;
    static int[][] map;
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i <N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <3 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i <3 ; i++) {
            dp[0][i] = map[0][i];
        }

        for (int i = 0; i <N-1 ; i++) {
            dp[i+1][0] = map[i+1][0] + Math.min(dp[i][1],dp[i][2]);
            dp[i+1][1] = map[i+1][1] + Math.min(dp[i][0],dp[i][2]);
            dp[i+1][2] = map[i+1][2] + Math.min(dp[i][0],dp[i][1]);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <3 ; i++) {
            min = Math.min(min,dp[N-1][i]);
        }
        System.out.println(min);

    }
}
