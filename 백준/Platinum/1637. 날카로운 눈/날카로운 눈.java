import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static double ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		long l = 0;
		long r = Integer.MAX_VALUE + (long) 1;
		long ans = -1;
		while (l < r) {
			long mid = (l + r) >> 1;
			long c = 0;
			for (int i = 0; i < N; i++)
				if (arr[i][0] <= mid)
					c += (Math.min(mid, arr[i][1]) - arr[i][0]) / arr[i][2] + 1;
			if (c % 2 == 0) {
				l = mid + 1;
			} else {
				r = mid;
				ans = c;
			}
		}
		if (r - 1 == Integer.MAX_VALUE) {
			System.out.println("NOTHING");
			return;
		}
		for (int i = 0; i < N; i++)
			if (arr[i][0] <= r - 1)
				ans -= (Math.min(r - 1, arr[i][1]) - arr[i][0]) / arr[i][2] + 1;
		System.out.println((r) + " " + ans);
	}

}
