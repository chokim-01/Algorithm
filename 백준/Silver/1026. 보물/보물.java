import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		int[] a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] b = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(a);
		int ans = 0;
		for (int n : a)
			ans += n * findMax(b);

		System.out.println(ans);
	}

	static int findMax(int[] a) {
		int idx = 0;
		int max = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > max) {
				max = a[i];
				idx = i;
			}
		}
		a[idx] = -1;
		return max;
	}
}