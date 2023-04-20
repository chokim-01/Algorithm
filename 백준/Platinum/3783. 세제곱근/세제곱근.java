import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			BigDecimal c = sc.nextBigDecimal();
			BigDecimal res = BigDecimal.ONE;
			MathContext mc = new MathContext(200);
			int N = 0;

			while (true) {
				BigDecimal prev = res;
				res = res.subtract(res.pow(3).subtract(c, mc).divide(new BigDecimal("3").multiply(res.pow(2), mc), mc),
						mc);
				if (prev.subtract(res).abs().compareTo(
						new BigDecimal("0.0000000000000000000000000000000000000000000000000000000001")) < 0) {
					break;

				}

			}
			sb.append(res.setScale(10, RoundingMode.DOWN)).append("\n");
		}
		System.out.println(sb);
	}

}