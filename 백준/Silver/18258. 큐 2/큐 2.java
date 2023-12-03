import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder ans = new StringBuilder();
		Deque<Integer> dq = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			int b = 0;
			if (st.hasMoreTokens())
				b = Integer.parseInt(st.nextToken());
			switch (a) {
			case "push":
				dq.push(b);
				break;
			case "pop":
				if (dq.isEmpty())
					ans.append("-1");
				else
					ans.append(dq.pollLast());
				ans.append("\n");
				break;
			case "size":
				ans.append(dq.size()).append("\n");
				break;
			case "empty":
				if (dq.isEmpty())
					ans.append("1");
				else
					ans.append("0");
				ans.append("\n");
				break;
			case "front":
				if (dq.isEmpty())
					ans.append("-1");
				else
					ans.append(dq.peekLast());
				ans.append("\n");
				break;
			case "back":
				if (dq.isEmpty())
					ans.append("-1");
				else
					ans.append(dq.peekFirst());
				ans.append("\n");
				break;
			}
		}
		System.out.println(ans);
	}
}