import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		double[] arr = new double[N + 1];
		int[] size = new int[N + 1];
		ArrayList<Integer> lst[] = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			lst[i] = new ArrayList<>();

		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			size[a]++;
			lst[b].add(a);
		}
		arr[1] = 100;
		for (int i = 2; i <= N; i++) {
			for (int next : lst[i]) {
				arr[i] += arr[next] / size[next];
				arr[next] -= arr[next] / size[next]--;
			}
		}
		double max = 0;
		for (double a : arr)
			max = Math.max(max, a);
		System.out.println(max);
	}
}