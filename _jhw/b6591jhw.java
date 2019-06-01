/*
 * 문제 : 이항 쇼다운
 * 입력 : n, k ( <= 2^31-1 ), 0 0
 * 출력 : 이항 계수
 *
 *
 * */


import java.io.*;
        import java.math.BigInteger;
        import java.util.Arrays;
        import java.util.StringTokenizer;

public class BOJ6591 {
// 4 byte x 100 x 10 = 4000B = 4KB
// 4 byte x 1000,000,000 x 1 : 메모리 초과

    static int n, k;
    static int[][] c;

    public static int c(int n, int k) {
        if (n == k || k == 0) return 1;
        if (c[n][k] != 0) return c[n][k];

        c[n][k] = c(n - 1, k - 1) + c(n - 1, k);
        return c[n][k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        while (n != 0) {
            if (n == k || k == 0) bw.write(1 + "\n");
            else if ((n - k) == 1 || k == 1) bw.write(n + "\n");
            else {
                int[] a = {1, 1};
                for (int i = 1; i < n; i++) {
                    int[] b;
                    if (i >= n - k && i >= k) {
                        b = new int[a.length - 1];
                        for (int j = 1; j < a.length; j++) {
                            b[j - 1] = a[j - 1] + a[j];
                        }
                    } else if (i >= k) {
                        b = new int[a.length];
                        b[0] = 1;
                        for (int j = 1; j < a.length; j++) {
                            b[j] = a[j - 1] + a[j];
                        }
                    } else if (i >= n - k) {
                        b = new int[a.length];
                        for (int j = 1; j < a.length; j++) {
                            b[j - 1] = a[j - 1] + a[j];
                        }
                        b[a.length - 1] = 1;
                    } else {
                        b = new int[a.length + 1];

                        b[0] = 1;
                        for (int j = 1; j < a.length; j++) {
                            b[j] = a[j - 1] + a[j];
                        }
                        b[b.length - 1] = 1;
                    }
                    a = b;
                }
                bw.write(a[0] + "\n");
            }

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            /*
            if (n / 2 >= Integer.MAX_VALUE / (n - 1)) {
                if (n == k || k == 0) bw.write(1 + "\n");
                else bw.write(n + "\n");
            } else {
                c = new int[n + 1][k + 1];
                bw.write(c(n, k) + "\n");
            }
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            */
        }
        bw.flush();
    }
}
