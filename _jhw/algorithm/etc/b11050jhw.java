/*
* 문제 : 이항 계수 1
* 입력 : 자연수 N, K
* 출력 : 이항 계수
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11050 {

    static int N, K;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int n=N;
        int k=1;
        for (int i = 2; i <=K; i++) {
            k *= i;
            n *= --N;
        }
        if(K==0) {
            System.out.println(1);
        } else {
            System.out.println(n/k);
        }
    }
}
