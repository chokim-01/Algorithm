import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int[] arr = IntStream.range(1, 45).map(x -> (x * (x + 1)) >> 1).toArray();

		boolean[] save = new boolean[1001];
		for (int i : arr)
			for (int j : arr)
				for (int k : arr)
					if (i + j + k > 1000)
						break;
					else
						save[i + j + k] = true;

		StringBuilder ans = new StringBuilder();
		while (K-- > 0)
			ans.append(save[sc.nextInt()] ? 1 : 0).append("\n");

		System.out.println(ans);
	}
}