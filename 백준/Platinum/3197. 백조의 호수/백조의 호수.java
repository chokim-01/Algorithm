import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] map;
	static int[][] iceTime;
	static Queue<Node> ice;
	static ArrayList<Node> swan;

	static class Node implements Comparable<Node> {
		int x, y, time = 0;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int time) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub

			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ice = new LinkedList<>();
		swan = new ArrayList<>();
		map = new boolean[N][M];
		iceTime = new int[N][M];

//		map[0][0] = true;
//		map[1499][1499] = true;
//		swan.add(new Node(0, 0));
//		swan.add(new Node(1499, 1499));

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				if (c == 'L') {
					map[i][j] = true;
					swan.add(new Node(i, j));
				} else
					map[i][j] = c == '.' ? true : false;
			}
		}

		int time = 0;
		while (time <= 3000) {
			time++;
			if (time == 1)
				meltFirst(time);
			else
				melt(time);
			if (ice.size() == 0)
				break;
		}
		System.out.println(canGo());
	}

	static int dxy[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int canGo() {
		Node start = swan.get(0);
		Node end = swan.get(1);

		boolean[][] visit = new boolean[N][M];

		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start.x, start.y, 0));
		int ans = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!mapChk(nx, ny) || !map[nx][ny])
					continue;
				if (nx == end.x && ny == end.y)
					return n.time;
				if (visit[nx][ny])
					continue;
				int max = Math.max(iceTime[nx][ny], n.time);
				visit[nx][ny] = true;

				q.add(new Node(nx, ny, max));
			}
		}
		return ans;
	}

	static void melt(int time) {
		int size = ice.size();
		while (size-- > 0) {
			Node n = ice.poll();
			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if (!mapChk(nx, ny) || map[nx][ny])
					continue;
				map[nx][ny] = true;
				iceTime[nx][ny] = time;
				ice.add(new Node(nx, ny));
			}
		}
	}

	static void meltFirst(int time) {
		boolean[][] tmp = new boolean[N][M];
		for (int i = 0; i < N; i++)
			tmp[i] = map[i].clone();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!map[i][j])
					continue;
				// map .
				for (int d = 0; d < 4; d++) {
					int nx = i + dxy[d][0];
					int ny = j + dxy[d][1];

					if (!mapChk(nx, ny))
						continue;
					if (map[nx][ny] || tmp[nx][ny])
						continue;
					// map X
					tmp[nx][ny] = true;
					iceTime[nx][ny] = time;
					ice.add(new Node(nx, ny));
				}
			}
		}
		for (int i = 0; i < N; i++)
			map[i] = tmp[i].clone();
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= M)
			return false;
		return true;
	}
}