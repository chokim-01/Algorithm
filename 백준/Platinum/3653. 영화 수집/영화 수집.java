import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int T, N, M;

	static class Seg {
		int[] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new int[(N + M + 1) << 1];
			for (int i = 1; i <= N; i++) {
				int n = i + N + M + M;
				do
					tree[n]++;
				while ((n /= 2) != 0);
			}
		}

		public int query(int v) {
			int ret = 0;
			int l = N + M + 1;
			int r = v;
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1)
					ret += tree[l++];
				if ((r & 1) == 1)
					ret += tree[--r];
			}
			return ret;
		}

		public void update(int i, int v) {
			do
				tree[i] += v;
			while ((i /= 2) != 0);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[] loc = new int[N + 1];
			for (int i = 1; i <= N; i++)
				loc[i] = i + N + M + M;

			int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Seg seg = new Seg();
			int count = 0;
			for (int a : arr) {
				sb.append(seg.query(loc[a])).append(" ");
				seg.update(loc[a], -1);
				seg.update(loc[a] = N + M + M - count++, 1);
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}