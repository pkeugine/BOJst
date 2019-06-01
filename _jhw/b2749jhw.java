import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 문제 : 피보나치 수 3
 * 입력 : 피보나치 순서 n ( n <= 1,000,000,000,000,000,000 )
 * 출력 : 피보나치 수
 *
 *
 *
 * */
public class BOJ2749 {

    static long n;
    static long[] f = new long[5];
    static int[] a = {0,1,1,2,3};


    public static void f(long n, int position) {
        if (n <= 4 && position == 0) {
            if(n==4) {
                f[0] = a[(int)n];
                f[1] = a[(int)n]+a[(int)n-1];
                return;
            }
            f[0] = a[(int)n];
            f[1] = a[(int)n+1];
            return;
        }
        if (n <= 4 && position == 1) {
            f[0] = a[(int)n-1];
            f[1] = a[(int)n];
            return;
        }

        long num = n / 2;
        if (num % 2 == 0) {
            f(num - 2, 0);
        } else {
            f(num - 1, 1);
        }
        f[2] = (f[0] + f[1]);
        f[3] = (f[1] + f[2]);
        f[4] = (f[2] + f[3]);

        long tmp;
        if (position == 0) {
            tmp = ((f[0] * f[3]) + (f[1] * f[4]));
            f[1] = ((f[1] * f[3]) + (f[2] * f[4]));
            f[0] = tmp;
        } else {
            tmp = ((f[0] * f[2]) + (f[1] * f[3]));
            f[1] = ((f[0] * f[3]) + (f[1] * f[4]));
            f[0]= tmp;
        }
        if(f[0]/1000000!=0 && f[1]/1000000!=0) {
            f[0] %= 1000000;
            f[1] %= 1000000;
        }
        // System.out.println("n : "+n+", position : "+position+", result : "+f[0]+", "+f[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());

        long num = n/2;
        if(n==1) {
            System.out.println(a[(int)n]);
            return;
        }
        if (n % 2 == 0) {
            f(n,0);
            System.out.println(f[0]%1000000);
        } else {
            if (num % 2 == 0) {
                f(num, 1);
            } else {
                f(num - 1, 0);
            }
            f[2] = (f[0] + f[1]);
            f[3] = (f[1] + f[2]);
            System.out.println(((f[0] * f[2])%1000000 + (f[1] * f[3])%1000000)%1000000);
        }

    }
}



