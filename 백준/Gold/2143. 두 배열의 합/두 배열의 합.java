import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long T = Integer.parseInt(br.readLine());

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int[] B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		long[] aComb = new long[N * (N + 1) / 2];
		long[] bComb = new long[M * (M + 1) / 2];
		int aIdx = 0;
		for (int i = 0; i < N; i++) {
			long sum = aComb[aIdx++] = A[i];
			for (int j = i + 1; j < N; j++) {
				aComb[aIdx++] = sum = sum + A[j];
			}
		}
		int bIdx = 0;
		for (int i = 0; i < M; i++) {
			long sum = bComb[bIdx++] = B[i];
			for (int j = i + 1; j < M; j++) {
				bComb[bIdx++] = sum = sum + B[j];
			}
		}
		Arrays.sort(aComb);
		Arrays.sort(bComb);
		long ans = 0;
		for (int i = 0; i < aComb.length;) {
			long aSize = binSearch2(aComb, aComb[i]) - binSearch(aComb, aComb[i]);
			long bSize = binSearch2(bComb, T - aComb[i]) - binSearch(bComb, T - aComb[i]);
			ans += aSize * bSize;
			i += aSize;
		}
		System.out.println(ans);
	}

	static int binSearch(long[] list, long find) {
		int left = 0;
		int right = list.length;
		while (left < right) {
			int mid = (left + right) / 2;

			if (list[mid] < find) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	static int binSearch2(long[] list, long find) {
		int left = 0;
		int right = list.length;
		while (left < right) {
			int mid = (left + right) / 2;

			if (list[mid] <= find) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
}