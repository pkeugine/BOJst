/*
* 문제 : 그림
* 입력 : 세로 n ( 1 <= n <= 500 ), 가로 m ( 1 <= m <= 500 ), 그림
* 출력 : 그림의 개수, 가장 넓은 그림 넓이
* 개념 : bfs
* 생각
* - 넓이 카운트 = 큐 카운트
*
*
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926 {

    static int n,m;
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static Queue<Pair> q;



    static int bfs(Pair pair) {
        int count=1;
        map[pair.y][pair.x]=0;
        q.add(pair);
        while(!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i <4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx <0 || ny<0 || nx >=m || ny >=n) continue;
                if(map[ny][nx]==0) continue;
                map[ny][nx] = 0;
                q.add(new Pair(nx,ny));
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        q = new LinkedList<>();
        
        for (int i = 0; i <n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        int max = 0;
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <m ; j++) {
                if(map[i][j]==0) continue;
                max = Math.max(max,bfs(new Pair(j,i)));
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }
    
    static class Pair {
        int x,y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
