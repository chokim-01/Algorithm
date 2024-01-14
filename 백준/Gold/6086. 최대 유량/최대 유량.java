import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class Main {
	static int N;
	static int[][] link;

	static class Node {
		int x, c;

		public Node(int x, int c) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.c = c;
		}
	}

	static Map<Character, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		link = new int[100][100];
		map.put('A', 0);
		map.put('Z', 1);
		int idx = 2;
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			if (!map.containsKey(a))
				map.put(a, idx++);
			if (!map.containsKey(b))
				map.put(b, idx++);
			int numA = map.get(a);
			int numB = map.get(b);
			int c = Integer.parseInt(st.nextToken());
			link[numA][numB] += c;
			link[numB][numA] += c;
		}
		int ans = 0;
		while (true) {
			Queue<Integer> q = new ArrayDeque<>();
			q.add(0);

			int[] save = IntStream.range(0, idx).map(x -> -1).toArray();
			outer: while (!q.isEmpty()) {
				int now = q.poll();
				for (int next = 1; next < idx; next++) {
					if (save[next] != -1 || link[now][next] == 0)
						continue;
					save[next] = now;
					if (next == 1)
						break outer;
					q.add(next);
				}
			}
			if (save[1] == -1)
				break;

			int min = Integer.MAX_VALUE;
			int now = 1;
			while (save[now] != -1) {
				min = Math.min(min, link[save[now]][now]);
				now = save[now];
			}
			ans += min;
			now = 1;
			while (save[now] != -1) {
				link[save[now]][now] -= min;
				link[now][save[now]] += min;
				now = save[now];
			}
		}
		System.out.println(ans);

	}
}
