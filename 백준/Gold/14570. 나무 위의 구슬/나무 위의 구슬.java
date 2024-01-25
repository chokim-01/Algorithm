import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static Node[] node;

	static class Node {
		int num, cCount;
		Node left, right;

		public Node(int num) {
			// TODO Auto-generated constructor stub
			this.num = num;
		}

		public void add(int l, int r) {
			// TODO Auto-generated constructor stub
			if (l != -1)
				this.left = node[l];
			if (r != -1)
				this.right = node[r];
			cCount = (l == -1 ? 0 : 1) + (r == -1 ? 0 : 1);
		}
	}

	static int dfs(Node now, long k) {
		if (now.cCount == 0)
			return now.num;
		int ret = 0;
		if (now.cCount == 2) {
			if ((k & 1) == 1)
				ret = dfs(now.left, (k >> 1) + 1);
			else
				ret = dfs(now.right, k >> 1);
		} else if (now.left != null)
			ret = dfs(now.left, k);
		else if (now.right != null)
			ret = dfs(now.right, k);
		return ret;
	}

	// 2 4 2 5 | 2 4 2 5 | 2 4 2 5
	// left, right left, left, right right
	// l r 반복
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		node = new Node[N + 1];
		for (int i = 1; i <= N; i++)
			node[i] = new Node(i);
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			node[i].add(a, b);
		}
		long k = Long.parseLong(br.readLine());
		System.out.println(dfs(node[1], k));
	}
}