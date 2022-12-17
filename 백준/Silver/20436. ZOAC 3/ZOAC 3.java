import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		boolean flag;
		int x, y;

		public Node(boolean flag, int x, int y) {
			// TODO Auto-generated constructor stub
			this.flag = flag;
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Map<Character, Node> map = new HashMap<>();
		char[][] list = { { 'q', 'w', 'e', 'r', 't' }, { 'a', 's', 'd', 'f', 'g' }, { 'z', 'x', 'c', 'v' } };
		for (int i = 0; i < list.length; i++)
			for (int j = 0; j < list[i].length; j++)
				map.put(list[i][j], new Node(true, i, j));
		list = new char[][] { { '.', 'y', 'u', 'i', 'o', 'p' }, { '.', 'h', 'j', 'k', 'l' }, { 'b', 'n', 'm' } };
		for (int i = 0; i < list.length; i++)
			for (int j = 0; j < list[i].length; j++)
				map.put(list[i][j], new Node(false, i, j));

		st = new StringTokenizer(br.readLine());
		Node left = map.get(st.nextToken().charAt(0));
		Node right = map.get(st.nextToken().charAt(0));

		int ans = 0;
		char[] s = br.readLine().toCharArray();
		for (char c : s) {
			ans += 1;
			Node next = map.get(c);
			if (next.flag) {
				ans += calc(left.x, left.y, next.x, next.y);
				left = next;
			} else {
				ans += calc(right.x, right.y, next.x, next.y);
				right = next;
			}
		}
		System.out.println(ans);
	}

	static int calc(int x, int y, int ex, int ey) {
		return Math.abs(x - ex) + Math.abs(y - ey);
	}
}