import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	static int K, lK;
	static long ans, save;
	static int[] nums, count, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder num = new StringBuilder(br.readLine()).reverse();
		int len = num.length();
		max = new int[len];
		nums = new int[len];
		count = new int[10];
		int n = len;
		int idx = -1;
		while (n-- > 0 && ++idx < num.length())
			nums[n] = num.charAt(idx) - '0';
		Arrays.fill(max, 9);

		ans = Long.MAX_VALUE;
		for (int i = 1; i <= 10; i++) {
			if (len % i != 0)
				continue;
			Arrays.fill(count, 0);
			save = Long.MAX_VALUE;
			K = i;
			lK = len / i;
			dfs(0, 0, 0, false, false);
			ans = ans < save ? ans : save;
			long a = ans;
			n = len;
			while (a != 0 && n-- > 0) {
				max[n] = (int) (a % 10);
				a /= 10;
			}
		}
		System.out.println(ans);
	}

	static boolean dfs(int idx, int v, long num, boolean flag, boolean flag2) {
		if (idx == nums.length) {
			save = num;
			return true;
		}
		int m = nums.length - idx;
		for (int choice = (flag ? 0 : nums[idx]); choice <= (flag2 ? 9 : max[idx]); choice++) {
			if (count[choice] >= lK)
				continue;
			int nv = (v | (1 << choice)) + ((num == 0 && choice == 0) ? -1 : 0);
			int nc = Integer.bitCount(nv);
			if (nc > K || nc + m < K)
				continue;
			long next = num * 10 + choice;
			if (nc != 0)
				count[choice]++;

			if (dfs(idx + 1, nv, next, flag || choice > nums[idx], flag2 || choice < max[idx]))
				return true;
			if (nc != 0)
				count[choice]--;
		}

		return false;
	}
}
//10
// 