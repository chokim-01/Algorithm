import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(nums);
			int bef = 0;
			for (int i = 1, Q; i < nums.length; i++) {
				Q = nums[i] - nums[0];
				if (bef == Q) {
					bef = Q;
					continue;
				}
				if (bef < Q)
					bef = gcd(Q, bef);
				else
					bef = gcd(bef, Q);
			}
			System.out.println(bef == 0 ? "INFINITY" : bef);
		}
	}

	static int gcd(int x, int y) {
		int r;
		while (y > 0) {
			r = x % y;
			x = y;
			y = r;
		}
		return x;
	}
}