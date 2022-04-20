import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	static int[][] map;
	static int[][] dxy = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static List[][] mapHorse;
	static List<Loc> horse;

	static class Loc {
		int x, y, dir;

		public Loc(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		mapHorse = new LinkedList[N][N];
		horse = new LinkedList<Loc>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapHorse[i][j] = new LinkedList<Integer>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;

			horse.add(new Loc(x, y, dir));
			mapHorse[x][y].add(i);
		}
		int ans = -1;
		int turn = 0;
		outer: while (turn++ < 1000) {
			
			for (int h = 0; h < horse.size(); h++) {
				Loc l = horse.get(h);

				int nx = l.x + dxy[l.dir][0];
				int ny = l.y + dxy[l.dir][1];

				if (!mapChk(nx, ny)) {
					horse.get(h).dir = getDir(l.dir);
					nx = l.x + dxy[l.dir][0];
					ny = l.y + dxy[l.dir][1];
					if (!mapChk(nx, ny))
						continue;
				}

				if (map[nx][ny] == 0) {
					int index = 0;
					for (int i = 0; i < mapHorse[l.x][l.y].size(); i++) {
						int hNum = (int) mapHorse[l.x][l.y].get(i);
						horse.set(hNum, new Loc(nx, ny, horse.get(hNum).dir));
						mapHorse[nx][ny].add(index++, hNum);
						mapHorse[l.x][l.y].remove(i);
						if (hNum == h)
							break;
						i -= 1;
					}
				}
				else if (map[nx][ny] == 1) {
					int index = 0;
					for (int i = 0; i < mapHorse[l.x][l.y].size(); i++) {
						int hNum = (int) mapHorse[l.x][l.y].get(i);
						horse.set(hNum, new Loc(nx, ny, horse.get(hNum).dir));
						mapHorse[nx][ny].add(0, hNum);
						mapHorse[l.x][l.y].remove(i);
						if (hNum == h)
							break;
						i -= 1;
					}
				}
				if (gameEnd()) {
					ans = turn;
					break outer;
				}
			}
		}
		System.out.println(ans);
	}

	static boolean gameEnd() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (mapHorse[i][j].size() >= 4)
					return true;
		return false;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		if (map[x][y] == 2)
			return false;
		return true;

	}

	static int getDir(int x) {
		if (x == 1)
			return 0;
		if (x == 0)
			return 1;
		if (x == 2)
			return 3;
		return 2;
	}

}