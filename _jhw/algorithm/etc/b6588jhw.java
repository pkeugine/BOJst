/*
* 문제 : 골드바흐의 추측
* 입력 : 짝수 정수 n ( 6 <= n <= 1000000 )
* 출력 : n = a + b ( b - a 가 가장 큰 경우 )
* 개념 : 소수 구하기, 간단하게
*
*
* */


import java.io.*;

public class BOJ6588 {

    static int N;
    static boolean[] primecheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        primecheck = new boolean[1000001];
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <=1000 ; i++) {
            if(primecheck[i]) continue;
            for (int j = i*2; j <= 1000000 ; j=j+i) {
                primecheck[j] = true;
            }
        }


        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N==0) break;
            boolean flag=false;
            for (int i = 3; i <= N/2 ; i++) {
                if(!primecheck[i] && !primecheck[N-i]){
                    sb.append(N).append(" = ").append(i).append(" + ").append(N-i).append("\n");
                    flag = true;
                    break;
                }
            }
            if(!flag) sb.append("Goldbach's conjecture is wrong.\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
