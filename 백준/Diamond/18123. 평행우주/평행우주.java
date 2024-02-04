import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] link;
	static HashMap<ArrayList<Integer>, Integer> map;
	static ArrayList<Integer> center;
	static int[] size;
	static int N, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new HashMap<>();
		c = 0;

		HashSet<Integer> trees[] = new HashSet[31];
		for (int i = 0; i <= 30; i++)
			trees[i] = new HashSet<>();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			makeLink(br);

			size = new int[N];
			center = new ArrayList<>();
			findSentroid(0, -1);

			int go = center.get(0);
			if (center.size() == 2) {
				go = 30;
				link[go].add(center.get(0));
				link[go].add(center.get(1));
				link[center.get(0)].remove(Integer.valueOf(center.get(1)));
				link[center.get(1)].remove(Integer.valueOf(center.get(0)));
			}

			int hashCode = dfs(go, new boolean[31]);
			trees[N].add(hashCode);
		}
		int ans = 0;
		for (int i = 0; i <= 30; i++)
			ans += trees[i].size();
		System.out.println(ans);
	}

	static void findSentroid(int now, int p) {
		size[now] = 1;
		int mx = 0;
		for (int next : link[now]) {
			if (next == p)
				continue;
			findSentroid(next, now);
			size[now] += size[next];
			mx = Math.max(mx, size[next]);
		}
		if (Math.max(mx, N - size[now]) <= N / 2)
			center.add(now);

	}

	static int dfs(int now, boolean[] v) {
		v[now] = true;
		ArrayList<Integer> list = new ArrayList<>();
		for (int next : link[now]) {
			if (v[next])
				continue;
			list.add(dfs(next, v));
		}

		Collections.sort(list);
		if (!map.containsKey(list))
			map.put(list, ++c);
		return map.get(list);
	}

	static void makeLink(BufferedReader br) throws IOException {
		link = new ArrayList[31];
		for (int i = 0; i <= 30; i++)
			link[i] = new ArrayList<>();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
	}
}