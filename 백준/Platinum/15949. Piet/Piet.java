import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw;

	static HashMap<Integer, ArrayList<Node>> codel;
	static int[][] mapNum;
	static char[][] map;
	static int N, M;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[" + x + "," + y + "] ";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		mapNum = new int[N][M];

		codel = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);

			}
		}
		makeMap();

		// 첫 위치 : 0,0
		int x = 0;
		int y = 0;
		int DP = 1;
		int CC = 3;

		while (true) {
			bw.write(map[x][y]);
			Node n = null;
			for (int d = 0; d < 4; d++) {
				n = sort(mapNum[x][y], DP, CC);
				if (n == null) {
					CC = (CC + 2) % 4;
					n = sort(mapNum[x][y], DP, CC);
				}
				if (n != null)
					break;
				DP = (DP + 1) % 4;
			}
			if (n == null)
				break;
			x = n.x + dxy[DP][0];
			y = n.y + dxy[DP][1];
		}
		bw.flush();
		bw.close();
	}

	static void makeMap() {
		int count = -1;
		for (int i = 0; i < N; i++)
			Arrays.fill(mapNum[i], -1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'X' || mapNum[i][j] != -1)
					continue;
				count++;

				// 번호에 해당하는 codel 추가.
				codel.put(count, new ArrayList<>());
				mapNum[i][j] = count;
				ArrayList<Node> l = codel.get(count);
				l.add(new Node(i, j));

				Queue<Node> q = new ArrayDeque<>();
				q.add(new Node(i, j));
				while (!q.isEmpty()) {
					Node n = q.poll();
					for (int d = 0; d < 4; d++) {
						int nx = n.x + dxy[d][0];
						int ny = n.y + dxy[d][1];
						if (!mapChk(nx, ny) || mapNum[nx][ny] != -1 || map[i][j] != map[nx][ny])
							continue;
						mapNum[nx][ny] = count;
						l.add(new Node(nx, ny));
						q.add(new Node(nx, ny));
					}
				}
			}
		}
	}

	static int[][] dxy = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	// 1. DP 방향으로 가장 멀리 위치한 코델을 찾음.
	// 2. CC 방향으로 가장 끝에 있는 코델 선택
	// 3-1. 선택한 코델에서 DP방향으로 맞닿은 코델이 포함된 블록이 다음 블록
	// 3-2.false 맞닿은 블록이 격자 밖 또는 검은색 일 경우
	// 총 8회 시도.
	// 3-2-1. CC 값 반대.
	// 3-2-2. false -> CC 유지 후 DP 시계방향
	// 3-2-1과 3-2-2를 반복. 총 8회
	// 3-3-3. 실패 시
	// 3-3. to 1
	static Node sort(int c, int dp, int cc) {
		int dpcc = (dp + cc) % 4;
		ArrayList<Node> list = codel.get(c);
		// DP -> CC 로 코델 선택
		/*
		 * dp 0 cc 1 => 상,우 dpcc => 1 dp 0 cc 3 => 상,좌 dpcc => 3 dp 1 cc 1 => 우,하 dpcc
		 * => 2 dp 1 cc 3 => 우,상 dpcc => 0 dp 2 cc 1 => 하,좌 dpcc => 3 dp 2 cc 3 => 하,우
		 * dpcc => 1 dp 3 cc 1 => 좌,상 dpcc => 0 dp 3 cc 3 => 좌,하 dpcc => 2 ... dpcc =>
		 * (dp+cc)%4
		 */
		// 정렬 후 첫번 째가 정답.
		Collections.sort(list, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// dp 우선, dpcc 후순.
				// TODO Auto-generated method stub
				int xCnt = o1.x - o2.x;
				int yCnt = o1.y - o2.y;
				if (dp == 0) {
					if (xCnt == 0) {
						if (dpcc == 1)
							return -yCnt;
						return yCnt;
					}
					return xCnt;
				} else if (dp == 1) {
					if (yCnt == 0) {
						if (dpcc == 2)
							return -xCnt;
						return xCnt;
					}
					return -yCnt;
				} else if (dp == 2) {
					if (xCnt == 0) {
						if (dpcc == 3)
							return yCnt;
						return -yCnt;
					}
					return -xCnt;
				} else {
					if (yCnt == 0) {
						if (dpcc == 0)
							return xCnt;
						return -xCnt;
					}
					return yCnt;
				}
			}
		});
		Node n = list.get(0); // 선택

		int nx = n.x + dxy[dp][0];
		int ny = n.y + dxy[dp][1];
		if (!mapChk(nx, ny) || map[nx][ny] == 'X')
			return null;

		return n;
	}

	static boolean mapChk(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M)
			return false;
		return true;
	}
}
