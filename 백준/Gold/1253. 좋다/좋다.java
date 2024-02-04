import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int ans = 0;
		Arrays.sort(nums);
		for (int i = 0, c; i < N; i++) {
			c = nums[i];
			int l = 0;
			int r = N - 1;
			while (l < r) {
				if (nums[l] + nums[r] < c)
					l++;
				else if (nums[l] + nums[r] > c)
					r--;
				else {
					if (l == i)
						l++;
					else if (r == i)
						r--;
					else {
						ans++;
						break;
					}
				}
			}
		}
		System.out.println(ans);

	}
}