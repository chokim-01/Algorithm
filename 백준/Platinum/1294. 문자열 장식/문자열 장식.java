import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static class Node implements Comparable<Node> {
		StringBuilder s;
		int index;

		public Node(String s, int index) {
			// TODO Auto-generated constructor stub
			this.s = new StringBuilder(s);
			this.index = index;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			int len = Math.min(this.s.length(), o.s.length());
			for (int i = 0; i < len; i++) {
				if (this.s.charAt(i) == o.s.charAt(i))
					continue;
				return this.s.charAt(i) - o.s.charAt(i);
			}
			if (this.s.length() == 0)
				return 1;
			if (o.s.length() == 0)
				return -1;
			if (this.s.length() < o.s.length())
				return 1;

			return -1;
		}

		@Override
		public String toString() {
			return this.s.toString();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int max = 0;
		ArrayList<Node> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			Node n = new Node(br.readLine(), i);
			list.add(n);
			max += n.s.length();
		}

		StringBuilder ans = new StringBuilder();

		while (max-- > 0) {
			Collections.sort(list);
			Node n = list.get(0);
			ans.append(n.s.charAt(0));
			n.s.delete(0, 1);
		}
		System.out.println(ans);

	}
}