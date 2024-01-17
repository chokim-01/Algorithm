import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	static long[] dist;
	static LinkedList<int[]> list[];
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new LinkedList<>();
		for (int i = 0, a, b, c; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a].add(new int[] { b, c });
		}
		StringBuilder ans = new StringBuilder();
		if (!belman(1))
			ans.append("-1");
		else
			for (int i = 2; i <= N; i++)
				ans.append(dist[i] == Long.MAX_VALUE ? -1 : dist[i]).append("\n");
		System.out.println(ans);
	}

	static boolean belman(int x) {
		dist = new long[N + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[x] = 0;
		for (int i = 1; i <= N; i++) {
			for (int now = 1; now <= N; now++) {
				for (int[] next : list[now]) {
					if (dist[now] == Long.MAX_VALUE || dist[next[0]] <= dist[now] + next[1])
						continue;
					dist[next[0]] = dist[now] + next[1];
					if (i == N)
						return false;
				}
			}
		}
		return true;
	}
}
//10
// 