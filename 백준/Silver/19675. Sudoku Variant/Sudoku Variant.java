import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static final int N = 3;
	static int[][] map;
	static int ans;
	static ArrayList<Node> zero;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		map = new int[N][N];
		zero = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = s[j].charAt(0) - '0';
				if (map[i][j] == 0)
					zero.add(new Node(i, j));
			}
		}
		ans = 0;
		dfs(0);
		System.out.println(ans);
	}

	static void dfs(int index) {
		if (index == zero.size()) {
			ans++;
			return;
		}
		boolean[] flag = new boolean[10];
		Node now = zero.get(index);
		for (int i = 0; i < 3; i++) {

			flag[map[now.x][i]] = true;
			flag[map[i][now.y]] = true;
		}
		for (int i = 1; i < 10; i++) {
			if (flag[i])
				continue;
			map[now.x][now.y] = i;
			dfs(index + 1);
			map[now.x][now.y] = 0;
		}
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}
}