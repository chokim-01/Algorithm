import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.TreeSet;
import java.util.function.BiFunction;

public class Main {
	static int N, totK;
	static int sx, sy;
	static char[][] cMap;
	static int[][] map;
	static boolean[][] visit;
	static int[][] dxy = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static TreeSet<Integer> list;
	static BiFunction<Integer, Integer, Boolean> chk = (x, y) -> x >= 0 && x < N && y >= 0 && y < N;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {

		input();
		System.out.println(pointer());
	}

	static int pointer() {
		ArrayList<Integer> lst = new ArrayList<>(list);
		int ret = Integer.MAX_VALUE;
		int l = 0;
		int r = 0;
		while (l <= r && l != lst.size() && r != lst.size()) {

			int lv = lst.get(l);
			int rv = lst.get(r);
			if (map[sx][sy] < lv)
				break;
			if (rv < map[sx][sy]) {
				r++;
				continue;
			}
			if (bfs(lv, rv)) {
				ret = ret < rv - lv ? ret : rv - lv;
				l++;
			} else
				r++;
		}
		return ret;
	};

	static boolean bfs(int start, int end) {
		Queue<Node> q = new ArrayDeque<>();

		visit = new boolean[N][N];
		visit[sx][sy] = true;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] < start || map[i][j] > end)
					visit[i][j] = true;

		int k = 0;
		q.add(new Node(sx, sy));
		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int[] d : dxy) {
				int nx = n.x + d[0];
				int ny = n.y + d[1];
				if (!chk.apply(nx, ny) || visit[nx][ny])
					continue;

				if (cMap[nx][ny] == 'K')
					k++;

				if (totK == k)
					return true;

				visit[nx][ny] = true;

				q.add(new Node(nx, ny));
			}

		}
		return false;

	};

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		cMap = new char[N][N];
		totK = 0;
		sx = 0;
		sy = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				cMap[i][j] = s.charAt(j);
				if (cMap[i][j] == 'K')
					totK++;
				else if (cMap[i][j] == 'P') {
					sx = i;
					sy = j;
				}
			}
		}
		list = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				list.add(map[i][j]);
			}
		}
	}
}