import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][];
		while (N-- > 0)
			arr[N] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(arr,(o1, o2) -> o2[1] == o1[1] ? (o2[2] == o1[2] ? o2[3] - o1[3] : o2[2] - o1[2]) : (o2[1] - o1[1]));

		for (int i = 0; i < arr.length; i++)
			if (arr[i][0] == K) {
				System.out.println(i + 1);
				return;
			}
	}
}