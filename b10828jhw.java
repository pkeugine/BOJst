/*
 * 문제 : 이항 쇼다운
 * 입력 : n, k ( <= 2^31-1 ), 0 0
 * 출력 : 이항 계수
 *
 *
 * */


import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ10828 {

    static int N;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Estack stack = new Estack();



    static int command(String com) {
        switch (com) {
            case "top":
                return stack.top();
            case "size":
                return stack.size();
            case "empty":
                return stack.empty();
            case "pop":
                return stack.pop();
            default:
                return -1;
        }
    }

    static void command(String com, int num) {
        switch (com) {
            case "push":
                stack.push(num);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Estack stack = new Estack();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (st.countTokens() > 1) {
                command(st.nextToken(), Integer.parseInt(st.nextToken()));
            } else {
                bw.write(command(st.nextToken()) + "\n");
            }
        }
        bw.flush();
    }
}

class Node {
    int value;
    Node next;

    Node() {
    }

    Node(int n) {
        this.value = n;
        this.next = null;
    }
}


class Estack {
    private Node top;
    private int size;

    Estack() {
        top = null;
        size = 0;
    }

    private boolean isEmpty() {
        if (top == null) return true;
        else return false;
    }

    public void push(int n) {
        Node node = new Node(n);
        Node pre = top;
        top = node;
        top.next = pre;
        size++;
    }



    public int pop() {
        if(isEmpty()) return -1;
        int result = top.value;
        top = top.next;
        size--;
        return result;
    }


    int top() {
        if (top == null) {
            return -1;
        }
        return top.value;
    }

    int size() {
        return size;
    }

    int empty() {
        if (top != null) {
            return 0;
        } else {
            return 1;
        }
    }

}