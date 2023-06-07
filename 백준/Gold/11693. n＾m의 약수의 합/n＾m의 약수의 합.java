import java.util.Scanner;

class Main {
	static int N;
	static final int mod = 1000000007;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		long ans = 1;
		long deo = 1;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				long count = 0;
				while (n % i == 0) {
					n /= i;
					count++;
				}
				ans = (ans * (getOff(i, count * m + 1) - 1)) % mod;
				deo = (deo * (i - 1)) % mod;
			}
		}
		ans = (ans * getOff(deo, mod - 2)) % mod;
		if (n != 1)
			ans = ((ans * ((getOff(n, m + 1) - 1)) % mod) * getOff(n - 1, mod - 2)) % mod;
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
