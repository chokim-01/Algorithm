import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++)
				map[i][j] = s.charAt(j - 1) == '0' ? false : true;

		}
		int ans = 0;
		int[][] d = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!map[i][j])
					continue;
				ans = Math.max(ans, d[i][j] = Math.min(Math.min(d[i - 1][j - 1], d[i - 1][j]), d[i][j - 1]) + 1);
			}
		}
//		for (int[] f : d)
//			System.out.println(Arrays.toString(f));
		System.out.println(ans*ans);
	}
}