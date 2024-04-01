import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // 자리수 고정, 내림차순

        Stack<Integer> stack = new Stack<>();

        int N = sc.nextInt();
        int K = sc.nextInt();
        String nums = sc.next();

        int count = 0;
        for (int i = 0; i < N; i++) {
            int num = nums.charAt(i) - '0';
            while (!stack.isEmpty() && count < K && stack.peek() < num) {
                count++;
                stack.pop();
            }
            stack.push(num);
        }
        while(count++<K)
            stack.pop();
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.insert(0,stack.pop());
        System.out.println(sb);
    }
}