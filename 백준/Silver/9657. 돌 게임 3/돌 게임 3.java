import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[] dp = new boolean[N + 1];
		if (N >= 2)
			dp[2] = true;
		for (int i = 6; i <= N; i++)
			if (dp[i - 1] || dp[i - 3] || dp[i - 4])
				dp[i] = false;
			else
				dp[i] = true;
		System.out.println(dp[N] ? "CY" : "SK");
		/*
		 * 1 = SK 2 = CY 3 = SK 4 = SK 5 = SK 6 = SK
		 */
	}
}