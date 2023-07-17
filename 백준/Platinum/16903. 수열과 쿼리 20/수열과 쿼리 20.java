import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static class Trie {
		Node head;

		public Trie() {
			// TODO Auto-generated constructor stub
			head = new Node();
		}

		void insert(int x) {
			Node now = this.head;

			for (int i = 0; i < 30; i++) {
				int c = (x & (1 << 29 - i)) != 0 ? 1 : 0;
				if (now.childs[c] == null) {
					now.childs[c] = new Node();
				}
				now = now.childs[c];
				now.cnt += 1;
			}
		}

		// 배열 만들어서.
		// String xo 반복
		void delete(int x) {
			Node now = head;
			for (int i = 0; i < 30; i++) {
				int c = (x & (1 << 29 - i)) != 0 ? 1 : 0;
				now = now.childs[c];
				now.cnt -= 1;
			}
		}

		// 앞에서부터, 1나올 때 마다
		int xor(int x) {
			Node now = head;
			int[] arr = new int[30];
			for (int i = 0; i < 30; i++) {
				// 둘 중 하나는 1
				int n = (x & (1 << 29 - i)) != 0 ? 1 : 0;
				if ((n ^ 0) == 0) {
					if (now.childs[1] != null && now.childs[1].cnt > 0) {
						now = now.childs[1];
						arr[i] = 1;
					} else {
						now = now.childs[0];
					}
				} else {
					if (now.childs[0] != null && now.childs[0].cnt > 0) {
						now = now.childs[0];
						arr[i] = 1;
					} else {
						now = now.childs[1];
					}
				}
			}
			int ans = 0;
			for (int i = 0; i < arr.length; i++)
				ans += arr[i] << (29 - i);
			return ans;
		}
	}

	static class Node {
		Node[] childs;
		int cnt;

		public Node() {
			// TODO Auto-generated constructor stub
			childs = new Node[2];
			cnt = 0;
		}
	}

	static Trie trie;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		trie.insert(0);
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			if (o == 1) {
				trie.insert(x);
			} else if (o == 2) {
				trie.delete(x);
			} else {
				sb.append(trie.xor(x)).append("\n");
			}
		}

		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
}