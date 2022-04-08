import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static class Trie {
		private TrieNode rootNode;

		public Trie() {
			// TODO Auto-generated constructor stub
			this.rootNode = new TrieNode();
		}

		StringBuffer insert(String word) {
			StringBuffer sb = new StringBuffer();
			TrieNode thisNode = this.rootNode;

			boolean flag = true;
			// 존재하면 추가하고 다 것 까지.
			for (int i = 0; i < word.length(); i++) {
				if (!thisNode.getChildNodes().containsKey(word.charAt(i))) {
					thisNode.getChildNodes().put(word.charAt(i), new TrieNode());
					if (flag == true)
						sb.append(word.charAt(i)); // 끝
					flag = false;
				} else if (flag) {
					sb.append(word.charAt(i)); // 시작
				}
				thisNode = thisNode.getChildNodes().get(word.charAt(i));
			}
			thisNode.addLastCharCnt();
			// 끝이 안정해짐 -> 정할 수 없음
			if (thisNode.lastCharCnt() != 1 && word.equals(sb.toString()))
				sb.append(thisNode.lastCharCnt());
			return sb;
		}
	}

	static class TrieNode {
		HashMap<Character, TrieNode> childs = new HashMap<>();
		int lastCharCnt = 0;

		Map<Character, TrieNode> getChildNodes() {
			return this.childs;
		}

		int lastCharCnt() {
			return this.lastCharCnt;
		}

		void addLastCharCnt() {
			this.lastCharCnt += 1;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		Trie root = new Trie();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			bw.write(root.insert(s).toString());
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}
