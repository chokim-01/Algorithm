import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		if (b - a == 0) {
			System.out.println(0);
			return;
		}
		int m = (int) Math.sqrt(b - a);
		int ans = m << 1;
		if (m * m == b - a)
			ans -= 1;
		else if (m * m < b - a && b - a <= m * m + m)
			;
		else
			ans += 1;
		System.out.println(ans);
	}
}