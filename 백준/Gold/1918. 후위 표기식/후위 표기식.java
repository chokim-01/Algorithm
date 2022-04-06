import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		String str = sc.next();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c - 'A' >= 0 && c - 'A' <= 26) {
				sb.append(c);
				continue;
			}
			if (c == ')') {
				while (stack.peek() != '(')
					sb.append(stack.pop());
				stack.pop();
			} else if (c == '(')
				stack.push(c);
			else {
				while (!stack.isEmpty() && prePare(stack.peek()) >= prePare(c)) {
					sb.append(stack.pop());
				}
				stack.push(c);
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb.toString());
	}

	static int prePare(char c) {
		if (c == '+' || c == '-')
			return 1;
		if (c == '*' || c == '/')
			return 2;
		return 0;
	}
}