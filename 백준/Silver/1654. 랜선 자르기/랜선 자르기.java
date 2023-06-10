import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int[] lans = new int[k];
		for (int i = 0; i < lans.length; i++)
			lans[i] = Integer.parseInt(br.readLine());

		long l = 0;
		long r = Integer.MAX_VALUE;
		while (l <= r) {
			long mid = (l + r) / 2;
			if (possible(mid, n, lans))
				l = mid + 1;
			else
				r = mid - 1;
		}
		System.out.println(r);
	}

	static boolean possible(long m, int n, int[] lans) {
		long c = 0;
		for (int l : lans)
			c += l / m;

		return c >= n;
	}
}