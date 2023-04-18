import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int[][] arrr = new int[N][2];
		for (int i = 0, a, b; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			arrr[i][0] = a;
			arrr[i][1] = b;
		}
		Arrays.sort(arrr, (x1, x2) -> x1[0] == x2[0] ? x1[1] - x2[1] : x1[0] - x2[0]);
		int[] arr = new int[N];
		int[] dp = new int[arr.length];
		int[] ans = new int[arr.length];
		Arrays.fill(dp, Integer.MAX_VALUE);

		for (int i = 0; i < N; i++) {
			arr[i] = arrr[i][1];
		}

		dp[0] = arr[0];
		int idx = 0;
		for (int i = 1; i < arr.length; i++) {

			if (dp[idx] < arr[i]) {
				dp[++idx] = arr[i];
			}
			int index = binSearch(dp, arr[i]);
			dp[index] = arr[i];
			ans[i] = index;

		}
		StringBuilder sb = new StringBuilder();
		sb.append(N - (idx + 1) + "\n");
//
//		int index = N;
//		N = idx + 1;
//		HashSet<Integer> set = new HashSet<>();
//		while (N-- > 0) {
//			while (--index >= 0) {
//				if (ans[index] == N) {
//					set.add(arr[index]);
//					break;
//				}
//			}
//		}
//		for (int[] ar : arrr) {
//			if(set.contains(ar[1]))
//				continue;
//			sb.append(ar[0]).append("\n");
//		}

		System.out.print(sb);
	}

	static int binSearch(int[] arr, int find) {
		int left = 0;
		int right = arr.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (arr[mid] < find)
				left = mid + 1;
			else
				right = mid;
		}
		return right;

	}
}