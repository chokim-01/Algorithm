import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	static final int mod = 1000000007;
	static final long[][] initVal = { { 1, 1 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long[][] ans = fib(initVal, N);
		System.out.println(ans[0][1]);
	}

	static long[][] fib(long[][] matrix, long num) {
		if (num == 1)
			return matrix;

		if (num % 2 == 0)
			return fib(power(matrix,matrix), num / 2);

		return power(fib(matrix, num-1),matrix);
	}

	static long[][] power(long[][] m, long[][] m2) {
		long[][] ret = new long[2][2];

		ret[0][0] = calc(m[0][0], m2[0][0], m[0][1], m2[1][0]);
		ret[0][1] = calc(m[0][0], m2[0][1], m[0][1], m2[1][1]);
		ret[1][0] = calc(m[1][0], m2[0][0], m[1][1], m2[1][0]);
		ret[1][1] = calc(m[1][0], m2[0][1], m[1][1], m2[1][1]);
		return ret;
	}

	static long calc(long n, long n2, long n3, long n4) {
		return (((n * n2) % mod) + ((n3 * n4) % mod)) % mod;
	}
}