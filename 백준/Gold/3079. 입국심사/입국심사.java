import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = new int[N];
		for (int i = 0; i < N; i++)
			L[i] = Integer.parseInt(br.readLine());

		BigInteger l = BigInteger.ONE;
		BigInteger r = l.multiply(BigInteger.TEN).pow(18);
		while (l.compareTo(r) < 0) {
			long mid = l.add(r).divide(BigInteger.TWO).longValue();
			if (possible(mid) >= M)
				r = BigInteger.valueOf(mid);
			else
				l = BigInteger.valueOf(mid + 1);
		}
		System.out.println(r);
	}

	static long possible(long m) {
		long res = 0;
		for (int lo : L) {
			res += m / lo;
			if (res < 0)
				return M;
		}
		return res;
	}
}