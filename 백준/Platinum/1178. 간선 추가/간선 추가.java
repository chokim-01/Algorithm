import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static boolean[] v;
	static int[] counts;
	static ArrayList<Integer>[] link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		v = new boolean[V + 1];
		link = new ArrayList[V + 1];

		for (int i = 1; i <= V; i++)
			link[i] = new ArrayList<>();

		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			link[a].add(b);
			link[b].add(a);
		}
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int uf = -1;
		for (int i = 1; i <= V; i++) {
			if (v[i])
				continue;
			ArrayList<Integer> lst = new ArrayList<>();
			uf++;
			v[i] = true;
			dfs(i, lst);
			list.add(lst);
		}
		int a = 0;
		for (ArrayList<Integer> lst : list) {
			int c = 0;
			for (int l : lst)
				if ((link[l].size() & 1) == 1)
					c++;
			if (c <= 2);
			else
				a += (c - 2) / 2;
		}
		System.out.println(a + uf);
	}

	static void dfs(int now, ArrayList<Integer> list) {
		list.add(now);
		for (int next : link[now]) {
			if (v[next])
				continue;
			v[next] = true;
			dfs(next, list);
		}
	}
}