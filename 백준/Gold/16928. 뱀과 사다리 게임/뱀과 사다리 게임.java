import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] to = new int[101];

		while (N-- + M > 0) {
			st = new StringTokenizer(br.readLine());
			to[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}

		int count = 0;
		boolean[] v = new boolean[101];
		Queue<Integer> q = new ArrayDeque<>();
		v[1] = true;
		q.add(1);
		outer: while (!q.isEmpty()) {
			count++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int now = q.poll();
				for (int d = 1; d <= 6; d++) {
					int next = now + d;
					if (next > 100)
						break;
					if (to[next] != 0)
						next = to[next];
					if (v[next])
						continue;
					v[next] = true;
					q.add(next);
					if (next == 100)
						break outer;
				}
			}
		}
		System.out.println(count);
	}

}
