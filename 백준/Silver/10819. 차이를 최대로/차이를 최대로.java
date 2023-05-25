import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	static int N, ans;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		ans = 0;

		dfs(0, 0);
		System.out.println(ans);
	}

	static void dfs(int index, int val) {
		if (index == N) {
			int cnt = 0;
			for (int i = 0; i < N - 1; i++)
				cnt += Math.abs(arr[i] - arr[i + 1]);
			
			ans = ans < cnt ? cnt : ans;
			return;
		}
		for (int i = index; i < N; i++) {
			swap(index, i);
			dfs(index + 1, val + arr[index]);
			swap(index, i);
		}
	}

	static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
