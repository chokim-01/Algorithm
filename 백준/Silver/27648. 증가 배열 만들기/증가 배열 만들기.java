import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N + 1][M + 1];
		map[1] = IntStream.rangeClosed(0, M).map(x -> x).toArray();
		for (int i = 1; i <= N; i++)
			map[i][1] = i;
		for (int i = 2; i <= N; i++)
			for (int j = 2; j <= M; j++)
				map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]) + 1;

		StringBuilder ans = new StringBuilder();
		if (map[N][M] > K)
			ans.append("NO");
		else {
			ans.append("YES").append("\n");
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++)
					ans.append(map[i][j]).append(" ");
				ans.append("\n");
			}
		}
		System.out.println(ans);

	}
}
