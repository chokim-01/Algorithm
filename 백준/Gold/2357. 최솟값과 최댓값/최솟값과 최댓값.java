import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[2][N << 1];

		Arrays.fill(tree[0], Integer.MAX_VALUE);
		Arrays.fill(tree[1], Integer.MIN_VALUE);

		for (int i = 0; i < N; i++)
			tree[0][N + i] = tree[1][N + i] = Integer.parseInt(br.readLine());
		init();

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) + N - 1;

			int b = Integer.parseInt(st.nextToken()) + N;

			int ans[] = getMinMax(a, b);
			sb.append(ans[0]).append(" ").append(ans[1]).append("\n");

		}
		System.out.println(sb);
	}

	static void init() {
		for (int i = N - 1; i > 0; --i) {
			tree[0][i] = Math.min(tree[0][i << 1], tree[0][i << 1 | 1]);
			tree[1][i] = Math.max(tree[1][i << 1], tree[1][i << 1 | 1]);
		}
	}

	static int[] getMinMax(int left, int right) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		while (left < right) {
			if ((left & 1) == 1) {
				min = Math.min(min, tree[0][left]);
				max = Math.max(max, tree[1][left]);
				left++;
			}
			if ((right & 1) == 1) {
				right--;
				min = Math.min(min, tree[0][right]);
				max = Math.max(max, tree[1][right]);
			}
			left /= 2;
			right /= 2;
		}
		return new int[] { min, max };
	}
}