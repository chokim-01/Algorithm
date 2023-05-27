import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static Node[] tree;
	static final long mod = 1000000007;

	static class Node {
		boolean zFlag;
		long v;

		public Node() {
			// TODO Auto-generated constructor stub
			this.zFlag = false;
			this.v = 1;
		}

		public Node(long b) {
			this.zFlag = false;
			this.v = b;
		}

		public Node(boolean a, long b) {
			this.zFlag = a;
			this.v = b;
		}

		@Override
		public String toString() {
			return "[ " + this.zFlag + " , " + this.v + " ]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		tree = new Node[N << 1];
		for (int i = 0; i < N; i++)
			tree[i] = new Node();
		for (int i = N; i < tree.length; i++)
			tree[i] = new Node(Integer.parseInt(br.readLine()));

		init();

		int q = M + K;

		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) { // change
				update(b, c);
			} else { // print
				answer.append(query(b, c)).append("\n");
			}
		}
		System.out.println(answer);
	}

	static long query(int a, int b) {
		long res = 1;
		a = a + N;
		b = b + N;
		for (; a < b; a >>= 1, b >>= 1) {
			if ((a & 1) == 1) {
				res = (res * (tree[a].zFlag ? 0 : tree[a].v)) % mod;
				a++;
			}
			if ((b & 1) == 1) {
				b--;
				res = (res * (tree[b].zFlag ? 0 : tree[b].v)) % mod;
			}
		}
		return res;
	}

	static void update(int index, int mul) {
		index += N;

		if (mul == 0) {
			do
				tree[index].zFlag = true;
			while ((index >>= 1) != 0);
		} else {
			int divide = (int) tree[index].v;
			long d = officer(divide, mod - 2);
			do {
				tree[index].v = ((tree[index].v * d) % mod * mul) % mod;
				tree[index].zFlag = false;
				if (index < N)
					if (tree[index << 1].zFlag || tree[(index << 1) + 1].zFlag)
						tree[index].zFlag = true;

			} while ((index >>= 1) != 0);
		}
	}

	static long officer(long d, long cnt) {
		if (cnt == 0)
			return 1;
		long res = officer(d, cnt / 2);
		if ((cnt & 1) == 1)
			return (((res * res) % mod) * d) % mod;
		else
			return (res * res) % mod;

	}

	static void init() {
		for (int i = N; i < tree.length; i++) {
			int n = i;
			if (tree[i].v == 0) {
				tree[i].zFlag = true;
				tree[i].v = 1;
			}

			while ((n >>= 1) != 0) {
				if (tree[i].zFlag)
					tree[n].zFlag = true;
				else
					tree[n].v = (tree[n].v * tree[i].v) % mod;
			}
		}
	}
}
