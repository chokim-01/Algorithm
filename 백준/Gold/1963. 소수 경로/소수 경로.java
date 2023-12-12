import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] prime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		prime = new boolean[10000];
		Arrays.fill(prime, true);
		prime[1] = false;
		for (int i = 2; i < 10000; i++) {
			if (!prime[i])
				continue;
			for (int j = i * i; j < 10000; j += i)
				prime[j] = false;
		}

		StringBuilder ans = new StringBuilder();
		outer: while (N-- > 0) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();

			boolean[] v = new boolean[10000];
			Queue<StringBuilder> q = new ArrayDeque<>();
			q.add(new StringBuilder(a));
			v[Integer.parseInt(a)] = true;
			int c = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				for (int s = 0; s < size; s++) {
					StringBuilder now = q.poll();
					if (now.toString().equals(b)) {
						ans.append(c).append("\n");
						continue outer;
					}
					for (int d = 0; d < 4; d++) {
						for (int i = d == 0 ? 1 : 0; i < 10; i++) {
							char ch = (char) (48 + i);
							StringBuilder next = new StringBuilder(now);
							next.setCharAt(d, ch);
							int iNext = Integer.parseInt(next.toString());
							if (v[iNext] || !prime[iNext])
								continue;
							v[iNext] = true;
							q.add(next);
						}
					}
				}
				c++;
			}
			ans.append("Impossible").append("\n");
		}
		System.out.println(ans);

	}
}