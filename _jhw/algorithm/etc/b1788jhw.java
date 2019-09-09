/*
* 문제 : 피보나치 수의 확장
* 입력 : n ( -1000000 <= n <= 1000000 )
* 출력 : 부호, 절댓값%1000000000
* */
// -1 : 1 0 = 1
// -2 : -1 1 = 0
// -3 : 2 -1  = 1
// -4 : -3 2 -1
// -5 : 5 -3 2
// -6 : -8 5 -3
// 1 -1 2 -3 5 -8


import java.io.*;

public class BOJ1788 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        if(n<0) {
            int f0 = 0;
            int f1 = 1;
            int f = 0;
            for (int i = -1; i >= n ; i--) {
                f = f1-f0;
                f %= 1000000000;
                f1 = f0;
                f0 = f;
            }
            if(n%2==0) {
                bw.write(-1+"\n");
                bw.write((f*-1)+"\n");
            } else {
                bw.write(1+"\n");
                bw.write(f+"\n");
            }
        } else if(n==0) {
            bw.write(0+"\n");
            bw.write(0+"\n");
        } else {
            int f0 = 0;
            int f1 = 1;
            int f = f1;
            for (int i = 2; i <= n ; i++) {
                f = f0+f1;
                f %= 1000000000;
                f0 = f1;
                f1 = f;
            }
            bw.write(1+"\n");
            bw.write(f+"\n");
        }
        bw.flush();
    }
}
