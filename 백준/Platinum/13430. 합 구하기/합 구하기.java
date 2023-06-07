import java.util.Scanner;

class Main {
	static int N;
	static final int mod = 1000000007;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt() + 2;
		int n = sc.nextInt();

		if (n == 1) {
			System.out.println(1);
			return;
		}
		long ans = 1;
		for (int i = k + n - 2; i >= n; i--)
			ans = (ans * i) % mod;

		long deo = 1;
		for (int i = 2; i <= k - 1; i++)
			deo = (deo * i) % mod;

		ans = (ans * getOff(deo, mod - 2)) % mod;
		System.out.println(ans);
	}

	static long getOff(long n, long m) {
		long result = 1;
		while (m > 0) {
			if ((m & 1) == 1) {
				result = (result * n) % mod;
			}
			n = (n * n) % mod;
			m >>= 1;
		}
		return result;
	}
}
