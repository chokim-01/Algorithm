import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		long[] candy = new long[N];
		long min = 0;
		long max = Long.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			candy[i] = Long.parseLong(st.nextToken());
			max = max < candy[i] ? candy[i] : max;
		}
		System.out.println(binSearch(min, max, candy));
	}

	static long binSearch(long l, long r, long[] c) {
		long ret = 0;
		while (l <= r) {
			long m = (l + r) >> 1;
			long sum = 0;
			for (int i = 0; i < N; i++)
				sum += Math.max(c[i] - m, 0);
			if (sum <= K) {
				r = m - 1;
				ret = m;
			} else
				l = m + 1;
		}
		return ret;

	}
}