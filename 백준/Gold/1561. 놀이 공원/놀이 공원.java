import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		L = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		if (N <= K) {
			System.out.println(N);
			return;
		}

		long l = 1;
		long r = 30L * N;
		while (l < r) {
			long mid = (l + r) / 2;
			if (possible(mid) >= N)
				r = mid;
			else
				l = mid + 1;
		}
		long c = possible(r - 1);
		int ans = 0;
		for (int i = 0; i < K && c != N; i++)
			if (r % L[i] == 0 && ++c == N)
				ans = i + 1;
		System.out.println(ans);

	}

	static long possible(long m) {
		long res = 0;
		for (int lo : L)
			res += m / lo + 1;
		return res;
	}
}