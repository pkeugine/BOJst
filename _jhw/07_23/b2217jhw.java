/*
* 문제 : 로프
* 입력 : 로프의 개수 N ( 1 <= N <= 100,000 ), 버릴 수 있는 최대 중량 ( <= 10,000 )
* 출력 : 들어올릴 수 있는 물체의 최대 중량
* 개념 : 그리디
* 생각
* - 큰 순 정렬 1 ~ n
* - 최소 x 개수
*
*
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2217 {

    static int N;
    static int[] rope;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rope = new int[N];
        for (int i = 0; i <N ; i++) {
            rope[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(rope);
        int max = 0;
        for (int i = N-1; i >=0 ; i--) {
            max = Math.max(rope[i]*(N-i),max);
        }
        System.out.println(max);

    }
}
