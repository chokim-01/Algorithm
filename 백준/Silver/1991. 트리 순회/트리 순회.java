import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Node {
		String str;
		Node left, right;

		public Node(String s) {
			// TODO Auto-generated constructor stub
			this.str = s;
		}

		void insert(String s, String l, String r) {
			if (this.str.equals(s)) {
				if (!l.equals("."))
					this.left = new Node(l);
				if (!r.equals("."))
					this.right = new Node(r);
			} else {
				if (this.left != null)
					this.left.insert(s, l, r);
				if (this.right != null)
					this.right.insert(s, l, r);
			}

		}

	}

	static StringBuffer sb[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer[3];
		for (int i = 0; i < 3; i++)
			sb[i] = new StringBuffer();
		int N = Integer.parseInt(br.readLine());
		// A 추가
		Node root = new Node("A");
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			root.insert(s[0], s[1], s[2]);
		}

		dfs(root);
		for (int i = 0; i < 3; i++)
			System.out.println(sb[i].toString());
	}

	static void dfs(Node now) {
		if (now == null)
			return;
		sb[0].append(now.str);
		dfs(now.left);
		sb[1].append(now.str);
		dfs(now.right);
		sb[2].append(now.str);

	}
}
