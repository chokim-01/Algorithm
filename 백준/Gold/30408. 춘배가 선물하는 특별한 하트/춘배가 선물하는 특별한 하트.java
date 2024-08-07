import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long M = sc.nextLong();

		long left = M;
		long right = M;
		boolean ans = false;
		while (left <= N) {
			if (left <= N && N <= right) {
				ans = true;
				break;
			}
			left = left * 2 - 1;
			right = right * 2 + 1;
		}
		System.out.println(ans ? "YES" : "NO");
	}
}