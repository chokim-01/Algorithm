import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int ans;
	static int[] mods = { 2, 3, 5, 7 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			sb.append("Case #").append(tc).append(": ");

			dfs(0, 0, br.readLine().toCharArray());
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int index, long sum, char[] chars) {
		if (index == chars.length) {
			for (int m : mods) {
				if (sum % m == 0) {
					ans++;
					break;
				}
			}
		}
		long number = 0;
		for (int i = index; i < chars.length; i++) {
			number *= 10;
			number += chars[i] - '0';
			dfs(i + 1, sum + number, chars);
			if (index != 0)
				dfs(i + 1, sum - number, chars);
		}

	}
}