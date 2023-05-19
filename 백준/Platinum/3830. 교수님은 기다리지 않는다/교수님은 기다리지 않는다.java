import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[][] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;
			parent = new long[N + 1][2];
			for (int i = 1; i < parent.length; i++)
				parent[i][0] = i;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				if (st.nextToken().equals("!")) {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());

					union(a, b, w);

				} else {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int fa = find(a);
					int fb = find(b);
					if (fa != fb) {
						ans.append("UNKNOWN").append("\n");
						continue;
					}
					ans.append(parent[b][1] - parent[a][1]).append("\n");
				}
			}
		}
		System.out.println(ans);
	}

	static void union(int a, int b, int w) {
		int fa = find(a);
		int fb = find(b);

		if (fa == fb)
			return;
		parent[fb][1] = parent[a][1] - parent[b][1] + w;
		parent[fb][0] = fa;
	}

	static int find(int a) {
		if (a == parent[a][0])
			return a;
		int fa = find((int) parent[a][0]);
		parent[a][1] += parent[(int) parent[a][0]][1];

		return (int) (parent[a][0] = fa);
	}
}
