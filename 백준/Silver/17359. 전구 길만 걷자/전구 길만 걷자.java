import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int min = Integer.MAX_VALUE;
	static char[][] sec;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sec = new char[N][2];
		visit = new boolean[N];
		String[] s = new String[N];

		int sum = 0;
		while (N-- > 0) {
			s[N] = br.readLine();
			sum += calcCounts(s[N]);
			sec[N][0] = s[N].charAt(0);
			sec[N][1] = s[N].charAt(s[N].length() - 1);
		}
		dfs(0, -1, '\0');
		System.out.println(min + sum);
	}

	static void dfs(int idx, int cnt, char bef) {
		if(cnt >= min)
			return;
		if (idx == sec.length) {
			min = cnt < min ? cnt : min;
			return;
		}
		for (int i = 0; i < sec.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			dfs(idx + 1, cnt + (bef != sec[i][0] ? 1 : 0), sec[i][1]);
			visit[i] = false;
		}
	}

	static int calcCounts(String s) {
		int cnt = -1;
		char bef = '\0';
		for (char c : s.toCharArray()) {
			if (bef == c)
				continue;
			bef = c;
			cnt++;
		}
		return cnt;
	}
}