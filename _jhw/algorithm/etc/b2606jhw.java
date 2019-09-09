/*
* 문제 : 바이러스
* 입력 : 컴퓨터 수 ( <= 100 ), 컴퓨터 쌍의 수, 컴퓨터 번호 쌍
* 출력 : 1번 컴퓨터를 통해 바이러스에 걸리는 컴퓨터 수
* 개념
*
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2606 {

    static int N,M;
    static int count;
    static List<Integer>[] list;
    static boolean[] visited;


    static void dfs(int start) {
//        System.out.println(start);
        visited[start] = true;
        count++;
        int size = list[start].size();
        for (int i = 0; i <size ; i++) {
            int next = list[start].get(i);
            if(visited[next]) continue;
            dfs(next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <=N ; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i <M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        dfs(1);
        System.out.println(count-1);
    }
}
