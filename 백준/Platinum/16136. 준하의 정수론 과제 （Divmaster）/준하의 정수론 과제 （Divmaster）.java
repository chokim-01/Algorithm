import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] ans;
	static long[][] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		makeMeasure();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] works = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		tree = new long[works.length << 2][2];

		init(1, 1, N, works);
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == 1) { // change
				update(1, 1, N, b, c);
			} else { // query
				answer.append(query(1, 1, N, b, c)).append("\n");
			}
		}

		System.out.println(answer);
	}

	static long query(int node, int l, int r, int a, int b) {
		if (b < l || r < a)
			return 0;
		if (a <= l && r <= b) {
			return tree[node][0];
		}
		int mid = (l + r) / 2;
		return query(node * 2, l, mid, a, b) + query(node * 2 + 1, mid + 1, r, a, b);

	}

	static long[] update(int node, int l, int r, int a, int b) {
		if (b < l || r < a)
			return tree[node];

		if (tree[node][1] < 3)
			return tree[node];

		if (l == r)
			return tree[node] = new long[] { ans[(int) tree[node][0]], ans[(int) tree[node][0]] };

		int mid = (l + r) / 2;
		long[] left = update(node * 2, l, mid, a, b);
		long[] right = update(node * 2 + 1, mid + 1, r, a, b);

		tree[node][0] = left[0] + right[0];
		tree[node][1] = Math.max(left[1], right[1]);
		return tree[node];
	}

	static long[] init(int node, int l, int r, int[] works) {
		if (l == r)
			return tree[node] = new long[] { works[l - 1], works[l - 1] };

		int mid = (l + r) / 2;
		long[] left = init(node * 2, l, mid, works);
		long[] right = init(node * 2 + 1, mid + 1, r, works);
		tree[node][0] = left[0] + right[0];
		tree[node][1] = Math.max(left[1], right[1]);
		return tree[node];
	}

	static void makeMeasure() {
		N = 1000001;
		ans = new int[N];
		int[] measure = new int[N];
		int[] measureCounts = new int[N];

		// che
		N -= 1;
		for (int i = 1; i <= N; i++)
			measure[i] = i;
		for (int i = 2; i <= N; i++) {
			if (measure[i] == i) {
				for (int j = 2; i * j <= N; j++) {
					if (measure[i * j] == i * j)
						measure[i * j] = i;
				}
			}
		}
		ans[1] = 1;
		for (int i = 2; i <= N; i++) {
			if (measure[i] == i) { // 자기자신
				ans[i] = 2;
				measureCounts[i]++;
			} else { // 약수 개수 구하기
				int p = measure[i];
				int m = i / p;
				if (p != measure[m])
					measureCounts[i]++;
				else
					measureCounts[i] = measureCounts[m] + 1; // 이전거.
				int a = measureCounts[i];
				ans[i] = (ans[m] / a) * (a + 1);
			}
		}
	}
}
