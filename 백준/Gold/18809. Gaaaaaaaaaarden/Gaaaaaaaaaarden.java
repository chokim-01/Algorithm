import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Acid {
		boolean possible;
		boolean green, red;
		int time;

		public Acid(boolean possible) {
			this.possible = possible;
			this.green = false;
			this.red = false;
			this.time = 1;
		}

		public Acid(boolean possible, boolean green, boolean red, int time) {
			this.possible = possible;
			this.green = green;
			this.red = red;
			this.time = time;
		}

		public boolean greenPossible() {
			if (!this.possible || this.green || this.red)
				return false;
			return true;
		}

		public boolean redPossible(int time) {
			if (!this.possible || this.red)
				return false;
			if (this.green) {
				if (this.time == time) {
					this.possible = false;
					return true;
				}
				return false;
			}
			return true;
		}
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, G, R; // 행, 열, 초록 배양액 수, 빨간 배양액 수
	// 0은 호수 1은 배양액 x, 2는 배양액 뿌릴 수 있는 땅
	static boolean[][] map;
	static Acid[][] acidMapOrigin;
	static List<Node> seedPossible;
	static int ans;
	static int[] choice;
	static Acid[][] acidMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		acidMap = new Acid[N][M];
		seedPossible = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0, n; j < M; j++) {
				n = Integer.parseInt(st.nextToken());

				if (n == 0)
					map[i][j] = true;
				if (n == 2)
					seedPossible.add(new Node(i, j));
			}
		}

		choice = new int[seedPossible.size()];
		// 피울 수 있는 꽃의 최대 개수
		ans = 0;
		seedSelect(0, 0, 0);
		System.out.println(ans);
	}

	static void seedSelect(int index, int g, int r) {
		if (index == choice.length) {
			if (g == G && r == R) { // red end
				int a = bfs();
				ans = ans < a ? a : ans;
			}
			return;
		}

		if (g < G) {
			choice[index] = 1;
			seedSelect(index + 1, g + 1, r);
		}
		if (r < R) {
			choice[index] = 2;
			seedSelect(index + 1, g, r + 1);
		}
		// not choice
		choice[index] = 0;
		seedSelect(index + 1, g, r);
	}

	static int dxy[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int bfs() {
		// origin Map clone
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				acidMap[i][j] = new Acid(map[i][j] ? false : true);

		Queue<Node> green = new LinkedList<>();
		Queue<Node> red = new LinkedList<>();

		int flowerCnt = 0;
		for (int i = 0; i < choice.length; i++) {
			Node n = seedPossible.get(i);
			if (choice[i] == 1) {
				green.add(n);
				acidMap[n.x][n.y].green = true;
			} else if (choice[i] == 2) {
				red.add(n);
				acidMap[n.x][n.y].red = true;
			}
		}

		int time = 1;
		while (!green.isEmpty()) {
			int greenSize = green.size();
			time += 1;

			Queue<Node> tempGreen = new LinkedList<>();
			// queue green
			for (int g = 0; g < greenSize; g++) {
				Node gr = green.poll();
				for (int d = 0; d < 4; d++) {
					int nx = gr.x + dxy[d][0];
					int ny = gr.y + dxy[d][1];
					if (!mapChk(nx, ny) || !acidMap[nx][ny].greenPossible())
						continue;
					// green setting
					acidMap[nx][ny].green = true;
					acidMap[nx][ny].time = time;

					// temp Queue
					tempGreen.add(new Node(nx, ny));
				}
			}
			// queue red
			int redSize = red.size();
			for (int r = 0; r < redSize; r++) {
				Node re = red.poll();
				for (int d = 0; d < 4; d++) {
					int nx = re.x + dxy[d][0];
					int ny = re.y + dxy[d][1];
					if (!mapChk(nx, ny) || !acidMap[nx][ny].redPossible(time))
						continue;
					if (!acidMap[nx][ny].possible) {
						flowerCnt++;
						acidMap[nx][ny].time = -1;
						continue;
					}
					acidMap[nx][ny].red = true;
					acidMap[nx][ny].time = time;

					red.add(new Node(nx, ny));
				}
			}
			// green add without duplicated
			while (!tempGreen.isEmpty()) {
				Node tmp = tempGreen.poll();
				if (acidMap[tmp.x][tmp.y].time == -1)
					continue;
				green.add(tmp);
			}
		}
		return flowerCnt;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		return true;
	}
}