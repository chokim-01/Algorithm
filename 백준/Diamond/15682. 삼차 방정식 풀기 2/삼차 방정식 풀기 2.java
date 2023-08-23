import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

/*

1
999999.9999999 -999999.999999991 -999999.999999991 999999.99999999999
-1.000000000 0.999999792 1.000000207

1
-9808.54648188 64555.01333346 800000.13 -543.66666687

 */
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
	public static BigDecimal A, B, C, D;

	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = new BigDecimal(st.nextToken());
			B = new BigDecimal(st.nextToken());
			C = new BigDecimal(st.nextToken());
			D = new BigDecimal(st.nextToken());
			BigDecimal start = BigDecimal.valueOf(-1000001);
			BigDecimal end = BigDecimal.valueOf(1000001);
			BigDecimal mid = BigDecimal.ZERO;
			int cnt = 100;
			if (A.compareTo(BigDecimal.ZERO) < 0) {
				A = A.multiply(BigDecimal.valueOf(-1));
				B = B.multiply(BigDecimal.valueOf(-1));
				C = C.multiply(BigDecimal.valueOf(-1));
				D = D.multiply(BigDecimal.valueOf(-1));
			}
			while (cnt-- > 0) { // 이분법으로 근사해 찾기
				mid = start.add(end).divide(BigDecimal.valueOf(2), MathContext.DECIMAL64);
				BigDecimal center = calc(mid);
				if (center.compareTo(BigDecimal.ZERO) <= 0) {
					start = mid;
				} else {
					end = mid;
				}

			}
			// 해 하나 찾으면 (x - k)(ax² + bx + c) 꼴로 만들 수 있음
			/*
			 * (x - k)(ax² + bx + c) = Ax³ + Bx² + Cx + D ax³ + bx² + cx - akx² - bkx - ck
			 * ax³ + (b - ak)x² + (c - bk)x - ck = 0
			 * 
			 * A = a B = b - ak, b = B + Ak C = c - bk, c = C + (B + Ak)k
			 */
			mid = start;
			HashSet<BigDecimal> set = new HashSet<>();
			BigDecimal b = B.add(mid.multiply(A));
			BigDecimal c = C.add(mid.multiply(b));
			BigDecimal disc = b.multiply(b).subtract(BigDecimal.valueOf(4).multiply(A).multiply(c)); // b² - 4ac

			set.add(start.setScale(9, RoundingMode.DOWN));

			if (disc.compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal distSqrt = disc.sqrt(MathContext.DECIMAL64);
				BigDecimal r1 = b.multiply(BigDecimal.valueOf(-1));
				r1 = r1.add(distSqrt);
				r1 = r1.divide(BigDecimal.valueOf(2).multiply(A), MathContext.DECIMAL64);
				BigDecimal r2 = b.multiply(BigDecimal.valueOf(-1));
				r2 = r2.subtract(distSqrt);
				r2 = r2.divide(BigDecimal.valueOf(2).multiply(A), MathContext.DECIMAL64);

				set.add(r1.setScale(9, RoundingMode.DOWN));
				set.add(r2.setScale(9, RoundingMode.DOWN));

			} else if (disc.compareTo(BigDecimal.ZERO) == 0) {
				BigDecimal r = b.multiply(BigDecimal.valueOf(-1));
				r = r.divide(BigDecimal.valueOf(2).multiply(A), MathContext.DECIMAL64);
				set.add(r.setScale(9, RoundingMode.DOWN));
			}

			ArrayList<BigDecimal> list = new ArrayList<>(set);

			Collections.sort(list);
			for (BigDecimal res : list) {
				bw.write(res.toPlainString() + " ");
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	public static BigDecimal calc(BigDecimal k) { // f(x)
		// Ax³ + Bx² + Cx + D
		BigDecimal res = A.multiply(k).multiply(k).multiply(k);
		res = res.add(B.multiply(k).multiply(k));
		res = res.add(C.multiply(k));
		res = res.add(D);
		return res;
	}

	public static BigDecimal calc1(BigDecimal k) { // f'(x)
		// 3Ax² + 2Bx + C
		BigDecimal res = BigDecimal.valueOf(3).multiply(A).multiply(k).multiply(k);
		res = res.add(BigDecimal.valueOf(2).multiply(B).multiply(k));
		res = res.add(C);
		return res;
	}

	public static BigDecimal newtonRaphson(BigDecimal k) {
		int cnt = 0;
		while (true) {
			BigDecimal r1 = calc(k);
			BigDecimal r2 = calc1(k);
			if (r2.compareTo(BigDecimal.ZERO) == 0) {
				return k;
			}
			k = k.subtract(r1.divide(r2, MathContext.DECIMAL64));
			BigDecimal res = calc(k);
			if (res.compareTo(BigDecimal.valueOf(-1.0E-20)) >= 0 && res.compareTo(BigDecimal.valueOf(1.0E-20)) <= 0
					|| cnt == 100) {
				return k;
			}
			cnt++;
		}
	}

	public static BigDecimal newtonRaphsonSqrt(BigDecimal k) {
		// √b² - 4ac의 근사값 구하기
		// √b² - 4ac = x² - (b² - 4ac) = 0
		// x² = b² - 4ac = k
		// f'(x) = 2x
		BigDecimal xn_1 = BigDecimal.valueOf(1);
		int cnt = 0;
		while (true) {
			BigDecimal xn = xn_1.multiply(xn_1);
			xn = xn.subtract(k);
			if (xn_1.compareTo(BigDecimal.ZERO) == 0) {
				return xn;
			}
			xn = xn.divide(xn_1.multiply(BigDecimal.valueOf(2)), MathContext.DECIMAL64);
			xn = xn_1.subtract(xn);
			BigDecimal res = k.subtract(xn.multiply(xn)).abs();
			if (res.compareTo(BigDecimal.valueOf(1.0E-20)) <= 0 || cnt == 100) {
				return xn;
			}
			cnt++;
			xn_1 = xn;
		}
	}
}