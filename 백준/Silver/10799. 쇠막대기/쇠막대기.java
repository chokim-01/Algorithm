import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		char[] mathO = br.readLine().replace("()", "|").toCharArray();
		Stack<Character> stack = new Stack<>();
		int ans = 0;
		for (char c : mathO) {
			if (c == '(') {
				stack.push(c);
			} else if(c == '|'){
				ans += stack.size();
			} else {
				stack.pop();
				ans +=1;
			}
		}
		System.out.println(ans);
	}
}