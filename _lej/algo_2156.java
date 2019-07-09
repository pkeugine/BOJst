/*
 * 동적할당 기초 / 백준 2156번 / 포도주
 */
package java_algo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo_2156{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        long[] input = new long[t+1];
        
        for(int i = 1; i <= t; i++) {
        	input[i] = Integer.parseInt(br.readLine());
        }
        
        long[] dp = new long[t+1];
        
        if(t == 1) {
        	dp[1] = input[1];
        }
        
        if(t > 1){
        	dp[2] = input[1] + input[2];
        }

        if(t > 2){
        	for(int i=3; i<= t; i++){
        		dp[i] = Math.max(dp[i-3]+input[i - 1] + input [i] //OXO
        				, Math.max(dp[i-2] + input[i], dp[i-1]) //OOX, XOO		
        			);
        	}
        }
        
        System.out.println(dp[t]);
    }
}
    
