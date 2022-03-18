import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		int N = str.length();
		int[] nums = new int[N + 2];

		for (int i = 1; i <= N; i++) {// 1 2 3 4 5
			nums[i] = str.charAt(i - 1) - '0';
		}
		nums[N+1] = 1;
		// 실패
		if (nums[1] == 0) {
			System.out.println(0);
			return;
		}
		dp = new int[N + 1];

		if (N >= 1)
			dp[1] = 1;
		if (N >= 2) {
			// 실패
			if (nums[2] == 0 && nums[1] > 2) {
				System.out.println(0);
				return;
			} else if (nums[2] == 0) {
				dp[2] = 1;
			} else if (nums[1] == 1 || (nums[1] == 2 && nums[2] < 7))
				dp[2] = 2;
			else
				dp[2] = 1;
		}
		for (int i = 3; i <= N; i++) {
			// 실패
			if (nums[i] == 0 && (nums[i - 1] > 2 || nums[i - 1] == 0)) {
				System.out.println(0);
				return;
			}
			if (nums[i] != 0 && (i <= N && nums[i + 1] != 0) && (nums[i - 1] == 1 || (nums[i - 1] == 2 && nums[i] < 7))) {
				dp[i] = (dp[i - 1] + dp[i - 2])%1000000;
				
			} else
				dp[i] = dp[i - 1];
		}
		System.out.println(dp[N]);

	}
}