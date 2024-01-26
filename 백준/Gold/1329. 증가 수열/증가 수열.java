import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static String[] ans, save;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ans = new String[81];
		save = new String[81];
		Arrays.fill(ans, "-1");
		String s = sc.next();
		String end = "";
		boolean flag = false;
		BigInteger bef = BigInteger.ONE;
		StringBuilder last = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			last.insert(0, s.charAt(i));
			BigInteger num = new BigInteger(last.toString());
			if (bef.compareTo(num) != 0 && flag) {
				break;
			}
			Arrays.fill(save, "-1");
			if (dfs(0, i, s, BigInteger.ONE.negate(), num, 0)) {
				for (int j = 0; j < 81; j++) {
					int v = new BigInteger(ans[j]).compareTo(new BigInteger(save[j]));
					if (v == -1) {
						ans = save.clone();
						end = last.toString();
						break;
					} else if (v == 0)
						continue;
					else
						break;
				}
				flag = true;
			}
			bef = num;
		}
		StringBuilder sb = new StringBuilder();
		int idx = 0;

		if (ans[0].equals("-1") || BigInteger.ZERO.compareTo(new BigInteger(ans[0])) == 0) {
			System.out.println(s);
		} else {
			while (idx < 81 && !ans[idx].equals("-1"))
				sb.append(ans[idx++]).append(",");
			sb.append(end);
			System.out.println(sb);
		}
	}

	static boolean dfs(int sIdx, int sEnd, String s, BigInteger bef, BigInteger end, int idx) {
		if (sIdx == sEnd)
			return true;
		StringBuilder now = new StringBuilder(s.substring(sIdx, sEnd + 1));
		for (int i = sEnd; i > sIdx; i--) {
			now.delete(now.length() - 1, now.length());
			BigInteger num = new BigInteger(now.toString());
			if (num.compareTo(end) == -1 && num.compareTo(bef) == 1) {
				save[idx] = now.toString();
				if (dfs(i, sEnd, s, num, end, idx + 1))
					return true;
			}
		}
		return false;
	}
}