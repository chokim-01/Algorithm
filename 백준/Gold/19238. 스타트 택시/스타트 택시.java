import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static int taxiX, taxiY, taxiK;
	static HashMap<Integer, Node> customers;
	static int[][] dxy = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	static class Node {
		int num, x, y, destX, destY;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Node(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}

		public Node(int num, int x, int y, int destX, int destY) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.x = x;
			this.y = y;
			this.destX = destX;
			this.destY = destY;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		taxiK = sc.nextInt();
		map = new int[N][N];
		customers = new HashMap<>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();

		taxiX = sc.nextInt() - 1;
		taxiY = sc.nextInt() - 1;
		// Input
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			int ex = sc.nextInt() - 1;
			int ey = sc.nextInt() - 1;
			map[x][y] = i + 3;
			customers.put(i + 3, new Node(i + 3, x, y, ex, ey));
		}

		// 모든 손님을 이동시킬 때 까지
		while (M-- > 0) {
			// return 손님과의 거리, 손님 번호
			int[] info = gotoCustomer();
			// 도착하지 못함.
			if (info == null) {
				taxiK = -1;
				break;
			}
			int dist = info[0];

			// 목적지까지 거리 찾기
			int dist2 = cusToDest(taxiX, taxiY, customers.get(info[1]));
			// 도착하지 못함
			if (dist2 == -1) {
				taxiK = -1;
				break;
			}

			// taxiK : 연료. 갈 수 없으면 -1
			taxiK = taxiK - dist - dist2;
			if (taxiK < 0) {
				taxiK = -1;
				break;
			}
			// 이동하면서 소모한 연료의 두배 충전
			taxiK = taxiK + dist2 * 2;
		}
		System.out.println(taxiK);
	}

	static int cusToDest(int x, int y, Node dest) {
		if (x == dest.destX && y == dest.destY)
			return 0;

		Queue<Node> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		visit[x][y] = true;
		q.add(new Node(x, y));
		int time = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();
			time += 1;
			for (int s = 0; s < qsize; s++) {
				Node n = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = n.x + dxy[d][0];
					int ny = n.y + dxy[d][1];
					if (!mapChk(nx, ny) || map[nx][ny] == 1 || visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					if (nx == dest.destX && ny == dest.destY) {
						taxiX = nx;
						taxiY = ny;
						return time;
					}
					q.add(new Node(nx, ny));
				}
			}
		}
		// 도착하지 못함
		return -1;

	}

	static int[] gotoCustomer() {
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(taxiX, taxiY));
		// 현재 위치에 있으면 바로 제거하고 null
		if (map[taxiX][taxiY] >= 2) {
			int tmp = map[taxiX][taxiY];
			map[taxiX][taxiY] = 0;
			return new int[] { 0, tmp };
		}

		boolean[][] visit = new boolean[N][N];
		visit[taxiX][taxiY] = true;
		int rx = 33;
		int ry = 33;
		int time = 0;
		while (!q.isEmpty()) {
			time += 1;
			boolean flag = false;
			int qsize = q.size();
			for (int s = 0; s < qsize; s++) {
				Node n = q.poll();

				for (int d = 0; d < 4; d++) {
					int nx = n.x + dxy[d][0];
					int ny = n.y + dxy[d][1];

					if (!mapChk(nx, ny) || map[nx][ny] == 1 || visit[nx][ny])
						continue;
					visit[nx][ny] = true;
					// 손님
					if (map[nx][ny] != 0) {
						flag = true;
						if (rx == nx)
							ry = ry > ny ? ny : ry;
						if (rx > nx) {
							rx = nx;
							ry = ny;
						}
					}
					q.add(new Node(nx, ny));
				}
			}
			if (flag) {
				int tmp = map[rx][ry];
				map[rx][ry] = 0;
				taxiX = rx;
				taxiY = ry;
				return new int[] { time, tmp };
			}
		}
		// 도착하지 못함
		return null;
	}

	// 맵 범위
	static boolean mapChk(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}
}
