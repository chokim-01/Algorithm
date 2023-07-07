import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] tree;
	static int[] top, parent, size;
	static int[] in, out, depth;
	static boolean[] visit;
	static int[] depot;
	static ArrayList<Integer>[] link;
	static int count = 0;

	static class Node {
		int x, w;

		public Node(int x, int w) {
			this.x = x;
			this.w = w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		input(br);

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) { // update
				update(in[b], c);
			} else { // query
				ans.append(getAns(b, c)).append("\n");
			}
		}
		System.out.println(ans);
	}

	static int getAns(int a, int b) {
		int x = a;
		int y = b;
		int answer = 0;
		while (top[x] != top[y]) {
			if (depth[top[x]] < depth[top[y]]) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			answer ^= query(in[top[x]], in[x]);
			x = parent[top[x]];
		}
		if (in[x] > in[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		answer ^= query(in[x], in[y]);
		return answer;
	}

	static void setUpdate(int a, int b) {
		int x = a;
		int y = b;
		while (top[x] != top[y]) {
			if (depth[top[x]] < depth[top[y]]) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			update(in[top[x]], in[x]);
			x = parent[top[x]];
		}
		if (in[x] > in[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		update(in[x] + 1, in[y]);
	}

	static int query(int l, int r) {
		l += N;
		r += N + 1;
		int ans = 0;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				ans ^= tree[l++];
			}
			if ((r & 1) == 1) {
				ans ^= tree[--r];
			}
		}
		return ans;
	}

	static void update(int now, int v) {
		now = now + N;

		tree[now] = v;
		while ((now >>= 1) != 0)
			tree[now] = tree[now << 1] ^ tree[now << 1 | 1];
	}

	static void input(BufferedReader br) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());
		top = new int[N];
		parent = new int[N];
		size = new int[N];
		in = new int[N];
		out = new int[N];
		depth = new int[N];
		visit = new boolean[N];
		depot = new int[N];

		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		ArrayList<Integer>[] iLink = new ArrayList[N];
		link = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			link[i] = new ArrayList<>();
			iLink[i] = new ArrayList<>();
		}
		tree = new int[N << 1];
		for (int i = 1; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			iLink[a].add(b);
			iLink[b].add(a);
		}
		visit[1] = true;
		dfs(1, iLink);
		dfs1(1);
		dfs2(1);

		for (int i = 0; i < nums.length; i++)
			update(in[i + 1], nums[i]);

	}

	static void dfs(int now, ArrayList<Integer>[] iLink) {
		for (int next : iLink[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			link[now].add(next);
			dfs(next, iLink);
		}
	}

	static void dfs1(int now) {
		size[now] = 1;

		for (int i = 0; i < link[now].size(); i++) {
			int next = link[now].get(i);
			depth[next] = depth[now] + 1;
			parent[next] = now;
			dfs1(next);
			size[now] += size[next];
			if (size[link[now].get(0)] < size[next])
				Collections.swap(link[now], 0, i);
		}
	}

	static void dfs2(int now) {
		in[now] = ++count;
		depot[count - 1] = now;
		for (int next : link[now]) {
			top[next] = next == link[now].get(0) ? top[now] : next;
			dfs2(next);
		}
		out[now] = count;
	}
}
