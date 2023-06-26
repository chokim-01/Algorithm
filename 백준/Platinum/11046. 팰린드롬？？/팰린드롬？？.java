import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] in;
	static int[] out;
	static int[] colors;
	static int[] numbers;
	static ArrayList<Integer>[] tree;
	static ArrayList<Integer>[] link;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int len = Integer.parseInt(br.readLine());
		int[] numbers2 = new int[len * 2 + 1];
		int i = 0;
		st = new StringTokenizer(br.readLine());
		while (i < numbers2.length - 1) {
			numbers2[i++] = -1;
			numbers2[i++] = Integer.parseInt(st.nextToken());
		}
		numbers2[i] = -1;

		int[] dp = manachers(numbers2);
		StringBuilder ans = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		while (n-- > 0) {
			int[] m = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			ans.append(dp[m[0] + m[1] - 1] >= m[1] - m[0] + 1 ? 1 : 0).append("\n");
		}
		System.out.println(ans);
	}

	static int[] manachers(int[] numbers) {
		N = numbers.length;
		int r = 0, p = 0;
		int dp[] = new int[N];
		for (int i = 0; i < N; i++) {
			// 대칭점
			if (i <= r)
				dp[i] = Math.min(dp[2 * p - i], r - i);
			else
				dp[i] = 0;
			// 증가
			while (i - dp[i] - 1 >= 0 && i + dp[i] + 1 < N && numbers[i - dp[i] - 1] == numbers[i + dp[i] + 1])
				dp[i]++;

			if (r < i + dp[i]) {
				r = i + dp[i];
				p = i;
			}
		}

		return dp;
	}
}
