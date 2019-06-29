/*
 * 문제 : 토마토
 * 입력 : 상자의 크기 M,N ( 2 <= M,N <= 1,000 ), 토마토 정보
 * 출력 : 최소 날짜
 * 개념 : bfs, 위치 리스트 저장
 * 생각
 * - bfs or dfs
 * - 출발점 : 저장
 * - 동시 출발
 *
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ7576 {

    static int N, M;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result;
    static Queue<Pair> q;
    static int zero;


    static void bfs() {
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = pair.x + dx[j];
                    int ny = pair.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                    if (map[ny][nx] != 0) continue;
                    map[ny][nx] = 1;
                    zero--;
                    q.add(new Pair(nx, ny));
                }
            }
            result++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) zero++;
                if (map[i][j] == 1) {
                    q.add(new Pair(j, i));
                }
            }
        }
        if (zero==0) {
            System.out.println(0);
            return;
        }
        bfs();


        if(zero>0) System.out.println(-1);
        else System.out.println(result-1);

    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }
}