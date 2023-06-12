import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, r, c;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		dfs(0, 0, 0, 1 << N);
	}

	static void dfs(int a, int b, int ans, int n) {
		if (n == 1) {
			System.out.println(ans);
			return;
		}
		int nn = n / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int ni = a + i * nn;
				int nj = b + j * nn;
				if (ni <= r && r < ni + nn && nj <= c && c < nj + nn)
					dfs(ni, nj, ans, nn);
				else
					ans += nn * nn;
			}
		}
	}
}