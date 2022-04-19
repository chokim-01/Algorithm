import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {
	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		LinkedList<Node>[] list = new LinkedList[K + 1];
		for (int i = 0; i <= K; i++)
			list[i] = new LinkedList<>();

		int[][] map = new int[N][N];

		int zeroCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n != 0)
					list[n].add(new Node(i, j));
				else
					zeroCnt +=1;
			}
		}
		st = new StringTokenizer(br.readLine());

		int s = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;

		int time = 0;
		while (time < s) {
			for (int i = 1; i < list.length; i++) {
				@SuppressWarnings("unchecked")
				Queue<Node> q = (Queue<Node>) list[i].clone();
				int qsize = q.size();
				for (int t = 0; t < qsize; t++) {
					Node n = q.poll();
					for (int d = 0; d < 4; d++) {
						int nx = n.x + dxy[d][0];
						int ny = n.y + dxy[d][1];
						if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] != 0)
							continue;
						map[nx][ny] = i;
						zeroCnt--;
						q.add(new Node(nx, ny));
					}
				}
				list[i].addAll(q);

			}
			if(zeroCnt == 0)
				break;
			time++;
		}
		System.out.println(map[x][y]);

	}
}
