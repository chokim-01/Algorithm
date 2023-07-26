import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++)
			q.add(i);

		int ans = -1;
		while (q.size() != 1) {
			q.add(q.poll());
			int k = K - 1;
			while (k-- > 0) {
				if (q.size() == 1)
					break;
				q.poll();
			}
			if (q.size() < K) {
				ans = q.poll();
				break;
			}
		}
		System.out.println(ans == -1 ? q.poll() : ans);
	}
}