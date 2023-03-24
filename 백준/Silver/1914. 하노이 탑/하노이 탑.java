import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();

		int N = sc.nextInt();
		if (N <= 20) {
			sb.append((1 << N) - 1 + "\n");
			dfs(N, 1, 2, 3);
		} else {
			BigInteger count = new BigInteger("1");
			while (N-- > 0)
				count = count.multiply(new BigInteger("2"));
			sb.append(count.subtract(new BigInteger("1")));
		}
		System.out.println(sb);
	}

	static void dfs(int N, int from, int to, int dest) {
		if (N == 1) {
			sb.append(from + " " + dest + "\n");
			return;
		}
		dfs(N - 1, from, dest, to);
		sb.append(from + " " + dest + "\n");
		dfs(N - 1, to, from, dest);
	}
}
