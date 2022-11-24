import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static int[] list;
	static boolean flag[];
	static boolean successFlag;
	static LinkedHashSet<Integer> visit;
	static int tail;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			flag = new boolean[n + 1];
			list = new int[n + 1];
			tail = -1;
			int ans = 0;

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
				successFlag = false;
				dfs(i);
				if (successFlag) {
					Integer[] v = visit.toArray(new Integer[visit.size()]);
					boolean f = false;
					for (int k = 0; k < v.length; k++) {
						flag[v[k]] = true;
						if (v[k] == tail)
							f = true;
						if (!f)
							continue;
						ans++;
					}
				} else
					for (Integer v : visit)
						flag[v] = true;
			}
			System.out.println(n - ans);
		}
	}

	static void dfs(int x) {
		if (flag[x])
			return;
		if (visit.contains(x)) {
			tail = x;
			successFlag = true;
			return;
		}
		visit.add(x);
		dfs(list[x]);
	}
}