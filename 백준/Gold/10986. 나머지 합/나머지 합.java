import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] arr = new long[M];
		long sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0, n; i < N; i++) {
			n = Integer.parseInt(st.nextToken());
			arr[(int) ((sum += n) % M)]++;
		}
		long ans = 0;
		ans += arr[0];
		for (int i = 0; i < M; i++)
			ans += arr[i] * (arr[i] - 1) / 2;

		System.out.println(ans);

		System.out.println();
	}
}