/*
 * 문제 : DFS와 BFS
 * 입력 : 정점의 개수 N ( 1 <= N <= 1,000 ), 간선의 개수 M ( 1 <= M <= 10,000 ), 탐색 시작 번호 V, 두 정점의 번호
 * 출력 : DFS, BFS 수행 결과
 * 개념 : DFS, BFS, 정렬
 * 생각
 * - dfs : stack, bfs : queue
 * - 간선 연결 : ArrayList[정점] = { 연결되 정점,...}
 * - 작은 순부터 방문 : 정렬
 *
 * */

import java.io.*;
import java.util.*;

public class BOJ1260 {

    static int N, M, V;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static StringBuilder dsb;
    static StringBuilder bsb;
    static Queue<Integer> q;

    static void dfs(int num) {
        visited[num] = true;
        dsb.append(num).append(" ");
        int size = list[num].size();
        if (size == 0) return;
        for (int i = 0; i < size; i++) {
            int next = list[num].get(i);
            if (visited[next]) continue;

            dfs(next);
        }
    }


    static void bfs(int v) {
        visited[v] = true;
        bsb.append(v).append(" ");
        q.add(v);
        while (!q.isEmpty()) {
            int num = q.poll();
            int size = list[num].size();
            for (int i = 0; i < size; i++) {
                int next = list[num].get(i);
                if (visited[next]) continue;
                visited[next] = true;
                bsb.append(next).append(" ");
                q.add(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dsb = new StringBuilder();
        bsb = new StringBuilder();
        q = new LinkedList<>();


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }

        dfs(V);
        Arrays.fill(visited,false);
        bfs(V);
        System.out.println(dsb.toString());
        System.out.println(bsb.toString());

    }
}
