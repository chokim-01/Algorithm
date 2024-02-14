import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int post[], in[], ans[];
	static HashMap<Integer, Integer> inOrderIndex;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		in = new int[N];
		post = new int[N];
		ans = new int[N];
		inOrderIndex = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int j = 0, n; j < N; j++) {
			n = Integer.parseInt(st.nextToken());
			in[j] = n;
			inOrderIndex.put(n, j);
		}
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < N; j++)
			post[j] = Integer.parseInt(st.nextToken());

		dfs(0, N - 1, 0, N - 1);
		for(int i = 0;i<N;i++)
			System.out.print(ans[i] + " ");
	}

	static int index = 0;

	static void dfs(int inStart, int inEnd, int postStart, int postEnd) {
		if (inStart > inEnd || postStart > postEnd)
			return;
		if (index == N)
			return;

		ans[index++] = post[postEnd];
		int now = inOrderIndex.get(post[postEnd]);

		int size = now - inStart;

		// left
		dfs(inStart, now - 1, postStart, postStart + size - 1);
		// right
		dfs(now + 1, inEnd, postStart + size, postEnd - 1);
	}
}