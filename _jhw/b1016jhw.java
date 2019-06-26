/*
* 문제 : 제곱 ㄴㄴ 수
* 입력 : min,max ( 1 <= min <= max <= min+1,000,000 <= 1,000,000,000,000 )
* 출력 : 구간 내에 제곱 ㄴㄴ수의 개수
* 개념
* - 에라토스테네스 채
* - [min,max] 구간으로 밀기
*
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1016 {

    static long min,max;
    static boolean[] primecheck;
    static int count;
    static final int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        primecheck = new boolean[MAX];

        StringTokenizer st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        int length = (int)(max-min);

        for (long i = 2; i <MAX ; i++) {
            long pow = i*i;
            long init;
            if(min%pow==0) init = 0;
            else init = pow-min%pow;

            for (long j = init; j <=length ; j=j+pow) {
                if(primecheck[(int)j]) continue;
                primecheck[(int)j] = true;
            }
        }

        for (int i = 0; i <=length ; i++) {
            if(!primecheck[i]) count++;
        }

        System.out.println(count);
    }
}
