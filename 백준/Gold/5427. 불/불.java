import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int dxy[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Node {
		int x, y, cnt;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int M = sc.nextInt();
			int N = sc.nextInt();

			boolean[][] visit = new boolean[N][M];
			Queue<Node> human = new LinkedList<>();
			Queue<Node> fire = new LinkedList<>();
			char[][] map = new char[N][M];
			
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '@') {
						human.add(new Node(i, j, 1));
						visit[i][j] = true;
					} else if (map[i][j] == '*')
						fire.add(new Node(i, j));
				}
			}
			
			int res = -1;
			outer : while (!human.isEmpty()) {
				// start fire while size
				int fireSize = fire.size();
				for (int i = 0; i < fireSize; i++) {
					Node fn = fire.poll();
					
					for (int d = 0; d < 4; d++) {
						int fnx = fn.x + dxy[d][0];
						int fny = fn.y + dxy[d][1];
						
						if (!mapChk(fnx, fny, N, M))
							continue;
						if (map[fnx][fny] == '.' || map[fnx][fny] == '@') {
							fire.add(new Node(fnx, fny));
							map[fnx][fny] = '*';
						}
					}
				}
				
				// start human while size
				int humanSize = human.size();
				for (int i = 0; i < humanSize; i++) {
					Node hn = human.poll();

					if (hn.x == 0 || hn.x == N - 1 || hn.y == 0 || hn.y == M - 1) {
						res = hn.cnt;
						break outer;
					}

					for (int d = 0; d < dxy.length; d++) {
						int hnx = hn.x + dxy[d][0];
						int hny = hn.y + dxy[d][1];

						if (!mapChk(hnx, hny, N, M))
							continue;
						if (map[hnx][hny] != '.' || visit[hnx][hny]) {
							continue;
						}
						human.add(new Node(hnx, hny, hn.cnt + 1));
						visit[hnx][hny] = true;
						map[hnx][hny] = '@';
					}
				}

			}
			System.out.println(res == -1 ? "IMPOSSIBLE" : res);
		}
	}

	static boolean mapChk(int i, int j, int N, int M) {
		if (i < 0 || j < 0 || i >= N || j >= M)
			return false;
		return true;

	}

}
