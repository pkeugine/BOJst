/*
* 문제 : 패션왕 신해빈
* 입력 : 테스트 케이스 수, 의상 수, 의상의 이름, 의상의 종류
* 출력 : 경우의 수
*
* */


import java.io.*;
import java.util.*;

public class BOJ9375 {
//  종류별  의상 수 + 1
//  최종 -1




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        while(tc-->0) {
            Map<String,Integer> hs = new HashMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] clo = new int[n];
            Arrays.fill(clo,1);
            int count=0;
            for (int i = 0; i <n ; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String item = st.nextToken();
                if(!hs.containsKey(item)) {
                    clo[count]++;
                    hs.put(item,count++);
                } else {
                    clo[hs.get(item).intValue()]++;
                }
            }
            int result = 1;
            for (int i = 0; i <clo.length ; i++) {
                result *= clo[i];
            }
            bw.write(result-1+"\n");
            hs.clear();
        }
        bw.flush();
    }
}
