import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visit;
	static ArrayList<Integer>[] link;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new boolean[N];
		link = new ArrayList[N];
		for (int i = 0; i < N; i++)
			link[i] = new ArrayList<>();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		ans = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit, false);
			visit[i] = true;
			dfs(i, 0);
			if (ans == 1)
				break;
		}
		System.out.println(ans);
	}

	static void dfs(int now, int cnt) {
		if (cnt == 4) {
			ans = 1;
			return;
		}
		for (int next : link[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			dfs(next, cnt + 1);
			visit[next] = false;
		}
	}

}