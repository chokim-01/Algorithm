import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int N;
	static char[][] map;
	static boolean[][][] visit;
	static int mirrorCnt;

	static class Node implements Comparable<Node>{
		int x, y, d, cnt;

		public Node(int x, int y, int d, int cnt) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visit = new boolean[N][N][4];

		int startX = 0;
		int startY = 0;
		mirrorCnt = 0;

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '#') {
					startX = i;
					startY = j;
				}
				if (map[i][j] == '!')
					mirrorCnt += 1;
			}
		}

		System.out.println(bfs(startX, startY));

	}

	static int bfs(int startX, int startY) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < 4; i++) {
			q.add(new Node(startX, startY, i, 0));
			visit[startX][startY][i] = true;
		}

		int ans = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (startX != n.x || startY != n.y) {
				if (map[n.x][n.y] == '#') {
					ans = n.cnt;
					break;
				}
			}
			int nx = n.x + dxy[n.d][0];
			int ny = n.y + dxy[n.d][1];
			if (!mapChk(nx, ny) || map[nx][ny] == '*')
				continue;
			if (visit[nx][ny][n.d])
				continue;
			visit[nx][ny][n.d] = true;
			if (map[nx][ny] == '.' || map[nx][ny] == '#') {
				q.add(new Node(nx, ny, n.d, n.cnt));
			} else if (map[nx][ny] == '!') {
				q.add(new Node(nx, ny, n.d, n.cnt)); // 그대로 진행
				if (n.cnt + 1 <= mirrorCnt) {
					q.add(new Node(nx, ny, (n.d + 5) % 4, n.cnt + 1));
					q.add(new Node(nx, ny, (n.d + 3) % 4, n.cnt + 1));
				}
			}
		}
		return ans;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;

	}

}