import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Aris aris;
	static boolean[][] dust;
	static boolean[][][] v;
	static int[][][] rule;

	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static class Aris {
		int x, y, d;
		boolean f;

		public Aris(int x, int y, int d) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.d = d;
			this.f = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);

		int[][] saveMap = new int[N][M];
		int save = 0;
		int cnt = 0;
		while (true) {
			if (!mapChk(aris.x, aris.y) || (v[aris.x][aris.y][aris.d] && save == saveMap[aris.x][aris.y]))
				break;
			cnt++;
			int pd = 1;
			if (dust[aris.x][aris.y]) {
				aris.f = false;
				pd = 0;
				saveMap[aris.x][aris.y] = save = cnt;
				dust[aris.x][aris.y] = false;
			} else
				v[aris.x][aris.y][aris.d] = true;
			aris.d = (rule[pd][aris.x][aris.y] + aris.d) % 4;
			aris.x = aris.x + dxy[aris.d][0];
			aris.y = aris.y + dxy[aris.d][1];
		}
		System.out.println(save);
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}

	static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		aris = new Aris(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));

		rule = new int[2][N][M];
		v = new boolean[N][M][4];
		dust = new boolean[N][M];
		for (int i = 0; i < N; i++)
			Arrays.fill(dust[i], true);
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++)
					rule[k][i][j] = s.charAt(j) - '0';
			}
		}
	}
}
