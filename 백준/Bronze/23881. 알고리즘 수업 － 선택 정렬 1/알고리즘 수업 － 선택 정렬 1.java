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
			count++;
			if (count == K) {
				ans1 = arr[num];
				ans2 = arr[N - i - 1];
				break;
			}
			int tmp = arr[num];
			arr[num] = arr[N - i - 1];
			arr[N - i - 1] = tmp;
		}
		if (ans1 > ans2) {
			int tmp = ans1;
			ans1 = ans2;
			ans2 = tmp;
		}
		System.out.println(ans1 == -1 ? -1 : (ans1 + " " + ans2));
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
