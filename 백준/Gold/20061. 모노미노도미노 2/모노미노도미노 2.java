import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Node {
		int x, y, x2, y2;

		public Node(int x, int y, int x2, int y2) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	static boolean[][] map1;
	static boolean[][] map2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map1 = new boolean[10][4];
		map2 = new boolean[10][4];

		int N = sc.nextInt();

		ans1 = 0;
		ans2 = 0;
		for (int i = 0; i < N; i++) {
			int t = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			// 1 : 1x1
			// 2 : 1x2
			// 3 : 2x1
			for (int a = 0; a < 4; a++)
				for (int b = 0; b < 4; b++)
					map1[a][b] = map2[a][b] = false;

			Queue<Node> q1 = new LinkedList();
			Queue<Node> q2 = new LinkedList();
			// to map1
			if (t == 1) {
				q1.add(new Node(3, y, 3, y));
				q2.add(new Node(3, 3 - x, 3, 3 - x));
			}
			if (t == 2) {
				q1.add(new Node(3, y, 3, y + 1));
				q2.add(new Node(3, 3 - x, 2, 3 - x));
			}
			if (t == 3) {
				q1.add(new Node(2, y, 3, y));
				q2.add(new Node(3, 3 - x, 3, 2 - x));
			}

			moveMap(map1, q1);
			moveMap(map2, q2);

			blockClear(map1);
			blockClear(map2);

			blockOverflow(map1);
			blockOverflow(map2);
			
		}
		ans2 += getAns2(map1);
		ans2 += getAns2(map2);

		System.out.println(ans1 + "\n"+ans2);
	}

	static int ans1;
	static int ans2;

	static int getAns2(boolean[][] map) {
		int cnt = 0;
		for (int i = 6; i < 10; i++)
			for (int j = 0; j < 4; j++)
				if (map[i][j])
					cnt += 1;

		return cnt;
	}

	static void blockOverflow(boolean[][] map) {
		for (int i = 4; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (!map[i][j])
					continue;
				boolean[][] tmp = new boolean[4][4];
				for (int a = 0; a < 4; a++)
					tmp[a] = map[i + a].clone();

				for (int a = 6; a < 10; a++) {
					map[a] = tmp[a - 6];
				}
				for (int a = 4; a < 6; a++)
					Arrays.fill(map[a], false);
				return;
			}
		}
	}
	// i == 4면
	// 6부터 9에 4부터 7을 복사
	// 6 7 8 9 | 4 5 6 7 -2
	// i == 5면
	// 6부터 9에 5부터 8을 복사
	// 6 7 8 9 | 5 6 7 8 -1

	static void blockClear(boolean[][] map) {
		for (int i = 9; i >= 5; i--) {
			int c = 0;
			for (int j = 0; j < 4; j++)
				if (map[i][j])
					c += 1;
			if (c == 4) {
				ans1 += 1;

				for (int a = i; a >= 4; a--)
					map[a] = map[a - 1].clone();
				i += 1;

			}
		}

	}

	static void moveMap(boolean[][] map, Queue<Node> q) {
		
		while (!q.isEmpty()) {
			Node n = q.poll();

			int nx = n.x + 1;
			int ny = n.y;
			int nx2 = n.x2 + 1;
			int ny2 = n.y2;

			if (nx >= 10 || nx2 >= 10 || map[nx][ny] || map[nx2][ny2]) {
				map[n.x][n.y] = true;
				map[n.x2][n.y2] = true;
				return;
			}
			q.add(new Node(nx, ny, nx2, ny2));
		}

	}

}