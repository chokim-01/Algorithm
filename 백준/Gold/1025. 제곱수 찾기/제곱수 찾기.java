import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		Arrays.setAll(map, i -> {
			try {
				return br.readLine().chars().map(Character::getNumericValue).toArray();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		int max = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int pi = -N; pi < N; pi++) {
					for (int pj = -M; pj < M; pj++) {
						if (pi == 0 && pj == 0)
							continue;
						int num = 0;
						int x = i;
						int y = j;
						while (mapChk(x, y)) {
							num = num * 10 + map[x][y];
							int sNum = (int) Math.sqrt(num);
							if (sNum * sNum == num)
								max = Math.max(max, num);
							x += pi;
							y += pj;
						}
					}
				}
			}
		}
		System.out.println(max);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}
