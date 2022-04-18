import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class Main {

	static int T;
	static int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static StringBuffer sb;
	static LinkedList<String> answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuffer();
		T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			answer = new LinkedList<String>();
			rec(0, new int[N - 1]);
			Collections.sort(answer);
			for(String s : answer)
				System.out.println(s);
			System.out.println();
		}

	}

	static void rec(int index, int[] between) { // + - *
		if (index == between.length) {
			// 2 : 문자 합치기
			Stack<Integer> s = new Stack<>();
			s.push(1);
			for (int i = 0; i < between.length; i++) {
				if (between[i] == 2) {
					int n = s.pop();
					s.push(n * 10 + (n < 0 ? -nums[i + 1] : nums[i + 1]));
				} else if (between[i] == 1) {
					s.push(-nums[i + 1]);
				} else {
					s.push(nums[i + 1]);
				}
			}
			int ans = 0;
			for (int i : s)
				ans += i;
			if (ans != 0)
				return;
			sb = new StringBuffer();
			for (int n : s) {
				if (n / 10 != 0) {
					if (n > 0 && sb.length() != 0)
						sb.append("+");
					char[] a = String.valueOf(n).toCharArray();
					for (int ab = 0; ab < a.length; ab++) {
						sb.append(a[ab]);
						if (ab != a.length - 1 && a[ab] != '-')
							sb.append(" ");
					}
				} else {
					sb.append(n > 0 ? n == 1 ? n : ("+" + n) : n);
				}
			}
			answer.add(sb.toString());
			return;
		}
		for (int i = 0; i < 3; i++) {
			int tmp = between[index];
			between[index] = i;
			rec(index + 1, between);
			between[index] = tmp;
		}
	}
}
