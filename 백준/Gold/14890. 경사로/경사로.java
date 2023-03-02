import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, L;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();
		int[][] map = new int[N * 2][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		for (int i = N; i < N * 2; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = map[j][i - N];

		int ans = 0;

		for (int i = 0; i < N * 2; i++) {
			boolean flag = true;
			int cnt = 1;
			for (int j = 1; j < N; j++) {
				if (map[i][j - 1] == map[i][j])
					cnt += 1;
				else if (map[i][j - 1] == map[i][j] - 1) {
					if (cnt < L) {
						flag = false;
						break;
					}
					cnt = 1;
				} else if (map[i][j - 1] == map[i][j] + 1) {
					if(cnt < 0) {
						flag = false;
						break;
					}
					cnt = 1 - L;
				} else {
					flag = false;
					break;
				}
			}
			if(flag && cnt >= 0)
				ans +=1;

		}
		System.out.println(ans);

	}

	static boolean mapChk(int x) {
		if (x < 0 || x >= N)
			return false;
		return true;

	}

}