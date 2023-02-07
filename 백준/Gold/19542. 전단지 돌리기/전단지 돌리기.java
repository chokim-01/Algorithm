import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int ans = 0;
	static int N, S, D;
	static ArrayList<Integer> list[];
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		for (int i = 1, a, b; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		visit = new boolean[N + 1];
		visit[S] = true;
		dfs(S);
		System.out.println(ans <= 1 ? 0 : (ans - 1) * 2);

	}

	static int dfs(int now) {
		int power = 0;
		for (int next : list[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			power = Math.max(power, dfs(next));
		}
		if (power >= D)
			ans++;

		return power + 1;
	}
}
/*
 * 12 2 2 1 1 2 1 1 2 2 1 1 1 -2 2 1 -2 2 3
 * 
 * 5 1 1 2 1 2 -2 1 -1 1
 */
