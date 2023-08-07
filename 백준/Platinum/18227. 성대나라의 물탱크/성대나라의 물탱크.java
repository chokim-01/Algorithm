import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] in, out, depth;
	static long[] tree;
	static ArrayList<Integer>[] link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		makeTree(br);
		StringBuilder sb = new StringBuilder();

		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			if (q == 1)
				update(a);
			else
				sb.append(query(a)).append("\n");
		}
		System.out.println(sb);
	}

	static void update(int idx) {
		idx = in[idx] + N - 1;
		do
			tree[idx] += 1;
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
		return ret * depth[idx];
	}

	static void makeTree(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		in = new int[N + 1];
		out = new int[N + 1];
		depth = new int[N + 1];
		tree = new long[N << 1];

		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			link[i] = new ArrayList<>();
		for (int i = 1, a, b; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			link[a].add(b);
			link[b].add(a);
		}
		depth[C] = 1;
		oiler(C);
	}

	static int cnt = 0;

	static void oiler(int now) {
		in[now] = ++cnt;
		for (int next : link[now]) {
			if (depth[next] != 0)
				continue;
			depth[next] = depth[now] + 1;
			oiler(next);
		}
		out[now] = cnt;
	}
}