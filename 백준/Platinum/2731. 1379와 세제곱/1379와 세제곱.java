import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {

			int[] s = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

			int index = s.length - 1;
			BigDecimal bd = BigDecimal.ONE;
			if (s[index] == 3)
				bd = bd.multiply(BigDecimal.valueOf(7));
			else if (s[index] == 7)
				bd = bd.multiply(BigDecimal.valueOf(3));
			else if (s[index] == 9)
				bd = bd.multiply(BigDecimal.valueOf(9));

			// s.length -1 : bd.Index bd.length-1
			// ... s.length-2 : bd.Index bd.length-2
			// ... 0 :
			StringBuilder number = new StringBuilder(bd.toString());
			int bdIndex = 1;
			while (index-- > 0) {
				boolean choice = false;
				for (int i = 0; i < 10; i++) {
					BigDecimal b = new BigDecimal(String.valueOf(i) + number.toString());
					String bS = b.multiply(b).multiply(b).toString();

					if (bS.length() - bdIndex - 1 < 0)
						continue;
					if (s[index] == bS.charAt(bS.length() - bdIndex - 1) - '0') {
						number.insert(0, i);
						choice = true;
						break;
					}
				}
				if (!choice)
					number.insert(0, "0");
				bdIndex++;
			}
			sb.append(Long.valueOf(number.toString())).append("\n");
		}
		System.out.println(sb);
	}
}