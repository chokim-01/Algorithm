import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		br.readLine();
		
		long[] prime = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		long K = Long.parseLong(br.readLine());

		long ans = 0;
		for (long p : prime) {
			long n = p;
			while (n <= K) {
				ans += K / n;
				n *= p;
			}
		}
		System.out.println(ans);
	}
}