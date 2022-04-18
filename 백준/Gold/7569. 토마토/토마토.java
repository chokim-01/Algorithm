import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Nodee {
	int x, y, z, cnt;

	public Nodee(int x, int y, int z, int cnt) {
// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.z = z;
		this.cnt = cnt;
	}
}

public class Main {

	static int[][] dxyz = { { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
	static Queue<Nodee> q = new LinkedList<>();
	static int[][] map[];

	static int M, N, H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N][M];

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		int count = 0;
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[k][i][j] == 1) {
						q.add(new Nodee(i, j, k, 0));
					}
				}
			}
		}
		while (!q.isEmpty()) {
			Nodee n = q.poll();
			count = n.cnt;
			for (int i = 0; i < dxyz.length; i++) {
				int nx = n.x + dxyz[i][0];
				int ny = n.y + dxyz[i][1];
				int nz = n.z + dxyz[i][2];

				if (nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= M || nz >= H)
					continue;
				if (map[nz][nx][ny] == 0) {
					q.add(new Nodee(nx, ny, nz, n.cnt + 1));
					map[nz][nx][ny] = 1;
				}

			}
		}

		int cnt = 0;
		for (int k = 0; k < H; k++)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (map[k][i][j] == 0)
						cnt++;

		System.out.println(cnt == 0 ? count : -1);

	}

}
