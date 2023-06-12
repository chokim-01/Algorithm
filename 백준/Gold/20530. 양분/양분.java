import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static boolean[] cycle;
	static boolean[] visit;

	static int[] degree;
	static int[] numbers;
	static ArrayList<Integer>[] link;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		degree = new int[N + 1];
		visit = new boolean[N + 1];
		link = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			link[i] = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
			degree[a]++;
			degree[b]++;
		}
		// cycle
		cycle = new boolean[N + 1];
		bfs();

		numbers = new int[N + 1];
		visit = cycle.clone();
		int number = 0;
		for (int i = 1; i <= N; i++) { // start : cycle
			if (!cycle[i])
				continue;
			visit[i] = true;
			dfs2(i, ++number);
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (numbers[a] == numbers[b])
				answer.append(1);
			else
				answer.append(2);
			answer.append("\n");
		}
		System.out.println(answer);
	}

	static void dfs2(int now, int n) {
		numbers[now] = n;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			dfs2(next, n);
		}

	}

	// cycle find
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; ++i) {
			cycle[i] = true;
			if (degree[i] == 1)
				q.add(i);
		}
		while (!q.isEmpty()) {
			int now = q.poll();
			visit[now] = true;
			cycle[now] = false;
			for (int next : link[now]) {
				degree[next]--;
				if (!visit[next] && degree[next] == 1) {
					q.add(next);
				}
			}
		}
	}
}