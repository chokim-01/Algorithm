import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static ArrayList<Node> list, list2;

	static class Node {
		int idx, x, y;

		public Node(int idx, int x, int y) {
			// TODO Auto-generated constructor stub
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Node(i, a, b));
		}
		dfs(0, 0, new ArrayList<Node>());
		System.out.println(ans);
	}

	static int ans = Integer.MAX_VALUE;

	static void dfs(int idx, int cnt, ArrayList<Node> choice) {
		if (idx == N) {
			if (cnt == K) {
				int max = 0;
				for (Node n : list) {
					int min = Integer.MAX_VALUE;
					for (Node next : choice) {
						int v = Math.abs(n.x - next.x) + Math.abs(n.y - next.y);
						min = min > v ? v : min;
					}
					if (min != Integer.MAX_VALUE)
						max = max < min ? min : max;
				}
				ans = ans > max ? max : ans;
			}
			return;
		}
		choice.add(list.get(idx));
		dfs(idx + 1, cnt + 1, choice);
		choice.remove(choice.size() - 1);
		dfs(idx + 1, cnt, choice);
	}
}