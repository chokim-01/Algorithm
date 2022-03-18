import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static boolean[][] map;
	static boolean[] visit;
	static int[][] ans;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new boolean[N][N];

		ans = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt() == 1 ? true : false;

		for (int i = 0; i < N; i++) {
			visit = new boolean[N];
			dfs(i);
			for (int j = 0; j < N; j++) {
				ans[i][j] = visit[j] ? 1 : 0;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(ans[i][j] + " ");
			System.out.println();
		}
	}

	static void dfs(int x) {

		for (int i = 0; i < N; i++) {
			if (map[x][i]) {
				if (!visit[i]) {
					visit[i] = true;
					dfs(i);
				}
			}
		}
	}
}