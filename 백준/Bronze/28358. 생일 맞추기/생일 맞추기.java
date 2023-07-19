import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] month = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			boolean[] arr = new boolean[10];
			int idx = 0;
			while (st.hasMoreTokens())
				arr[idx++] = Integer.parseInt(st.nextToken()) == 1 ? false : true;

			int ans = 0;

			for (int i = 1; i < month.length; i++)
				for (int j = 1; j <= month[i]; j++)
					if (chk(i, arr) && chk(j, arr))
						ans++;
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static boolean chk(int n, boolean[] b) {
		if (n >= 10)
			return b[n / 10] && b[n % 10];
		return b[n];
	}
}