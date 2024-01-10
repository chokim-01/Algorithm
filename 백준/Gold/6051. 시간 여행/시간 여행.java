import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Node[] list;

	static class Node {
		Node parent;
		int num;

		public Node(int n, Node p) {
			// TODO Auto-generated constructor stub
			this.parent = p;
			this.num = n;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		list = new Node[N + 1];
		list[0] = new Node(-1, null);

		for (int k = 1; k <= N; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int n = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : -1;
			switch (c) {
			case 'a':
				list[k] = new Node(n, list[k - 1]);
				ans.append(n);
				break;
			case 's':
				list[k] = list[k - 1].parent;
				if (list[k].parent == null)
					ans.append(-1);
				else
					ans.append(list[k].num);
				break;
			case 't':
				list[k] = list[n - 1];
				if (list[k].parent == null)
					ans.append(-1);
				else
					ans.append(list[k].num);
				break;
			}
			ans.append("\n");
		}
		System.out.println(ans);
	}
}