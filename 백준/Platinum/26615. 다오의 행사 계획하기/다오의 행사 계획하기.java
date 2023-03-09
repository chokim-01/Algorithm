import java.beans.EventSetDescriptor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T;
	static long[] ans;
	static int[] depth;
	static int[][] par;
	static boolean[][][] map;
	static ArrayDeque<EventNode> events;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

	}

	static class EventNode implements Comparable<EventNode> {
		int start, end, a, b, c, d, v;

		public EventNode(int start, int end, int a, int b, int c, int d, int v) {
			this.start = start;
			this.end = end;
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.v = v;
		}

		@Override
		public int compareTo(EventNode o) {
			// TODO Auto-generated method stub
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		input();

		conNode();

		setPrefixSum();

		long a = 0;
		for (int t = 1; t <= T; t++) {
			a += ans[t];
			bw.write(a + "\n");
		}
		bw.flush();
		bw.close();
	}

	static void setPrefixSum() {
		while (!events.isEmpty())
			getDistance(events.poll());
	}

	static void getDistance(EventNode events) {
		int count = 1;

		int aNode = M * events.a + events.b + 1;
		int bNode = M * events.c + events.d + 1;

		if (depth[aNode] < depth[bNode]) {
			int tmp = aNode;
			aNode = bNode;
			bNode = tmp;
		}
		// == depth
		int dep = 17;
		while (--dep > -1 && depth[aNode] != depth[bNode]) {
			if (1 << dep > depth[aNode] - depth[bNode])
				continue;
			aNode = par[aNode][dep];
			count += 1 << dep;
		}

		// find par
		if (aNode != bNode) {
			dep = 17;
			while (--dep > -1) {
				if (par[aNode][dep] == par[bNode][dep])
					continue;
				aNode = par[aNode][dep];
				bNode = par[bNode][dep];
				count += 2 * (1 << dep);
			}
			count += 2;
		}
		int value = count * events.v;
		ans[events.start] += value;
		ans[events.end+1] -= value;

	}

	static void conNode() {
		int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		Stack<Node> s = new Stack<>();
		boolean[] visit = new boolean[N * M + 1];
		visit[1] = true;
		s.add(new Node(0, 0));

		while (!s.isEmpty()) {
			Node now = s.pop();
			int nowNum = now.x * M + now.y + 1;
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dxy[d][0];
				int ny = now.y + dxy[d][1];
				if (!mapChk(nx, ny) || visit[M * nx + ny + 1])
					continue;
				if (map[now.x][now.y][0] && d == 1)
					continue;
				if (map[now.x][now.y][1] && d == 3)
					continue;
				if (map[nx][ny][0] && d == 0)
					continue;
				if (map[nx][ny][1] && d == 2)
					continue;

				Node next = new Node(nx, ny);
				int nextNum = nx * M + ny + 1;

				visit[nextNum] = true;
				par[nextNum][0] = nowNum;
				depth[nextNum] = depth[nowNum] + 1;
				s.add(next);
			}
		}
		for (int j = 1; j < 17; j++)
			for (int i = 1; i < par.length; i++)
				par[i][j] = par[par[i][j - 1]][j - 1];
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		ans = new long[T + 2];
		depth = new int[N * M + 1];
		par = new int[N * M + 1][17];
		map = new boolean[N][M][2];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j][0] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M - 1; j++)
				map[i][j][1] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
		}

		events = new ArrayDeque();
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			events.add(new EventNode(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
	}

	static void print() {
		for (int i = 1; i < par.length; i++) {
			System.out.println(Arrays.toString(par[i]));
		}
	}

}
