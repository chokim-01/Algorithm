import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();

		int k = K - 2;
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = 0;
			if (k == -1) {
				c = a - b < 0 ? b - a : a - b;
			} else {
				while (a != b) {
					if (a > b) {
						long tmp = a;
						a = b;
						b = tmp;
					}
					b = (b + k) / K;
					c++;
				}
			}
			sb.append(c).append("\n");
		}
		System.out.println(sb);
	}
}