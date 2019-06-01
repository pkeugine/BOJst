/*
 * 문제 : 괄호
 * 입력 : 테스트 케이스 개수 T, 괄호 문자열
 * 출력 : 올바름 판단
 *
 * */


import java.io.*;
import java.util.Stack;

public class BOJ9012 {

//  ( push
//  ) pop
//  스택이 남아있거나 부족할 때

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            int i;
            for (i = 0; i <str.length() ; i++) {
                char ps = str.charAt(i);
                if(ps=='(') {
                    stack.add(str.charAt(i));
                } else {
                    if(stack.isEmpty()) {
                        break;
                    }
                    stack.pop();
                }
            }

            if(stack.isEmpty() && (i==str.length())) bw.write("YES"+"\n");
            else bw.write("NO"+"\n");
        }
        bw.flush();

    }


}
