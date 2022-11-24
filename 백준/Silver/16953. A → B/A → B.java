import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static class Node implements Comparable<Node> {
		long x;
		int count;

		public Node(long x, int count) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.count - o.count;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(a, 1));
		int ans = -1;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.x == b) {
				ans = n.count;
				break;
			}
			if (n.x * 2 <= b)
				q.add(new Node(n.x * 2, n.count + 1));
			if (n.x * 10 + 1 <= b)
				q.add(new Node(n.x * 10 + 1, n.count + 1));
		}
		System.out.println(ans);
	}
}