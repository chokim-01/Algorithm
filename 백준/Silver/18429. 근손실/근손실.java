import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		dfs(new boolean[N], 0, 0);
		System.out.println(ans);

	}

	static int ans = 0;

	static void dfs(boolean[] visit, int cnt, int val) {
		if (cnt == N) {
			ans++;
			return;
		}
		for (int i = 0; i < visit.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			int tmp = val;
			val += numbers[i] - M;
			if (val >= 0)
				dfs(visit, cnt + 1, val);
			val = tmp;
			visit[i] = false;
		}
	}

}