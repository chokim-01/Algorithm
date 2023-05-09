import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dfs(new int[M], 0, 1);
		System.out.println(sb);
	}

	static void dfs(int[] visit, int index, int c) {
		if (index == M) {
			for (int num : visit)
				sb.append(num + " ");
			sb.append("\n");
			return;
		}
		// choice
		for (int i = c; i <= N; i++) {
			visit[index] = i;
			dfs(visit, index + 1, i);
			visit[index] = 0;
		}
	}
}