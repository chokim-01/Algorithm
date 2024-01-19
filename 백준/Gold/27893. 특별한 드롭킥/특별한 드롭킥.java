import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	static class Node implements Comparable<Node> {
		int x, count;
		boolean tag;

		public Node(int x, int count, boolean tag) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.count = count;
			this.tag = tag;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", count=" + count + ", tag=" + tag + "]";
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.count - o.count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String s = br.readLine();
		boolean[] go = new boolean[N + 1];
		for (int i = 1; i <= N; i++)
			go[i] = s.charAt(i - 1) == '.' ? true : false;

		int index = 0;
		ArrayList<Node> list = new ArrayList<>();
		int count = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 1; i <= N; i++) {
			if (go[i]) {
				count = stack.size();
				if (count != 0)
					list.add(new Node(index++, count, false));
				if (list.size() != 0 && list.get(index - 1).tag)
					list.get(index - 1).count += 1;
				else
					list.add(new Node(index++, 1, true));
				stack = new Stack<>();
			} else
				stack.push(i);
		}
		count = stack.size();
		if (count != 0)
			list.add(new Node(index++, count, false));
		if (list.size() == 1) {
			if (list.get(0).tag)
				System.out.println(N - (M >= 2 ? (M - 2) : 0));
			else
				System.out.println(2);
		} else {
			PriorityQueue<Node> q = new PriorityQueue<>();
			for (int i = 1; i < list.size() - 1; i++) {
				Node n = list.get(i);
				if (!n.tag)
					continue;
				q.add(n);
			}
			while (!q.isEmpty()) {
				Node n = q.poll();
				if (n.count > M)
					continue;
				M -= n.count;
				list.get(n.x).tag = false;
			}
			int ans = 0;
			boolean f = false;
			if (list.get(0).tag && list.get(0).count <= M) {
				list.get(0).tag = false;
				M -= list.get(0).count;
			}
			if (list.get(list.size() - 1).tag && list.get(list.size() - 1).count <= M) {
				list.get(list.size() - 1).tag = false;
				M -= list.get(list.size() - 1).count;
			}
			for (Node n : list) {
				if (!n.tag) {
					if (f)
						continue;
					f = true;
					ans += 2;
				} else {
					ans += n.count;
					f = false;
				}
			}
			System.out.println(ans - M);
		}
	}
}
//10
// 