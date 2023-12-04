import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		sc.nextLine();

		int[] v = new int[N + 1];
		Arrays.fill(v, -1);
		int[] nums = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Queue<Integer> q = new ArrayDeque<>();
		for (int n : nums) {
			q.add(n);
			v[n] = 0;
		}

		int last = 0;
		int count = 0;
		while (!q.isEmpty()) {
			count++;
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int now = q.poll();
				last = now;
				for (int i = 0; i < 20; i++) {
					int next = (now ^ (1 << i));
					if (next > N)
						continue;
					if (v[next] != -1)
						continue;
					v[next] = count;
					q.add(next);
				}
			}
		}
		System.out.println(v[last]);
	}
}