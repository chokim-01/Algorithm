import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] map = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int min = map[0];
		for (int i = 1; i < N; i++) {
			if (min <= map[i])
				map[i] = min;
			if (min > map[i])
				min = map[i];
		}
		int[] map2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int ans = 0;
		for (int i = 0; i < M; i++) {
			if (map2[i] > map[0])
				break;
			for (int j = N - 1; j >= 0; j--) {
				if (map2[i] <= map[j]) {
					N = j;
					if (i == M - 1)
						ans = j + 1;
					break;
				}
			}
		}

		System.out.println(ans);
	}
}