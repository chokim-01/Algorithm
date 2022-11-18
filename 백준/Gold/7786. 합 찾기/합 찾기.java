import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int l = Integer.parseInt(st.nextToken()) - 1;
		int u = Integer.parseInt(st.nextToken());

		long[] e = new long[11];
		long[] e2 = new long[11];
		long c = 1;
		int uNow = -1;
		int lNow = -1;
		for (int i = 0; i <= 10; i++) {
			e[i] = c;

			if (c > l && lNow == -1)
				lNow = i - 1;
			if (c > u && uNow == -1)
				uNow = i - 1;

			c *= 10;
		}

		for (int i = 1; i <= 10; i++)
			e2[i] = 10 * e2[i - 1] + 45 * (long) Math.pow(10, i - 1);

		long ans = calc(e2, u, uNow) - calc(e2, l, lNow);

		System.out.println(ans);
	}

	static long calc(long[] e2, int u, int now) {
		long ans = 0;
		long weight = 0;
		
		while (u > 0) {
			now = (int) Math.log10(u);
			int eNum = (int) Math.pow(10, now);
			int fNum = u / eNum;

			long add = 0;
			for (int i = 1; i <= fNum; i++) {
				add += (i - 1) * eNum;
				add += e2[now] + weight * eNum;
			}
			weight += fNum;
			u = u % eNum;
			ans += add + fNum;
		}
		return ans;
	}
}
//20 300 4000 50000 600000