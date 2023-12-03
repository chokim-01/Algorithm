import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				q.add(Integer.parseInt(st.nextToken()));
		}
		while (n-- > 1)
			q.poll();

		System.out.println(q.peek());
	}
}