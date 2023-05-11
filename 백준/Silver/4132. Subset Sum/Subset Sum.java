import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int ans;
	static int bag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		bag = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[] milks = new int[N];
		for (int i = 0; i < N; i++)
			milks[i] = Integer.parseInt(br.readLine());

		ans = Integer.MAX_VALUE;
		dfs(milks, 0, 0);
		System.out.println(ans);

	}

	static void dfs(int[] milks, int index, int val) {
		if (val >= bag) {
			ans = ans < val ? ans : val;
			return;
		}

		for (int i = index; i < milks.length; i++)
			dfs(milks, i + 1, val + milks[i]);

	}
}