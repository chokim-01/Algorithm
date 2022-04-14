import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static long D, P, Q;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		D = sc.nextLong();
		P = sc.nextLong();
		Q = sc.nextLong();
		if (P < Q) {
			long tmp = P;
			P = Q;
			Q = tmp;
		}
		long g = gcd(P,Q);
		long a = P / g;
		long b = Q / g;
		if(gcd(P,Q) * (a-1) * (b-1) <= D) {
			System.out.println((long)Math.ceil(D/(double)g)*g);
			return;
		}
		long ans = Integer.MAX_VALUE;
		a = D/P + 1;
		while (a != -1) {
			long ap = a * P;
			b = (D - ap) / Q;
			if (D <= ap)
				b = 0;
			else if (ap + b * Q < D)
				b += 1;

			long c = ap + b * Q;
			ans = ans > c ? c : ans;
			if (ans == D)
				break;

			a--;
		}
		System.out.println(ans);
	}

	static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

}