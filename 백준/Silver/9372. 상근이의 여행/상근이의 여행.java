import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			parent = IntStream.rangeClosed(0, N).map(x -> x).toArray();
			int ans = 0;
			while (M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (find(a) == find(b))
					continue;
				union(a, b);
				ans++;
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x < y)
			parent[x] = y;
		else
			parent[y] = x;
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
}