import java.io.*;
import java.util.StringTokenizer;

/*
* 문제 : 피보나치 함수
* 입력 : 테스트 케이스 개수 T, N ( 0 <= N <= 40 )
* 출력 : 0 출력 횟수, 1 출력 횟수
*
*
*
* */
public class BOJ1003 {

//    0 : 1 0
//    1 : 0 1
//    2 : 1 1
//    3 : 1 2
//    4 : 2 3
//    5 : 3 5
//    6 : 5 8




    static int T,N;
    static int[] dp;


    static int dp(int n) {
        if(dp[n]!=0) return dp[n];
        if(n==1) return 1;
        if(n==0) return 0;
        dp[n] = dp(n-1)+dp(n-2);
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        while(T-->0) {
            N = Integer.parseInt(br.readLine());
            dp = new int[N+1];
            if(N==0) bw.write(1+" "+0+"\n");
            else bw.write(dp(N-1)+" "+dp(N)+"\n");
        }
        bw.flush();

    }
}
