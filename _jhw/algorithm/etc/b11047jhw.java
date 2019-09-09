/*
 * 문제 : 동전 0
 * 입력 : 동전의 종류 N ( 1 <= N <= 10 ), 가치의 합 K ( 1 <= K <= 100,000,000 ), 각 동전의 가치 ( 1 <= Ai <= 1,000,000, A1=1 )
 * 출력 : K원을 만드는데 필요한 동전 개수의 최솟값
 * 개념 : 그리디
 * 생각
 * - 배수이기 때문에 큰수가 최적
 *
 *
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BOJ11047 {

    static int N,K;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        int start = 0;
        for (int i = 0; i <N ; i++) {
            A[i] = Integer.parseInt(br.readLine());
            if(A[i]<=K) start = i;
        }
        int result = 0;
        for (int i = start; i >=0 ; i--) {
            if(A[i]<=K) {
                result += K/A[i];
                K = K%A[i];
            }
            if(K==0) break;
        }
        System.out.println(result);

    }
}
