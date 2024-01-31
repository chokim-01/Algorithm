import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int MAX;
	static int[] nums;

	static class FWick {
		int[][] tree;

		public FWick() {
			// TODO Auto-generated constructor stub
			tree = new int[MAX][2];
		}

		void update(int a, int v) {
			for (; a < MAX; a += a & -a)
				tree[a][0] = Math.max(tree[a][0], v);
		}

		void update2(int a, int v) {
			for (; a > 0; a -= a & -a)
				tree[a][1] = Math.max(tree[a][1], v);
		}

		int query(int a, int b) {
			int ret = 0;
			
			int bef = a;
			for (int now = bef + (bef & -bef); now <= b; bef = now, now += now & -now)
				ret = Math.max(ret, tree[bef][1]);
			
			ret = Math.max(ret, nums[bef]);
			
			bef = b;
			for (int now = bef - (bef & -bef); now >= a; bef = now, now -= now & -now)
				ret = Math.max(ret, tree[bef][0]);
			return ret;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		MAX = N + 1;

		nums = Stream.of((("0 ") + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

		FWick fwick = new FWick();

		for (int i = 1; i <= N; i++) {
			fwick.update(i, nums[i]);
			fwick.update2(i, nums[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = M; i <= N - M + 1; i++)
			sb.append(fwick.query(i - M + 1, i + M - 1)).append(" ");
		System.out.println(sb);
	}
}