import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();

		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] v = new boolean[N + 1];
		q.add(1);
		v[1] = true;
		int ans = 0;
		for (int f : link[1]) {
			q.add(f + (ans - ans++));
			v[f] = true;
		}
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int next : link[now]) {
				if (v[next])
					continue;
				v[next] = true;
				ans++;
			}
		}
		System.out.println(ans);

	}
}