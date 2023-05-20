import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long ans = 0;
		Stack<int[]> stack = new Stack<>();
		while (N-- > 0) {
			int key = Integer.parseInt(br.readLine());

			int cnt = 1;
			while (!stack.isEmpty() && stack.peek()[0] <= key) {

				ans += stack.peek()[1];

				if (stack.peek()[0] == key)
					cnt += stack.peek()[1];

				stack.pop();
			}
			if (!stack.isEmpty())
				ans++;
			stack.add(new int[] { key, cnt });
		}
		System.out.println(ans);
	}
}
