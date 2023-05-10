import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		String[] str = String.valueOf(N).split("");
		numbers = Stream.of(str).mapToInt(Integer::parseInt).sorted().toArray();
		dfs(new boolean[str.length], 0, 0);
		System.out.println(ans);

	}

	static int ans = 0;

	static void dfs(boolean[] visit, int cnt, int val) {
		if (ans != 0)
			return;
		if (cnt == visit.length) {
			if (val > N)
				ans = val;
			return;
		}
		for (int i = 0; i < visit.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;

			dfs(visit, cnt + 1, val * 10 + numbers[i]);
			visit[i] = false;
		}
	}

}