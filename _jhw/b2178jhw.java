/*
* 문제 : 미로 탐색
* 입력 : 정수 N,M ( 2 <= N, M <= 100 ), N줄의 M개 정수
* 출력 : 지나야 하는 최소 칸 수
* 개념 : BFS
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {

    static int N,M;
    static boolean[][] map;
    static Queue<Pair> q;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int count=1;

    static void bfs(Pair p) {
        map[p.y][p.x] = false;
        q.add(p);
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i <size ; i++) {
                Pair pair = q.poll();
                for (int j = 0; j <4 ; j++) {
                    int nx = pair.x + dx[j];
                    int ny = pair.y + dy[j];
                    if(nx==M-1 && ny==N-1) return;
                    if(nx<0 || ny<0 || nx>= M || ny>= N || !map[ny][nx]) continue;
                    map[ny][nx] = false;
                    q.add(new Pair(nx,ny));
//                    System.out.println(nx+", "+ny);
                }
            }
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        q = new LinkedList<>();

        for (int i = 0; i <N ; i++) {
            String str = br.readLine();
            for (int j = 0; j <M ; j++) {
                if(str.charAt(j)=='1') map[i][j] = true;
            }
        }

        bfs(new Pair(0,0));
        System.out.println(count+1);

    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
