/*
* 문제 : 바이러스
* 입력 : 컴퓨터의 수 ( <= 100 ), 쌍의 수, 번호 쌍
* 출력 : 1번 컴퓨터를 통해 바이러스에 걸리게 되는 컴퓨터의 수
* 개념 : dfs
* 생각
* - 양쪽 서로 연결
*
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2606 {

    static int n,m;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int count;

    static void dfs(int num) {
        visited[num] = true;
        for (int i = 0,size = list[num].size(); i <size ; i++) {
            int n = list[num].get(i);
            if(visited[n]) continue;
            dfs(n);
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        for (int i = 1; i <=n ; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i <m ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        count = 0;
        dfs(1);
        System.out.println(count);
    }
}
