import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {

	static class Trie {
		private TrieNode rootNode;

		public Trie() {
			// TODO Auto-generated constructor stub
			this.rootNode = new TrieNode();
		}

		/*
		 * boolean contains(String word) { TrieNode thisNode = this.rootNode;
		 * 
		 * for (int i = 0; i < word.length(); i++) { char character = word.charAt(i);
		 * TrieNode node = thisNode.getChildNodes().get(character); if (node == null) //
		 * 비교하려는 글자가 안에 없음. return false; // YES thisNode = node; } return
		 * thisNode.isLastChar(); // 제일 마지막 }
		 */
		boolean insert(String word) {
			TrieNode thisNode = this.rootNode;

			for (int i = 0; i < word.length(); i++) {
				if (!thisNode.getChildNodes().containsKey(word.charAt(i)))
					thisNode.getChildNodes().put(word.charAt(i), new TrieNode());
				thisNode = thisNode.getChildNodes().get(word.charAt(i));

				if (thisNode.isLastChar)
					return false;
				// a b c d
				// c(d) -> 자식에 있음
			}
			thisNode.setIsLastChar(true);
			return true; // 추가 성공
		}
	}

	static class TrieNode {
		private Map<Character, TrieNode> childNodes = new HashMap<>();
		private boolean isLastChar;

		Map<Character, TrieNode> getChildNodes() {
			return this.childNodes;
		}

		boolean isLastChar() {
			return this.isLastChar;
		}

		void setIsLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();

			String numbers[] = new String[N];
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				numbers[i] = s;
			}
			Arrays.sort(numbers);
			Trie trie = new Trie();
			String ans = "YES";
			for (int i = 0; i < numbers.length; i++) {
				if (!trie.insert(numbers[i])) {
					ans = "NO";
					break;
				}
			}
			System.out.println(ans);
		}
	}
}
//10
// 