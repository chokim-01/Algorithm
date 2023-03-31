import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
	static int N;
	static Node ari;
	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
	static char[][] map;
	static boolean[][] light;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new char[N][N];
		light = new boolean[N][N];

		String order = sc.next();

		ari = new Node(0, 0, 2);
		Queue<Node> zombies = new ArrayDeque<Node>();
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'Z')
					zombies.add(new Node(i, j, 2));
			}
		}
		String ans = "Phew...";
		for (char o : order.toCharArray()) {
			switch (o) {
			case 'F':
				if (!moveAri(zombies))
					ans = "Aaaaaah!";
				break;
			case 'R':
				ari.dir = (ari.dir + 1) % 4;
				break;
			case 'L':
				ari.dir = (ari.dir + 3) % 4;
				break;
			}
			if (!moveZombie(ari, zombies))
				ans = "Aaaaaah!";
			if (ans.equals("Aaaaaah!"))
				break;
		}
		System.out.println(ans);
	}

	static void turnOn(int x, int y) {
		light[x][y] = true;
		for (int d = 0; d < 8; d++) {
			int nx = x + dxy[d][0];
			int ny = y + dxy[d][1];
			if (!mapChk(nx, ny))
				continue;
			light[nx][ny] = true;
		}
	}

	static boolean moveAri(Queue<Node> zombies) {
		int nx = ari.x + dxy[ari.dir][0];
		int ny = ari.y + dxy[ari.dir][1];
		if (!mapChk(nx, ny))
			return true;
		if (map[nx][ny] == 'S')
			turnOn(nx, ny);
		if (light[nx][ny]) {
			ari = new Node(nx, ny, ari.dir);
			return true;
		}
		for (Node n : zombies)
			if (nx == n.x && ny == n.y)
				return false;
		ari = new Node(nx, ny, ari.dir);

		return true;
	}

	static boolean moveZombie(Node ari, Queue<Node> zombies) {
		int size = zombies.size();
		while (size-- > 0) {
			Node n = zombies.poll();

			// go zombies
			int nx = n.x + dxy[n.dir][0];
			int ny = n.y + dxy[n.dir][1];

			if (!mapChk(nx, ny)) {
				n.dir = (n.dir + 2) % 4;
				zombies.add(new Node(n.x, n.y, n.dir));
				continue;
			}
			if (nx == ari.x && ny == ari.y)
				if (!light[nx][ny])
					return false;

			zombies.add(new Node(nx, ny, n.dir));
		}

		return true;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}

	static class Node {
		int x, y, dir;

		public Node(int x, int y, int dir) {

			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "[" + x + " : " + y + " ] ";
		}
	}
}