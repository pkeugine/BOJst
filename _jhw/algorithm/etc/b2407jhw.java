/*
* 문제 : 조합
* 입력 : n,m ( 5 <= m <= n <= 100 )
* 출력 : nCm
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ2407 {

    static int n,m;
    static BigInteger[][] c;
    static BigInteger big;

    public static BigInteger c(int n,int m) {
        if(n==m || m==0) {
            c[n][m] = BigInteger.valueOf(1);
            return c[n][m];
        }
        if(!c[n][m].equals(BigInteger.ZERO)) return c[n][m];
        c[n][m]=c(n-1,m-1).add(c(n-1,m));
        return c[n][m];
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = new BigInteger[n+1][m+1];
        for (int i = 0; i <=n ; i++) {
            for (int j = 0; j <=m ; j++) {
                c[i][j] = new BigInteger("0");
            }
        }
        System.out.println(c(n,m));

    }
}
