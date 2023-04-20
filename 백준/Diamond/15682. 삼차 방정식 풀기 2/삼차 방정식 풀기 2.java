import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	static BigDecimal A, B, C, D;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			A = sc.nextBigDecimal();
			B = sc.nextBigDecimal();
			C = sc.nextBigDecimal();
			D = sc.nextBigDecimal();
			TreeSet<BigDecimal> set = new TreeSet<>();

			BigDecimal res = BigDecimal.valueOf(-1000000);
			if (D.compareTo(BigDecimal.ZERO) == 0) {
				res = BigDecimal.ZERO;
			} else {
				BigDecimal left = BigDecimal.valueOf(-1000001);
				BigDecimal right = BigDecimal.valueOf(1000001);
				BigDecimal mid = BigDecimal.ZERO;
				
				// A가 음수면 변환
				if (A.compareTo(BigDecimal.ZERO) < 0) {
					A = A.negate();
					B = B.negate();
					C = C.negate();
					D = D.negate();
				}
				int N = 0;
				while (N++ < 100) {
					mid = left.add(right).divide(BigDecimal.valueOf(2), MathContext.DECIMAL64);
					BigDecimal ress = A.multiply(mid.pow(3)).add(B.multiply(mid.pow(2))).add(C.multiply(mid)).add(D);
					if (ress.compareTo(BigDecimal.ZERO) <= 0) {
						left = mid;
					} else {
						right = mid;
					}
					res = mid;
				}
			}
			set.add(res.setScale(9, RoundingMode.DOWN));

			// 해 하나를 구했음
			// 2차방정식을 구함
			BigDecimal a = A;
			BigDecimal b = B.add(A.multiply(res));
			BigDecimal c = C.add(res.multiply(b));
			// b^2-4ac
			BigDecimal calc = b.pow(2).subtract(BigDecimal.valueOf(4).multiply(a).multiply(c));
			int compare = calc.compareTo(BigDecimal.ZERO);
			if (compare == 1) {
				// 서로 다른 두 근
				// route
				BigDecimal route = sqrt(calc);
				BigDecimal r1 = route.add(b.negate()).divide(BigDecimal.valueOf(2).multiply(a), MathContext.DECIMAL64);
				BigDecimal r2 = route.negate().add(b.negate()).divide(BigDecimal.valueOf(2).multiply(a),
						MathContext.DECIMAL64);
				set.add(r1.setScale(9, RoundingMode.DOWN));
				set.add(r2.setScale(9, RoundingMode.DOWN));
			} else if (compare == 0) {
				// 중근
				BigDecimal r1 = b.negate().divide(BigDecimal.valueOf(2).multiply(a), MathContext.DECIMAL64);
				set.add(r1.setScale(9, RoundingMode.DOWN));
			} else {
				// 근이 없다
			}
			for (BigDecimal s : set)
				sb.append(String.format("%.9f", s) + " ");

			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static BigDecimal sqrt(BigDecimal input) {
		return input.sqrt(MathContext.DECIMAL64);
	}
}