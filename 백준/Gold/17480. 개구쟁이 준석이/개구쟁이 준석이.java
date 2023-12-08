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
			dfs(0, tot, new StringBuilder(s.substring(i, i + tot)), 0);
		}

		return save.size();
	}

	static void dfs(int s, int e, StringBuilder sb, int seq) {
		if (e - s == 1) {
			save.add(sb.toString());
			return;
		}
		int mid = (s + e) >> 1;
		StringBuilder left = new StringBuilder(sb.substring(s, mid));
		StringBuilder right = new StringBuilder(sb.substring(mid, e));
		StringBuilder lNext = new StringBuilder(sb).replace(s, mid, new StringBuilder(left).reverse().toString());
		StringBuilder rNext = new StringBuilder(sb).replace(mid, e, new StringBuilder(right).reverse().toString());
		dfs(mid, e, lNext, 1);
		dfs(s, mid, rNext, 2);
		if ((mid << 1) != s + e) {
			mid += 1;
			left = new StringBuilder(sb.substring(s, mid));
			right = new StringBuilder(sb.substring(mid, e));
			lNext = new StringBuilder(sb).replace(s, mid, new StringBuilder(left).reverse().toString());
			rNext = new StringBuilder(sb).replace(mid, e, new StringBuilder(right).reverse().toString());
			dfs(mid, e, lNext, 1);
			dfs(s, mid, rNext, 2);
		}
	}
}