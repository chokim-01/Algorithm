import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][][] choice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		choice = new int[N + 1][N + 1][11];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				choice[i][j][n] = 1;
				for (int k = 1; k <= 10; k++)
					choice[i][j][k] += choice[i - 1][j][k] + choice[i][j - 1][k] - choice[i - 1][j - 1][k];
			}
		}

		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int ans = 0;
			for (int i = 1; i <= 10; i++)
				if (choice[c][d][i] - choice[a - 1][d][i] - choice[c][b - 1][i] + choice[a - 1][b - 1][i] > 0)
					ans++;
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

}