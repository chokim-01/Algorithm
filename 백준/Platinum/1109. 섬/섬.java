import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static int[] ans;
	static int[][] map;
	static boolean[][] visit;
	static Queue<Node> list[];
	static ArrayList<Integer>[] childs;
	static boolean[][] mapOrigin;
	static int[][] dxy = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M];
		mapOrigin = new boolean[N][M];
		list = new ArrayDeque[1000];
		for (int i = 0; i < 1000; i++)
			list[i] = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			char[] s = br.readLine().toCharArray();
			for (int j = 0; j < M; j++)
				mapOrigin[i][j] = s[j] == 'x' ? true : false;
		}
		int count = makeMap() + 1;
		ans = new int[count];
		childs = new ArrayList[count];
		for (int i = 0; i < count; i++)
			childs[i] = new ArrayList<Integer>();

		setParents();
		for (int i = 1; i < count; i++)
			ans[dfs(i, 0)]++;

		if (ans[0] == 0)
			System.out.println(-1);
		else {
			StringBuilder sb = new StringBuilder();
			for (int a : ans) {
				if (a == 0)
					continue;
				sb.append(a + " ");
			}
			System.out.println(sb);
		}

	}

	static int dfs(int now, int num) {
		if (childs[now].isEmpty())
			return num;
		num += 1;
		for (int next : childs[now])
			num = Math.max(dfs(next, num), num);
		return num;
	}

	static void setParents() {
		boolean[][] visit;
		int islandCount = 0;
		for (Queue<Node> q : list) {
			if (q.isEmpty())
				continue;
			islandCount++;
			Node start = q.peek();
			visit = new boolean[N][M];
			int min = 2500;

			outer: while (!q.isEmpty()) {
				Node n = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = n.x + dxy[d][0];
					int ny = n.y + dxy[d][1];
					if (!mapChk(nx, ny)) {
						min = -1;
						break outer;
					}

					if (visit[nx][ny] || map[nx][ny] == map[start.x][start.y])
						continue;
					if (map[nx][ny] != 0) {
						min = Math.min(min, map[nx][ny]);
						continue;
					}
					visit[nx][ny] = true;
					q.add(new Node(nx, ny));
				}
			}
			if (min != -1)
				childs[min].add(islandCount);
		}

	}

	static int makeMap() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j] || !mapOrigin[i][j])
					continue;
				bfs(i, j, ++count);
			}
		}
		return count;
	}

	static void bfs(int x, int y, int count) {
		map[x][y] = count;
		visit[x][y] = true;
		list[count].add(new Node(x, y));

		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y));

		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int d = 0; d < 8; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!mapChk(nx, ny))
					continue;
				if (visit[nx][ny] || !mapOrigin[nx][ny])
					continue;
				visit[nx][ny] = true;
				map[nx][ny] = count;
				q.add(new Node(nx, ny));
				list[count].add(new Node(nx, ny));
			}
		}

	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[ " + x + " , " + y + " ] ";
		}
	}
}