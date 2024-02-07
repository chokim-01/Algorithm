import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, count;
	static Tree treeA, treeB, treeC;
	static StringBuilder ans;

	static class Tree {
		int[] parent;
		ArrayList<Integer>[] link;
		TreeSet<Integer>[] childs;

		public Tree() {
			// TODO Auto-generated constructor stub
			this.parent = new int[N + 1];
			Arrays.fill(this.parent, -1);
			this.parent[1] = 0;

			this.link = new ArrayList[N + 1];
			this.childs = new TreeSet[N + 1];
			for (int i = 1; i <= N; i++) {
				link[i] = new ArrayList<>();
				childs[i] = new TreeSet<>();
			}
		}

		void addLink(int a, int b) {
			this.link[a].add(b);
			this.link[b].add(a);
		}

		void dfs(int now) {
			for (int next : link[now]) {
				if (parent[next] != -1)
					continue;
				childs[now].add(next);
				parent[next] = now;
				dfs(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		
		treeA.dfs(1);
		treeC.dfs(1);

		count = 0;
		ans = new StringBuilder();
		aToB(1, 1);
		bToC(1, 1);
		System.out.println(ans.insert(0, count + "\n"));
	}

	static void aToB(int now, int depth) {
		if (depth >= 3) {
			int b = treeA.parent[now];
			int c = treeA.parent[b];
			count++;
			ans.append(now).append(" ").append(b).append(" ").append(c).append("\n");
			treeA.parent[now] = c;
		}
		for (int next : treeA.childs[now])
			aToB(next, depth + 1);
	}

	static void bToC(int now, int depth) {
		for (int next : treeC.childs[now])
			bToC(next, depth + 1);
		if (depth >= 3) {
			count++;
			ans.append(now).append(" ").append(1).append(" ").append(treeC.parent[now]).append("\n");
		}
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		treeA = new Tree();
		treeC = new Tree();

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			treeA.addLink(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			treeC.addLink(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}
}