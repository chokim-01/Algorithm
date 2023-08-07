import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static int[] in, out;
	static long[] tree;
	static boolean[] visit;
	static ArrayList<Integer>[] link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		makeTree(br);
		StringBuilder sb = new StringBuilder();

		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			if (q == 1) {
				int v = Integer.parseInt(st.nextToken());
				update(a, v);
			} else
				sb.append(query(a)).append("\n");
		}
		System.out.println(sb);
	}

	static void update(int idx, int v) {
		idx = in[idx] + N - 1;
		do
			tree[idx] += v;
		while ((idx >>= 1) != 0);
	}

	static long query(int idx) {
		long ret = 0;
		int l = in[idx] + N - 1;
		int r = out[idx] + N;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1)
				ret += tree[l++];
			if ((r & 1) == 1)
				ret += tree[--r];
		}
		return ret;
	}

	static void makeTree(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		in = new int[N + 1];
		out = new int[N + 1];
		tree = new long[N << 1];
		visit = new boolean[N + 1];

		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2, a; i <= N; i++)
			link[Integer.parseInt(st.nextToken())].add(i);

		oiler(1);
	}

	static int cnt = 0;

	static void oiler(int now) {
		in[now] = ++cnt;
		for (int next : link[now]) {
			if (visit[next])
				continue;
			visit[next] = true;
			oiler(next);
		}
		out[now] = cnt;
	}
}