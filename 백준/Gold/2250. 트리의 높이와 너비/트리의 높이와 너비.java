import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int len;
	static int N;
	static int[] con[];
	static boolean[] visit;
	static int depths[][];

	public static void main(String[] args) throws IOException {
		input();

		int root = 1;
		for (int i = 1; i <= N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				visitDfs(i);
				root = i;
			}
		}
		dfs(root, 1);

		printAns();
	}

	static void printAns() {
		int max = 0;
		int maxDepth = 0;
		for (int i = 1; i <= N; i++) {
			if (max < depths[i][1] - depths[i][0] + 1) {
				max = depths[i][1] - depths[i][0] + 1;
				maxDepth = i;
			}
		}
		System.out.println(maxDepth + " " + max);
	}

	static void dfs(int now, int depth) {

		if (con[now][0] != -1) // left
			dfs(con[now][0], depth + 1);

		depths[depth][0] = Math.min(depths[depth][0], ++len);
		depths[depth][1] = Math.max(depths[depth][1], len);

		if (con[now][1] != -1) // right
			dfs(con[now][1], depth + 1);

	}
	/*
	 * 1 0 1 2 0 2 4 0 3 8 0 4
	 * 
	 */

	static void visitDfs(int now) {

		for (int next : con[now]) {
			if (next == -1 || visit[next])
				continue;
			visit[next] = true;
			visitDfs(next);
		}
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N + 1];
		con = new int[N + 1][2];
		depths = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			depths[i][0] = Integer.MAX_VALUE;
			depths[i][1] = Integer.MIN_VALUE;
		}

		len = 0;
		for (int i = 0, a, b; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			for (int k = 0; k < 2; k++) {
				b = Integer.parseInt(st.nextToken());
				con[a][k] = b;
			}
		}
	}
}