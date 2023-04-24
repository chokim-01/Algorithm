import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();

		while (N-- > 0) {
			int num = Integer.parseInt(br.readLine());

			if (left.size() == right.size()) {
				left.add(num);

				if (!right.isEmpty() && left.peek() > right.peek()) {
					right.add(left.poll());
					left.add(right.poll());
				}

			} else {
				right.add(num);

				if (left.peek() > right.peek()) {
					right.add(left.poll());
					left.add(right.poll());
				}
			}
			sb.append(left.peek()).append("\n");
		}
		System.out.println(sb);
	}
}