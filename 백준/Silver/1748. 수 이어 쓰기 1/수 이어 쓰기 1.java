import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long ans = 0;
		int c = 1;
		int m = 1;
		while ((m *= 10) <= N)
			ans += (m == 10 ? m : m * 0.9) * c++;
		if (m != 10)
			ans--;
		ans += (N - m / 10 + 1) * c;

		System.out.println(ans);
	}
}