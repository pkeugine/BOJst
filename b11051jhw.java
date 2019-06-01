/*
* 문제 : 이항 계수 2
* 입력 : 자연수 N, K
* 출력 : 이항 계수
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11051 {

    static int N, K;
    static int[][] c;


    public static int c(int n, int k) {

        if(c[n][k]!=0) return c[n][k];
        if(n==k || k==0) {
            c[n][k]=1;
            return c[n][k];
        }
        c[n][k] = c(n-1,k-1)+c(n-1,k);
        c[n][k] %= 10007;
        return c[n][k];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = new int[N+1][K+1];
        System.out.println(c(N,K));
    }
}
