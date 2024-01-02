import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(calc(b) ^ calc(a - 1)).append("\n");
		}
		System.out.println(sb);
	}

	static int calc(int n) {
		int ret = 0;
		int dv = n / 4 * 4;
		for (int i = dv; i <= n; i++)
			ret ^= i;
		return ret;
	}
}
