import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		long x;
		int index;

		public Node(long x, int index) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.index = index;
		}

		@Override
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			if (this.x == o.x)
				return this.index - o.index;
			return Long.compare(this.x, o.x);
		}

		@Override
		public String toString() {
			return this.x + " " + this.index + " ";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] sorted = new Node[N];
		long[] numbers = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			long n = Long.parseLong(st.nextToken());
			sorted[i] = new Node(n, i);
			numbers[i] = n;
		}
		Arrays.sort(sorted);

		boolean flag = true;
		for (int i = 0; i < N; i++) {
			if (sorted[i].x == numbers[i])
				continue;
			long a = sorted[i].x;
			long b = numbers[i];
			long gcd = gcd(a, b);
			if (!valid(a, gcd) || !valid(b, gcd)) {
				flag = false;
				break;
			}
		}
		if (flag)
			System.out.println("YES");
		else
			System.out.println("NO");
	}

	static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static boolean valid(long a, long gcd) {
		long f = a / gcd;
		long sqrtF = (long) Math.sqrt(f);
		if (sqrtF * sqrtF == f)
			return true;
		return false;
	}
}