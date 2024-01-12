import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

class Main {
	static long prime[][];
	static ArrayList<Long> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		int tc = 0;
		while ((s = br.readLine()) != null) {
			tc++;
			long N = Long.parseLong(s);
			if (N == 0)
				break;
			TreeMap<Long, Integer> map = new TreeMap<>();
			long num = N;
			while (num > 1) {
				long n = pollard_rho(num);
				if (!map.containsKey(n))
					map.put(n, 0);
				map.put(n, map.get(n) + 1);
				num = num / n;
			}
			map.put((long) 1, 0);
			prime = new long[map.size()][];

			int index = 0;
			for (long n : map.keySet())
				prime[index++] = new long[] { n, map.get(n) };

			list = new ArrayList<>();
			dfs(0, 1);
			Collections.sort(list);
			int endI = binSearch(BigInteger.valueOf(N).pow(3));
			long ans = Long.MAX_VALUE;
			for (int i = 0; i <= endI; i++) {
				long a = list.get(i);
				long na = N / a;
				int endJ = binSearch(i, BigInteger.valueOf(na));
				if (endJ == -1)
					continue;
				for (int j = i; j <= endJ; j++) {
					long b = list.get(j);
					long c = na / b;
					if (na % b != 0)
						continue;
					if (a + b + c >= ans)
						continue;
					if (binSearch(c)) {
						ans = a + b + c;
//						endI = binSearch(BigInteger.valueOf(ans).pow(3));
					}
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static boolean binSearch(long value) {
		int l = 0;
		int r = list.size();
		while (l < r) {
			int mid = (l + r) >> 1;
			if (list.get(mid) == value)
				return true;
			else if (list.get(mid) < value)
				l = mid + 1;
			else
				r = mid;
		}
		return false;
	}

	static int binSearch(BigInteger max) {
		int l = 0;
		int r = list.size();
		int ret = -1;
		while (l < r) {
			int mid = (l + r) >> 1;
			BigInteger mul = BigInteger.valueOf(list.get(mid));
			if (mul.pow(3).compareTo(max) <= 0) {
				l = mid + 1;
				ret = mid;
			} else {
				r = mid;
			}
		}
		return ret;
	}

	static int binSearch(int l, BigInteger max) {
		int r = list.size();
		int ret = -1;
		while (l < r) {
			int mid = (l + r) >> 1;
			BigInteger mul = BigInteger.valueOf(list.get(mid));
			if (mul.multiply(mul).compareTo(max) <= 0) {
				l = mid + 1;
				ret = mid;
			} else {
				r = mid;
			}
		}
		return ret;
	}

	static void dfs(int idx, long count) {
		if (idx == prime.length) {
			list.add(count);
			return;
		}
		long c = 1;
		for (int d = 0; d <= prime[idx][1]; d++) {
			dfs(idx + 1, count * c);
			c *= prime[idx][0];
		}
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
