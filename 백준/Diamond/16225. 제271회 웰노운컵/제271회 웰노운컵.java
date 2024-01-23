import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	static class Node {
		int a, b;

		public Node(int a, int b) {
			// TODO Auto-generated constructor stub
			this.a = a;
			this.b = b;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];
		int[] b = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			a[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			b[i] = Integer.parseInt(st.nextToken());

		PriorityQueue<Node> list = new PriorityQueue<>((x, y) -> (x.b - y.b));
		for (int i = 0; i < N; i++)
			list.add(new Node(a[i], b[i]));

		long ans = list.poll().a;

		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
		for (int i = 0; i < N / 2 - 1; i++) {
			q.add(list.poll().a);
			q.add(list.poll().a);
			ans += q.poll();
		}
		System.out.println(ans);
	}
}