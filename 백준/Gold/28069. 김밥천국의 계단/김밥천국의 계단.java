import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		int[] min = new int[N + 1];
		Arrays.fill(min, N);
		min[0] = 0;
		for (int i = 1; i <= N; i++) {
			min[i] = Math.min(min[i], min[i - 1] + 1);
			if (i + i / 2 <= N)
				min[i + i / 2] = Math.min(min[i] + 1, min[i + i / 2]);
		}
		System.out.println(min[N] <= K ? "minigimbob" : "water");
	}
}