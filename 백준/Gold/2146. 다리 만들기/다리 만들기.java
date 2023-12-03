import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int res = 10000;

	static class Node {
		int x, y;
		int cnt;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			// TODO Auto-generated constructor stub
		}

		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			// TODO Auto-generated constructor stub
		}

	}

	static int dxy[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();

		boolean visit[][] = new boolean[N][N];
		int idx = 1;
		Queue<Node> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (visit[i][j] || map[i][j] == 0)
					continue;
				idx += 1;
				map[i][j] = idx;
				q.add(new Node(i, j));

				while (!q.isEmpty()) {
					Node n = q.poll();

					for (int a = 0; a < 4; a++) {
						int nx = n.x + dxy[a][0];
						int ny = n.y + dxy[a][1];

						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;
						if (map[nx][ny] == 1 && !visit[nx][ny]) {
							q.add(new Node(nx, ny));
							map[nx][ny] = idx;
							visit[nx][ny] = true;
						}

					}

				}

			}
		}
		/*
		 * for (int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));
		 */
		// 마지막 1부터 다음 1까지
		chkCnt(map);

		System.out.println(res);
	}

	static void chkCnt(int[][] map) {

		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					int startX = i;
					int startY = j;
					
					q.add(new Node(i,j,0));
					boolean[][] visit = new boolean[N][N];
					
					while(!q.isEmpty()) {
						Node n = q.poll();
						visit[n.x][n.y] = true;
						
						for(int a = 0;a<4;a++) {
							int nx = n.x + dxy[a][0];
							int ny = n.y + dxy[a][1];
							
							if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny])
								continue;
							if(map[nx][ny] == map[startX][startY]) {
								continue;
							}
							if(map[nx][ny] == 0) {
								visit[nx][ny] = true;
								q.add(new Node(nx, ny,n.cnt+1));
							} else
								if(res > n.cnt)
									res = n.cnt;	
							
						}
					}

				}

			}
		}

	}

}
