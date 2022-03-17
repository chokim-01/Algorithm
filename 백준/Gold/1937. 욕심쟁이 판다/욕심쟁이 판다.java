import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N;
	static int res;

	static int dxy[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		res = 0;
		int[][] map = new int[N][N];
		int[][] mapVisit = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				mapVisit[i][j] = -1;
			}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				int cnt = find(map, i, j, map[i][j], mapVisit);
				res = res < cnt  ? cnt  : res;
				
			}

		System.out.println(res);

	}

	static int find(int[][] map, int x, int y, int bamboo, int[][] mapVisit) {
		if (mapVisit[x][y] != -1 && mapVisit[x][y] != 0)
			return mapVisit[x][y];

		mapVisit[x][y] = 1;
		boolean flag = true;
		int cnt[] = new int[4];
		for (int i = 0; i < 4; i++) {
			int nx = x + dxy[i][0];
			int ny = y + dxy[i][1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;
			if (bamboo >= map[nx][ny])
				continue;
			flag = false;
			cnt[i] += find(map, nx, ny, map[nx][ny], mapVisit);
			
		
		}
		Arrays.sort(cnt);
		mapVisit[x][y] += cnt[3];
		
		if (flag) {
			mapVisit[x][y] = 1;
			return 1;
		}

		return mapVisit[x][y];
	}

}
