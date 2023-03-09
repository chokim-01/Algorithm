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
	static ArrayList<Integer> con[];

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

			bw.write(String.valueOf(getLCA(a, b))+"\n");
		}
		bw.flush();
		bw.close();
	}

	static int getLCA(int a, int b) {
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
			a = par[a][dep];
		}
		if (a == b)
			return a;

		// find LCA
		dep = 17;
		while (--dep > -1) {
			if(par[a][dep] == par[b][dep])
				continue;
			a = par[a][dep];
			b = par[b][dep];
		}
		return par[a][0];
	}

	static void conNode() {
		Stack<Integer> s = new Stack<>();
		boolean[] visit = new boolean[N];
		visit[1] = true;
		s.add(1);
		while (!s.isEmpty()) {
			int now = s.pop();
			for (int next : con[now]) {
				if (visit[next])
					continue;
				visit[next] = true;
				par[next][0] = now;
				depth[next] = depth[now] + 1;
				s.add(next);
			}
		}
		// set par
		for (int j = 1; j < 17; j++)
			for (int i = 1; i < N; i++)
				par[i][j] = par[par[i][j - 1]][j - 1];

	}

	static void input(BufferedReader br, StringTokenizer st) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		par = new int[N][17];
		con = new ArrayList[N];
		depth = new int[N];

		for (int i = 1; i < N; i++)
			con[i] = new ArrayList<>();
		for (int i = 1, a, b; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			con[a].add(b);
			con[b].add(a);
		}
	}
}