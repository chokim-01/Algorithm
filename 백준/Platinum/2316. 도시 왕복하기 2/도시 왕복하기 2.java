import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
	static int N, P, s, e, ans;
	static int[] parent;
	static int[][] capacity;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);

		edKarf();
		System.out.println(ans);
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		parent = new int[(N + 1) << 1];
		capacity = new int[(N + 1) << 1][(N + 1) << 1];
		ans = 0;
		s = 3;
		e = 4;
		while (P-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			capacity[a << 1 | 1][b << 1] = capacity[b << 1 | 1][a << 1] = 1;
		}
		for (int i = 4; i < parent.length; i += 2)
			capacity[i][i | 1] = 1;

	}

	static void edKarf() {
		while (true) {
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1;
			Queue<Integer> q = new ArrayDeque<>();
			q.add(s);
			outer: while (!q.isEmpty()) {
				int now = q.poll();
				for (int next = 4; next < parent.length; next++) {
					if (parent[next] != -1 || capacity[now][next] == 0)
						continue;
					parent[next] = now;
					if (next == e)
						break outer;
					q.add(next);
				}
			}
			if (parent[e] == -1)
				break;
			ans++;
			for (int now = e; now != s; now = parent[now]) {
				capacity[parent[now]][now] -= 1;
				capacity[now][parent[now]] += 1;
			}
//			for (int[] n : capacity)
//				System.out.println(Arrays.toString(n));
//			System.out.println();
		}
	}
}
