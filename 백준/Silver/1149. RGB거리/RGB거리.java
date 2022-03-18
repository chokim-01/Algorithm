import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] dp;
	static int[][] color;
	static int[] flag;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// 2* N 크기의 직사각형을 1*2, 2*1 의 타일로 채우는 방법의 수

		int N = sc.nextInt();

		color = new int[N][3];
		dp = new int[3];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < 3; j++)
				color[i][j] = sc.nextInt();

		dp = color[0];

		for (int i = 1; i < N; i++) {
			int a = Math.min(dp[1], dp[2]);
			int b = Math.min(dp[0], dp[2]);
			int c = Math.min(dp[1], dp[0]);
			
			dp[0] = a + color[i][0];
			dp[1] = b + color[i][1];
			dp[2] = c + color[i][2];
		}
		
		System.out.println(Math.min(Math.min(dp[0], dp[1]),dp[2]));

	}

}