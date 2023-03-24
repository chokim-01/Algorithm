import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		sc.nextLine();
		int[] arr = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int ans1 = -1;
		int ans2 = -1;

		int count = 0;
		for (int i = 0; i < N; i++) {
			// find max
			int num = findMax(arr, i);
			if (num == N - i - 1)
				continue;
			if (count == K)
				break;
			count++;
			int tmp = arr[num];
			arr[num] = arr[N - i - 1];
			arr[N - i - 1] = tmp;
		}
		if (count == K)
			for (int i = 0; i < N; i++)
				System.out.print(arr[i] + " ");
		else
			System.out.println(-1);

	}

	static int findMax(int[] arr, int count) {
		int index = 0;
		int max = -1;
		for (int i = 0; i < arr.length - count; i++) {
			if (max < arr[i]) {
				max = arr[i];
				index = i;
			}
		}
		return index;
	}
}
