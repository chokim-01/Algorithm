import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int T, N, M;
	static char[][] map;
	static boolean[] visit;
	static int[] occupancy;
	static TreeSet<Integer>[] link;
	static HashMap<Integer, Node> town;
	static HashMap<Integer, String> townName;
	static int xCnt;

	static class Node {
		int x, y;
		int townNum;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int townNum) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		makeLink();
		find();
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static int[][] dxy = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	static void makeLink() {
		int[][] mapNum = new int[N][M];
		int count = 0;
		int count2 = 0;

		town = new HashMap<>();
		townName = new HashMap<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'x') {
					mapNum[i][j] = ++count;
					town.put(count, new Node(i, j));
				} else if (map[i][j] != '.') {
					++count2;
					StringBuilder sb = new StringBuilder();
					while (j < M && map[i][j] >= 65 && map[i][j] <= 90) {
						mapNum[i][j] = xCnt + count2;
						sb.append(map[i][j]);
						j++;
					}
					j--;
					townName.put(mapNum[i][j], sb.toString());
				}
			}
		}
		occupancy = new int[xCnt + count2 + 1];
		Arrays.fill(occupancy, -1);

		link = new TreeSet[100001];
		for (int i = 0; i < link.length; i++)
			link[i] = new TreeSet<>();
		for (int key : town.keySet()) {
			Node n = town.get(key);
			for (int d = 0; d < 8; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!mapChk(nx, ny) || mapNum[nx][ny] <= xCnt)
					continue;
				link[key].add(mapNum[nx][ny]);
			}
		}
	}

	static void find() {
		for (int i = 1; i <= xCnt; i++) {
			Arrays.fill(visit, false);
			dfs(i);
		}
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < occupancy.length; i++) {
			if (occupancy[i] == -1)
				continue;
			Node n = town.get(occupancy[i]);
			ans.append(n.x + 1).append(" ").append(n.y + 1).append(" ").append(townName.get(i)).append("\n");
		}
		System.out.println(ans);
	}

	static boolean dfs(int now) {
		if (visit[now])
			return false;
		visit[now] = true;
		for (int next : link[now]) {
			if (occupancy[next] == -1 || dfs(occupancy[next])) {
				occupancy[next] = now;
				return true;
			}
		}
		return false;
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[100001];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'x')
					xCnt++;
			}
		}
	}
}