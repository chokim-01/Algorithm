import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static int N;
	static ArrayList<Node> bList;
	static ArrayList<Node> hList;
	static boolean[] visitRight;
	static boolean[] visitLeft;

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
		N = sc.nextInt();
		visitRight = new boolean[N * 2];
		visitLeft = new boolean[N * 2];
		bList = new ArrayList<>();
		hList = new ArrayList<>();
		ans = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int n = sc.nextInt();
				if (n == 1) {
					if ((i + j) % 2 == 0)
						bList.add(new Node(i, j));
					else
						hList.add(new Node(i, j));
				}
			}
		}

		int answer = 0;
		dfs(0, 0, bList);
		answer += ans;
		ans = 0;
		dfs(0, 0, hList);
		answer += ans;
		System.out.println(answer);
	}

	static int ans;

	static void dfs(int index, int count, ArrayList<Node> list) {

		if (index == list.size()) { // 가능한 것만 골랐으므로 정답. ans = ans < count ? count :
			ans = ans < count ? count : ans;
			return;
		}

		// 추가할 것이 true가 아니면 오른쪽대각선, 왼쪽대각선
		Node n = list.get(index);
		if (!visitRight[n.x - n.y + N] && !visitLeft[n.x + n.y]) {
			visitRight[n.x - n.y + N] = true;
			visitLeft[n.x + n.y] = true;
			dfs(index + 1, count + 1, list);
			visitLeft[n.x + n.y] = false;
			visitRight[n.x - n.y + N] = false;
		}
		dfs(index + 1, count, list);

	}

}
