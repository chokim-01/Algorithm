import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] a = new int[N + 2][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			a[i][0] = Integer.parseInt(st.nextToken());
			a[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] d = new int[N + 2];
		for (int i = 1; i <= N + 1; i++) {
			d[i] = Math.max(d[i], d[i - 1]);
			int next = i + a[i][0];
			if (next <= N + 1)
				d[next] = Math.max(d[next], d[i] + a[i][1]);
		}
		System.out.println(d[N + 1]);

	}
}