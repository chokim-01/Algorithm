import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] numbers;
	static ArrayList<Integer>[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		tree = new ArrayList[N * 2];

		init();

		int ans = 0;
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) ^ ans;
			int b = Integer.parseInt(st.nextToken()) ^ ans;
			int k = Integer.parseInt(st.nextToken()) ^ ans;
			ans = query(a + N - 1, b + N, k);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void init() {
		for (int i = 0; i < tree.length; i++)
			tree[i] = new ArrayList<>();
		for (int i = N; i < N + numbers.length; i++) {
			int n = i;
			do {
				tree[n].add(numbers[i - N]);
			} while ((n /= 2) != 0);
		}
		for (int i = 0; i < N; i++)
			Collections.sort(tree[i]);
	}

	static int query(int l, int r, int key) {
		int res = 0;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				if (tree[l].get(0) > key)
					res += tree[l].size();
				else if (tree[l].get(tree[l].size() - 1) <= key)
					;
				else
					res += tree[l].size() - upperBound(tree[l], key);
				l++;
			}

			if ((r & 1) == 1) {
				--r;
				if (tree[r].get(0) > key)
					res += tree[r].size();
				else if (tree[r].get(tree[r].size() - 1) <= key)
					;
				else
					res += tree[r].size() - upperBound(tree[r], key);
			}
		}
		return res;
	}

	public static int upperBound(ArrayList<Integer> arr, int key) {
		int mid;
		int front = 0;
		int rear = arr.size();
		while (front < rear) {
			mid = (front + rear) / 2;
			if (arr.get(mid) <= key)
				front = mid + 1;
			else
				rear = mid;
		}
		return rear;
	}
}