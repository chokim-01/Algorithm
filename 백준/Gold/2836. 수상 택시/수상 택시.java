import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	static class Node implements Comparable<Node> {
		int s, d;

		public Node(int s, int d) {
			// TODO Auto-generated constructor stub
			this.s = s;
			this.d = d;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			if (this.d == o.d)
				return this.s - o.s;
			return this.d - o.d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Node> list = new ArrayList<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Node(a, b));
		}
		Collections.sort(list);
		long ans = M;
		int now = 0;
		for (Node n : list) {
			if (n.s <= n.d)
				continue;
			if (now >= n.d)
				if (now < n.s)
					ans += (n.s - now) << 1;
				else
					continue;
			else
				ans += (n.s - n.d) << 1;

			now = n.s;
		}
		System.out.println(ans);
	}
}
