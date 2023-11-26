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
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			input(br);
			makeLink();
			find();
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static int[][] dxy = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };

	static void makeLink() {
		link = new ArrayList[N * M];
		for (int i = 0; i < link.length; i++)
			link[i] = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = i * M + j;
				for (int d = 0; d < 6; d++) {
					int nx = i + dxy[d][0];
					int ny = j + dxy[d][1];
					int nnum = nx * M + ny;
					if (!mapChk(nx, ny) || !map[nnum])
						continue;
					link[num].add(nnum);
				}
			}
		}
	}

	static void find() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0, num; j < M; j += 2) {
				num = i * M + j;
				if (!map[num])
					continue;
				Arrays.fill(visit, false);
				if (dfs(num))
					count++;
			}
		}
		System.out.println(tot - count);
	}

	static boolean dfs(int now) {
		if (visit[now])
			return false;
		visit[now] = true;
		for (int next : link[now]) {
			if (!map[next])
				continue;
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
		map = new boolean[N * M];
		visit = new boolean[N * M];
		occupancy = new int[N * M];
		Arrays.fill(occupancy, -1);
		tot = 0;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == '.')
					tot++;
				map[i * M + j] = s.charAt(j) == '.' ? true : false;
			}
		}
	}
}