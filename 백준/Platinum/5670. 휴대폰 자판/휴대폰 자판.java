import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

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
				if (now.childs[c - 'a'] == null) {
					now.childs[c - 'a'] = new Node();
					now.childCount += 1;
				}
				now = now.childs[c - 'a'];
			}
			now.end = true;

		}

		int search(String s) {
			int ret = 1;
			Node now = head.childs[s.charAt(0) - 'a'];
			int index = 0;
			while (++index < s.length()) {
				if (now.childCount != 1 || now.end)
					ret++;
				now = now.childs[s.charAt(index) - 'a'];
			}

			return ret;
		}
	}

	static class Node {
		Node[] childs;
		int childCount;
		boolean end;

		public Node() {
			// TODO Auto-generated constructor stub
			childs = new Node[26];
			childCount = 0;
			end = false;
		}
	}

	static Trie trie;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String[] s;
		String sbr;
		while ((sbr = br.readLine()) != null) {
			trie = new Trie();
			
			int N = Integer.parseInt(sbr);
			s = new String[N];
			for (int i = 0; i < N; i++) {
				s[i] = br.readLine();
				trie.insert(s[i]);
			}
			double ans = 0;
			for (int i = 0; i < N; i++)
				ans += trie.search(s[i]);

			sb.append(String.format("%.2f", ans / N)).append("\n");
		}

		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
}