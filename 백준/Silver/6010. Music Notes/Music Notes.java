import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static long[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] o = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		nums = new long[o[0] + 1];
		int idx = 0;
		while (idx++ < o[0])
			nums[idx] += Long.parseLong(br.readLine()) + nums[idx - 1];

		StringBuilder ans = new StringBuilder();
		while (o[1]-- > 0)
			ans.append(binSearch(Long.parseLong(br.readLine()))).append("\n");
		System.out.println(ans);
	}

	static int binSearch(long f) {
		int l = 0;
		int r = nums.length;
		while (l < r) {
			int mid = (l + r) >> 1;
			if (nums[mid] <= f)
				l = mid + 1;
			else
				r = mid;
		}
		return l;
	}
}