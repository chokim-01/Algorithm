import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		Node left;
		Node right;

		void add(int v) {
			int n = 31;
			Node now = this;
			while (n-- > 0) {
				int bit = 1 << n;
				if ((v & bit) == bit) {
					// 1
					if (now.right == null)
						now.right = new Node();
					now = now.right;
				} else {
					// 0
					if (now.left == null)
						now.left = new Node();
					now = now.left;
				}
			}
		}

		int calc(int v) {
			int ret = v;
			int n = 31;
			Node now = this;
			while (n-- > 0) {
				int bit = 1 << n;
				if ((v & bit) == 0 && now.right != null) { // 0 1 | 0 0
					now = now.right; // 1
					ret ^= bit;
				} else if (now.left != null)
					now = now.left;
				else {
					ret ^= bit;
					now = now.right;
				}
			}
			return ret;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] nums = new int[N + 1];

			HashSet<Integer> set = new HashSet<>();
			set.add(0);
			for (int i = 1; i <= N; i++)
				set.add(nums[i] = Integer.parseInt(st.nextToken()) ^ nums[i - 1]);
			Node top = new Node();

			Iterator<Integer> list = set.iterator();
			while (list.hasNext())
				top.add(list.next());

			int ans = 0;
			list = set.iterator();
			while (list.hasNext())
				ans = Math.max(ans, top.calc(list.next()));

			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}