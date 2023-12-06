import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long ans = 0;
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (!s.isEmpty() && s.peek() > n) {
				ans += s.size();
			} else if (!s.isEmpty() && s.peek() <= n) {
				while (!s.isEmpty() && s.peek() <= n)
					s.pop();
				if (!s.isEmpty()) {
					ans += s.size();
					if (s.peek() == n)
						continue;
				}
			}
			s.push(n);
		}
		System.out.println(ans);
	}
}