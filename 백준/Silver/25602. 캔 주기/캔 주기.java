import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, k, answer = 0;
	static int[] a;
	static int[][] r, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		a = new int[n];
		r = new int[k][n];
		m = new int[k][n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				r[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);

		System.out.print(answer);
	}

	public static void dfs(int depth, int sum) {
		if (depth == k) {
			answer = Math.max(answer, sum);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (a[i] >= 1) {
				a[i]--;
				// choice r
				for (int j = 0; j < n; j++) {
					if (a[j] >= 1) {
						a[j]--;
						dfs(depth + 1, sum + r[depth][i] + m[depth][j]); // choice r
						a[j]++;
					}
				}
				a[i]++;
			}
		}
	}
}