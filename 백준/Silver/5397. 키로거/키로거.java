import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb;
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			String s = br.readLine();
			
			for (int i = 0; i < s.length(); i++) {

				switch (s.charAt(i)) {
				case '<':
					if (!left.isEmpty())
						right.push(left.pop());
					break;
				case '>':
					if (!right.isEmpty())
						left.push(right.pop());
					break;
				case '-':
					if (!left.isEmpty())
						left.pop();
					break;
				default:
					left.push(s.charAt(i));

				}
			}
			sb = new StringBuilder();
			while (!left.isEmpty())
				right.push(left.pop());
			while (!right.isEmpty())
				sb.append(right.pop());
			bw.write(sb.toString()+"\n");
		}
		bw.flush();
		bw.close();
	}
}