import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sb.append("The bit patterns are").append("\n");
			dfs(0, 0, new boolean[N]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int index, int cnt, boolean[] choice) {
		if (cnt == M) {
			for (boolean c : choice)
				sb.append(c ? 1 : 0);
			sb.append("\n");
			return;
		}
		if (index == N) {
			return;
		}
		for (int i = index; i < N; i++) {
			choice[i] = true;
			dfs(i + 1, cnt + 1, choice);
			choice[i] = false;
		}
	}

}