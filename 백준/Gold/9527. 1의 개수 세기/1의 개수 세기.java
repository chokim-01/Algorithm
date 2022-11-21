import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long arr[] = new long[60];
		arr[1] = 1;

		for (int i = 2; i < 60; i++)
			arr[i] = (long) Math.pow(2, i - 1) + 2 * arr[i - 1];

		long x = sc.nextLong();
		long y = sc.nextLong();
		System.out.println(calc(y, arr) - calc(x-1, arr));
	}

	static long calc(long num, long[] arr) {
		long ans = 0;
		// 들어온 수 구하기

		String s = Long.toBinaryString(num);
		long count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0')
				continue;
			int n = s.length() - i - 1;
			if (num == 0)
				break;
			long t = (long) Math.pow(2, n);

			num -= t;
			count += 1;
			ans += (count - 1) * (t - 1) + arr[n] + count;
		}

		return ans;
	}
}