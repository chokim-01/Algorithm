import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N = 4;
	static Fish[][] map;
	static Fish[] fishs;
	static int[][] dxy = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };

	static class Fish implements Comparable<Fish> {
		int num, x, y, dir;

		public Fish(int num, int dir) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.dir = dir;
		}

		public Fish(int num, int x, int y, int dir) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			return this.num - o.num;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 맵에 fish 저장
		map = new Fish[4][4];
		fishs = new Fish[17];
		ans = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int num = sc.nextInt();
				int dir = sc.nextInt() - 1;
				map[i][j] = new Fish(num, dir);
			}
		}
		dfs(0, 0, map[0][0].num);
		System.out.println(ans);
	}

	static int ans;

	static void dfs(int x, int y, int eatCount) {
		ans = ans < eatCount ? eatCount : ans;
		// 상어가 x,y위치를 잡아먹음
		int sdir = map[x][y].dir;

		Fish[][] mapRepair = new Fish[N][N];
		repair(mapRepair, map);

		int nx = x;
		int ny = y;
		map[x][y] = null;
		fishMove(x, y);
		while (true) {
			nx += dxy[sdir][0];
			ny += dxy[sdir][1];
			if (!mapChk(nx, ny))
				break;
			if (map[nx][ny] == null)
				continue;
			dfs(nx, ny, eatCount + map[nx][ny].num);
		}
		repair(map, mapRepair);
	}

	static void print() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == null)
					System.out.print(0 + " ");
				else
					System.out.print(map[i][j].num + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void repair(Fish[][] to, Fish[][] from) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				to[i][j] = from[i][j];
	}

	static void makeFish() {
		fishs = new Fish[17];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == null)
					continue;
				fishs[map[i][j].num] = new Fish(map[i][j].num, i, j, map[i][j].dir);
			}
		}

	}

	static void fishMove(int sx, int sy) {
		makeFish();
		for (Fish f : fishs) {
			if (f == null)
				continue;
			int nfx = f.x;
			int nfy = f.y;
			while (true) {
				nfx = f.x + dxy[f.dir][0];
				nfy = f.y + dxy[f.dir][1];
				if (!mapChk(nfx, nfy) || (nfx == sx && nfy == sy)) {
					f.dir = (f.dir + 1) % 8;
					continue;
				}
				break;
			}
			if (map[nfx][nfy] == null) {
				// map change
				map[nfx][nfy] = new Fish(f.num, f.dir);
				map[f.x][f.y] = null;
				// list change
				fishs[f.num] = new Fish(f.num, nfx, nfy, f.dir);
			} else {
				// map change
				Fish tmp = new Fish(map[nfx][nfy].num, map[nfx][nfy].dir);
				map[nfx][nfy] = new Fish(f.num, f.dir);
				map[f.x][f.y] = new Fish(tmp.num, tmp.dir);
				// list change
				fishs[tmp.num] = new Fish(tmp.num, f.x, f.y, tmp.dir);
				fishs[f.num] = new Fish(f.num, nfx, nfy, f.dir);
			}
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}
}