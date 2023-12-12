import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int[] depths;
	static int[] parent;
	static int[] childCount;
	static ArrayList<Integer>[] link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		link = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			link[i] = new ArrayList<>();
		depths = new int[N + 1];
		parent = new int[N + 1];
		childCount = new int[N + 1];

		Arrays.fill(depths, -1);
		link[0].add(1);
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		dfs(1, 1);
		int[] go = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		ArrayList<Integer> save[] = new ArrayList[N + 1];
		ArrayList<Integer> saveParent[] = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			save[i] = new ArrayList<>();
			saveParent[i] = new ArrayList<>();
		}

		save[0].add(0);

		int bef = 0;
		for (int i = 1, idx; i <= N; i++) {
			idx = go[i - 1];

			if (bef > depths[idx]) {
				System.out.println(0);
				return;
			}
			while (childCount[idx]-- > 0)
				saveParent[depths[idx]].add(idx);
			save[depths[idx]].add(idx);
			bef = depths[idx];
		}
		for (int s = N; s > 1; s--) {
			if (save[s].isEmpty())
				continue;
			int idx = 0;
			for (int i = 0; i < save[s].size(); i++) {
				if (idx == saveParent[s - 1].size()) {
					System.out.println(0);
					return;
				}
				if (saveParent[s - 1].get(idx) != parent[save[s].get(i)]) {
					idx++;
					i--;
					continue;
				}
			}
		}
		System.out.println(1);
	}

	static void dfs(int now, int cnt) {
		depths[now] = cnt;
		for (int next : link[now]) {
			if (depths[next] != -1)
				continue;
			parent[next] = now;
			childCount[now]++;
			dfs(next, cnt + 1);

		}
	}
}
