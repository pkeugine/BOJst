/*
* 문제 : 환상의 짝꿍
* 입력 : 테스트 케이스의 수 ( 1 <= T <= 500 ), 끈의 길이 정수 A,B ( 1 <= A,B <= 2 x 10^12 )
* 출력 : 소수인 두개의 끈으로 정확히 나눌 수 있는지 판단
* 생각
* - 6이상 짝수 YES
* - 2와 소수 YES
* - 큰 수의 소수판단 : sqrt까지의 소수로 나누기 연산
* - 밀러-라빈 소수 판별법 : 홀수 n이 소수인지 확률적으로 판별
*
* */


import java.io.*;
import java.util.StringTokenizer;

public class BOJ15711 {

    static BufferedWriter bw;
    static long A,B;
    static boolean[] primecheck;
    static int[] prime;
    static int primesize;
    static final int max = 4000001;

    static void solve(long num) throws IOException {
        if(num<max) {
            if(!primecheck[(int)num]) bw.write("YES\n");
            else bw.write("NO\n");
        } else {
            for (int i = 0; i <primesize ; i++) {
                if(num%prime[i]==0) {
                    bw.write("NO\n");
                    return;
                }
            }
            bw.write("YES\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int sqrt = (int)Math.sqrt(max);
        primecheck = new boolean[max];
        prime = new int[max/2];

        for (int i = 2; i <= sqrt ; i++) {
            if(primecheck[i]) continue;
            for (int j = 2*i; j <max ; j=j+i) {
                primecheck[j] = true;
            }
        }

        for (int i = 2; i <max ; i++) {
            if(!primecheck[i]) prime[primesize++] = i;
        }

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());
            long num = A+B;
            if(num<4) bw.write("NO\n");
            else {
                if(num%2==0) bw.write("YES\n");
                else solve(A+B-2);
            }
        }
        bw.flush();
    }
}
