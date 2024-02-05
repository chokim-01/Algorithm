import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static FWick fwick;
	static final int MAX = 1000001;

	static class FWick {
		long[] tree;

		public FWick() {
			// TODO Auto-generated constructor stub
			tree = new long[MAX];
		}

		int query(int x) {
			int ret = 0;
			for (; x > 0; x -= x & -x)
				ret += tree[x];
			return ret;
		}

		void update(int x, int v) {
			for (; x < tree.length; x += x & -x)
				tree[x] += v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		FWick fwick = new FWick();

		int[] nums = Stream.of(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 1; i <= N; i++)
			if ((nums[i] & 1) == 0)
				fwick.update(i, 1);

		StringBuilder ans = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = 0;
			switch (o) {
			case 1:
				if (nums[a] % 2 == 0 && b % 2 == 1)
					fwick.update(a, -1);
				else if (nums[a] % 2 == 1 && b % 2 == 0)
					fwick.update(a, 1);
				nums[a] = b;
				break;
			case 2:
				c = fwick.query(b) - fwick.query(a - 1);
				ans.append(c).append("\n");
				break;
			case 3:
				c = (b - a + 1) - (fwick.query(b) - fwick.query(a - 1));
				ans.append(c).append("\n");
				break;
			}
		}
		System.out.println(ans);
	}
}