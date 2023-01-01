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

		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());

			switch (st.nextToken()) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				stack.push(num);
				break;
			case "pop":
				if(stack.isEmpty())
					bw.write("-1");
				else
					bw.write(String.valueOf(stack.pop()));
				bw.newLine();
				break;
			case "top":
				if (stack.isEmpty())
					bw.write("-1");
				else
					bw.write(String.valueOf(stack.peek()));
				bw.newLine();
				break;
			case "size":
				bw.write(String.valueOf(stack.size()));
				bw.newLine();
				break;
			case "empty":
				if (stack.isEmpty())
					bw.write("1");
				else
					bw.write("0");
				bw.newLine();
				break;
			}
		}
		bw.flush();
		bw.close();
	}
}