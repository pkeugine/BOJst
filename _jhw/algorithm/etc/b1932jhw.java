/*
* 문제 : 정수 삼각형
* 입력 : 삼각형의 크기 N ( 1 <= N <= 500 ), 정수 삼각형
* 출력 : 합이 최대가 되는 경로에 있는 수의 합
* 개념 : 2차원 DP
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {

    static int n;
    static int[][] map;
    static int[][] dp;

    static int dp(int x,int y) {
        if(y==n-1) return map[y][x];
        if(dp[y][x]!=0) return dp[y][x];
        dp[y][x] = map[y][x] + Math.max(dp(x,y+1),dp(x+1,y+1));
        return dp[y][x];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i <n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <=i ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dp(0,0));
    }

}
