import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

class Main {
	static int N, M, s, e;
	static int[] a, b, parent;
	static int[][] ans, capacity;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		s = N << 1;
		e = s | 1;
		M = (N << 1) + 2;
		parent = new int[M];
		ans = new int[N][N];
		capacity = new int[M][M];

		a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		b = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < N; i++) {
			capacity[s][i] = a[i];
			capacity[i + N][e] = b[i];
			for (int j = N; j < N << 1; j++)
				capacity[i][j] = 1;
		}
		エドワードエルリック();
		StringBuilder sb = new StringBuilder();
		if (!valid())
			sb.append(-1);
		else {
			sb.append(1).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					sb.append(ans[i][j]);
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}

	static boolean valid() {
		for (int i = 0; i < N; i++)
			for (int j = N; j < N << 1; j++)
				ans[i][j - N] = capacity[i][j] == 0 ? 1 : 0;
		for (int i = 0; i < N; i++) {
			int sumA = 0;
			int sumB = 0;
			for (int j = 0; j < N; j++) {
				sumA += ans[i][j];
				sumB += ans[j][i];
			}
			if (a[i] != sumA || b[i] != sumB)
				return false;
		}
		return true;
	}

	static void エドワードエルリック() {
		while (true) {
			for (int i = 0; i < M; i++)
				parent[i] = -1;
			Queue<Integer> q = new ArrayDeque<>();
			q.add(s);
			outer: while (!q.isEmpty()) {
				int now = q.poll();
				for (int next = 0; next < M; next++) {
					if (s == next || parent[next] != -1 || capacity[now][next] == 0)
						continue;
					parent[next] = now;
					if (next == e)
						break outer;
					q.add(next);
				}
			}
			if (parent[e] == -1)
				break;
			int min = Integer.MAX_VALUE;
			for (int now = e; now != s; now = parent[now])
				min = Math.min(min, capacity[parent[now]][now]);
			for (int now = e; now != s; now = parent[now]) {
				capacity[parent[now]][now] -= min;
				capacity[now][parent[now]] += min;
			}
		}
	}
}
