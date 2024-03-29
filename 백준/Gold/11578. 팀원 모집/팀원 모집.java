import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		N = (1 << N) - 1;

		int[] bits = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while (st.hasMoreTokens())
				bits[i] = bits[i] | 1 << Integer.parseInt(st.nextToken()) - 1;
		}

		int ans = Integer.MAX_VALUE;
		for (int i = 0, correct, cnt; i < 1 << M; i++) {
			correct = 0;
			cnt = 0;
			for (int j = 0; j < M; j++) {
				if ((i & 1 << j) > 0) {
					cnt++;
					correct = correct | bits[j];
				}
				if (cnt >= ans || correct > N)
					break;
			}
			if (correct == N)
				ans = ans < cnt ? ans : cnt;
		}
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
}