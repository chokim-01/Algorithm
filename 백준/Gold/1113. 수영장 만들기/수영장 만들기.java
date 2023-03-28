import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, ans;
	static int[][] map;
	static boolean[][] visitOrigin;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;

		map = new int[N][M];
		visitOrigin = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			visitOrigin[i][0] = true;
			visitOrigin[i][M - 1] = true;
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - 48;
				visitOrigin[0][j] = true;
				visitOrigin[N - 1][j] = true;
			}
		}

		makeCantIn();

		for (int i = 1; i < 9; i++)
			findGroupNumber(i);

		System.out.println(ans);
	}

	static void findGroupNumber(int num) {
		boolean[][] visit = new boolean[N][M];

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				if (map[i][j] != num || visitOrigin[i][j] || visit[i][j])
					continue;
				visit[i][j] = true;
				bfs(visit, i, j);
			}

		}
	}

	static void bfs(boolean[][] visit, int x, int y) {
		Stack<Node> stack = new Stack<>();
		Queue<Node> q = new ArrayDeque<>();
		Node n = new Node(x, y);

		stack.add(n);
		q.add(n);
		while (!q.isEmpty()) {
			Node now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dxy[d][0];
				int ny = now.y + dxy[d][1];

				if (!mapChk(nx, ny) || visit[nx][ny] || visitOrigin[nx][ny])
					continue;
				if (map[x][y] != map[nx][ny])
					continue;
				if (nx == 0 || ny == 0 || nx == N || ny == M)
					return;
				if (map[x][y] > map[nx][ny])
					return;
				visit[nx][ny] = true;
				Node nNode = new Node(nx, ny);
				q.add(nNode);
				stack.add(nNode);
			}
		}

		int min = 10;
		for (Node s : stack) {
			for (int d = 0; d < 4; d++) {
				int nx = s.x + dxy[d][0];
				int ny = s.y + dxy[d][1];
				if (!visit[nx][ny])
					min = min < map[nx][ny] ? min : map[nx][ny];
			}
		}
		if (min - map[x][y] <= 0)
			return;
		ans += stack.size() * (min - map[x][y]);
		while (!stack.isEmpty()) {
			Node s = stack.pop();
			map[s.x][s.y] = min;
		}
	}

	static void makeCantIn() {
		Queue<Node> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (visitOrigin[i][j])
					q.add(new Node(i, j));

		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];

				if (!mapChk(nx, ny) || visitOrigin[nx][ny])
					continue;
				if (map[n.x][n.y] > map[nx][ny])
					continue;
				visitOrigin[nx][ny] = true;
				q.add(new Node(nx, ny));
			}
		}

	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}