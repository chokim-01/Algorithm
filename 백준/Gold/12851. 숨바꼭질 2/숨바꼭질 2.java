import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] mul = { { 1, -1 }, { 1, 1 }, { 2, 0 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int[] v = new int[100001];

		int a = sc.nextInt();
		int b = sc.nextInt();

		if (b <= a)
			sb.append(a - b).append("\n").append(1);
		else {
			Queue<Integer> q = new ArrayDeque<>();
			q.add(a);
			v[a] = 1;
			int ans1 = 0;
			int ans2 = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				ans1++;
				for (int i = 0; i < size; i++) {
					int n = q.poll();
					for (int d = 0; d < 3; d++) {
						int next = n * mul[d][0] + mul[d][1];
						if (next < 0 || next > 100000)
							continue;
						if (next == b)
							ans2++;
						else if (v[next] == 0 || v[next] == v[n] + 1) {
							q.add(next);
							v[next] = v[n] + 1;
						}
					}
				}
				if (ans2 != 0)
					break;
			}
			sb.append(ans1).append("\n").append(ans2);
		}
		System.out.println(sb);
	}
}
