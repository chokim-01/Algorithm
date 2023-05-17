import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		int index = 0;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			arr[index][0] = Integer.parseInt(st.nextToken());
			arr[index++][1] = Integer.parseInt(st.nextToken());
		}
		int[][] result = Arrays.stream(arr).sorted(Comparator.comparingInt((int[] a) -> a[0]).thenComparing(b -> b[1]))
				.toArray(int[][]::new);
		for (int[] re : result) {
			for (int r : re) {
				sb.append(r + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}