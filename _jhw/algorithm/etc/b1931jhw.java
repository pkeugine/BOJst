/*
* 문제 : 회의실배정
* 입력 : 회의실의 수 N ( 1 <= N <= 100,000 ), 회의 정보 ( <= 2^31-1 )
* 출력 : 최대 사용할 수 있는 회의 수
* 개념 : 그리디, 정렬, (우선순위 큐)
* 생각
* - 끝시간이 가장 빠른 회의 선택
*
*
*
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ1931 {

    static int N;
    static int[][] C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Pair> list = new ArrayList<>();


        for (int i = 0; i <N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }


        Collections.sort(list, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.end>o2.end) return 1;
                else if(o1.end==o2.end) {
                    return o1.start-o2.start;
                }
                else return -1;
            }
        });

        int start = list.get(0).end;
        int result = 1;
        for (int i = 1; i <N ; i++) {
            if(start<=list.get(i).start) {
                start = list.get(i).end;
                result++;
            }
        }
        System.out.println(result);
    }


    static class Pair {
        int start,end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
