import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, E;

	static class Node {
		int x, cnt;

		public Node(int x, int cnt) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.cnt = cnt;
		}
	}

	static LinkedList<int[]> list[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		list = new LinkedList[N + 1];
		for (int i = 0; i < N + 1; i++)
			list[i] = new LinkedList<>();

		for (int i = 0, a, b, c; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			list[a].add(new int[] { b, c });
			list[b].add(new int[] { a, c });
		}
		st = new StringTokenizer(br.readLine());
		int a1 = Integer.parseInt(st.nextToken());
		int a2 = Integer.parseInt(st.nextToken());
		// 1번정점에서 N번 정점까지. 도중에 두개를 거쳐가야함.
		int[] dist1 = getDistance(1); // 1번에서 다른곳 모두
		int[] dist2 = getDistance(a1); // a1번에서 다른곳 모두
		int[] dist3 = getDistance(a2); // a2번에서 다른곳 모두

		int ans = Integer.MAX_VALUE;

		// 1번에서 시작해서 a1 a2 N을 도달 못할 경우 : -1
		if (dist1[a1] == Integer.MAX_VALUE || dist1[a2] == Integer.MAX_VALUE || dist1[N] == Integer.MAX_VALUE) {
			ans = -1;
		} // 양방향이라 1번에서 갈 수 있다면 모두 가능.
			// 최소가 되는 경우
			// 1에서 dist1[a1], a1에서 dist2[a2], a2에서 dist3[N]
			// 1에서 dist1[a2], a2에서 dist3[a1], a1에서 dist2[N]
		else 
			ans = Math.min(dist1[a1] + dist2[a2] + dist3[N], dist1[a2] + dist3[a1] + dist2[N]);

		System.out.println(ans);
	}

	static int[] getDistance(int x) {
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[x] = 0;
		Queue<Node> q = new LinkedList();
		q.add(new Node(x, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int[] l : list[n.x]) {
				if (distance[l[0]] > n.cnt + l[1]) {
					distance[l[0]] = n.cnt + l[1];
					q.add(new Node(l[0], n.cnt + l[1]));
				}
			}
		}

		return distance;
	}
}