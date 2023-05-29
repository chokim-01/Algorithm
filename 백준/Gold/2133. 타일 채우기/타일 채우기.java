import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		int N = new Scanner(System.in).nextInt();
		if (N % 2 == 1)
			System.out.println(0);
		else {
			int[] arr = new int[N + 1];
			arr[2] = 3;
			if (N >= 4) {
				arr[4] = 11;
				int bef = 0;
				for (int i = 6; i <= N; i += 2) {
					bef += arr[i - 4] * 2;
					arr[i] = arr[i - 2] * 3 + 2 + bef;
				}
			}
			System.out.println(arr[N]);
		}
	}
}