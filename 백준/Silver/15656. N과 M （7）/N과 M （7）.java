import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] numbers;
	static StringBuilder ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
		ans = new StringBuilder();
		dfs(new int[M], 0);
		System.out.println(ans);

	}

	static void dfs(int[] choice, int cnt) {
		if (cnt == M) {
			for (int c : choice)
				ans.append(c + " ");
			ans.append("\n");
			return;
		}
		for (int i = 0; i < numbers.length; i++) {
			
			choice[cnt] = numbers[i];
			dfs(choice, cnt + 1);
			choice[cnt] = 0;
		}
	}

}