import java.util.Scanner;

public class Main {
	static final int mod = 1000000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long n = sc.nextLong();

		int[] l = new int[15 * (mod / 10)];
		l[1] = 1;
		for (int i = 2; i < l.length; i++)
			l[i] = (l[i - 1] + l[i - 2]) % mod;
		System.out.println(l[(int) (n % (15 * mod / 10))]);
	}
}