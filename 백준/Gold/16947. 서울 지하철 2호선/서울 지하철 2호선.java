import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] cycle;
	static boolean[] visit;
	static ArrayList<Integer>[] con;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		cycle = new boolean[N + 1];
		visit = new boolean[N + 1];
		con = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++)
			con[i] = new ArrayList<>();

		for (int i = 0, a, b; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			con[a].add(b);
			con[b].add(a);
		}

		findCycle(1, 0, new boolean[N + 1]);

		for (int i = 1; i <= N; i++)
			System.out.print(bfs(i) + " ");
	}

	static boolean findCycle(int now, int before, boolean[] visit) {
		visit[now] = true;
		for (int next : con[now]) {
			if (next == before)
				continue;
			if (visit[next]) {
				visit[next] = false;
				cycle[next] = true;
				return true;
			} else {
				if (findCycle(next, now, visit)) {
					if (visit[now]) { // cycle add
						cycle[next] = true;
						return true;
					} else {
						visit[now] = true;
						cycle[next] = true;
						return false;
					}
				}
			}
		}
		return false;
	}

	static int bfs(int x) {
		int time = 0;
		if (cycle[x])
			return time;

		visit = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(x);
		visit[x] = true;

		while (!q.isEmpty()) {
			time++;
			int size = q.size();
			while (size-- > 0) {
				int now = q.poll();
				for (int next : con[now]) {
					if (visit[next])
						continue;
					if (cycle[next])
						return time;
					visit[next] = true;
					q.add(next);
				}
			}
		}
		return time;
	}
}