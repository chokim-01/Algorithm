import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {
	static final int MAX = 1000001;
	static final int mod = 1000000007;

	static class FWick {
		long[][] tree;

		public FWick() {
			// TODO Auto-generated constructor stub
			tree = new long[MAX][2];
		}

		long[] query(int r) {
			long[] ret = new long[] { 0, 0 };
			for (; r > 0; r -= r & -r) {
				if (tree[r][0] == ret[0])
					ret[1] = (ret[1] + tree[r][1]) % mod;
				else if (tree[r][0] > ret[0]) {
					ret[0] = tree[r][0];
					ret[1] = tree[r][1];
				}
			}
			return ret;
		}

		void update(int idx, int v, long v2) {
			for (; idx < MAX; idx += idx & -idx) {
				if (tree[idx][0] == v)
					tree[idx][1] = (tree[idx][1] + v2) % mod;
				else if (tree[idx][0] < v) {
					tree[idx][0] = v;
					tree[idx][1] = v2;
				}
			}
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
				map.put(c, ++cnt);

		FWick fwick = new FWick();
		for (int i = 0; i < N; i++) {
			int num = map.get(nums[i]);
			long[] res = fwick.query(num - 1);
			fwick.update(num, (int) res[0] + 1, res[1] == 0 ? 1 : res[1]);
		}
		long[] ans = fwick.query(MAX - 1);
		System.out.println(ans[0] + " " + ans[1]);
	}
}