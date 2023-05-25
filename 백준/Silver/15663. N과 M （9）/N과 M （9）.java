import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] arr;
	static StringBuilder sb;
	static LinkedHashSet<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		set = new LinkedHashSet<>();
		sb = new StringBuilder();
		dfs(0, new int[M], new boolean[N]);
		
		set.forEach(k -> {
			for (char c : k.toCharArray())
				if (c == '|')
					sb.append(" ");
				else
					sb.append(c);
			sb.append("\n");
		});
		System.out.println(sb);
	}

	static void dfs(int index, int[] choice, boolean[] visit) {
		if (index == M) {
			set.add(Arrays.toString(choice).replaceAll("[^0-9 ]", "").toString().replace(" ", "|"));

			return;
		}

		for (int i = 0; i < N; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			choice[index] = arr[i];
			dfs(index + 1, choice, visit);
			visit[i] = false;
		}

	}

	static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
