import java.io.*;
import java.util.Stack;

public class BOJ1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <n ; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        int re = 0;
        boolean flag = true;
        for (int i = 0; i <n ; i++) {
            if(num[i]<re) {
                if(num[i]==stack.peek()) {
                    stack.pop();
                    sb.append("-\n");
                } else {
                    flag = false;
                    break;
                }
            } else {
                for (int j = re+1; j <=num[i] ; j++) {
                    stack.push(j);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
                re = num[i];
            }
        }
        if(flag) System.out.println(sb);
        else System.out.println("NO");
    }
}
