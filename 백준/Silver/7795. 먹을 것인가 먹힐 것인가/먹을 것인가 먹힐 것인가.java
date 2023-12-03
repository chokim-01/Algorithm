import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuilder ans = new StringBuilder();
		sc.nextLine();
		while (T-- > 0) {
			sc.nextLine();
			int arr[] = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int brr[] = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			Arrays.sort(arr);
			Arrays.sort(brr);
			int cnt = 0;
			for (int a : arr) {
				int left = 0;
				int right = brr.length;
				int ret = 0;
				while (left < right) {
					int mid = (left + right) >> 1;
					if (brr[mid] < a) {
						ret = mid + 1;
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				cnt += ret;
			}
			ans.append(cnt).append("\n");
		}
		System.out.println(ans);

	}
}