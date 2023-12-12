import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] nums = Stream.of(("0 " + sc.nextLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] d = new int[N + 1];
		int max = 1000000;
		Arrays.fill(d, max);
		d[1] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N && j <= i + nums[i]; j++)
				d[j] = Math.min(d[i] + 1, d[j]);
		}
		System.out.println(d[N] == max ? -1 : d[N]);
	}
}
