import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {
	static final int N = 5;
	static int[][] map;
	static ArrayList<Integer> list;
	static ArrayList<Integer> stars;
	static ArrayList<ArrayList<Integer>> ansList;
	static int[][] save;
	static int min = Integer.MAX_VALUE;
	static int[] dt = { 1, -1, -5, 5 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		input(br);

		bfs();
		findAnsList();
		dfs(0);

		System.out.println(min);
	}

	static void init() {
		map = new int[N][N];
		save = new int[25][25];
		stars = new ArrayList<>();
		ansList = new ArrayList<>();
	}

	static void input(BufferedReader br) throws IOException {
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().chars().map(x -> x == '.' ? 0 : 1).toArray();
			for (int j = 0; j < N; j++)
				if (map[i][j] == 1)
					stars.add(24 - (i * N + j));
		}
	}

	static void findAnsList() {
		int count = stars.size();
		int max = 1 << 25;
		for (int i = 1; i < max; i++) {
			int sum = 0;
			for (int j = 0, k; j < 25 && sum <= count; j++) {
				k = 1 << j;
				if ((i & k) == k)
					sum++;
			}
			if (sum == count)
				if (matched(i))
					ansList.add(list);
		}
	}

	static void dfs(int depth) {
		if (depth == stars.size()) {
			for (int i = 0; i < ansList.size(); i++) {
				int c = 0;
				for (int j = 0; j < stars.size(); j++) {
					int a = ansList.get(i).get(j);
					int b = stars.get(j);
					c += save[a][b];
				}
				min = Math.min(c, min);
			}
		}
		for (int i = depth; i < stars.size(); i++) {
			swap(depth, i);
			dfs(depth + 1);
			swap(depth, i);
		}
	}

	static void swap(int x, int y) {
		int tmp = stars.get(x);
		stars.set(x, stars.get(y));
		stars.set(y, tmp);
	}

	static void bfs() {
		int[][] dxy = { { 1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean[][] v = new boolean[N][N];
				Queue<int[]> q = new ArrayDeque<>();
				q.add(new int[] { i, j, 0 });
				v[i][j] = true;
				while (!q.isEmpty()) {
					int[] n = q.poll();
					for (int d = 0; d < 3; d++) {
						int nx = n[0] + dxy[d][0];
						int ny = n[1] + dxy[d][1];
						if (ny < 0 || nx >= N || ny >= N || v[nx][ny])
							continue;
						v[nx][ny] = true;
						int a = 24 - (i * N + j);
						int b = 24 - (nx * N + ny);
						int c = n[2] + 1;
						save[a][b] = c;
						save[b][a] = c;
						q.add(new int[] { nx, ny, c });
					}
				}
			}
		}
	}

	// +1 -1 +5

	static boolean matched(int map) {
		list = new ArrayList<>();
		for (int i = 0; i < 25; i++)
			if ((map & 1 << i) == 1 << i)
				list.add(i);
		Set<Integer> v = new HashSet<>();
		Queue<Integer> q = new ArrayDeque<>();
		q.add(list.get(0));
		v.add(list.get(0));
		int c = 1;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int d = 0; d < 4; d++) {
				if (!mapChk(dt[d], now))
					continue;
				int next = now + dt[d];
				if (v.contains(next) || ((1 << next) & map) != 1 << next)
					continue;
				v.add(next);
				c++;
				q.add(next);
			}
		}
		if (c != stars.size())
			return false;
		return true;
	}

	static boolean mapChk(int d, int x) {
		if (d == -1 && x % 5 == 0)
			return false;
		if (d == 1 && (x + 1) % 5 == 0)
			return false;
		if (d == -5 && x < 5)
			return false;
		if (d == 5 && x > 19)
			return false;
		return true;
	}
}