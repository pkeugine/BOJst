/* 이은지
 * 6단계 4번 ) 음계
 * */
package java_algo;

import java.util.Scanner;

public class algo6_4{
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] arr = new int[8];
        int asc = 0, desc = 0;
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
            
            if (i+1 == arr[i]) {
                asc++;
            } else if (8-i == arr[i]) {
                desc++;
            }
        }
        sc.close();
        
        if (asc == 8) {
            System.out.println("ascending");
        } else if (desc == 8) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}