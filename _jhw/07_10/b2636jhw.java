/*
 * 문제 : 치즈
 * 입력 : 가로 세로 ( <= 100 ), 치즈
 * 출력 : 모두 녹는 데 걸리는 시간, 모두 녹기 직전 치즈 조각이 놓여 있는 칸의 개수
 * 개념 : dfs ( or bfs ), 이중
 * 생각
 * - 0 침투
 *
 *
 *
 * */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Pair> q;


//  1은 큐에 저장, 0은 모두 방문 표시
    static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if (visited[ny][nx]) continue;
            visited[ny][nx] = true;
            if (map[ny][nx] == 1) q.add(new Pair(nx, ny));
            else dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

//      입력
        int cheese=0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//      기본 바깥 치즈 저장
        dfs(0, 0);


//      시간 계산
        int cnt = 0;
        int size = 0;
        while (!q.isEmpty()) {
            size = q.size(); // 시간 구분 && 치즈 (1) 개수
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = pair.x + dx[j];
                    int ny = pair.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                    if (visited[ny][nx]) continue;
                    visited[ny][nx] = true;
                    if (map[ny][nx] == 1) q.add(new Pair(nx, ny)); // 1이면 저장
                    else dfs(nx, ny); // 0 이면 다시 침투
                }
            }
            cnt++; // 시간 세기
        }


        bw.write(cnt + "\n");
        bw.write(size + "\n");
        bw.flush();
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }
}
