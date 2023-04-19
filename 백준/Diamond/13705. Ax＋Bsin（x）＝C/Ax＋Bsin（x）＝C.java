import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class Main {
	static BigDecimal A, B, C;
	static BigDecimal pi = new BigDecimal("3.14159265358979323846264338327950288419716939937510582097");

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		A = BigDecimal.valueOf(sc.nextInt());
		B = BigDecimal.valueOf(sc.nextInt());
		C = BigDecimal.valueOf(sc.nextInt());
		System.out.println(eBunTamSaek(A, B, C).setScale(6, BigDecimal.ROUND_HALF_UP));
	}

	static BigDecimal eBunTamSaek(BigDecimal A, BigDecimal B, BigDecimal C) {
		BigDecimal left = BigDecimal.ZERO;
		BigDecimal right = BigDecimal.valueOf(100000);
		BigDecimal mid = BigDecimal.ZERO;
		int n = 100;
		while (n-- > 0) {
			mid = left.add(right).divide(BigDecimal.valueOf(2));
			BigDecimal res = A.multiply(mid).add(B.multiply(sin(mid)));
			if (res.compareTo(C) <= 0) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return mid;
	}

	static BigDecimal sin(BigDecimal bd) {
		bd = bd.remainder(pi.multiply(new BigDecimal(2)));
		int n = 0;
		BigDecimal term = bd;
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal bunza = bd;
		BigDecimal bunmo = BigDecimal.ONE;
		BigDecimal sign = BigDecimal.ONE;

		while (n++ < 50) {
			result = result.add(sign.multiply(bunza.divide(bunmo, MathContext.DECIMAL128)));
			bunza = bunza.multiply(term).multiply(term);
			bunmo = bunmo.multiply(new BigDecimal((2 * n) * (2 * n + 1)));
			sign = sign.negate();
		}
		return result;
	}
}