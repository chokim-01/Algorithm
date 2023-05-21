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
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		tree = new ArrayList[N * 2];

		init();

		int M = Integer.parseInt(st.nextToken());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int left = -1000000000;
			int right = 1000000000;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (query(a + N - 1, b + N, mid) < k)
					left = mid + 1;
				else
					right = mid - 1;
			}
			sb.append(left).append("\n");
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
	// [ 1,5,2,6,4]
	// 정렬 후 3번째.

	static int query(int l, int r, int key) {
		int res = 0;
		for (; l < r; l >>= 1, r >>= 1) {
			if ((l & 1) == 1) {
				res += upperBound(tree[l], key);
				l++;
			}

			if ((r & 1) == 1) {
				--r;
				res += upperBound(tree[r], key);
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