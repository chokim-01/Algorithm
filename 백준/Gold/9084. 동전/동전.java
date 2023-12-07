import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(coins);
			int M = Integer.parseInt(br.readLine());
			int[] d = new int[M + 1];
			d[0] = 1;
			for (int c : coins) {
				for (int i = 0; i <= M; i++)
					if (i - c >= 0)
						d[i] += d[i - c];
			}
			System.out.println(d[M]);
		}
	}
}