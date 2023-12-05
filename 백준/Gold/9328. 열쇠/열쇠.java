import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[] keys;
	static boolean[][] v;
	static ArrayList<Node> occupancy;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input(br);
			setStartXY();
			ans.append(spread()).append("\n");
		}
		System.out.println(ans);
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int spread() {
		int ret = 0;

		while (true) {
			boolean flag = true;
			int size = occupancy.size();
			for (int i = 0; i < size; i++) {
				Node now = occupancy.get(i);
				for (int d = 0; d < 4; d++) {
					int nx = now.x + dxy[d][0];
					int ny = now.y + dxy[d][1];
					if (!mapChk(nx, ny) || v[nx][ny] || map[nx][ny] == '*')
						continue;
					if (map[nx][ny] >= 65 && map[nx][ny] <= 90) {
						if (keys[map[nx][ny] - 'A'])
							map[nx][ny] = '.';
						else
							continue;
					}
					if (map[nx][ny] >= 97) {
						keys[map[nx][ny] - 'a'] = true;
						map[nx][ny] = '.';
					}
					if (map[nx][ny] == '$') {
						ret++;
						map[nx][ny] = '.';
					}
					flag = false;
					v[nx][ny] = true;
					occupancy.add(new Node(nx, ny));
				}
			}

			if (flag)
				break;
		}
		return ret;
	}

	static void setStartXY() {
		for (int i = 0; i < N; i++) {
			if (!v[i][0]) {
				v[i][0] = true;
				occupancy.add(new Node(i, 0));
			}
			if (!v[i][M + 1]) {
				v[i][M + 1] = true;
				occupancy.add(new Node(i, M + 1));
			}
		}
		for (int i = 0; i < M; i++) {
			if (!v[0][i]) {
				v[0][i] = true;
				occupancy.add(new Node(0, i));
			}
			if (!v[N + 1][i]) {
				v[N + 1][i] = true;
				occupancy.add(new Node(N + 1, i));
			}
		}
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = new boolean[N + 2][M + 2];
		map = new char[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++)
				map[i][j] = s.charAt(j - 1);
		}
		// upper door
		// lower key
		occupancy = new ArrayList<>();
		keys = new boolean[26];
		String s = br.readLine();
		if (s.equals("0"))
			return;
		for (char c : s.toCharArray())
			keys[c - 'a'] = true;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x > N || y > M)
			return false;
		return true;
	}
}