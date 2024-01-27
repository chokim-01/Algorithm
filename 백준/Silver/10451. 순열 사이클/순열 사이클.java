import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	static int T;
	static int N;
	static LinkedList<Integer> node;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();

		while (T-- > 0) {
			N = sc.nextInt();
			ans = 0;
			node = new LinkedList<Integer>();
			for (int i = 0; i < N; i++) {
				node.add(i, sc.nextInt() - 1);
			}
			boolean[] visit = new boolean[N];
			
			for(int i = 0;i<N;i++) {
				if(visit[i])
					continue;
				visit = bfs(i, visit);
			}

			System.out.println(ans);
		}

	}

	static boolean[] bfs(int x, boolean[] visit) {
		Queue<Integer> q = new LinkedList<>();
		boolean flag = false;
		boolean[] v = new boolean[N];
		v = visit.clone();
		q.add(x);

		while (!q.isEmpty()) {
			int n = q.poll();
			v[n] = true;
			q.add(node.get(n));

			if (node.get(n) == x) {
				flag = true;
				ans += 1;
				break;
			}

		}
		if (flag)
			return v;
		return visit;
	}

}
