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

		int[] counts = new int[5];
		int[][] arr = new int[6][2];
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			arr[i][0] = c;
			arr[i][1] = Integer.parseInt(st.nextToken());
			counts[c]++;
		}
		int big = 1;
		for (int[] a : arr)
			if (counts[a[0]] == 1)
				big *= a[1];

		int small = 1;
		for (int i = 0; i < 6; i++) {
			if (counts[arr[i][0]] == 1)
				continue;
			if (counts[arr[i][0]] == counts[arr[(i + 2) % 6][0]])
				small *= arr[(i + 1) % 6][1];
		}
		System.out.println((big - small) * N);

	}
}