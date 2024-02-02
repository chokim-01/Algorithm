import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[][][] save;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		save = new long[21][21][21];
		StringBuilder ans = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == -1 && b == -1 && c == -1)
				break;
			ans.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(dfs(a, b, c))
					.append("\n");
		}
		System.out.println(ans);
	}

	static long dfs(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0)
			return 1;
		if (a > 20 || b > 20 || c > 20)
			return dfs(20, 20, 20);
		if (save[a][b][c] != 0)
			return save[a][b][c];
		if (a < b && b < c)
			return save[a][b][c] = dfs(a, b, c - 1) + dfs(a, b - 1, c - 1) - dfs(a, b - 1, c);

		return save[a][b][c] = dfs(a - 1, b, c) + dfs(a - 1, b - 1, c) + dfs(a - 1, b, c - 1)
				- dfs(a - 1, b - 1, c - 1);
	}
}