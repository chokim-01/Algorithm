import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		long N = Long.parseLong(br.readLine());
		if (N == 1) {
			System.out.println(1);
			return;
		}
		PriorityQueue<Long> q = new PriorityQueue<>();
		while (N > 1) {
			long n = pollard_rho(N);
			q.add(n);
			N = N / n;
		}
		while (!q.isEmpty())
			sb.append(q.poll()).append("\n");
		System.out.println(sb);
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
