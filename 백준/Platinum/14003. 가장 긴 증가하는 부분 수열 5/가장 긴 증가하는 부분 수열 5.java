import java.util.ArrayList;
import java.util.Arrays;
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
		int[] ans = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();

		Arrays.fill(dp, Integer.MIN_VALUE);
		dp[0] = arr[0];
		ans[0] = ans[0];
		int cnt = 1;
		for (int i = 1; i < N; i++) {
			if (dp[cnt - 1] < arr[i]) {
				dp[cnt++] = arr[i];
			}
			int index = binSearch(0, cnt - 1, arr[i]);

			dp[index] = arr[i];
			ans[i] = index;

		}
		StringBuilder sb = new StringBuilder();
		sb.append(cnt + "\n");

		int index = N;
		N = cnt;
		ArrayList<Integer> list = new ArrayList<>();
		while (N-- > 0) {
			while (--index >= 0) {
				if (ans[index] == N) {
					list.add(arr[index]);
					break;
				}
			}
		}
		for (int i = list.size() - 1; i >= 0; i--)
			sb.append(list.get(i) + " ");

		System.out.println(sb);
	}

	static int binSearch(int left, int right, int target) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (dp[mid] < target)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}
}