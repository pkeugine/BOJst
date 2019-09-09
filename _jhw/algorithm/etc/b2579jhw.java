/*
* 문제 : 계단 오르기
* 입력 : 계단의 개수 ( <= 300 ), 계단의 점수 ( <= 10000 )
* 출력 : 총 점수의 최댓값
* 개념 : DP, 반대 발상
*
*
* */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579 {

    static int N;
    static int[] step;
    static int[] dp;

    static int dp(int num) {

        if(num<0) return 0;
        if(dp[num]!=0) return dp[num];
        dp[num] = step[num] + Math.min(dp(num-2),dp(num-3));
        return dp[num];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        step = new int[N+1];
        dp = new int[N+1];
        int sum=0;
        for (int i = 0; i <N ; i++) {
            step[i] = Integer.parseInt(br.readLine());
            sum += step[i];
        }

        System.out.println(sum-dp(N));

    }
}
