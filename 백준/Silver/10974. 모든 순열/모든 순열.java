import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static StringBuilder ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		ans = new StringBuilder();
		dfs(new int[N], new boolean[N + 1], 0);
		System.out.println(ans);

	}

	static void dfs(int[] choice, boolean[] visit, int cnt) {
		if (cnt == N) {
			for (int c : choice)
				ans.append(c + " ");
			ans.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			choice[cnt] = i;
			dfs(choice, visit, cnt + 1);
			choice[cnt] = 0;
			visit[i] = false;
		}
	}

}