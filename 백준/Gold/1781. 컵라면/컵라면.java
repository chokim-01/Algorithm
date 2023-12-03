import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int deadLine, count;

		public Node(int d, int c) {
			// TODO Auto-generated constructor stub
			this.deadLine = d;
			this.count = c;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			if (this.deadLine == o.deadLine)
				return o.count - this.count;
			return this.deadLine - o.deadLine;
		}

		@Override
		public String toString() {
			return this.deadLine + " " + this.count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Node> q = new PriorityQueue<>();
		PriorityQueue<Integer> choice = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			q.add(new Node(a, b));
		}
		int ans = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (choice.size() < n.deadLine)
				choice.add(n.count);

			else if (choice.peek() < n.count) {
				choice.poll();
				choice.add(n.count);
			}
		}
		for (int c : choice)
			ans += c;
		System.out.println(ans);
	}
}