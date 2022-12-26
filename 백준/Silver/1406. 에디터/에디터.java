import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++)
			left.push(s.charAt(i));

		int t = Integer.parseInt(br.readLine());
		char o = '.';
		char c = '.';
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			o = st.nextToken().charAt(0);

			if (st.hasMoreTokens())
				c = st.nextToken().charAt(0);
			if (o == 'P') {
				left.push(c);
			} else if (o == 'L') {
				if (!left.isEmpty())
					right.push(left.pop());
			} else if (o == 'D') {
				if (!right.isEmpty())
					left.push(right.pop());
			} else if (!left.isEmpty()) {
				left.pop();
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!left.isEmpty())
			right.push(left.pop());
		while (!right.isEmpty())
			sb.append(right.pop());
		System.out.println(sb.toString());
	}
}