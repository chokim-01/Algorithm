import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] snow = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			snow[i] = Integer.parseInt(st.nextToken());

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 1, 0 });
		int ans = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			ans = ans < now[1] ? now[1] : ans;
			if (now[2] == M)
				continue;
			// 1
			if (now[0] + 1 <= N)
				q.add(new int[] { now[0] + 1, now[1] + snow[now[0] + 1], now[2] + 1 });
			if (now[0] + 2 <= N)
				q.add(new int[] { now[0] + 2, now[1] / 2 + snow[now[0] + 2], now[2] + 1 });
		}
		System.out.println(ans);

	}
}