import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Shark shark;
	static int[][] map;

	static class Shark {
		int x, y; // Loc
		int size; // Size;
		int eatCount; // eatCount

		public Shark(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
		public Shark(int x, int y,int size) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.size = size;
		}

		public void setDefault() {
			this.eatCount = 0;
			this.size = 2;
		}

		public void Eat() {
			// TODO Auto-generated constructor stub
			if (++this.eatCount == this.size) {
				this.eatCount = 0;
				this.size += 1;
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					map[i][j] = 0;
					shark = new Shark(i, j);
					shark.setDefault();
				}
			}
		}

		int time = 0;
		while (true) {
			// 먹을 수 있는 물고기가 없음
			if (!canEat(shark.size))
				break;
			// 먹을 수 있는 물고기 찾기
			time+= startEat();

		}
		System.out.println(time);
	}

	static int startEat() {
		Queue<Shark> q = new LinkedList<>();
		q.add(shark);
		boolean flag = false;
		boolean[][] visit = new boolean[N][N];
		visit[shark.x][shark.y] = true;

		int rx = 23;
		int ry = 23;

		int time = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			time += 1;
			for (int f = 0; f < qsize; f++) {
				Shark s = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = s.x + dxy[d][0];
					int ny = s.y + dxy[d][1];

					if (!mapChk(nx, ny) || map[nx][ny] > shark.size || visit[nx][ny])
						continue;
					// 같다면
					visit[nx][ny] = true;
					if (map[nx][ny] != 0 && map[nx][ny] < shark.size) {
						if (rx == nx)
							if (ry > ny)
								ry = ny;
						if (rx > nx) {
							rx = nx;
							ry = ny;
						}
						flag = true;
					} else
						q.add(new Shark(nx, ny));
				}
			}
			if (flag) {
				map[rx][ry] = 0;
				shark.x = rx;
				shark.y = ry;
				shark.Eat();
				return time;
			}
		}
		return -1;
	}

	static boolean canEat(int size) {
		Queue<Shark> q = new LinkedList<>();
		q.add(shark);
		boolean[][] visit = new boolean[N][N];
		visit[shark.x][shark.y] = true;
		while (!q.isEmpty()) {
			Shark s = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = s.x + dxy[d][0];
				int ny = s.y + dxy[d][1];
				if (!mapChk(nx, ny) || map[nx][ny] > s.size || visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				if (map[nx][ny] != 0 && map[nx][ny] < s.size)
					return true;
				q.add(new Shark(nx, ny,s.size));
			}

		}
		return false;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}
}
// 아기 상어 크기 2 : 9
// 1초 상하좌우
// 큰 물고기가 있는 칸은 지나갈 수 없.
// 작은 물고기 먹음.
// 같은 물고기 지나갈 수만 있음.

// 이동
// 1. 먹을수 있는 물고기가 없다면 도움 요청
// 1-2. 물고기가 1마리라면 go
// 1-3. 1< : 가장 가까운 물고기
// 1-4. 가까운 물고기 많다면 가장 위, 가장 왼.
// 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
// 몇 초동안 엄마상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는가?
// exit : 먹을 수 있는 물고기가 없을 때