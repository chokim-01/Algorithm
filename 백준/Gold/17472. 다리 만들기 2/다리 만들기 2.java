import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static int[][] map;
	static int[][] edges;

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node implements Comparable<Node> {
		int x, y, d, cnt;

		public Node(int x) {
			// TODO Auto-generated constructor stub
			this.x = x;
		}

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int d, int cnt) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.y - o.y;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] ss = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(ss[j]);
			}
		}

		// 맵 구성
		int islandCount = islandNumSet();

		// 섬 거리 계산
		edges = new int[islandCount][islandCount];
		for (int i = 0; i < islandCount; i++)
			Arrays.fill(edges[i], Integer.MAX_VALUE);
		
		// 섬 거리를 간선으로 만듬
		islandListSet(islandCount);

		// Prim으로 최소 사이클 거리 계산
		int ans = calcBridgeLeastCost();
		
		System.out.println(ans == 0 ? -1 : ans);

	}
	static int islandNumSet() {
		boolean[][] visit = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j] || map[i][j] == 0)
					continue;
				Queue<Node> q = new LinkedList<>();
				q.add(new Node(i, j));
				visit[i][j] = true;
				map[i][j] = ++cnt;

				while (!q.isEmpty()) {
					Node n = q.poll();

					for (int d = 0; d < 4; d++) {
						int nx = n.x + dxy[d][0];
						int ny = n.y + dxy[d][1];
						if (!mapChk(nx, ny) || visit[nx][ny] || map[nx][ny] != 1)
							continue;
						visit[nx][ny] = true;
						map[nx][ny] = cnt;
						q.add(new Node(nx, ny));
					}
				}
			}
		}
		return cnt;

	}
	
	static void islandListSet(int islandCount) {
		LinkedList<Node> iList[] = new LinkedList[islandCount];
		for (int i = 0; i < islandCount; i++)
			iList[i] = new LinkedList<>();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] != 0)
					for (int d = 0; d < 4; d++)
						iList[map[i][j] - 1].add(new Node(i, j, d, 0));

		for (int i = 0; i < islandCount; i++)
			islandDistanceSet(iList[i], i + 1);
	}

	static void islandDistanceSet(LinkedList<Node> list, int islandNum) {
		Queue<Node> q = list;

		while (!q.isEmpty()) {
			Node n = q.poll();

			int nx = n.x + dxy[n.d][0];
			int ny = n.y + dxy[n.d][1];

			if (!mapChk(nx, ny) || map[nx][ny] == islandNum)
				continue;
			if (map[nx][ny] != 0) {
				if (n.cnt < 2)
					continue;
				if (edges[islandNum - 1][map[nx][ny] - 1] <= n.cnt)
					continue;
				edges[islandNum - 1][map[nx][ny] - 1] = n.cnt;
				edges[map[nx][ny] - 1][islandNum - 1] = n.cnt;
				continue;
			}
			q.add(new Node(nx, ny, n.d, n.cnt + 1));

		}
	}

	// MST Prim
	static int calcBridgeLeastCost() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0)); // y : cnt

		boolean[] visit = new boolean[edges.length];
		int cnt = 0;
		int ans = 0;
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visit[n.x])
				continue;
			visit[n.x] = true;
			ans += n.y;
			for (int i = 0; i < edges[n.x].length; i++) {
				if (visit[i] || edges[n.x][i] == Integer.MAX_VALUE)
					continue;
				pq.add(new Node(i, edges[n.x][i]));
			}
			// 정점 순회 끝
			if (++cnt == edges.length)
				break;
		}
		if(cnt != edges.length)
			return 0;
		return ans;
	}
	
	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}
