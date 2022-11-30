import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N;
	static int visit[];
	static int arr[];
	static int dp[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		visit = new int[N];
		arr = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();

		Arrays.fill(visit, -1);
		Arrays.fill(dp, 1);
		int max = 0;

		int now = 0;
		for (int i = 0; i < N; i++) {
			int a = arr[i];
			for (int j = 0; j < i; j++) {
				int b = arr[j];
				if (a > b) {
					if (dp[i] < dp[j] + 1)
						visit[i] = j; // i to j
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			if (max < dp[i]) {
				max = dp[i];
				now = i;
			}
		}
		System.out.println(max);
		find(now);
	}

	static void find(int now) {
		if (now == -1)
			return;
		find(visit[now]);
		System.out.print(arr[now] + " ");
	}

}