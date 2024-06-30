import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    static int N;

    static class Node {
        int idx, num;

        Node(int i, int n) {
            this.idx = i;
            this.num = n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] ans = IntStream.range(0, N).map(x -> -1).toArray();

        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek().num < nums[i])
                ans[stack.pop().idx] = nums[i];
            stack.add(new Node(i, nums[i]));
        }
        StringBuilder sb = new StringBuilder();
        for (int a : ans)
            sb.append(a).append(" ");
        System.out.println(sb);
    }
}
//10
// 