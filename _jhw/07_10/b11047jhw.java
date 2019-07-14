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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 시스템 입력을 읽어 들임
        StringTokenizer st = new StringTokenizer(br.readLine()); // 문자열을 토큰으로 나눠줌 ( 기본 구분자 : 공백 )
        N = Integer.parseInt(st.nextToken()); // 동전의 종류
        K = Integer.parseInt(st.nextToken()); // 구할 가치
        A = new int[N]; // 각 동전의 가치


//      동전 가치 입력 & 가장 가치가 큰 동전 구하기 ( 구할 가치보다 작은 동전에 한에 )
        int start = 0; // 구할 가치에 가장 근접한 동전 인덱스
        for (int i = 0; i <N ; i++) {
            A[i] = Integer.parseInt(br.readLine());
            if(A[i]<=K) start = i; // 구할 가치보다 작은 동전의 중 가장 큰 동전 ( 오름차 순 입력이기에 계속 갱신하여 구함 )
        }


//      동전 개수 계산
        int result = 0; // 동전 개수
        for (int i = start; i >=0 ; i--) { // 가장 가치가 큰 동전부터 내림차 순 반복
            if(A[i]<=K) {
                result += K/A[i]; // 동전 개수
                K = K%A[i]; // 나머지
            }
            if(K==0) break; // 나머지가 없으면 탈출
        }

//      동전 개수 출력
        System.out.println(result);
    }
}
