import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int[][][] intMap;
	static int T, N, M, K;
	static char[][][] map;
	static ArrayList<Integer>[] link;
	static int[] nums;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			input(br);
			int count = bfs();
			makeLink(count);
			nums = new int[count + 1];
			Arrays.fill(nums, -1);
			int ans = 0;
			for (int i = 1; i <= count; i++) {
                			v = new boolean[count + 1];

				if (dfs(i))
					ans++;
			}
			sb.append("Case #").append(tc).append(": ").append(count - ans).append("\n");
		}
		System.out.println(sb);
	}

	static boolean dfs(int now) {
		if (v[now])
			return false;
		v[now] = true;
		for (int next : link[now]) {
			if (nums[next] == -1 || dfs(nums[next])) {
				nums[next] = now;
				return true;
			}
		}
		return false;
	}

	static void makeLink(int count) {
		link = new ArrayList[count + 1];
		for (int i = 1; i <= count; i++)
			link[i] = new ArrayList<>();

		boolean[][] v2 = new boolean[count + 1][count + 1];
		for (int k = 1; k < K; k += 2) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (intMap[k][i][j] == 0)
						continue;
					for (int t = -1; t <= 1; t += 2) {
						if (!mapChk(k + t))
							continue;
						if (intMap[k + t][i][j] == 0 || v2[intMap[k][i][j]][intMap[k + t][i][j]])
							continue;
						v2[intMap[k][i][j]][intMap[k + t][i][j]] = true;
						link[intMap[k][i][j]].add(intMap[k + t][i][j]);
					}
				}
			}
		}
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int bfs() {
		boolean[][][] v = new boolean[K][N][M];
		int c = 0;
		Queue<Node> q;
		for (int k = 0; k < K; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (v[k][i][j] || map[k][i][j] == '#')
						continue;
					q = new ArrayDeque<>();
					v[k][i][j] = true;
					intMap[k][i][j] = ++c;
					q.add(new Node(i, j));
					while (!q.isEmpty()) {
						Node n = q.poll();
						for (int d = 0; d < 4; d++) {
							int nx = n.x + dxy[d][0];
							int ny = n.y + dxy[d][1];
							if (!mapChk(nx, ny) || v[k][nx][ny] || map[k][nx][ny] == '#')
								continue;
							v[k][nx][ny] = true;
							intMap[k][nx][ny] = c;
							q.add(new Node(nx, ny));
						}
					}
				}
			}
		}
		return c;
	}

	static boolean mapChk(int k) {
		if (k < 0 || k >= K)
			return false;
		return true;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		intMap = new int[K][N][M];
		map = new char[K][N][M];
		for (int i = 0; i < K; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = br.readLine().toCharArray();

	}
}
