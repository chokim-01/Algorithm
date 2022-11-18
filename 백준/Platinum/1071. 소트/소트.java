import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, min;
	static int[] numbers;
	static StringBuffer sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuffer();
		numbers = new int[1002];
		N = sc.nextInt();

		int numCnt = 0;
		for (int i = 0; i < N; i++) {
			int n = sc.nextInt();
			if (numbers[n] == 0)
				numCnt += 1;
			numbers[n] += 1;

		}
		int now = -2;
		while (true) {
			int next = getNow(now);
			if (next == -1)
				break;

			if (numCnt == 2 && Math.abs(getSide(next) - next) == 1) {
				print(next);
			} else {
				sb.append(next + " ");
				numbers[next]--;
				if (numbers[next] == 0)
					numCnt--;
				now = next;
			}
		}
		System.out.println(sb.toString());
	}

	static void print(int next) {
		for (int a = 1; a >= -1; a--)
			for (int i = 0; i < numbers[next + a]; i++)
				sb.append((next + a) + " ");

		numbers[next] = 0;
		numbers[next + 1] = 0;
		numbers[next - 1] = 0;
	}

	static int getSide(int x) {
		for (int i = 0; i < numbers.length; i++) {
			if (x == i)
				continue;
			if (numbers[i] > 0)
				return i;
		}
		return -1;
	}

	static int getNow(int now) {
		for (int i = 0; i < numbers.length; i++) {
			if (i == now + 1)
				continue;
			if (numbers[i] > 0)
				return i;
		}
		return -1;
	}
}