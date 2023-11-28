import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M;
	static boolean[][] map;
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
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static int[][] dxy = { { 0, 1 }, { 1, 0 } };

	static int makeLink() {
		int[][] mapNum = new int[N][M];

		// se
		int count = 0;
		for (int i = 0; i < N; i++)
			Arrays.fill(mapNum[i], ++count);

		int ret = N;
		link = new ArrayList[100001];
		for (int i = 0; i < 100001; i++)
			link[i] = new ArrayList<>();
		// sw
		int[][] mapNum2 = new int[N][M];

		for (int i = 0; i < M; i++) {
			++count;
			for (int j = 0; j < N; j++)
				mapNum2[j][i] = count;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!map[i][j])
					continue;
				link[mapNum[i][j]].add(mapNum2[i][j]);
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		visit = new boolean[100001];
		occupancy = new int[100001];
		Arrays.fill(occupancy, -1);
		tot = 0;
		for (int i = 0; i < N; i++)
			Arrays.fill(map[i], true);
		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = false;
		}
	}
}