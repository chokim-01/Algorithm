import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static FWick fwick;
	static final int MAX = 1000001;

	static class FWick {
		long[][] tree;

		public FWick() {
			// TODO Auto-generated constructor stub
			tree = new long[MAX][2];
		}

		int query(int x) {
			int ret = 0;
			for (; x > 0; x -= x & -x)
				ret += tree[x][0];
			return ret;
		}

		int query2(int x) {
			int ret = 0;
			for (; x > 0; x -= x & -x)
				ret += tree[x][1];
			return ret;
		}

		void update(int x, int v) {
			for (; x < tree.length; x += x & -x)
				tree[x][0] += v;
		}

		void update2(int x, int v) {
			for (; x < tree.length; x += x & -x)
				tree[x][1] += v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		fwick = new FWick();
		int[] count = new int[MAX];

		StringBuilder ans = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (a == 1) {
				if (++count[v] < 2)
					fwick.update(v, 1);
				fwick.update2(v, 1);
			} else {
				if (--count[v] < 1)
					fwick.update(v, -1);
				fwick.update2(v, -1);
			}
			ans.append(binSearch()).append("\n");
		}
		System.out.println(ans);
	}

	static int binSearch() {
		int l = 0;
		int r = MAX;
		int res = 0;
		while (l < r) {
			int mid = (l + r) >> 1;
			if (fwick.query(mid) == mid) {
				res = l = mid;
				l++;
			} else
				r = mid;
		}
		return fwick.query2(res);
	}
}