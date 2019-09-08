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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 시스템 입력을 읽어 들임
        N = Integer.parseInt(br.readLine()); // 회의실 수
        ArrayList<Pair> list = new ArrayList<>(); // 회의 리스트 ( 시작 시간과 종료 시간 저장 )

//      회의 정보 입력 ( 회의 리스트에 저장 )
        for (int i = 0; i <N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

//      회의 리스트 정렬
        Collections.sort(list, new Comparator<Pair>() { // 정렬 조건 설정
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.end>o2.end) return 1; // 회의 종료 시간에 오름차 순
                else if(o1.end==o2.end) { // 회의 종료 시간이 같을 시
                    return o1.start-o2.start; // 회의 시간 시간에 오름차 순
                }
                else return -1;
            }
        });

//      회의 수 계산 ( 겹치지 않고 회의 종료 시간이 빠른 순, 같은 종료 시간 회의는 더 이른 시작 회의 먼저 )
        int start = list.get(0).end; // 회의실 회의 종료 시간
        int result = 1; // 회의 수
        for (int i = 1; i <N ; i++) {
            if(start<=list.get(i).start) { // 회의실 회의 종료 시간 <= 회의 시작 시간
                start = list.get(i).end; // 회의실 회의 종료 시간 갱신
                result++; // 회의 수 추가
            }
        }

//      회의 수 출력
        System.out.println(result);
    }

//  시작 시간과 종료 시간 저장 클래스
    static class Pair {
        int start,end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
