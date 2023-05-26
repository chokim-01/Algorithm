import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] ans, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		makeMeasure();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] works = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		tree = new int[works.length << 1];
		treeInit(works);

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			if (a == 1) { // change
				for (int i = b; i <= c; i++) {
					if (tree[i + N] < 3)
						continue;
					update(i);
				}
			} else { // query
				answer.append(query(b, c)).append("\n");
			}
		}
		System.out.println(answer);
	}

	static void update(int v) {
		v = v + N;
		int val = tree[v] - ans[tree[v]];
		do
			tree[v] -= val;
		while ((v >>= 1) != 0);
	}

	static int query(int l, int r) {
		int res = 0;
		l = l + N;
		r = r + N + 1;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				res += tree[l];
				l++;
			}
			if ((r & 1) == 1) {
				r--;
				res += tree[r];
			}
		}
		return res;
	}

	static void treeInit(int[] works) {
		for (int i = N; i < tree.length; i++) {
			int n = i;
			do
				tree[n] += works[i - N];
			while ((n = n >> 1) != 0);
		}
	}

	static void makeMeasure() {
		N = 1000001;
		ans = new int[N];
		int[] measure = new int[N]; // 소인수 중 가장 작은
		int[] measureCounts = new int[N];

		// che
		N -= 1;
		for (int i = 1; i <= N; i++)
			measure[i] = i;
		for (int i = 2; i <= N; i++) {
			if (measure[i] == i) {
				for (int j = 2; i * j <= N; j++) {
					if (measure[i * j] == i * j)
						measure[i * j] = i;
				}
			}
		}
		ans[1] = 1;
		for (int i = 2; i <= N; i++) {
			if (measure[i] == i) { // 자기자신
				ans[i] = 2;
				measureCounts[i]++;
			} else { // 약수 개수 구하기
				int p = measure[i];
				int m = i / p;
				if (p != measure[m])
					measureCounts[i]++;
				else
					measureCounts[i] = measureCounts[m] + 1; // 이전거.
				int a = measureCounts[i];
				ans[i] = (ans[m] / a) * (a + 1);
			}
		}
	}
}
