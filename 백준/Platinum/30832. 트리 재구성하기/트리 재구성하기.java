import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, count;
	static Tree treeA, treeC;
	static StringBuilder ans;

	static class Tree {
		ArrayList<Integer>[] link;

		public Tree() {
			// TODO Auto-generated constructor stub
			this.link = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++)
				link[i] = new ArrayList<>();
		}

		void addLink(int a, int b) {
			this.link[a].add(b);
			this.link[b].add(a);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);

		count = 0;
		ans = new StringBuilder();
		aToB(1, 0, 1, new boolean[N + 1]);
		bToC(1, 0, 1, new boolean[N + 1]);
		System.out.println(ans.insert(0, count + "\n"));
	}

	static void aToB(int now, int p, int depth, boolean[] v) {
		v[now] = true;
		if (depth >= 3)
			print(now, p, 1);
		for (int next : treeA.link[now])
			if (!v[next])
				aToB(next, now, depth + 1, v);
	}

	static void bToC(int now, int p, int depth, boolean[] v) {
		v[now] = true;
		for (int next : treeC.link[now])
			if (!v[next])
				bToC(next, now, depth + 1, v);
		if (depth >= 3)
			print(now, 1, p);
	}

	static void print(int a, int b, int c) {
		count++;
		ans.append(a).append(" ").append(b).append(" ").append(c).append("\n");
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