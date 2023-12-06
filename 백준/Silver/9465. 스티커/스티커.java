import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] stic = new int[2][N + 2];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 2; j <= N + 1; j++)
					stic[i][j] = Integer.parseInt(st.nextToken());
			}
			int[][] d = new int[2][N + 2];
			for (int i = 2; i <= N + 1; i++)
				for (int j = 0; j <= 1; j++)
					d[j][i] = Math.max(Math.max(d[1][i - 2], d[0][i - 2]), d[(j + 1) % 2][i - 1]) + stic[j][i];

			ans.append(Math.max(d[0][N + 1], d[1][N + 1])).append("\n");
		}
		System.out.println(ans);

	}
}