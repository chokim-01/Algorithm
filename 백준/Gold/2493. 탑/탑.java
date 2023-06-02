import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {
	static class Node {
		int n, index;

		public Node(int n, int i) {
			// TODO Auto-generated constructor stub
			this.n = n;
			this.index = i;
		}

		@Override
		public String toString() {
			return "[ " + this.n + "," + this.index + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		br.readLine();
		int[] numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Stack<Node> s = new Stack<>();
		s.add(new Node(Integer.MAX_VALUE, 0));

		for (int i = 0; i < numbers.length; i++) {
			while (s.peek().n < numbers[i])
				s.pop();

			ans.append(s.peek().index + " ");
			s.add(new Node(numbers[i], i + 1));
		}
		System.out.println(ans);
	}
}