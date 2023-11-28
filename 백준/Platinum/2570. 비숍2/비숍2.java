import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M;
	static boolean[] map;
	static boolean[] visit;
	static int[] occupancy;
	static ArrayList<Integer>[] link;
	static int tot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		int ret = makeLink();
		find(ret);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}

	static int[][] dxy = { { 1, 1 }, { 1, -1 } };

	static int makeLink() {
		int[][] mapNum = new int[N][N];

		// se
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = i * N + j;
				if (map[num] || mapNum[i][j] != 0)
					continue;
				count++;
				int nx = i;
				int ny = j;
				do {
					mapNum[nx][ny] = count;
					nx += dxy[0][0];
					ny += dxy[0][1];
				} while (mapChk(nx, ny) && !map[nx * N + ny]);
			}
		}
		int ret = count;
		link = new ArrayList[200001];
		for (int i = 0; i < 200001; i++)
			link[i] = new ArrayList<>();
		// sw
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = i * N + j;
				if (map[num] || visit[num])
					continue;
				count++;
				int nx = i;
				int ny = j;
				while (true) {
					int nnum = nx * N + ny;
					if (!mapChk(nx, ny) || map[nnum])
						break;
					visit[nnum] = true;
					link[mapNum[nx][ny]].add(count);
					nx += dxy[1][0];
					ny += dxy[1][1];
				}
			}
		}
		return ret;
	}

	static void find(int r) {
		int count = 0;
		for (int i = 1; i <= r; i++) {
			Arrays.fill(visit, false);
			if (dfs(i))
				count++;
		}
		System.out.println(count);
	}

	static boolean dfs(int now) {
		if (visit[now])
			return false;
		visit[now] = true;
		for (int next : link[now]) {
			if (occupancy[next] == -1 || dfs(occupancy[next])) {
				occupancy[next] = now;
				return true;
			}
		}
		return false;
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new boolean[N * N];
		visit = new boolean[200001];
		occupancy = new int[200001];
		Arrays.fill(occupancy, -1);
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a * N + b] = true;
		}
	}
}