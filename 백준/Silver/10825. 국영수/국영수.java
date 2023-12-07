import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		String name;
		int k, e, m;

		public Node(StringTokenizer st) {
			// TODO Auto-generated constructor stub

			this.name = st.nextToken();
			this.k = Integer.parseInt(st.nextToken());
			this.e = Integer.parseInt(st.nextToken());
			this.m = Integer.parseInt(st.nextToken());
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			if (this.k == o.k) {
				if (this.e == o.e) {
					if (o.m == this.m) {
						int min = Math.min(this.name.length(), o.name.length());
						for (int i = 0; i < min; i++)
							if (this.name.charAt(i) != o.name.charAt(i))
								return this.name.charAt(i) - o.name.charAt(i);
						return this.name.length() - o.name.length();
					}
					return o.m - this.m;
				}
				return this.e - o.e;
			}
			return o.k - this.k;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> q = new PriorityQueue<>();
		while (N-- > 0)
			q.add(new Node(new StringTokenizer(br.readLine())));

		StringBuilder ans = new StringBuilder();
		while (!q.isEmpty())
			ans.append(q.poll().name).append("\n");
		System.out.println(ans);
	}
}