import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] link = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(link[i], 1000000000);
			link[i][i] = 0;
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			link[a][b] = c;
		}
		for (int k = 0; k < N; k++)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					link[i][j] = Math.min(link[i][j], link[i][k] + link[k][j]);

		int ans = 1000000000;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (i != j)
					ans = Math.min(ans, link[i][j] + link[j][i]);
		System.out.println(ans == 1000000000 ? -1 : ans);
	}
}
