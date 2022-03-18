import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 물품의 수
		int K = sc.nextInt(); // 무게
		int[][] map = new int[N + 1][2];
		// dp : 무게
		dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			map[i][0] = sc.nextInt();
			map[i][1] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) { // 세로 물건 수. 물건 번호
			for (int j = 1; j <= K; j++) { // 가로 무게
				// 지금 넣으려는 물건의 무게가 초과일 경우.
				if (map[i][0] > j) 
					dp[i][j] = dp[i - 1][j];
				// 이전과 이전에서 남은 무게 수. 즉, 가로 = 남은 무게
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - map[i][0]] + map[i][1]);
			}
		}
		System.out.println(dp[N][K]);

	}
}