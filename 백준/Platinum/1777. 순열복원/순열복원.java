import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;

	static class Seg {
		int[] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new int[N << 1];
		}

		int query(int a, int b) {
			int ret = 0;
			a += N;
			b += N;
			while (a < b) {
				if ((a & 1) == 1)
					ret += tree[a++];
				if ((b & 1) == 1)
					ret += tree[--b];
				a >>= 1;
				b >>= 1;
			}
			return ret;
		}

		void update(int a) {
			a += N;
			do
				tree[a]++;
			while ((a >>= 1) != 0);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Seg seg = new Seg();

		int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int now = N;
		int[] ans = new int[N];
		while (now-- > 0) {
			int l = 0;
			int r = N;
			while (l < r) {
				int mid = (l + r) >> 1;

				if (mid + array[now] + seg.query(mid, N) < N)
					l = mid + 1;
				else
					r = mid;

			}
			seg.update(l - 1);
			ans[l - 1] = now + 1;
		}
		StringBuilder sb = new StringBuilder();
		for (int a : ans)
			sb.append(a).append(" ");
		System.out.println(sb);
	}
}
