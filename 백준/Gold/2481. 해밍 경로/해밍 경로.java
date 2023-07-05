import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	static class Node {
		int index, num;

		public Node(int index, int num) {
			// TODO Auto-generated constructor stub
			this.index = index;
			this.num = num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int first = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine(), 2);
			if (i == 0)
				first = n;
			map.put(n, i + 1);
		}

		int[] visit = new int[N + 1];
		Arrays.fill(visit, -1);
		Queue<Node> q = new ArrayDeque<>();
		visit[1] = 0;
		q.add(new Node(1, first));
		while (!q.isEmpty()) {
			Node now = q.poll();

			for (int i = 0; i < K; i++) {
				int n = 1 << i;
				if (!map.containsKey(now.num ^ n))
					continue;
				int next = map.get(now.num ^ n);
				if (visit[next] != -1)
					continue;
				visit[next] = now.index;
				q.add(new Node(next, now.num ^ n));
			}
		}
		StringBuilder ans = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			int a = Integer.parseInt(br.readLine());
			if (visit[a] == -1) {
				ans.append(-1);

			} else {
				Stack<Integer> stack = new Stack<>();
				while (a > 0) {
					stack.add(a);
					a = visit[a];
				}
				while (!stack.isEmpty())
					ans.append(stack.pop() + " ");
			}
			ans.append("\n");
		}
		System.out.println(ans);
	}
}