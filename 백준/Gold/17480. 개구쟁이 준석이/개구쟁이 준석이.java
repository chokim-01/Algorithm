import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static HashSet<String> save;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = find(br);
		System.out.println(ans);
	}

	static int find(BufferedReader br) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int[] alphabets = new int[26];
		save = new HashSet<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int tot = 0;
		while (N-- > 0) {
			char c = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			alphabets[c - 'a'] = cnt;
			tot += cnt;
		}
		int[] al = new int[26];
		String s = br.readLine();
		outer: for (int i = 0; i <= s.length() - tot; i++) {
			if (alphabets[s.charAt(i) - 'a'] == 0)
				continue;
			al = alphabets.clone();
			for (int j = i; j < i + tot; j++)
				if (--al[s.charAt(j) - 'a'] < 0)
					continue outer;
			dfs(0, tot, new StringBuilder(s.substring(i, i + tot)));
		}
		return save.size();
	}

	static void dfs(int s, int e, StringBuilder sb) {
		if (e - s == 1) {
			save.add(sb.toString());
			return;
		}
		int mid = (s + e) >> 1;
		for (int i = 0, m; i <= s + e - mid * 2; i++) {
			m = i + mid;
			StringBuilder left = new StringBuilder(sb.substring(s, m));
			StringBuilder right = new StringBuilder(sb.substring(m, e));
			StringBuilder lNext = new StringBuilder(sb).replace(s, m, left.reverse().toString());
			StringBuilder rNext = new StringBuilder(sb).replace(m, e, right.reverse().toString());
			dfs(m, e, lNext);
			dfs(s, m, rNext);
		}
	}
}