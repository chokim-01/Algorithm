import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String except = br.readLine();
		StringBuffer str = new StringBuffer(br.readLine());

		int idxLeft = 0;
		int idxRight = str.length() - 1;
		int len = except.length();

		Stack<Character> stackL = new Stack<>();
		Stack<Character> stackR = new Stack<>();

		boolean lrFlag = true;

		while (idxLeft <= idxRight) {
			if (lrFlag) {
				char a = str.charAt(idxLeft);
				stackL.add(a);
				if (idxLeft - len >= -1 && stackL.size() >= len) {
					if (stackL.peek() == str.charAt(idxLeft)) {
						boolean flag = true;
						for (int i = 0; i < len; i++) {
							if (stackL.get(stackL.size() - 1 - i) != except.charAt(len - i - 1)) {
								flag = false;
								break;
							}
						}
						if (flag) {
							lrFlag = false;
							for (int i = 0; i < len; i++)
								stackL.pop();
						}
					}
				}
				idxLeft++;
			} else {
				char a = str.charAt(idxRight);
				stackR.add(a);
				if (stackR.size() >= len) {
					if (stackR.peek() == str.charAt(idxRight)) {

						boolean flag = true;
						for (int i = 0; i < len; i++) {
							if (stackR.get(stackR.size() - 1 - i) != except.charAt(i)) {
								flag = false;
								break;
							}
						}
						if (flag) {
							lrFlag = true;
							for (int i = 0; i < len; i++)
								stackR.pop();
						}
					}
				}
				idxRight--;
			}
		}
		StringBuffer ans = new StringBuffer();
		for (int i = 0; i < stackL.size(); i++)
			ans.append(stackL.get(i));
		while (!stackR.isEmpty())
			ans.append(stackR.pop());

		while(ans.toString().contains(except)) {
			int tmp = ans.indexOf(except);
			ans.delete(tmp, tmp + len);
		}

			System.out.println(ans.toString());

	}

}
