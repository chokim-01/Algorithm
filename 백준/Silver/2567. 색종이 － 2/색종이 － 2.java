import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		map = new boolean[100][100];

		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			for (int i = 90 - a; i < 100 - a; i++)
				for (int j = b; j < b + 10; j++)
					map[i][j] = true;
		}

		int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (!map[i][j])
					continue;
				for (int d = 0; d < 4; d++) {
					int nx = i + dxy[d][0];
					int ny = j + dxy[d][1];
					if (mapChk(nx, ny))
						continue;
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= 100 || y >= 100 || !map[x][y])
			return false;
		return true;
	}
}