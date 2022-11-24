import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static int[] list;
	static boolean flag[];
	static LinkedHashSet<Integer> visit;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			flag = new boolean[n + 1];
			list = new int[n + 1];
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
				visit = new LinkedHashSet<>();
				visit.add(i);
				dfs(i);
			}
			System.out.println(n - ans);
		}
	}

	static void dfs(int x) {
		if (flag[x]) {
			if (visit.contains(x)) {
				Integer[] v = visit.toArray(new Integer[visit.size()]);
				int c = v.length;
				for (int i = 0; i < v.length; i++) {
					if (v[i] == x)
						break;
					c -= 1;
				}
				ans += c;
			}
			return;
		}
		flag[x] = true;
		visit.add(x);
		dfs(list[x]);
	}
}