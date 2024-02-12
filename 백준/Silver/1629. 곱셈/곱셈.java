import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());
		System.out.println(dfs(a, b, c));
	}

	static long dfs(long a, long b, long c) {
		if (b == 1)
			return a % c;

		long r = dfs(a, b / 2, c) % c;
		if (b % 2 == 1)
			return ((r * r % c) * a) % c;
		return r * r % c;
	}
}
