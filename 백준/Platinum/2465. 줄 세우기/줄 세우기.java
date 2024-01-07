import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {
	static final int tN = 100001;
	static int N;
	static int[] nums;
	static int[] array;
	static HashMap<Integer, Integer> map, map2;

	static class Seg {
		int[] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			this.tree = new int[tN << 2];
		}

		void update(int i, int v) {
			i += tN;
			do
				tree[i] += v;
			while ((i >>= 1) != 0);
		}

		int query(int l, int r) {
			l += tN;
			r += tN;
			int ret = 0;
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1)
					ret += tree[l++];
				if ((r & 1) == 1)
					ret += tree[--r];
			}
			return ret;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		Seg seg = new Seg();
		for (int n : nums)
			seg.update(n, 1);

		int[] ans = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			int l = 0;
			int r = N - 1;
			while (l <= r) {
				int mid = (l + r) >> 1;
				if (seg.query(0, nums[mid]) <= array[i])
					l = mid + 1;
				else
					r = mid - 1;
			}
			ans[i] = nums[l - 1];
			seg.update(nums[l - 1], -1);

		}
		StringBuilder sb = new StringBuilder();
		for (int a : ans)
			sb.append(map2.get(a)).append("\n");
		System.out.println(sb);
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		map2 = new HashMap<>();
		nums = new int[N];
		for (int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(br.readLine());

		Arrays.sort(nums);
		int c = 1;
		for (int i = 0; i < N; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], c);
				map2.put(c, nums[i]);
				c++;
			}
			nums[i] = c - 1;
		}
		array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}