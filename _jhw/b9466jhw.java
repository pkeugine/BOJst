/*
* 문제 : 텀 프로젝트
* 입력 : 테스트 케이스의 수 T, 학생의 수 n ( 2 <= n <= 100,000 ), 선택한 학생들의 번호
* 출력 : 프로젝트 팀에 속하지 못한 학생 수
* 생각
* - 한 번 방문 하고 또 방문 한 곳이라면 스탑
*   - 스탑할 때 까지 이동한 거리 - 처음 방문했을 때 저장한 거리 = 팀 크기
* - dfs
*
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466 {

    static int T,n,result;
    static int[] a;
    static int[] visited; // 0 : 방문 X / 1: 첫 방문, / 2 : 팀 / 3 : 팀 X
    static int check;
    static boolean flag;

    static void dfs(int x) {
        if(visited[x] == 3 || visited[x] == 2) {
            return;
        }
        if(visited[x]==1) {
            check = x;
            flag = true;
            return;
        }
        visited[x] = 1;
        dfs(a[x]);
        if(flag) visited[x] = 2;
        else {
            visited[x] = 3;
            result++;
        }

        if(check==x) flag = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while(T-->0) {
            result=0;
            n = Integer.parseInt(br.readLine());
            a = new int[n+1];
            visited = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <=n ; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <=n ; i++) {
                flag = false;
                dfs(i);
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());

    }
}
