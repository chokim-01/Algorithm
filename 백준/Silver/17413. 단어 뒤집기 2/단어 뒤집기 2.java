import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		Stack<Character> stack = new Stack<>();
		String s = br.readLine();
		boolean flag = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '<') {
				while (!stack.isEmpty())
					sb.append(stack.pop());
				flag = true;
			}
			if (flag) {
				sb.append(s.charAt(i));
				if (c == '>') 
					flag = false;
			} else {
				if(c == ' ') {
					while(!stack.isEmpty())
						sb.append(stack.pop());
					sb.append(c);
					continue;
				}
				stack.push(c);
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb.toString());
	}
}
