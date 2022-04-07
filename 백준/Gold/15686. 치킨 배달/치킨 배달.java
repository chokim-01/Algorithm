import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static LinkedList<Node> shops;
	static LinkedList<Node> house;
	static int N, M;
	static int ans;

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
		M = sc.nextInt();
		ans = Integer.MAX_VALUE;

		house = new LinkedList<>();
		shops = new LinkedList<>();

		for (int i = 0, tmp; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp = sc.nextInt();
				if (tmp == 1)
					house.add(new Node(i, j));
				else if (tmp == 2)
					shops.add(new Node(i, j));
			}
		}
		boolean[] choice = new boolean[shops.size()];
		comb(0, 0, choice);
		System.out.println(ans);
	}

	static int calcChickenD(boolean[] shop) {
		int allCnt = 0;
		for (Node h : house) {
			int cnt = Integer.MAX_VALUE;
			for (int i = 0; i < shop.length; i++) {
				if (!shop[i])
					continue;
				Node s = shops.get(i);
				cnt = Math.min(cnt, Math.abs(s.x - h.x) + Math.abs(s.y - h.y));
				
			}
			allCnt += cnt;
			if(allCnt >= ans)
				return Integer.MAX_VALUE;
		}

		return allCnt;
	}

	static void comb(int index, int choosen, boolean[] choice) {

		if (index == choice.length) {
			if (choosen == 0)
				return;
			int tmp = calcChickenD(choice);
			ans = ans < tmp ? ans : tmp;
			return;
		}
		if (M - (choosen+1) >= 0) {
			choice[index] = true;
			comb(index + 1, choosen + 1, choice);// chose
			choice[index] = false;
		}
		comb(index + 1, choosen, choice); // not chosen

	}
}
