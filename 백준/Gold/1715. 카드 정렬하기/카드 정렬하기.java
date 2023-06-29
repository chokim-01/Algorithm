import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<Long> q = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());
		while (N-- > 0)
			q.add(Long.parseLong(br.readLine()));

		long ans = 0;
		while (!q.isEmpty()) {
			if (q.size() == 1)
				break;
			long a = q.poll();
			long b = q.poll();
			ans += a + b;
			q.add(a + b);
		}
		System.out.println(ans);
	}
}
