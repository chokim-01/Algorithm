import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x, y;

		public Node() {
			// TODO Auto-generated constructor stub
			this.x = -1;
			this.y = 99999;
		}

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + this.x + "," + this.y + ") ";
		}
	}

	static int N, M, P;
	static int[][] map;
	static int[] SofPlayer;
	static Node[][] mapSave;
	static Queue<Node>[] q;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {

		input();
		spread();
		int[] ans = calc();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < ans.length; i++)
			sb.append(ans[i] + " ");
		System.out.println(sb.toString());
	}

	static int[] calc() {
		int[] ans = new int[P + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (mapSave[i][j].x == -1)
					continue;
				ans[mapSave[i][j].x]++;
			}
		}
		return ans;
	}

	static void spread() {
		boolean[][] visit = new boolean[N][M];

		while (true) {
			boolean flag = false;
			visit = new boolean[N][M];
			for (int i = 1; i <= P; i++) {
				if (q[i].isEmpty())
					continue;

				int count = -1;
				count++;
				for (int a = 0; a < SofPlayer[i]; a++) {
					int size = q[i].size();
					if(size == 0)
						break;
					for (int s = 1; s <= size; s++) {
						Node n = q[i].poll();
						visit[n.x][n.y] = true;
						
						for (int d = 0; d < 4; d++) {
							int nx = n.x + dxy[d][0];
							int ny = n.y + dxy[d][1];

							if (!mapChk(nx, ny) || map[nx][ny] != 0 || visit[nx][ny])
								continue;
							if (count >= mapSave[nx][ny].y)
								continue;

							mapSave[nx][ny].x = i;
							mapSave[nx][ny].y = count;
							flag = true;
							visit[nx][ny] = true;
							q[i].add(new Node(nx, ny));
						}
					}

				}
			}
			if (!flag)
				break;
		}

	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		SofPlayer = new int[P + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= P; i++)
			SofPlayer[i] = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		mapSave = new Node[N][M];
		q = new ArrayDeque[P + 1];
		for (int i = 1; i <= P; i++)
			q[i] = new ArrayDeque<Node>();

		for (int i = 0, num; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				mapSave[i][j] = new Node();
				if (s.charAt(j) == '#')
					map[i][j] = -1;
				else if (s.charAt(j) != '.') {
					num = s.charAt(j) - 48;
					map[i][j] = num;
					q[num].add(new Node(i, j));
					mapSave[i][j].x = num;
					mapSave[i][j].y = 0;
				}
			}
		}
	}
}
