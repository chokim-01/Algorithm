import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static int N, M;
	static boolean[][] wall;
	static boolean[][] visit;
	static int[][] map;
	static int[][] mapNum;
	static int num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] st = br.readLine().split(" ");
		N = Integer.parseInt(st[0]);
		M = Integer.parseInt(st[1]);
		wall = new boolean[N][M];
		visit = new boolean[N][M];
		map = new int[N][M];
		mapNum = new int[N][M];
		num = 1;

		// true : blank | false : wall
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				wall[i][j] = (s.charAt(j) - 48 == 0) ? true : false;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (wall[i][j] && !visit[i][j])
					bfs(i, j);
			}
		}
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				if(wall[i][j]) {
					bw.append("0");
					continue;
				}
				HashSet<Integer> set = new HashSet<Integer>();
				int cnt = 1;
				for(int d = 0;d<4;d++) {
					int nx = i + dxy[d][0];
					int ny = j + dxy[d][1];

					if(!mapChk(nx, ny))
						continue;
					if(set.contains(mapNum[nx][ny]))
						continue;
					set.add(mapNum[nx][ny]);
					cnt += map[nx][ny];
				}
				bw.append(String.valueOf(cnt%10));
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();

	}

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			// TODO Auto-generated constructor stub
		}
	}
	static boolean mapChk(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=M)
			return false;
		return true;
	}

	static void bfs(int x, int y) {
		// 큐에 추가 하고 끝나면 더.
		int cnt = 0;
		Queue<Node> q = new LinkedList<Node>();
		Queue<Node> qSet = new LinkedList<Node>();
		q.add(new Node(x, y));
		qSet.add(new Node(x, y));
		visit[x][y] = true;

		while (!q.isEmpty()) {
			Node n = q.poll();
			cnt +=1 ;
			for(int d = 0;d<4;d++) {
				int nx = n.x + dxy[d][0];
				int ny = n.y + dxy[d][1];
				if(!mapChk(nx, ny) || visit[nx][ny])
					continue;
				if(!wall[nx][ny])
					continue;
				visit[nx][ny] = true;
				q.add(new Node(nx,ny));
				qSet.add(new Node(nx,ny));
			}
		}
		while(!qSet.isEmpty()) {
			Node n = qSet.poll();
			map[n.x][n.y] = cnt;
			mapNum[n.x][n.y]= num; 
		}
		num++;
	}
}