import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static LinkedHashSet<Integer> visited;
	static boolean[] visit;
	static ArrayList<Integer>[] con;
	static HashSet<Integer> cycle;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new LinkedHashSet<>();
		visit = new boolean[N + 1];
		con = new ArrayList[N + 1];
		cycle = new HashSet<>();

		for (int i = 1; i <= N; i++)
			con[i] = new ArrayList<>();

		for (int i = 0, a, b; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			con[a].add(b);
			con[b].add(a);
		}

		findCycle(1, 0);

		for (int i = 1; i <= N; i++)
			System.out.print(bfs(i) + " ");
	}

	static void findCycle(int now, int before) {
		visited.add(now);
		for (int next : con[now]) {
			if (cycle.size() != 0)
				return;
			if (next == before)
				continue;
			if (visited.contains(next)) { // cycle add
				boolean f = false;
				for (int v : visited) {
					if (v == next)
						f = true;
					if (f) 
						cycle.add(v);
				}
				return;
			}
			findCycle(next, now);
		}
		visited.remove(now);
	}

	static int bfs(int x) {
		int time = 0;
		if (cycle.size() == 0 || cycle.contains(x))
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
					if (cycle.contains(next))
						return time;
					visit[next] = true;
					q.add(next);
				}
			}
		}
		return time;
	}
}