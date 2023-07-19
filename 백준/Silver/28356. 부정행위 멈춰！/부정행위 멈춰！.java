import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[2][M];
		int cnt = -1;
		int max = 4;
		if (N == 1 && M == 1)
			max = 1;
		else if (N == 1 || M == 1)
			max = 2;
		for (int i = 0; i < 2; i++) {
			if (i == N)
				break;

			for (int j = 0; j < M; j++)
				map[i][j] = (++cnt) % max + 1;
			cnt = (++cnt + (M % 1) == 1 ? 1 : 0) % max + 1;
		}
		System.out.println(max);
		printMap(map);
	}

	static void printMap(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				sb.append(map[i % 2][j] + " ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}