import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M;
	static char[][] map;
	static List<Node> prisoner;
	static int[][][] countOfMap;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int x, y, cnt;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int c) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.cnt = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringBuilder ans = new StringBuilder();
		while (T-- > 0) {
			input(br);

			move();

//			print();

			ans.append(getAns()).append("\n");
		}
		System.out.println(ans);
	}

	static void print() {
		for (int i = 0; i < 3; i++) {
			System.out.println("case : " + i);
			for (int j = 0; j < N + 2; j++) {
				for (int k = 0; k < M + 2; k++)
					System.out.print(countOfMap[i][j][k] == 50000 ? "# " : countOfMap[i][j][k] + " ");
				System.out.println();
			}
			System.out.println();
		}
	}

	static int getAns() {
		int ret = Integer.MAX_VALUE;
		for (int j = 1; j <= N; j++) {
			for (int k = 1; k <= M; k++) {
				int c = 0;
				for (int i = 0; i < 3; i++)
					c += countOfMap[i][j][k];
				if (map[j][k] == '#')
					c -= 2;
				ret = ret < c ? ret : c;
			}
		}
		return ret;
	}

	static void move() {
		for (int i = 0; i < 3; i++) {
			Node n = prisoner.get(i);

			countOfMap[i][n.x][n.y] = 0;
			Queue<Node> q = new ArrayDeque<>();
			q.add(new Node(n.x, n.y, 0));

			while (!q.isEmpty()) {
				n = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = n.x + dxy[d][0];
					int ny = n.y + dxy[d][1];
					if (!mapChk(nx, ny) || map[nx][ny] == '*')
						continue;
					int nc = map[nx][ny] == '#' ? n.cnt + 1 : n.cnt;
					if (countOfMap[i][nx][ny] <= nc)
						continue;
					countOfMap[i][nx][ny] = nc;
					q.add(new Node(nx, ny, nc));
				}
			}
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N + 2 || y >= M + 2)
			return false;
		return true;
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N + 2][M + 2];

		prisoner = new ArrayList<>();
		prisoner.add(new Node(0, 0));

		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = s.charAt(j - 1);
				if (map[i][j] == '$')
					prisoner.add(new Node(i, j));
			}
		}
		countOfMap = new int[3][N + 2][M + 2];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < N + 2; j++)
				Arrays.fill(countOfMap[i][j], 10000);
	}
}
