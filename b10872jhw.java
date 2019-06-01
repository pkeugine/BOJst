/*
* 문제 : 팩토리얼
* 입력 : 정수 N ( 0 <= N <= 12 )
* 출력 : N!
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10872 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int result = 1;
        for (int i = 2; i <=N ; i++) {
            result *= i;
        }
        if (N==0) System.out.println(0);
        else System.out.println(result);
    }
}
