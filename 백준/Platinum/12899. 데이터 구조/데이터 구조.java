import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int N = 2097152;
	static Segment seg;

	static class Segment {
		int[] tree;

		public Segment() {
			// TODO Auto-generated constructor stub
			tree = new int[N << 1];
		}

		void update(int idx, int v) {
			idx += N;
			tree[idx] += v;
			while ((idx /= 2) != 0)
				tree[idx] = tree[idx << 1] + tree[idx << 1 | 1];
		}

		int query(int l, int r) {
			int ret = 0;
			l += N;
			r += N + 1;
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1)
					ret += tree[l++];
				if ((r & 1) == 1)
					ret += tree[--r];
			}
			return ret;
		}

		int binarySearch(int x) {
			int ret = 0;
			int l = 0;
			int r = 2000000;
			while (l < r) {
				int mid = (l + r) >> 1;
				if (seg.query(0, mid) >= x) {
					ret = mid;
					r = mid;
				} else {
					l = mid + 1;
				}
			}
			return ret;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		seg = new Segment();

		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) {
				seg.update(b, 1);
			} else {
				int r = seg.binarySearch(b);
				seg.update(r, -1);
				ans.append(r).append("\n");
			}
		}
		System.out.println(ans);
	}
}
