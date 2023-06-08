import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return this.x + " " + this.y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		Stack<Node> stack = new Stack<>();

		int width = 0;
		long answer = 0;
		st = new StringTokenizer(br.readLine());
		while (width <= N) {

			int height = !st.hasMoreTokens() ? 0 : Integer.parseInt(st.nextToken());
			int nextIdx = width;
			int nextHeight = height;

			while (!stack.isEmpty() && stack.peek().y >= height) {
				nextIdx = stack.peek().x;
				nextHeight = stack.peek().y;

				long ansHeight = (long) (width - nextIdx) * nextHeight;
				answer = Math.max(ansHeight, answer);
				stack.pop();
			}

			stack.push(new Node(nextIdx, height));
			width++;
		}
		System.out.println(answer);

	}
}