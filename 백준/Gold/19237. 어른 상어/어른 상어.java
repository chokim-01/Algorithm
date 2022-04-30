import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M, K;
	static Shark[][] map;
	static boolean[][] visit;

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Shark {
		int num, time, dir;
		int[][] seq;
		boolean flag;

		public Shark(int num) {
			// TODO Auto-generated constructor stub
			this.num = num;
			if (num != 0) {
				this.time = K;
				this.flag = true;
			}
			this.flag = false;
		}

		public Shark(int num, int[][] seq, int dir, int time) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.seq = seq;
			this.dir = dir;
			this.flag = true;
			this.time = K;
		}

		public void clear() {
			this.seq = null;
			this.flag = false;
			this.dir = 0;
		}

		public Shark returnS() {
			return new Shark(this.num, this.seq, this.dir, this.time);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		map = new Shark[N][N];
		visit = new boolean[N][N];

		// 상어 위치 기록
		int[][] sharkLoc = new int[M + 1][2]; // 0 : empty

		// 맵
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int a = sc.nextInt();
				map[i][j] = new Shark(a);
				if (a != 0)
					sharkLoc[a] = new int[] { i, j };
			}
		}
		// 방향
		for (int i = 1; i < M + 1; i++) {
			map[sharkLoc[i][0]][sharkLoc[i][1]].time = K;
			map[sharkLoc[i][0]][sharkLoc[i][1]].dir = sc.nextInt() - 1;
		}

		// 상어 우선순위
		for (int i = 1; i < M + 1; i++) {
			int[][] tmpd = new int[4][4];
			for (int j = 0; j < 4; j++) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int c = sc.nextInt() - 1;
				int d = sc.nextInt() - 1;
				tmpd[j] = new int[] { a, b, c, d };
			}
			map[sharkLoc[i][0]][sharkLoc[i][1]].seq = tmpd;
		}

		// 아무 냄새 없는 칸으로 방향을 잡음.
		// 없으면 자신의 냄새가 있는 칸. 즉, 되돌아가기
		int deadCount = M;
		int totalTime = 0;
		while (totalTime <= 1000) {
			// 상어 이동 시킴 죽으면 -1,-1
			if (deadCount == 1)
				break;

			// 이전에 방문 했던곳인지. / 와리가리 방지
			for(int i = 0;i<N;i++) {
				Arrays.fill(visit[i], false);
				for(int j = 0;j<N;j++)
					if(map[i][j].time != 0)
						visit[i][j] = true;
			}
			
			for (int s = 1; s < M + 1; s++) {
				if (sharkLoc[s][0] == -1) 
					continue;

				Shark shark = map[sharkLoc[s][0]][sharkLoc[s][1]];

				int x = sharkLoc[s][0];
				int y = sharkLoc[s][1];
				int nx = x;
				int ny = y;
				boolean flag = true;
				// 우선순위 탐색
				for (int d = 0; d < 4; d++) {
					nx = x + dxy[shark.seq[shark.dir][d]][0];
					ny = y + dxy[shark.seq[shark.dir][d]][1];
					if (!mapChk(nx, ny))
						continue;
					// 빠른 번호가 갈 때 다음 상어가 안갔으므로 다른 것.
					if(shark.num == map[nx][ny].num)
						continue;
					if (shark.num < map[nx][ny].num) {
						if (map[nx][ny].time == K || map[nx][ny].time != 0)
							continue;
						
					} else {
						if (map[nx][ny].time !=K && map[nx][ny].num != 0)
							continue;
						if(visit[nx][ny])
							continue;
					}
					shark.dir = shark.seq[shark.dir][d];
					flag = false;
					break;
				}
				// 없을 때 다시 구함
				if (flag) {
					for (int d = 0; d < 4; d++) {
						nx = x + dxy[shark.seq[shark.dir][d]][0];
						ny = y + dxy[shark.seq[shark.dir][d]][1];
						if (!mapChk(nx, ny))
							continue;
						if (map[nx][ny].num != map[x][y].num)
							continue;

						shark.dir = shark.seq[shark.dir][d];
						break;
					}
				}
				// 겹치면 없앰.
				if (map[nx][ny].num < shark.num && map[nx][ny].num != 0) {
					map[x][y].clear();
					sharkLoc[s][0] = -1;
					deadCount -= 1;
					continue;
				}
				// 이전거에 k-1 세팅하고 현재에 상어 넣음.
				map[nx][ny] = map[x][y].returnS();
				sharkLoc[s][0] = nx;
				sharkLoc[s][1] = ny;
				map[x][y].clear();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].time == 0 || map[i][j].flag)
						continue;
					map[i][j].time -= 1;
					if (map[i][j].time == 0)
						map[i][j].num = 0;
				}
			}
			totalTime++;
		}
		System.out.println(totalTime > 1000 ? -1 : totalTime);
	}

	static int getDir(int x) {
		if (x == 0)
			return 1;
		if (x == 1)
			return 0;
		if (x == 2)
			return 3;
		return 2;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}

}