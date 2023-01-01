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

		Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int num = 1;
		while (st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
			stack.add(n);

			while (!stack.isEmpty() && stack.peek() == num) {
				stack.pop();
				num++;
			}
		}
		boolean flag = true;
		if (!stack.isEmpty())
			flag = false;
		System.out.println(flag ? "Nice" : "Sad");
	}
}