/*
* 문제 : 단지번호붙이기
* 입력 : 지도의 크기 N ( 5 <= N <= 25 ), 지도 ( 0 : 빈 집, 1 : 집 )
* 출력 : 단지 수, 각 단지내 집의 수
* 개념 : bfs, 정렬
* 생각
* - bfs로 단지내 집의 수 count
* - bfs가 끝날때 단지 수 count
* - 완전 탐색 : visited 제외
* - 오름차순 : 리스트에 저장 후 정렬
* */

import java.io.*;
import java.util.*;

public class BOJ2667 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> list;

    static Queue<Pair> q = new LinkedList<>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static int bfs(int x, int y) {
        q.add(new Pair(x,y));
        visited[y][x] = true;
        int count = 0;
        while(!q.isEmpty()) {
            Pair pair = q.poll();
            count++;
            for (int i = 0; i <4 ; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                if(nx>=N || nx<0 || ny>=N || ny<0 || visited[ny][nx] || map[ny][nx]==0) continue;
                visited[ny][nx] = true;
                q.add(new Pair(nx,ny));
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        list = new ArrayList<>();

        for (int i = 0; i <N ; i++) {
            String str = br.readLine();
            for (int j = 0; j <N ; j++) {
                map[i][j] = Integer.parseInt(str.charAt(j)+"");
            }
        }

        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
                if(map[i][j]==1) {
                    if(visited[i][j]) continue;;
                    list.add(bfs(j,i));
                }
            }
        }

        Collections.sort(list);
        bw.write(list.size()+"\n");
        for (int i = 0; i <list.size() ; i++) {
            bw.write(list.get(i)+"\n");
        }
        bw.flush();
    }

    static class Pair {
        int x,y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
