import java.util.Scanner;

public class Main {

	static int ans;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		// 정확하게 N킬로그램
		// 3 or 5

		int number3 = 0;
		int number5 = N / 5; // N을 5로 나눈 나머지 몫
		N %= 5;

		boolean flag = false;

		while (true) {

			if (N % 3 == 0) {
				number3 = N / 3;
				flag = true;
				break;
			}

			N += 5;
			number5 = number5 - 1;

			if (number5 < 0)
				break;

		}
		
		System.out.println(flag?(number3+number5):-1);

	}

}