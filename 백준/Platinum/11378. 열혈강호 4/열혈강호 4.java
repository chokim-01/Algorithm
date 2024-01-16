import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, M, K, T;
	static int s, m, e, ans;
	static int[] parent;
	static int[][] capacity;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input(br);
		edKarf();
		System.out.println(ans);
	}

	static void edKarf() {
		while (true) {
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1;
			Queue<Integer> q = new ArrayDeque<>();
			q.add(s);

			outer: while (!q.isEmpty()) {
				int now = q.poll();
				for (int next = 0; next < parent.length; next++) {
					if (parent[next] != -1 || capacity[now][next] == 0)
						continue;
					parent[next] = now;
					if (next == e)
						break outer;
					q.add(next);
				}
			}
//			System.out.println(Arrays.toString(parent));
			if (parent[e] == -1)
				break;
			ans++;
			int min = Integer.MAX_VALUE;
			for (int now = e; now != s; now = parent[now])
				min = Math.min(min, capacity[parent[now]][now]);
			for (int now = e; now != s; now = parent[now]) {
				capacity[parent[now]][now] -= min;
				capacity[now][parent[now]] += min;
			}
		}
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = N + M + 3;
		s = N + M;
		m = s + 1;
		e = m + 1;
		ans = 0;
		// s,m,e
		// N+M+3, s,m,e
		parent = new int[T];
		capacity = new int[T][T];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while (st.hasMoreTokens()) {
				int n = N + Integer.parseInt(st.nextToken()) - 1;
				capacity[i][n] = 1;
			}
		}
		for (int i = 0; i < N; i++) {
			capacity[s][i] = 1;
			capacity[m][i] = 123123;
		}
		for (int i = N; i < N + M; i++)
			capacity[i][e] = 1;
		capacity[s][m] = K;
		for (int i = 0; i < N; i++)
			capacity[m][i] = K;
	}
}
