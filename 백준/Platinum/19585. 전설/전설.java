import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static class Trie {
		Node head;

		public Trie() {
			// TODO Auto-generated constructor stub
			head = new Node();
		}

		void insert(String str) {
			Node now = this.head;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (now.childs[c - 'a'] == null)
					now.childs[c - 'a'] = new Node();
				now = now.childs[c - 'a'];
			}
			now.end = true;

		}

		boolean search(String str) {
			Node now = this.head;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (now.end)
					if (set.contains(str.substring(i)))
						return true;

				if (now.childs[c - 'a'] == null)
					return false;

				now = now.childs[c - 'a'];
			}
			return false;
		}
	}

	static class Node {
		Node[] childs;
		boolean end;

		public Node() {
			// TODO Auto-generated constructor stub
			childs = new Node[26];
			end = false;
		}
	}

	static Trie trie;
	static HashSet<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		set = new HashSet<>();
		trie = new Trie();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++)
			trie.insert(br.readLine());

		for (int i = 0; i < M; i++)
			set.add(br.readLine());

		int K = Integer.parseInt(br.readLine());

		while (K-- > 0) {
			String s = br.readLine();
			boolean flag = false;
			flag = trie.search(s);

			if (flag) {
				sb.append("Yes");
			} else {
				sb.append("No");
			}
			sb.append("\n");
		}
		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
}