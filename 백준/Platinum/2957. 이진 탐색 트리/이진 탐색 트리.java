import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		TreeSet<Integer> set = new TreeSet<>();
		set.add(0);
		set.add(N + 1);
		
		int[] arr = new int[N + 2];
		Arrays.fill(arr, -1);
		long cnt = 0;
		StringBuilder ans = new StringBuilder();

		while (N-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int l = arr[set.lower(n)];
			int r = arr[set.higher(n)];
			set.add(n);
			ans.append(cnt += arr[n] = (l < r ? r : l) + 1).append("\n");
		}
		System.out.println(ans);
	}
}