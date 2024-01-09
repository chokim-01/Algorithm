import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
	static int N;
	static int[] nums;
	static HashMap<Character, Integer> map;
	static HashMap<Integer, Character> map2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nums = new int[100];
		N = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		map2 = new HashMap<>();
		for (char i = '0'; i <= '9'; i++) {
			map.put(i, i - '0');
			map2.put(i - '0', i);
		}
		for (char i = 'A'; i <= 'Z'; i++) {
			map.put(i, i - 'A' + 10);
			map2.put(i - 'A' + 10, i);
		}
		BigInteger[][] cnt = new BigInteger[50][2];
		for (int i = 0; i < 50; i++) {
			cnt[i][0] = BigInteger.ZERO;
			cnt[i][1] = BigInteger.valueOf(i);
		}
		StringBuilder[] sbs = new StringBuilder[N];
		while (N-- > 0) {
			sbs[N] = new StringBuilder(br.readLine()).reverse();
			BigInteger max = BigInteger.valueOf(35);
			BigInteger m = BigInteger.ONE;
			for (char c : sbs[N].toString().toCharArray()) {
				cnt[c - '0'][0] = cnt[c - '0'][0]
						.add(m.multiply(max).add(m.multiply(BigInteger.valueOf(map.get(c)).negate())));
				m = m.multiply(BigInteger.valueOf(36));
			}
		}
		Arrays.sort(cnt, Comparator.<BigInteger[], BigInteger>comparing(row -> row[0], Comparator.reverseOrder())
				.thenComparing(Comparator.comparing(row -> row[1])));
		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++)
			map.put((char) (cnt[i][1].intValue() + '0'), 35);

		BigInteger nums = BigInteger.ZERO;
		for (StringBuilder sb : sbs) {
			BigInteger m = BigInteger.ONE;
			for (char c : sb.toString().toCharArray()) {
				nums = nums.add(m.multiply(BigInteger.valueOf(map.get(c))));
				m = m.multiply(BigInteger.valueOf(36));
			}
		}
		BigInteger m = BigInteger.valueOf(36);
		StringBuilder answer = new StringBuilder();
		while (!nums.toString().equals("0")) {
//			System.out.print(nums%36 + " " );
			answer.append(map2.get((int) nums.mod(m).longValue()));
			nums = nums.divide(m);
		}
		System.out.println(answer.length() == 0 ? 0 : answer.reverse());
	}
}