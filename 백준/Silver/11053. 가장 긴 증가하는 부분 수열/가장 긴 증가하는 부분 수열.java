import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static int arr[];
	static int dp[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		arr = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();

		Arrays.fill(dp, 1);
		int max = 0;

		for (int i = 0; i < N; i++) {
			int a = arr[i];
			for (int j = 0; j < i; j++) {
				int b = arr[j];
				if (a > b)
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			max = max < dp[i] ? dp[i] : max;
		}
		System.out.println(max);
	}

}