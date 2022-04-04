import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static int dxy[][] = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int[][] map;

	static LinkedList<Node> cloudLoc;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// map
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		// cloud
		int[][] cloudMoveArr = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			cloudMoveArr[i][0] = Integer.parseInt(st.nextToken()) - 1;
			cloudMoveArr[i][1] = Integer.parseInt(st.nextToken());
		}

		// 구름 위치
		cloudLoc = new LinkedList<>();
		cloudLoc.add(new Node(N - 1, 0));
		cloudLoc.add(new Node(N - 1, 1));
		cloudLoc.add(new Node(N - 2, 0));
		cloudLoc.add(new Node(N - 2, 1));

		// M번 반복
		for (int t = 0; t < M; t++) {
			// 1. 구름 이동
			cloudMove(cloudMoveArr[t][0], cloudMoveArr[t][1]);
			// 2. 비를 뿌림
			cloudRain();
			// 3. 구름 사라짐

			// 4. 물복사 버그
			cloudAddWater();
			// 구름 갱신
			cloudSet();
		}
		System.out.println(getAns());

	}

	// 1. 구름 이동
	static void cloudMove(int dir, int cnt) {
		for (int i = 0; i < cloudLoc.size(); i++) {
			Node n = cloudLoc.get(i);

			int nx = n.x + cnt * dxy[dir][0];
			int ny = n.y + cnt * dxy[dir][1];

			nx = (nx + cnt*N) % N;
			ny = (ny + cnt*N) % N;

			cloudLoc.set(i, new Node(nx, ny));
		}
	}

	// 2. 구름이 비를 뿌림
	static void cloudRain() {
		for (int i = 0; i < cloudLoc.size(); i++) {
			Node n = cloudLoc.get(i);

			map[n.x][n.y] += 1;
		}
	}

	// 4-1. 물 양의 증가
	static void cloudAddWater() {
		for (int i = 0; i < cloudLoc.size(); i++) {
			Node n = cloudLoc.get(i);
			int cnt = calcWater(n.x, n.y);
			map[n.x][n.y] += cnt;
		}
	}

	// 4-2. 구름이 증가한 칸 대각선 거리 1 검사
	static int calcWater(int x, int y) {
		int cnt = 0;
		for (int d = 1; d < 8; d += 2) {
			int nx = x + dxy[d][0];
			int ny = y + dxy[d][1];
			if (!mapChk(nx, ny) || map[nx][ny] == 0)
				continue;
			cnt += 1;
		}
		return cnt;
	}

	// 5. 구름 생성
	static void cloudSet() {
		boolean[][] visit = new boolean[N][N];

		for (Node n : cloudLoc)
			visit[n.x][n.y] = true;
		cloudLoc.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] < 2 || visit[i][j])
					continue;
				map[i][j] -= 2;
				cloudLoc.add(new Node(i, j));

			}
		}
	}

	// 6. 물 양
	static int getAns() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}

	// 출력
	static void printArr() {
		for (int i = 0; i < N; i++)
			System.out.println(Arrays.toString(map[i]));
	}

	// 맵검사
	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}
}