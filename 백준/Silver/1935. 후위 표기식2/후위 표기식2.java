import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		HashMap<Character, Integer> map = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		char[] mathO = br.readLine().toCharArray();
		char index = 'A';
		Stack<Double> stack = new Stack<>();
		while (N-- > 0)
			map.put(index++, Integer.parseInt(br.readLine()));

		for (char m : mathO) {
			if (m >= 65 && m <= 90)
				stack.push((double) map.get(m));
			else {
				double n = stack.pop();
				double n2 = stack.pop();
				switch (m) {
				case '*':
					stack.push(n * n2);
					break;
				case '+':
					stack.push(n + n2);
					break;
				case '/':
					stack.push(n2 / n);
					break;
				case '-':
					stack.push( n2-n);
					break;
				default:
					break;
				}
			}
		}
		System.out.format("%.2f", stack.peek());
	}
}