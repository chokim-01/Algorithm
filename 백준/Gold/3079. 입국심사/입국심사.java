import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

		long l = 1;
		long r = 1000000000 * 1000000000L;

		while (l < r) {
			long mid = (l + r) / 2;
			if (possible(mid) >= M)
				r = mid;
			else
				l = mid + 1;
		}
		System.out.println(r);
	}

	static long possible(long m) {
		long res = 0;
		for (int lo : L) {
			res += m / lo;
            if(res < 0)
                return M;
        }
		return res;
	}
}