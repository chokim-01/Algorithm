import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		sb = new StringBuilder();
		dfs(0, new int[M], new boolean[N]);

		System.out.println(sb);
	}

	static void dfs(int index, int[] choice, boolean[] visit) {
		if (index == M) {
			for (int c : choice)
				sb.append(c + " ");
			sb.append("\n");

			return;
		}
		int before = -1;
		for (int i = 0; i < N; i++) {
			if (before == arr[i])
				continue;
			before = arr[i];
			choice[index] = arr[i];

			dfs(index + 1, choice, visit);
		}
	}

	static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
