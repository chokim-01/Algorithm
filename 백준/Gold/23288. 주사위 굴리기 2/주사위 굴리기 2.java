import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {

	static int N, M, K;
	static int[][] map;
	static int[][] mapCount;

	// 북 동 남 서
	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] diceChange = { { 0, 5, 6, 3, 4, 2, 1 }, { 0, 4, 3, 1, 2, 5, 6 }, { 0, 6, 5, 3, 4, 1, 2 },
			{ 0, 3, 4, 2, 1, 5, 6 } };

	static class Node {
		int x, y, dir;
		int[] dice;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.dir = 1;
			dice = new int[] { 0, 1, 6, 3, 4, 5, 2 };
		}

		public Node(int x, int y, int dir) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		public Node(int x, int y, int dir, int[] dice) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.dice = dice;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N][M];
		mapCount = new int[N][M];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();

		// 최적화 : 맵에 미리 큐를 돌려서 카운트를 저장해놓고 있음
		setGrade();

		// 주사위 : 좌표 (1,1)에서 시작
		Queue<Node> dice = new LinkedList<>();
		dice.add(new Node(0, 0));
		// 위 아래 동 서 남 북
		int ans = 0;

		while (K-- > 0) {
			Node n = dice.poll();
			// 주사위 nx,ny
			int nx = n.x + dxy[n.dir][0];
			int ny = n.y + dxy[n.dir][1];

			// 만약 칸이 없다면 이동방향을 바꾸고 반대로
			if (!mapChk(nx, ny)) {
				n.dir = (n.dir + 2) % 4;
				nx = n.x + dxy[n.dir][0];
				ny = n.y + dxy[n.dir][1];
			}
			// 도착한 칸에 대한 점수를 획득한다.
			ans += mapCount[nx][ny] * map[nx][ny];

			// 주사위가 이동 방향으로 한 칸 이동한다. 주사위 변경
			int[] nextDice = new int[7];
			for (int i = 1; i < 7; i++)
				nextDice[i] = n.dice[diceChange[n.dir][i]];

			// 주사위의 아랫면과 맵을 비교해서 이동방향을 결정한다.
			int ndir = n.dir;
			if (nextDice[2] > map[nx][ny])
				ndir = (n.dir + 1) % 4;
			else if (nextDice[2] < map[nx][ny])
				ndir = (n.dir + 3) % 4;

			dice.add(new Node(nx, ny, ndir, nextDice));
		}
		System.out.println(ans);
	}

	// 연속해서 이동할 수 있는 칸.
	static void setGrade() {
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j])
					continue;
				visit[i][j] = true;
				boolean[][] visit2 = new boolean[N][M];

				int count = 1;

				Queue<Node> q = new LinkedList<>();
				q.add(new Node(i, j));
				
				while (!q.isEmpty()) {
					Node n = q.poll();
					visit2[n.x][n.y] = true;
					for (int d = 0; d < 4; d++) {
						int nx = n.x + dxy[d][0];
						int ny = n.y + dxy[d][1];

						if (!mapChk(nx, ny) || visit[nx][ny] || map[i][j] != map[nx][ny])
							continue;
						count++;
						visit[nx][ny] = true;
						q.add(new Node(nx, ny));
					}
				}

				for (int a = 0; a < N; a++)
					for (int b = 0; b < M; b++)
						if (visit2[a][b])
							mapCount[a][b] = count;
			}
		}

	}

	// 연속해서 이동할 수 있는 칸.
	static int getGrade(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));

		int number = map[x][y];
		int count = 0;
		boolean[][] visit = new boolean[N][M];
		visit[x][y] = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			count += 1;

			for (int d = 0; d < 4; d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];

				if (!mapChk(nx, ny) || number != map[nx][ny] || visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, n.dir));
			}
		}
		return count;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}
