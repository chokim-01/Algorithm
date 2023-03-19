import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, T;
	static ArrayList<Integer> con[];
	static int[] jobs;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// M : human, N : books
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		init();

		// idx : p_num, val : books
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			setCon(i, st);
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			visit = new boolean[N];
			if (dfs(i))
				ans++;
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			visit = new boolean[N];
			if (dfs(i)) {
				ans++;
				count++;
			}
			if (count == K)
				break;
		}
		System.out.println(ans);
	}

	static boolean dfs(int human) {
		if(visit[human])
			return false;
		visit[human] = true;

		for (int job : con[human]) {
			if (jobs[job] == -1 || dfs(jobs[job])) {
				jobs[job] = human;
				return true;
			}
		}
		return false;
	}

	static void setCon(int idx, StringTokenizer st) {
		st.nextToken();
		while (st.hasMoreTokens())
			con[idx].add(Integer.parseInt(st.nextToken()) - 1);
	}

	static void init() {
		jobs = new int[M];
		Arrays.fill(jobs, -1);

		visit = new boolean[N];
		con = new ArrayList[N];
		for (int i = 0; i < N; i++)
			con[i] = new ArrayList<>();
	}
}