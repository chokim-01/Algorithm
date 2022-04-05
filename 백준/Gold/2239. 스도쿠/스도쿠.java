import java.util.LinkedList;
import java.util.Scanner;

class Main {
	static int N;
	static int[][] map;
	static LinkedList<Node> list;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = 0;
		map = new int[9][9];
		list = new LinkedList<>();

		for (int i = 0; i < 9; i++) {
			char[] a = sc.next().toCharArray();
			for (int j = 0; j < 9; j++) {
				map[i][j] = a[j] - '0';
				if (map[i][j] == 0) {
					list.add(new Node(i, j));
					N += 1;
				}
			}
		}
		dfs(0);
		// 0 : 채워지지 않은 칸

	}

	static void dfs(int index) {
		if (index == list.size()) {
			StringBuffer sb;
			for (int i = 0; i < 9; i++) {
				sb = new StringBuffer();
				for (int j = 0; j < 9; j++)
					sb.append(map[i][j]);
				System.out.println(sb.toString());
			}
			System.exit(0);

			return;
		}

		Node n = list.get(index);
		boolean[][] nums = new boolean[3][10];
		nums[0] = wideChk(n.x);
		nums[1] = heightChk(n.y);
		nums[2] = widheiChk(n.x, n.y);

		for (int i = 1; i <= 9; i++) {
			if (nums[0][i] || nums[1][i] || nums[2][i])
				continue;
			map[n.x][n.y] = i;
			dfs(index + 1);
			map[n.x][n.y] = 0;
		}
	}

	static boolean[] wideChk(int x) {
		boolean[] nums = new boolean[10];
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == 0)
				continue;
			nums[map[x][i]] = true;
		}
		return nums;
	}

	static boolean[] heightChk(int y) {
		boolean[] nums = new boolean[10];
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == 0)
				continue;
			nums[map[i][y]] = true;
		}
		return nums;
	}

	static boolean[] widheiChk(int x, int y) {
		boolean[] nums = new boolean[10];
		x = x / 3 * 3;
		y = y / 3 * 3;

		for (int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if (map[i][j] == 0)
					continue;
				nums[map[i][j]] = true;
			}
		}
		return nums;
	}
}