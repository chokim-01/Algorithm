import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] tree;
	static HLD hld;
	static UF uf;
	static Queue<Node> order;

	static int count = 0;

	static class UF {
		int[] parent;

		public UF() {
			// TODO Auto-generated constructor stub
			parent = new int[N];
			for (int i = 1; i < N; i++)
				parent[i] = i;
		}

		boolean union(int a, int b) {
			a = find(a);
			b = find(b);
			if (a == b)
				return false;
			if (a < b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			parent[a] = b;
			return true;
		}

		int find(int x) {
			if (x == parent[x])
				return x;
			return parent[x] = find(parent[x]);
		}

	}

	static class HLD {
		int[] top, parent, size;
		int[] in, out, depth;
		boolean[] visit;
		int[] depot;
		ArrayList<Integer>[] link;

		public HLD() {
			// TODO Auto-generated constructor stub
			top = new int[N];
			parent = new int[N];
			size = new int[N];
			in = new int[N];
			out = new int[N];
			depth = new int[N];
			visit = new boolean[N];
			depot = new int[N];
			link = new ArrayList[N];
		}

		void dfs(int now, ArrayList<Integer>[] iLink) {
			for (int next : iLink[now]) {
				if (visit[next])
					continue;
				visit[next] = true;
				link[now].add(next);
				dfs(next, iLink);
			}
		}

		void dfs1(int now) {
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

		void dfs2(int now) {
			in[now] = ++count;
			depot[count - 1] = now;
			for (int next : link[now]) {
				top[next] = next == link[now].get(0) ? top[now] : next;
				dfs2(next);
			}
			out[now] = count;
		}

		int getAns(int a, int b) {
			int x = a;
			int y = b;
			int answer = 0;
			while (top[x] != top[y]) {
				if (depth[top[x]] < depth[top[y]]) {
					int tmp = x;
					x = y;
					y = tmp;
				}
				answer += query(in[top[x]], in[x]);
				x = parent[top[x]];
			}
			if (in[x] > in[y]) {
				int tmp = x;
				x = y;
				y = tmp;
			}
			return answer += query(in[x], in[y]);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		groundWork(br);

		uf = new UF();
		StringBuilder answer = new StringBuilder();
		while (!order.isEmpty()) {
			Node o = order.poll();

			if (o.a.equals("bridge")) {
				if (uf.union(o.b, o.c))
					answer.append("yes");
				else
					answer.append("no");
				answer.append("\n");
			} else if (o.a.equals("penguins")) {
				update(hld.in[o.b], o.c);
			} else {
				if (uf.find(o.b) != uf.find(o.c))
					answer.append("impossible");
				else
					answer.append(hld.getAns(o.b, o.c));
				answer.append("\n");
			}
		}

		System.out.println(answer);
	}

	static int query(int l, int r) {
		l += N;
		r += N + 1;
		int ans = 0;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				ans += tree[l++];
			}
			if ((r & 1) == 1) {
				ans += tree[--r];
			}
		}
		return ans;
	}

	static void update(int now, int v) {
		now = now + N;

		tree[now] = v;
		while ((now >>= 1) != 0)
			tree[now] = tree[now << 1] + tree[now << 1 | 1];
	}

	static void groundWork(BufferedReader br) throws Exception {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()) + 1;

		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		uf = new UF();
		hld = new HLD();
		ArrayList<Integer>[] iLink = new ArrayList[N];
		for (int i = 1; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			iLink[i] = new ArrayList<>();
			hld.link[i] = new ArrayList<>();
		}

		int M = Integer.parseInt(br.readLine());
		order = new ArrayDeque<>();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			order.add(new Node(a, b, c));
			if (a.equals("bridge")) {
				if (uf.union(b, c)) {
					iLink[b].add(c);
					iLink[c].add(b);
				}
			}
		}
		for (int i = 2; i < N; i++)
			if(uf.union(1, i))
				iLink[1].add(i);
		

		hld.visit[1] = true;
		hld.dfs(1, iLink);
		hld.dfs1(1);
		hld.dfs2(1);

		tree = new int[N << 1];
		for (int i = 1; i < N; i++)
			update(hld.in[i], nums[i]);

	}

	static class Node {
		String a;
		int b, c;

		public Node(String a, int b, int c) {
			// TODO Auto-generated constructor stub
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

}
