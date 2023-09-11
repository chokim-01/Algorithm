import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	static class Node implements Comparable<Node> {
		String s;

		public Node(String s) {
			// TODO Auto-generated constructor stub
			this.s = s;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub

			String a = this.s + o.s;
			String b = o.s + this.s;
			return b.compareTo(a);
		}

		@Override
		public String toString() {
			return this.s.toString();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		ArrayList<Integer> lst = new ArrayList<>();
		PriorityQueue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < K; i++) {
			String s = br.readLine();
			lst.add(Integer.parseInt(s));
			q.add(new Node(s));
		}
		Collections.sort(lst, Collections.reverseOrder());

		for (int i = 0; i < N - K; i++)
			q.add(new Node(String.valueOf(lst.get(0))));
		StringBuilder ans = new StringBuilder();
		while (!q.isEmpty())
			ans.append(q.poll());
		System.out.println(ans);
	}
}