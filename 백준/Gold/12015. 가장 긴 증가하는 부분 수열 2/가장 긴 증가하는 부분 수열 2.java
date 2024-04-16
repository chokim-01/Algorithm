import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int[] tree;

	static class Node implements Comparable<Node> {
		int index, num;

		public Node(int index, int num) {
			this.index = index;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if (this.num == o.num)
				return o.index - this.index;
			return this.num - o.num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		ArrayList<Node> arr = new ArrayList<Node>();

		st = new StringTokenizer(br.readLine());
		int index = 1;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr.add(new Node(index++, num));
		}
		Collections.sort(arr);
		int size = arr.size();
		tree = new int[(size + 1) << 1];

		int max = 0;
		for (Node n : arr) {
			index = n.index;
			int sum = treeSum(size, index + size - 1);
			treeUpdate(index + size - 1, sum + 1);
			max = max < sum + 1 ? sum + 1 : max;
		}
		System.out.println(max);
	}

	static void treeUpdate(int a, int b) {
		tree[a] = b;
		while ((a = a / 2) != 0)
			tree[a] = Math.max(tree[a], b);
	}

	static int treeSum(int left, int right) {
		int ans = 0;
		while (left < right) {
			if ((left & 1) == 1)
				ans = Math.max(ans, tree[left++]);
			if ((right & 1) == 1)
				ans = Math.max(ans, tree[--right]);
			left /= 2;
			right /= 2;
		}
		return ans;
	}
}