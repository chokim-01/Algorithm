import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int C;
	static int count;
	static String ans;
	static Character[] chars;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String sa = br.readLine();
			if(sa == null)
				break;
			StringTokenizer st = new StringTokenizer(sa);
			if (!st.hasMoreTokens())
				break;
			String s = st.nextToken();
			chars = s.chars().sorted().mapToObj(c -> (char) c).toArray(Character[]::new);
			C = Integer.parseInt(st.nextToken());
			count = 0;
			ans = "";
			dfs(new char[chars.length], new boolean[chars.length], 0);
			sb.append(s).append(" ").append(C).append(" = ");
			if(ans.isEmpty())
				ans = "No permutation";
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(char[] choice, boolean[] visit, int cnt) {
		if (!ans.equals(""))
			return;
		if (cnt == visit.length) {
			count++;
			if (count == C) {
				ans = String.valueOf(choice);
				return;
			}
		}

		for (int i = 0; i < visit.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			choice[cnt] = chars[i];
			dfs(choice, visit, cnt + 1);
			choice[cnt] = '\0';
			visit[i] = false;
		}
	}

}