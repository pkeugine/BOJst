/*
* 문제 : 1로 만들기
* 입력 : 정수 N ( 1 <= N <= 1000000 )
* 출력 : 연산 횟수의 최솟값
* 개념 : DP, 메모이제이션
*
*
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {

    static int N;
    static int[] dp;

    static int dp(int num) {
        if(dp[num]!=0) return dp[num];
        if(num==1) return 0;

        int min = Integer.MAX_VALUE;
        if(num%3==0) min = dp(num/3)+1;
        if(num%2==0) min = Math.min(min,dp(num/2)+1);
        min = Math.min(min,dp(num-1)+1);
        dp[num] = min;

        return dp[num];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        System.out.println(dp(N));

    }
}
