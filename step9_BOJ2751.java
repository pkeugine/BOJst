import java.io.*;

/**
 * 문제 : 수 정렬하기
 * 입력 : 숫자의 개수 N ( 1<=N<= 1,000,000 )
 * 출력 : 오름차 순 결과
 * 힙 소트
 * - 힙 구조 상 루트노드에 가장 큰 값이 존재함을 이용
 */

public class step9_BOJ2751 {

    static int N;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        num = new int[N];

        for(int i=0;i<N;i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        heapSort(num);
        for(int i=0;i<N;i++) {
            bw.write(num[i]+"\n");
        }
        bw.flush();
    }

    static public void heapSort(int[] array) { // 힙 정렬

        int n = array.length;

        for(int i=n/2-1; i>=0; i--) { // 리프 노드를 제외한 마지막 노드부터
            heapify(array,n,i); // 힙 구현
        }
        for(int i=n-1; i>0; i--) { // 루트 노드를 맨 뒤로 보내고 맨 뒤의 배열을 제외하고 힙 구현
            swap(array,0,i);
            heapify(array,i,0);
        }
    }

    static public void heapify(int[] array, int n, int i) {
        int p = i;
        int l = i * 2 + 1;
        int r = i * 2 + 2;

        if ( l<n && array[p]<array[l] ) {
            p = l;
        }

        if ( r<n && array[p]<array[r] ) {
            p = r;
        }

        if (i != p) {
            swap(array, p, i);
            heapify(array, n, p); // 재귀
        }
        // 셋 중 가장 큰 값이 위로
    }

    static public void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

}