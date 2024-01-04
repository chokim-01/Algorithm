import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static class Node implements Comparable<Node> {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Integer> q = new PriorityQueue<>();
		ArrayList<Node> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Node(a, b));
		}
		Collections.sort(list);
		for (Node n : list) {
			if (!q.isEmpty() && q.peek() <= n.x)
				q.poll();
			q.add(n.y);
		}
		System.out.println(q.size());
	}
}
