/*
 * 그리디 알고리즘 / 백준 1931번 / 회의실배정
 */

package java_algo;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class algo_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] c_t = new int[n][2];
        int result = 0;
        int endTime = -1;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            c_t[i][0] = Integer.parseInt(st.nextToken());
            c_t[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(c_t,(c_t1 , c_t2) -> c_t1[1]==c_t2[1] ? c_t1[0]-c_t2[0] : c_t1[1]-c_t2[1]);

        for (int i = 0; i < n; i++) {
            if (c_t[i][0] >= endTime){
                result++;
                endTime = c_t[i][1];
            }
        }
        System.out.println(result);
    }
}