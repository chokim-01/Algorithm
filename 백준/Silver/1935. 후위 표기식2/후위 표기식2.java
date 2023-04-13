import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// A - Z : 26
		Double[] alphabets = new Double[26];
		// 피 연산자 개수
		int N = sc.nextInt();

		// 문자들
		String str = sc.next();

		// A,B,C,D,E,....
		for (int i = 0; i < N; i++) {
			alphabets[i] = sc.nextDouble();
		}

		Stack<Double> stack = new Stack<>();
		for (char c : str.toCharArray()) {
			if (c >= 65 && c <= 90) { // A - Z이다.
				stack.push(alphabets[c - 'A']);
			} else {
				double number1 = stack.pop();
				double number2 = stack.pop();
				double calcNumber = 0;
				switch (c) {
				case '*':
					calcNumber = number1 * number2;
					break;
				case '+':
					calcNumber = number1 + number2;
					break;
				case '/':
					calcNumber = (double) number2 / number1;
					break;
				case '-':
					calcNumber = number2 - number1;
					break;
				}
				stack.push(calcNumber);
			}
		}
		System.out.printf("%.2f", stack.pop());

	}
}