import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int ans = 0;
	static int[][] link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		link = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Arrays.fill(link[a], -1);
			if (b != -1)
				link[a][0] = b;
			if (c != -1)
				link[a][1] = c;
		}
		inorder(1);
		dfs(1);
		System.out.println(ans);
	}

	static int end = 0;

	static void inorder(int now) {
		int i = 0;
		for (int next : link[now]) {
			i ^= 1;
			if (next != -1)
				inorder(next);
			if (i == 1)
				end = now;
		}
	};

	static boolean flag = true;

	static void dfs(int now) {
		for (int next : link[now]) {
			if (next != -1) {
				ans++;
				dfs(next);
				if (flag)
					ans++;
			}
			if (now == end)
				flag = false;
		}
		return;
	}
}