import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, T;
	static ArrayList<Integer> con[];
	static int[] books;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		// M : human, N : books
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			init();

			// idx : p_num, val : books
			for (int i = 0, a, b; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken()) - 1;
				b = Integer.parseInt(st.nextToken()) - 1;
				setCon(i, a, b);
			}
			int count = 0;
			for (int i = 0; i < M; i++) {
				Arrays.fill(visit, false);
				if (dfs(i))
					count++;
			}
			sb.append(count+"\n");
		}
		System.out.println(sb);
	}

	static boolean dfs(int human) {
		if (visit[human])
			return false;
		visit[human] = true;
		for (int book : con[human]) {
			if (books[book] == -1 || dfs(books[book])) {
				books[book] = human;
				return true;
			}
		}

		return false;
	}

	static void setCon(int idx, int a, int b) {
		for (int i = a; i <= b; i++)
			con[idx].add(i);
	}

	static void init() {
		books = new int[N];

		visit = new boolean[M];
		Arrays.fill(books, -1);
		con = new ArrayList[M];
		for (int i = 0; i < M; i++)
			con[i] = new ArrayList<>();
	}
}