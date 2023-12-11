import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int len = 1200000;
		boolean[] nums = new boolean[len + 1];
		Arrays.fill(nums, true);
		nums[1] = false;
		for (int i = 2; i <= len; i++) {
			if (!nums[i])
				continue;
			for (int j = 2 * i; j <= len; j += i) {
				nums[j] = false;
			}
		}
		int ans = 0;
		int N = new Scanner(System.in).nextInt();
		outer: for (int i = N; i < nums.length; i++) {
			if (!nums[i])
				continue;
			String s = String.valueOf(i);
			for (int a = 0; a < s.length() / 2; a++) {
				if (s.charAt(a) != s.charAt(s.length() - 1 - a))
					continue outer;
			}
			ans = i;
			break;
		}
		System.out.println(ans);
	}
}
