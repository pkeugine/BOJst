/*
* 문제 : 팩토리얼 0의 개수
* 입력 : N ( 0 <= N <= 50 )
* 출력 : 0의 개수
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
//      5 : 1
//     10 : 2
//     15 : 3
//     20 : 4
//     25 : 6
//     5의 개수
        int count=0;
        for (int i = 5; i <=N ; i=i+5) {
            if(i%25==0) count++;
            if(i%125==0) count++;
            count++;
        }
        System.out.println(count);
    }
}
