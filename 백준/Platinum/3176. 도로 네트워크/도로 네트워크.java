import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	static boolean[] visit;
	static ArrayList<int[]>[] con;
	static int[][][] par;
	static int[] nodeDepth;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()) + 1;

		con = new ArrayList[N];

		par = new int[N][17][3];
		nodeDepth = new int[N];

		for (int i = 1; i < N; i++) {
			con[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 17; j++) {
				par[i][j][1] = Integer.MAX_VALUE;
				par[i][j][2] = -1;
			}
		}

		for (int i = 1, a, b, c; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			con[a].add(new int[] { b, c });
			con[b].add(new int[] { a, c });
		}

		visit = new boolean[N];
		visit[1] = true;
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		while (!stack.isEmpty()) {
			int now = stack.pop();

			for (int[] next : con[now]) {
				if (visit[next[0]])
					continue;
				visit[next[0]] = true;
				par[next[0]][0][0] = now;
				par[next[0]][0][1] = next[1];
				par[next[0]][0][2] = next[1];
				nodeDepth[next[0]] = nodeDepth[now] + 1;
				stack.push(next[0]);
			}
		}

		for (int j = 1; j < 17; j++) {
			for (int i = 1; i < N; i++) {
				par[i][j][0] = par[par[i][j - 1][0]][j - 1][0];
				par[i][j][1] = Math.min(par[par[i][j - 1][0]][j - 1][1], par[i][j - 1][1]);
				par[i][j][2] = Math.max(par[par[i][j - 1][0]][j - 1][2], par[i][j - 1][2]);
			}
		}

		int K = Integer.parseInt(br.readLine());

		for (int k = 0, a, b; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			if (nodeDepth[a] < nodeDepth[b]) {
				int tmp = 0;
				tmp = a;
				a = b;
				b = tmp;
			}
			int jump = 17;
			while (nodeDepth[a] != nodeDepth[b]) {
				if (Math.pow(2, jump) > nodeDepth[a] - nodeDepth[b]) {
					jump--;
					continue;
				}
				min = Math.min(par[a][jump][1], min);
				max = Math.max(par[a][jump][2], max);
				a = par[a][jump][0];
				jump--;
			}
			jump = 16;

			if (a != b) {
				while (jump != -1) {
					if(par[a][jump][0] == par[b][jump][0]) {
						jump--;
						continue;
					}
					min = Math.min(Math.min(par[a][jump][1], par[b][jump][1]), min);
					max = Math.max(Math.max(par[a][jump][2], par[b][jump][2]), max);
					a = par[a][jump][0];
					b = par[b][jump][0];
					jump--;

				}
				min = Math.min(Math.min(par[a][0][1], par[b][0][1]), min);
				max = Math.max(Math.max(par[a][0][2], par[b][0][2]), max);
			}
			bw.write(min + " " + max + "\n");
		}

		bw.flush();
		bw.close();

	}
}
