import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N = 12;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] cows = new int[N];
		for (int i = 0; i < N; i++)
			cows[i] = Integer.parseInt(br.readLine());
		ans = Integer.MAX_VALUE;
		dfs(cows, new int[4], new int[4], 0);
		System.out.println(ans);

	}

	static void dfs(int[] cows, int[] team, int[] visit, int index) {
		if (index == N) {
			int max = 0;
			int min = Integer.MAX_VALUE;
			for (int t : team) {
				max = max < t ? t : max;
				min = min < t ? min : t;
			}
			ans = ans < max - min ? ans : max - min;
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (visit[i] == 3)
				continue;
			visit[i] += 1;
			team[i] += cows[index];
			dfs(cows, team, visit, index + 1);
			team[i] -= cows[index];
			visit[i] -= 1;
		}
	}
}