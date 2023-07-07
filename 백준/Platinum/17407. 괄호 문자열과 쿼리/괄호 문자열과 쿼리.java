import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;

	static class Seg {
		int[] tree;
		int[] lazy;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new int[N << 2];
			lazy = new int[N << 2];
		}

		public int init(int node, int l, int r, int[] numbers) {
			if (l == r) {
				return tree[node] = numbers[l - 1];
			}
			int mid = (l + r) >> 1;
			return tree[node] = Math.min(init(node << 1, l, mid, numbers), init(node << 1 | 1, mid + 1, r, numbers));
		}

		public void propagate(int node, int l, int r) {
			if (lazy[node] != 0) {
				tree[node] += lazy[node];
				if (l != r) {
					lazy[node << 1] += lazy[node];
					lazy[node << 1 | 1] += lazy[node];
				}
				lazy[node] = 0;
			}
		}

		public int updateRange(int node, int l, int r, int s, int e, int v) {
			// lazy
			propagate(node, l, r);
			if (r < s || l > e)
				return tree[node];
			if (s <= l && r <= e) {
				lazy[node] += v;
				propagate(node, l, r);
				return tree[node];
			}
			int mid = (l + r) >> 1;
			updateRange(node << 1, l, mid, s, e, v);
			updateRange(node << 1 | 1, mid + 1, r, s, e, v);
			return tree[node] = Math.min(tree[node << 1], tree[node << 1 | 1]);
		}

		public int query(int node, int l, int r, int s, int e) {
			// lazy
			propagate(node, l, r);
			if (r < s || l > e)
				return 0;
			if (s <= l && r <= e)
				return tree[node];
			int mid = (l + r) >> 1;
			return Math.min(query(node << 1, l, mid, s, e), query(node << 1 | 1, mid + 1, r, s, e));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		int[] numbers = String.valueOf(input).chars().map(x -> x == '(' ? 1 : -1).toArray();
		N = numbers.length;
		Seg seg = new Seg();
		for (int i = 1; i < numbers.length; i++)
			numbers[i] += numbers[i - 1];
		seg.init(1, 1, N, numbers);

		int end = numbers[N - 1];
		M = Integer.parseInt(br.readLine());

		int answer = 0;
		while (M-- > 0) {
			int a = Integer.parseInt(br.readLine());
			int v = input[a - 1] == ')' ? 2 : -2;
			input[a - 1] = input[a - 1] == '(' ? ')' : '(';
			seg.updateRange(1, 1, N, a, N, v);
			end += v;
			answer += seg.query(1, 1, N, 1, N) == 0 && end == 0 ? 1 : 0;
		}
		System.out.println(answer);

	}
}
// -1이 되면 안됨
