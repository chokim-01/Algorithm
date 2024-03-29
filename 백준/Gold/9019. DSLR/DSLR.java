import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] v;
	static Queue<Node> q;

	static class Node {
		int num;
		StringBuilder route;

		public Node(int num, StringBuilder route) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.route = route;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			v = new boolean[10000];
			v[start] = true;
			q = new ArrayDeque<>();
			q.add(new Node(start, new StringBuilder()));
			outer: while (!q.isEmpty()) {
				Node n = q.poll();
				for (int d = 0; d < 4; d++) {
					int next = make(n.num, d);
					if (v[next])
						continue;
					v[next] = true;

					StringBuilder tsb = new StringBuilder(n.route);
					switch (d) {
					case 0:
						tsb.append('D');
						break;
					case 1:
						tsb.append('S');
						break;
					case 2:
						tsb.append('L');
						break;
					case 3:
						tsb.append('R');
						break;
					}
					if (next == end) {
						ans.append(tsb).append("\n");
						break outer;
					}
					q.add(new Node(next, tsb));
				}
			}
		}
		System.out.println(ans);

	}

	static int make(int v, int d) {
		if (d == 0)
			return v * 2 % 10000;
		else if (d == 1)
			return v == 0 ? 9999 : v - 1;
		else if (d == 2) {
			int n = v / 1000;
			v %= 1000;
			return v * 10 + n;
		} else {
			int n = v % 10;
			return v / 10 + n * 1000;
		}
	}
}