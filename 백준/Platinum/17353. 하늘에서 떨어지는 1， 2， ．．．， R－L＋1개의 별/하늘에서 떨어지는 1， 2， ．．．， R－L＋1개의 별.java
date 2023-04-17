import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class segmentTree {
		long[] tree;
		long[] lazy;

		public segmentTree(int n) {
			// TODO Auto-generated constructor stub
			tree = new long[4 * n];
			lazy = new long[4 * n];
		}

		long init(int start, int end, int node) {
			if (start == end)
				return tree[node] = a[start];
			int mid = (start + end) / 2;
			tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
			return tree[node];
		}

		long query(int node, int start, int end, int left, int right) {
			propagation(node, start, end);
			if (right < start || left > end)
				return 0;
			if (left <= start && right >= end)
				return tree[node];
			int mid = (start + end) / 2;
			long l = query(node * 2, start, mid, left, right);
			long r = query(node * 2 + 1, mid + 1, end, left, right);
			return l + r;
		}

		void update(int node, int start, int end, int index, long val) {
			if (index < start || index > end)
				return;
			tree[node] = tree[node] + val;
			if (start != end) {
				int mid = (start + end) / 2;
				update(node * 2, start, mid, index, val);
				update(node * 2 + 1, mid + 1, end, index, val);
			}
		}

		void updateRange(int node, int start, int end, int left, int right, long val) {
			propagation(node, start, end);
			if (right < start || left > end)
				return;
			if (left <= start && end <= right) {
				tree[node] += (end - start + 1) * val;
				if (start != end) {
					lazy[node * 2] += val;
					lazy[node * 2 + 1] += val;
				}
				return;
			}
			int mid = (start + end) / 2;
			updateRange(node * 2, start, mid, left, right, val);
			updateRange(node * 2 + 1, mid + 1, end, left, right, val);
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}

		void propagation(int node, int start, int end) {
			if (lazy[node] != 0) {
				tree[node] += (end - start + 1) * lazy[node];
				if (start != end) {
					lazy[node * 2] += lazy[node];
					lazy[node * 2 + 1] += lazy[node];
				}
				lazy[node] = 0;
			}
		}

	}

	static long[] a;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		a = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			a[i] = Long.parseLong(st.nextToken());
		long[] a2 = new long[N];
		for (int i = 1; i < N; i++) {
			a2[i] = a[i] - a[i - 1];
		}
		a2[0] = a[0];
		a = a2.clone();
		
		segmentTree tree = new segmentTree(N);
		tree.init(0, N - 1, 1);

		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());

			if (order == 1) {
				int left = Integer.parseInt(st.nextToken()) - 1;
				int right = Integer.parseInt(st.nextToken()) - 1;
				tree.updateRange(1, 0, N - 1, left, right, 1);
				if (right + 1 < a.length)
					tree.updateRange(1, 0, N - 1, right + 1, right + 1, -(right - left + 1));

			} else {
				int point = Integer.parseInt(st.nextToken()) - 1;
				long sum = tree.query(1, 0, N - 1, 0, point);
				sb.append(sum).append("\n");
			}
		}
		System.out.println(sb);

	}
}