/*
* 문제 : 숨바꼭질
* 입력 : 위치 N, K ( 0 <= N, K <= 100,000 )
* 출력 : 가장 빠른 시간
* 개념 : bfs
* 생각
* - bfs
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {

    static int a,b;
    static int result;
    static boolean[] visited;
    static Queue<Integer> q;

    static void bfs(int num) {
        q.add(num);
        while(!q.isEmpty()){

            int size = q.size();
            for (int s = 0; s <size ; s++) {
                int n = q.poll();
                System.out.println("!"+n+", "+result);
                if(n==b) break;
                for (int i = -1; i <2 ; i=i+2) {
                    int m = n+i;
                    if(m==b) return;
                    if(m<=0 || m>100000 || visited[m]) continue;
                    visited[m] = true;
                    q.add(m);
                }
                if(2*n==b) return;
                if(2*n<=100000 && !visited[2*n]) {
                    visited[2*n] = true;
                    q.add(2*n);
                }
            }
            result++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        q = new LinkedList<>();
        visited = new boolean[100001];
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        if(a>=b) System.out.println(a-b);
        else {
            bfs(a);
            System.out.println(result+1);
        }

    }
}
