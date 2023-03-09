import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] depth;
	static int[][] par;
	static int[][] parDist;
	static ArrayList<int[]> con[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		input(br, st);
		conNode();

		M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			bw.write(String.valueOf(getLcaDist(a, b)) + "\n");
		}
		bw.flush();
		bw.close();
	}

	static int getLcaDist(int a, int b) {
		int distance = 0;
		if (depth[a] < depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		// equal depth matching
		int dep = 17;
		while (--dep > -1 && depth[a] != depth[b]) {
			if (1 << dep > depth[a] - depth[b])
				continue;
			distance += parDist[a][dep];
			a = par[a][dep];
		}
		if (a == b)
			return distance;

		// find LCA
		dep = 17;
		while (--dep > -1) {
			if (par[a][dep] == par[b][dep])
				continue;
			distance += parDist[a][dep] + parDist[b][dep];
			a = par[a][dep];
			b = par[b][dep];
		}
		return distance + parDist[a][0] + parDist[b][0];
	}

	static void conNode() {
		Stack<Integer> s = new Stack<>();
		boolean[] visit = new boolean[N];
		visit[1] = true;
		s.add(1);
		while (!s.isEmpty()) {
			int now = s.pop();
			for (int[] next : con[now]) {
				if (visit[next[0]])
					continue;
				visit[next[0]] = true;
				par[next[0]][0] = now;
				parDist[next[0]][0] = next[1];
				depth[next[0]] = depth[now] + 1;
				s.add(next[0]);
			}
		}
		// set par
		for (int j = 1; j < 17; j++) {
			for (int i = 1; i < N; i++) {
				par[i][j] = par[par[i][j - 1]][j - 1];
				parDist[i][j] = parDist[par[i][j - 1]][j - 1] + parDist[i][j - 1];
			}
		}

	}

	static void input(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;

		depth = new int[N];
		con = new ArrayList[N];
		par = new int[N][17];
		parDist = new int[N][17];

		for (int i = 1; i < N; i++)
			con[i] = new ArrayList<>();
		for (int i = 1, a, b, c; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			con[a].add(new int[] { b, c });
			con[b].add(new int[] { a, c });
		}
	}
}