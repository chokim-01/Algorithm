import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int K;
	static long ans;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder num = new StringBuilder(st.nextToken()).reverse();
		K = Integer.parseInt(st.nextToken());
		nums = new int[19];
		int n = 19;
		int idx = -1;
		while (n-- > 0 && ++idx < num.length())
			nums[n] = num.charAt(idx) - '0';
		ans = 0;
		dfs(0, 0, 0, 0, false);
		System.out.println(ans);

	}

	static boolean dfs(int idx, int v, int c, long num, boolean flag) {
		if (idx == nums.length) {
			ans = num;
			return true;
		}

		for (int choice = flag ? 0 : nums[idx]; choice < 10; choice++) {
			int nv = (v | (1 << choice)) + ((num == 0 && choice == 0) ? -1 : 0);
			int nc = Integer.bitCount(nv);
			if (nc + (nums.length - (idx + 1)) < K || nc > K)
				continue;
			if (dfs(idx + 1, nv, nc, num * 10 + choice, flag ? true : choice > nums[idx]))
				return true;
		}

		return false;
	}
}
//10
// 