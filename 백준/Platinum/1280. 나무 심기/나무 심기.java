import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MAX = 200001;
	static final long MOD = 1000000007;

	static class Seg {
		long[][] tree;

		public Seg() {
			// TODO Auto-generated constructor stub
			tree = new long[MAX << 1][2];
		}

		long[] query(int l, int r) {
			l += MAX;
			r += MAX;
			long[] ret = { 0, 0 };
			for (; l < r; l >>= 1, r >>= 1) {
				if ((l & 1) == 1) {
					ret[0] = (ret[0] + tree[l][0]) % MOD;
					ret[1] += tree[l][1];
					l++;
				}
				if ((r & 1) == 1) {
					--r;
					ret[0] = (ret[0] + tree[r][0]) % MOD;
					ret[1] += tree[r][1];
				}
			}
			return ret;
		}

		void update(int x) {
			int v = x;
			x += MAX;
			do {
				tree[x][0] = (tree[x][0] + v) % MOD;
				tree[x][1]++;
			} while ((x >>= 1) != 0);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Seg seg = new Seg();
		long ans = 1;
		int N = Integer.parseInt(br.readLine()) - 1;
		seg.update(Integer.parseInt(br.readLine()));
		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());

			long[] q = seg.query(0, num);
			long[] q2 = seg.query(num, MAX);

			long l = (q[1] * num - q[0]) % MOD;
			long r = ((q2[0] + MOD) - (q2[1] * num) % MOD) % MOD;

			seg.update(num);
			ans = (ans * ((l + r) % MOD)) % MOD;
		}
		System.out.println(ans);
	}
}