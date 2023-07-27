import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		M = 1000001;
		int[] min = new int[M];
		Arrays.fill(min, M);
		min[1] = 1;
		min[2] = 2;
		min[3] = 3;

		for (int i = 3; i < M; i++) {
			min[i] = Math.min(min[i], min[i - 1] + 1);
			if (i + i / 2 < M)
				min[i + i / 2] = Math.min(min[i] + 1, min[i + i / 2]);
		}
		System.out.println(min[N] <= K ? "minigimbob" : "water");
	}
}