import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int index, num;

		public Node(int index, int num) {
			// TODO Auto-generated constructor stub
			this.index = index;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		ArrayDeque<Node> dq = new ArrayDeque<Node>();
		for (int i = 0; i < N; i++)
			dq.add(new Node(i + 1, Integer.parseInt(st.nextToken())));

		while (N-- > 0) {
			Node now = dq.poll();
			sb.append(now.index + " ");

			if (dq.isEmpty())
				break;

			int cnt = now.num;
			if (cnt > 0)
				while (cnt-- > 1)
					dq.addLast(dq.poll());
			else
				while (cnt++ < 0)
					dq.addFirst(dq.pollLast());

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}