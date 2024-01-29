import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {
	static final int MAX = 1000001;
	static final int mod = 1000000007;

	static class Seg {
		long[][] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new long[MAX << 1][2];
		}

		long[] query(int l, int r) {
			long[] ret = new long[] { 0, 0 };
			l += MAX;
			r += MAX;
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1)
					ret = merge(ret, tree[l++]);
				if ((r & 1) == 1)
					ret = merge(ret, tree[--r]);
			}
			return ret;
		}

		void update(int idx, int v, long v2) {
			idx += MAX;
			tree[idx][1] = (v2 + (tree[idx][0] == v ? tree[idx][1] : 0)) % mod;
			tree[idx][0] = v;
			while ((idx >>= 1) != 0)
				tree[idx] = merge(tree[idx << 1], tree[idx << 1 | 1]);
		}

		long[] merge(long[] a, long[] b) {
			if (a[0] > b[0])
				return a;
			else if (a[0] < b[0])
				return b;
			return new long[] { a[0], (a[1] + b[1]) % mod };
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] c_nums = nums.clone();
		Arrays.sort(c_nums);

		HashMap<Integer, Integer> map = new HashMap<>();
		int cnt = 0;
		for (int c : c_nums)
			if (!map.containsKey(c))
				map.put(c, cnt++);

		Seg front = new Seg();
		for (int i = 0; i < N; i++) {
			int num = map.get(nums[i]);
			long[] res = front.query(0, num);
			front.update(num, (int) res[0] + 1, res[1] == 0 ? 1 : res[1]);
		}
		System.out.println(front.tree[1][0] + " " + front.tree[1][1]);
	}
}