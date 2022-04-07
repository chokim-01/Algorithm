import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static class Node {
		int x, y, cnt;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			// x, y : 치킨집
		}
	}

	static List<Node> list;
	static Queue<Node> q;
	static int M;
	static int[][] map;
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 맵
		M = sc.nextInt(); // 치킨집 개수 M개를 고름

		map = new int[N][N];

		q = new LinkedList<>();
		list = new LinkedList<>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1)
					q.add(new Node(i, j));
				if (map[i][j] == 2) {
					list.add(new Node(i, j));
					map[i][j] = 0;
				}
			}
		// 집을 큐에 넣음
		// 치킨집을 리스트에 넣음

		// 치킨 집을 고름 ( 조합 )
		rec(new LinkedList<>(), 0, 0);
		System.out.println(res);

	}

	static int find_chicken(List<Node> lst) {
		Queue<Node> que = new LinkedList<>();
		for (Node n : q)
			que.add(n);
		int cnt = 0;
		/*
		 * for (int i = 0; i < map.length; i++)
		 * System.out.println(Arrays.toString(map[i]));
		 * 
		 * System.out.println();
		 */
		while (!que.isEmpty()) {
			int tmp = Integer.MAX_VALUE;
			Node nq = que.poll();
			for(Node n :lst) {
				int val = Math.abs(nq.x-n.x)+Math.abs(nq.y-n.y);
				if(tmp > val)
					tmp = val;
			}
			cnt += tmp;
			
		}
		return cnt;
	}

	static void rec(List<Node> tmp_list, int idx, int cnt) {
		if (cnt == M) {
			// 조합 돌린거에서 돌림
			
			int count = find_chicken(tmp_list);
			if (res > count)
				res = count;

			return;
		}
		if (idx == list.size())
			return;

		tmp_list.add(new Node(list.get(idx).x, list.get(idx).y));
		rec(tmp_list, idx + 1, cnt + 1);
		tmp_list.remove(tmp_list.size() - 1);
		rec(tmp_list, idx + 1, cnt);

	}

}