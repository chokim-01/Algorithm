import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, T;
	static ArrayList<Integer> con[];
	static int[] barns;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// M : human, N : books
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		init();

		// idx : p_num, val : books
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			setCon(i, st);
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit, false);
			if (dfs(i))
				count++;
		}
		System.out.println(count);
	}

	static boolean dfs(int cow) {
		if (visit[cow])
			return false;
		visit[cow] = true;
		for (int barn : con[cow]) {
			if (barns[barn] == -1 || dfs(barns[barn])) {
				barns[barn] = cow;
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
		barns = new int[M];
		Arrays.fill(barns, -1);

		visit = new boolean[N];
		con = new ArrayList[N];
		for (int i = 0; i < N; i++)
			con[i] = new ArrayList<>();
	}
}