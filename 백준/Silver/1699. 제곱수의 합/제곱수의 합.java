import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] ans = new int[num + 1];
		IntStream.rangeClosed(1, num).forEach(i -> {
			ans[i] = i;
			for (int j = 1; j * j <= i; j++)
				ans[i] = Math.min(ans[i], ans[i - j * j] + 1);
		});
		System.out.println(ans[num]);
	}
}