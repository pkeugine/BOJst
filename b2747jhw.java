import java.io.*;

/*
* 문제 : 피보나치 수
* 입력 : 피보나치 순서 n ( n <= 45 )
* 출력 : 피보나치 수
*
*
*
* */
public class BOJ2747 {

    static int n;
    static long result;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];

        long n0 = 0;
        long n1 = 1;
        result = 1;

        for (int i = 2; i <=n ; i++) {
            result = (n0+n1);
            n0 = n1;
            n1 = result;
        }

        /*
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <=n ; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        result = dp[n];
        */

        System.out.println(result);
    }
}



