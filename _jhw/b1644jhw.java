/*
* 문제 : 소수의 연속합
* 입력 : 자연수 N ( 1 <= N <= 4,000,000 )
* 출력 : 연속된 소수의 합으로 나타낼 수 있는 경우의 수
* 개념 : 소수 구하기, ( 구간합 )
* 생각
* - 소수 구간합 ( N 까지 )
* - <=N : 다음 구간 합
* - > N : 왼쪽 증가
* - 소수 사이즈 만큼
*
*
*
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1644 {

    static int N;
    static int[] primesum;
    static boolean[] prime;
    static int count;
    static int primesize=1;

    static void sumPrime(int num) {
        int sqrt = (int)Math.sqrt(num);
        for (int i = 2; i <=sqrt ; i++) {
            if(prime[i]) continue;
            for (int j = 2; i*j <= num ; j++) {
                if(prime[i*j]) continue;
                prime[i*j] = true;
            }
        }
        int right = 0;
        for (int i = 2; i <=num ; i++) {
            if(!prime[i]) {
                primesum[primesize] = primesum[primesize-1] + i;
                primesize++;
            }
        }
    }

    static void solve(int left, int right) {
        if(right>=primesize) return;

        int sum = primesum[right]-primesum[left];
        if(sum==N) count++;
        if(sum<=N) solve(left,right+1);
        else solve(left+1,right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prime = new boolean[N+1];
        primesum = new int[N+1];
        sumPrime(N);
        solve(0,1);
        System.out.println(count);
    }
}
