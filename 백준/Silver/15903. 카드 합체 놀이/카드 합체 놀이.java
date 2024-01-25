import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] n = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		StringTokenizer st = new StringTokenizer(br.readLine());

		PriorityQueue<Long> q = new PriorityQueue<>();
		while (st.hasMoreTokens())
			q.add(Long.parseLong(st.nextToken()));
		while (n[1]-- > 0) {
			long sum = q.poll() + q.poll();
			q.add(sum);
			q.add(sum);
		}
		long ans = 0;
		while (!q.isEmpty())
			ans += q.poll();
		System.out.println(ans);
	}
}