import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] depth;
	static int[][] par;
	static long[][] parDist;
	static ArrayList<int[]>[] con;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()) + 1;

		depth = new int[N];
		par = new int[N][17];
		parDist = new long[N][17];

		con = new ArrayList[N];
		for (int i = 0; i < N; i++)
			con[i] = new ArrayList<>();

		for (int i = 1, a, b, c; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			con[a].add(new int[] { b, c });
			con[b].add(new int[] { a, c });
		}
		setParents();

		M = Integer.parseInt(br.readLine());
		for (int i = 0, a, b, k, n; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if (n == 1) {
				if (depth[a] < depth[b]) {
					int tmp = a;
					a = b;
					b = tmp;
				}
				// 깊이를 맞춤
				long ans = 0;
				int diff = 17;
				while (--diff > -1 && depth[a] != depth[b]) {
					if (1 << diff > depth[a] - depth[b])
						continue;
					ans += parDist[a][diff];
					a = par[a][diff];
				}
				// 같이 올라가기
				diff = 17;
				if (a != b) { // 끝 : 바로 위 조상이 정답
					while (--diff > -1) {
						if (par[a][diff] == par[b][diff])
							continue;
						ans += parDist[a][diff] + parDist[b][diff];
						a = par[a][diff];
						b = par[b][diff];
					}
					ans += parDist[a][0] + parDist[b][0];
				}
				sb.append(ans + "\n");

			} else { // u에서 v로 가는 경로에 존재하는 정점중 k번째 정점
				k = Integer.parseInt(st.nextToken()) - 1;
				// 부모 찾기
				int c = a;
				int d = b;
				if (depth[a] < depth[b]) {
					c = b;
					d = a;
				}

				int diff = 17;
				while (--diff > -1 && depth[c] != depth[d]) {
					if (1 << diff > depth[c] - depth[d])
						continue;
					c = par[c][diff];
				}
				// 같이 올라가기
				diff = 17;
				if (c != d) { // 끝 : 바로 위 조상이 정답
					while (--diff > -1) {
						if (par[c][diff] == par[d][diff])
							continue;
						c = par[c][diff];
						d = par[d][diff];
					}
					c = par[c][0];
				}
				// a에서 공통부모 까지의 거리
				// depth a - depth c(공통부모) 만큼 건너뜀
				if (depth[a] - depth[c] >= k) {
					diff = 17;
					while (--diff > -1 && a != c) {
						int p = 1 << diff;
						if (p > depth[a] - depth[c])
							continue;
						if (k < p)
							continue;
						a = par[a][diff];
						k -= p;
					}
					sb.append(a + "\n");
				} else {

					k = (depth[b] - depth[c]) - (k - (depth[a] - depth[c]));
					diff = 17;
					while (--diff > -1) {
						int p = 1 << diff;
						if (k > depth[b] - depth[c])
							continue;
						if (k < p)
							continue;
						b = par[b][diff];
						k -= p;
					}
					sb.append(b + "\n");
				}
			}

		}

		System.out.println(sb.toString());

	}

	static void setParents() {
		boolean[] visit = new boolean[N];
		visit[1] = true;
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		while (!stack.isEmpty()) {
			int now = stack.pop();
			for (int[] next : con[now]) {
				if (visit[next[0]])
					continue;
				visit[next[0]] = true;
				par[next[0]][0] = now;
				parDist[next[0]][0] = next[1];
				depth[next[0]] = depth[now] + 1;
				stack.push(next[0]);
			}
		}
		for (int j = 1; j < 17; j++) {
			for (int i = 1; i < N; i++) {
				par[i][j] = par[par[i][j - 1]][j - 1];
				parDist[i][j] = parDist[par[i][j - 1]][j - 1] + parDist[i][j - 1];
			}
		}
	}
}
