import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int ans;
	static boolean[] prison;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("Case #" + tc + ":" + " ");
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			prison = new boolean[p];
			int[] releases = new int[q];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < q; i++)
				releases[i] = Integer.parseInt(st.nextToken()) - 1;
			perm(releases, 0);

			sb.append(ans).append("\n");
		}
		System.out.println(sb);

	}

	static void perm(int[] releases, int index) {
		if (index == releases.length) {
			int c = 0;
			boolean[] p = prison.clone();
			for (int r : releases) {
				p[r] = true;
				c += dfs(p.clone(), r, 0) - 1;
			}
			ans = ans < c ? ans : c;
			return;
		}
		for (int i = index; i < releases.length; i++) {
			swap(releases, index, i);
			perm(releases, index + 1);
			swap(releases, index, i);
		}

	}

	static void swap(int[] a, int b, int c) {
		int tmp = a[b];
		a[b] = a[c];
		a[c] = tmp;
	}

	static int dfs(boolean[] prison, int num, int cnt) {
		cnt++;
		prison[num] = true;
		if (num > 0 && !prison[num - 1])
			cnt = dfs(prison, num - 1, cnt);
		if (num < prison.length - 1 && !prison[num + 1])
			cnt = dfs(prison, num + 1, cnt);
		return cnt;
	}

}