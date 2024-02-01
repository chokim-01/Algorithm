import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] v;
	static int[][] bef;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder ans = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			v = new boolean[10000];
			bef = new int[10000][2];
			v[start] = true;
			bef[start][0] = -1;
			bef[start][1] = -1;
			q = new ArrayDeque<>();
			q.add(start);
			outer: while (!q.isEmpty()) {
				int now = q.poll();
				for (int d = 0; d < 4; d++) {
					int next = make(now, d);
					if (v[next])
						continue;
					v[next] = true;
					bef[next][0] = now;
					bef[next][1] = d;
					if (next == end)
						break outer;
					q.add(next);
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int now = end; bef[now][0] != -1; now = bef[now][0]) {
				switch (bef[now][1]) {
				case 0:
					sb.append('D');
					break;
				case 1:
					sb.append('S');
					break;
				case 2:
					sb.append('L');
					break;
				case 3:
					sb.append('R');
					break;
				}
			}
			ans.append(sb.reverse()).append("\n");
		}
		System.out.println(ans);

	}

	static int make(int v, int d) {
		if (d == 0)
			return v * 2 % 10000;
		else if (d == 1)
			return v == 0 ? 9999 : v - 1;
		else if (d == 2) {
			int n = v / 1000;
			v %= 1000;
			return v * 10 + n;
		} else {
			int n = v % 10;
			return v / 10 + n * 1000;
		}
	}
}