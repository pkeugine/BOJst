import java.io.*;

/**
 * 문제 : 수 정렬하기
 * 입력 : 숫자의 개수 N ( 1<=N<= 1,000,000 )
 * 출력 : 오름차 순 결과
 * 머지 소트
 * - 쪼갠 후 정렬, 합병
 * - 링크드 리스트에 효율적
 */

public class step9_BOJ2751_1 {

    static int N;
    static int[] num;
    static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        sorted = new int[N];

        for(int i=0;i<N;i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        new step9_BOJ2751_1().mergeSort(num,0,N-1);

        for(int n : num) {
            bw.write(n+"\n");
        }
        bw.flush();
    }

    public void mergeSort(int[] array, int left, int right) { // 쪼갠 후 정렬
        if(left<right) {
            int mid = (left + right) /2;
            // 쪼개기
            mergeSort(array,left,mid);
            mergeSort(array,mid+1,right);
            // 정렬
            merge(array, left, mid, right);
        }
    }

    public void merge(int[] array, int left, int mid, int right) {

        int i = left, j = mid+1, k = left;

        while (i <=mid && j <= right) {
            if (array[i] <= array[j]) sorted[k++] = array[i++];
            else sorted[k++] = array[j++];
        }
        // 남아 있는 수 채우기
        while (i <= mid) {
            sorted[k++] = array[i++];
        }
        while (j <= right) {
            sorted[k++] = array[j++];
        }

        for(int n=left;n<=right;n++) { // 재복사
            array[n] = sorted[n];
        }
    }
}
