import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Tc t
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i + M > N || j + M > N)
						continue;
					// 계산
					int count = 0;

					for (int a = i; a < i + M; a++) {
						for (int b = j; b < j + M; b++) {
							count += map[a][b];
						}
					}
					ans = ans < count ? count : ans;
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}
}