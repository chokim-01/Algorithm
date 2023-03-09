import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] parMin;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());

		parMin = new int[N][17];

		for (int i = 1, n; i < N; i++) {
			n = Integer.parseInt(br.readLine());
			parMin[i][0] = n;
		}
		setPar();

		for (int i = 0, a, b; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int depth = (int) (Math.log(b - a + 1) / (Math.log(2)));
			sb.append(Math.min(parMin[a][depth], parMin[b + 1 - (1 << depth)][depth]) + "\n");

		}
		System.out.println(sb);
	}

	static void setPar() {
		int step = 1;
		for (int j = 1; j < 17; j++) {
			for (int i = 1; i < N; i++) {
				if (i + step >= N)
					continue;
				parMin[i][j] = Math.min(parMin[i][j - 1], parMin[i + step][j - 1]);
			}
			step <<= 1;
		}
	}
}
