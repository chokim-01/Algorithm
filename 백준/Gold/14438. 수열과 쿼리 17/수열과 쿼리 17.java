import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		tree = new int[N << 1];
		Arrays.fill(tree, Integer.MAX_VALUE);
		st = new StringTokenizer(br.readLine());
		for (int i = 0, n; i < N; i++) {
			n = Integer.parseInt(st.nextToken());
			arr[i] = tree[N + i] = n;
		}
		init();
		int M = Integer.parseInt(br.readLine());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) + N - 1;
			int c = Integer.parseInt(st.nextToken());

			if (a == 1) {
				update(b, c);
			} else {
				int min = getMin(b, c + N);
				sb.append(min).append("\n");
			}
		}
		System.out.println(sb);
	}

	static void init() {
		for (int i = N - 1; i > 0; --i)
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
	}

	static int getMin(int left, int right) {
		int min = Integer.MAX_VALUE;
		while (left < right) {
			if ((left & 1) == 1) {

				min = Math.min(min, tree[left++]);
			}
			if ((right & 1) == 1)
				min = Math.min(min, tree[--right]);
			left /= 2;
			right /= 2;
		}
		return min;

	}

	static void update(int index, int val) {
		tree[index] = val;
		while ((index /= 2) != 0) 
			tree[index] = Math.min(tree[index << 1], tree[index << 1 | 1]);
		
	}

}