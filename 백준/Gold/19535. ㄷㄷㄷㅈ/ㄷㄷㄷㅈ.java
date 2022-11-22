import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static LinkedList<Integer>[] con;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		dCount = 0;
		gCount = 0;
		con = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++)
			con[i] = new LinkedList<>();

		Queue<Node> q = new LinkedList<>();

		for (int i = 0, a, b; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			con[a].add(b);
			con[b].add(a);
			q.add(new Node(a, b));
		}
		for (int i = 1; i <= N; i++) {
			long a = con[i].size();
			if (a >= 3) {
				gCount += a * (a - 1) * (a - 2) / 6;
			}
		}
		find(q);

		if (dCount > gCount * 3)
			System.out.println("D");
		else if (dCount < gCount * 3)
			System.out.println("G");
		else
			System.out.println("DUDUDUNGA");
	}

	static long dCount;
	static long gCount;

	static void find(Queue<Node> q) {
		// num : Group A : next : Group B
		// num과 next는 연결되어있음을 보장.
		// 따라서 num-next와 next-num count의 곱
		// con[num].size = groupA 개수. con[next].size = 3번째 개수
		while (!q.isEmpty()) {
			Node n = q.poll();
			dCount += (con[n.x].size() - 1) * (con[n.y].size() - 1);
		}
	}
}