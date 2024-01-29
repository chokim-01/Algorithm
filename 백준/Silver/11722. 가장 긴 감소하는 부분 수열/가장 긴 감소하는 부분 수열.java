import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static final int MAX = 1001;

	static class Seg {
		int[] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new int[MAX << 1];
		}

		int query(int l, int r) {
			int ret = 0;
			l += MAX;
			r += MAX;
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1)
					ret = Math.max(ret, tree[l++]);
				if ((r & 1) == 1)
					ret = Math.max(ret, tree[--r]);
			}
			return ret;
		}

		void update(int idx, int v) {
			idx += MAX;
			tree[idx] = v;
			while ((idx >>= 1) != 0)
				tree[idx] = Math.max(tree[idx << 1], tree[idx << 1 | 1]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
//		Seg front = new Seg();
		Seg rear = new Seg();
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] calc = new int[N];
		for (int i = 0; i < N; i++) {
//			int fq = front.query(0, nums[i]);
			int rq = rear.query(0, nums[N - i - 1]);
//			front.update(nums[i], fq + 1);
			rear.update(nums[N - i - 1], rq + 1);
//			calc[i] += fq + 1;
			calc[N - i - 1] += rq + 1;
		}
		int ans = 0;
		for (int c : calc)
			ans = Math.max(c, ans);
		System.out.println(ans);
	}
}