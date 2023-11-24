import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static boolean[] v;
	static PriorityQueue<Integer>[] link;
	static long[] ans;
	static long[] ans2;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);

		v[R] = true;
		dfs(R, 0);
		print();

	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		long sum = 0;
		for (int i = 1; i <= N; i++)
			sum += ans[i] * ans2[i];
		sb.append(sum);
		System.out.println(sb);
	}

	static void dfs(int now, int count) {
		ans2[now] = cnt++;
		ans[now] = count;

		while (!link[now].isEmpty()) {
			int next = link[now].poll();
			if (v[next])
				continue;
			v[next] = true;
			dfs(next, count + 1);
		}

	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		v = new boolean[N + 1];
		link = new PriorityQueue[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new PriorityQueue();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		ans = new long[N + 1];
		ans2 = new long[N + 1];
		Arrays.fill(ans, -1);
		cnt = 1;
	}
}