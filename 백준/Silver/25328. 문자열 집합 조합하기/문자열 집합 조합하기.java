import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N;
	static HashSet<String> set;
	static TreeSet<String> ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] strs = new String[3];
		for (int i = 0; i < 3; i++)
			strs[i] = br.readLine();
		N = Integer.parseInt(br.readLine());

		set = new HashSet<>();
		ans = new TreeSet<>();

		for (String s : strs)
			dfs(s.toCharArray(), new char[N], 0, 0);

		StringBuilder sb = new StringBuilder();
		if (ans.isEmpty())
			sb.append(-1);
		else
			for (String s : ans)
				sb.append(s).append("\n");
		System.out.println(sb);

	}

	static void dfs(char[] s, char[] choice, int index, int cnt) {
		if (cnt == N) {
			String g = String.valueOf(choice);
			if (set.contains(g))
				ans.add(g);
			else
				set.add(g);
			return;
		}
		for (int i = index; i < s.length; i++) {
			choice[cnt] = s[i];
			dfs(s, choice, i + 1, cnt + 1);
		}

	}
}