import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static boolean[][] correct;
	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		correct = new boolean[N][M];
		ans = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				int x = -1;
				switch (c) {
				case 'U':
					x = 0;
					break;
				case 'D':
					x = 1;
					break;
				case 'L':
					x = 2;
					break;
				case 'R':
					x = 3;
					break;
				}
				map[i][j] = x;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (correct[i][j])
					continue;
				dfs(i, j, false);
			}
		}

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (correct[i][j])
					ans++;
		System.out.println(ans);
	}

	static boolean dfs(int x, int y, boolean flag) {
		int nx = x + dxy[map[x][y]][0];
		int ny = y + dxy[map[x][y]][1];
		if (!mapChk(nx, ny) || correct[nx][ny]) {
			correct[x][y] = true;
			return true;
		}

		if (!visit[nx][ny]) {
			visit[nx][ny] = true;
			flag = dfs(nx, ny, flag);
			visit[nx][ny] = false;
		}
		if (flag)
			correct[x][y] = true;
		return flag;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}