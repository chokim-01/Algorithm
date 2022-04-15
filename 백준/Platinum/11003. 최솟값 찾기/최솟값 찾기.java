import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	static class Node {
		int x, num;

		public Node(int x, int num) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.num = num;
		}

		@Override
		public String toString() {
			return x + " " + num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		Deque<Node> q = new LinkedList<Node>();

		// i-L부터 i까지 중 최솟값.
		// 가장 작은 값 : 처음에 들어있음
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i < N + 1; i++) {
			int next = Integer.parseInt(st.nextToken());

			// 마지막 값이 들어오려는 값보다 크다면 빼고 추가
			while (!q.isEmpty() && q.peekLast().num > next)
				q.pollLast();

			q.add(new Node(i, next));
			// 가장 마지막에 들어온 것 : 0
			while (!q.isEmpty()) {
				if (q.peek().x < i - L + 1)
					q.poll();
				else
					break;
			}
			bw.write(q.peek().num + " ");
		}
		bw.flush();
		bw.close();

	}
}
