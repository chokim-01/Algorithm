import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		long[] bits = new long[20]; // false : even true : odd
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			int b = 0;
			do
				if (((1 << b) & num) == (1 << b))
					bits[b]++;
			while (b++ < 20);
		}
		long ans = 0;
		for (int i = 19; i >= 0; i--)
			ans = (ans += bits[i] * (N - bits[i])) << 1;

		System.out.println(ans >> 1);
	}
}