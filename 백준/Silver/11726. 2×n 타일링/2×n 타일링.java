import java.io.IOException;
import java.util.Scanner;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// 2* N 크기의 직사각형을 1*2, 2*1 의 타일로 채우는 방법의 수

		int N = sc.nextInt();

		dp = new int[N + 1];
        dp[1]=1;
		if (N >= 2)
			dp[2] = 2;
		
		for (int i = 3; i <= N; i++)
			dp[i] = (dp[i - 1] + dp[i - 2])%10007;
		System.out.println(dp[N]);

		// 2 * 2 2
		// 2 * 3 3
		// 2 * 4 5
		// 5 : 8
		// 6 : 13
		// 7 : 21
	}

}