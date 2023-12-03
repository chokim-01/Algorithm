import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.w3c.dom.Node;

public class Main {
	static class Node {
		String s;
		int sum;

		public Node(String s) {
			// TODO Auto-generated constructor stub
			this.s = s;
			this.sum = 0;
			for (char c : s.toCharArray())
				if (Character.isDigit(c))
					sum += c - '0';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Node> lst = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0)
			lst.add(new Node(br.readLine()));
		Collections.sort(lst, new Comparator<Node>() {

			@Override
			public int compare(Main.Node o1, Main.Node o2) {
				// TODO Auto-generated method stub
				if (o1.s.length() == o2.s.length()) {
					if (o1.sum == o2.sum)
						for (int i = 0; i < o1.s.length(); i++)
							if (o1.s.charAt(i) == o2.s.charAt(i))
								continue;
							else if (o1.s.charAt(i) < o2.s.charAt(i))
								return -1;
							else
								return 1;

					return o1.sum - o2.sum;
				}
				return o1.s.length() - o2.s.length();
			}
		});
		StringBuilder ans = new StringBuilder();
		for (Node n : lst)
			ans.append(n.s).append("\n");
		System.out.println(ans);
	}
}