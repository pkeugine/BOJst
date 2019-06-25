import java.util.Scanner;
public class nineDigits2577 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] digits = new int[10];
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        String product = Integer.toString(a*b*c);
        for(int i=0; i<10; i++)
            digits[i] = 0;
        for(int i=0; i<product.length(); i++) {
            if(product.charAt(i) == '0')
                digits[0] += 1;
            else if(product.charAt(i) == '1')
                digits[1] += 1;
            else if(product.charAt(i) == '2')
                digits[2] += 1;
            else if(product.charAt(i) == '3')
                digits[3] += 1;
            else if(product.charAt(i) == '4')
                digits[4] += 1;
            else if(product.charAt(i) == '5')
                digits[5] += 1;
            else if(product.charAt(i) == '6')
                digits[6] += 1;
            else if(product.charAt(i) == '7')
                digits[7] += 1;
            else if(product.charAt(i) == '8')
                digits[8] += 1;
            else if(product.charAt(i) == '9')
                digits[9] += 1;
        }
        for(int i=0; i<10; i++)
            System.out.println(digits[i]);
    }
