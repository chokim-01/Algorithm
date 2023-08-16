import java.util.Scanner;

public class Main {
	static final int mod = 998244353;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		long ans = 29030400;

		if (N == 1)
			ans = 1;
		else if (N < 14)
			ans = -1;
		else {
			for (int i = 15; i <= N; i++) {
				long b = (long) (Math.sqrt(i * 2 - 1));
				ans = (ans * ((i << 1) - b * b)) % mod;
			}
		}
		System.out.println(ans);
	}
}