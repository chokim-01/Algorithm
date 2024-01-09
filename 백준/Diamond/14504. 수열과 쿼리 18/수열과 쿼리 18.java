import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] nums;
	static Bucket[] bucket;
	static ArrayList<Order> order;
	static final int div = 333;

	static class Bucket {
		ArrayList<Node> list;
		ArrayList<Node> sortedList;

		public Bucket() {
			// TODO Auto-generated constructor stub
			list = new ArrayList<>();
			sortedList = new ArrayList<>();
		}

		void add(int v, int i) {
			list.add(new Node(v, i));
			sortedList.add(new Node(v, i));
		}

		class Node {
			int value, idx;

			public Node(int value, int idx) {
				// TODO Auto-generated constructor stub
				this.value = value;
				this.idx = idx;
			}
		}

		void sortByValue() {
			Collections.sort(this.sortedList, new Comparator<Node>() {

				@Override
				public int compare(Main.Bucket.Node o1, Main.Bucket.Node o2) {
					// TODO Auto-generated method stub
					return o1.value - o2.value;
				}
			});
		}

		int search(int x, int l, int r, boolean flag) {
			if (flag) {
				return list.size() - binSearch(x + 1, 0, list.size());
			} else {
				int c = 0;
				r = Math.min(list.size() - 1, r);
				for (int i = l; i <= r; i++)
					if (list.get(i).value > x)
						c++;
				return c;
			}
		}

		int binSearch(int x, int l, int r) {
			int ret = list.size();
			while (l < r) {
				int mid = (l + r) >> 1;
				if (sortedList.get(mid).value >= x) {
					r = mid;
					ret = mid;
				} else
					l = mid + 1;
			}
			return ret;
		}

		void change(int idx, int x) {
			int index = binSearch(list.get(idx).value, 0, list.size());
			sortedList.get(index).value = x;
			list.get(idx).value = x;
			sortByValue();
		}
	}

	static class Order {
		boolean o;
		int i, j, k;
		ArrayList<Integer> idxList;

		public Order(int i, int j, int k) {
			// TODO Auto-generated constructor stub
			this.o = true;
			this.i = i;
			this.j = j;
			this.k = k;
			idxList = new ArrayList<>();
			while (i <= j) {
				idxList.add(i / div);
				i = (i / div + 1) * div;
			}
		}

		public Order(int i, int k) {
			this.o = false;
			this.i = i - i / div * div;
			this.k = k;
			idxList = new ArrayList<>();
			idxList.add(i / div);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);

		setBucket();

		solve();
	}

	static StringBuilder ans;

	static void solve() {
		ans = new StringBuilder();
		for (Order o : order) {
			if (o.o) {
				int v = 0;
				for (int i = 0; i < o.idxList.size(); i++) {
					int n = o.idxList.get(i);
					if (i == 0 || i == o.idxList.size() - 1) {
						int l = Math.max(o.i - n * div, 0);
						int r = Math.min(o.j - n * div, div - 1);
						o.i = 0;
						v += bucket[n].search(o.k, l, r, false);
					} else
						v += bucket[n].search(o.k, -1, -1, true);
				}
				ans.append(v).append("\n");
			} else {
				int bIdx = o.idxList.get(0);
				bucket[bIdx].change(o.i, o.k);
			}
		}
		System.out.println(ans);
	}

	static void setBucket() {
		bucket = new Bucket[div];
		for (int i = 0; i < bucket.length; i++)
			bucket[i] = new Bucket();
		for (int i = 0; i < N; i++)
			bucket[i / div].add(nums[i], i - i / div * div);
		for (int i = 0; i < bucket.length; i++)
			bucket[i].sortByValue();
	}

	static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
//		nums = new int[N];
//		for (int i = 0; i < N; i++)
//			nums[i] = 1000000000;
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(br.readLine());
		order = new ArrayList<>();
		StringTokenizer st;
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (st.hasMoreTokens()) {
				int c = Integer.parseInt(st.nextToken());
				order.add(new Order(a - 1, b - 1, c));
			} else {
				order.add(new Order(a - 1, b));
			}
		}
	}
}