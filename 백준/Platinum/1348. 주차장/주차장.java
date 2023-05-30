import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class Main {
	static int N, M, cCount;
	static char[][] map;
	static boolean[] v;
	static boolean[][] visit;
	static int[] parents;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static ArrayList<Car>[] link;
	static BiFunction<Integer, Integer, Boolean> chk = (x, y) -> x >= 0 && x < N && y >= 0 && y < M;

	static class Node {
		int x, y, cnt;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int cnt) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static class Car {
		int eNum, cost;

		public Car(int e, int c) {
			// TODO Auto-generated constructor stub
			this.eNum = e;
			this.cost = c;
		}

		@Override
		public String toString() {
			return eNum + " " + cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		makeLink();
		System.out.println(binSearch());
	}

	static int ans = 0;

	static int binSearch() {
		int ret = -1;
		int l = 0;
		int r = 2500;
		while (l <= r) {
			int mid = (l + r) / 2;
			int match = 0;
			Arrays.fill(parents, -1);

			for (int i = 0; i < cCount; i++) {
				v = new boolean[cCount];
				if (dfs(i, mid))
					match++;
			}
			if (match == cCount) {
				ret = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return ret;
	}

	static boolean dfs(int now, int time) {
		if (v[now])
			return false;
		v[now] = true;
		for (Car n : link[now]) {
			if (n.cost > time)
				continue;
			if (parents[n.eNum] == -1 || dfs(parents[n.eNum], time)) {
				parents[n.eNum] = now;
				ans = ans < n.cost ? n.cost : ans;
				return true;
			}
		}
		return false;
	}

	static void makeLink() {
		ArrayList<Node> start = new ArrayList<>();
		int number = 10000;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'C')
					start.add(new Node(i, j));

				else if (map[i][j] == 'P')
					map[i][j] = (char) number++;

			}
		}
		cCount = start.size();
		parents = new int[number - 10000];
		link = new ArrayList[cCount];
		for (int i = 0; i < link.length; i++)
			link[i] = new ArrayList<>();

		int index = 0;
		for (Node n : start) {
			bfs(n, index);
			index++;
		}
	}

	static void bfs(Node now, int number) {
		visit = new boolean[N][M];
		Queue<Node> q = new ArrayDeque<>();
		visit[now.x][now.y] = true;
		q.add(new Node(now.x, now.y, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int[] d : dxy) {
				int nx = n.x + d[0];
				int ny = n.y + d[1];
				if (!chk.apply(nx, ny) || visit[nx][ny])
					continue;
				if (map[nx][ny] == 'X')
					continue;
				visit[nx][ny] = true;
				int ncnt = n.cnt + 1;
				if (map[nx][ny] >= 10000)
					link[number].add(new Car(map[nx][ny] - 10000, ncnt));
				q.add(new Node(nx, ny, ncnt));
			}
		}
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
	}
}