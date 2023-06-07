import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N;
	static final int mod = 1000000007;
	static long[] pac;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		pac = new long[4000001];
		pac[0] = 1;
		for (int i = 1; i < pac.length; i++)
			pac[i] = (pac[i - 1] * i) % mod;

		N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = getOff((pac[b] * pac[a - b]) % mod, mod - 2);
			ans.append((pac[a] * c) % mod).append("\n");
		}
		System.out.println(ans);
	}

	static long getOff(long n, int m) {
		if (m == 0)
			return 1;
		long r = getOff(n, m / 2);
		if ((m & 1) == 1)
			return (((r * r) % mod) * n) % mod;
		else
			return (r * r) % mod;
	}
}
