import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static int[] list;
	static boolean[] flag;
	static boolean[] visit;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			list = new int[n + 1];
			flag = new boolean[n + 1];
			visit = new boolean[n + 1];
			ans = 0;

			for (int i = 1; i <= n; i++) {
				int a = Integer.parseInt(st.nextToken());
				list[i] = a;
				if (a == i) {
					flag[i] = true;
					ans++;
				}
			}
			for (int i = 1; i <= n; i++) {
				if (flag[i])
					continue;
				dfs(i);
			}
			System.out.println(n - ans);
		}
	}

	static void dfs(int x) {
		if (flag[x]) 
			return;
		if(visit[x]) {
			flag[x] = true;
			ans++;
		}
		visit[x] = true;
		dfs(list[x]);
		flag[x] = true;
		visit[x] = false;
	}
}