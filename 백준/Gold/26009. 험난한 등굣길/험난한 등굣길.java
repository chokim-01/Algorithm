import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static boolean[][] map;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		map = new boolean[N][M];

		for (int i = 0; i < N; i++)
			Arrays.fill(map[i], true);

		for (int i = 0, r, c, d; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			d = Integer.parseInt(st.nextToken());

			makeVisit(r, c, d);
		}
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));

		map[0][0] = false;
		int time = -1;
		int t = 0;
		outer: while (true) {
			int size = q.size();
			if(size == 0)
				break;
			t++;
			for (int i = 0; i < size; i++) {
				Node n = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = n.x + dxy[d][0];
					int ny = n.y + dxy[d][1];

					if (!mapChk(nx, ny) || !map[nx][ny])
						continue;
					if (nx == N - 1 && ny == M - 1) {
						time = t;
						break outer;
					}
					map[nx][ny] = false;
					q.add(new Node(nx, ny));
				}
			}
		}
		if (time == -1) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			System.out.println(time);
		}
	}

	static void makeVisit(int x, int y, int k) {
		for (int d = 0; d < 2; d++) {
			for (int c = 0; c <= k; c++) {
				int nx = x + (k - c) * dxy[d][0];
				int ny = y + (c) * dxy[d][0];
				if (!mapChk(nx, ny))
					continue;
				map[nx][ny] = false;
			}
		}
		for (int d = 2; d < 4; d++) {
			for (int c = 1; c < k; c++) {
				int nx = x - (k - c) * dxy[d][1];
				int ny = y + (c) * dxy[d][1];
				if (!mapChk(nx, ny))
					continue;
				map[nx][ny] = false;
			}
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}
// (n-0) * num1 == (max - n) * num2
