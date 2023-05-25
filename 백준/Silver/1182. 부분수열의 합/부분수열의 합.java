import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	static int N, S, ans;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		S = input[1];
		ans = 0;
		if (S == 0)
			ans--;
		dfs(0, 0);
		System.out.println(ans);
	}

	static void dfs(int index, int val) {
		if (index == N) {
			if (val == S)
				ans++;
			return;
		}

		dfs(index + 1, val + arr[index]);
		dfs(index + 1, val);

	}
}
