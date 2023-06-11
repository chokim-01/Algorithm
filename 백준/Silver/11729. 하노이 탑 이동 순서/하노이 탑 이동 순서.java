import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int cnt = 0;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dfs(N, 1, 2, 3);
		answer.insert(0, cnt + "\n");
		System.out.println(answer);
	}

	static void dfs(int n, int a, int b, int c) {
		if (n == 1) {
			cnt++;
			answer.append(a + " " + c).append("\n");
			return;
		}
		dfs(n - 1, a, c, b);
		cnt++;
		answer.append(a + " " + c).append("\n");
		dfs(n - 1, b, a, c);
	}
}