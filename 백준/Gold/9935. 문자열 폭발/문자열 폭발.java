import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s1 = br.readLine();
		String s2 = br.readLine();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s1.length(); i++) {
			stack.push(s1.charAt(i));
			if (stack.size() >= s2.length()) {
				// flag
				boolean flag = true;
				for (int j = 0; j < s2.length(); j++) {
					if (stack.get(stack.size() - s2.length() + j) != s2.charAt(j)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					int c = s2.length();
					while (c-- > 0)
						stack.pop();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		if (stack.isEmpty()) {
			sb.append("FRULA");
		} else {
			for (char c : stack) {
				sb.append(c);
			}
		}
		System.out.println(sb);

	}
}