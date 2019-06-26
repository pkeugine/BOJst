import java.util.Scanner;
public class nineNumbers2562 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        arr[0] = 0;
        int tempv = 0;
        int tempn = 0;
        for(int i=1; i<=9; i++) {
            arr[i] = sc.nextInt();
            if(arr[i] >= tempv) {
                tempv = arr[i];
                tempn = i;
            }
        }
        System.out.println(tempv);
        System.out.println(tempn);
    }
}
