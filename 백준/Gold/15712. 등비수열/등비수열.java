import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int A, R, N, Mod;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Mod = Integer.parseInt(st.nextToken());

		long ans = dfs(N, N / 2);
		System.out.println(ans * A % Mod);
	}

	static long pow(long n, long r) {
		if (r == 0)
			return 1;
		long res = pow(n, r / 2);
		res = res * res % Mod;

		if (r % 2 == 1)
			return res * n % Mod;

		return res;
	}

	static long dfs(int len, int mul) {
		if (len == 1)
			return 1;

		// left
		long left = dfs(len / 2, mul / 2);

		// right
		long m = pow(R, mul == 0 ? 1 : mul) % Mod;
		long right = m * left % Mod;
		if (len / 2 != len - len / 2)
			right += m * pow(R, len - len / 2 - 1) % Mod;

		return (left + right) % Mod;
	}
}