import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int T, N, M;
	static ArrayList<Integer>[] link;
	static int[] eCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			input(br);
			ans.append(topology()).append("\n");
		}
		System.out.println(ans);
	}

	static StringBuilder topology() {
		StringBuilder ret = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<>();
		// 차수 0
		int c = 0;
		for (int i = 1; i <= N; i++) {
			if (eCount[i] == 0) {
				q.add(i);
				c++;
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();
			ret.append(now).append(" ");

			for (int next : link[now]) {
				eCount[next]--;
				if (eCount[next] == 0) {
					q.add(next);
					c++;
				}
			}
		}
		if (c != N)
			return new StringBuilder("IMPOSSIBLE");
		return ret;
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		eCount = new int[N + 1];
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();

		int[] rank = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				link[rank[i]].add(rank[j]);
				eCount[rank[j]]++;
			}
		}
		M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (link[a].contains(b)) {
				link[a].remove((Integer) b);
				eCount[b]--;
				link[b].add(a);
				eCount[a]++;
			} else {
				link[b].remove((Integer) a);
				eCount[a]--;
				link[a].add(b);
				eCount[b]++;
			}
		}

	}
}