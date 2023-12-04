import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		boolean[] map = new boolean[N + 1];
		while (M-- > 0)
			map[sc.nextInt()] = true;

		int[] dp = new int[N + 1];
		dp[1] = 1;
		if (N > 1)
			dp[2] = 2;
		for (int d = 3; d <= N; d++)
			dp[d] = dp[d - 1] + dp[d - 2];

		int res = 1;
		for (int i = 1; i < map.length; i++) {
			if (map[i])
				continue;
			int c = 0;
			while (i < map.length && !map[i]) {
				c++;
				i++;
			}
			i--;
			res *= dp[c];
		}
		System.out.println(res);
	}
}