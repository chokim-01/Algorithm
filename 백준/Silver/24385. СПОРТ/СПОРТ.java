import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] s = br.readLine().toCharArray();
		Arrays.sort(s);
		dfs(0, new char[s.length], new boolean[s.length], s);
		System.out.println(sb);
	}

	static void dfs(int index, char[] choice, boolean[] visit, char[] s) {
		if (index == choice.length) {
			sb.append(String.valueOf(choice)).append("\n");
			return;
		}
		for (int i = 0; i < s.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			choice[index] = s[i];
			dfs(index + 1, choice, visit, s);
			visit[i] = false;
		}

	}
}