import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static final int N = 10;
	static int T, M, A;
	static ArrayList<Node> map[][];
	static int[][] moveInfo;
	static Queue<int[]> nodeInfo;

	static int[][] dxy = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static class Node implements Comparable<Node> {
		int number, value;

		public Node(int n, int v) {
			this.number = n;
			this.value = v;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return o.value - this.value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			input(br);

			setMap();

			sb.append("#" + tc + " " + move() + "\n");
		}
		System.out.println(sb);
	}

	static int move() {
		int ans = 0;
		int[] a = { 0, 0 };
		int[] b = { N - 1, N - 1 };
		for (int i = 0; i <= M; i++) {
			a[0] += dxy[moveInfo[i][0]][0];
			a[1] += dxy[moveInfo[i][0]][1];
			b[0] += dxy[moveInfo[i][1]][0];
			b[1] += dxy[moveInfo[i][1]][1];
			// choice A
			int cAN = map[a[0]][a[1]].get(0).number;
			int cAV = map[a[0]][a[1]].get(0).value;
			// choice B
			int cBN = map[b[0]][b[1]].get(0).number;
			int cBV = map[b[0]][b[1]].get(0).value;
			if (cAN != 0 && cAN == cBN) {
				int max = cAV;
				max = Math.max(max, cAV + map[b[0]][b[1]].get(1).value);
				max = Math.max(max, cBV + map[a[0]][a[1]].get(1).value);
				ans += max;
			} else {
				ans += map[a[0]][a[1]].get(0).value + map[b[0]][b[1]].get(0).value;
			}
		}
		return ans;
	}

	static void setMap() {
		int number = 0;
		while (!nodeInfo.isEmpty()) {
			int[] node = nodeInfo.poll();
			number++;
			for (int cnt = node[2]; cnt >= 0; cnt--) {

				for (int y = node[1] - cnt; y <= node[1] + cnt; y++) {
					int x1 = node[0] + (node[2] - cnt);
					int x2 = node[0] - (node[2] - cnt);

					if (mapChk(x1, y))
						map[x1][y].add(new Node(number, node[3]));
					if (x1 != x2) {
						if (mapChk(x2, y))
							map[x2][y].add(new Node(number, node[3]));
					}
				}
			}
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				Collections.sort(map[i][j]);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
				map[i][j].add(new Node(0, 0));
			}
		}
		moveInfo = new int[M + 1][2];
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++)
				moveInfo[j][i] = Integer.parseInt(st.nextToken());
		}
		nodeInfo = new ArrayDeque<>();
		for (int i = 0, a, b, c, d; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());

			nodeInfo.add(new int[] { b, a, c, d });
		}
	}
}