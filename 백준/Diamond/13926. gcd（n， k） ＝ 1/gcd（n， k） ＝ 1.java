import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Main {
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		long N = Long.parseLong(br.readLine());
		BigInteger s = BigInteger.valueOf(N);
		if (s.isProbablePrime(25)) {
			System.out.println(N - 1);
			return;
		}
		if (N == 1) {
			System.out.println(N);
			return;
		}
		ans = N;
		Set<Long> list = new HashSet<>();
		while (N > 1) {
			long n = pollard_rho(N);
			list.add(n);
			N = N / n;
		}
		for (long l : list)
			ans = ans / l * (l - 1);
		sb.append(ans).append("\n");
		System.out.println(sb);
	}

	static ArrayList<Long> calc(long N) {
		long save = N;
		Set<Long> set = new HashSet<>();
		for (long i = 2; i * i <= N; i++) {
			while (N % i == 0) {
				set.add(i);
				N /= i;
			}
		}
		if (N != 1 && save != N)
			set.add(N);
		return new ArrayList<>(set);
	}

	static long pollard_rho(long n) {
		if (BigInteger.valueOf(n).isProbablePrime(50))
			return n;
		if (n == 1)
			return 1;
		if ((n & 1) == 0)
			return 2;
		BigInteger x = BigInteger.valueOf((long) (Math.random() * (n - 1) + 2));
		BigInteger c = BigInteger.valueOf((long) (Math.random() * n + 1));
		BigInteger y = x;
		long d = 1;
		BigInteger bn = BigInteger.valueOf(n);
		while (d == 1) {
			x = x.multiply(x).mod(bn).add(c).add(bn).mod(bn);
			y = y.multiply(y).mod(bn).add(c).add(bn).mod(bn);
			y = y.multiply(y).mod(bn).add(c).add(bn).mod(bn);

			d = gcd(n, x.add(y.negate()).abs().longValue());
			if (d == n)
				return pollard_rho(n);
		}
		if (BigInteger.valueOf(d).isProbablePrime(50))
			return d;
		return pollard_rho(d);
	}

	static long gcd(long a, long b) {
		while (b != 0) {
			long t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
}
