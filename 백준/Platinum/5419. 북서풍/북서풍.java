import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static long[] tree;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[" + this.x + "," + y + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			ArrayList<Node> list = new ArrayList<>();
			ArrayList<Integer> numbers = new ArrayList<>();
			for (int i = 0, a, b; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				list.add(new Node(a, b));
				numbers.add(b);
			}
			list.sort((x1, x2) -> x1.x == x2.x ? x2.y - x1.y : x1.x - x2.x);
			Collections.sort(numbers, Collections.reverseOrder());

			HashMap<Integer, Integer> zip = new HashMap<>();
			int ySize = numbers.size();
			for (int i = 0; i < ySize; i++)
				zip.put(numbers.get(i), i);

			tree = new long[ySize << 1];
			long ans = 0;
			for (Node n : list) {
				int y = zip.get(n.y);
				ans += getSum(ySize, ySize + y + 1);
				setTree(ySize + y);
			}
			bw.write(String.valueOf(ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	static long getSum(int left, int right) {
		long ans = 0;
		while (left < right) {
			if ((left & 1) == 1)
				ans += tree[left++];
			if ((right & 1) == 1)
				ans += tree[--right];
			left /= 2;
			right /= 2;
		}
		return ans;
	}

	static void setTree(int index) {
		do
			tree[index] += 1;
		while ((index = index / 2) != 0);
	}
}
//2812462500
