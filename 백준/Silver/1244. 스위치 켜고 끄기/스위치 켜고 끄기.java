import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == 1) {
				int c = b;
				while (b <= N) {
					arr[b] += 1;
					b += c;
				}

			} else {
				arr[b] += 1;
				int plus = 1;
				while (b + plus <= N && b - plus > 0) {
					if (arr[b + plus] % 2 != arr[b - plus] % 2)
						break;

					arr[b + plus] += 1;
					arr[b - plus] += 1;
					plus++;
				}
			}
		}
		for (int i = 1; i < arr.length; i++) {
			System.out.print(arr[i] % 2 == 1 ? "1 " : "0 ");
			if (i % 20 == 0)
				System.out.println();
		}
		System.out.println();

	}
}