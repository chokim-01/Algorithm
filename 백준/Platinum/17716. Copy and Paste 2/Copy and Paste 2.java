import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int K, N;
	static String s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		s = br.readLine();
		StringBuilder ans = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		// s, t, m
		int[][] o = new int[N][3];
		for (int i = N - 1; i >= 0; i--)
			o[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int i = 0; i < K; i++) {
			int now = i;
			for (int j = 0; j < N; j++) {
				if (now < o[j][2])
					continue;
				if (now < o[j][1] - o[j][0] + o[j][2])
					now += o[j][0] - o[j][2];
				else
					now += o[j][0] - o[j][1];
			}
			ans.append(s.charAt(now));
		}
		System.out.println(ans);

	}
}