import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long tot = sc.nextLong();
		long c = sc.nextLong();

		long avg = c * 100 / tot;
		long ans = 0;
		long left = 1;
		long right = 10000000000L;
		while (left < right) {
			long mid = (left + right) >> 1;
			if (calc(c + mid, tot + mid) == avg)
				left = mid + 1;
			else {
				ans = mid;
				right = mid;
			}
		}
		System.out.println(ans == 0 ? -1 : ans);
	}

	static long calc(long n, long tot) {
		return n * 100 / tot;
	}
}