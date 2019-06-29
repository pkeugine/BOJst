/*
 * 문제 : 벽 부수고 이동하기
 * 입력 : N,M ( 1 <= N,M <= 1,000 ), NxM 맵
 * 출력 : 최단거리 출력 ( 불가능 -1 )
 * 개념 : bfs, 3차원 배열
 * 생각
 * - bfs
 * - 벽을 부실 수 있는 횟수 정보
 * - 중복된 경로 최대한 삭제
 * - 부시고 간 경로와 부시지 않고 간 경로 구분
 *
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

    static int N, M;
    static boolean[][] map;
    static boolean[][][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result;
    static boolean flag;
    static Queue<Pair> q;

    static void bfs(int x, int y) {
        q.add(new Pair(x, y, true));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                if (pair.y == N - 1 && pair.x == M - 1) {
                    flag = true;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = pair.x + dx[j];
                    int ny = pair.y + dy[j];
                    // 거르기
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue; // 범위 밖
                    if (pair.chance && visited[ny][nx][0]) continue; // 벽을 안 부신 경로 방문점
                    if (!pair.chance && (visited[ny][nx][0] || visited[ny][nx][1])) continue; // 벽을 부신 경로 방문점
                    if (!pair.chance && map[ny][nx]) continue; // 벽을 부실 수 없을 때

                    if (pair.chance) visited[ny][nx][0] = true;
                    else visited[ny][nx][1] = true;

                    if (map[ny][nx]) {
                        q.add(new Pair(nx, ny, false));
                    } else {
                        q.add(new Pair(nx, ny, pair.chance));
                    }
                }
            }
            result++;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        q = new LinkedList<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '1') map[i][j] = true;
            }
        }
        bfs(0, 0);

        if (flag) System.out.println(result + 1);
        else System.out.println(-1);

    }


    static class Pair {
        int x, y;
        boolean chance;

        Pair(int x, int y, boolean chance) {
            this.x = x;
            this.y = y;
            this.chance = chance;
        }
    }
}
