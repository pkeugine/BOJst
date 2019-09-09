import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *
 * */
public class BOJ2839 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int hand = -1;
        int num = N/5;

        for(int i=num; i>=0; i--) {
            if((N-(i*5))%3 == 0) {
                hand = i + (N - (i * 5)) / 3;
                break;
            }
        }
        System.out.println(hand);
    }

}
