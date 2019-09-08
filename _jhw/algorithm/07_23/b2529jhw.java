/*
 * 문제 : 부등호
 * 입력 : 부등호 문자의 개수 k ( 2 <= k <= 9 )
 * 출력 : k+1 자리의 최대, 최소 정수
 * 개념 : 시뮬레이션
 * 생각
 * - 반대 부등호만큼 숫자 증가
 * - 다시 돌아가서 오름차순 / 내린차순
 *
 *
 * */


import java.io.*;
import java.util.StringTokenizer;

public class BOJ2529 {

    static int k;
    static char[] c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        k = Integer.parseInt(br.readLine());
        c = new char[k];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            c[i] = st.nextToken().charAt(0);
        }


        StringBuilder max = new StringBuilder();
        int i;
        for (i = 0; i <k ; i++) {
            int tmp = i;
            while (i < k && c[i] == '<') {
                i++;
            }
            for (int j = i; j >= tmp; j--) {
                max.append(9-j);
            }
        }
        if(max.length()!=k+1) max.append(9-i);
        bw.write(max.toString()+"\n");

        StringBuilder min = new StringBuilder();
        for (i = 0; i <k ; i++) {
            int tmp = i;
            while(i<k && c[i]=='>') {
                i++;
            }
            for (int j = i; j >=tmp ; j--) {
                min.append(j);
            }
        }
        if(min.length()!=k+1) min.append(i);

        bw.write(min.toString());
        bw.flush();

    }
}
